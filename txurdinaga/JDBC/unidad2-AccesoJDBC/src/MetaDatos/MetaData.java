package MetaDatos;

import java.sql.*;


import org.apache.commons.dbcp.BasicDataSource;

//import com.mysql.jdbc.DatabaseMetaData;
//import com.mysql.jdbc.DatabaseMetaData;

public class MetaData {
	
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

	public void obtenerInfoBBDD(){
	try{
		DatabaseMetaData dbmd = con.getMetaData();
		//info sobre la BBDD
		System.out.println("Info de la base de datos");
		System.out.println("Nombre: "+ dbmd.getDatabaseProductName());
		System.out.println("Driver: " + dbmd.getDriverName());
		System.out.println("URL: " + dbmd.getURL());
		System.out.println("Usuario: " + dbmd.getUserName());
		System.out.println("------------------------------");
		
		//obtener info sobre tablas y vistas
		ResultSet rs = dbmd.getTables(null, "coches", null, null);
		System.out.println("Info de las tablas y vistas");
		while(rs.next()){
			System.out.println("Catalogo: " + rs.getString(1));
			System.out.println("Esquema: " + rs.getString(2));
			System.out.println("Nombre: " + rs.getString(3));
			System.out.println("Tipo: " + rs.getString(4));
			System.out.println("-------------------------");
		}
		
		}catch(SQLException e){
		e.printStackTrace();
		}
	}
	
	public void obtenerInfoTabla(String nombreTabla){
		try{
			DatabaseMetaData dbmd = con.getMetaData();
			System.out.println("Info de la tabla");
			//obtenemos la info de una tabla en concreto
			ResultSet rs = dbmd.getColumns(null, "coches", nombreTabla, null);
			while(rs.next()){
				System.out.println("Nombre: " + rs.getString(4));
				System.out.println("Tipo: " + rs.getString(6));
				System.out.println("Tamaño: " + rs.getString(7));
				System.out.println("Digitos: " + rs.getString(9));
				System.out.println("Puede ser nula: " + rs.getString(18));
				System.out.println("**********************************");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void obtenerInfoResulset(){
		try{
			//Creamos un statement - Consulta
			Statement sta = con.createStatement();
			//ejecutamos la Sentencia y la metemos en un objeto Resulset
			ResultSet rs = sta.executeQuery("SELECT * from coches");
			
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
		MetaData metadata = new MetaData();
		metadata.crearConexion("coches", "user_empl", "vinagres");
		//Obtener info BBDD
		metadata.obtenerInfoBBDD();
		//Obtener info TABLA
		metadata.obtenerInfoTabla("coches");
		//Obtener info de un resulset
		metadata.obtenerInfoResulset();
		metadata.cerrarConexion();
	}

}
