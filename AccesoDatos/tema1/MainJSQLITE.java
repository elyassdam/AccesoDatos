package tema1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainJSQLITE {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            // Cargar el driver
            Class.forName("org.sqlite.JDBC");
            // Establecer la conexión con la BD (debes especificar la ubicación y nombre del archivo)
            conexion = DriverManager.getConnection("jdbc:sqlite:ejemplo.db");
            // Preparar la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos";
            ResultSet resul = sentencia.executeQuery(sql);
            // Recorrer el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van mostrando
            while (resul.next()) {
                System.out.printf("%d, %s, %s %n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3));
            }
            resul.close();
            sentencia.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
