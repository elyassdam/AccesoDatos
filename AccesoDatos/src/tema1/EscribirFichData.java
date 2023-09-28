package tema1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFichData {

	public static void main(String[] args) throws IOException {

		File fichero=new File("Fichdata.dat");
		DataOutputStream dataOut=new DataOutputStream(new FileOutputStream(fichero));
		String nombres[]= {"Ana","Luis Miguel","Alicia"};
		int edades[]= {12,14,18};
		for (int i = 0; i < edades.length; i++) {
			dataOut.writeUTF(nombres[i]);
			dataOut.writeInt(edades[i]);
			
		}
		dataOut.close();
			}

		
	}


