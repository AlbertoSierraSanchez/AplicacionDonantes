package Modelo;

public class Donacion {

	private int Num_donacion;
	private String Fecha;
	private String Tipo;
	private String Pulso;
	private String TA_sist;
	private String TA_dias;
	private String HB_cap;
	private String HB__ven;
	private int Volumen;
	
	
	
	public Donacion(int num_donacion, String fecha, String tipo, String pulso, String tA_sist, String tA_dias,
			String hB_cap, String hB__ven,int Volumen) {
		super();
		this.Num_donacion = num_donacion;
		this.Fecha = fecha;
		this.Tipo = tipo;
		this.Pulso = pulso;
		this.TA_sist = tA_sist;
		this.TA_dias = tA_dias;
		this.HB_cap = hB_cap;
		this.HB__ven = hB__ven;
		this.Volumen = Volumen;
	}
	
	
	
	




	public int getNum_donacion() {
		return Num_donacion;
	}




	public void setNum_donacion(int num_donacion) {
		Num_donacion = num_donacion;
	}




	public String getFecha() {
		return Fecha;
	}




	public void setFecha(String fecha) {
		Fecha = fecha;
	}




	public String getTipo() {
		return Tipo;
	}




	public void setTipo(String tipo) {
		Tipo = tipo;
	}




	public String getPulso() {
		return Pulso;
	}




	public void setPulso(String pulso) {
		Pulso = pulso;
	}




	public String getTA_sist() {
		return TA_sist;
	}




	public void setTA_sist(String tA_sist) {
		TA_sist = tA_sist;
	}




	public String getTA_dias() {
		return TA_dias;
	}




	public void setTA_dias(String tA_dias) {
		TA_dias = tA_dias;
	}




	public String getHB_cap() {
		return HB_cap;
	}




	public void setHB_cap(String hB_cap) {
		HB_cap = hB_cap;
	}




	public String getHB__ven() {
		return HB__ven;
	}




	public void setHB__ven(String hB__ven) {
		HB__ven = hB__ven;
	}








	public int getVolumen() {
		return Volumen;
	}








	public void setVolumen(int volumen) {
		Volumen = volumen;
	}
	
	
	


}
