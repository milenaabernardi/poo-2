package com.example.gestordejogos.dao;

import com.example.gestordejogos.model.Jogo;
import java.sql.SQLException;
import java.util.List;

public interface JogoDAO {
    void criar(Jogo jogo) throws SQLException;
    List<Jogo> consultar() throws SQLException;
    void atualizar(Jogo jogo) throws SQLException;
    void excluir(int id) throws SQLException;
}