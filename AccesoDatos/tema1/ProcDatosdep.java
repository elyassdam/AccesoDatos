package tema1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcDatosdep {


	    public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
	            // Establecemos la conexion con la BD
	            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

	            // Recuperar argumentos de main
	            int dep = 10;
	            String nom;
	            String locali;
// Construir orden INSERT
	            String sql = "{call datos_dep (?,?,?)}";

	            CallableStatement llamada = conexion.prepareCall(sql);
	            llamada.registerOutParameter(2,Types.VARCHAR);
	            llamada.setInt(1,dep);
	            llamada.registerOutParameter(3,Types.VARCHAR);
	            
	            llamada.execute();
	    nom=llamada.getString(2);
	    locali=llamada.getString(3);
	           
	            System.out.println("Nombre :"+nom);
	            System.out.println("Localizacion :"+locali );

	            llamada.close();
	            conexion.close();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


