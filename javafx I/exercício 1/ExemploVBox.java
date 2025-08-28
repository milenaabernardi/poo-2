import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExemploVBox extends Application {

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Exemplo VBox");
        Button btn1 = new Button("Botão 1");
        Button btn2 = new Button("Botão 2");
        Button btn3 = new Button("Botão 3");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, btn1, btn2, btn3);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setTitle("Layout VBox");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}