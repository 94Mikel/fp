package negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;

import aereo.Aeropuerto;
import aereo.Avion;
import aereo.Compania;
import aereo.Helicoptero;
import aereo.Vehiculo;

public class ejemploAeropuerto {
	
	private static void insertarTodo() {
		// TODO Auto-generated method stub
		try {
			
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","aereo"));

			//Abre la Base de datos
			db.open();
			db.startTransaction();
			
			
			Compania comp = new Compania(db); 
			
			Aeropuerto aero = new Aeropuerto(db);
			Aeropuerto aero2 = new Aeropuerto(db);	
			
			Avion avion = new Avion(db);
			Avion avion2 = new Avion(db);
			
			Helicoptero heli = new Helicoptero(db);	
			Helicoptero heli2 = new Helicoptero(db);	
			
			Vehiculo vehiculos[]= new Vehiculo[2];
			
			Aeropuerto aeros[] = new Aeropuerto[2];

	           
		 /* aqui si hubiera puesto un dato tipo Date
			GrgorianCalendar fecha = new GregorianCalendar();
			Date fechaActual = new Date();
			fecha.setTime(fechaActual);
			compania.setAno(fecha);
	   	*/
			
			//comp.setNombre("Iberia");
			//comp.setPresidente("Luis Gallego");
			//comp.setAnofundacion(1998);
			
		
			//aero.setNombre("Madrid");	
			//aero.setPais("España");
			//aero.setNumeropistas(6);
			
			//avion.setNmotores(2);
			//avion.setPotencia(4000);
			//avion.setModelo("Boeing A420");
			//avion.setano(1995);
			//avion.setNmatricula(12233);
			//avion.setCombustible(22280);
			
			//avion.setNmotores(5);
			//avion.setPotencia(5000);
			//avion.setModelo("Boeing A550");
			//avion.setano(1955);
			//avion.setNmatricula(55633);
			//avion.setCombustible(55280);
			
			avion.setNmotores(10);
			avion.setPotencia(8010);
			avion.setModelo("Boeing A10550");
			avion.setano(2105);
			avion.setNmatricula(25433);
			avion.setCombustible(11280);
			
			//heli.setNhelices(1);
			//heli.setMedida(8);
			//	heli.setCapacidad(12);
			//	heli.setano(2010);
			//	heli.setNmatricula(3344421);
			//	heli.setCombustible(5000);
			
			
		/*	aero2.setNombre("Barcelona");	
			aero2.setPais("España");
			aero2.setNumeropistas(7);
			
			avion2.setNmotores(4);
			avion2.setPotencia(5000);
			avion2.setModelo("Boeing B500");
			avion2.setano(1991);
			avion2.setNmatricula(3437799);
			avion2.setCombustible(28210);
			
			heli2.setNhelices(2);
			heli2.setMedida(10);
			heli2.setCapacidad(12);
			heli2.setano(2005);
			heli2.setNmatricula(125744);
			heli2.setCombustible(4500);
			
			
			vehiculos[0]=heli;
			vehiculos[1]=avion;
			aero.setPosee(vehiculos);
			
			
			vehiculos[0]=heli2;
			vehiculos[1]=avion2;
			aero2.setPosee(vehiculos);
			
			
			aeros[0] =aero;
			aeros[1] = aero2;
					
			comp.setOpera(aeros);
		*/	
			
			db.commit();
			//Cierra la Base de datos
			db.close();
		} catch (MtException mte) {
			// TODO: handle exception
			System.out.println("MtException: " + mte.getMessage());
			
		}
	}
		
