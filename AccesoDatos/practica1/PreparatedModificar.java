package practica1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparatedModificar {

		public static void main(String[] args)  throws
		ClassNotFoundException, SQLException {
		//CONEXION A MYSOL
		Class. forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
		//Realizamos una modificación en la tabla de medicos
		String sql="UPDATE medicos set Especialidad=? where Especialidad=? AND Apellidos=? ";
		PreparedStatement sentencia = conexion.prepareStatement(sql) ;
		sentencia.setString(1, "Geriatra");//cambiar el nombre de la especialidad a Geriartra
		sentencia.setString(2, "Médico de Urgencias");//Es la especialidad de la que queremos cambiar
		sentencia.setString(3,"Sánchez Ruiz");//El apellido que tiene aparte de ser medico de urgencias
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




