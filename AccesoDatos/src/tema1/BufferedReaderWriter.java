import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderWriter {


			public static void main(String[] args) throws IOException {
try {
	
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\damSegundo\\eclipse-workspace\\AccesoDatos\\src\\tema1\\LeerFichBuffer.java"));
		String linea;
		BufferedWriter writer=new BufferedWriter (new FileWriter ("salida.txt"));
		while((linea=reader.readLine())!=null) {
		writer.write(linea);
		writer.newLine();
			}
		reader.close();
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


