package tema1;

	import java.sql.*;
	public class Main {
	public static void main (String [] args) {
	try {
	//Cargar el driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	//Establecemos la conexion con la BD
	Connection conexion = DriverManager.getConnection
	("jdbc:mysql://localhost/ejemplo","ejemplo","ejemplo");
	// Preparamos la consulta
	Statement sentencia = conexion.createStatement();
	String sql = "SELECT * FROM departamentos";
	ResultSet resul = sentencia. executeQuery (sql);
	//Recorremos el resultado para visualizar cada fila
	//Se hace un bucle mientras haya registros y se van mostrando
	while (resul.next ()) {
	System.out.printf("%d, %s, %s %n",
	resul.getInt(1),
	resul.getString(2),
	resul.getString(3));
}
resul.close();
sentencia.close();
conexion.close();}
	catch(ClassNotFoundException cn) {
		cn.printStackTrace();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	}
	}

	
	
