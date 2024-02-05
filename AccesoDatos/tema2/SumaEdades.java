package tema2;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class SumaEdades {

    public static void main(String[] args) {
    	ODB odb = ODBFactory.open("EQUIPOS.db");
        try  {
 
            Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
            // Muestra alias
            Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).sum("edad"));
            ObjectValues ov = val.nextValues();
            BigDecimal value = (BigDecimal) ov.getByAlias("edad");
            System.out.printf("Suma de edad: %d%n", value.longValue());
            contadorymediaporpais(odb);
        } catch (Exception e) {
            e.printStackTrace();
        	odb.close();
        }
    }

    private static void contadorymediaporpais(ODB odb) {
        System.out.println("Numero de jugadores por país, max de edad y media de edad: ");
        Values groupby = odb.getValues(new ValuesCriteriaQuery(
                Jugadores.class, Where.isNotNull("pais.nombrepais"))
                .field("pais.nombrepais").count("nombre").max("edad").sum("edad").groupBy("pais.nombrepais"));

        if (groupby.size() == 0)
            System.out.println("La consulta no devuelve datos");
        else {
            try {
                while (groupby.hasNext()) {
                    ObjectValues objetos = (ObjectValues) groupby.next();
                    float media = ((BigDecimal) objetos.getByIndex(3)).floatValue()
                            / ((BigInteger) objetos.getByIndex(1)).floatValue();
                    System.out.printf("Pais: %-8s Num jugadores: %d, Edad Máxima: %.0f, Suma de Edad: %.0f, Edad media: %.2f %n",
                            objetos.getByAlias("pais.nombrepais"), objetos.getByIndex(1), objetos.getByIndex(2),
                            objetos.getByIndex(3), media);
                }
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
                Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class)
                        .sum("edad").count("edad"));
                ObjectValues ov2 = val2.nextValues();
                float media;
                BigDecimal sumaedad = (BigDecimal) ov2.getByIndex(0);
                BigInteger cuenta = (BigInteger) ov2.getByIndex(1);
                media = sumaedad.intValue() / cuenta.intValue();
                System.out.printf("La media de edad es: %.2f Contador = %d suma = %.2f %n", media, cuenta, sumaedad);
           odb.close();
            }
        }
    }
}
