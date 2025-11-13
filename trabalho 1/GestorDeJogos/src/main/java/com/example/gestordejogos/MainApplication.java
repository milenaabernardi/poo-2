package com.example.gestordejogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    // Eu presumi que você está tentando carregar esta tela,
    // com base nos erros anteriores.
    // Se for outro arquivo (ex: "GerenciarEstudios.fxml"), apenas mude o nome aqui.
    private static final String FXML_PATH = "GerenciarJogos.fxml";

    @Override
    public void start(Stage stage) {
        try {
            // 1. Tenta carregar o FXML
            URL fxmlUrl = getClass().getResource(FXML_PATH);

            // 2. Verifica se o arquivo FXML foi encontrado
            if (fxmlUrl == null) {
                // Se o arquivo .fxml não for encontrado, este será o erro.
                System.err.println("Erro Crítico: Não foi possível encontrar o arquivo FXML: " + FXML_PATH);
                System.err.println("Verifique se o nome está correto e se o arquivo está na pasta 'resources/com/example/gestordejogos/'");
                throw new IOException("Arquivo FXML não encontrado: " + FXML_PATH);
            }

            // 3. Tenta carregar a cena (é aqui que o initialize() do seu Controller é chamado)
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(fxmlLoader.load(), 600, 450); // Ajuste o tamanho (W, H)

            // 4. Se tudo deu certo, mostra a tela
            stage.setTitle("Gestor de Jogos e Estúdios");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // !! ESTA É A PARTE IMPORTANTE !!
            // Se qualquer coisa falhar (no load, no initialize do controller, etc.),
            // o erro real será impresso aqui.
            System.err.println("### A APLICAÇÃO FALHOU AO INICIAR ###");
            System.err.println("Causa do erro:");
            e.printStackTrace(); // Isso vai imprimir o "stack trace" que precisamos!
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}