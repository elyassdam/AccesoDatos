package practica1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparatedInsertar {

		public static void main(String[] args)  throws
		ClassNotFoundException, SQLException {
		//CONEXION A MYSOL
		Class. forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
		//Insertamos una fila en la tabla pacientes con nuevo paciente
		String sql="UPDATE pacientes";
		PreparedStatement sentencia = conexion.prepareStatement(sql) ;
		int id=18;
		String nombre="Marco";
		String apellido="Polo";
		Date fecha=new Date(1985-8-20);
		String direccion="San Juan de la Cruz,32";
		String numero="555-890-1879";
		sentencia.setInt(1, id);
		sentencia.setString(2, nombre);
		sentencia.setString(3, nombre);
		sentencia.setDate(4, fecha);
		sentencia.setString(5, direccion);
		sentencia.setString(6, numero);
		sentencia.setInt(7,2);
int filas=sentencia.executeUpdate();

		if(filas>0) {
		
		System.out.printf("Filas afectadas:%d %n",filas) ;
		}else{
			System.out.println("No se ha ehcho ningun cambio");
		}
		sentencia.close();
		conexion.close();
	}
	}




