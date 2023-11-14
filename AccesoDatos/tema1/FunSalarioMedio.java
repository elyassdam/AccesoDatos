package tema1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;


public class FunSalarioMedio {
		    public static void main(String[] args) {
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
		            // Establecemos la conexion con la BD
		            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

		            // Recuperar argumentos de main
		            int dep = 10;
		            // Construir orden INSERT
		            String sql = "{?=call salariomedio (?)}";

		            CallableStatement llamada = conexion.prepareCall(sql);
		            llamada.registerOutParameter(1, Types.DOUBLE);//return
		            llamada.setInt(2, dep);//entrada
		            llamada.execute();
		            double salario=llamada.getDouble(1);
		            System.out.printf("salario medio : %.2f",salario);
		            
		            llamada.close();
		            conexion.close();

		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}




