package Entidades;

public class Cancion {
	//Atributos
	private int idCancion;
	private int duracion;
	private String nombre;
	private String idDisco;
	
	
	// Metodos - GETTERS & SETTERS
	public int getIdCancion() {
		return idCancion;
	}
	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdDisco() {
		return idDisco;
	}
	public void setIdDisco(String idDisco) {
		this.idDisco = idDisco;
	}
	
}
