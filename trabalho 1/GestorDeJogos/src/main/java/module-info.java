// Localização: src/main/java/module-info.java
module com.example.gestordejogos {

    // --- Requisitos da Aplicação ---
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.postgresql.jdbc;

    // --- Permissões para o JavaFX ---
    opens com.example.gestordejogos to javafx.fxml;
    opens com.example.gestordejogos.controller to javafx.fxml;
    opens com.example.gestordejogos.model to javafx.base;

    // --- Exporta seus pacotes ---
    exports com.example.gestordejogos;
    exports com.example.gestordejogos.controller;
    exports com.example.gestordejogos.model;
    exports com.example.gestordejogos.dao;
    exports com.example.gestordejogos.database;
}