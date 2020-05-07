package Vista;

import Controlador.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladoraUIAltaDonacion {
	
	
	@FXML
	   private Button Guardar;
	@FXML
	   private Button Restablecer;
	@FXML
	   private Button Volver;
	
	@FXML
	   private ChoiceBox<String> Tipo;
	@FXML
	   private DatePicker Fecha;
	
	
	 @FXML
	   private TextField Pulso_txt;
	   @FXML
	   private TextField TA_SIS_txt;
	   @FXML
	   private TextField TA_DIAS_txt;
	   @FXML
	   private TextField HB_CAP_txt;
	   @FXML
	   private TextField HB_VEN_txt;
	
	
	
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
	
	
}
