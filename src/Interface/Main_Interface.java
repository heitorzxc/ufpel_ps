package src.Interface;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.Maquina.Maquina;
import src.Memoria.*;
import src.Registradores.Registrador;

public class Main_Interface extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    // Carregar o arquivo FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface.fxml"));
    Parent root = loader.load();
    Controller controller = loader.getController();

    // Maquina maquina = Maquina.getInstance();
    // maquina.setAquivo("./resources/binarios/ex.txt");
    // controller.updateInterface();
    // controller.handleTABLE();

    // Carregar a imagem de fundo
    // Image image = new Image("./resources/img/fundo3.png");
    // BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true,
    // false, true);
    // BackgroundImage backgroundImage = new BackgroundImage(image,
    // BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
    // BackgroundPosition.CENTER, backgroundSize);

    // Verificar se o root é uma instância de Pane e, em caso afirmativo, definir o
    // plano de fundo
    // if (root instanceof Pane) {
    // Pane pane = (Pane) root;
    // pane.setBackground(new Background(backgroundImage));
    // }

    // Configurar a cena
    Scene scene = new Scene(root);

    // Configurar o palco (Stage)
    primaryStage.setTitle("SIC/XE");
    primaryStage.setScene(scene);

    // Mostrar o palco
    primaryStage.show();

  }
}