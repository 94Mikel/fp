package negocio;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.hibernate.ObjectNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.query.Query;

import primero.Equipos;
import primero.Estadisticas;
import primero.HibernateUtil;
import primero.Jugadores;


public class BaloncestoHibernate {

	//Ejercicio 5
	public static void estadisticasJugador(int codJug){
		//Obtenemos la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos la sesion
		Session session = sesion.openSession();
		
		try{
			Jugadores jug = new Jugadores();
			
			jug = (Jugadores) session.load(Jugadores.class, codJug);
			Equipos equipos = jug.getEquipos();
			
			System.out.println("DATOS DEL JUGADOR: " + jug.getCodigo());
			System.out.println("Nombre: " + jug.getNombre());
			System.out.println("Equipo: " + equipos.getNombre());
			System.out.println("Temporada  --   Ptos --    Asis  --   Tap  --   Reb");
			System.out.println("===================================================");
		
			Set<Estadisticas> listEst = jug.getEstadisticases();
			Iterator<Estadisticas> it = listEst.iterator();
			
			int numregistros=0;
			int i=0;
			
			while(it.hasNext()){
				Estadisticas est=it.next();
				
				System.out.println(est.getTemporada() + " -- " + est.getPuntosPorPartido() + " --  " + est.getAsistenciasPorPartido() + " --  " + est.getTaponesPorPartido() + " -- " + est.getRebotesPorPartido());
				i++;
				numregistros++;
				
				if(!it.hasNext()){
					System.out.println("===================================================");
				}
			}
			System.out.println("Numero de registros: " + numregistros);
			System.out.println("===================================================");
			session.close();
			
		}catch(ObjectNotFoundException o){
			System.out.println("El jugador no existe");
			System.out.println("===================================================");
		}
	}
	
	//Ejercicio 6
	public static void ListadoJugadoresPorPartido(){
		//Obtenemos la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos la sesion
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Equipos");
		
		List<Equipos> lista = q.getResultList();
		Iterator<Equipos> it = lista.iterator();
		
		System.out.println("Numero de equipos: " + lista.size());
		System.out.println("==================================");
		
		while(it.hasNext()){
			Equipos eq = it.next();
			System.out.println("Equipo: " + eq.getNombre());
			
			Set<Jugadores> jug = eq.getJugadoreses();
			Iterator<Jugadores> itjug = jug.iterator();
			
			while(itjug.hasNext()){

			Jugadores juga = itjug.next();
				
			System.out.print(juga.getCodigo() + ", " + juga.getNombre() + " :   ");
				
			Set<Estadisticas> est = juga.getEstadisticases();
			Iterator<Estadisticas> itest = est.iterator();
				
			int contador = 0;
			float puntostotal = 0;
			while(itest.hasNext()){
					
			Estadisticas estad = itest.next();
				puntostotal = puntostotal + estad.getPuntosPorPartido();
				contador++;
				puntostotal = puntostotal/contador;
			}
					
			System.out.print(puntostotal + "\n");
			puntostotal = 0;
			contador = 0;		
		}	
	}	
}	
	
	//Ejercicio 7
	public static void InsertarEstadisticas(String temp, int codjug, float ptspartido, float asistpartido, float tappartido, float rebpartido){
		//obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//abrimos la sesion
		Session session = sesion.openSession();
		//Crear una transaccion para esa sesion
		Transaction tx = session.beginTransaction();
		
		//System.out.println("Insertar datos: " + codjug);
		Estadisticas est = new Estadisticas();
		Jugadores jug = new Jugadores();
				
		est.setTemporada(temp);
		jug.setCodigo(codjug);
		est.setPuntosPorPartido(ptspartido);
		est.setAsistenciasPorPartido(asistpartido);
		est.setTaponesPorPartido(tappartido);
		est.setRebotesPorPartido(rebpartido);
		
		try{
			session.save(est);
			tx.commit();
		}catch (TransientPropertyValueException e) {
			session.close();
			System.out.println("Error el cliente no existe");
		}
	}
	
	public static void main(String[] args) {
	java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
	//estadisticasJugador(227);
	//ListadoJugadoresPorPartido();
	//InsertarEstadisticas("05/06",123,7,5,0,0);
	//InsertarEstadisticas("06/07",123,10,0,3,0);
	}
}
