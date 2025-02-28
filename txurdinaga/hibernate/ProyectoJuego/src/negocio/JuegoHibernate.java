package negocio;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import primero.Clientes;
import primero.HibernateUtil;
import primero.Productos;
import primero.Ventas;

public class JuegoHibernate {
	
	static public void cargarVenta(int idCliente){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		
		System.out.println("CONSULTA DE VENTAS");
		System.out.println("*********************");
		//Ventas ven = new Ventas();
		Clientes cli = new Clientes();
		Productos pro = new Productos();
		int i = 0;
		float total = 0;		
		
		try{
			cli = (Clientes) session.load(Clientes.class, idCliente);
			System.out.println("Nombre: " + cli.getNombre());	
			
			Set<Ventas> listVentas = cli.getVentases();
			Iterator<Ventas> it = listVentas.iterator();
			
			while(it.hasNext()){
				Ventas ven = it.next();
				pro = (Productos)ven.getProductos();
				total = ven.getCantidad() * pro.getPvp();
				i++;		
				System.out.println("Producto: " + pro.getDescripcion());
				System.out.println("Cantidad: " + ven.getCantidad());
				System.out.println("Importe: " + String.format("5,7", ven.getCantidad()));
			}
			System.out.println("\tNumero total de ventas: " + i);
			System.out.println("\tImporte total: " + String.format("5,7", total));
			System.out.println("********************************************");
			
		}catch(ObjectNotFoundException o){
			System.out.println("El empleado con indice " + idCliente + "No existe" );
		}
		//cerramos sesion
		session.close();
		//System.exit(0);
	}
	
	public static void insertarCliente(int idCliente, String nombre, String dir, String pob, int telefono, String NIF, int id){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		System.out.println("Insertar cliente: " + idCliente);
		Clientes cli = new Clientes();
		cli.setId(idCliente);
		cli.setNombre(nombre);
		cli.setDireccion(dir);
		cli.setPoblacion(pob);
		cli.setTelefono(telefono);
		cli.setNif(NIF);

		//Guarda la session
		session.save(cli);
		//Para que se hagan persistentes los datos
		tx.commit();
		//Cierra la sesion
		session.close();
	}
	
	public static void insertarProductos(int idNum, String descripcion, int StockActual, int StockMinimo, int pvp){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		System.out.println("Insertar Productos: " + idNum);
		Productos pro = new Productos();
		pro.setIdNumerico(idNum);
		pro.setDescripcion(descripcion);
		pro.setStockActual(StockActual);
		pro.setStockMinimo(StockMinimo);
		pro.setPvp(pvp);
		
		//Guarda la session
		session.save(pro);
		//Para que se hagan persistentes los datos
		tx.commit();
		//Cierra la sesion
		session.close();
	}
		
	public static void insertarVentas(int idVentas, int idCli, int idPro, int cantidad){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		System.out.println("Insertar ventas: " + idVentas + " En el producto" + idPro);
		
		Ventas ven = new Ventas();
		ven.setIdVenta(idVentas);
		ven.setCantidad(cantidad);
		
		java.util.Date hoy = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(hoy.getTime());		
		ven.setFechaVenta(fecha);
		
		Clientes cli = new Clientes();
		cli.setId(idCli);
		ven.setClientes(cli);
		
		Productos pro = new Productos();
		pro.setIdNumerico(idPro);
		ven.setProductos(pro);
		
		try{
		  session.save(ven);
		  tx.commit();
		}catch (TransientPropertyValueException e) {
		 System.out.println("Error el cliente o el producto no existe");
		}catch(Exception e){
		 System.out.println("Error... el cliente o el producto no existe");
		}
		session.close();
	}
	
	public static void borrarVentas(int idVentas){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		try{
		Ventas ven = session.load(Ventas.class, idVentas);
		//Borra la sesion
		session.delete(ven);
		//Para que se hagan persistentes los datos
		tx.commit();
		System.out.println("Ventas: " + idVentas + "Borrado");
		}catch(EntityNotFoundException e){
			System.out.println("No existe esa venta" + idVentas);
		}catch(ConstraintViolationException c){
			System.out.println("No se puede borrar esa venta por que tiene nada");
		}catch(Exception e){
			System.out.println("No se puede borrar la venta");
		}
		session.close();
	}
	
