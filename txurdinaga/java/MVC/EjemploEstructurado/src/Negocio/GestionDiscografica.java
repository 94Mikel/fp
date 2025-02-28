package Negocio;

import Entidades.Disco;
import Entidades.Cancion;
import Persistencia.DiscograficaPersistencia;

public class GestionDiscografica {

	public void anyadirDisco(Disco discoNew){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		discoPersistencia.anyadirDisco(discoNew);
		discoPersistencia.cerrarConexion();
	}
	
	public void deleteDisco(String discoNombre){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		discoPersistencia.deleteDisco(discoNombre);
		discoPersistencia.cerrarConexion();
	}
	
	public void leerDisco(){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		discoPersistencia.leerDatos();
		discoPersistencia.cerrarConexion();
	}
	
	public void leerIdDiscos(){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		discoPersistencia.leerIdDiscos();
		discoPersistencia.cerrarConexion();
	}
	
	public void anyadirCancion(Cancion cancionNew){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		//discoPersistencia.leerIdDiscos();
		discoPersistencia.anyadirCancion(cancionNew);
		discoPersistencia.cerrarConexion();
	}
	
	public void borrarCancion(String cancionNombre){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		//discoPersistencia.leerIdDiscos();
		discoPersistencia.borrarCancion(cancionNombre);
		discoPersistencia.cerrarConexion();
	}
	public void listarCanciones(){
		DiscograficaPersistencia discoPersistencia = new DiscograficaPersistencia();
		discoPersistencia.crearConexion("discografia");
		discoPersistencia.leerCanciones();
		discoPersistencia.cerrarConexion();
	}
}
