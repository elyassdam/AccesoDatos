package tema1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream{

	protected MiObjectOutputStream(OutputStream out) throws IOException, SecurityException {
		super();}
//Redifinición del méetodo de escribir en la cabecera para que no haga nada	
		protected void writeStreamHeader()throws IOException{
			
		}
		

	

	}


