package FicheroCaracteres;

import java.io.*;
import java.util.Scanner;

public class AccesoFicheroCaracteres {
	
	

	//FUNCION PARA ESCRIBIR FICHERO SECUENCIAL
	public void escribir(String ruta_fichero){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try{
			fichero = new FileWriter(ruta_fichero);
			pw = new PrintWriter(fichero);
			//INICIALIZO
			for (int i=0;i<10;i++){
				pw.println("Linea: " + i);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			 System.out.println("Error al acceder al fichero");
		}finally{
			try{
				fichero.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
				 System.out.println("Error al cerrar el fichero");
			}
		}

	}

	//FUNCION PARA LEER FICHERO SECUENCIAL
	public void leer(String ruta_fichero){
	      
	      FileReader fichero = null;
	      BufferedReader br = null;

	      try{
	    	  fichero= new FileReader(ruta_fichero);
	    	  br = new BufferedReader(fichero);
	    	  String linea;
	    	  while((linea=br.readLine())!=null){
	    		  System.out.println(linea);
	    	  }
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  System.out.println("Error al acceder al fichero");
	      }finally{
			
	    	  try{
				fichero.close();
				}
	    	  catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Error al cerrar el fichero");
				}
			}

		}
	
	//FUNCION PARA AÑADIR FICHERO SECUENCIAL
	public void añadir(String ruta_fichero){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try{
			fichero = new FileWriter(ruta_fichero,true);
			pw = new PrintWriter(fichero);
			//INICIALIZO
			
				pw.println("FINAL DE FICHERO: ");
			
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Error al acceder al fichero");
		}finally{
			try{
				fichero.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
				System.out.println("Error al añadir el fichero");
			}
		}

	}

	//FUNCION ALEATORIA PARA FICHERO SECUENCIAL
	public void leerAleatorio(String ruta_fichero){
		RandomAccessFile fichero=null;
		long pos=0; 		
		Scanner sc= new Scanner(System.in);
		String palabra;
		
		try{
			fichero = new RandomAccessFile(ruta_fichero,"r");
			//SACAMOS POR PANTALLA EL TAMAÑO DEL FICHERO
			System.out.println(fichero.length());

			//PEDIMOS POR PANTALLA UNA POSICION
			System.out.println("Introduce una posicion para leer el fichero:");
			palabra = sc.nextLine();
			pos = Integer.parseInt(palabra);
			
		while(pos>fichero.length()){
			//PEDIMOS POR PANTALLA UNA POSICION
			System.out.println("Introduce una posicion para leer el fichero:");
			palabra = sc.nextLine();
			pos = Integer.parseInt(palabra);
		}
			
			//NOS POSICIONAMOS EN LA POSICION EN CONCRETO
			fichero.seek(pos);
			//SACAMOS LA LINEA POR PANTALLA
			System.out.println(fichero.readLine());
			
		}catch (Exception e){
			e.printStackTrace();

		}finally{
	}
		try{
			fichero.close();
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	public void leerAleatorioRegistro(String ruta_fichero){
		RandomAccessFile fichero=null;
		long pos=0; 	
		Scanner sc= new Scanner(System.in);
		String palabra;
		
		int indice=0;
		long tamaño_indice=34;
		
		try{
			fichero = new RandomAccessFile(ruta_fichero,"rw");
			//SACAMOS POR PANTALLA EL TAMAÑO DEL FICHERO
			//System.out.println("Tamaño del fichero bytes:"+fichero.length());
			//System.out.println("Tamaño del indice:"+ (fichero.length()/tamaño_indice));

			//PEDIMOS POR PANTALLA UNA POSICION
			System.out.println("Introduce el indice para leer en el fichero:");
			palabra = sc.nextLine();
			indice = Integer.parseInt(palabra);
			
		while(indice>(fichero.length())/tamaño_indice){
			//PEDIMOS POR PANTALLA UNA POSICION
			System.out.println("Introduce el indice para leer en el fichero:");
			palabra = sc.nextLine();
			indice = Integer.parseInt(palabra);
		}
			
			//NOS POSICIONAMOS EN LA POSICION EN CONCRETO
			pos = ((indice-1)*tamaño_indice);
			//System.out.println(pos);
			fichero.seek(pos);
			
			//SACAMOS LA LINEA POR PANTALLA
			System.out.println(fichero.readLine());
			
			//ESCRIBIMOS UNA ULTIMA LINEA
			pos=fichero.length();
			fichero.seek(pos);
			fichero.writeBytes("\n XXXXX ultima linea XXXXX");
			
		}catch (Exception e){
			e.printStackTrace();

		}finally{
	}
		try{
			fichero.close();
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	//Metodo para lectura y escritura de fichero binario
	//No contienen caracteres
	public void copiaFicheroBinario(String ficheroOriginal, String ficheroCopia){
		FileInputStream fileInput = null;
		BufferedInputStream bufferedInput = null;
		FileOutputStream fileOutput = null;
		BufferedOutputStream bufferedOutput = null;

		
		try{
			
			//Se abre un fichero original para la lectura
			fileInput = new FileInputStream(ficheroOriginal);
			bufferedInput = new BufferedInputStream(fileInput);
			
			//Se abre el fichero de destino donde se realizara la copia
			fileOutput = new FileOutputStream(ficheroCopia);
			bufferedOutput = new BufferedOutputStream(fileOutput);
			
			//Bucle para recorrer el fichero original y escribir en el otro
			byte [] array = new byte[5000];
			int leidos = bufferedInput.read(array);
		
			if(leidos >0){
				bufferedOutput.write(array,0,leidos);
				System.out.println("Hemos leido y copiado el nº de bytes :" + leidos);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			//cierre el fichero Input
			if (bufferedInput !=null){
				try{
				bufferedInput.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			//cierre el fichero Output
			if (bufferedOutput !=null){
				try{
				bufferedOutput.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}		
		public static void main(String[] Args){
			AccesoFicheroCaracteres afc= new AccesoFicheroCaracteres();
			//afc.escribir("prueba.txt");
			//afc.añadir("prueba.txt");
			//afc.leer("prueba.txt");
			//afc.leerAleatorio("prueba.txt");
			//afc.leerAleatorioRegistro("prueba2.txt");
			afc.copiaFicheroBinario("prueba.txt.","prueba4.txt");
		}
}	