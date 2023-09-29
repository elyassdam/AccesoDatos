import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFichBytes {

	public static void main(String[] args) throws IOException {
File fichero=new File("C:\\Users\\Yassin\\eclipse-workspace\\AccesoDatos\\fichero1.dat");
	FileOutputStream fileout=new FileOutputStream(fichero,true);
	FileInputStream filein=new FileInputStream(fichero);
	int i;
	for ( i = 0; i < 100; i++) {
		fileout.write(i);
		
	}
	fileout.close();
	
while((i=filein.read())!=-1) {
	System.out.println(i);
	
}
filein.close();
	}

}
