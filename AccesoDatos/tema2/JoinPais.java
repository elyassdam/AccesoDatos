package tema2;

import java.math.BigDecimal;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class JoinPais {
public static void main(String[] args) {
	ODB odb=ODBFactory.open("EQUIPOS.db");
	Objects<Jugadores> objects =odb.getObjects(Jugadores.class);
	//Muestra alias 
	Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("nombre").field("edad").field("pais.nombrepais"));
	while(val.hasNext()) {
	ObjectValues ov=(ObjectValues)val.next();
	System.out.printf("Nombre: %s Edad:%d ,Pais: %s %n",ov.getByAlias("nombre"),ov.getByIndex(1),ov.getByIndex(2));
	}
	odb.close();

}
}
