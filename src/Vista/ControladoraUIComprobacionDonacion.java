package Vista;

import Controlador.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladoraUIComprobacionDonacion {

	@FXML
	   private Button Continuar;
	@FXML
	   private Button Restablecer;
	@FXML
	   private Button ComprobarN;
	@FXML
	   private Button ComprobarF;
	@FXML
	   private Button Volver;
	@FXML
	   private CheckBox CKN;
	@FXML
	   private CheckBox CKF;
	@FXML
	   private TextField NID_txt;

	@FXML
	   private ChoiceBox<String> cb1;
	@FXML
	   private ChoiceBox<String> cb2;
	@FXML
	   private ChoiceBox<String> cb3;
	@FXML
	   private ChoiceBox<String> cb4;
	@FXML
	   private ChoiceBox<String> cb5;
	@FXML
	   private ChoiceBox<String> cb6;
	@FXML
	   private ChoiceBox<String> cb7;
	@FXML
	   private ChoiceBox<String> cb8;
	@FXML
	   private ChoiceBox<String> cb9;
	@FXML
	   private ChoiceBox<String> cb10;
	@FXML
	   private ChoiceBox<String> cb11;
	@FXML
	   private ChoiceBox<String> cb12;
	@FXML
	   private ChoiceBox<String> cb13;
	@FXML
	   private ChoiceBox<String> cb14;
	@FXML
	   private ChoiceBox<String> cb15;
	@FXML
	   private ChoiceBox<String> cb16;
	@FXML
	   private ChoiceBox<String> cb17;
	@FXML
	   private ChoiceBox<String> cb18;
	@FXML
	   private ChoiceBox<String> cb19;
	@FXML
	   private ChoiceBox<String> cb20;
	@FXML
	   private ChoiceBox<String> cb21;
	@FXML
	   private ChoiceBox<String> cb22;
	@FXML
	   private ChoiceBox<String> cb23;
	@FXML
	   private ChoiceBox<String> cb24;
	@FXML
	   private ChoiceBox<String> cb25;
	@FXML
	   private ChoiceBox<String> cb26;
	@FXML
	   private ChoiceBox<String> cb27;
	@FXML
	   private ChoiceBox<String> cb28;
	@FXML
	   private ChoiceBox<String> cb29;
	@FXML
	   private ChoiceBox<String> cb30;
	@FXML
	   private ChoiceBox<String> cb31;
	@FXML
	   private ChoiceBox<String> cb32;
	@FXML
	   private ChoiceBox<String> cb33;
	@FXML
	   private ChoiceBox<String> cb34;
	@FXML
	   private ChoiceBox<String> cb35;
	
	
	  private Stage ventana;
	   
	   @FXML
	   private Main MnPrincipal;
	
	
	   public void setMnPrincipal(Main MnPrincipal) {
       this.MnPrincipal = MnPrincipal;
   }
	 
	   
	   public void setStagePrincipal(Stage ventana) {
			
			this.ventana = ventana;
		}

		public void closeWindow(){
			this.ventana.close();
		}
	
	
	public void Continuar(){
		this.MnPrincipal.mostrarMenuAltaDonacion();
	}
	public void Restablecer(){
		
	}
	public void ComprobarN(){
		
	}
	public void ComprobarF(){
		
	}
	
	public void Volver(){
		
	}
	
	
	
}
