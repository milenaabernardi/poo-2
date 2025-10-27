package com.example.gestordejogos.dao;

import com.example.gestordejogos.model.Estudio;
import java.sql.SQLException;
import java.util.List;

// Interface define o "contrato" (métodos obrigatórios)
public interface EstudioDAO {
    void criar(Estudio estudio) throws SQLException;
    List<Estudio> consultar() throws SQLException;
    void atualizar(Estudio estudio) throws SQLException;
    void excluir(int id) throws SQLException;
}