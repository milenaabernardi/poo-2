package com.example.gestordejogos.controller;

import com.example.gestordejogos.dao.EstudioDAO;
import com.example.gestordejogos.dao.EstudioDAOImpl;
import com.example.gestordejogos.dao.JogoDAO;
import com.example.gestordejogos.dao.JogoDAOImpl;
import com.example.gestordejogos.model.Estudio;
import com.example.gestordejogos.model.Jogo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class GerenciarJogosController {

    // Componentes da View (Ligados pelo @FXML)
    @FXML private TextField txtTitulo;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAno;
    @FXML private ComboBox<Estudio> cmbEstudio;
    @FXML private TableView<Jogo> tblJogos;
    @FXML private TableColumn<Jogo, Integer> colId;
    @FXML private TableColumn<Jogo, String> colTitulo;
    @FXML private TableColumn<Jogo, String> colGenero;
    @FXML private TableColumn<Jogo, Integer> colAno;
    @FXML private TableColumn<Jogo, Estudio> colEstudio;

    // DAOs para acessar o banco
    private JogoDAO jogoDAO;
    private EstudioDAO estudioDAO;

    // Objeto para rastrear o item selecionado na tabela
    private Jogo jogoSelecionado;

    // Método executado quando o FXML é carregado
    @FXML
    public void initialize() {
        try {
            // Instancia os DAOs
            jogoDAO = new JogoDAOImpl();
            estudioDAO = new EstudioDAOImpl();

            // Configura a Tabela
            configurarTabela();

            // Carrega os dados iniciais
            carregarEstudiosNoComboBox();
            carregarJogosNaTabela();

            // Adiciona um listener para saber qual item foi selecionado na tabela
            tblJogos.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarJogo(newValue));

        } catch (SQLException e) {
            exibirAlertaErro("Erro de Conexão", "Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Liga as colunas da tabela aos atributos da classe Jogo
    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colEstudio.setCellValueFactory(new PropertyValueFactory<>("estudio")); // Usa o toString() do Estudio
    }

    // Busca estúdios no banco e põe no ComboBox
    private void carregarEstudiosNoComboBox() throws SQLException {
        List<Estudio> estudios = estudioDAO.consultar();
        cmbEstudio.setItems(FXCollections.observableArrayList(estudios));
    }

    // Busca jogos no banco e põe na Tabela
    private void carregarJogosNaTabela() throws SQLException {
        List<Jogo> jogos = jogoDAO.consultar();
        tblJogos.setItems(FXCollections.observableArrayList(jogos));
    }

    // Chamado pelo botão "Salvar"
    @FXML
    void salvarJogo() {
        try {
            String titulo = txtTitulo.getText();
            Estudio estudio = cmbEstudio.getSelectionModel().getSelectedItem();

            // Validação simples (pode melhorar com ValidatorFX)
            if (titulo.isEmpty() || estudio == null) {
                exibirAlertaErro("Dados incompletos", "Título e Estúdio são obrigatórios.");
                return;
            }

            Jogo jogo = new Jogo();
            jogo.setTitulo(titulo);
            jogo.setGenero(txtGenero.getText());
            jogo.setAnoLancamento(Integer.parseInt(txtAno.getText()));
            jogo.setEstudio(estudio);

            if (jogoSelecionado == null) {
                // Se nenhum jogo está selecionado, é um novo (Criar)
                jogoDAO.criar(jogo);
            } else {
                // Se um jogo está selecionado, é uma edição (Atualizar)
                jogo.setId(jogoSelecionado.getId());
                jogoDAO.atualizar(jogo);
            }

            carregarJogosNaTabela(); // Atualiza a tabela
            limparCampos(); // Limpa o formulário

        } catch (NumberFormatException e) {
            exibirAlertaErro("Formato inválido", "O ano deve ser um número.");
        } catch (Exception e) {
            exibirAlertaErro("Erro ao salvar", "Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Chamado pelo botão "Excluir"
    @FXML
    void excluirJogo() {
        if (jogoSelecionado == null) {
            exibirAlertaErro("Nenhum jogo selecionado", "Selecione um jogo na tabela para excluir.");
            return;
        }
        try {
            jogoDAO.excluir(jogoSelecionado.getId());
            carregarJogosNaTabela();
            limparCampos();
        } catch (SQLException e) {
            exibirAlertaErro("Erro ao excluir", "Não foi possível excluir o jogo. Verifique se ele não está sendo usado.");
            e.printStackTrace();
        }
    }

    // Chamado pelo botão "Novo"
    @FXML
    void limparCampos() {
        jogoSelecionado = null; // Remove a seleção
        txtTitulo.clear();
        txtGenero.clear();
        txtAno.clear();
        cmbEstudio.getSelectionModel().clearSelection();
        tblJogos.getSelectionModel().clearSelection();
    }

    // Chamado pelo listener da tabela
    void selecionarJogo(Jogo jogo) {
        jogoSelecionado = jogo;
        if (jogo != null) {
            // Preenche o formulário com os dados do jogo selecionado
            txtTitulo.setText(jogo.getTitulo());
            txtGenero.setText(jogo.getGenero());
            txtAno.setText(String.valueOf(jogo.getAnoLancamento()));
            cmbEstudio.setValue(jogo.getEstudio());
        }
    }

    // Função utilitária para mostrar alertas de erro
    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}