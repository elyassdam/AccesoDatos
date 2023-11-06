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
		byte id[]= {1,2,3};
		char inicial[]= {'A','L','A'};
		boolean mayor[]= {false,false,true};
		short numeroLetras[]= {3,10,6};
		long numRegistrosTotales=3;
		double notas[]= {2.5,8.5,6.0};
		float pruebaFloat[]= {2.2f,3.3f,1.5f};
		
		for (int i = 0; i < edades.length; i++) {
			dataOut.writeUTF(nombres[i]);
			dataOut.writeInt(edades[i]);
			dataOut.writeBoolean(mayor[i]);
			dataOut.write(id[i]);
			dataOut.writeChar(inicial[i]);
			dataOut.writeChars(nombres[i]);
		}
		dataOut.close();
			}

		
	}


