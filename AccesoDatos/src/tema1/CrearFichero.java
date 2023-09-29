import java.io.*;
public class CrearFichero {

	public static void main(String[] args) {
		File d =new File("NUEVODIR");
		if(d.delete())
			System.out.println("se ha borrado");
		else 
			System.out.println("no se ha borrado");
		File f1 =new File(d,"FICHERO1.TXT");
		File f2 =new File(d,"FICHERO2.TXT");
		d.mkdir();//Crea Directorio
		
		try {
			if(f1.createNewFile()) {
				System.out.println("Fichero1 creado correctamente");
			}else {
				System.out.println("No se ha podido crear Fichero1  ");
			}if(f2.createNewFile()) {
				System.out.println("Fichero2 creado correctamente");
			}else {
				System.out.println("No se ha podido crear Fichero1  ");}
		}catch(IOException ioe) {ioe.printStackTrace();
		}
		
		f1.renameTo(new File(d,"FICHERO1NUEVO"));
		try {
			File f3=new File("NUEVODIR/FICHERO3.TXT");
			f3.createNewFile();//Crea Fichero en NuevoDir
			f3.delete();
		} catch (IOException ioe) {ioe.printStackTrace();
		
		}
		if(f1.delete())
			System.out.println("Fichero1 borrado correctamente");
	else
		System.out.println("No se ha podido borrar Fichero1  ");
	f2.delete();
	
	if(d.delete())
		System.out.println("si se ha borrado");
	else
	System.out.println("no se ha podido borrar el directorio");

		ListarFich.listar();


	}

	
}
