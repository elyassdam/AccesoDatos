package tema1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProcSubida {


	    public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
	            // Establecemos la conexion con la BD
	            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

	            // Recuperar argumentos de main
	            int dep = 10;
	            int subida=250;
	            // Construir orden INSERT
	            String sql = "{call subdiasal (?,?)}";

	            CallableStatement llamada = conexion.prepareCall(sql);
	            llamada.setInt(1, dep);
	            llamada.setInt(2, subida);
	            llamada.executeUpdate();
	            System.out.println("Subida realizada..");

	            llamada.close();
	            conexion.close();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


