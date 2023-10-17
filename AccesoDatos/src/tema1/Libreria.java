package tema1;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Libreria {
private ArrayList<Libro> listaLibro;
private String nombre;
private String lugar;
public Libreria(ArrayList<Libro>listaLibro,String nombre,String lugar) {
	super();
	this.listaLibro=listaLibro;
	this.nombre=nombre;
}
public Libreria() {}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getLugar() {
	return lugar;
}
public void setLugar(String lugar) {
	this.lugar = lugar;
}
//Wrapper,envoltura alrededor la representación XML
@XmlElementWrapper(name ="ListaLibro")//
@XmlElement(name="Libro")
public ArrayList<Libro>getListaLibro(){
	return listaLibro;
}
public void setListaLibro(ArrayList<Libro>listaLibro) {
	this.listaLibro=listaLibro;
	
}


	
}
