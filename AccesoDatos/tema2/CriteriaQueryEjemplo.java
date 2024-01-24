package tema2;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class CriteriaQueryEjemplo {public static void main(String[] args) {
	

ODB odb =ODBFactory.open("neodatis.test");
IQuery query =new CriteriaQuery(Jugadores.class,Where.equal("nombre","Maria"));
query.orderByAsc("nombre,edad");
Objects<Jugadores> objects=odb.getObjects(query);
/*System.out.println("Hay "+ objects.size()+" Jugadores de tenis:");
int i = 1; //contador para mostrar listados los objetos
//visualizar los objetos
while(objects.hasNext()) {
// Creo un objeto Jugadores y almaceno ahí el objeto que recupero de la BD
Jugadores jug = objects.next();
// Imprimo las propiedades que me interesan de ese objeto
System.out.println((i++)+" - "+"Nombre: "+jug.getNombre()+", Deporte: "+ jug.getDeporte());
}
*/
//ModificarEjemplo 
Jugadores jug = (Jugadores)objects.getFirst();
jug.setDeporte("voley-playa");
odb.store(jug);
odb.commit();
//Imprimo las propiedades que me interesan de ese objeto
System.out.println(" - "+"Nombre: "+jug.getNombre()+", Deporte: "+ jug.getDeporte());





}
}




