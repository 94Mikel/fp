package Persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import AccesoDOM.Libro;
import Entidades.Discos;

public class DiscosPersistence {

Document document;
	
	public Document getDocument(){
		return document;
	}
	
	//ESTO ES INDEPENDIENTE AL XML
	public void abrirXML_DOM(String ruta_archivo){
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		File fichero = null;
		
		
		try{
			fichero = new File(ruta_archivo);
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document= builder.parse(fichero);
			document.getDocumentElement().normalize(); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	}
}
	
	public void guardarDOM_XML(String ruta_archivo){
		try{
		//Aqui se crea un archivo con el nombre pasado por parametro
		File salida = new File(ruta_archivo);
		//Especifimamos el formato de salida
		OutputFormat format = new OutputFormat(document);
		//Especificamos que indexe  la salida
		format.setIndenting(true);
		//Escribimos el documento donde esta el arbol DOM en el FILE
		XMLSerializer serializer = new XMLSerializer(new FileOutputStream(salida),format);
		serializer.serialize(document);
		}catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public ArrayList<Discos> obtenerListaDisco(Document doc){
		ArrayList<Discos> arrayDiscos = new ArrayList<Discos>();
		Node hijo;  
		Node raiz = doc.getFirstChild();
		NodeList hijos = raiz.getChildNodes();
		for(int i=0; i<hijos.getLength(); i++){
			hijo = hijos.item(i);
			if(hijo.getNodeType()==Node.ELEMENT_NODE){
				Discos disco = procesarDiscoObjeto(hijo);
				arrayDiscos.add(disco);
			}
		}
		return arrayDiscos;
	}
	
	public Discos procesarDiscoObjeto(Node hijo) {
		Discos disco = new Discos();
		disco.setAño_lanzamiento(hijo.getAttributes().item(0).getNodeValue());
		NodeList nietos = hijo.getChildNodes();
		for(int i=0; i<nietos.getLength(); i++){
			Node nieto = nietos.item(i);
			if(nieto.getNodeType()==Node.ELEMENT_NODE){
				if(nieto.getNodeName()=="Grupo"){
					disco.setGrupo(nieto.getChildNodes().item(0).getNodeValue());		
				}																		
				if(nieto.getNodeName()=="Titulo"){
					disco.setTitulo(nieto.getChildNodes().item(0).getNodeValue());
			}
				if(nieto.getNodeName()=="Genero"){
					disco.setGenero(nieto.getChildNodes().item(0).getNodeValue());		
				}																		
				if(nieto.getNodeName()=="Duracion"){
					disco.setDuracion(nieto.getChildNodes().item(0).getNodeValue());
			}
		}
	}
		return disco;
}
	
	public void anyadirNuevoDisco(ArrayList<Discos> arrayDiscos, String Año, String Grupo, String Titulo, String Genero, String Duracion){
		Discos disconew = new Discos();
		disconew.setAño_lanzamiento(Año);
		disconew.setGrupo(Grupo);
		disconew.setTitulo(Titulo);
		disconew.setGenero(Genero);
		disconew.setDuracion(Duracion);		
		arrayDiscos.add(disconew);
	}

	public void mostrarArray(ArrayList<Discos> arrayDiscos){
		for (int i=0; i<arrayDiscos.size();i++){
			System.out.println("Año Lanzamiento: " + arrayDiscos.get(i).getAño_lanzamiento());
			System.out.println("Grupo: " + arrayDiscos.get(i).getGrupo());
			System.out.println("Titulo: " + arrayDiscos.get(i).getTitulo());
			System.out.println("Genero: " + arrayDiscos.get(i).getGenero());
			System.out.println("Duracion: " + arrayDiscos.get(i).getDuracion());			
			System.out.println("--------------------------------------------" );
			
		}
	}
	
	public void modificarDisco(ArrayList<Discos> arrayDiscos,String Titulo, String AñoNuevo){
		for (int i=0; i<arrayDiscos.size();i++){
			if(arrayDiscos.get(i).getTitulo().equals(Titulo)){
				arrayDiscos.get(i).setAño_lanzamiento(AñoNuevo);
			}
		}
	}

	public void eliminarDisco(ArrayList<Discos> arrayDiscos,String Titulo){
		for (int i=0; i<arrayDiscos.size();i++){
			if(arrayDiscos.get(i).getTitulo().equals(Titulo)){
				arrayDiscos.remove(i);
			}
		}
	}

	
	
	
}

