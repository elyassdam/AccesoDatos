package practica1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProcedimientoCambioHospitalMedico {

	    public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");

	            
	            int idMedico = 1; 
	            int nuevoHospital = 1; 

	            String sql = "{call CambiarHospitalMedico(?,?)}";

	            CallableStatement llamada = conexion.prepareCall(sql);
	            llamada.setInt(1, idMedico);
	            llamada.setInt(2, nuevoHospital);
	            llamada.executeUpdate();
	            System.out.println("Cambio de hospital realizado.");

	            llamada.close();
	            conexion.close();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


