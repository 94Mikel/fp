package primero;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	
	//Conector crea la conexion
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory(){
		try{
			//Crea el sessionFactory a partir del hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory(
			new StandardServiceRegistryBuilder().configure().build()
			);
		}catch(Throwable ex){
			System.err.println("Error al crear la sessionFactory inicial");
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