	public static void ActualizarStockActual(int idProducto /*, int StockActualNew*/){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		try{
			Productos pro = session.load(Productos.class, idProducto);
			//pro.setStockActual(StockActualNew);
			pro.setStockActual((int) (pro.getStockActual()*pro.getStockActual()*0.5));
			session.update(pro);
			//Para que se hagan persistentes los datos
			tx.commit();
			System.out.println("Modificado stock producto " + idProducto);
		}catch(EntityNotFoundException e){
			System.out.println("No existe el empleado: " + idProducto);
		}catch(Exception e){
			System.out.println("No se puede actualizar el salario" + idProducto);
		}
	}
	
	public static void listarClientes(){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		Query q = session.createQuery("From Clientes");
		
		List<Clientes> lista = q.getResultList();
		
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Clientes> it = lista.iterator();
		Clientes cli = new Clientes();
		
		while(it.hasNext()){
			cli = it.next();
			System.out.println("Id: " + cli.getId());
			System.out.println("Nombre: " + cli.getNombre());
			System.out.println("Direccion: " + cli.getDireccion());
			System.out.println("Poblacion: " + cli.getPoblacion());
			System.out.println("Telefono: " + cli.getTelefono());
			System.out.println("NIF: "  + cli.getNif() + "\n");
		}
		session.close();	
	}
	
/*	static public void cargarProducto(int stockactual){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
			
		Productos pro = new Productos();
		
		try{
			pro = (Productos) session.load(Productos.class, stockactual);
			System.out.println("Id numerico: " + pro.getIdNumerico());
			System.out.println("Descripcion: " + pro.getDescripcion());
			System.out.println("Stock Actual: " + pro.getStockActual());
			System.out.println("Stock minimo: " + pro.getStockMinimo());
			System.out.println("PVP: " + pro.getPvp());			
			
		}catch(ObjectNotFoundException o){
			System.out.println("El/los producto/s con Stock mayores a 100 son: " + stockactual);
		}
		//cerramos sesion
		session.close();
	}*/
	
	public static void listarClientesPorProductos(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
				
		Query q = session.createQuery("from Ventas as vent where vent.productos.stockActual > 100");
		List <Ventas> lista = q.getResultList();
				
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Ventas> it = lista.iterator();
		Ventas ven = new Ventas();
							
		while(it.hasNext()){
			ven = it.next();
			System.out.println(ven.getProductos().getDescripcion() + " - " + ven.getProductos().getStockActual());
		}			
		//cerramos sesion
		session.close();
	}		
	
	public static void ventasMayoresA100(int idcliente){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
				
		Query q = session.createQuery("from Ventas as vent where vent.productos.stockActual > 100 and vent.clientes.id = :idcliente");
		q.setParameter("idcliente", idcliente);
		List <Ventas> lista = q.getResultList();
				
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Ventas> it = lista.iterator();
		Ventas ven = new Ventas();
							
		while(it.hasNext()){
			ven = it.next();
			System.out.println(ven.getProductos().getDescripcion() + " - " + ven.getProductos().getStockActual());
		}			
		//cerramos sesion
		session.close();
	}	
	
	public static void listado1(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
				
		Query q = session.createQuery("from Clientes");
		
		List <Clientes> lista = q.getResultList();
				
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Clientes> it = lista.iterator();
		Clientes cli = new Clientes();
							
		while(it.hasNext()){
			cli = it.next();
			System.out.println(cli.getId() + " - " + cli.getNombre());
			ventasMayoresA100(cli.getId());
		}			
		//cerramos sesion
		session.close();
	}
	
	public static void listado1bis(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();	
		
		Query q = session.createQuery("from Clientes");
		List<Clientes> lista = q.getResultList();
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator<Clientes> iter = lista.iterator();
		Clientes cliente = new Clientes();
		while(iter.hasNext()){
			cliente=iter.next();
			System.out.println(cliente.getId()+" - "+cliente.getNombre());
			
			q = session.createQuery("from Ventas as vent where vent.productos.stockActual > 100 and vent.clientes.id= :idcliente");
			q.setParameter("idcliente", cliente.getId());
			List<Ventas> lista2 = q.getResultList();
			Iterator<Ventas> iter2 = lista2.iterator();
			Ventas venta = new Ventas();
			while(iter2.hasNext()){
				venta=iter2.next();
				System.out.println("     "+venta.getProductos().getDescripcion()+ " - "+ venta.getProductos().getStockActual());			
			}			
			
		}
		session.close();
	}
	
