package primero;
// Generated 22-dic-2016 14:53:05 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;

/**
 * Ventas generated by hbm2java
 */
public class Ventas implements java.io.Serializable {

	private int idVenta;
	private Clientes clientes;
	private Productos productos;
	private Date fechaVenta;
	private Integer cantidad;

	public Ventas() {
	}

	public Ventas(int idVenta) {
		this.idVenta = idVenta;
	}

	public Ventas(int idVenta, Clientes clientes, Productos productos, Date fechaVenta, Integer cantidad) {
		this.idVenta = idVenta;
		this.clientes = clientes;
		this.productos = productos;
		this.fechaVenta = fechaVenta;
		this.cantidad = cantidad;
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Productos getProductos() {
		return this.productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
