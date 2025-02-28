package Presentacion;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Entidades.Cancion;
import Entidades.Disco;
import org.apache.commons.dbcp.BasicDataSource;

import Negocio.GestionDiscografica;

public class Presentacion {

	static Scanner sc = new Scanner(System.in);
	static GestionDiscografica gestor = new GestionDiscografica();
	Connection con = null;
		
	public static void menuInicial(){
		System.out.println("XXXXXXX Menu Inicial XXXXXXXX");
		System.out.println("1.Gestion de discos");
		System.out.println("2.Gestion de canciones");
		System.out.println("3.Consulta disco");
		System.out.println("");
		System.out.println("------------------------------");
		System.out.println("Selecciona una opcion: ");
		int opcion = Integer.parseInt(sc.nextLine());
		
		switch(opcion){
		case 1: menuDisco();
		break;
		}
	}
	
	public static void menuDisco(){
		System.out.println("XXXXXXX Menu Inicial XXXXXXXX");
		System.out.println("1.Insertar discos");
		System.out.println("2.Borrar disco");
		System.out.println("");
		System.out.println("0.Volver");
		System.out.println("------------------------------");
		System.out.println("Selecciona una opcion: ");
		int opcion = Integer.parseInt(sc.nextLine());
		
		switch(opcion){
		case 1: anyadirDisco();
		break;
		case 0: menuInicial();
		break;
		}
	}
	
	public static void menuCanciones(){
		System.out.println("XXXX MENU CANCION XXXX");
		System.out.println("1.Insertar canción");
		System.out.println("2.Borrar canción");
		System.out.println("3.Listar canciones");
		System.out.println("");
		System.out.println("0.Volver");
		System.out.println("---------------------");
		System.out.println("Selecciona una opción:");
		int opcion = Integer.parseInt(sc.nextLine());
		switch(opcion){
		case 3: listarCanciones();
			break;
		case 2: borrarCancion();
			break;
		case 1: anyadirCancion();
			break;
		case 0: menuInicial();
			break;
		}
	}
	
	public static void anyadirDisco(){
		Disco discoNew = new Disco();
		System.out.println("Nombre: ");
		discoNew.setNombre(sc.nextLine());		
		System.out.println("Grupo: ");
		discoNew.setGrupo(sc.nextLine());	
		System.out.println("Anyo: ");
		discoNew.setAnyo(Integer.parseInt(sc.nextLine()));	
		System.out.println("Discografica: ");
		discoNew.setDiscografica(sc.nextLine());	
		System.out.println("Genero: ");
		discoNew.setGenero(sc.nextLine());	
		discoNew.setIdDisco(discoNew.getGrupo() + "-" + discoNew.getNombre());
		gestor.anyadirDisco(discoNew);
	}
	
	public static void borrarDisco(){
		System.out.println("Introduce el nombre del disco a borrar");
		String discoNombre = sc.nextLine();
		gestor.deleteDisco(discoNombre);
		
	}
	public static void anyadirCancion(){
		Cancion cancionNew = new Cancion();
		gestor.leerIdDiscos();
		System.out.println("Nombre");
		cancionNew.setNombre(sc.nextLine());
		System.out.println("Duración");
		cancionNew.setDuracion(Integer.parseInt(sc.nextLine()));
		System.out.println("IdDisco");
		cancionNew.setIdDisco(sc.nextLine());
		gestor.anyadirCancion(cancionNew);
	}
	public static void borrarCancion(){
		System.out.println("Introduce el nombre de la canción a borrar");
		String cancionNombre = sc.nextLine();
		gestor.borrarCancion(cancionNombre);
		
	}
	public static void listarCanciones(){
		//System.out.println("\nLista de canciones:\n");
		gestor.listarCanciones();
	}
	public static void leerDiscos(){
		System.out.println("\n");
		gestor.leerDisco();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuInicial();
	}

}
