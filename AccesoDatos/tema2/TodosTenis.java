package tema2;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
public class TodosTenis {
	public static void main(String[] args) {
	//El siguiente ejemplo obtiene todos los jugadores que practican el deporte tenis:
	ODB odb = ODBFactory.open("neodatis.test"); 
	IQuery query = new CriteriaQuery(Jugadores.class, Where.equal ("deporte", "tenis"));
	query.orderByAsc("nombre, edad");
	//Ordena ascendentemente por nombre y edad
	Objects<Jugadores> objects = odb.getObjects(query); //Obtiene todos los jugadores
	//Para obtener el primer objeto usamos el método getFirst0:
	//obtiene solo el 1°
	try{
		Jugadores j = (Jugadores)odb.getObjects(query).getFirst ();
	}catch (IndexOutOfBoundsException e) {
	System.out.printf("OBJETO NO LOCALIZADO");
	

	}
}
}
