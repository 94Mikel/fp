package primero;
// Generated 22-dic-2016 14:53:05 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private int id;
	private String nombre;
	private String direccion;
	private String poblacion;
	private int telefono;
	private String nif;
	private Set<Ventas> ventases = new HashSet<Ventas>(0);

	public Clientes() {
	}

	public Clientes(int id, String nombre, String direccion, String poblacion, int telefono, String nif) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.nif = nif;
	}

	public Clientes(int id, String nombre, String direccion, String poblacion, int telefono, String nif,
			Set<Ventas> ventases) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.nif = nif;
		this.ventases = ventases;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Set<Ventas> getVentases() {
		return this.ventases;
	}

	public void setVentases(Set<Ventas> ventases) {
		this.ventases = ventases;
	}

}
