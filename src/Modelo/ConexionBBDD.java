package Modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



	public class ConexionBBDD {
		private String url= "";
		private   String user = "";
		private String pwd = "";
		private   String usr = "";
		private   Connection conexion;
		
	public ConexionBBDD()  {

		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("src/Modelo/datos.ini");
			if (miFichero.exists()){
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				url=propiedades.getProperty("url");
				user=propiedades.getProperty("user");
				pwd=propiedades.getProperty("pwd");
				usr=propiedades.getProperty("usr");
			}

			else
				System.out.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, user, pwd);

			if(conexion.isClosed())
				System.out.println("Fallo en Conexión con la Base de Datos");


		}catch (Exception e) {
			System.out.println("ERROR en conexión con ORACLE");
			e.printStackTrace();
		}
	}
	
	public ObservableList<Donante> MostrarTabla() throws SQLException{

		

		
		ObservableList<Donante> listaPersonas =  FXCollections.observableArrayList();

		//Preparo la conexión para ejecutar sentencias SQL de tipo update
		Statement stm = conexion.createStatement();

		// Preparo la sentencia SQL CrearTablaPersonas
		String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE";
		
		//ejecuto la sentencia
		try{
			ResultSet resultado = stm.executeQuery(selectsql);

			while(resultado.next()){
				int N_donante=resultado.getInt(1);
				String Identificacion=resultado.getString(2);
				String Nombre=resultado.getString(3);
				String Ape1=resultado.getString(4);
				String Ape2=resultado.getString(5);
				String Email=resultado.getString(6);
				String Estado=resultado.getString(7);
				int Telefono=resultado.getInt(8);
				int Cod_Postal=resultado.getInt(9);
				String Fecha_Nac=resultado.getString(10);
				String Sexo=resultado.getString(11);
				String GrupoS=resultado.getString(12);
				String Ciclo=resultado.getString(13);
				
				Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
				listaPersonas.add(donante);
			}

		}catch(SQLException sqle){

			System.out.println(sqle);
			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0,pos);

			System.out.println(codeErrorSQL);
		}
		

		return listaPersonas;
	}
	
	public ObservableList<Donante> Filtrar(String nombre,String estado,String ciclo) throws SQLException{

		//"SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE NOMBRE = ? AND APELLIDO1 = ? AND IDENTIFICACION = ? AND ESTADO = ? AND CICLO = ?"
		/*
		 * 		pstmt.setString(1, nombre);
    			pstmt.setString(2, ape1);
    			pstmt.setString(3, dni);
    			pstmt.setString(4, estado);
    			pstmt.setString(5, ciclo);
    	*/
    	ObservableList<Donante> listaPersonas2 =  FXCollections.observableArrayList();
    	
    	//Preparo la conexión para ejecutar sentencias SQL de tipo update
    			
    			
    			if(nombre.length()!=0 && estado.equals("-") && ciclo.equals("-")){
    		
					    		// Preparo la sentencia SQL CrearTablaPersonas
								String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE NOMBRE = ?";
								PreparedStatement pstmt = conexion.prepareStatement(selectsql);
								
								pstmt.setString(1, nombre);
								
								//ejecuto la sentencia
								try{
									ResultSet resultado = pstmt.executeQuery();
					
									while(resultado.next()){
										
										int N_donante=resultado.getInt(1);
										String Identificacion=resultado.getString(2);
										String Nombre=resultado.getString(3);
										String Ape1=resultado.getString(4);
										String Ape2=resultado.getString(5);
										String Email=resultado.getString(6);
										String Estado=resultado.getString(7);
										int Telefono=resultado.getInt(8);
										int Cod_Postal=resultado.getInt(9);
										String Fecha_Nac=resultado.getString(10);
										String Sexo=resultado.getString(11);
										String GrupoS=resultado.getString(12);
										String Ciclo=resultado.getString(13);
										
										Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
										listaPersonas2.add(donante);
									}
					
								}catch(SQLException sqle){
					
									System.out.println(sqle);
									
									int pos = sqle.getMessage().indexOf(":");
									String codeErrorSQL = sqle.getMessage().substring(0,pos);
					
									System.out.println(codeErrorSQL);
								}
    		}else{
    			
    			if((nombre.length()!=0 && !estado.equals("-") && ciclo.equals("-"))){
    				
    				
    				String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE NOMBRE = ? AND ESTADO = ?";
					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
					
					pstmt.setString(1, nombre);
					pstmt.setString(2, estado);
					
					//ejecuto la sentencia
					try{
						ResultSet resultado = pstmt.executeQuery();
		
						while(resultado.next()){
							
							int N_donante=resultado.getInt(1);
							String Identificacion=resultado.getString(2);
							String Nombre=resultado.getString(3);
							String Ape1=resultado.getString(4);
							String Ape2=resultado.getString(5);
							String Email=resultado.getString(6);
							String Estado=resultado.getString(7);
							int Telefono=resultado.getInt(8);
							int Cod_Postal=resultado.getInt(9);
							String Fecha_Nac=resultado.getString(10);
							String Sexo=resultado.getString(11);
							String GrupoS=resultado.getString(12);
							String Ciclo=resultado.getString(13);
							
							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
							listaPersonas2.add(donante);
						}
		
					}catch(SQLException sqle){
		
						System.out.println(sqle);
						
						int pos = sqle.getMessage().indexOf(":");
						String codeErrorSQL = sqle.getMessage().substring(0,pos);
		
						System.out.println(codeErrorSQL);
					}
    				
    				
    			}else{
    				if((nombre.length()!=0 && !estado.equals("-") && !ciclo.equals("-"))){
    					
    					
    					String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE NOMBRE = ? AND ESTADO = ? AND CICLO=?";
    					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
    					
    					pstmt.setString(1, nombre);
    					pstmt.setString(2, estado);
    					pstmt.setString(3, ciclo);
    					
    					//ejecuto la sentencia
    					try{
    						ResultSet resultado = pstmt.executeQuery();
    		
    						while(resultado.next()){
    							
    							int N_donante=resultado.getInt(1);
    							String Identificacion=resultado.getString(2);
    							String Nombre=resultado.getString(3);
    							String Ape1=resultado.getString(4);
    							String Ape2=resultado.getString(5);
    							String Email=resultado.getString(6);
    							String Estado=resultado.getString(7);
    							int Telefono=resultado.getInt(8);
    							int Cod_Postal=resultado.getInt(9);
    							String Fecha_Nac=resultado.getString(10);
    							String Sexo=resultado.getString(11);
    							String GrupoS=resultado.getString(12);
    							String Ciclo=resultado.getString(13);
    							
    							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
    							listaPersonas2.add(donante);
    						}
    		
    					}catch(SQLException sqle){
    		
    						System.out.println(sqle);
    						
    						int pos = sqle.getMessage().indexOf(":");
    						String codeErrorSQL = sqle.getMessage().substring(0,pos);
    		
    						System.out.println(codeErrorSQL);
    					}
    					
    				}else{
    					
    					if((nombre.length()==0 && !estado.equals("-") && ciclo.equals("-"))){
    						
    						
    						String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE ESTADO = ?";
        					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
        					
        					
        					pstmt.setString(1, estado);
        					
        					
        					//ejecuto la sentencia
        					try{
        						ResultSet resultado = pstmt.executeQuery();
        		
        						while(resultado.next()){
        							
        							int N_donante=resultado.getInt(1);
        							String Identificacion=resultado.getString(2);
        							String Nombre=resultado.getString(3);
        							String Ape1=resultado.getString(4);
        							String Ape2=resultado.getString(5);
        							String Email=resultado.getString(6);
        							String Estado=resultado.getString(7);
        							int Telefono=resultado.getInt(8);
        							int Cod_Postal=resultado.getInt(9);
        							String Fecha_Nac=resultado.getString(10);
        							String Sexo=resultado.getString(11);
        							String GrupoS=resultado.getString(12);
        							String Ciclo=resultado.getString(13);
        							
        							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
        							listaPersonas2.add(donante);
        						}
        		
        					}catch(SQLException sqle){
        		
        						System.out.println(sqle);
        						
        						int pos = sqle.getMessage().indexOf(":");
        						String codeErrorSQL = sqle.getMessage().substring(0,pos);
        		
        						System.out.println(codeErrorSQL);
        					}
    						
    					}else{
    						if((nombre.length()==0 && !estado.equals("-") && !ciclo.equals("-"))){
    							
    							String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE ESTADO = ? AND CICLO=?";
            					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
            					
            					
            					pstmt.setString(1, estado);
            					pstmt.setString(2, ciclo);
            					
            					
            					//ejecuto la sentencia
            					try{
            						ResultSet resultado = pstmt.executeQuery();
            		
            						while(resultado.next()){
            							
            							int N_donante=resultado.getInt(1);
            							String Identificacion=resultado.getString(2);
            							String Nombre=resultado.getString(3);
            							String Ape1=resultado.getString(4);
            							String Ape2=resultado.getString(5);
            							String Email=resultado.getString(6);
            							String Estado=resultado.getString(7);
            							int Telefono=resultado.getInt(8);
            							int Cod_Postal=resultado.getInt(9);
            							String Fecha_Nac=resultado.getString(10);
            							String Sexo=resultado.getString(11);
            							String GrupoS=resultado.getString(12);
            							String Ciclo=resultado.getString(13);
            							
            							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
            							listaPersonas2.add(donante);
            						}
            		
            					}catch(SQLException sqle){
            		
            						System.out.println(sqle);
            						
            						int pos = sqle.getMessage().indexOf(":");
            						String codeErrorSQL = sqle.getMessage().substring(0,pos);
            		
            						System.out.println(codeErrorSQL);
            					}
    						}else{
    							
    							if((nombre.length()==0 && estado.equals("-") && !ciclo.equals("-"))){
    								
    								
    								String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE CICLO=?";
                					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
                					
                					
                				
                					pstmt.setString(1, ciclo);
                					
                					
                					//ejecuto la sentencia
                					try{
                						ResultSet resultado = pstmt.executeQuery();
                		
                						while(resultado.next()){
                							
                							int N_donante=resultado.getInt(1);
                							String Identificacion=resultado.getString(2);
                							String Nombre=resultado.getString(3);
                							String Ape1=resultado.getString(4);
                							String Ape2=resultado.getString(5);
                							String Email=resultado.getString(6);
                							String Estado=resultado.getString(7);
                							int Telefono=resultado.getInt(8);
                							int Cod_Postal=resultado.getInt(9);
                							String Fecha_Nac=resultado.getString(10);
                							String Sexo=resultado.getString(11);
                							String GrupoS=resultado.getString(12);
                							String Ciclo=resultado.getString(13);
                							
                							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
                							listaPersonas2.add(donante);
                						}
                		
                					}catch(SQLException sqle){
                		
                						System.out.println(sqle);
                						
                						int pos = sqle.getMessage().indexOf(":");
                						String codeErrorSQL = sqle.getMessage().substring(0,pos);
                		
                						System.out.println(codeErrorSQL);
                					}
                					
    								
    								
    							}else{
    								if((nombre.length()!=0 && estado.equals("-") && !ciclo.equals("-"))){
    									String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE WHERE NOMBRE=? AND CICLO=?";
                    					PreparedStatement pstmt = conexion.prepareStatement(selectsql);
                    					
                    					
                    				
                    					pstmt.setString(1, nombre);
                    					pstmt.setString(2, ciclo);
                    					
                    					
                    					//ejecuto la sentencia
                    					try{
                    						ResultSet resultado = pstmt.executeQuery();
                    		
                    						while(resultado.next()){
                    							
                    							int N_donante=resultado.getInt(1);
                    							String Identificacion=resultado.getString(2);
                    							String Nombre=resultado.getString(3);
                    							String Ape1=resultado.getString(4);
                    							String Ape2=resultado.getString(5);
                    							String Email=resultado.getString(6);
                    							String Estado=resultado.getString(7);
                    							int Telefono=resultado.getInt(8);
                    							int Cod_Postal=resultado.getInt(9);
                    							String Fecha_Nac=resultado.getString(10);
                    							String Sexo=resultado.getString(11);
                    							String GrupoS=resultado.getString(12);
                    							String Ciclo=resultado.getString(13);
                    							
                    							Donante donante = new Donante(N_donante,Identificacion,Nombre,Ape1,Ape2,Email,Estado,Telefono,Cod_Postal,Fecha_Nac,Sexo,GrupoS,Ciclo);
                    							listaPersonas2.add(donante);
                    						}
                    		
                    					}catch(SQLException sqle){
                    		
                    						System.out.println(sqle);
                    						
                    						int pos = sqle.getMessage().indexOf(":");
                    						String codeErrorSQL = sqle.getMessage().substring(0,pos);
                    		
                    						System.out.println(codeErrorSQL);
                    					}
    								}
    								
    							}
    						}
    					}
    					
    					
    				}
    			}
    			
    			
    			
    		}
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    			
    					
			return listaPersonas2;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    		
    	}
    	
    public void Eliminar(int numero) throws SQLException{
    	
    	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	
		
		// Preparo la sentencia SQL
		String insertsql = "DELETE FROM "+usr+".DONANTE WHERE N_DONANTE = ?";
		
		PreparedStatement pstmt = conexion.prepareStatement(insertsql);
		
		pstmt.setInt(1, numero);
		//ejecuto la sentencia
		try{
			int resultado = pstmt.executeUpdate();

			if(resultado !=1)
				System.out.println("Error en la inserción " + resultado);
		}catch(SQLException sqle){
			
			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0, pos);
			
			if(codeErrorSQL.equals("ORA-00001") )
				System.out.println("la tabla PERSON2 ya estaba creada!!!");
			else
				System.out.println("Ha habido algún problema con  Oracle al hacer el borrado de tabla");
		}
    	
    	
    	

    }
    	
    	
    	
    public void InsertarDonante(int n_donante, String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento,File imagen, String sexo,
			String g_sangre, String ciclo) throws SQLException, FileNotFoundException{
    	
    	
    	
    		
    	
    	
    	
    	
    	
    	
    	
    	String insertsql = "INSERT INTO "+usr+".DONANTE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conexion.prepareStatement(insertsql);
		
		
		pstmt.setInt(1, n_donante);
		pstmt.setString(2, identificacion);
		pstmt.setString(3, nombre);
		pstmt.setString(4, apellido1);
		pstmt.setString(5, apellido2);
		pstmt.setString(6, email);
		pstmt.setString(7, estado);
		pstmt.setInt(8, telefono);
		pstmt.setInt(9, cod_postal);
		pstmt.setString(10, f_nacimiento);
		FileInputStream convertir_imagen = new FileInputStream (imagen);
		pstmt.setBlob(11, convertir_imagen, imagen.length());
		pstmt.setString(12, sexo);
		pstmt.setString(13, g_sangre);
		pstmt.setString(14, ciclo);
		
		
		//ejecuto la sentencia
	try{
		
			int resultado = pstmt.executeUpdate();
		
			if(resultado != 1)
				System.out.println("Error en la inserción " + resultado);
		
	}catch(SQLException sqle){
		
		int pos = sqle.getMessage().indexOf(":");
		String codeErrorSQL = sqle.getMessage().substring(0, pos);
		if(codeErrorSQL.equals("ORA-00001") )
			System.out.println("Ese correo ya existe!!!");
		
		else if(codeErrorSQL.equals("ORA-00001"))
			
			System.out.println("El correo debe contener un máximo de 20 caracteres");
		else
			System.out.println("Problemas de Inserción: " + sqle);
	}


    	
    	
    	
    	
    	
    }
    	
    	
    	
    public int ProxDonante() throws SQLException{
    	
    	
    	String selectsql = "SELECT N_DONANTE FROM ALBERTO95.DONANTE";
    	Statement stm = conexion.createStatement();
    	
    	ResultSet resultado1 = stm.executeQuery(selectsql);
    	
    	int contador=0;
    	
    	int numerito=0;
    	
    	
    	try{
    	while(resultado1.next()){
    		
    		contador++;
    		 
    	}
    	
    	
    	
	}catch(SQLException sqle){
		
		
		System.out.println(sqle);
		
	}
    	return contador+1;
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
