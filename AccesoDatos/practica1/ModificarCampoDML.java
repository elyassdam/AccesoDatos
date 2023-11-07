package practica1;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
public class ModificarCampoDML {

	

	

		public static void main(String[] args)  throws
		ClassNotFoundException, SQLException {
		//CONEXION A MYSOL
		Class. forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
		//Insertamos una fila en la tabla pacientes con nuevo paciente
		String sql="UPDATE pacientes set nombre='Francisco' where nombre='Marco'";
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



