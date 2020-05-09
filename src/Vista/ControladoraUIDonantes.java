package Vista;


import java.sql.SQLException;
import java.util.Optional;

import Controlador.Main;
import Modelo.ConexionBBDD;
import Modelo.Donante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	   
	   @FXML
	   private Main MnPrincipal;
	
	   
	   
	   ConexionBBDD con;
	   
	   
	   private ObservableList<Donante> TablaDonantes = FXCollections.observableArrayList();
	   private ObservableList<Donante> ListaFiltrado = FXCollections.observableArrayList();
	   private ObservableList<String> DesEstado = FXCollections.observableArrayList("-","APTO","EXCLUÍDO");
	   private ObservableList<String> DesCiclo = FXCollections.observableArrayList("-","DAM","DAW","ASIR","TAFAD");
	   
	   
	   
	   
	 
	   
	   public void initialize() throws SQLException{
			
			
			con = new ConexionBBDD();
			
			TablaDonantes=con.MostrarTabla();
			
			Tabla.setItems(this.TablaDonantes);

			ColNumero.setCellValueFactory(new PropertyValueFactory<Donante,Integer>("N_donante"));
			ColIden.setCellValueFactory(new PropertyValueFactory<Donante,Integer>("Identificacion"));
			ColNom.setCellValueFactory(new PropertyValueFactory<Donante,String>("Nombre"));
			ColApe1.setCellValueFactory(new PropertyValueFactory<Donante,String>("Apellido1"));
			ColApe2.setCellValueFactory(new PropertyValueFactory<Donante,String>("Apellido2"));
			ColEmail.setCellValueFactory(new PropertyValueFactory<Donante,String>("Email"));
			ColEstado.setCellValueFactory(new PropertyValueFactory<Donante,Boolean>("Estado"));
			ColTelefono.setCellValueFactory(new PropertyValueFactory<Donante,Integer>("Telefono"));
			ColCP.setCellValueFactory(new PropertyValueFactory<Donante,Integer>("Cod_postal"));
			ColFNacimiento.setCellValueFactory(new PropertyValueFactory<Donante,String>("F_nacimiento"));
			ColSexo.setCellValueFactory(new PropertyValueFactory<Donante,String>("Sexo"));
			ColGSangre.setCellValueFactory(new PropertyValueFactory<Donante,String>("G_sangre"));
			ColCiclo.setCellValueFactory(new PropertyValueFactory<Donante,String>("Ciclo"));
			
			
			FCiclo.setItems(DesCiclo);
			FCiclo.setValue("-");
			
			FEstado.setItems(DesEstado);
			FEstado.setValue("-");

		}
	   
	
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
	   

	   public void Filtrar(ActionEvent event) throws SQLException{
		   
		   if(FNombre.getText().length()==0 && FEstado.getValue().equals("-") && FCiclo.getValue().equals("-")){
				
				initialize();
			
			}else{
				
				
				ListaFiltrado=con.Filtrar(FNombre.getText(),FEstado.getValue().toString(),FCiclo.getValue().toString());
				Tabla.setItems(ListaFiltrado);
				
			}
	   }
		  
	   public void Nuevo(ActionEvent event) throws SQLException{
		   
		   this.MnPrincipal.mostrarMenuNuevoDonante();
		   
		   
	   }
	   public void Modificar(ActionEvent event) throws SQLException{
		   this.MnPrincipal.mostrarMenuMODDonante();
	   }
	   public void Eliminar(ActionEvent event) throws SQLException{
		   
		   
		   
		   int index = Tabla.getSelectionModel().getSelectedIndex();

			if(index>=0){
				
				Donante seleccionada = Tabla.getSelectionModel().getSelectedItem();

						Alert alert = new Alert(AlertType.CONFIRMATION);
				       alert.setTitle("Borrando...");
				       alert.setHeaderText("Desea Borrar a " + seleccionada.getNombre() +" "+ seleccionada.getApellido1());
				      
				       Optional <ButtonType> result = alert.showAndWait ();
				       
				      if (result.get () == ButtonType.OK){
				    	  
				    	  	con.Eliminar(seleccionada.getN_donante());
				    	   	TablaDonantes.remove(seleccionada);
							
				    	   	Alert alerta = new Alert ( AlertType.INFORMATION ); 
				    	   	alerta . setTitle ( "Información" ); 
				    	   	alerta . setHeaderText (null); 
				    	   	alerta . setContentText ( "¡Eliminado!" );  
				    	   	alerta . showAndWait ();
				    	   	
				    	   	
				    	   	
				       }

			}else{
				
					Alert alert = new Alert(AlertType.ERROR);
			       alert.setTitle("Error !");
			       alert.setHeaderText("Seleccione una fila...");
			       alert.showAndWait();
			}
			
			
			
			
			
	   }
}
		   
	
	  

