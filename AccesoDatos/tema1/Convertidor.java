package tema1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.*;
public class Convertidor  {
public static void main(String[] args)throws IOException {
		String hojaEstilo ="alumnoPlantilla.xsl";
	String datosAlumnos = "alumnos.xml";
	File pagHTML = new File ("mipagina.html");
	//crear fichero HTML
	FileOutputStream os =new FileOutputStream (pagHTML) ;
	Source estilos = new StreamSource (hojaEstilo);//fuente XSI
	Source datos = new StreamSource (datosAlumnos); //fuente XML
	//resultado de la transformación
	StreamResult result = new StreamResult (os) ;
	try {Transformer transformer=TransformerFactory.newDefaultInstance().newTransformer(estilos);
	transformer.transform(datos, result);
	}
	//obtiene el HIMI
	catch (Exception e) {
		System.err.println("Error: "+e) ; 
	}
	os. close ();
	//cerrar fichero
	//de main
	//de la clase
	//El fichero HTML generado se muestra en la Figura 1.3.
}
}
