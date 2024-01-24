package calses;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import tema2.Jugadores;

public class Main4_10 {
	public static void main(String[] args) {
		Paises p1= new Paises(1,"España");
		
		
		
		
		Jugadores j1 =new Jugadores("Maria","voleibol","Madrid",14,p1);
		Jugadores j2 =new Jugadores("Miguel","tenis","Madrid",15,p1);
		Jugadores j3 =new Jugadores("Mario","baloncesto","Guadalajara",15,p1);
		Jugadores j4 =new Jugadores("Alicia","tenis","Madrid",14,p1);
ODB odb=ODBFactory.open("EQUIPOS.db");
odb.store(j1);
odb.store(j2);
odb.store(j3);
odb.store(j4);
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
