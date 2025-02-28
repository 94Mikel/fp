package AccesoDOM;

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
}
