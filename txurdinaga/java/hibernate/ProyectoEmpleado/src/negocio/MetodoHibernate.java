package negocio;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import primero.*;


public class MetodoHibernate {
	
	static public void CargarDepartamento(int dept_no){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		
		System.out.println("CONSULTA DE DEPARTAMENTOS");
		System.out.println("*********************");
		Departamentos dep = new Departamentos();
		
		try{
			dep = (Departamentos) session.load(Departamentos.class, dept_no);
			System.out.println("Nº Dept: " + dep.getDeptNo());
			System.out.println("Nombre: " + dep.getDnombre());
			System.out.println("Localidad: " + dep.getLoc());
			
			//sacamos los empleados de ese departamento
			System.out.println("EMPLEADOS DEL DEPARTAMENTO");
			System.out.println("**************************");
			Set<Empleados> listEmple = dep.getEmpleadoses();
			Iterator<Empleados> it = listEmple.iterator();
			
			while(it.hasNext()){
				Empleados emple = it.next();
				System.out.println("Apellidos: " + emple.getApellido());
				System.out.println("Oficio: " + emple.getOficio());
				System.out.println("Salario: " + emple.getSalario());
			}
			
			
		}catch(ObjectNotFoundException o){
			System.out.println("El empleado con indice " + dept_no + "No existe" );
		}
		//cerramos sesion
		session.close();
		//System.exit(0);
	}
	
	static public void cargarEmpleado(int emp_no){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		
		System.out.println("CONSULTA DE EMPLEADOS");
		System.out.println("*********************");
		Empleados emp = new Empleados();
		
		try{
			emp = (Empleados) session.load(Empleados.class, emp_no);
			System.out.println("Apellido: " + emp.getApellido());
			System.out.println("Oficio: " + emp.getOficio());
			System.out.println("Direccion: " + emp.getDir());
			//emp.getDepartamentos();
		}catch(ObjectNotFoundException o){
			System.out.println("El empleado con indice " + emp_no + "No existe" );
		}
		//cerramos sesion
		session.close();
		//System.exit(0);
	}
	
	static public void insertarDepartamento(int depNo, String dnombre, String loc){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		System.out.println("Insertar departamento: " + depNo);
		Departamentos dep = new Departamentos();
		dep.setDeptNo(depNo);
		dep.setDnombre(dnombre);
		dep.setLoc(loc);
		//Guarda la session
		session.save(dep);
		//Para que se hagan persistentes los datos
		tx.commit();
		//Cierra la sesion
		session.close();
	}
	
	public static void insertarEmpleado(int empNo, String apellido, int dir, String oficio, int salario, int comision, int depNo){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
				
		System.out.println("Insertar empleado: " + empNo + " En el departamento: " + depNo);
		Empleados emp = new Empleados();
		emp.setEmpNo(empNo);
		emp.setApellido(apellido);
		emp.setDir(dir);
		emp.setOficio(oficio);
		emp.setSalario(salario);
		emp.setComision(comision);
				
		java.util.Date hoy = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(hoy.getTime());		
		emp.setFechaAlt(fecha);
			
		Departamentos dep = new Departamentos();
		dep.setDeptNo(depNo);
		emp.setDepartamentos(dep);
		
		try{
		//Guarda la session
		session.save(emp);	
		}catch(TransientPropertyValueException e){
		//Cierra la sesion
		System.out.println("Error... El departamento no existe");
		}catch(ConstraintViolationException ee){
			System.out.println("Ese número de empleado Ya existe en la tabla");			
		}
		//Para que se hagan persistentes los datos
		tx.commit();		
		session.close();		
	}
	
	public static void borrarDepartamento(int dept_no){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		try{
		//Cargamos el objeto departamento a borrar	
		Departamentos dep = session.load(Departamentos.class, dept_no); //(Departamentos) puede ir entre = y session
		//Borra la sesion
		session.delete(dep);
		//Para que se hagan persistentes los datos
		tx.commit();
		System.out.println("Departamento: " + dept_no + "Borrado");
		}catch(EntityNotFoundException e){
			System.out.println("No existe ese departamento" + dept_no);
		}catch(ConstraintViolationException c){
			System.out.println("No se puede borrar el departamento por que tiene empleados");
		}catch(Exception e){
			System.out.println("No se puede borrar el departamento");
		}
		session.close();
	}
	
	public static void actualizarSalario(int emp_no, int salarioNew){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		try{
			Empleados emp = session.load(Empleados.class, emp_no);
			emp.setSalario(salarioNew);
			session.update(emp);
			//Para que se hagan persistentes los datos
			tx.commit();
			System.out.println("Modificado salario empleado " + emp_no);
		}catch(EntityNotFoundException e){
			System.out.println("No existe el empleado: " + emp_no);
		}catch(Exception e){
			System.out.println("No se puede actualizar el salario" + emp_no);
		}
	}
	
