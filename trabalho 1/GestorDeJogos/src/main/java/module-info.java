module com.example.gestordejogos {
    // Requisitos do JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Requisito do Banco de Dados
    requires java.sql;

    // Requisitos das bibliotecas adicionais
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    // --- Permissões Essenciais ---

    // Abre o pacote principal para o JavaFX
    opens com.example.gestordejogos to javafx.fxml;

    // Abre o pacote de controllers para o JavaFX
    opens com.example.gestordejogos.controller to javafx.fxml;

    // Abre o pacote de models para a TableView (javafx.base)
    opens com.example.gestordejogos.model to javafx.base;

    // Exporta seus pacotes para serem usados
    exports com.example.gestordejogos;
    exports com.example.gestordejogos.controller;
    exports com.example.gestordejogos.model;
    exports com.example.gestordejogos.dao;
    exports com.example.gestordejogos.database;
}