	public static void listadoDeVentasdeClientesPorPoblacion(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();			
		
		Query q = session.createQuery("from Clientes order by poblacion ");
		List<Clientes> lista = q.getResultList();
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator<Clientes> iter = lista.iterator();
		Clientes cliente = new Clientes();
		String comodin = "xxx";
		
		while(iter.hasNext()){
			cliente=iter.next();
			if(!comodin.equals(cliente.getPoblacion())){
				System.out.println("Clientes de: " + cliente.getPoblacion());
				comodin=cliente.getPoblacion();
			}
			System.out.println("     " + "Id: " +  cliente.getId() + " " + " Nombre: " + cliente.getNombre() + " " + " DNI: " + cliente.getNif());
			mostrarVentasClientes(cliente.getId());
		}
		session.close();
	}	
	
	public static void mostrarVentasClientes(int idCliente){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();			
				
		Query q = session.createQuery("from Ventas v where v.clientes.id = :idCliente");
		q.setParameter("idCliente", idCliente);
		List<Ventas> lista = q.getResultList();
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator<Ventas> iter = lista.iterator();
		Ventas venta = new Ventas();
		
		while(iter.hasNext()){
			venta = iter.next();
			System.out.println("          " + "Id Venta: " + venta.getIdVenta() + "  " + " Fecha Venta: " + venta.getFechaVenta() + "  " + " Nombre Producto: " + venta.getProductos().getDescripcion()+ "  " + " Cantidad: " + venta.getCantidad() + "  " + " Stock Actual: " + venta.getProductos().getStockActual());
		}
		session.close();
	}
	
	public static void listadoDeVentasdeClientesPorPoblacion2(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();			
		
		Query q = session.createQuery("from Clientes order by poblacion ");
		List<Clientes> lista = q.getResultList();
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator<Clientes> iter = lista.iterator();
		Clientes cliente = new Clientes();
		String comodin = "xxx";
		
		while(iter.hasNext()){
			cliente=iter.next();
			if(!comodin.equals(cliente.getPoblacion())){
				System.out.println("Clientes de: " + cliente.getPoblacion());
				comodin=cliente.getPoblacion();
			}
			Set<Ventas> listavent = cliente.getVentases();
			if(listavent.size()!=0){
				System.out.println("      " + cliente.getId() + " " + cliente.getNombre() + " " + cliente.getNif());
			}
			Iterator<Ventas> iterventa = listavent.iterator();
			Ventas venta = new Ventas();
			while(iterventa.hasNext()){
				venta = iterventa.next();
				System.out.println("     " + "Id: " +  cliente.getId() + " " + " Nombre: " + cliente.getNombre() + " " + " DNI: " + cliente.getNif());
			}
		}
		session.close();
	}
		
