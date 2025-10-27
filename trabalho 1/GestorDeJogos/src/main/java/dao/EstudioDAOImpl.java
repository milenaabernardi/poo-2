package com.example.gestordejogos.dao;

import com.example.gestordejogos.database.ConexaoPostgreSQL;
import com.example.gestordejogos.model.Estudio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementação que contém o código SQL real
public class EstudioDAOImpl implements EstudioDAO {

    private Connection conn;

    public EstudioDAOImpl() throws SQLException {
        this.conn = ConexaoPostgreSQL.getConexao();
    }

    @Override
    public void criar(Estudio estudio) throws SQLException {
        String sql = "INSERT INTO estudio (nome, pais_origem) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estudio.getNome());
            stmt.setString(2, estudio.getPaisOrigem());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir estúdio.");
            throw e;
        }
    }

    @Override
    public List<Estudio> consultar() throws SQLException {
        List<Estudio> estudios = new ArrayList<>();
        String sql = "SELECT * FROM estudio ORDER BY nome";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudio estudio = new Estudio();
                estudio.setId(rs.getInt("id"));
                estudio.setNome(rs.getString("nome"));
                estudio.setPaisOrigem(rs.getString("pais_origem"));
                estudios.add(estudio);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar estúdios.");
            throw e;
        }
        return estudios;
    }

    @Override
    public void atualizar(Estudio estudio) throws SQLException {
        String sql = "UPDATE estudio SET nome = ?, pais_origem = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estudio.getNome());
            stmt.setString(2, estudio.getPaisOrigem());
            stmt.setInt(3, estudio.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estúdio.");
            throw e;
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM estudio WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir estúdio (Pode estar em uso por um Jogo).");
            throw e;
        }
    }
}