package ManejoDeFicheros;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ManejoDeFicheros.SAX_manejoFicheros;
import ManejoDeFicheros.Libros;
import ManejoDeFicheros.Libros.Libro;

public class SAX_manejoFicheros {
	
	ManejadorSAX sh;
	SAXParser parser;
	static ArrayList<Libro> manejoFichero = new ArrayList<Libro>();
	File ficheroXML;
	Libro libronew;
	
	
	public void Abrir_XMLFichero_SAX(String ruta_archivo){
		try{
			File fichero = new File(ruta_archivo);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//Se crea un objeto SAXParser para interpretar el fichero
			parser = factory.newSAXParser();
			//Se crea una instancia del manejador
			//Que es el que leera secuencialmente el fichero
			sh = new ManejadorSAX();
			ficheroXML = fichero;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void recorrerSAXFicheroyMostrar(){
		try{
			parser.parse(ficheroXML, sh);
		/*	for(int i=0; i<manejoFichero.size(); i++){
				System.out.println("Titulo: " + manejoFichero.get(i).getTitulo());
				System.out.println("Autor: " + manejoFichero.get(i).getAutor());
				System.out.println("ISBN: " + manejoFichero.get(i).getISBN());
				System.out.println("Numero de ejemplares: " + manejoFichero.get(i).getNumerodeejemplares());
				System.out.println("Editorial: " + manejoFichero.get(i).getEditorial());
				System.out.println("Número de páginas: " + manejoFichero.get(i).getNumerodepaginas());
				System.out.println("---------------");
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	class ManejadorSAX extends DefaultHandler{
		int UltimoElemento;
		//boolean autorMetido, isbnMetido, nºejemMetido, editorialMetido, nºpagMetido, AñoEdicionMetido;
		
		public ManejadorSAX(){
			UltimoElemento=0;
		}
		
		public void startDocument() throws SAXException{
			System.out.println("Inicio del documento XML");
		}
		
		public void endDocument() throws SAXException{
			System.out.println("Fin del documento XML");
			System.out.println("---------------------");
		}
	
		//ME DABA UN ERROR QUE ERA QUE AL MOSTRAR POR PANTALLA NO ME SALIAN TODOS LOS .EQUALS --> AHORA SE QUE TIENE QUE ESTAR ESCRITOS IGUAL QUE EN EL XML
		public void startElement(String uri, String localname, String qName, Attributes atts){
			if(qName.equals("Libro")){
				
				System.out.println("Titulo: "+atts.getValue(atts.getQName(0)));
				UltimoElemento=1;
			} 
			else if(qName.equals("Autor")){
				System.out.println("AUTOR: ");
				UltimoElemento=2;
			}
			else if(qName.equals("ISBN")){
				System.out.println("ISBN: ");
				UltimoElemento=3;
			}
			else if(qName.equals("Numerodeejemplares")){
				System.out.println("Nº DE EJEMPLARES: ");
				UltimoElemento=4;
			}
			else if(qName.equals("Editorial")){
				System.out.println("EDITORIAL: ");
				UltimoElemento=5;
			}
			else if(qName.equals("Numerodepaginas")){
				System.out.println("Nº DE PAGINAS: ");
				UltimoElemento=6;
			}
			else if(qName.equals("Añodeedicion")){
				System.out.println("AÑO DE EDICION: ");
				UltimoElemento=7;
			}
		}
		public void endElement(String uri, String localname, String qName, Attributes atts) throws SAXException{
			if(qName.equals("Libro")){

			}
		}
		public void characters(char[] ch, int start, int lenght) throws SAXException{
			String valor =  new String(ch, start, lenght);
			valor = valor.replace("\n", "");
			if(UltimoElemento==1 || UltimoElemento==2 || UltimoElemento==3 || UltimoElemento==4 || UltimoElemento==5 || UltimoElemento==6 || UltimoElemento==7){
				System.out.println(valor);
			}
		}
	}
	
	public static void main(String[] args) {
		try{
			SAX_manejoFicheros accSAXmanejoFichero = new SAX_manejoFicheros();
			//Abrimos el XML mediante SAX
			accSAXmanejoFichero.Abrir_XMLFichero_SAX("ManejodeFicheros.xml");
			//Recorremos el contenido de el ArrayList y lo imprimimos por pantalla
			accSAXmanejoFichero.recorrerSAXFicheroyMostrar();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
