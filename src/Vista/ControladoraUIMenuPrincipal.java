package Vista;




import java.sql.SQLException;

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
	
	
	public void initialize() throws SQLException{
		
		tituloMnPrincipal.setText("Bienvenido a mi App!");
		
	}
	
public void AbrirDonantes(ActionEvent action){
		System.out.println("Donante");
		
	}
	
public void AbrirDonaciones(ActionEvent action){
	System.out.println("Donaciones");
	}
	
public void AbrirInformes(ActionEvent action){
	System.out.println("Informes");
}
	
	
}
