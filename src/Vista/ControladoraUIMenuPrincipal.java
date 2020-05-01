package Vista;




import java.util.ResourceBundle;

import Controlador.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladoraUIMenuPrincipal {

	@FXML
	   private Button AdminDonantes;
	@FXML
	   private Button AdminDonacionnes;
	@FXML
	   private Button InformeYEstad;
	@FXML
	   private Label tituloMnPrincipal;
	@FXML
	   private Main MnPrincipal;
	
	
	public void setMnPrincipal(Main MnPrincipal) {
        this.MnPrincipal = MnPrincipal;
    }
	public void initialize() {
		
		
	}
	
	 public void AbrirDonantes(ActionEvent action){
		System.out.println("Donante");
		this.MnPrincipal.mostrarVentanaSecundaria();
}
	
	
	 public void AbrirDonaciones(ActionEvent action){
	System.out.println("Donaciones");
	}
	
	 public void AbrirInformes(ActionEvent action){
	System.out.println("Informes");
}


	

	
	
}
