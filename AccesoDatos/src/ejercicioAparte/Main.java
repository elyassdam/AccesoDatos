package ejercicioAparte;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import ejercicioAparte.Persona;

class Main {

		static String BDPer ="DBPersonas-yap";
		public static void main (String[] args) {
		ObjectContainer db=
		Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDPer);
		
		// Se crean obietos Persona
		Persona pl = new Persona ("Juan","Guadalajara");
		Persona p2 = new Persona ("Ana","Madrid");
		Persona p3 = new Persona ("Luis","Granada");
		Persona p4 = new Persona ("Pedro", "Asturias");
		// Almacenar objetos Persona en la base de datos
		db. store (pl);
		db. store (p2);
		db. store (p3);
		db.store (p4);
		db.close (); // cerrar base de datos
		// fin metodo main
		// fin de la clase Main ;
	
		}
		

}
