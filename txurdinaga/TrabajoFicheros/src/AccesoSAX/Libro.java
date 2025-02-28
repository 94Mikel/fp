package AccesoSAX;

import java.util.List;

public class Libro {
	
	//Atributos	
	String PublicadoEn;
	String Titulo;
	String Autor;
	
	//Metodo publicadoEn
	public String getPublicadoEn(){
		return PublicadoEn;
	}
	public void setPublicadoEn(String PublicadoEn){
		this.PublicadoEn = PublicadoEn;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public void setAñodeedicion(String añodeedicion) {
		// TODO Auto-generated method stub
		
	}
	public void setNumerodepaginas(String numerodepaginas) {
		// TODO Auto-generated method stub
		
	}
	public void setEditorial(String editorial) {
		// TODO Auto-generated method stub
		
	}
	public void setNumerodeejemplares(String numerodeejemplares) {
		// TODO Auto-generated method stub
		
	}
	public void setISBN(String iSBN) {
		// TODO Auto-generated method stub
		
	}
	
}