	public static void listarDepartamentos(){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		
		Query q = session.createQuery("From Departamentos");
		List <Departamentos> lista = q.getResultList();
		
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Departamentos> it = lista.iterator();
		Departamentos dep = new Departamentos();
		
		while(it.hasNext()){
			dep = it.next();
			System.out.println("Código departamento: " + dep.getDeptNo());
			System.out.println("Nombre: " + dep.getDnombre());
			System.out.println("Localizacion: "  + dep.getLoc() + "\n");
			
		}
		session.close();
	} 
		
	public static void listarEmpleadosPorDepartamentos(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		System.out.println("CONSULTA DE DEPARTAMENTOS");
		
		Query q = session.createQuery("From Departamentos");
		List <Departamentos> lista = q.getResultList();
		
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Departamentos> it = lista.iterator();
		Departamentos dep = new Departamentos();
		
		while(it.hasNext()){
			dep = it.next();
			System.out.println("Código departamento: " + dep.getDeptNo());
			System.out.println("Nombre: " + dep.getDnombre());
			System.out.println("Localizacion: "  + dep.getLoc() + "\n");
			
			Query qu = session.createQuery("From Empleados where dept_no = " + dep.getDeptNo() + " ");
			List <Empleados> list = qu.getResultList();
			
			//Ya tenemos la lista.. Creamos un iterator para recorrerla
			Iterator <Empleados> ite = list.iterator();
			Empleados emp = new Empleados();
			
			while(ite.hasNext()){
				emp = ite.next();
				System.out.println("Código Empleado: " + emp.getEmpNo());
				System.out.println("Apellido: " + emp.getApellido());
				System.out.println("Ofico: "  + emp.getOficio());
				System.out.println("Dir: " + emp.getDir());
				System.out.println("Salario: " + emp.getSalario());
				System.out.println("Comision: "  + emp.getComision());
				System.out.println("Departamentos: "  + emp.getDepartamentos() + "\n");
			}			
		}
		//cerramos sesion
		session.close();
	}		
	
	public static void listarEmpleadosPorDepartamentos2(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		System.out.println("CONSULTA DE DEPARTAMENTOS");
		
		Query q = session.createQuery("From Departamentos");
		List <Departamentos> lista = q.getResultList();
		
		//Ya tenemos la lista.. Creamos un iterator para recorrerla
		Iterator <Departamentos> it = lista.iterator();
		Departamentos dep = new Departamentos();
					
		while(it.hasNext()){
			dep = it.next();
			CargarDepartamento(dep.getDeptNo());
		}			
		//cerramos sesion
		session.close();
	}	
	
	public static void consultasConWhere(){
		//obtenemos la session actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Select que devuelve un unico registro				//as dep --> para darle un alias
		Query q = session.createQuery("from Departamentos as dep where dep.loc='LEIOA'");
		Departamentos dep = (Departamentos) q.uniqueResult();
		System.out.println(dep.getDeptNo() + " - " + dep.getDnombre());
		
		System.out.println("----------------------------------------");
		
		//Select devuelve nas de un registro							//Para que devuelva los que tienen A/a --> %A%
		q = session.createQuery("from Departamentos as dep where dep.loc like '%A%'");
		List<Departamentos> lista = lista = q.getResultList();
		
		//Pasamos la lista a Iterator para recorrerla
		Iterator <Departamentos> iter = lista.iterator();
		while(iter.hasNext()){
			dep = iter.next();
			System.out.println(dep.getDeptNo() + " - " + dep.getDnombre());
		}
		
		//Select con mas una join
		q = session.createQuery("from Empleados as empl where empl.departamentos.deptNo=2");
		List<Empleados> lista2 = q.getResultList();
		
		//Pasamos la lista a Iterator para recorrerla
		Iterator<Empleados> iter2 = lista2.iterator();
		Empleados emple = new Empleados();
		while(iter2.hasNext()){
			emple = iter2.next();
			System.out.println(emple.getApellido() + " - " + emple.getComision());
		}
		
		System.out.println("------------------------------------------");
		
		//Select con paramentros
		q = session.createQuery("from Empleados as empl where empl.departamentos.deptNo= :numdept");
		q.setParameter("numdept", 1);
		
		lista2 = q.getResultList();
		iter2 = lista2.iterator();
		while(iter2.hasNext()){
			emple = iter2.next();
			System.out.println(emple.getApellido() + " - " + emple.getComision());
		}
		
		session.close();
	}
	
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		//cargarEmpleado(1); 
		//CargarDepartamento(1);	//CargarDepartamento(18); Este daria un fallo pues el numero indicado no existe
		//insertarDepartamento(3,"MULTIPLATAFORMA","RETUERTO");
		//insertarEmpleado(2,"Izarra",50444,"Alumno",1000,500,2); //El ultimo numero tiene que ser un 2 para que no de error
		//insertarEmpleado(4,"Malmasin",85201,"Director",3500,1500,3); //El ultimo numero tiene que ser un 2 para que no de error
		//borrarDepartamento(4);
		//actualizarSalario(1,9999);
		//listarDepartamentos();
		//listarEmpleadosPorDepartamentos();
		//listarEmpleadosPorDepartamentos2();
		consultasConWhere();
	}

}
