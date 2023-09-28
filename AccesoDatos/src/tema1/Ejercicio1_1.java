package tema1;
import java.io.*;
public class Ejercicio1_1 {
public static void main(String[] args) {
	String dir="NUEVODIR";
	File f=new File(dir);
	File[] listarFiles=f.listFiles();
	for (int i = 0; i < listarFiles.length; i++) {
System.out.println(listarFiles[i].getName());	
System.out.println(listarFiles[i].getParent());
System.out.println(listarFiles[i].getPath());

	}
}
}
