package tema1;

import java.io.File;

public class ListarFich {
	public static void main(String[] args) {
		listar();
	}
public static void listar() {
String dir ="NUEVODIR"; //directorio actual
	File f = new File (dir);
	String [] archivos=f.list();
	System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
	for (int i = 0; i < archivos.length; i++) {
		File f2=new File(f,archivos[i]);
		String nombre=archivos[i];
		System.out.print("Nombre :"+nombre);
		if(f2.isFile()) {
			System.out.print(" Es fcihero ");
		}else if(f2.isDirectory()){
			System.out.println(" Es directorio ");
		}
		
		//System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n", archivos[i], f2.isFile(), f2.isDirectory());
	}
	}
}

