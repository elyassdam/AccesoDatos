package tema1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


		public class LeerFich {
			public static void main(String[] args) throws IOException {
				
			//Creamos el array en el cual van a ir las palabras de 5 en 5
			char buf[]=new char[5];
			//declarar fichero
			File fichero=	new File("C:\\Users\\damSegundo\\eclipse-workspace\\AccesoDatos\\NUEVODIR\\fich1.txt");
			//Abrimos lectura con FileReader
		FileReader fic=new FileReader(fichero);
		int i;
		int desplazamiento=2;
		while((i=fic.read(buf,0,4))!= -1) 
			System.out.println(buf);
		fic.close();
		}
		}
		
		
	


