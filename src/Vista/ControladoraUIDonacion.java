package Vista;

import java.sql.SQLException;

import Controlador.Main;
import Modelo.Donacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class ControladoraUIDonacion {
	
	
	@FXML
   	private TableView<Donacion> TablaDon;
	
	@FXML
	private TableColumn<Donacion,Integer> ColNumero;
	@FXML
	private TableColumn<Donacion,String> ColFecha;
	@FXML
	private TableColumn<Donacion,String> ColTipo;
	@FXML
	private TableColumn<Donacion,Integer> ColPulso;
	@FXML
	private TableColumn<Donacion,Integer> ColTAD;
	@FXML
	private TableColumn<Donacion,Integer> ColTAS;
	@FXML
	private TableColumn<Donacion,Integer> ColHBC;
	@FXML
	private TableColumn<Donacion,Integer> ColHBV;
	
	
	@FXML
	private Button FiltrarD;
		
	@FXML
	   private TextField FNumero;
	   @FXML
	   private TextField FTipo;
	   
	
	   @FXML
	   private Button NuevoD;
	   
	   @FXML
	   private Button VolverD;
	   
	   @FXML
	   private Button EliminarD;
	   
	   
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
		
	   
	   public void NuevoD(ActionEvent event) throws SQLException{
		   
	   }
	   public void EliminarD(ActionEvent event) throws SQLException{
		   
	   }
	   public void VolverD(ActionEvent event) throws SQLException{
		   
	   }
	   public void FiltrarD(ActionEvent event) throws SQLException{
		   
	   }
}


	
	
	
	
	
