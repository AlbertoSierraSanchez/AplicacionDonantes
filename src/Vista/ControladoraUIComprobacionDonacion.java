package Vista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Controlador.Main;
import Modelo.ConexionBBDD;
import Modelo.Formulario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	   private TextField NID_txt;
	@FXML
	   private TextField NumForm;
	@FXML
	   private CheckBox CKF;
	
	
	
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
	   
	  private ObservableList<String> CBs = FXCollections.observableArrayList("-","SI","NO");
	  
	  ArrayList<ChoiceBox> cb = new ArrayList<ChoiceBox>();
	  ConexionBBDD con;
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
	
	
	   
	   public void initialize(){
		   
		   con = new ConexionBBDD();
		   
		   cb.add(cb1);	
		   cb.add(cb2);
		   cb.add(cb3);
		   cb.add(cb4);
		   cb.add(cb5);
		   cb.add(cb6);
		   cb.add(cb7);
		   cb.add(cb8);
		   cb.add(cb9);
		   cb.add(cb10);
		   cb.add(cb11);
		   cb.add(cb12);
		   cb.add(cb13);
		   cb.add(cb14);
		   cb.add(cb15);
		   cb.add(cb16);
		   cb.add(cb17);
		   cb.add(cb18);
		   cb.add(cb19);
		   cb.add(cb20);
		   cb.add(cb21);
		   cb.add(cb22);
		   cb.add(cb23);
		   cb.add(cb24);
		   cb.add(cb25);
		   cb.add(cb26);
		   cb.add(cb27);
		   cb.add(cb28);
		   cb.add(cb29);
		   cb.add(cb30);
		   cb.add(cb31);
		   cb.add(cb32);
		   cb.add(cb33);
		   cb.add(cb34);
		   cb.add(cb35);
		   
		   for(int i =0;i<cb.size();i++){
			   
			   cb.get(i).setItems(CBs);
			   cb.get(i).setValue("-");
			   
			   
		   }
	   }
	   
 
	   
	public void Continuar() throws SQLException{
		
		
		if(CKF.isSelected()==true && CKN.isSelected()==true && !NumForm.getText().equals("")){
			
			LocalDate ahora = LocalDate.now();
			
			
			
			Formulario form = new Formulario(Integer.parseInt(NumForm.getText()), cb1.getValue(), cb2.getValue(), cb3.getValue(), cb4.getValue(), cb5.getValue(), cb6.getValue(), cb7.getValue(), cb8.getValue(), cb9.getValue(), cb10.getValue(), cb11.getValue(), cb12.getValue(), cb13.getValue(), cb14.getValue(), cb15.getValue(), cb16.getValue(), cb17.getValue(), cb18.getValue(), cb19.getValue(),cb20.getValue(), cb21.getValue(), cb22.getValue(), cb23.getValue(), cb24.getValue(), cb25.getValue(), cb26.getValue(), cb27.getValue(), cb28.getValue(), cb29.getValue(), cb30.getValue(), cb31.getValue(), cb32.getValue(), cb33.getValue(), cb34.getValue(), cb35.getValue(), ahora.toString());
			
			con.GuardarFormulario(form,Integer.parseInt(NumForm.getText()));
			
			this.MnPrincipal.mostrarMenuAltaDonacion();
			
		}else{
			 Alert alerta = new Alert ( AlertType.INFORMATION ); 
	    	   	alerta . setTitle ( "Información" ); 
	    	   	alerta . setHeaderText ("¡No es posible continuar!"); 
	    	   	alerta . setContentText ( "Compruebe el formulario o el número del Donante" );  
	    	   	alerta . showAndWait ();
		}
		
		
		
		
	}
	public void Restablecer(){
		
		
		 for(int i =0;i<cb.size();i++){
			   
			   
			   cb.get(i).setValue("-");
			   
			   
		   }
		
	}
	public void ComprobarN(ActionEvent event) throws SQLException {
		
		
		
		int txt = Integer.parseInt(NID_txt.getText());
		
		
		
		try{
			
			if(NID_txt.getText().equals("") || con.ComprobarNumero(txt)==false){
				CKN.setSelected(false);
				 Alert alerta = new Alert ( AlertType.INFORMATION ); 
		    	   	alerta . setTitle ( "Información" ); 
		    	   	alerta . setHeaderText ("¡Ese número no existe o no puede donar!"); 
		    	   	alerta . setContentText ( "Compruebe el estado del donante o el número introducido" );  
		    	   	alerta . showAndWait ();
			
		
			
			}else{
				
				
				if(con.ComprobarNumero(txt)==true){
					CKN.setSelected(true);
					 Alert alerta = new Alert ( AlertType.INFORMATION ); 
			    	   	alerta . setTitle ( "Información" ); 
			    	   	alerta . setHeaderText ("¡Válido!"); 
			    	   	alerta . setContentText ( "Rellene el Formulario y valídelo" );  
			    	   	alerta . showAndWait ();
				
				}
				
				
				
			}
		}catch(SQLException e){
			System.out.println(e);
		}
				

		
				
				
					
					
						
			
			
				
				
				
		
		
				
				
	}
	public void ComprobarF(ActionEvent event){
		
		
		//Si está vaío
		int contador=0;
		 for(int i =0;i<cb.size();i++){
			 if(cb.get(i).getValue().equals("-")){
				 contador++; 
			 }

		 }
		 //Exclusion temporal
		 int contadorTemp=0;
		 if(cb.get(0).getValue().equals("NO") || cb.get(2).getValue().equals("NO") ||
				 
			 cb.get(11).getValue().equals("SI") || cb.get(13).getValue().equals("SI") || 
				 
			 cb.get(15).getValue().equals("SI") || cb.get(16).getValue().equals("SI")){
			
			 contadorTemp++;
			  
		 }
		 
		 
		 //Exclusion definitiva
		 int contadorDefinitiva=0;
		 if(cb.get(32).getValue().equals("SI") || cb.get(33).getValue().equals("SI") ||
				 
				 cb.get(34).getValue().equals("SI")){
				
				 contadorDefinitiva++;
				  
			 }
		 if(contadorDefinitiva!=0){
			 Alert alerta = new Alert ( AlertType.INFORMATION ); 
	    	   	alerta . setTitle ( "Información" ); 
	    	   	alerta . setHeaderText ("¡El resultado de su Formulario es Negativo!"); 
	    	   	alerta . setContentText ( "Usted petenece a la categoría de excluídos de forma DEFINITIVA" );  
	    	   	alerta . showAndWait ();
	    	
	    	   	CKF.setSelected(false);
	    	   	
		 }
		 if(contadorTemp!=0 && contadorDefinitiva==0){ 
			 Alert alerta = new Alert ( AlertType.INFORMATION ); 
	    	   	alerta . setTitle ( "Información" ); 
	    	   	alerta . setHeaderText ("¡El resultado de su Formulario es Negativo!"); 
	    	   	alerta . setContentText ( "Usted petenece a la categoría de excluídos de forma TEMPORAL" );  
	    	   	alerta . showAndWait ();
	    	   
	    	   	CKF.setSelected(false);
		 }
		 if(contador!=0){
				Alert alerta = new Alert ( AlertType.INFORMATION ); 
	    	   	alerta . setTitle ( "Información" ); 
	    	   	alerta . setHeaderText (null); 
	    	   	alerta . setContentText ( "¡Rellene todos los campos de las preguntas!" );  
	    	   	alerta . showAndWait ();
			}
		 
		 
		 if(contador==0 && contadorTemp==0 && contadorDefinitiva==0){
			 Alert alerta = new Alert ( AlertType.INFORMATION ); 
	    	   	alerta . setTitle ( "Información" ); 
	    	   	alerta . setHeaderText ("¡Gracias por rellenar el Formulario!"); 
	    	   	alerta . setContentText ( "Compruebe si ha validado su Nº de Donante y pulse Continuar" );  
	    	   	alerta . showAndWait ();
	    	 
	    	   	CKF.setSelected(true);
		 }
		 

		 
		 
		 
		 
		 
		
		 
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
