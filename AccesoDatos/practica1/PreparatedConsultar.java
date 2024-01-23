	package practica1;
	
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	public class PreparatedConsultar {
	
			public static void main(String[] args)  throws
			ClassNotFoundException, SQLException {
			//CONEXION A MYSOL
			Class. forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");
			//Realizamos una modificación en la tabla de medicos
			String sql="SELECT * from medicos where Especialidad=? ";
			PreparedStatement sentencia = conexion.prepareStatement(sql) ;
			sentencia.setString(1, "Pediatra");//Es la especialidad de la que queremos consultar
			ResultSet rs=sentencia.executeQuery();
	
			while(rs.next()) {
	int idmedico=rs.getInt("IDMedico");
	String nombre=rs.getString("Nombre");
	String apellidos=rs.getString("Apellidos");
	String especialidad=rs.getString("Especialidad");
	String Consultorio=rs.getString("Consultorio");
	String telefono=rs.getString("Telefono");
	int idhospital=rs.getInt("IDHospital");
			
			System.out.printf("Lo evuelto sera: %d %n %s %n %s %n %s %n %s %n %s %n %d %n",idmedico,nombre,apellidos,especialidad,Consultorio,telefono,idhospital);
			}
			
		
	rs.close();
	sentencia.close();
	conexion.close();
		}
	}
	
	
	
	
