package tema1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerFichData {

	public static void main(String[] args) throws IOException {
			
		
File fichero=new File("Fichdata.dat");
DataInputStream datain=new DataInputStream(new FileInputStream(fichero));
String n=null;
int e=0;
try {
	

for (int i = 0; i < fichero.length(); i++) {
	 n =datain.readUTF();
	 e=datain.readInt();
	
		System.out.println("Nombre :"+n + ", Edad :" + e);
	}
	
}catch (EOFException eo) {
	datain.close();
}
	}
}
/*while(datain.available()>0) {
	System.out.println("Nombre :"+n);
	System.out.println("Edad :" + e);
}*/



	
		
	
