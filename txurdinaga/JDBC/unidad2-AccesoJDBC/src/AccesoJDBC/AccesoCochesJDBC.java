package AccesoJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class AccesoCochesJDBC {
	
	Connection con = null;
	
	public void crearConexion(String nombreBD){

		BasicDataSource bdsource = new BasicDataSource();
		bdsource.setUrl("jdbc:mysql://localhost:3306/" + nombreBD);
		bdsource.setUsername("user_empl");
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
	
		
	public void leerDatos(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT * from coches");
		//recorremos el resultado
		while(rs.next()){
			System.out.println("Nº de Matricula: " + rs.getString(1));
			System.out.println("Modelo: " + rs.getString(2));
			System.out.println("Marca: " + rs.getString(3));
			System.out.println("Cilindrada: " + rs.getShort(4));
			System.out.println("Precio: " + rs.getFloat(5));
			System.out.println("------------------------------------------");	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	
	public void masCaro(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT * FROM coches WHERE precio = (SELECT MAX(precio) FROM coches)");
		//recorremos el resultado
		while(rs.next()){
			System.out.println("MAS CARO");	
			System.out.println("\nNº de Matricula: " + rs.getString(1) +
			"\nModelo: " + rs.getString(2) +
			"\nMarca: " + rs.getString(3) +
			"\nCilindrada: " + rs.getShort(4) +
			"\nPrecio: " + rs.getFloat(5) +
			("\n------------------------------------------"));	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	public void masBarato(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT * FROM coches WHERE precio = (SELECT MIN(precio) FROM coches)");
		//ResultSet rs = sta.executeQuery("SELECT *,MIN(precio) FROM coches)");		
		//recorremos el resultado
		while(rs.next()){
			System.out.println("MAS BARATO");	
			System.out.println("\nNº de Matricula: " + rs.getString(1) +
			"\nModelo: " + rs.getString(2) +
			"\nMarca: " + rs.getString(3) +
			"\nCilindrada: " + rs.getShort(4) +
			"\nPrecio: " + rs.getFloat(5) +
			("\n------------------------------------------"));	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	
	
/*	public void masCaroBarato(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT * FROM coches WHERE precio = (SELECT MAX(precio) FROM coches) OR precio = (SELECT MIN(precio) FROM coches)");
		//recorremos el resultado
		while(rs.next()){
			System.out.println("MAS CARO Y BARATO");	
			System.out.println("\nNº de Matricula: " + rs.getString(1) +
			"\nModelo: " + rs.getString(2) +
			"\nMarca: " + rs.getString(3) +
			"\nCilindrada: " + rs.getShort(4) +
			"\nPrecio: " + rs.getFloat(5) +
			("\n------------------------------------------"));	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
	}*/
	
	
	public void importeTotal(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT SUM(precio) AS precio from coches");
		//recorremos el resultado
		while(rs.next()){
			System.out.println("IMPORTE TOTAL");
			System.out.println("\nPrecio: " + rs.getFloat("precio") +
			"\n------------------------------------------");	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AccesoCochesJDBC accCochesJDBC = new AccesoCochesJDBC();
		accCochesJDBC.crearConexion("coches");
		accCochesJDBC.leerDatos();
		accCochesJDBC.masCaro();
		accCochesJDBC.masBarato();
		//accCochesJDBC.masCaroBarato();
		accCochesJDBC.importeTotal();
		accCochesJDBC.cerrarConexion();

	}

}
