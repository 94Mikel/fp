package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import Entidades.Cancion;
import Entidades.Disco;

public class DiscograficaPersistencia {

Connection con = null;
	
	public void crearConexion(String nombreBD){

		BasicDataSource bdsource = new BasicDataSource();
		bdsource.setUrl("jdbc:mysql://localhost:3306/" + nombreBD);
		bdsource.setUsername("user_discografica");
		bdsource.setPassword("vinagres");
		try{
			if(con !=null){
				System.out.println("No se puede crear la conexion");
			}else{
				con = bdsource.getConnection();
				System.out.println("Conexion creada con la BD " + nombreBD);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion(){
		try{
			con.close();
			System.out.println("Conexion cerrada. OK!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void anyadirDisco(Disco disconew){
		try{
			PreparedStatement sta = con.prepareStatement("INSERT INTO discos VALUES (?,?,?,?,?,?)");
			sta.setString(1, disconew.getIdDisco());
			sta.setString(2, disconew.getNombre());
			sta.setString(3, disconew.getGrupo());
			sta.setInt(4, disconew.getAnyo());
			sta.setString(5, disconew.getDiscografica());
			sta.setString(6, disconew.getGenero());
			sta.executeUpdate();
			sta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void leerDatos(){
		try{
			//Creamos una statement
			Statement sta = con.createStatement();
			//EJECUTAMOS LA SENTENCIA Y LA METEMOS EN UN OBJETO RESULSET
			ResultSet rs = sta.executeQuery("SELECT * FROM discos");
			//Recorremos el ResultSet
			while(rs.next()){
				System.out.println("Nº de disco: " + rs.getString(1) 
				+ "\nNombre: " + rs.getString("Nombre") + "\nGrupo: " + rs.getString(3) 
				+ "\nAño: " + rs.getShort("Anyo") + "\nDiscografia: " + rs.getString("Discografica") 
				+ "\nGenero: " + rs.getString("Genero") + "\n-----------");
			}
			rs.close();
			sta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteDisco(String discoNombre){
		try{
			//Statement sta = con.createStatement();
			String sql = "DELETE FROM discos where Nombre=?";
			PreparedStatement psta = con.prepareStatement(sql);
			psta.setString(1, discoNombre);
			psta.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void leerIdDiscos(){
		try{
			//Creamos una statement
			Statement sta = con.createStatement();
			//EJECUTAMOS LA SENTENCIA Y LA METEMOS EN UN OBJETO RESULSET
			ResultSet rs = sta.executeQuery("SELECT IdDisco FROM discos");
			//Recorremos el ResultSet
			while(rs.next()){
				System.out.println("Nº de disco: " + rs.getString(1)  + "\n-----------");
			}
			rs.close();
			sta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void anyadirCancion(Cancion cancionNew){
		try{
			PreparedStatement sta = con.prepareStatement("INSERT INTO canciones (Nombre, Duracion, IdDisco) VALUES (?,?,?)");
			//sta.setInt(1, cancionNew.getIdCancion());
			sta.setString(1, cancionNew.getNombre());
			sta.setInt(2, cancionNew.getDuracion());
			sta.setString(3, cancionNew.getIdDisco());
			sta.executeUpdate();
			sta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void borrarCancion(String cancionNombre){
		try{
			//Statement sta = con.createStatement();
			String sql = "DELETE FROM canciones where Nombre=?";
			PreparedStatement psta = con.prepareStatement(sql);
			psta.setString(1, cancionNombre);
			psta.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void leerCanciones(){
		try{
			//Creamos una statement
			Statement sta = con.createStatement();
			//EJECUTAMOS LA SENTENCIA Y LA METEMOS EN UN OBJETO RESULSET
			ResultSet rs = sta.executeQuery("SELECT * FROM canciones");
			//Recorremos el ResultSet
			while(rs.next()){
				System.out.println("Nº de canción: " + rs.getInt(1) 
				+ "\nNombre: " + rs.getString("Nombre") + "\nDuración: " + rs.getInt(3) 
				+ "\nNombre: " + rs.getString("IdDisco") + "\n-----------");
			}
			rs.close();
			sta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiscograficaPersistencia accJDBC = new DiscograficaPersistencia();
		accJDBC.crearConexion("discografia");
		accJDBC.leerDatos();
		accJDBC.cerrarConexion();
	}
}