	public static void listadoAcumuladoVentasPorProducto(){	
		
		SessionFactory session= HibernateUtil.getSessionFactory();
		Session sesion=session.openSession();
			
		Query q = sesion.createQuery("from Ventas as v order by v.productos.id asc");
		
		List<Ventas> ventas = q.getResultList();
		Iterator<Ventas> it1=  ventas.iterator();
		
		String productoNuevo = " ";
		int unidades = 0;
		
		int id = 0;
		String descripcion = "";
		float pvp = 0;
		boolean acabado = false;
		float total = 0;
		int tProductos = 0;
		int tUnidades = 0;
		float tImporte = 0;
				
		while(it1.hasNext()){
		
		 Ventas v = new Ventas();
		 Productos p = new Productos();
			
		 v = it1.next();
		 p = v.getProductos();	
		 String producto = p.getDescripcion();
			
		 tUnidades += v.getCantidad();
			
		 if(productoNuevo.equals(" ")){
		 	productoNuevo = producto;
		 	tProductos++;
		}
			
		if(producto.equals(productoNuevo)){
			unidades += v.getCantidad();
			id = p.getIdNumerico();
			descripcion = p.getDescripcion();
			pvp =  p.getPvp();
			tImporte += total;
			
		if(!it1.hasNext()){
			total = unidades*p.getPvp();
			tImporte += total;
			id = p.getIdNumerico();
			descripcion = p.getDescripcion();
			pvp =  p.getPvp();
			//acabado = true;
			System.out.println("IdProducto: " + id + " Nombre Producto: " + descripcion + " PVP Unidad: " + pvp + " Unidades vendidas: " + unidades + " Importe Venta: " + total );	
			}
								
			}
			else if(!producto.equals(productoNuevo)){
				tProductos++;
				
		if(!acabado){
			tImporte += unidades*pvp;
			System.out.println("IdProducto: " + id + " - " + " Nombre Producto: " + descripcion + " - " + " PVP Unidad: " + pvp + " - " + " Unidades vendidas: " + unidades  + " - " + " Importe Venta: " + unidades*pvp );				
			}
				
			unidades = v.getCantidad();
				
		if(!it1.hasNext()){
			total = unidades*p.getPvp();
			tImporte += total; 
			id = p.getIdNumerico();
			descripcion = p.getDescripcion();
			pvp =  p.getPvp();
			System.out.println("IdProducto: " + id + " - " + " Nombre Producto: " + descripcion + " - " + " PVP Unidad: " + pvp + " - " + " Unidades vendidas: " + unidades + " - " + " Importe Venta: " + total );	
		}				
	}
			productoNuevo = producto;
		}
				
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("Nº TOTAL PRODUCTOS: " + tProductos  + " - " + " TOTAL UNIDADES: " + " - " + tUnidades + " - " + " TOTAL VENTAS: " + tImporte);
		sesion.close();
		session.close();
	}	
	
	/* COMO EN EL DE ARRIBA PERO DE DIFERENTE MANERA*/
	public static void ProductosTotal(){
	      SessionFactory sesion = HibernateUtil.getSessionFactory();
	      Session session = sesion.openSession();
	      Query q= session.createQuery("from Productos order by idnumero");
	      List<Productos> lista = q.getResultList();
	      Iterator<Productos> iter = lista.iterator();
	      Productos pro = new Productos();
	      int numerototaldeproductos=0;
	      int totalVentas=0;
	      int totalDeUnidades=0;
	      while(iter.hasNext()){
	        pro=iter.next();
	        int cantidadUnidades=unidadesVendidas(pro.getIdNumerico());
	        System.out.println(pro.getIdNumerico()+" - "+pro.getDescripcion()+" - "+pro.getPvp()+" - "+cantidadUnidades+" - "+pro.getPvp()*cantidadUnidades);
	        numerototaldeproductos++;
	        totalDeUnidades=totalDeUnidades+cantidadUnidades;
	        totalVentas=totalVentas+pro.getPvp()*cantidadUnidades;
	      }
	      System.out.println("Total Productos: "+numerototaldeproductos+" TotalUnidades: "+totalDeUnidades+" Total Ventas: "+totalVentas);
	      
	    }
	    
	public static int unidadesVendidas(int idnumero){
	      
	      SessionFactory sesion = HibernateUtil.getSessionFactory();
	      Session session = sesion.openSession();
	      Query q= session.createQuery("from Ventas where productos='"+idnumero+"'");
	      List<Ventas> lista = q.getResultList();
	      Iterator<Ventas> iter = lista.iterator();
	      Ventas ven = new Ventas();
	      int totalCantidad=0;
	      while(iter.hasNext()){
	        ven=iter.next();
	        totalCantidad=totalCantidad+ven.getCantidad();
	      }
	      return totalCantidad;
	    }

	
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		//cargarCliente(1);
		//cargarProducto(1);
		cargarVenta(1);
		//insertarCliente(2,"Garikoitz","Sta Isabel","Arrigorriaga",654789321,"1235897-J");
		//insertarCliente(4,"Gary","Los burros","Basauri",696369632,"5879241-J", 0);
		//insertarProductos(3,"Producto3",6,1,10);
		//insertarVentas(3,2,2,100); //2,2 son sobre el cliente y producto
		//borrarVentas(3);
		//ActualizarStockActual(1);
		//listarClientes();
		//listarClientesPorProductos();
		//ventasMayoresA100(1);
		//listado1();
		//listado1bis();
		//listadoDeVentasdeClientesPorPoblacion();
		//listadoDeVentasdeClientesPorPoblacion2();
		listadoAcumuladoVentasPorProducto();
		//ProductosTotal();
	}

}
