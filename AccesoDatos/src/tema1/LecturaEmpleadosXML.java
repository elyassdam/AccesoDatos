package tema1;
import java.io.File;

import javax.xml.parsers.*;
import org.w3c.dom.*;
public class LecturaEmpleadosXML {
	

public static void main (String []args) {
	

DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
try {
	DocumentBuilder builder =factory.newDocumentBuilder();
Document document =builder.parse(new File("Empleados.xml"));
document.getDocumentElement().getNodeName();
System.out.printf("Elemento raiz : %s %n ",document.getDocumentElement().getNodeName());
NodeList empleados =document.getElementsByTagName( "empleado");
System.out.printf("Nodos empleado a recorrer: %d %n",empleados.getLength());

//recorrer la lista
for (int i =0; i < empleados.getLength(); i ++){
	Node emple =empleados.item(i);//Obetener un nodo empleado
	if(emple.getNodeType()==Node.ELEMENT_NODE) {
		//tipo de nodo obtener los elementos del nodo 
		Element elemento=(Element)emple;
		System.out.printf("ID =%s %n",elemento.getElementsByTagName("id").item(0).getTextContent());
		System.out.printf(" * Apellido =%s %n",elemento.getElementsByTagName("apellido").item(0).getTextContent());
		System.out.printf(" * Departamento =%s %n",elemento.getElementsByTagName("dep").item(0).getTextContent());
		System.out.printf(" * Salario  =%s %n",elemento.getElementsByTagName("salario").item(0).getTextContent());
	}
	}
}catch (Exception e)
{
	e.printStackTrace();
	}
}

}


