package Controlador;

import java.io.IOException;

import Vista.ControladoraUIDonacion;
import Vista.ControladoraUIDonantes;
import Vista.ControladoraUIInformes;
import Vista.ControladoraUIMenuPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

    private static Stage stagePrincipal;
    private AnchorPane rootPane;

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Main.stagePrincipal = stagePrincipal;
        mostrarMenuPrincipal();

    }

    /* En un proyecto JavaFX el main llama al launch que a su vez llama a start */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * cargamos la ventana principal
     */
    public void mostrarMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIMenuPrincipal.fxml"));
            rootPane=(AnchorPane) loader.load();
            Scene scene = new Scene(rootPane);
            stagePrincipal.setTitle("Aplicacion de Donantes y Donaciones");
            stagePrincipal.setScene(scene);
            /*
             * Añadidos las llamadas del main al Controlador y del controlador al main ***/
            ControladoraUIMenuPrincipal controller = loader.getController();
            controller.setMnPrincipal(this);

            stagePrincipal.show();
        } catch (IOException e) {
        	System.out.println(e);
            //tratar la excepción.
        }
   }


    /* Este método es llamado cuando se presiona el botón de la ventana principal
     * Lo llama el controlador de la vista principal
     */
    public void mostrarMenuDonantes() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIDonantes.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Administracion de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);

            ControladoraUIDonantes controller2 = loader.getController();
            controller2.setStagePrincipal(ventana);

            ventana.show();

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    
    public void mostrarMenuDonaciones() {
        try {
        	
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIDonacion.fxml"));
            AnchorPane ventanaTres = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana3 = new Stage();
            ventana3.setTitle("Administracion de Donaciones");
            /* Le decimos a la ventana quién es la ventana original */
            ventana3.initOwner(stagePrincipal);
            Scene scene2 = new Scene(ventanaTres);
            ventana3.setScene(scene2);
            ControladoraUIDonacion controller3 = loader.getController();
            controller3.setStagePrincipal(ventana3);

            ventana3.show();

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    
    public void mostrarMenuInformes() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIInformes.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Administracion de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);

            ControladoraUIInformes controller2 = loader.getController();
            controller2.setStagePrincipal(ventana);

            ventana.show();

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    
    
}
	
	
	
	
	
	
