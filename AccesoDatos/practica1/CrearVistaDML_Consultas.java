package practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearVistaDML_Consultas {

public static void main(String[] args)  throws
	ClassNotFoundException, SQLException {
	//CONEXION A MYSOL
	Class. forName("com.mysql.cj.jdbc.Driver");
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
	String sql="CREATE VIEW Vista_DiagnosticoConPacientes AS  SELECT C.Diagnostico, CONCAT(P.Nombre, ' ', P.Apellido) AS NombrePaciente FROM Consultas C JOIN Pacientes P ON C.PacienteID = P.IDPaciente";			
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

