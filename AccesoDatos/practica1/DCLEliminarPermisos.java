package practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DCLEliminarPermisos {

	public static void main(String[] args)  throws
	ClassNotFoundException, SQLException {
	//CONEXION A MYSOL
	Class. forName("com.mysql.cj.jdbc.Driver");
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
	//Insertamos una fila en la tabla pacientes con nuevo paciente
	String sql="REVOKE  SELECT, INSERT, UPDATE, DELETE ON sanidad.* FROM 'sanidad'@'localhost'";
	Statement sentencia = conexion. createStatement () ;
	boolean valor = sentencia. execute (sql) ;
	if (!valor) {
System.out.println("permisos eliminados correctamente");
	}else{
System.out.println("No eliminados correctamente, revisa tu código");	
	}
	sentencia.close();
	conexion.close();
}
}

