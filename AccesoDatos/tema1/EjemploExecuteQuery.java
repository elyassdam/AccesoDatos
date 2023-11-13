package tema1;
import java.sql.*;
public class EjemploExecuteQuery {


public static void main(String[] args)  throws
	ClassNotFoundException, SQLException {
	//CONEXION A MYSOL
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conexion =DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","ejemplo", "ejemplo");
	//String sql="SELECT * FROM empleados";
    String sql = "ALTER TABLE empleados ADD COLUMN fecha_alta DATE";
	Statement sentencia=conexion.createStatement () ;
	 sentencia.executeUpdate(sql) ;
	//ResultSetMetaData rsmd =valor.getMetaData();
	//int nColumnas = rsmd.getColumnCount ();
	//System.out.printf("%d,%s, %s %n",valor.getInt(1), valor.getString (2), valor.getString (3));
	//System.out.printf("Filas afectadas: %d %n",rsmd.getColumnCount());
	System.out.println("Fecha agregada con éxito");
	sentencia.close();
	conexion.close();

	}
public static void ejecutarScriptMysQL () {
File scriptFile= new File (". /script/scriptmysql.sql");
System.out.println("\n\nFichero de consulta :"+ scriptFile.getName ()) );
System.out.println("Convirtiendo el fichero a cadena");
BufferedReader entrada = null;
try(entrada = new BufferedReader(new FileReader(scriptFile));
) catch (FileNotFoundException e) {
System.out. printIn ("ERROR NO HAY FILE: " + e.getMessage () ) ;
String linea = null;
StringBuilder stringBuilder = new StringBuilder ();
String salto;
try {
System.getProperty ("line.separator");
while ((linea = entrada. readline ()) != null) 
stringBuilder.append (linea);
stringBuilder.append (salto);
} catch (IOException e) {
System.out.printin ("ERROR de E/S, al operar "+e. getMessage () ) ;
}
String consulta = stringBuilder. toString ();
System.out. printin (consulta);
try {
Class. forName ( "com.mysql. jabc.Driver");
}catch (ClassNotFoundException e) {
System.out. println ("ERROR Driver:" + e.getMessage () ) ;
try {
Connection connmysql = DriverManager.getConnection
("jabc:mysql://localhost/ejemplo?allowMultiQueries=true",
"ejemplo",
"ejemplo");
Statement sents = connmysql.createStatement () ;
int res = sents.executeUpdate(consulta);
System.out.println("Script creado con éxito, res = " + res);
connmysgl.close();
sents.close ();
} catch (SOLException e) {
System.out.println("ERROR AL EJECUTAR EL SCRIPT:");
}
}
}



	//main



