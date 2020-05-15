package Vista;

import java.sql.SQLException;
import java.util.Optional;

import Controlador.Main;
import Modelo.ConexionBBDD;
import Modelo.Donacion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableColumn<Donacion,String> ColPulso;
	@FXML
	private TableColumn<Donacion,String> ColTAD;
	@FXML
	private TableColumn<Donacion,String> ColTAS;
	@FXML
	private TableColumn<Donacion,String> ColHBC;
	@FXML
	private TableColumn<Donacion,String> ColHBV;
	@FXML
	private TableColumn<Donacion,Integer> ColVolumen;
	
	
	
	@FXML
	private Button FiltrarD;
		
	@FXML
	   private ChoiceBox<Integer> FNumero;
	   @FXML
	   private ChoiceBox<String> FTipo;
	   
	
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
			
			this.ventana = ventana;
		}

		public void closeWindow(){
			this.ventana.close();
		}
		
	   
	   public void NuevoD(ActionEvent event) throws SQLException{
		   
		   this.MnPrincipal.mostrarMenuComprobacionDonacion();
		   
	   }
	   
	   
	   ConexionBBDD con;
	   
	   private ObservableList<Donacion> TablaDonaciones = FXCollections.observableArrayList();
	   private ObservableList<Donacion> TablaFiltrada = FXCollections.observableArrayList();
	   
	   private ObservableList<String> TipoLista = FXCollections.observableArrayList("-","SANGRE","AFÉRESIS");
	   private ObservableList<Integer> NumeroLista = FXCollections.observableArrayList();
	   
	   public void initialize() throws SQLException{
			
			
			con = new ConexionBBDD();
			
			TablaDonaciones=con.MostrarDonaciones();
			
			TablaDon.setItems(this.TablaDonaciones);

			NumeroLista=con.NumerosDon();
			NumeroLista.add(0);
			this.FNumero.setItems(NumeroLista);
			this.FNumero.setValue(0);
			
			
			
		
			
			this.FTipo.setItems(TipoLista);
			FTipo.setValue("-");
			
			ColNumero.setCellValueFactory(new PropertyValueFactory<Donacion,Integer>("Num_donacion"));
			ColFecha.setCellValueFactory(new PropertyValueFactory<Donacion,String>("Fecha"));
			ColTipo.setCellValueFactory(new PropertyValueFactory<Donacion,String>("Tipo"));
			ColPulso.setCellValueFactory(new PropertyValueFactory<Donacion,String>("Pulso"));
			ColTAD.setCellValueFactory(new PropertyValueFactory<Donacion,String>("TA_sist"));
			ColTAS.setCellValueFactory(new PropertyValueFactory<Donacion,String>("TA_dias"));
			ColHBC.setCellValueFactory(new PropertyValueFactory<Donacion,String>("HB_cap"));
			ColHBV.setCellValueFactory(new PropertyValueFactory<Donacion,String>("HB__ven"));
			ColVolumen.setCellValueFactory(new PropertyValueFactory<Donacion,Integer>("Volumen"));
			
			
			
			
			
			

		}
	   
	   
	   
	   
	   
	   public void EliminarD(ActionEvent event) throws SQLException{
		   
		   int index = TablaDon.getSelectionModel().getSelectedIndex();

			if(index>=0){
				
				Donacion seleccionada = TablaDon.getSelectionModel().getSelectedItem();

						Alert alert = new Alert(AlertType.CONFIRMATION);
				       alert.setTitle("Borrando...");
				       alert.setHeaderText("Desea Borrar la Donación número : " + seleccionada.getNum_donacion() +" del día : "+ seleccionada.getFecha() + " ?");
				      
				       Optional <ButtonType> result = alert.showAndWait ();
				       
				      if (result.get () == ButtonType.OK){
				    	  
				    	  	con.EliminarDon(seleccionada.getNum_donacion());
				    	  	TablaDonaciones.remove(seleccionada);
							
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
	  
	   public void FiltrarD(ActionEvent event) throws SQLException{
		   
		   
		   if(this.FNumero.getValue().equals("0") && FTipo.getValue().equals("-")){
				
				initialize();
			
			}else{
				
				int numero=(int)this.FNumero.getValue();
				
					TablaFiltrada=con.FiltrarDon(numero, this.FTipo.getValue().toString());
					TablaDon.setItems(TablaFiltrada);
				
				
				
			}
		   
		   
		   
		   
	   }
}


	
	
	
	
	
