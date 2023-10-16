package tema1;



import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CrearEmpleadoXML {

	public static void main(String[] args) throws IOException {
		File fichero = new File("AleatorioEmple.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int id, dep, posicion = 0;
		Double salario;
		char apellido[] = new char[10];
		char aux;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0");
			for (;;) {
				file.seek(posicion);
				id = file.readInt();
				for (int i = 0; i < apellido.length; i++) {
					aux = file.readChar();
					apellido[i] = aux;

				}
				String apellidos = new String(apellido);
				dep = file.readInt();
				salario = file.readDouble();
				if (id > 0) {
					Element raiz = (Element) document.createElement("empleado");
					document.getDocumentElement().appendChild((Node) raiz);
					// añadir id
					CrearElemento("id", Integer.toString(id), raiz, document);
					CrearElemento("apellido", apellidos.trim(), raiz, document);
					CrearElemento("dep", Integer.toString(dep), raiz, document);
					CrearElemento("salario", Double.toString(salario), raiz, document);
				}
				posicion = posicion + 36;
				if (file.getFilePointer() == file.length())
					break;
			} // fin del for que recorre el fichero
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Empleados.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			System.err.println("Error :" + e);
		}
		file.close();
	}

	// Insercion de los datos del empleado
	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
	Element elem =  document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);

	}

}
