package Vista;

import Modelo.Donante;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladoraUIDonantes {

	   	@FXML
	   	private TableView<Donante> Tabla;

	    @FXML
		private TableColumn<Donante,Integer> ColNumero;
	    @FXML
		private TableColumn<Donante,Integer> ColIden;
	   @FXML
		private TableColumn<Donante,String> ColNom;
	   @FXML
		private TableColumn<Donante,String> ColApe1;
	   @FXML
		private TableColumn<Donante,String> ColApe2;
	   @FXML
		private TableColumn<Donante,String> ColEmail;
	   @FXML
		private TableColumn<Donante,Boolean> ColEstado;
	   @FXML
		private TableColumn<Donante,Integer> ColTelefono;
	   @FXML
		private TableColumn<Donante,Integer> ColCP;
	   @FXML
		private TableColumn<Donante,String> ColFNacimiento;
	   @FXML
		private TableColumn<Donante,String> ColSexo;
	   @FXML
		private TableColumn<Donante,String> ColGSangre;
	   @FXML
		private TableColumn<Donante,String> ColCiclo;
	  
	   
	   @FXML
	   private Button Filtrar;
	   
	   @FXML
	   private TextField FNombre;
	   @FXML
	   private TextField FApellido;
	   @FXML
	   private TextField FDNI;
	   
	   @FXML
	   private ComboBox<String> FEstado;
	   @FXML
	   private ComboBox<String> FCiclo;
	   
	   @FXML
	   private Button Nuevo;
	   
	   @FXML
	   private Button Modificar;
	   
	   @FXML
	   private Button Eliminar;
	   
	   @FXML
	   private Button Imprimir;
	   
	   @FXML
	   private Button Volver;
	   
	   private Stage ventana;
	   
	   
	 
	   
	   public void setStagePrincipal(Stage ventana) {
			// TODO Auto-generated method stub
			this.ventana = ventana;
		}

		public void closeWindow(){
			this.ventana.close();
		}

/*
	   public void Filtrar(ActionEvent event) throws SQLException{
		   
	   }
	   public void Nuevo(ActionEvent event) throws SQLException{
		   
	   }
	   public void Modificar(ActionEvent event) throws SQLException{
		   
	   }
	   public void Eliminar(ActionEvent event) throws SQLException{
		   
	   }
	   public void Volver(ActionEvent event) throws SQLException{
		   
	   }
	*/
	
	   
	
	
	
}
