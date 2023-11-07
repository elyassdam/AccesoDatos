package practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertarDML {
    public static void main(String[] args) {
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sanidad", "sanidad", "sanidad");

    	    Scanner scanner = new Scanner(System.in);

    	    // Solicitar al usuario que ingrese los valores
    	    System.out.println("Ingrese los siguientes valores para el nuevo paciente:");
    	    System.out.print("Nombre: ");
    	    String nombre = scanner.nextLine();
    	    System.out.print("Apellido: ");
    	    String apellido = scanner.nextLine();
    	    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
    	    String fechaNacimiento = scanner.next();
    	    scanner.nextLine();
    	    System.out.print("Dirección: ");
    	    String direccion = scanner.nextLine();
    	    System.out.print("Teléfono: ");
    	    String telefono = scanner.next();
    	    System.out.print("ID del hospital: ");
    	    int hospitalID = scanner.nextInt();

    	    // Crear una sentencia SQL para la inserción con los valores ingresados
    	    Statement sentencia = conexion.createStatement();
    	    String sql = "INSERT INTO pacientes VALUES ('" + nombre + "', '" + apellido + "', '" + fechaNacimiento + "', '" + direccion + "', '" + telefono + "', " + hospitalID + ")";

    	    // Ejecutar la sentencia de inserción
    	    int filasAfectadas = sentencia.executeUpdate(sql);

    	    if (filasAfectadas > 0) {
    	        System.out.println("Inserción exitosa. Filas afectadas: " + filasAfectadas);
    	    } else {
    	        System.out.println("No se insertaron filas.");
    	    }

    	    sentencia.close();
    	    conexion.close();
    	    scanner.close();
    	} catch (ClassNotFoundException en) {
    	    en.printStackTrace();
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

}
}
