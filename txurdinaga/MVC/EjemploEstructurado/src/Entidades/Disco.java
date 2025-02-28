package Entidades;

import Entidades.Cancion;
import java.util.ArrayList;

public class Disco {
	
	private String idDisco = null;
	private String nombre = null;
	private String grupo = null;
	private int anyo;
	private String discografica = null;
	private String genero = null;
	private ArrayList<Cancion> listaCanciones;

	public void anyadirCancion (Cancion cancionNew){
		if(listaCanciones == null){
			listaCanciones = new ArrayList<Cancion>();
		}
		cancionNew.setIdDisco(idDisco);
		listaCanciones.add(cancionNew);
	}
	
	//Metodos GETTERS & SETTERS
	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	
	public String getIdDisco() {
		return idDisco;
	}

	public void setIdDisco(String idDisco) {
		this.idDisco = idDisco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getDiscografica() {
		return discografica;
	}

	public void setDiscografica(String discografica) {
		this.discografica = discografica;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	


}
