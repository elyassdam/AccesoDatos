package tema1;
import java.sql.*;
public class EjemploExecute {


public static void main(String[] args)  throws
	ClassNotFoundException, SQLException {
	//CONEXION A MYSOL
	Class. forName("com.mysql.cj.jdbc.Driver");
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");
	String sql="create view vista_empleados_madrid_inner as SELECT empleados.*,departamentos.loc FROM empleados INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no WHERE departamentos.loc = 'Madrid'";
			
	Statement sentencia = conexion. createStatement () ;
	boolean valor = sentencia. execute (sql) ;
	if (valor) {
	ResultSet rs = sentencia.getResultSet() ;
	while (rs.next()) 
	System.out.printf("%d,%s, %s %n",rs.getInt(1), rs.getString (2), rs.getString (3));
	rs.close ();
	}else{
		int f = sentencia.getUpdateCount () ;
	
	System.out.printf("Filas afectadas:%d %n",f) ;
	}
	sentencia.close();
	conexion.close();
}
}
	//main



