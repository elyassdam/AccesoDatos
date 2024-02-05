package tema2;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class AliasIndice {
public static void main(String[] args) {
ODB odb=ODBFactory.open("neodatis.test");
Objects<Jugadores> objects =odb.getObjects(Jugadores.class);
int i=1;
//Muestra alias 
Values valores = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("nombre").field("ciudad"));
while(valores.hasNext()) {
ObjectValues objectValues=(ObjectValues)valores.next ();

System.out.printf("%s, Ciudad : %s %n",objectValues.getByAlias("nombre"),objectValues.getByIndex(1));
}
odb.close();

}
}
