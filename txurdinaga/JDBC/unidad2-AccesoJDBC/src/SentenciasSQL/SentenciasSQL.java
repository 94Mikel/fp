package SentenciasSQL;

import java.sql.*;
import org.apache.commons.dbcp.BasicDataSource;

public class SentenciasSQL {
	
	Connection con = null;
	public void crearConexion(String nombreBD, String user, String pass){

		BasicDataSource bdsource = new BasicDataSource();
		bdsource.setUrl("jdbc:mysql://localhost:3306/" + nombreBD);
		bdsource.setUsername(user);
		bdsource.setPassword(pass);
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
	
	public void select(){
		try{
			//Creamos el objeto sentencia
			Statement sta = con.createStatement();
			//ejecutamos la Sentencia y la metemos en un objeto Resulset
			ResultSet rs = sta.executeQuery("SELECT * from coches");
			
			while(rs.next()){
				System.out.println("Matricula: " + rs.getString(1));
				System.out.println("Modelo: " + rs.getString("modelo"));
				System.out.println("Marca: " + rs.getString(3));
				System.out.println("CV: " + rs.getString(4));
				System.out.println("Precio: " + rs.getString(5));
				System.out.println("---------------------------------");
			}
			rs.close();
			sta.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void selectPreestablecida(String valor, String valor2){
		try{
			//creamos una sentencia select con un parametro
			String Query = "Select * FROM coches where precio > ? and precio < ?";
			PreparedStatement psta = con.prepareStatement(Query);
			psta.setString(1,valor);
			psta.setString(2,valor2);
			ResultSet rs = psta.executeQuery();
			//recorremos el resultado
			while(rs.next()){
				System.out.println("Nº de Matricula: " + rs.getString(1));
				System.out.println("Modelo: " + rs.getString("modelo"));
				System.out.println("Marca: " + rs.getString(3));				
				System.out.println("CV: " + rs.getString(4));
				System.out.println("Precio: " + rs.getString(5));
				System.out.println("------------------------------------------");
				System.out.println("------------------------------------------");	
			}
			rs.close();
			psta.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void insertar(){
		try{
		//Creamos la sentencia
		Statement sta = con.createStatement();
		String sql = "insert into coches values ('8520DFG','Leon','Seat', '100', '1420.00')";
		int i = sta.executeUpdate(sql);
		System.out.println(i + "Registro/s coches insertado");
		
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try{
			//Creamos la sentencia
			Statement sta = con.createStatement();
			String sql = "delete from coches where marca = 'Seat'";
			int i = sta.executeUpdate(sql);
			System.out.println(i + "Registro/s coches borrado");
			
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	public void update(){
		try{
			//Creamos la sentencia
			Statement sta = con.createStatement();
			String sql = "update coches set matricula = '8520DFG', modelo = 'Ibiza' where modelo='Leon'";
			int i = sta.executeUpdate(sql);
			System.out.println(i + "Registro/s coches actualizado");
			
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SentenciasSQL SQL = new SentenciasSQL();
		SQL.crearConexion("coches", "user_empl", "vinagres");
		//SQL.select();
		//SQL.selectPreestablecida("4000","8500");
		//SQL.insertar();
		//SQL.delete();
		SQL.update();
		SQL.cerrarConexion();
	}

}   
