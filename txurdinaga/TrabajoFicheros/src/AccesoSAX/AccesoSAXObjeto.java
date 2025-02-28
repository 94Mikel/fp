package AccesoSAX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class AccesoSAXObjeto {

	ManejadorSAX sh;
	SAXParser parser;
	static ArrayList<Libro> arrayLibros = new ArrayList<Libro>();
	File ficheroXML;
	Libro libronew;
	
	public void Abrir_XML_SAX(String ruta_archivo){
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
	
	private void recorrerSAXyMostrar() throws IOException{
	try{
		//Da inicio al recorrido secuencial
		parser.parse(ficheroXML,sh);		
	}catch(SAXException s){
		s.printStackTrace();
		System.out.println("Excepcion generada por SAX");
	}
}

	class ManejadorSAX extends DefaultHandler{
		int UltimoElemento;
		boolean tituloMetido, autorMetido;
		
		public ManejadorSAX(){
			UltimoElemento=0;
		}
		
		public void startDocument() throws SAXException{
			System.out.println("Inicio del documento XML");
		}
		
		public void endDocument() throws SAXException{
			System.out.println("Fin del documento XML");
		}
	
		public void startElement(String uri, String localname, String qName, Attributes atts){
			if(qName.equals("Libro")){
				libronew = new Libro();
				tituloMetido=false;
				autorMetido=false;
				libronew.setPublicadoEn(atts.getValue(atts.getQName(0)));
				UltimoElemento=0;
			}
			else if(qName.equals("Titulo")){
				System.out.println("Titulo: ");
				UltimoElemento=2;
			}
			else if(qName.equals("Autor")){
				System.out.println("Autor: ");
				UltimoElemento=3;
			}
		}
		public void endElement(String uri, String localname, String qName, Attributes atts) throws SAXException{
			if(qName.equals("Libro")){
				arrayLibros.add(libronew);
			}
		}
		public void characters(char[] ch, int start, int lenght) throws SAXException{
			String valor =  new String(ch, start, lenght);
			valor = valor.replace("\t\n", "");
			if(UltimoElemento==2 && tituloMetido==false){
				System.out.println("Estoy en titulo: " + valor);
				libronew.setTitulo(valor);
			}else if(UltimoElemento==3 && autorMetido==false){
				libronew.setAutor(valor);
				autorMetido=true;
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		try{
		AccesoSAXObjeto accSAX = new AccesoSAXObjeto();
		accSAX.Abrir_XML_SAX("LibrosXML.xml");
		accSAX.recorrerSAXyMostrar();
		for(int i=0; i<arrayLibros.size();i++){
			System.out.println("Publicado en: " + arrayLibros.get(i).getPublicadoEn());
			System.out.println("Titulo: " + arrayLibros.get(i).getTitulo());
			System.out.println("Autor: " + arrayLibros.get(i).getAutor());
			System.out.println("--------------------------------------------" );
		}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
