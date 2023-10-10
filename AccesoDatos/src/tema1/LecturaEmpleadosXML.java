package tema1;
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class LecturaEmpleadosXML {
	

public static void main (String []args) {
	

DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
try {
	

	DocumentBuilder builder =factory.newDocumentBuilder()
DocumentBuilder builder - Cactory.newDocumentBuilder () :
Document document - bulider parse (пем File ("Empleados.xm1")) :
document-getDocumentElement.().normalize().
System.out. printf ("Elemento raiz: is in"
document. getDocument Blement () -getNodeName ()) ;
//crea una lista con todos los nodos empleado
NodeList empleados - document getElementsByTagName ( "empleado")
System.out.printf("Nodos empleado a recorrer: &d Sn"
empleados.getLength())
//recorrer la lista
for (int i - 0; 1 « empleados.gettength(); i ++)
Node emple = empleados.item (i), //obtener un nodo empleado
{E (emple.getNodeType () =- Node BLEMENT NODB) (//tipo de nodo
//obtener los elementos del nodo
Blement elemento - (Element) emple;
System.out printE ("ID = &s in".
elemento. getElementsByTagitame("1d")
item(0) getTextContent.0);
System.out.printf("*
Apellido - "Lin"
elemento-getElementsByTagName("apellido").
item(O).getText.ContentO):
System out print[(* * Departamento = 95 §n"
eLemento-getElementsByTagName("dep")
item (0) getTextContent.();
Syscen/outprintET"
SalAra0 F 월터 원고
elemerto.getElementsByTagName("salario")
item (0) .getTextContent ()) ;
(catos excepcion e)
[e printScackTrace 0) s
//Ein de la clase
PHILIPS
