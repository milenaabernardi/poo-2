import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ExemploBorderPane extends Application {

    @Override
    public void start(Stage stage) {
        Button top = new Button("Topo");
        Button bottom = new Button("Rodapé");
        Button left = new Button("Esquerda");
        Button right = new Button("Direita");
        Button center = new Button("Centro");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setBottom(bottom);
        borderPane.setLeft(left);
        borderPane.setRight(right);
        borderPane.setCenter(center);

        Scene scene = new Scene(borderPane, 400, 250);
        stage.setTitle("Layout BorderPane");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}