package tema1;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
public class EscribirPersonasXML {
public static void main(String[] args) throws IOException, ClassNotFoundException {
	File fichero =new File("FichPersona.dat");
	FileInputStream filein=new FileInputStream(fichero);
	ObjectInputStream dataIS=new ObjectInputStream(filein);
	System.out.println("Comienza el proceso...");
	//Creamos una lista de personas
	ListaPersonas listaper=new ListaPersonas();
	try {
		while (true) {
			//lecturaFichero
			Persona persona=(Persona) dataIS.readObject();
			listaper.add(persona);//añadir persona a la lista
		}
	}catch (EOFException eo) {}
	dataIS.close();
	try {
		XStream xstream=new XStream();
		//cambiar el nombre a las etiquetas XML
		xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
		xstream.alias("DatosPersona", Persona.class);
		//quitar etiqueta lista(atributo de ela clase ListaPersonas)
		xstream.addImplicitCollection(ListaPersonas.class, "lista");
		//Insertar los objetos en el XML
		xstream.toXML(listaper,new FileOutputStream("Personas.xml"));
		System.out.println("Creado fichero XML");
	}catch(Exception e) {
		e.printStackTrace();}
		
	}
	
	
	
}
 class ListaPersonas{
	private List <Persona>lista =new ArrayList<Persona>();

	public void  add(Persona per) {
		lista.add(per);
	}

	public List<Persona> getLista() {
		return lista;
	}
	
}

