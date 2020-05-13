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
    	
    	
    public void Modificar(int n_donante,String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento,File imagen, String sexo,
			String g_sangre, String ciclo) throws SQLException, FileNotFoundException{
	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	
		
		// Preparo la sentencia SQL
		String insertsql = "UPDATE "+usr+".DONANTE SET IDENTIFICACION =?, NOMBRE=?, APELLIDO1=?, APELLIDO2 =?, EMAIL =?, ESTADO=?, TELEFONO= ?, COD_POSTAL=?, FECHA_NACIMIENTO =?, FOTO=?, GRUPO_SANGRE=?, CICLO=? WHERE N_DONANTE=?";
		
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
		FileInputStream convertir_imagen = new FileInputStream (imagen);
		pstmt.setBlob(10, convertir_imagen, imagen.length());
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
		System.out.println(sqle);
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

			int pos = sqle.getMessage().indexOf(":");
			String codeErrorSQL = sqle.getMessage().substring(0,pos);


				System.out.println( codeErrorSQL);
		}

		return byteImage;
	}

	
	
    public boolean ComprobarCorreo(String correo) throws SQLException{
	
	
	ArrayList<String> listaPersonas =  new ArrayList<String>();

	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	Statement stm = conexion.createStatement();

	// Preparo la sentencia SQL CrearTablaPersonas
	String selectsql = "SELECT EMAIL FROM "+usr+".DONANTE";
	
	//ejecuto la sentencia
	try{
		ResultSet resultado = stm.executeQuery(selectsql);
			int contador=0;
		while(resultado.next()){
			contador++;
			String correoBBDD=resultado.getString(contador);
			
			listaPersonas.add(correoBBDD);
		}

	}catch(SQLException sqle){

		System.out.println(sqle);
		

	}
	
	
	if(listaPersonas.contains(correo)){
		
		return true;
	}else{
		return false;
	}
	
	

	
	


	
	
	
	
	
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
    			int contador=0;
    		while(resultado.next()){
    			contador++;
    			String dniBBDD=resultado.getString(contador);
    			
    			listaPersonas.add(dniBBDD);
    		}

    	}catch(SQLException sqle){

    		System.out.println(sqle);
    	
    	}
    	
    	
    	if(listaPersonas.contains(dni)){
    		
    		return true;
    	}else{
    		return false;
    	}
    	
    	

    	
    	


    	
    	
    	
    	
    	
    }
	
	
	
    public boolean ComprobarTLF(int telfno) throws SQLException{
    	
    	
    	ArrayList<Integer> listaPersonas =  new ArrayList<Integer>();

    	//Preparo la conexión para ejecutar sentencias SQL de tipo update
    	Statement stm = conexion.createStatement();

    	// Preparo la sentencia SQL CrearTablaPersonas
    	String selectsql = "SELECT TELEFONO FROM "+usr+".DONANTE";
    	
    	//ejecuto la sentencia
    	try{
    		ResultSet resultado = stm.executeQuery(selectsql);
    			int contador=0;
    		while(resultado.next()){
    			contador++;
    			int tlfnoBBDD=resultado.getInt(contador);
    			
    			listaPersonas.add(tlfnoBBDD);
    		}

    	}catch(SQLException sqle){

    		System.out.println(sqle);
    	
    	}
    	
    	
    	if(listaPersonas.contains(telfno)){
    		
    		return true;
    	}else{
    		return false;
    	}
    	
    	

    	
    	


    	
    	
    	
    	
    	
    }
	
	
    public boolean ComprobarNumero(int numero) throws SQLException{
	
	
	ArrayList<Integer> listaPersonas =  new ArrayList<Integer>();

	//Preparo la conexión para ejecutar sentencias SQL de tipo update
	Statement stm = conexion.createStatement();

	// Preparo la sentencia SQL CrearTablaPersonas
	String selectsql = "SELECT N_DONANTE FROM "+usr+".DONANTE";
	
	//ejecuto la sentencia
	try{
		ResultSet resultado = stm.executeQuery(selectsql);
			int contador=0;
		while(resultado.next()){
			contador++;
			int numeroBBDD=resultado.getInt(contador);
			
			listaPersonas.add(numeroBBDD);
		}

	}catch(SQLException sqle){

		System.out.println(sqle);
		
	}
	
	
	if(listaPersonas.contains(numero)){
		
		return true;
	}else{
		return false;
	}
	
	

	
	


	
	
	
	
	
}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
