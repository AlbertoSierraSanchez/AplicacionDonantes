package Controlador;

import java.io.IOException;

import Vista.ControladoraUIAltaDonacion;
import Vista.ControladoraUIComprobacionDonacion;
import Vista.ControladoraUIDonacion;
import Vista.ControladoraUIDonantes;
import Vista.ControladoraUIInformes;
import Vista.ControladoraUIMenuPrincipal;
import Vista.ControladoraUIModDonantes;
import Vista.ControladoraUINuevoDonante;
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
            Stage ventana1 = new Stage();
            ventana1.setTitle("Administracion de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana1.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana1.setScene(scene);

            ControladoraUIDonantes controller2 = loader.getController();
            controller2.setStagePrincipal(ventana1);
            controller2.setMnPrincipal(this);
            ventana1.show();
            
        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    public void mostrarMenuDonaciones() {
        try {
        	
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIDonacion.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana2 = new Stage();
            ventana2.setTitle("Administracion de Donaciones");
            /* Le decimos a la ventana quién es la ventana original */
            ventana2.initOwner(stagePrincipal);
            Scene scene2 = new Scene(ventanaDos);
            ventana2.setScene(scene2);
            ControladoraUIDonacion controller3 = loader.getController();
            controller3.setStagePrincipal(ventana2);
            controller3.setMnPrincipal(this);
            ventana2.show();
            
        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    public void mostrarMenuInformes() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIInformes.fxml"));
            AnchorPane ventanaTres = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana3 = new Stage();
            ventana3.setTitle("Administracion de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana3.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaTres);
            ventana3.setScene(scene);

            ControladoraUIInformes controller2 = loader.getController();
            controller2.setStagePrincipal(ventana3);

            ventana3.show();

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    public void mostrarMenuNuevoDonante() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UINuevoDonante.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Alta de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);

            ControladoraUINuevoDonante controller2 = loader.getController();
            controller2.setStagePrincipal(ventana);

            ventana.show();
            

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }

    public void mostrarMenuMODDonante() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIModDonante.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Modificación de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);

            ControladoraUIModDonantes controller2 = loader.getController();
            controller2.setStagePrincipal(ventana);

            ventana.show();
            

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }

    public void mostrarMenuComprobacionDonacion() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIComprobacionDonacion.fxml"));
            AnchorPane ventanaD = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Comprobación de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaD);
            ventana.setScene(scene);

            ControladoraUIComprobacionDonacion controller3 = loader.getController();
            controller3.setStagePrincipal(ventana);
            controller3.setMnPrincipal(this);

            ventana.show();
            

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    
    
    public void mostrarMenuAltaDonacion() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/UIAltaDonacion.fxml"));
            AnchorPane ventanaD = (AnchorPane) loader.load();
            /* Creamos la segunda ventana como otro stage */
            Stage ventana = new Stage();
            ventana.setTitle("Alta de Donantes");
            /* Le decimos a la ventana quién es la ventana original */
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaD);
            ventana.setScene(scene);

            ControladoraUIAltaDonacion controller3 = loader.getController();
            controller3.setStagePrincipal(ventana);
            controller3.setMnPrincipal(this);

            ventana.show();
            

        } catch (Exception e) {
            //tratar la excepción
        	System.out.println(e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
	
	
	
