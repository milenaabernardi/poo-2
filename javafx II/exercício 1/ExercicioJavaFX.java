import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExercicioJavaFX extends Application {

    private Label lblNome;
    private Label lblMsg;
    private TextField txtNome;
    private Button btnOla;
    private Button btnSair;

    @Override
    public void start(Stage primaryStage) {
        lblNome = new Label("Digite seu nome:");
        lblMsg = new Label();
        txtNome = new TextField();
        btnOla = new Button("Olá!");
        btnSair = new Button("Sair!");

        lblMsg.setStyle("-fx-text-fill: blue;");

        btnOla.setOnAction(e -> {
            String nome = txtNome.getText();
            lblMsg.setText("Olá, " + nome);
        });

        btnSair.setOnAction(e -> {
            Platform.exit();
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(lblNome, txtNome, lblMsg, btnOla, btnSair);
        
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Exercício de JavaFX - usando eventos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}