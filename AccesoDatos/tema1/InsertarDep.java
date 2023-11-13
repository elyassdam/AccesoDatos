package tema1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertarDep {

		public static void main (String [] args) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");//Cargar el driver
		// Establecemos la conexion con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","ejemplo","ejemplo");
		//recuperar argumentos de main
		int dep = 35;
		String dnombre ="Tecnología";
		String loc = "Valencia";
		double subida=200;
		int emp_no=107;
	double salario =5000;
	String apellido="Ramon";
	String oficio="Diseñador";
	int dir=6;
	double comision=0.23;
	
		Date fecha=new Date (2022-03-02);
		//construir orden INSENT
		//String sql =("INSERT INTO departamentos VALUES (?,?,?)");
		
		//String sql1=("INSERT INTO empleados values(?,?,?,?,?,?,?,?)");
		
		//String sql=("UPDATE empleados SET salario=salario + ? WHERE emp_no=?");
		String sql =("SELECT apellido, salario FROM empleados where dept_no =? AND oficio= ? ORDER BY 1");
		PreparedStatement sentencia =conexion.prepareStatement(sql);
		sentencia.setInt(1, dep);
		sentencia.setString(2, oficio);
		ResultSet rs=sentencia.executeQuery();
		while(rs.next()) 
			System.out.printf("%s => %.2f %n", rs.getString("apellido"),rs.getFloat("salario"));
		rs.close();
		sentencia.close();
		conexion.close();
		
		//sentencia.setInt(2, emp_no) ; 
		//sentencia.setDouble(1,subida); 
		//int filas = sentencia.executeUpdate();

		//construimos la orden SELECT
		//String sql= "SELECT apellido, salario FROM empleados
/*PreparedStatement sentencia=conexion.prepareStatement(sql);
//sentencia.setInt(1, dep);
//sentencia.setString(2, dnombre);
//sentencia.setString(3, loc);
sentencia.setInt(1, emp_no);
sentencia.setString(2, apellido);
sentencia.setString(3,oficio);
sentencia.setInt(4,dir);
sentencia.setDouble(5,salario);
sentencia.setDouble(6,comision);
sentencia.setInt(7,dep);
sentencia.setDate(8, fecha);





int filas=sentencia.executeUpdate();
*/

		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();		}
		}
		
}
