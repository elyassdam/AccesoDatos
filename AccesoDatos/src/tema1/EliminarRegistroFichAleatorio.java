package tema1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EliminarRegistroFichAleatorio {

	public static void main(String[] args) throws IOException {
File fichero=new File("AleatorioEmple.dat");
RandomAccessFile file=new RandomAccessFile(fichero,"rw");
File archivoTemporal=new File("AleatorioEmple_temp.dat");
RandomAccessFile tempFile=new RandomAccessFile(archivoTemporal,"rw");

int idEliminar=20;
while(file.getFilePointer()<file.length()) {
	int id=file.readInt();
	char[] apellido = new char[10];
	for (int i = 0; i < 10; i++) {
	    apellido[i] = file.readChar();
	}
	  int dep = file.readInt();
      double salario = file.readDouble();
      if (id!=idEliminar) {
    	  tempFile.writeInt(id);
    	  for (int i = 0; i < 10; i++) {
    		  tempFile.writeChar(apellido[i]);
		} 
    	  tempFile.writeInt(dep);
	      tempFile.writeDouble(salario);
      }
}
    	  file.close();
    	  tempFile.close();
    	  fichero.delete();
    	  archivoTemporal.renameTo(fichero);
    	  //Lectura del fichero sine l id5
    	  RandomAccessFile nuevo=new RandomAccessFile(fichero,"rw");
    	  while(nuevo.getFilePointer()<nuevo.length()) {
        	  String apellidos="";
    		  int id=nuevo.readInt();
    			char[] apellido = new char[10];
    			for (int i = 0; i < 10||id<=0; i++) {
    			    apellido[i] = nuevo.readChar();
    			    apellidos=apellidos+apellido[i];
    			}
    			  int dep = nuevo.readInt();
    		      double salario = nuevo.readDouble();
  				System.out.printf("ID: %s, Apellido: %s , Departamento :%d,Salario: %.2f %n",id, apellidos.trim(),dep,salario);

    	  }
    	  nuevo.close();
    	  System.out.println("Registro se ha eliminado con exito"+idEliminar);
    	  
      }

	

}
