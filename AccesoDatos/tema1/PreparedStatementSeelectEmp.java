package tema1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementSeelectEmp {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

            // Recuperar argumentos de main
            int dep = 35;
            String oficio = "Diseñador";

            // Construir orden SELECT
            String sql = "SELECT apellido, salario FROM empleados WHERE dept_no = ? AND oficio = ? ORDER BY 1";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, dep);
            sentencia.setString(2, oficio);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next())
                System.out.printf("%s => %.2f %n", rs.getString("apellido"), rs.getFloat("salario"));

            rs.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
