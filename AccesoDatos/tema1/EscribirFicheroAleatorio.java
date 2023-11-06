package tema1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFicheroAleatorio {

	public static void main(String[] args) throws IOException {
		File fichRandom=new File("AleatorioEmple.dat");
		RandomAccessFile file=new RandomAccessFile(fichRandom,"rw");
		String apellido[]= {"Fernandez","Ramos,Alvarez","Gonzalez","Lopez"};
		int dep []= {10,20,10,10,30};//departamentos
		Double salario[]= {1000.45,2400.60,3000.0,1500.56,2200.0};//salarios
		StringBuffer buffer=null;//buffer para almacenar apellido seria como si fuese un array y lo inicializamos a null  
		int n=apellido.length;//guardamos el tamaño del String de apellido 
		for (int i = 0; i < n; i++) {
			file.writeInt(i+1);//Escribe el id partiendo de 1 
			buffer=new StringBuffer(apellido[i]);
			buffer.setLength(10);//Le indicamos el espacio que tendran todos los apellidos siendo de 10
			file.writeChars(buffer.toString());//Pasamos el buffer a String
			file.writeInt(dep[i]);//insertar departamento
			file.writeDouble(salario[i]);//inserta salario
		}
		file.close();//Cerramos el fichero
		}
		
	}


