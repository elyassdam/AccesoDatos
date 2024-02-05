package ejerciciosNeodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class ejemploOid {
public static void main(String[] args) {
	ODB odb=ODBFactory.open("neodatis.test");
	OID oid =OIDFactory.buildClassOID(3);
	Jugadores jug=(Jugadores) odb.getObjectFromId(oid);
	System.out.printf("%s,%s,%s,%d %n",jug.getNombre(),jug.getDeporte(),jug.getCiudad(),jug.getEdad());
	odb.close();

}
	
	
}
