package Vista;





import Controlador.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
		private Stage ventana;
	
		public void CerrarVentana(){
			this.ventana.close();
		}
		
	public void setMnPrincipal(Main MnPrincipal) {
        this.MnPrincipal = MnPrincipal;
    }
	public void initialize() {
		tituloMnPrincipal.setText("Aplicación de Gestión");
		
	}
	
	 public void AbrirDonantes(ActionEvent action){
		
		this.MnPrincipal.mostrarMenuDonantes();
		
}
	
	
	 public void AbrirDonaciones(ActionEvent action){
		 
		
		this.MnPrincipal.mostrarMenuDonaciones();
	
	}
	
	 public void AbrirInformes(ActionEvent action){
	
	this.MnPrincipal.mostrarMenuInformes();
}


	

	
	
}
