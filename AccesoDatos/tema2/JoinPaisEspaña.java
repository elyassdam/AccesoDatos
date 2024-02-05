package tema2;

import java.math.BigDecimal;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class JoinPaisEspaña {
public static void main(String[] args) {
	ODB odb=ODBFactory.open("EQUIPOS.db");
	//Muestra alias 
	Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class,new And().add(Where.equal("pais.nombrepais", "España")).add(Where.equal("edad", 15))).field("nombre").field("ciudad"));
	while(val.hasNext()) {
	ObjectValues ov=(ObjectValues)val.next();
	System.out.printf("Nombre: %s ,Ciudad: %s %n",ov.getByAlias("nombre"),ov.getByAlias("ciudad"));
	}
	odb.close();

}
}
