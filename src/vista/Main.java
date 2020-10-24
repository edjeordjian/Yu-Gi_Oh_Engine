package vista;

import controlador.ControladorPartidaAlGoOh;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Constantes;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	new ControladorPartidaAlGoOh("Jugador 1", "Jugador 2", primaryStage).iniciar();
    	primaryStage.getIcons().add(new Image(Constantes.RUTA_DE_LAS_IMAGENES + "mazo.jpg"));
    	primaryStage.setTitle("Mini Yu-Gi-Oh");
    	primaryStage.show();
   
    }
}
