package tema1;
import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
public class EscribirFichTexto {
	
		public static void main(String[] args) throws IOException {
	File fichero=new 
	File("C:\\Users\\damSegundo\\eclipse-workspace\\AccesoDatos\\FichProvincias.txt");
	FileWriter fic=new FileWriter(fichero);
	String []prov={"Albacete","Avila","Badajoz","Cáceres","Huelva","Jaén","Madrid","Segovia"};
	for (int i = 0; i < prov.length; i++) {
		fic.write(prov[i]);
		
		
	}
	fic.append ("*");

	fic.close();

		}

	}

