package Vista;

import Controlador.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladoraUIInformes {

	 	@FXML
	 	private Button N_totalDonat;
	   @FXML
	   private Button N_totalDonac;
	   @FXML
	   private Button Aptos;
	   @FXML
	   private Button Excluidos;
	   @FXML
	   private Button R_Ult_Mes;
	   @FXML
	   private Button DxD;
	   @FXML
	   private Button Buscar;
	   @FXML
	   private Button Volver;
	   
	
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
	
	   
	   public void NTotalDonantes(){
		   
	   }
	   
	   public void NTotalDonacion(){
		   
	   }
	
	   public void Aptos(){
		   
	   }
	   public void Excluidos(){
		   
	   }
	   public void UltimoMes(){
		   
	   }
	   public void DonacionesPorDonante(){
		   
	   }
	   public void Buscar(){
		   
	   }
	  
		
	
}
