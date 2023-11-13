package tema1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementUpdateEmp {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

            // Recuperar argumentos de main
            double subida = 200;
            int emp_no = 107;

            // Construir orden UPDATE
            String sql = "UPDATE empleados SET salario = salario + ? WHERE emp_no = ?";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setDouble(1, subida);
            sentencia.setInt(2, emp_no);

            int filas = sentencia.executeUpdate();

            System.out.println("Filas afectadas: " + filas);

            // Construimos la orden SELECT
            String selectSql = "SELECT apellido, salario FROM empleados WHERE emp_no = ?";
            PreparedStatement selectStatement = conexion.prepareStatement(selectSql);
            selectStatement.setInt(1, emp_no);
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next())
                System.out.printf("%s => %.2f %n", rs.getString("apellido"), rs.getFloat("salario"));

            rs.close();
            selectStatement.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
