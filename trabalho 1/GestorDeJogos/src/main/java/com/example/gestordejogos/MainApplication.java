package com.example.gestordejogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Caminho ABSOLUTO para o arquivo FXML (começa com "/")
        String fxmlPath = "/com/example/gestordejogos/GerenciarJogos.fxml";

        URL fxmlUrl = getClass().getResource(fxmlPath);

        if (fxmlUrl == null) {
            // Erro fatal se o FXML não for encontrado
            System.err.println("Não foi possível encontrar o arquivo FXML: " + fxmlPath);
            throw new IOException("Arquivo FXML não encontrado");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(fxmlLoader.load(), 600, 450); // Ajuste o tamanho (W, H)
        stage.setTitle("Gestor de Jogos e Estúdios");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}