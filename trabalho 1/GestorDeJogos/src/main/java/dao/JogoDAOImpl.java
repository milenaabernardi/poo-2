package com.example.gestordejogos.dao;

import com.example.gestordejogos.database.ConexaoPostgreSQL;
import com.example.gestordejogos.model.Estudio;
import com.example.gestordejogos.model.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoDAOImpl implements JogoDAO {

    private Connection conn;

    public JogoDAOImpl() throws SQLException {
        this.conn = ConexaoPostgreSQL.getConexao();
    }

    @Override
    public void criar(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO jogo (titulo, genero, ano_lancamento, estudio_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogo.getTitulo());
            stmt.setString(2, jogo.getGenero());
            stmt.setInt(3, jogo.getAnoLancamento());
            stmt.setInt(4, jogo.getEstudio().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Jogo> consultar() throws SQLException {
        List<Jogo> jogos = new ArrayList<>();
        // SQL com JOIN para buscar o nome do estúdio
        String sql = "SELECT j.id, j.titulo, j.genero, j.ano_lancamento, " +
                "e.id AS estudio_id, e.nome AS estudio_nome, e.pais_origem AS estudio_pais " +
                "FROM jogo j " +
                "LEFT JOIN estudio e ON j.estudio_id = e.id " +
                "ORDER BY j.titulo";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Cria o objeto Estúdio
                Estudio estudio = new Estudio();
                estudio.setId(rs.getInt("estudio_id"));
                estudio.setNome(rs.getString("estudio_nome"));
                estudio.setPaisOrigem(rs.getString("estudio_pais"));

                // Cria o objeto Jogo
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
                jogo.setEstudio(estudio); // Associa o estúdio ao jogo

                jogos.add(jogo);
            }
        }
        return jogos;
    }

    @Override
    public void atualizar(Jogo jogo) throws SQLException {
        String sql = "UPDATE jogo SET titulo = ?, genero = ?, ano_lancamento = ?, estudio_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogo.getTitulo());
            stmt.setString(2, jogo.getGenero());
            stmt.setInt(3, jogo.getAnoLancamento());
            stmt.setInt(4, jogo.getEstudio().getId());
            stmt.setInt(5, jogo.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM jogo WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}