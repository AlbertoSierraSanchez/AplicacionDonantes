package Modelo;

import java.io.File;

public class Donante {
	private int N_donante;
	private String Identificacion;
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private String Email;
	private String Estado;
	private int Telefono;
	private int Cod_postal;
	private String F_nacimiento;
	private String Sexo;
	private String G_sangre;
	private String Ciclo;
	private File Imagen;
	
	
	
	public Donante(int n_donante, String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento, String sexo,
			String g_sangre, String ciclo, File imagen) {
		super();
		N_donante = n_donante;
		Identificacion = identificacion;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		Email = email;
		Estado = estado;
		Telefono = telefono;
		Cod_postal = cod_postal;
		F_nacimiento = f_nacimiento;
		Sexo = sexo;
		G_sangre = g_sangre;
		Ciclo = ciclo;
		Imagen = imagen;
	}
	
	public Donante(int n_donante, String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, String f_nacimiento, String sexo,
			String g_sangre, String ciclo) {
		super();
		N_donante = n_donante;
		Identificacion = identificacion;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		Email = email;
		Estado = estado;
		Telefono = telefono;
		Cod_postal = cod_postal;
		F_nacimiento = f_nacimiento;
		Sexo = sexo;
		G_sangre = g_sangre;
		Ciclo = ciclo;
	}
	
	
	
	
	
	
	



	public int getN_donante() {
		return N_donante;
	}



	public void setN_donante(int n_donante) {
		N_donante = n_donante;
	}



	public String getIdentificacion() {
		return Identificacion;
	}



	public void setIdentificacion(String identificacion) {
		Identificacion = identificacion;
	}



	public String getNombre() {
		return Nombre;
	}



	public void setNombre(String nombre) {
		Nombre = nombre;
	}



	public String getApellido1() {
		return Apellido1;
	}



	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}



	public String getApellido2() {
		return Apellido2;
	}



	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getEstado() {
		return Estado;
	}



	public void setEstado(String estado) {
		Estado = estado;
	}



	public int getTelefono() {
		return Telefono;
	}



	public void setTelefono(int telefono) {
		Telefono = telefono;
	}



	public int getCod_postal() {
		return Cod_postal;
	}



	public void setCod_postal(int cod_postal) {
		Cod_postal = cod_postal;
	}



	public String getF_nacimiento() {
		return F_nacimiento;
	}



	public void setF_nacimiento(String f_nacimiento) {
		F_nacimiento = f_nacimiento;
	}



	public String getSexo() {
		return Sexo;
	}



	public void setSexo(String sexo) {
		Sexo = sexo;
	}



	public String getG_sangre() {
		return G_sangre;
	}



	public void setG_sangre(String g_sangre) {
		G_sangre = g_sangre;
	}



	public String getCiclo() {
		return Ciclo;
	}



	public void setCiclo(String ciclo) {
		Ciclo = ciclo;
	}



	public File getImagen() {
		return Imagen;
	}



	public void setImagen(File imagen) {
		Imagen = imagen;
	}
	
	
	
	

}



