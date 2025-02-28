package AccesoDOM;

import java.io.*;
//import para trabajo XML
import javax.xml.parsers.*;
import org.w3c.dom.*;

//Librerias para poder sacar el arbol dom

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

public class AccesoDOM {
	
	Document document;
	
	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
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
	
	public void recorrerDOM(Document doc){
		Node raiz = document.getFirstChild();
		Node hijo;
		NodeList hijos = raiz.getChildNodes();
		
		//Recorrer los hijos del raiz q estan en el Nodelist hijos
		for(int i=0;i<hijos.getLength(); i++){
			hijo = hijos.item(i);
			if(hijo.getNodeType()==Node.ELEMENT_NODE){
				//Este hijo es un libro
				procesarHijosyMostrar2(hijo);
			}
		}
	}
	
	//hacer esto siempre para hacer funcionar algo (PUBLIC VOID)

	//Este metodo dependera de la estructura generada por el  en arbol DOM
	public void procesarHijosyMostrar(Node hijo){
		String datos[] = new String [3];
		datos[0] = hijo.getAttributes().item(0).getNodeValue();
		//Creamos un array con los hijos de los hijo de raiz llamados nietos
		NodeList nietos = hijo.getChildNodes();
		int contador = 1;
		for (int i = 0; i < nietos.getLength();i++){
			Node nieto = nietos.item(i);
			if (nieto.getNodeType()==Node.ELEMENT_NODE){
				datos[contador]=nieto.getChildNodes().item(0).getNodeValue();
				contador++;
			}
		}
		System.out.println("Publicado en: " + datos[0]);
		System.out.println("Titulo: " + datos[1]);
		System.out.println("Autor: " + datos[2]);
		System.out.println("--------------------------");
	}
	
	public void procesarHijosyMostrar2(Node hijo){
	//sumando los atributos y los nodos nietos suman 3  en libros
		String datos[]= new String[4];
		Node nieto;
		NodeList nietos = hijo.getChildNodes();
		//Como los atributos tambien son nodos cogemso los atributos, el que este en laprimera posicion
		//y cogemos el valor.
		datos[0]=hijo.getAttributes().item(0).getNodeValue();
				
		//Iniciamos el contador a 1 porque ocupa la posicion 0 los atributos recogidos con anterioridad
			int contador=1;
			for(int i=0;i<nietos.getLength();i++){
				nieto=nietos.item(i);
				if(nieto.getNodeType()==Node.ELEMENT_NODE){
					if(nieto.getNodeName()=="Grupo"){ //Los nombres tienen q estar escritos igual que en el .xml
						datos[3]=nieto.getAttributes().item(0).getNodeValue();
					}
					datos[contador]=nieto.getChildNodes().item(0).getNodeValue();
					contador++;
					}
		}
		System.out.println("Estilo Musical: " + datos[0]);
		System.out.println("Titulo: " + datos[1]);
		System.out.println("Grupo: " + datos[2]);
		System.out.println("Componentes: " + datos[3]);
		System.out.println("--------------------------");
	}
	
	//Metodo para añadir un elemento liobro a la raiz
	public void añadirDOM(String autor, String titulo, String año ){
		
		try{
			
			//Creamos un nodo de tipo ELEMENT para <titulo>
			Node nTitulo = document.createElement("Titulo");
			//Creamos otro nodo esta vez de texto con el valor
			Node nTituloText = document.createTextNode(titulo);
			nTitulo.appendChild(nTituloText);
			
			//Creamos un nodo de tipo ELEMENT para <Autor>
			Node nAutor = document.createElement("Autor");
			//Creamos otro nodo esta vez de texto con el valor
			Node nAutorText = document.createTextNode(autor);
			nAutor.appendChild(nAutorText);
			
			//Creamos un nodo de tipo ELEMENT para <Libro>
			Node nLibro = document.createElement("Libro");
			((Element)nLibro).setAttribute("Publicado_en", año);
			//Se añaden los nodos autor y titulo como hijos de libro
			nLibro.appendChild(nTitulo);
			nLibro.appendChild(nAutor);
			
			//Ahora añadimos todo el arbol del libro a la raiz
			Node raiz = document.getFirstChild();
			raiz.appendChild(nLibro);
			
		}catch(Exception e){
			e.printStackTrace();
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
	
	//Metodo para añadir un elemento liobro a la raiz
		public void añadirDOM2(String autor, String titulo, String año, String componente ){
			
			try{
				
				//Creamos un nodo de tipo ELEMENT para <titulo>
				Node nTitulo = document.createElement("Titulo");
				//Creamos otro nodo esta vez de texto con el valor
				Node nTituloText = document.createTextNode(titulo);
				nTitulo.appendChild(nTituloText);
				
				//Creamos un nodo de tipo ELEMENT para <Autor>
				Node nAutor = document.createElement("Autor");
				//Creamos otro nodo esta vez de texto con el valor
				Node nAutorText = document.createTextNode(autor);
				nAutor.appendChild(nAutorText);
				
				//Creamos un nodo de tipo ELEMENT para <Libro>
				Node nLibro = document.createElement("Libro");
				((Element)nLibro).setAttribute("Publicado_en", año);
				//Se añaden los nodos autor y titulo como hijos de libro
				nLibro.appendChild(nTitulo);
				nLibro.appendChild(nAutor);
				
				//Ahora añadimos todo el arbol del libro a la raiz
				Node raiz = document.getFirstChild();
				raiz.appendChild(nLibro);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccesoDOM  acDOM = new AccesoDOM();
		//acDOM.abrirXML_DOM("LibrosXML.xml");
		//acDOM.recorrerDOM(acDOM.getDocument());
		
	//PARA DISCOS.xml
		//acDOM.abrirXML_DOM("discos.xml");
		//acDOM.recorrerDOM(acDOM.getDocument());
		
		//acDOM.abrirXML_DOM("LibrosXML.xml");
		//acDOM.añadirDOM("Stephen King", "El Resplandor", "1984");
		//acDOM.recorrerDOM(acDOM.getDocument());
		//acDOM.guardarDOM_XML("LibrosXML.xml");
		
	//Para Discos.xml	
		acDOM.abrirXML_DOM("discos.xml");
		acDOM.añadirDOM2("La Polla Records", "No Somos Nada", "1984", "4");
		acDOM.recorrerDOM(acDOM.getDocument());
		acDOM.guardarDOM_XML("discos.xml");
		
	}
}
