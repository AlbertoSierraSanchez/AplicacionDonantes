package Vista;

import Controlador.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladoraUIModDonantes {
	@FXML
	   private TextField Col_Nombre;
	   @FXML
	   private TextField Col_Apellido1;
	   @FXML
	   private TextField Col_Apellido2;
	   @FXML
	   private TextField Col_DNI;
	   @FXML
	   private TextField Col_Email;
	   @FXML
	   private TextField Col_Telefono;
	   @FXML
	   private TextField Col_CP;
	   @FXML
	   private DatePicker Col_Date;
	   @FXML
	   private ChoiceBox<String> Col_Sexo;
	   @FXML
	   private ChoiceBox<String> Col_Grupo;
	   @FXML
	   private ChoiceBox<String> Col_Ciclo;
	   @FXML
	   private Button Guardar;
	   @FXML
	   private Button ReestablecerCampos;
	   @FXML
	   private Button GuardarImprimir;
	   @FXML
	   private Button ReestablecerFoto;
	   @FXML
	   private Button Seleccionar;
	   @FXML
	   private Button Volver;
	   @FXML
	   private ImageView Imagen;
	   
	   
	   
	   
	   
	   private Stage ventana;
	   
	   @FXML
	   private Main MnPrincipal;
	
	   
	   public void setMnPrincipal(Main MnPrincipal) {
        this.MnPrincipal = MnPrincipal;
    }
	 
	   
	   public void setStagePrincipal(Stage ventana) {
			// TODO Auto-generated method stub
			this.ventana = ventana;
		}

		public void closeWindow(){
			this.ventana.close();
		}
	   
		public void Guardar(){
			   
		   }
		public void ResetCampos(){
			   
		   }
		public void GuardarImprimir(){
			   
		   }
		
		public void SeleccionarFoto(){
			   
		   }
		public void ResetFoto(){
			   
		   }
		
	   
	   
}
