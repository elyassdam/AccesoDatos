package calses;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import tema2.Jugadores;

public class Main4_10 {
	public static void main(String[] args) {
		
		Paises p1= new Paises(1,"España");
		Paises p2=new Paises(2,"Francia");
		Paises p3=new Paises(3,"Alemania");
		Paises p4=new Paises(4,"Portugal");

		Jugadores j1 =new Jugadores("Alejandro","voleibol","Lille",14,p2);
		Jugadores j2 =new Jugadores("Bosco","tenis","Paris",15,p2);
	
		
ODB odb=ODBFactory.open("EQUIPOS.db");
odb.store(j1);
odb.store(j2);

Objects<Jugadores> objects =odb.getObjects(Jugadores.class);
System.out.printf("%d Jugadores: %n",objects.size());
int i=1;
while(objects.hasNext()) {
	Jugadores jug =objects.next();
	System.out.printf("%d: %s,%s,%s %n, %s %n ",i++,jug.getNombre(),jug.getCiudad(),jug.getEdad(),jug.getPais());
}
odb.close();
}
}
