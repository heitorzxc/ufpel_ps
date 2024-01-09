package src.Interface; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import src.Maquina.Maquina;

public class TesteInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface.fxml"));
        Parent root = loader.load();
        Controller controller= loader.getController();
        
        Maquina maquina = new Maquina();
        maquina.setController(controller);
        controller.setMaquina(maquina);
        
        // Configurar a cena
        Scene scene = new Scene(root);

        // Configurar o palco (Stage)
        primaryStage.setTitle("SIC/XE");
        primaryStage.setScene(scene);

        // Mostrar o palco
        primaryStage.show();
        
    }
}

