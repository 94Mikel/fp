package AccesoDOM;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.*;

public class AccesoDomObjeto {
	
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
	
	public ArrayList<Libro> obtenerListaLibro(Document doc){
		ArrayList<Libro> arrayLibros = new ArrayList<Libro>();
		Node hijo;  
		Node raiz = doc.getFirstChild();
		NodeList hijos = raiz.getChildNodes();
		for(int i=0; i<hijos.getLength(); i++){
			hijo = hijos.item(i);
			if(hijo.getNodeType()==Node.ELEMENT_NODE){
				Libro libro = procesarLibroObjeto(hijo);
				arrayLibros.add(libro);
			}
		}
		return arrayLibros;
	}
	
	public Libro procesarLibroObjeto(Node hijo) {
		Libro libro = new Libro();
		libro.setPublicadoEn(hijo.getAttributes().item(0).getNodeValue());
		NodeList nietos = hijo.getChildNodes();
		for(int i=0; i<nietos.getLength(); i++){
			Node nieto = nietos.item(i);
			if(nieto.getNodeType()==Node.ELEMENT_NODE){
				if(nieto.getNodeName()=="Titulo"){
				libro.setTitulo(nieto.getChildNodes().item(0).getNodeValue());		
				}																		
				if(nieto.getNodeName()=="Autor"){
					libro.setAutor(nieto.getChildNodes().item(0).getNodeValue());
			}
		}
	}
		return libro;
}
	
	
	public void a�adirlibro(ArrayList<Libro> arrayLibros, String A�o, String Titulo, String Autor){
		Libro libronew = new Libro();
		libronew.setPublicadoEn(A�o);
		libronew.setAutor(Autor);
		libronew.setTitulo(Titulo);
		arrayLibros.add(libronew);
	}

	public void mostrarArray(ArrayList<Libro>arrayLibros){
		for (int i=0; i<arrayLibros.size();i++){
			System.out.println("Publicado en: " + arrayLibros.get(i).getPublicadoEn());
			System.out.println("Titulo: " + arrayLibros.get(i).getTitulo());
			System.out.println("Autor: " + arrayLibros.get(i).getAutor());
			System.out.println("--------------------------------------------" );
			
		}
	}
	
	public void modificarLibro(ArrayList<Libro> arrayLibros,String Titulo, String A�oNuevo){
		for (int i=0; i<arrayLibros.size();i++){
			if(arrayLibros.get(i).getTitulo().equals(Titulo)){
				arrayLibros.get(i).setPublicadoEn(A�oNuevo);
			}
		}
	}

	public void eliminarLibro(ArrayList<Libro> arrayLibros,String Titulo){
		for (int i=0; i<arrayLibros.size();i++){
			if(arrayLibros.get(i).getTitulo().equals(Titulo)){
				arrayLibros.remove(i);
			}
		}
	}

	public void DeArrayaDom(Document doc, ArrayList<Libro> arrayLibros){
		//sacamos el elemento raiz
		Node raiz = doc.getFirstChild();
		//sacamos los hijos del raiz
		NodeList hijos = raiz.getChildNodes();
		//Vamos borrando todos los hijos xq es info. vieja
		for (int i=0; i<hijos.getLength();i++){
			if(hijos.item(i).getNodeType()==Node.ELEMENT_NODE){
				raiz.removeChild(hijos.item(i));
			}
		}
		//Segundo paso crear el arbol Dom nuevo
		for (int i=0;i<arrayLibros.size();i++){
			try{
				//Creamos un elemento titulo
				Node nTitulo = doc.createElement("Titulo");
				//creamos un nodo de texto hijo suyo
				Node nTituloText = doc.createTextNode(arrayLibros.get(i).getTitulo());
				nTitulo.appendChild(nTituloText);
				//creamos un elemento autor
				Node nAutor = doc.createElement("Autor");
				//creamos un nodo de texto hijo suyo
				Node nAutorText = doc.createTextNode(arrayLibros.get(i).getTitulo());
				nAutor.appendChild(nAutorText);
				//creamos un elemento libro
				Node nLibro = doc.createElement("Libro");
				((Element)nLibro).setAttribute("Publicado_en", arrayLibros.get(i).getPublicadoEn());
				//Le decimos que nTitulo y nAutor son sus hijos
				nLibro.appendChild(nTitulo);
				nLibro.appendChild(nAutor);
				//nLibro meterlo como hijo de raiz
				raiz.appendChild(nLibro);
				
				}catch(Exception e){
					e.printStackTrace();
				}
		}
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccesoDomObjeto accDOM = new AccesoDomObjeto();
		//PAsamos del fichero xml al arbol DOM
		accDOM.abrirXML_DOM("LibrosXML.xml");
		//procesaremos el arbol
		ArrayList<Libro> libros= accDOM.obtenerListaLibro(accDOM.getDocument());
		/*
		for (int i=0; i<libros.size();i++){
			System.out.println("Publicado en: " + libros.get(i).getPublicadoEn());
			System.out.println("Titulo: " + libros.get(i).getTitulo());
			System.out.println("Autor: " + libros.get(i).getAutor());
			System.out.println("--------------------------------------------" );
			
		}*/
		//A�ADIR
		accDOM.a�adirlibro(libros, "2016", "Mis memorias", "Gari Diz");
		//MODIFICAR
		accDOM.modificarLibro(libros, "Mis memorias","2030");
		//BORRAR libro
		accDOM.eliminarLibro(libros, "El Capote");
		accDOM.mostrarArray(libros);
		//INSERTAR LIBRO NUEVO
		accDOM.DeArrayaDom(accDOM.getDocument(),libros);
		
		accDOM.guardarDOM_XML("LibrosXML3.xml");
		//pasar del arbol DOM al fichero XML
		//accDOM.guardarDOM_XML("LibrosXML.xml");
	}
}

