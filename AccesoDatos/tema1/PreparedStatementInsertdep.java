package tema1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementInsertdep {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

            // Recuperar argumentos de main
            int dep = 35;
            String dnombre = "Tecnología";
            String loc = "Valencia";

            // Construir orden INSERT
            String sql = "INSERT INTO departamentos VALUES (?,?,?)";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, dep);
            sentencia.setString(2, dnombre);
            sentencia.setString(3, loc);

            int filas = sentencia.executeUpdate();

            System.out.println("Filas afectadas: " + filas);

            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
