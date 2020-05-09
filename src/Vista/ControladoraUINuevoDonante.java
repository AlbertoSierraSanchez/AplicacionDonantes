package Vista;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import Controlador.Main;
import Modelo.ConexionBBDD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class ControladoraUINuevoDonante {

	
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
	   private Button Seleccionar;
	   @FXML
	   private Button Volver;
	   @FXML
	   private ImageView Imagen;
	   
	   private File file;
	 

		@FXML
		private TextField txtf_ruta;
		
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
	   
		
		
		 ConexionBBDD con;
		
		 
		// private ObservableList<Donante> Nuevo = FXCollections.observableArrayList();
		 private ObservableList<String> Sexo = FXCollections.observableArrayList("-","HOMBRE","MUJER");
		 private ObservableList<String> Grupo = FXCollections.observableArrayList("-","A +","A -","B +","B -","O +","O -","AB +","AB -" );
		 private ObservableList<String> Ciclo = FXCollections.observableArrayList("-","DAM","DAW","ASIR","TAFAD");
		 
		 
		public void initialize(){
			
			con = new ConexionBBDD();
			
			
			
			 Col_Sexo.setItems(Sexo);
			 Col_Sexo.setValue("-");
			 Col_Grupo.setItems(Grupo);
			 Col_Grupo.setValue("-");
			 Col_Ciclo.setItems(Ciclo);
			 Col_Ciclo.setValue("-");
		
			
			 
			 
		}
		
		
		
		public void Guardar(ActionEvent event) throws NumberFormatException, FileNotFoundException, SQLException{
			   
			
			String Estado="APTO";
			int aux = con.ProxDonante();
			DateTimeFormatter isoFormat = DateTimeFormatter.ISO_LOCAL_DATE;
			
			// Añadir un chequeo de campos vacíos o de validación de formato como el email (El campo Apellido2 y el campo foto no es obligatorio)
			
			
			if(Col_Nombre.getText().equals("") || Col_Apellido1.getText().equals("") || Col_DNI.getText().equals("") || Col_Email.getText().equals("") || Col_Telefono.getText().equals("") 
					|| Col_CP.getText().equals("") || Col_Sexo.getValue().equals("-") || Col_Grupo.getValue().equals("-") || Col_Ciclo.getValue().equals("-")){
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!!!");
				alert.setHeaderText("Observa que hayas introducido todos los datos");
				alert.setContentText("¡No se pueden grabar campos vacíos!");
				alert.showAndWait();
			}else{
				
				//Donante nuevo1 = new Donante(N_donante, Col_DNI.getText(),Col_Nombre.getText(), Col_Apellido1.getText(),Col_Apellido2.getText(), Col_Email.getText(),Estado, Integer.parseInt( Col_Telefono.getText()),Integer.parseInt(Col_CP.getText()), Col_Date.getId(), Col_Sexo.getValue(), Col_Grupo.getValue(), Col_Ciclo.getValue(), file);
				//Nuevo.add(nuevo1);
				
				
				String dateX = Col_Date.getValue().format(isoFormat);
				
				con.InsertarDonante(aux, Col_DNI.getText(),Col_Nombre.getText(), Col_Apellido1.getText(),Col_Apellido2.getText(), Col_Email.getText(),Estado, Integer.parseInt(Col_Telefono.getText()),Integer.parseInt(Col_CP.getText()), dateX, file, Col_Sexo.getValue(), Col_Grupo.getValue(), Col_Ciclo.getValue());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Información....");
				alert.setHeaderText("Vuelve al menú principal para actualizar los datos");
				alert.setContentText("Gracias !");
				alert.showAndWait();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		   }
		public void ResetCampos(){
			   
		   }
		public void GuardarImprimir(){
			   
		   }
		
		public void SeleccionarFoto() throws FileNotFoundException, SQLException{
			
			// TODO Auto-generated constructor stub
			// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Buscar Imagen");

			// Agregar filtros para facilitar la busqueda
			fileChooser.getExtensionFilters().addAll(
			                new FileChooser.ExtensionFilter("JPG", "*.jpeg"),
			                new FileChooser.ExtensionFilter("PNG", "*.png"),
			                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
			 );

			 Window stage = null;

			 // Obtener el archivo seleccionado
			 file = fileChooser.showOpenDialog(stage);

			 txtf_ruta.setText(file.getName());
			 
			FileInputStream conv_img = new FileInputStream(file);
			 
			Image img = new Image(conv_img);
			Imagen.setImage(img);
			 
		   }
		
		
		
	   
	   
	   
}
