package tema1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MiobjectCabeceraPersonalizada {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File fichero=new File("FichMiObject.dat");
		ObjectOutputStream dataOS;
		Persona persona;		
		String nombre[]= {"Ana","Luis Miguel","Alicia"};
		int edad[]= {12,14,18};
	
			if(!fichero.exists()) {
				FileOutputStream fileout=new FileOutputStream(fichero);
				 dataOS=new ObjectOutputStream(fileout);
			}else {
				dataOS=new MiObjectOutputStream(new FileOutputStream(fichero,true));
			}
			try {
			for (int i = 0; i < fichero.length(); i++) {
				persona=new Persona(nombre[i],edad[i]);
				dataOS.writeObject(persona);
			
	}
							
			}catch(Exception e) {
			dataOS.close();
			}
		
		
		
	ObjectInputStream dataIN=new ObjectInputStream(new FileInputStream(fichero));
	try {while(true) {
		try {
				persona=(Persona)dataIN.readObject();
				System.out.println(persona.toString());
				
			

}catch(EOFException eo) {
	dataIN.close();
	break;
}
}
} catch (Exception e) {
e.printStackTrace();
} finally {
dataIN.close();
}
	}
}


	

