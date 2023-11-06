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
            System.out.println("Ingrese los siguientes valores para la nueva consulta:");
            System.out.print("ID del paciente: ");
            int pacienteID = scanner.nextInt();
            System.out.print("ID del enfermero: ");
            int enfermeroID = scanner.nextInt();
            System.out.print("Fecha de la consulta (YYYY-MM-DD): ");
            String fechaConsulta = scanner.next();
            System.out.print("Hora de la consulta (HH:MM:SS): ");
            String horaConsulta = scanner.next();
            System.out.print("Síntomas: ");
            String sintomas = scanner.next();
            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.next();
            System.out.print("Tratamiento: ");
            String tratamiento = scanner.next();
            System.out.print("ID del hospital: ");
            int hospitalID = scanner.nextInt();
            
            // Crear una sentencia SQL para la inserción con los valores ingresados
            Statement sentencia = conexion.createStatement();
            String sql = "INSERT INTO consultas (PacienteID, EnfermeroID, FechaConsulta, HoraConsulta, Sintomas, Diagnostico, Tratamiento, IDHospital) " +
                         "VALUES (" + pacienteID + ", " + enfermeroID + ", '" + fechaConsulta + "', '" + horaConsulta + "', '" + sintomas + "', '" + diagnostico + "', '" + tratamiento + "', " + hospitalID + ")";
            
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
