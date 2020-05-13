package Vista;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.DocumentException;

import Controlador.Main;
import Modelo.ConexionBBDD;
import Modelo.ImprimePDF;
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
	   private TextField Col_NDonante;
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
		
		
		
		public void Guardar(ActionEvent event) throws NumberFormatException, SQLException, IOException{
			   
			
			String Estado="APTO";
		
			
			
			// Añadir un chequeo de campos vacíos o de validación de formato como el email (El campo Apellido2 y el campo foto no es obligatorio)
			//Col_Nombre.getText().equals("") || Col_Apellido1.getText().equals("") || Col_DNI.getText().equals("") || Col_Email.getText().equals("") || Col_Telefono.getText().equals("") 
			//|| Col_CP.getText().equals("") || Col_Sexo.getValue().equals("-") || Col_Grupo.getValue().equals("-") || Col_Ciclo.getValue().equals("-")
			
			if(Col_Nombre.getText().length()>25 ||  Col_Nombre.getText().equals("") || checkalfabeto(this.Col_Nombre.getText().toLowerCase())==false){
	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!!!");
				alert.setHeaderText("¡Revisa el campo Nombre!");
				alert.setContentText("1.- No podemos dejarlo vacío\n"+"2.- Máximo de 25 carácteres\n"+"3.- Sólo letras");
			
				alert.showAndWait();
			}else{
				if(Col_Apellido1.getText().length()>25 ||  Col_Apellido1.getText().equals("") || checkalfabeto(this.Col_Apellido1.getText().toLowerCase())==false){
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!!!");
					alert.setHeaderText("¡Revisa el campo Apellido1!");
					alert.setContentText("1.- No podemos dejarlo vacío\n"+"2.- Máximo de 25 carácteres\n"+"3.- Sólo letras");
				
					alert.showAndWait();
				}else{
					if(Col_Apellido2.getText().length()>25 ||  Col_Apellido2.getText().equals("") || checkalfabeto(this.Col_Apellido2.getText().toLowerCase())==false){
						
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error!!!");
						alert.setHeaderText("¡Revisa el campo Apellido2!");
						alert.setContentText("1.- No podemos dejarlo vacío\n"+"2.- Máximo de 25 carácteres\n"+"3.- Sólo letras");
					
						alert.showAndWait();
					}else{
						if(this.Col_Email.getText().indexOf('@')==0 && this.Col_Email.getText().indexOf('.')==0 ){
							
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error!!!");
							alert.setHeaderText("¡Revisa el campo Correo!");
							alert.setContentText("1.- No podemos dejarlo vacío\n 2.- Debe contener un '@' y al menos un '.' \n");
							alert.showAndWait();
						}else{
							
							String correo=Col_Email.getText();
							if(con.ComprobarCorreo(correo)==true){
								
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error!!!");
								alert.setHeaderText("¡Revisa el campo Correo!");
								alert.setContentText("1.-Ese correo ya existe !");
								alert.showAndWait();
							}else{
								String dni=Col_DNI.getText();
								if(this.Col_DNI.equals("") || con.ComprobarDNI(dni)){
									
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error!!!");
									alert.setHeaderText("¡Revisa el campo DNI!");
									alert.setContentText("Introduce un DNI válido !");
									alert.showAndWait();
									
									
									
								}else{
									
									if(this.Col_Telefono.getText().equals("") || this.Col_Telefono.getText().length()>11){
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Error!!!");
										alert.setHeaderText("¡Revisa el campo Telefono!");
										alert.setContentText("Introduce un Teléfono válido !");
										alert.showAndWait();
								}else{
									int telefono=Integer.parseInt(Col_Telefono.getText());
									if(con.ComprobarTLF(telefono)==true){
										
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Error!!!");
										alert.setHeaderText("¡Revisa el campo Telefono!");
										alert.setContentText("Ese télefono ya existe !");
										alert.showAndWait();
										
										
									}else{
										int numero = Integer.parseInt(this.Col_NDonante.getText());
										if(con.ComprobarNumero(numero)==true){
											
											Alert alert = new Alert(AlertType.ERROR);
											alert.setTitle("Error!!!");
											alert.setHeaderText("¡Revisa el campo Número de Donante!");
											alert.setContentText("Ese número ya existe !");
											alert.showAndWait();
											
										}else{
												if(this.Col_CP.getText().length()>6){
													
													Alert alert = new Alert(AlertType.ERROR);
													alert.setTitle("Atención !!!");
													alert.setHeaderText("¿Sabes que en España el CP sólo tiene 5 dígitos?");
													alert.setContentText(" Échale un vistazo anda...! ");
													alert.showAndWait();
												}else{
													
													if(this.Col_Grupo.getValue().equals("-") || this.Col_Sexo.getValue().equals("-") || this.Col_Ciclo.getValue().equals("-")){
														
														Alert alert = new Alert(AlertType.ERROR);
														alert.setTitle("Atención !!!");
														alert.setHeaderText("Por favor rellena TODOS los campos....");
														alert.setContentText(" Incluidos el sexo, el grupo sanguíneo y el ciclo");
														alert.showAndWait();
													}else{
														
														DateTimeFormatter isoFormat = DateTimeFormatter.ISO_LOCAL_DATE;
														String dateX = Col_Date.getValue().format(isoFormat);
														con.InsertarDonante(Integer.parseInt(Col_NDonante.getText()),Col_DNI.getText(),Col_Nombre.getText(), Col_Apellido1.getText(),Col_Apellido2.getText(), Col_Email.getText(),Estado, Integer.parseInt(Col_Telefono.getText()),Integer.parseInt(Col_CP.getText()), dateX, file, Col_Sexo.getValue(), Col_Grupo.getValue(), Col_Ciclo.getValue());
														
														Alert alert = new Alert(AlertType.INFORMATION);
														alert.setTitle("Información....");
														alert.setHeaderText("Vuelve al menú principal para actualizar los datos");
														alert.setContentText("Gracias !");
														alert.showAndWait();
														
														ResetCampos();
														}
														
														
													
													
													
												}
												
												
											}
											
											
											
											
											
											
										}
										
										
										
										
										
									}
								}
									
									
									
								}
								
								
							}
						}
					}
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
		   
		public void ResetCampos() throws FileNotFoundException{
			this.Col_Nombre.setText(""); 
			this.Col_Apellido1.setText("");
			this.Col_Apellido2.setText("");
			this.Col_DNI.setText("");
			this.Col_Email.setText("");
			this.Col_CP.setText("");
			this.Col_Telefono.setText("");
			this.Col_Ciclo.setValue("-");
			this.Col_Grupo.setValue("-");
			this.Col_Sexo.setValue("-");
			this.Col_Date.setValue(null);
			File img = new File("C:/Users/Lenovo/workspace/AplicacionDonantes/src/Vista/images.jpg");
			FileInputStream conv_img = new FileInputStream(img);
			Image img1 = new Image(conv_img);
			this.Imagen.setImage(img1);
			this.txtf_ruta.setText("");
			
		   }
		public void GuardarImprimir() throws FileNotFoundException, DocumentException, SQLException{
			   
			
			String Estado="APTO";
			
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
				
				con.InsertarDonante(Integer.parseInt(Col_NDonante.getText()),Col_DNI.getText(),Col_Nombre.getText(), Col_Apellido1.getText(),Col_Apellido2.getText(), Col_Email.getText(),Estado, Integer.parseInt(Col_Telefono.getText()),Integer.parseInt(Col_CP.getText()), dateX, file, Col_Sexo.getValue(), Col_Grupo.getValue(), Col_Ciclo.getValue());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Información....");
				alert.setHeaderText("Vuelve al menú principal para actualizar los datos");
				alert.setContentText("Gracias !");
				alert.showAndWait();
			}
			
			
			

			//ImprimePDF imprime = new ImprimePDF(" Carnet-"+Col_Nombre.getText(),"C:\\Users\\Lenovo\\Documents\\Documentos\\DAW\\Programación\\Programación");
			//imprime.GenerarPDF(Col_Nombre.getText(),Col_Apellido1.getText(),Col_Apellido2.getText(),Col_Grupo.getValue().toString(),file);
			
			
			ResetCampos();
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
		
		 public boolean checkalfabeto(String palabra){
			   
			   
			   char letra = 0;
			   int contador=0;
			   
			   
			   
			   for(int i = 0;i<palabra.length();i++){
				   
				   letra = (palabra.charAt(i));
				   
			   if(!((letra>='a' && letra<='z') && !(letra>='A' && letra<='Z'))){ 
				contador++;	
					}
			   }
			   
			   
			   if(contador!=0)
				   
				   return false;
			   
			   else
				   
				   return true;
				  
				  
				  
			  }
 
}