	private static void listadoGeneral(){
		try {				
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","aereo"));
			//abro la base de datos
			db.open();
					
			db.startTransaction();
					
			MtObjectIterator<Compania> it = Compania.<Compania>instanceIterator(db);
			while(it.hasNext()){
				Compania comp = it.next();
				System.out.println("Compañia: ");
				System.out.println("Nombre Compañia: " + comp.getNombre());
				System.out.println("Nombre: " + comp.getPresidente());
				System.out.println("Año Fundacion: " + comp.getAnofundacion());
				//al tenerlo como int no me hace falta pero sino --> System.out.println("Año Fundacion: " + comp.getAnofundacion().getTime());
				System.out.println("      ");
				
				Aeropuerto[] aeropuertos = comp.getOpera();	
				for(int i =0;i<aeropuertos.length;i++){
					System.out.println("Aeropuerto: ");
					System.out.println("Nombre Aeropuerto: " + aeropuertos[i].getNombre());
					System.out.println("Pais: " + aeropuertos[i].getPais());
					System.out.println("Nº Pistas: " + aeropuertos[i].getNumeropistas());
					System.out.println("   ");
					
					Vehiculo[] vehiculos = aeropuertos[i].getPosee();
					for(int x = 0; x<vehiculos.length;x++){
						System.out.println("Vehiculo: ");
						System.out.println("Nº Matricula: " + vehiculos[x].getNmatricula());
						System.out.println("Combustible: " + vehiculos[x].getCombustible());
						System.out.println("Año: " + vehiculos[x].getano());
						System.out.println("-----------------------------");
						
						//para determinar si es una clase abstracta
						if(vehiculos[x].isInstanceOf(Avion.getClass(db))){
							Avion a = (Avion)vehiculos[x];
							System.out.println("Nº Motores: " + a.getNmotores());
						}else{
							Helicoptero h = (Helicoptero)vehiculos[x];
							System.out.println("Nº Helices: " + h.getNhelices());
							System.out.println("  ");
						}
					}
				}	
			}
						it.close();
						db.rollback();
						//cierro la base de datos
						db.close();
				} catch (MtException mte) {
					// TODO: handle exception
					System.out.println("MtException: " + mte.getMessage());	
				} 
			}
	
	public static void borrarVehiculo(int matricula){
		try {				
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","aereo"));
			//abro la base de datos
			db.open();
			db.startTransaction();
			
			//Lísta todos los objetos Vehiculo
			System.out.println("\n" + Vehiculo.getInstanceNumber(db) + "vehiculo/s en la bd");
			//crea un iterator
			MtObjectIterator<Vehiculo> iter = Vehiculo.<Vehiculo>instanceIterator(db);
			System.out.println("Borra un vehiculo");
			
			while (iter.hasNext()) {				

	
			Vehiculo vehiculos = iter.next();
		
			/*	Para limpiar la bd de valores null
				 if(vehiculos.isNmatriculaNull()){
						vehiculos.deepRemove();
				}
			*/		
			System.out.println("Matricula " + vehiculos.getNmatricula());
			
			if(matricula == vehiculos.getNmatricula()){
				System.out.println("Matricula borrada del año: " + vehiculos.getano());
				vehiculos.deepRemove();
			}			

		}
			
			iter.close();
			//materializa los cambios y cierra la BD
			db.commit();
			//cierro la base de datos
			db.close();
			} catch (MtException mte) {
			// TODO: handle exception		
			System.out.println("MtException: " + mte.getMessage());	
		}
	}
	
	public static void modificarVehiculo(int matricula, int nmotores){
		try {				
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","aereo"));
			//abro la base de datos
			db.open();
			db.startTransaction();
			
			//Lísta todos los objetos Vehiculo
			System.out.println("\n" + Vehiculo.getInstanceNumber(db) + "vehiculo/s en la bd");
			//crea un iterator
			MtObjectIterator<Vehiculo> iter = Vehiculo.<Vehiculo>instanceIterator(db);
			System.out.println("Modifica un Vehiculo");
			
			while (iter.hasNext()) {				

	
			Vehiculo vehiculos = iter.next();
				
			System.out.println("Matricula " + vehiculos.getNmatricula());
			
			if(matricula == vehiculos.getNmatricula()){
				System.out.println("Matricula modificada del año: " + vehiculos.getano());
						
			Avion avion = (Avion) vehiculos;
			avion.setNmotores(nmotores);
			}
		}	
			iter.close();
			
			
			//materializa los cambios y cierra la BD
			db.rollback();
			//cierro la base de datos
			db.close();
			System.out.println("Modificado con exito");				
			} catch (MtException mte) {
			// TODO: handle exception		
			System.out.println("MtException: " + mte.getMessage());	
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertarTodo();
		//listadoGeneral();
		//borrarVehiculo(12233);
		modificarVehiculo(25433,3);
	}

}
