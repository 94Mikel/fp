package AccesoJAXB_ejer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import AccesoJAXB.Alumnos;
import AccesoJAXB.Alumnos.Alumno;
import AccesoJAXB_ejer.Lugares.Sitio;



public class AccesoJAXB_ejer {

	Lugares lugares;
	List<Lugares.Sitio> listaLugares;
	
	public void abrirXML_JAXB(String rutaFichero){
		
		File fichero = new File(rutaFichero);
		
		try{
			
			//creamos contexto de JAXB
			JAXBContext contexto = JAXBContext.newInstance(Lugares.class);
			Unmarshaller u = contexto.createUnmarshaller();
			lugares = (Lugares) u.unmarshal(fichero);
			
		}catch(Exception e){
			 
			e.printStackTrace();
		}
	}
	
	
public void recorrerLugares(){
		
		listaLugares= lugares.getSitio();
		for(int i =0; i<listaLugares.size(); i++){
			
			Lugares.Sitio lugar = listaLugares.get(i);
			System.out.println("Nombre: " + lugar.getNombre());
			System.out.println("Provincia: " + lugar.getProvincia());
			System.out.println("Parking: " + lugar.getParking());
			System.out.println("Duchas: " + lugar.getDuchas());
			System.out.println("Hoteles: " + lugar.getHotel());
			System.out.println("--------------------------------");
			
		}
	}
	


public void nuevoLugar(String nombre, String provincia, String parking, String duchas, String hotel){
	
	Sitio nuevoLugar = new Sitio();
	
	nuevoLugar.setNombre(nombre);
	nuevoLugar.setProvincia(provincia);
	nuevoLugar.setParking(parking);
	nuevoLugar.setDuchas(duchas);
	nuevoLugar.setHotel(hotel);
	listaLugares.add(nuevoLugar);
}


public void guardarXML_JAXB(String rutaFichero){
try{
		
		//creamos contexto de JAXB
		JAXBContext contexto = JAXBContext.newInstance(Lugares.class);
		Marshaller m = contexto.createMarshaller();
		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "Lugares.xsd");
		OutputStream os = new FileOutputStream(rutaFichero);
		m.marshal(lugares, os);
		
		
	}catch(Exception e){
		
		e.printStackTrace();
	}
}


public void modificarLugar(String nombre, String hotel){
	for (int i =0; i <listaLugares.size();i++){
		listaLugares.get(i).getNombre().equals(nombre);
			listaLugares.get(i).setHotel(hotel);	
		}
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		AccesoJAXB_ejer accJAXB = new AccesoJAXB_ejer();
		accJAXB.abrirXML_JAXB("Lugares.xml");
		accJAXB.recorrerLugares();
		accJAXB.nuevoLugar("urkiola","Bizkaia", "SI", "no","si");
	/*	accJAXB.modificarLugar("urkiola", "no");
		accJAXB.guardarXML_JAXB("Lugares2.xml");
		*/
		
		
		
		
	}

}
