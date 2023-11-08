package tema1;
import java.sql.*;
public class EjemploExecuteQuery {


public static void main(String[] args)  throws
	ClassNotFoundException, SQLException {
	//CONEXION A MYSOL
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conexion =DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","ejemplo", "ejemplo");
	//String sql="SELECT * FROM empleados";
    String sql = "ALTER TABLE empleados ADD COLUMN fecha_alta DATE";
	Statement sentencia=conexion.createStatement () ;
	 sentencia.executeUpdate(sql) ;
	//ResultSetMetaData rsmd =valor.getMetaData();
	//int nColumnas = rsmd.getColumnCount ();
	//System.out.printf("%d,%s, %s %n",valor.getInt(1), valor.getString (2), valor.getString (3));
	//System.out.printf("Filas afectadas: %d %n",rsmd.getColumnCount());
	System.out.println("Fecha agregada con éxito");
	sentencia.close();
	conexion.close();

	}
}

	//main



