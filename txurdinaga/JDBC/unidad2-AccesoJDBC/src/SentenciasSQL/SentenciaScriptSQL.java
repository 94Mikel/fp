package SentenciasSQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class SentenciaScriptSQL {

			
	Connection con = null;
	StringBuilder aux = new StringBuilder();
	

	public void crearConexion(String nombreBD, String user, String pass){
		BasicDataSource bdSource = new BasicDataSource();
		bdSource.setUrl("jdbc:mysql://127.0.0.1:3306/" + nombreBD + "?allowMultiQueries=true");
		bdSource.setUsername(user);
		bdSource.setPassword(pass);
		try{
			if(con != null){
				System.out.println("No se puede crear la conexión");
			}
			else{
				con = bdSource.getConnection();
				System.out.println("Conexión creada con BD " + nombreBD);
			}
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
			String Query = "Select * FROM coches where importe > ? and importe < ?";
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

	public void leer(String ruta_fichero){

		String cadena;
		FileReader fichero = null;
		BufferedReader r = null;
		String salto = System.getProperty("line.separator");
		
		try{
			fichero = new FileReader(ruta_fichero);
			r = new BufferedReader(fichero);
			
			while((cadena = r.readLine())!=null) {
	            //System.out.println(cadena);
	            aux.append(cadena);
	            aux.append(salto);
	        }
			System.out.println("Cadena:\n" + aux);
		}catch (Exception e1){
			e1.printStackTrace();
			System.out.println("Error al acceder al fichero");
		}finally{
			try{
				fichero.close();
			}catch (Exception e2){
				e2.printStackTrace();
				System.out.println("Error al cerrar el fichero");
			}
		}  
	}	

	public void ejecutar(){
		try{
			Statement sta = con.createStatement();
			//String sql =  aux;
			String aux2 = aux.toString();
			System.out.println("Cadena:\n" + aux2);
			sta.executeUpdate(aux2);
			//sta.execute(aux2);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	

	public void ejecutarProcedimiento(String procedimientoName, int subida){
		String sql = "{ call " + procedimientoName +" (?)}";
		try{
			CallableStatement llamada = con.prepareCall(sql);
			llamada.setInt(1, subida);
			llamada.executeUpdate();
			System.out.println("Procedimiento prueba creado con exito");
			llamada.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SentenciaScriptSQL SS = new SentenciaScriptSQL();
		SS.crearConexion("coches","user_empl","vinagres");
		//SS.AbrirScriptSQL("sql/scriptSQL.sql");
		//SS.select();
		//SS.selectPreestablecida("4000","8500");
		//SS.insertar();
		//SS.delete();
		//SS.update();
		
		//Ejectar script
		//SS.leer("sql/scriptSQL.sql");
		//SS.ejecutar();
		
		//procedimientos
		SS.ejecutarProcedimiento("prueba_procedimiento",99);
		SS.cerrarConexion();
	}

}
