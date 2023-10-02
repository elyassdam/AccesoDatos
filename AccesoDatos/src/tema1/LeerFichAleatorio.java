package tema1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class LeerFichAleatorio {

	public static void main(String[] args) throws IOException {
		File fichRandom=new File("AleatorioEmple.dat");
		RandomAccessFile file=new RandomAccessFile(fichRandom,"r");
		int id, dep,posicion;
		Double salario;
		char apellido[]=new char[10];
		char aux;//Creamos un char aux en el cual vamos a ir guardando las letras que leemos 
		posicion=0;//Indicamos la posicion en la cual vamos a empezar a leer el fichero
		//Creamos un bucle infinito que la salida sea cuando haya llegado a final del fichero
for(;;) {
			file.seek(posicion);
			id=file.readInt();//sacamos id porque estamos en la posicion 0.
			for (int i = 0; i < apellido.length; i++) {
				aux=file.readChar();
				apellido[i]=aux;
			}
			//Creamos un String apartir de otro
			String apellidos=new String(apellido);
			dep=file.readInt();
			salario=file.readDouble();
			if(id>0) 
				System.out.printf("ID: %s, Apellido: %s , Departamento :%d,Salario: %.2f %n",id, apellidos.trim(),dep,salario);
			;
			posicion=posicion+36;//36 porque es el tamaño que tiene cada registro de nuestro archivo  
			if(file.getFilePointer()==file.length())break;//la salida del bucle infinito
			
		}
		file.close();// cerramos el fichero
		

	}

}
