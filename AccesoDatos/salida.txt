package tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LeerFichBuffer {

	public static void main(String[] args) throws IOException {

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
	}


