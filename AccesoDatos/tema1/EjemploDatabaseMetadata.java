package tema1;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;
public class EjemploDatabaseMetadata {
public static void main (String[] args) {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
Connection conexion = DriverManager.getConnection
("jdbc:mysql://localhost/ejemplo","ejemplo","ejemplo") ;
DatabaseMetaData dbmd =conexion.getMetaData() ;
ResultSet resul = null;
String nombre= dbmd. getDatabaseProductName () ;
String driver=dbmd. getDriverName () ;
String url=dbmd.getURL();
String usuario = dbmd.getUserName ( );
System.out.println ( "INFORMACIÓN SOBRE LA BASE DE DATOS:") ;
System.out.println("===================================");
System.out.printf("Nombre: %s %n", nombre );
System.out.printf("Driver: %s %n", driver );
System.out.printf("URL: %s in", url);
System.out.printf("Usuario: %s in", usuario ) ;
//obtener información de las tablas y vistas que hay
resul = dbmd. getTables (null,"ejemplo", null, null);
while (resul.next () ) {
String catalogo = resul. getString (1) ; //columna 1
String esquema = resul.getString (2) ; //columna2
String tabla = resul.getString (3);
//columna 3
String tipo = resul.getString (4);
//columna 4
System.out.printf("%s - Catalogo: %s, Esquema: %s,Nombre: %s %n", tipo, catalogo, esquema, tabla);
}
System.out.println("COLUMNAS TABLA DEPARTAMENTOS:") ;
System.out.println("=================----==========");
ResultSet columnas=null;
columnas = dbmd.getColumns(null,"ejemplo", "departamentos", null);
while (columnas.next ()) {
String nombCol = columnas.getString("COLUMN_NAME"); //getString (4)
String tipoCol = columnas.getString("TYPE_NAME");
//getString (6)
String tamCol = columnas.getString("COLUMN_SIZE");
//getString (7)
String nula = columnas.getString("IS_NULLABLE");
//getstring (18)
System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s,¿Puede ser Nula:? %s \n", nombCol, tipoCol, tamCol, nula);
}
ResultSet pk = dbmd. getPrimaryKeys (null, "ejemplo","departamentos");
String pkDep="",separador="";
while
(pk.next () ) {
pkDep = pkDep + separador+pk.getString ("COLUMN_NAME"); //getString (4)
separador="+";
System.out.println("Clave Primaria:"+ pkDep);
}
 ResultSet fk = dbmd.getExportedKeys(null, "ejemplo", "departamentos");
while (fk.next()) {
String fk_name = fk.getString ("FKCOLUMN_NAME") ;
String pk_name = fk.getString("PKCOLUMN_NAME") ;
String pk_tablename = fk.getString ("PKTABLE_NAME") ;
String fk_tablename = fk.getString ("FKTABLE_NAME");
System.out.printf("Tabla PK: %s, Clave Primaria: %s %n",pk_tablename,pk_name);
System.out.printf("Tabla FK: %s, Clave Ajena: %s %n",fk_tablename,fk_name);
}

conexion.close (); 
}//Cerrar conexión
catch (ClassNotFoundException en) {en.printStackTrace () ;}
catch (SQLException e) {e.printStackTrace () ; }
}
 //fin de main
}//fin de la clase
