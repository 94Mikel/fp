package AccesoJAXB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import AccesoJAXB.Alumnos.Alumno;

public class AccesoJAXB {
	
	Alumnos misAlumnos;
	List<Alumnos.Alumno> listaAlumnos;

	public void abrir_XML_JAXB(String ruta_archivo){
		File fichero = new File(ruta_archivo);
		try{
			//Creamos el contexto de JAXB
			JAXBContext contexto = JAXBContext.newInstance(Alumnos.class);
			Unmarshaller u = contexto.createUnmarshaller();
			misAlumnos = (Alumnos) u.unmarshal(fichero);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void recorrerAlumnos(){
		//Creo la lista de Alumnos con el metodo getter de la clase Alumnos
		listaAlumnos = misAlumnos.getAlumno();
		
		for(int i=0; i<listaAlumnos.size(); i++){
			Alumnos.Alumno alumno = listaAlumnos.get(i);
			System.out.println("Anonacimiento: " + alumno.getAnoNacimiento());
			System.out.println("nombre: " + alumno.getNombre());
			System.out.println("apellido: " + alumno.getApellido());
			System.out.println("grupo: " + alumno.getGrupo());
			System.out.println("----------------------------------------");
		}
	}
	
	public void nuevoAlumno(String ano, String nombre, String apellido, String curso){
		Alumno nuevoAlumno = new Alumno();
		nuevoAlumno.setAnoNacimiento(ano);
		nuevoAlumno.setNombre(nombre);
		nuevoAlumno.setApellido(apellido);
		nuevoAlumno.setGrupo(curso);
		listaAlumnos.add(nuevoAlumno);
	}
	
	public void guardar_JAXB_XML(String ruta_archivo){
		try{
			//Creamos el contexto de JAXB
			JAXBContext contexto = JAXBContext.newInstance(Alumnos.class);
			Marshaller m = contexto.createMarshaller();
			//FORMATEAMOS CON
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,"Alumnos.xsd");
			//Asignamos fichero de salida
			OutputStream os = new FileOutputStream(ruta_archivo);
			m.marshal(misAlumnos, os);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void modificarAlumno(String nombre, String nuevocurso){
		for(int i=0; i<listaAlumnos.size();i++){
			listaAlumnos.get(i).getNombre().equals(nombre);{
				listaAlumnos.get(i).setGrupo(nuevocurso);
			}
		}
	}
	
	public static void main(String[] args) {
		AccesoJAXB accJAXB = new AccesoJAXB();
		accJAXB.abrir_XML_JAXB("Alumnos.xml");
		accJAXB.recorrerAlumnos();

		//accJAXB.nuevoAlumno("1975","Pepe","Gotera","2ºdw3");
		//accJAXB.guardar_JAXB_XML("Alumnos2.xml");
		
		//accJAXB.modificarAlumno("Pepe","2ºdm3");
		//accJAXB.guardar_JAXB_XML("Alumnos3.xml");
	}

}
