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
		char aux;
		posicion=0;
		for(;;) {
			file.seek(posicion);
			id=file.readInt();//sacamos id porque estamos en la posicion 0
			for (int i = 0; i < apellido.length; i++) {
				aux=file.readChar();
				apellido[i]=aux;
			}
			//el array a string
			String apellidos=new String(apellido);
			dep=file.readInt();
			salario=file.readDouble();
			if(id>0) 
				System.out.printf("ID: %s, Apellido: %s , Departamento :%d,Salario: %.2f %n",id, apellidos.trim(),dep,salario);
			;
			posicion=posicion+36;
			if(file.getFilePointer()==file.length())break;
			
		}
		file.close();
		

	}

}
