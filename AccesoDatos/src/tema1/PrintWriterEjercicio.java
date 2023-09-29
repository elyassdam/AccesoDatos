import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterEjercicio {


			public static void main(String[] args) throws IOException {
try {
	
		String linea;
		PrintWriter writer=new PrintWriter (new FileWriter ("salidaprint.txt"));
		for (int i = 1; i < 11; i++) {
			writer.print("Fila"+i);
		}
		writer.close();
			}
catch(FileNotFoundException fin ) {
		System.out.println("No se ha encontrado el fichero");
	
			}
catch(IOException io) {
	System.out.println("Error E/S");
	
}
}
}


