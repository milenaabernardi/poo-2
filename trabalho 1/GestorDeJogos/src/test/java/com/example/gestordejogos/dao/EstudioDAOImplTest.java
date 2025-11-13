package com.example.gestordejogos.dao;

import com.example.gestordejogos.model.Estudio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudioDAOImplTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private EstudioDAOImpl estudioDAO;

    @BeforeEach
    void setUp() throws SQLException {
        // Inicializa o DAO passando o mock da conexão
        estudioDAO = new EstudioDAOImpl(mockConnection);
    }

    @Test
    void testeSalvarEstudio_ComSucesso() throws SQLException {
        Estudio estudio = new Estudio();
        estudio.setId(1);
        estudio.setNome("Nintendo");
        estudio.setPaisOrigem("Japão");

        // CORRIGIDO: de "estudios" para "estudio"
        String sql = "INSERT INTO estudio (nome, pais_origem) VALUES (?, ?)";

        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        estudioDAO.criar(estudio);

        verify(mockPreparedStatement).setString(1, "Nintendo");
        verify(mockPreparedStatement).setString(2, "Japão");
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testeListarEstudios_RetornaListaCheia() throws SQLException {
        // CORRIGIDO: de "estudios" para "estudio"
        String sql = "SELECT * FROM estudio ORDER BY nome";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, true, false);

        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("nome")).thenReturn("Capcom", "Sega");
        when(mockResultSet.getString("pais_origem")).thenReturn("Japão", "Japão");

        List<Estudio> estudios = estudioDAO.consultar();

        assertNotNull(estudios);
        assertEquals(2, estudios.size());
        assertEquals("Capcom", estudios.get(0).getNome());
    }

    @Test
    void testeListarEstudios_RetornaListaVazia() throws SQLException {
        // CORRIGIDO: de "estudios" para "estudio"
        String sql = "SELECT * FROM estudio ORDER BY nome";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(false);

        List<Estudio> estudios = estudioDAO.consultar();

        assertNotNull(estudios);
        assertTrue(estudios.isEmpty());
    }

    @Test
    void testeSalvarEstudio_LancaSQLException() throws SQLException {
        Estudio estudio = new Estudio();
        estudio.setId(1);
        estudio.setNome("Nintendo");
        estudio.setPaisOrigem("Japão");

        // CORRIGIDO: de "estudios" para "estudio"
        String sql = "INSERT INTO estudio (nome, pais_origem) VALUES (?, ?)";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        when(mockPreparedStatement.executeUpdate())
                .thenThrow(new SQLException("Erro de violação de chave única"));

        SQLException exception = assertThrows(SQLException.class, () -> {
            estudioDAO.criar(estudio);
        });

        assertEquals("Erro de violação de chave única", exception.getMessage());
    }
}