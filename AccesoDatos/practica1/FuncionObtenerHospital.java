package practica1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class FuncionObtenerHospital {
	public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
       
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");

            int idMedico = 3; 

            String sql = "{?=call ObtenerHospitalMedico(?)}";

            CallableStatement llamada = conexion.prepareCall(sql);
            llamada.registerOutParameter(1, Types.INTEGER); 
            llamada.setInt(2, idMedico);
            llamada.execute();

            int idHospital = llamada.getInt(1);
            
            if (idHospital > 0) {
                System.out.printf("El médico con ID %d trabaja en el hospital con ID %d%n", idMedico, idHospital);
            } else {
                System.out.println("El médico no tiene un hospital asignado.");
            }

            llamada.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}


