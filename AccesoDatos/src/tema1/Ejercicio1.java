package tema1;
import java.io.*; 
public class Ejercicio1 {

	public static void main(String[] args) {
		/*String dir ="."; //directorio actual
		
		File f=new File (dir);
		String[] archivos =f.list();
		System.out.printf(" Ficheros en el directorio actual: %d %n",archivos.length);
		for (int i = 0; i < archivos.length;i++) {
			File f2 =new File(f,archivos[i]);
			System.out.printf("Nombre: %s, es fichero?: %b, es directorio?:%b %n", archivos[i], f2.isFile(),f2.isDirectory());	
		}*/
	//Ejercicio simplificado sin % ni printf
		String dir ="."; //directorio actual
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


