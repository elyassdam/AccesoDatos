package tema1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementInsertemp {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

            // Recuperar argumentos de main
            int emp_no = 107;
            String apellido = "Ramon";
            String oficio = "Diseñador";
            int dir = 6;
            double salario = 5000;
            double comision = 0.23;
            int dep = 35;
            Date fecha = new Date(2022 - 03 - 02);

            // Construir orden INSERT
            String sql = "INSERT INTO empleados VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, emp_no);
            sentencia.setString(2, apellido);
            sentencia.setString(3, oficio);
            sentencia.setInt(4, dir);
            sentencia.setDouble(5, salario);
            sentencia.setDouble(6, comision);
            sentencia.setInt(7, dep);
            sentencia.setDate(8, fecha);

            int filas = sentencia.executeUpdate();

            System.out.println("Filas afectadas: " + filas);

            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
