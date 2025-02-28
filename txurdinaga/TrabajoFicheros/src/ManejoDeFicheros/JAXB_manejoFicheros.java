package ManejoDeFicheros;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ManejoDeFicheros.Libros.Libro;



public class JAXB_manejoFicheros {
	
	Libros misLibros;
	List<Libros.Libro> listaLibro;
	
public void abrirXML_JAXBFicheros(String rutaFichero){
		
		File fichero = new File(rutaFichero);
		
		try{
			//creamos contexto de JAXB
			JAXBContext contexto = JAXBContext.newInstance(Libros.class);
			Unmarshaller u = contexto.createUnmarshaller();
			misLibros = (Libros) u.unmarshal(fichero);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void recorrerFicheros(){
	
		listaLibro= misLibros.getLibro();
		for(int i =0; i<listaLibro.size(); i++){
		
		Libros.Libro Libro = listaLibro.get(i);
		System.out.println("Titulo: " + Libro.getTitulo());
		System.out.println("Autor: " + Libro.getAutor());
		System.out.println("ISBN: " + Libro.getISBN());
		System.out.println("Numero de ejemplares: " + Libro.getNumerodeejemplares());
		System.out.println("Editorial: " + Libro.getEditorial());
		System.out.println("Numero de paginas: " + Libro.getNumerodepaginas());
		System.out.println("Año de edicion: " + Libro.getAñodeedicion());
		System.out.println("--------------------------------");
		}
	}

	public void nuevoLibro(String Titulo, String Autor, String ISBN, String Ndejemp,String Editorial, String Numdepag, String Añoedicion){
		
		Libro nuevoLibro = new Libro();
		nuevoLibro.setTitulo(Titulo);
		nuevoLibro.setAutor(Autor);
		nuevoLibro.setISBN(ISBN);
		nuevoLibro.setNumerodeejemplares(Ndejemp);
		nuevoLibro.setEditorial(Editorial);
		nuevoLibro.setNumerodepaginas(Numdepag);
		nuevoLibro.setAñodeedicion(Añoedicion);
		listaLibro.add(nuevoLibro);
	}

	public void guardarXML_JAXBFicheros(String rutaFichero){
		try{
			//creamos contexto de JAXB
			JAXBContext contexto = JAXBContext.newInstance(Libros.class);
			Marshaller m = contexto.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "ManejodeFicheros.xsd");
			OutputStream os = new FileOutputStream(rutaFichero);
			m.marshal(misLibros, os);
							
			}catch(Exception e){
			e.printStackTrace();
			}
		}
				
	public void modificarLibro(String ISBN, String nuevoTitulo){
		for (int i =0; i <listaLibro.size();i++){
		//Hago la comparativa con el if porque sino de la otra manera me modificaba todos los titulos. Si no fuera por el IF se me movian todos los titulos
			if (listaLibro.get(i).getISBN().equals(ISBN)){
				listaLibro.get(i).setTitulo(nuevoTitulo);
			}
			}
		}

	public static void main(String[] args) {
		
		JAXB_manejoFicheros accJAXBFichero = new JAXB_manejoFicheros();
		accJAXBFichero.abrirXML_JAXBFicheros("ManejodeFicheros.xml");
		accJAXBFichero.recorrerFicheros();
		accJAXBFichero.nuevoLibro("Luces de bohemia","Camilo Jose Cela","123456789","100","CasaLoca","200","1984");
		accJAXBFichero.nuevoLibro("Luces de bohemia","Camilo Jose Cela","1234","100","CasaLoca","200","1984");
		accJAXBFichero.modificarLibro("1234","La Factoria");
		accJAXBFichero.guardarXML_JAXBFicheros("ManejodeFicherosMODIFICADO.xml");

	}
}
