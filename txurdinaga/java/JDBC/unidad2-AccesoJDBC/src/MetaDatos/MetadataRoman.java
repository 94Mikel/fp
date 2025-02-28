package MetaDatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class MetadataRoman {
	
	Connection con = null;
	public void crearConexion(String bolsas, String remotoODBC /*String pass*/){

		BasicDataSource bdsource = new BasicDataSource();
		bdsource.setUrl("jdbc:mysql://10.9.52.156/" + bolsas);
		bdsource.setUsername(remotoODBC);
		bdsource.setPassword(remotoODBC);
		try{
			if(con !=null){
				System.out.println("No se puede crear la conexion");
			}else{
				con = bdsource.getConnection();
				System.out.println("Conexion creada con la BD " + bolsas);
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
	
	
	public void leerDatos(){
		try{
		//Creamos una statement - Consulta
		Statement sta = con.createStatement();
		//ejecutamos la sentencia y la metemos en un objeto resulset
		ResultSet rs = sta.executeQuery("SELECT * from wp_users");
		//recorremos el resultado
		while(rs.next()){
			System.out.println("Nº de Matricula: " + rs.getString(2));
			System.out.println("Modelo: " + rs.getString(3));
			System.out.println("------------------------------------------");	
		}
		rs.close();
		sta.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	
	
	public void obtenerInfoResulset(){
		try{
			//Creamos un statement - Consulta
			Statement sta = con.createStatement();
			//ejecutamos la Sentencia y la metemos en un objeto Resulset
			ResultSet rs = sta.executeQuery("SELECT * from wp_users");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int nColumnas=rsmd.getColumnCount();
			
			System.out.println("Info de un resultset");
			for(int i =1; i <= nColumnas; i++){
				System.out.println("Indice column :" + i);
				System.out.println("Nombre: " + rsmd.getColumnName(i));
				System.out.println("Tipo: " + rsmd.getColumnType(i));
				System.out.println("Es nula: " + rsmd.isNullable(i));
				System.out.println("Tamaño: " + rsmd.getColumnDisplaySize(i));
				System.out.println("****************************************");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetadataRoman metaRoman = new MetadataRoman();
		//Crear la conexion
		metaRoman.crearConexion("bolsas", "remotoODBC");
		//obtener info BBDD
		metaRoman.leerDatos();
		//obtener info Tabla
		//metaRoman.obtenerInfoTabla("wp_users");
		//Obtener info de un resultset
		//metaRoman.obtenerInfoResulset();
		//Cerrar la conexion
		metaRoman.cerrarConexion();
	}

}
