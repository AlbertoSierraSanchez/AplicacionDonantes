package Modelo;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ImprimePDF {

	
	private String filename;
	private String filepath;
	private int n_donante;
	private String nombre;
	private String ape1;
	private String ape2;
	private String G_Sangre;
	private String Foto;
	
	
	public ImprimePDF(String filename, String filepath) {
		super();
		this.filename = filename;
		this.filepath = filepath;
	}
	public ImprimePDF(String filename,String filepath,String RutaFoto){
		super();
		this.filename = filename;
		this.filepath = filepath;
		this.Foto=RutaFoto;
		
	}


	
	
	
	
	
	
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public int getN_donante() {
		return n_donante;
	}


	public void setN_donante(int n_donante) {
		this.n_donante = n_donante;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApe1() {
		return ape1;
	}


	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}


	public String getApe2() {
		return ape2;
	}


	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}


	



	public String getG_Sangre() {
		return G_Sangre;
	}


	public void setG_Sangre(String g_Sangre) {
		G_Sangre = g_Sangre;
	}


	
	
	
	public void GenerarPDF(int n_donante,String ape1,String ape2,String nombre,String G_Sangre,File foto) throws FileNotFoundException, DocumentException{
		
		String sFileNamePath = filepath + filename+".pdf";

		// Se crea el documento que se va a imprimir
		Document documento = new Document();


		// En principio el tamaño de la página está paara un A4 en vertical, pero puedes investigar y cambiar el tamaño de la zona de impresión
		// Eso sí... te tocará investigar y buscar: el método setPageSize
		//documento.setPageSize() Lo que le pases como  argumento

		System.out.println(sFileNamePath);
		
		
		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
				FileOutputStream ficheroPdf = new FileOutputStream(sFileNamePath);

				// Se asocia el documento al OutputStream y se indica que el espaciado entre
				// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

				// Se abre el documento.
				documento.open();
	
		
						Paragraph p1 = new Paragraph("Número de Socio: " + n_donante,
							FontFactory.getFont("arial",   // fuente
							11,                            // tamaño
							Font.BOLD));
	
							// Una vez creado el párrafo puedes modificar varias opciones... como aquí la alineación
							// ALIGN_CENTER || ALIGN_LEFT || ALIGN_RIGHT ALIGN_JUSTIFY
							p1.setAlignment(Element.ALIGN_RIGHT);
	
							// Añade el párrafo al documeento
							documento.add(p1);


						Paragraph p2 = new Paragraph("Primer Apellido: " + ape1,
								FontFactory.getFont("arial",   // fuente
								11,                            // tamaño
								Font.BOLD));

						// Una vez creado el párrafo puedes modificar varias opciones... como aquí la alineación
						// ALIGN_CENTER || ALIGN_LEFT || ALIGN_RIGHT ALIGN_JUSTIFY
						p2.setAlignment(Element.ALIGN_RIGHT);

						// Añade el párrafo al documeento
						documento.add(p2);


						Paragraph p3 = new Paragraph("Segundo Apellido: " + ape2,
								FontFactory.getFont("arial",   // fuente
								11,                            // tamaño
								Font.BOLD));

						// Una vez creado el párrafo puedes modificar varias opciones... como aquí la alineación
						// ALIGN_CENTER || ALIGN_LEFT || ALIGN_RIGHT ALIGN_JUSTIFY
						p3.setAlignment(Element.ALIGN_RIGHT);

						// Añade el párrafo al documeento
						documento.add(p3);
	
						Paragraph p4 = new Paragraph("Nombre: " + nombre,
								FontFactory.getFont("arial",   // fuente
								11,                            // tamaño
								Font.BOLD));

						// Una vez creado el párrafo puedes modificar varias opciones... como aquí la alineación
						// ALIGN_CENTER || ALIGN_LEFT || ALIGN_RIGHT ALIGN_JUSTIFY
						p4.setAlignment(Element.ALIGN_RIGHT);

						// Añade el párrafo al documeento
						documento.add(p4);
						
						
						Paragraph p5 = new Paragraph("Grupo Sanguíneo: " + G_Sangre,
								FontFactory.getFont("arial",   // fuente
								11,                            // tamaño
								Font.BOLD));

						// Una vez creado el párrafo puedes modificar varias opciones... como aquí la alineación
						// ALIGN_CENTER || ALIGN_LEFT || ALIGN_RIGHT ALIGN_JUSTIFY
						p5.setAlignment(Element.ALIGN_RIGHT);

						// Añade el párrafo al documeento
						documento.add(p5);
						
						
						
		
						// Meter una imagen

						try
						{
							
							String path =  foto.getPath();
							Image foto1 = Image.getInstance(path);

							foto1.scaleToFit(90, 90);
							foto1.setAbsolutePosition(20, 740);
							
							documento.add(foto1);
							
						}
						catch ( Exception e )
						{
							System.out.println(e.getMessage());
						}




						documento.close();

		
	}
	
	
	
	
	
}
