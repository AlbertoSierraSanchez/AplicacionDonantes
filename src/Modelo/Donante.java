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
	private File imagen;
	
	public Donante(int n_donante, String identificacion, String nombre, String apellido1, String apellido2,
			String email, String estado, int telefono, int cod_postal, File imagen) {
		
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
		this.imagen = imagen;
		
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

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {

		this.imagen = imagen;
	}

}



