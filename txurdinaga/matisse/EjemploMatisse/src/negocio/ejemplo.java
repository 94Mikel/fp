package negocio;

import java.sql.SQLException;

import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;

import biblioteca.Articulo;
import biblioteca.Autor;
import biblioteca.Libro;
import biblioteca.Obra;

public class ejemplo {

	public static void insertarLibro(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			
			db.startTransaction();
			
			Libro libro = new Libro(db);
			libro.setTitulo("La Torre oscura");
			libro.setPaginas(100);
			libro.setEditorial("Elhuyar");
			
			db.commit();			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		}
	}
	
	public static void insertarArticulo(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			
			db.startTransaction();
			
			Articulo articulo = new Articulo(db);
			articulo.setTitulo("El premio 'The Best'");
			articulo.setPaginas(100);
			articulo.setRevista("Don balon");
			
			db.commit();			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		}
	}
	
	public static void insertarAutor(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			
			db.startTransaction();
			
			Autor autor = new Autor(db);
			autor.setNombre("Tomas");
			autor.setApellidos("Roncero");
			autor.setEdad("32");
			
			db.commit();			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		}
	}
	
	public static void insertarObra(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			
			db.startTransaction();
			
			Obra obra = new Obra(db);
			obra.setTitulo("El blues del futbolista");
			obra.setPaginas(200);
			
			db.commit();			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		}
	}
	
	public static void insertarObraAutor(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			
			db.startTransaction();
			
		/*	Autor autor = new Autor(db);
			Obra obra = new Obra(db);
			
			Libro libro = new Libro(db);
			Libro libro2 = new Libro(db);
			
			autor.setNombre("Gari");
			autor.setApellidos("D. Sanchez");
			autor.setEdad("22");
			
			libro.setTitulo("El bicho");
			libro.setPaginas(70);
			libro.setEditorial("Salvat");
			
			libro2.setTitulo("El D10S");
			libro2.setPaginas(90);
			libro2.setEditorial("Salvat");
			
			//obra.setTitulo("El blues del futbolista");
			//obra.setPaginas(200);
			
			Obra o1[] = new Obra[2];
			o1[0]=libro;
			o1[1]=libro2;
			
			autor.setEscribe(o1); */
			
			Libro libro = new Libro(db);
			libro.setTitulo("El Visionario");
			libro.setPaginas(70);
			libro.setEditorial("Salvat");
			
			Articulo articulo = new Articulo(db);
			articulo.setTitulo("La clonacion de ranas");
			articulo.setPaginas(100);
			articulo.setRevista("EcoFull");
			
			Autor autor = new Autor(db);
			autor.setNombre("Garikoitz");
			autor.setApellidos("Diz Sanchez");
			autor.setEdad("22");
			
			Obra o1[] = new Obra[2];
			o1[0]=libro;
			o1[1]=articulo;
			autor.setEscribe(o1);
			
			db.commit();			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		}
		
	}

	public static void leerAutor(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			db.startTransaction();
			
			MtObjectIterator<Autor> it = Autor.<Autor>instanceIterator(db);
			//Creo una nueva clase en la BD
			Autor autor = new Autor(db);
			
			while(it.hasNext()){
				autor= it.next();
				System.out.println("----------------------------------");
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Apellidos: " + autor.getApellidos());
				System.out.println("Edad: " + autor.getEdad());
				//System.out.println("Metodo: " + autor.dameNombreyApellidos());
			}
			
			it.close();
			db.rollback(); //Con rollback los datos no se hacen persistentes			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		} /*catch(SQLException e){
			System.out.println(e.getMessage());
		}*/
	}
	
	public static void listadoAutorObras(){
		try{
			MtDatabase db = new MtDatabase("207_HZ321633", "Ejemplo", new MtPackageObjectFactory("","biblioteca")); // 207_HZ321633 es el nombre del equipo de Matisse
			//Abre la Base de datos
			db.open();
			db.startTransaction();
					
			
			MtObjectIterator<Autor> it = Autor.<Autor>instanceIterator(db);
			//Creo una nueva clase en la BD
			Autor autor = new Autor(db);
			
			while(it.hasNext()){
				autor= it.next();
				System.out.println("----------------------------------");
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Apellidos: " + autor.getApellidos());
				System.out.println("Edad: " + autor.getEdad());
				System.out.println("Metodo: " + autor.dameNombreyApellidos());
				
				Libro libro = new Libro(db);
			
				Obra[] obra = autor.getEscribe();
				for(int i=0; i<obra.length;i++){
					
					if(obra[i].isInstanceOf(libro.getMtClass())){
						System.out.println("Libro");
					}else{
						System.out.println("Revista");
					}
										
					System.out.println("\tTitulo: " + obra[i].getTitulo());
					System.out.println("\tPaginas: " + obra[i].getPaginas());
				}
			}
			
			it.close();
			
			db.rollback(); //Con rollback los datos no se hacen persistentes			
			//Cierra la Base de datos			
			db.close();			
			
		}catch(MtException mte){
			System.out.println("MTException: " + mte.getMessage());
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertarLibro();
		//insertarArticulo();
		//insertarAutor();
		//insertarObra();
		//insertarObraAutor();
		//leerAutor();
		listadoAutorObras();
	}

}
