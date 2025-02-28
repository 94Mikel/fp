package Aplicacion;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import AccesoDOM.AccesoDomObjeto;
import AccesoDOM.Libro;
import Entidades.Discos;
import Persistence.DiscosPersistence;

public class TestLeerDiscos {
  

  public static void main(String[] args) {
       
	  //Lo llamo DiscosPersistence por la clase a la que se tiene que dirigir
	  DiscosPersistence disPER = new DiscosPersistence();
		//PAsamos del fichero xml al arbol DOM
	  disPER.abrirXML_DOM("DiscosPrueba.xml");
		//procesaremos el arbol
		ArrayList<Discos> discos= disPER.obtenerListaDisco(disPER.getDocument());
		
		//AÑADIR
		//disPER.anyadirNuevoDisco(discos, "2050", "Garitronic","La NocheBuena","Garietton","5:02");
		//disPER.anyadirNuevoDisco(discos, "2050", "Garitronic","El Capote","Garietton","5:02");
		//disPER.anyadirNuevoDisco(discos, "2050", "Garitronic","Mis memorias","Garimiolon","5:02");
		//MODIFICAR
		//disPER.modificarDisco(discos, "Mis memorias","2020");
		//BORRAR Disco
		//disPER.eliminarDisco(discos, "El Capote");
		disPER.mostrarArray(discos);
		
		//Guarda el resultado final
		disPER.guardarDOM_XML("DiscosPrueba.xml");	
  }
}