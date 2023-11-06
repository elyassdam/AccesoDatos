package tema1;
import java.io.File;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
public class CopiaArchivo {

		public static void main(String[] args) throws IOException {

			File fichero=new File("C:\\Users\\damSegundo\\eclipse-workspace\\AccesoDatos\\FichProvincias.txt");
			FileReader fic=new FileReader(fichero);
			File ficheroCopia=new File("C:\\Users\\damSegundo\\eclipse-workspace\\AccesoDatos\\FichProvinciasCopia.txt");
			FileWriter ficheroCopiado=new FileWriter(ficheroCopia,true);
			int i;
			char caracter;
			while((i=fic.read())!= -1) {
				caracter=(char)i;
				System.out.println((char)i);
				ficheroCopiado.write(caracter);
				
			}
			fic.close();
			ficheroCopiado.close();
			}
			
		}



