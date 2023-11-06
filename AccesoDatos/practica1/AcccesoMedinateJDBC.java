package practica1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcccesoMedinateJDBC {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
            // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM consultas";
            ResultSet resul = sentencia.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van mostrando
            while (resul.next()) {
                System.out.printf("%d, %d, %d, %s, %s, %s, %s, %s, %d%n",
                        resul.getInt(1),
                        resul.getInt(2),
                        resul.getInt(3),
                        resul.getString(4),
                        resul.getTime(5),
                        resul.getString(6),
                        resul.getString(7),
                        resul.getString(8),
                        resul.getInt(9));
            }
            resul.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

	

