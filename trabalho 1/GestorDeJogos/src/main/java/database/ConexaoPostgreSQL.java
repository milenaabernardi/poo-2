package com.example.gestordejogos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    // !! ATENÇÃO: EDITE COM SEUS DADOS !!
    private static final String URL = "jdbc:postgresql://localhost:5432/steam_db"; // (Nome do seu banco)
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres"; // (Senha que você criou na instalação)

    private static Connection conn;

    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                // Carrega o driver
                Class.forName("org.postgresql.Driver");
                // Estabelece a conexão
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
        return conn;
    }
}