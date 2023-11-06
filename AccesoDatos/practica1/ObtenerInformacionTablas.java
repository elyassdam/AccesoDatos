package practica1;
import java.sql.*;

public class ObtenerInformacionTablas {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet resul = null;

            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("=============================");
            System.out.printf("Nombre: %s %n", nombre);
            System.out.printf("Driver: %s %n", driver);
            System.out.printf("URL: %s %n", url);
            System.out.printf("Usuario: %s %n", usuario);

            resul = dbmd.getTables(null, null, null, new String[] { "TABLE" });

            while (resul.next()) {
                String catalogo = resul.getString(1);
                String esquema = resul.getString(2);
                String tabla = resul.getString(3);
                String tipo = resul.getString(4);

                System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
            }

            System.out.println("COLUMNAS DE LA TABLA CONSULTAS:");
            System.out.println("===============================");
            ResultSet columnas = null;
            columnas = dbmd.getColumns(null, null, "consultas", null);

            while (columnas.next()) {
                String nombreCol = columnas.getString("COLUMN_NAME");
                String tipoCol = columnas.getString("TYPE_NAME");
                String tamCol = columnas.getString("COLUMN_SIZE");
                String esNula = columnas.getString("IS_NULLABLE");

                System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser Nula?: %s %n", nombreCol, tipoCol, tamCol, esNula);
            }

            ResultSet pk = dbmd.getPrimaryKeys(null, null, "consultas");
            String pkConsultas = "", separador = "";

            while (pk.next()) {
                pkConsultas = pkConsultas + separador + pk.getString("COLUMN_NAME");
                separador = "+";
            }

            System.out.println("Clave Primaria de Consultas: " + pkConsultas);

            conexion.close();
        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
