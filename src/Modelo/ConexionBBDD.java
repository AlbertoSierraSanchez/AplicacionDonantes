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
		String selectsql = "SELECT N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,SEXO,GRUPO_SANGRE,CICLO FROM "+usr+".DONANTE ORDER BY 1";
		
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

			System.out.println("Problemas de Mostrar Tabla: " + sqle);
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
					
									System.out.println("Problemas de Filtrar: " + sqle);
									
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
    	
	public ObservableList<Donacion> MostrarDonaciones() throws SQLException{
		
		
		
		ObservableList<Donacion> listaPersonas2 =  FXCollections.observableArrayList();
		
		
		Statement stm = conexion.createStatement();

		// Preparo la sentencia SQL CrearTablaPersonas
		String selectsql = "SELECT NUM_DONACION,FECHA,TIPO_DONACION,PULSO,TA_SIST,TA_DIAS,HB_CAP,HB_VEN,VOLUMEN FROM "+usr+".DONACION";
		
		//ejecuto la sentencia
		try{
			ResultSet resultado = stm.executeQuery(selectsql);

			while(resultado.next()){
				int N_donacion=resultado.getInt(1);
				String Fecha=resultado.getString(2);
				String Tipo=resultado.getString(3);
				String Pulso=resultado.getString(4);
				String TASIS=resultado.getString(5);
				String TADIA=resultado.getString(6);
				String HBCAP=resultado.getString(7);
				String HABVEN=resultado.getString(8);
				int Volumen=resultado.getInt(9);
				
				
				Donacion donacion = new Donacion(N_donacion, Fecha, Tipo, Pulso, TASIS, TADIA, HBCAP, HABVEN, Volumen);
				
				listaPersonas2.add(donacion);
			}

		}catch(SQLException sqle){

			System.out.println("Problemas de Mostrar Tabla: " + sqle);
			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0,pos);

			System.out.println(codeErrorSQL);
		}
		

		return listaPersonas2;
		
		
	}
	
	
	
	public ObservableList<Donacion> FiltrarDon(int numero,String tipo) throws SQLException{
		
		
		
		ObservableList<Donacion> listaDonacion2 =  FXCollections.observableArrayList();
		
		
		if(numero!=0 && tipo.equals("-")){
    		
    		// Preparo la sentencia SQL CrearTablaPersonas
			String selectsql = "SELECT NUM_DONACION,FECHA,TIPO_DONACION,PULSO,TA_SIST,TA_DIAS,HB_CAP,HB_VEN,VOLUMEN FROM "+usr+".DONACION WHERE NUM_DONACION=?";
			PreparedStatement pstmt = conexion.prepareStatement(selectsql);
			
			pstmt.setInt(1, numero);
			
			//ejecuto la sentencia
			try{
				ResultSet resultado = pstmt.executeQuery();

				while(resultado.next()){
					
					int N_donacion=resultado.getInt(1);
					String Fecha=resultado.getString(2);
					String Tipo=resultado.getString(3);
					String Pulso=resultado.getString(4);
					String TASIS=resultado.getString(5);
					String TADIA=resultado.getString(6);
					String HBCAP=resultado.getString(7);
					String HABVEN=resultado.getString(8);
					int Volumen=resultado.getInt(9);
					
					Donacion donacion = new Donacion(N_donacion, Fecha, Tipo, Pulso, TASIS, TADIA, HBCAP, HABVEN, Volumen);
					listaDonacion2.add(donacion);
				}

			}catch(SQLException sqle){

				System.out.println("Problemas de Filtrar: " + sqle);
				
				int pos = sqle.getMessage().indexOf(":");
				String codeErrorSQL = sqle.getMessage().substring(0,pos);

				System.out.println(codeErrorSQL);
			}
			
	}else{
		if(numero==0 && !tipo.equals("-")){
			
			// Preparo la sentencia SQL CrearTablaPersonas
						String selectsql = "SELECT NUM_DONACION,FECHA,TIPO_DONACION,PULSO,TA_SIST,TA_DIAS,HB_CAP,HB_VEN,VOLUMEN FROM "+usr+".DONACION WHERE TIPO_DONACION=?";
						PreparedStatement pstmt = conexion.prepareStatement(selectsql);
						
						pstmt.setString(1, tipo);
						
						//ejecuto la sentencia
						try{
							ResultSet resultado = pstmt.executeQuery();

							while(resultado.next()){
								
								int N_donacion=resultado.getInt(1);
								String Fecha=resultado.getString(2);
								String Tipo=resultado.getString(3);
								String Pulso=resultado.getString(4);
								String TASIS=resultado.getString(5);
								String TADIA=resultado.getString(6);
								String HBCAP=resultado.getString(7);
								String HABVEN=resultado.getString(8);
								int Volumen=resultado.getInt(9);
								
								Donacion donacion = new Donacion(N_donacion, Fecha, Tipo, Pulso, TASIS, TADIA, HBCAP, HABVEN, Volumen);
								listaDonacion2.add(donacion);
							}

						}catch(SQLException sqle){

							System.out.println("Problemas de Filtrar: " + sqle);
							
							int pos = sqle.getMessage().indexOf(":");
							String codeErrorSQL = sqle.getMessage().substring(0,pos);

							System.out.println(codeErrorSQL);
						}
			
		}else{
			
			if(numero!=0 && !tipo.equals("-")){
				
				// Preparo la sentencia SQL CrearTablaPersonas
							String selectsql = "SELECT NUM_DONACION,FECHA,TIPO_DONACION,PULSO,TA_SIST,TA_DIAS,HB_CAP,HB_VEN,VOLUMEN FROM "+usr+".DONACION WHERE TIPO_DONACION=? AND NUM_DONACION=?";
							PreparedStatement pstmt = conexion.prepareStatement(selectsql);
							
							pstmt.setString(1, tipo);
							pstmt.setInt(2, numero);
							
							//ejecuto la sentencia
							try{
								ResultSet resultado = pstmt.executeQuery();

								while(resultado.next()){
									
									int N_donacion=resultado.getInt(1);
									String Fecha=resultado.getString(2);
									String Tipo=resultado.getString(3);
									String Pulso=resultado.getString(4);
									String TASIS=resultado.getString(5);
									String TADIA=resultado.getString(6);
									String HBCAP=resultado.getString(7);
									String HABVEN=resultado.getString(8);
									int Volumen=resultado.getInt(9);
									
									Donacion donacion = new Donacion(N_donacion, Fecha, Tipo, Pulso, TASIS, TADIA, HBCAP, HABVEN, Volumen);
									listaDonacion2.add(donacion);
								}

							}catch(SQLException sqle){

								System.out.println("Problemas de Filtrar: " + sqle);
								
								int pos = sqle.getMessage().indexOf(":");
								String codeErrorSQL = sqle.getMessage().substring(0,pos);

								System.out.println(codeErrorSQL);
							}
				
			}
			
			
		}
	}
	
		return listaDonacion2;
	}
	
	
	public ObservableList<Integer> NumerosDon() throws SQLException{
		
		ObservableList<Integer> NumerosDon = FXCollections.observableArrayList();;
		Statement stm = conexion.createStatement();

		// Preparo la sentencia SQL CrearTablaPersonas
		String selectsql = "SELECT NUM_DONACION FROM "+usr+".DONACION";
		
		//ejecuto la sentencia
		try{
			ResultSet resultado = stm.executeQuery(selectsql);

			while(resultado.next()){
				int N_donacion=resultado.getInt(1);
				

				NumerosDon.add(N_donacion);
			}

		}catch(SQLException sqle){

			System.out.println("Problemas de Cargar Números: " + sqle);
			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0,pos);

			System.out.println(codeErrorSQL);
		}
		
		return NumerosDon;
			
			

		
		
		
	}
	
	
	
	public void EliminarDon(int numero) throws SQLException{
		//Preparo la conexión para ejecutar sentencias SQL de tipo update
		
		
			// Preparo la sentencia SQL
			String insertsql = "DELETE FROM "+usr+".DONACION WHERE NUM_DONACION = ?";
			
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
    	
    	
    public void Modificar(int n_donante,String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento,File imagen, String sexo,
			String g_sangre, String ciclo) throws SQLException, FileNotFoundException{
	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	
		
		// Preparo la sentencia SQL
		String insertsql = "UPDATE "+usr+".DONANTE SET IDENTIFICACION =?, NOMBRE=?, APELLIDO1=?, APELLIDO2 =?, EMAIL =?, ESTADO=?, TELEFONO= ?, COD_POSTAL=?, FECHA_NACIMIENTO =?, FOTO=?, SEXO=?, GRUPO_SANGRE=?, CICLO=? WHERE N_DONANTE=?";
		
		PreparedStatement pstmt = conexion.prepareStatement(insertsql);
		
		
		pstmt.setString(1, identificacion);
		pstmt.setString(2, nombre);
		pstmt.setString(3, apellido1);
		pstmt.setString(4, apellido2);
		pstmt.setString(5, email);
		pstmt.setString(6, estado);
		pstmt.setInt(7, telefono);
		pstmt.setInt(8, cod_postal);
		pstmt.setString(9, f_nacimiento);
		if(imagen!=null){
			FileInputStream convertir_imagen = new FileInputStream (imagen);
			pstmt.setBlob(10, convertir_imagen, imagen.length());
		}else{
			pstmt.setBlob(10, null,0);
		}
		pstmt.setString(11, sexo);
		pstmt.setString(12, g_sangre);
		pstmt.setString(13, ciclo);
		pstmt.setInt(14,n_donante );
		
		
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
    
    
    
    
    public void InsertarDonante(int n_donante,String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento,File imagen, String sexo,
			String g_sangre, String ciclo) throws SQLException, FileNotFoundException{
    	
    	
    	
    		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	String insertsql = "INSERT INTO "+usr+".DONANTE (N_DONANTE,IDENTIFICACION,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,ESTADO,TELEFONO,COD_POSTAL,FECHA_NACIMIENTO,FOTO,SEXO,GRUPO_SANGRE,CICLO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conexion.prepareStatement(insertsql);
		
		//seq_person.nextVal
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
		if(imagen!=null){
			FileInputStream convertir_imagen = new FileInputStream (imagen);
			pstmt.setBlob(11, convertir_imagen, imagen.length());
		}else{
			pstmt.setBlob(11, null,0);
		}
		
		pstmt.setString(12, sexo);
		pstmt.setString(13, g_sangre);
		pstmt.setString(14, ciclo);
		
		
		//ejecuto la sentencia
	try{
		
			int resultado = pstmt.executeUpdate();
		
			if(resultado != 1)
				System.out.println("Error en la inserción " + resultado);
		
	}catch(SQLException sqle){
		System.out.println("Problemas de Insertar Donante: " + sqle);
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
    	
    	

    public byte[] LeerFoto(int numero) throws SQLException{

		byte[] byteImage = null;
		//ejecuto la sentencia
		try{
			// Preparo la sentencia SQL
			String insertsql = "SELECT FOTO FROM ALBERTO95.DONANTE WHERE N_DONANTE=?";
			
			// Prepoparo la sentencia para ejecutar en la base de datos
			PreparedStatement pstmt = conexion.prepareStatement (insertsql);
			pstmt.setInt(1, numero);
			ResultSet resultado = pstmt.executeQuery();

			int contador = 0;
			while(resultado.next()){
				contador++;
				// obtener la columna imagen, luego el arreglo de bytes
			    Blob blob = resultado.getBlob(1);
			    
			    	
			    byteImage = blob.getBytes(1, (int) blob.length());
			    

			}

			if(contador==0)
				System.out.println("no data found");

		}catch(SQLException sqle){
			System.out.println("Problemas de Seleccionar Foto: " + sqle);
			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0,pos);


				System.out.println(codeErrorSQL);
		}

		return byteImage;
	}

	
	
    public boolean ComprobarCorreo(String correo) throws SQLException{
	
	
	

	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	Statement stm = conexion.createStatement();
	ArrayList<String> listaPersonas =  new ArrayList<String>();
	// Preparo la sentencia SQL CrearTablaPersonas
	String selectsql = "SELECT EMAIL FROM "+usr+".DONANTE";
	
	//ejecuto la sentencia
	try{
		ResultSet resultado = stm.executeQuery(selectsql);
			
			
			
		while(resultado.next()){
			
			String correoBBDD=resultado.getString(1);
			
			listaPersonas.add(correoBBDD);
	
		}
		
		
		if(listaPersonas.contains(correo)){
			
			return true;
		}
			return false;
		

		
	}catch(SQLException sqle){

		System.out.println("Problemas de Comprobar correo: " + sqle);
		

	}
	

		return false;
	
}
		
    
   
	
    public boolean ComprobarDNI(String dni) throws SQLException{
    	
    	
    	ArrayList<String> listaPersonas =  new ArrayList<String>();

    	//Preparo la conexión para ejecutar sentencias SQL de tipo update
    	Statement stm = conexion.createStatement();

    	// Preparo la sentencia SQL CrearTablaPersonas
    	String selectsql = "SELECT IDENTIFICACION FROM "+usr+".DONANTE";
    	
    	//ejecuto la sentencia
    	try{
    		ResultSet resultado = stm.executeQuery(selectsql);
    			
    		while(resultado.next()){
    		
    			String dniBBDD=resultado.getString(1);
    			
    			listaPersonas.add(dniBBDD);
    		}

    		if(listaPersonas.contains(dni)){
        		
        		return true;
        	}
    	}catch(SQLException sqle){

    		System.out.println("Problemas de Comprobar DNI: " + sqle);
    	
    	}
    	
    	
    	
    		return false;
    	
    	
    	

    	
    	


    	
    	
    	
    	
    	
    }
	
	
	
    public boolean ComprobarTLF(int telfno) throws SQLException{
    	
    	
    	ArrayList<Integer> listaPersonas =  new ArrayList<Integer>();

    	//Preparo la conexión para ejecutar sentencias SQL de tipo update
    	Statement stm = conexion.createStatement();

    	// Preparo la sentencia SQL CrearTablaPersonas
    	String selectsql = "SELECT TELEFONO FROM "+usr+".DONANTE";
    	int contador=0;
    	//ejecuto la sentencia
    	try{
    		ResultSet resultado = stm.executeQuery(selectsql);
    			
    		while(resultado.next()){
    			
    			int tlfnoBBDD=resultado.getInt(1);
    			listaPersonas.add(tlfnoBBDD);
    			contador++;
    			
    			
    		}
    		
    		if(listaPersonas.contains(telfno)){
        		
        		return true;
        	}

    	}catch(SQLException sqle){

    		System.out.println("Problemas de Comprobar TLFN: " + sqle);
    		System.out.println(contador);
    	
    	}
    	
		return false;
    	
    	
    	
    	
    	

    	
    	


    	
    	
    	
    	
    	
    }
	
	
    public boolean ComprobarNumero(int numero) throws SQLException{
	
	if(numero!=0){
	ArrayList<Integer> listaPersonas =  new ArrayList<Integer>();

	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	Statement stm = conexion.createStatement();

	// Preparo la sentencia SQL CrearTablaPersonas
	String selectsql = "SELECT N_DONANTE FROM "+usr+".DONANTE";
	
	//ejecuto la sentencia
	try{
		
		ResultSet resultado = stm.executeQuery(selectsql);
			
		while(resultado.next()){
			
			int numeroBBDD=resultado.getInt(1);
			
			listaPersonas.add(numeroBBDD);
		}

	
		
		
	}catch(SQLException sqle){

		System.out.println("Problemas de Comprobar N_donante: " + sqle);
		
	}
	
	if(listaPersonas.contains(numero)){
		
		return true;
	}else{
		return false;
	}
		
		

	
	
		}else{
			return false;
		}

    
	}
	
    
    public void GuardarFormulario(Formulario form,int numero) throws SQLException{
    	
    	
	String insertsql = "INSERT INTO "+usr+".FORMULARIO VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conexion.prepareStatement(insertsql);
		//Integer.parseInt(NumForm.getText()), cb1.getValue(), cb2.getValue(), cb3.getValue(), cb4.getValue(), cb5.getValue(), cb6.getValue(), cb7.getValue(), cb8.getValue(), cb9.getValue(), cb10.getValue(), cb11.getValue(), cb12.getValue(), cb13.getValue(), cb14.getValue(), cb15.getValue(), cb16.getValue(), cb17.getValue(), cb18.getValue(), cb19.getValue(),cb20.getValue(), cb21.getValue(), cb22.getValue(), cb23.getValue(), cb24.getValue(), cb25.getValue(), cb26.getValue(), cb27.getValue(), cb28.getValue(), cb29.getValue(), cb30.getValue(), cb31.getValue(), cb32.getValue(), cb33.getValue(), cb34.getValue(), cb35.getValue(), n.toString()
		//seq_person.nextVal
		pstmt.setInt(1, form.Cod_Form);
		pstmt.setString(2, form.cb1);
		pstmt.setString(3, form.cb2);
		pstmt.setString(4, form.cb3);
		pstmt.setString(5, form.cb4);
		pstmt.setString(6, form.cb5);
		pstmt.setString(7, form.cb6);
		pstmt.setString(8, form.cb7);
		pstmt.setString(9, form.cb8);
		pstmt.setString(10, form.cb9);
		pstmt.setString(11, form.cb10);
		pstmt.setString(12, form.cb11);
		pstmt.setString(13, form.cb12);
		pstmt.setString(14, form.cb13);
		pstmt.setString(15, form.cb14);
		pstmt.setString(16, form.cb15);
		pstmt.setString(17, form.cb16);
		pstmt.setString(18, form.cb17);
		pstmt.setString(19, form.cb18);
		pstmt.setString(20, form.cb19);
		pstmt.setString(21, form.cb20);
		pstmt.setString(22, form.cb21);
		pstmt.setString(23, form.cb22);
		pstmt.setString(24, form.cb23);
		pstmt.setString(25, form.cb24);
		pstmt.setString(26,	form.cb25);
		pstmt.setString(27, form.cb26);
		pstmt.setString(28, form.cb27);
		pstmt.setString(29, form.cb28);
		pstmt.setString(30, form.cb29);
		pstmt.setString(31, form.cb30);
		pstmt.setString(32, form.cb31);
		pstmt.setString(33, form.cb32);
		pstmt.setString(34, form.cb33);
		pstmt.setString(35, form.cb34);
		pstmt.setString(36, form.cb35);
		pstmt.setString(37, form.fecha);
		
		
		
		
		
		
		
		
		String insertsql2 = "INSERT INTO "+usr+".RELLENA VALUES (?,?)";
		
		PreparedStatement pstmt2 = conexion.prepareStatement(insertsql);
		
		pstmt.setInt(1, form.Cod_Form);
		pstmt.setInt(2, numero);
		
		
		
		//ejecuto la sentencia
	try{
		
			int resultado = pstmt.executeUpdate();
		
			if(resultado != 1)
				System.out.println("Error en la inserción " + resultado);
			
			
			
			int resultado2 = pstmt2.executeUpdate();
			if(resultado2 != 1)
				System.out.println("Error en la inserción " + resultado);
			
			
		
	}catch(SQLException sqle){
		System.out.println("Problemas de Insertar Donante: " + sqle);
		int pos = sqle.getMessage().indexOf(":");
		String codeErrorSQL = sqle.getMessage().substring(0, pos);
		
		if(codeErrorSQL.equals("ORA-00001") )
			System.out.println("Ese correo ya existe!!!");
		
		else
			
			System.out.println("Problemas de Inserción: " + sqle);
	
    }
    
    
	
	
    }
	}
	
	
	
	
	
	
	
	
