package tema2;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

 
public class EjemploNeodatis {
	public static void main(String[] args) {
		Jugadores j1 =new Jugadores("Maria","voleibol","Madrid",14);
		Jugadores j2 =new Jugadores("Miguel","tenis","Madrid",15);
		Jugadores j3 =new Jugadores("Mario","baloncesto","Guadalajara",15);
		Jugadores j4 =new Jugadores("Alicia","tenis","Madrid",14);
ODB odb=ODBFactory.open("neodatis.test");
odb.store(j1);
odb.store(j2);
odb.store(j3);
odb.store(j4);
Objects<Jugadores> objects =odb.getObjects(Jugadores.class);
System.out.printf("%d Jugadores: %n",objects.size());
int i=1;
while(objects.hasNext()) {
	Jugadores jug =objects.next();
	System.out.printf("%d: %s,%s,%s %n",i++,jug.getNombre(),jug.getCiudad(),jug.getEdad());
}
//Muestra alias 
Values valores = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("nombre").field("ciudad"));
while(valores.hasNext()) {
	ObjectValues objectValues=(ObjectValues)valores.next ();

System.out.printf("%s, Ciudad : %s %n",objectValues.getByAlias("nombre"),objectValues.getByIndex(1));
}
odb.close();

}
}

	
	
	
	
	

