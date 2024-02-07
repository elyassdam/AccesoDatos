package practicaEvaluable2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Main {
    // Atributo de clase static para la base de datos Neodatis
    private static ODB bd;

    public static void main(String[] args) throws ClassNotFoundException {
        // Conectar con la base de datos MySQL
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexionMySQL = null;
        try {
            // Datos de conexión a la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/sanidad";
            String usuario = "sanidad";
            String contraseña = "sanidad";

            // Establecer la conexión
            conexionMySQL = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión a la base de datos MySQL establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos MySQL: " + e.getMessage());
        }
        
            // Abrir la base de datos Neodatis
            bd = ODBFactory.open("SanidadNeodatis.db");
            System.out.println("Base de datos Neodatis abierta.");
        

        // Cerrar la conexión con la base de datos MySQL
            if (conexionMySQL != null) {
                try {
                	conexionMySQL.close();
                    System.out.println("Conexión a la base de datos MySQL cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión con la base de datos MySQL: " + e.getMessage());
                }
            }
        // Continuar con la implementación de otros métodos...

   
        // Cerrar la conexión con la base de datos MySQL
        if (conexionMySQL != null) {
            try {
            	conexionMySQL.close();
                System.out.println("Conexión a la base de datos MySQL cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión con la base de datos MySQL: " + e.getMessage());
            }
        }
    }
    
    public static  void InsertarPaciente(Connection conexion) throws SQLException{
        String consulta = "SELECT * FROM Pacientes";
    	Statement sentencia =(Statement)conexion.createStatement() ;
    	ResultSet resul =sentencia.executeQuery(consulta);
    	while(resul.next()) {
    		if(comprobarPaciente(resul.getInt(1))==false) {
    			Pacientes paciente = new Pacientes(
                        resul.getInt(1),
                        resul.getString("nombre"),
                        resul.getString("apellido"),
                        resul.getDate("fecha_nacimiento"),
                        resul.getString("direccion"),
                        resul.getString("telefono"));
                       bd.store(paciente);
                       System.out.println("Paciente Insertado en Neodatis correctamente");
    		}else {
    			System.out.println("El paciente ya existe en Neodatis");
                        
    			
    			
    		}
    	}
    }
    public static void InsertarMedico(Connection conexion) throws SQLException {
        // Consultar los médicos existentes en la base de datos MySQL
        String consulta = "SELECT * FROM Medicos";
    	Statement sentencia =(Statement)conexion.createStatement() ;

             ResultSet resultSet = sentencia.executeQuery(consulta);
            while (resultSet.next()) {
                // Verificar si el médico ya existe en la base de datos Neodatis
                int idMedico = resultSet.getInt(1); // Suponiendo que la columna 1 es el ID del médico
                if (!comprobarMedico(idMedico)) {
                    // Crear un objeto Medico con la información obtenida de MySQL
                    Medicos medico = new Medicos(
                            idMedico,
                            resultSet.getString("nombre"),
                            resultSet.getString("especialidad")
                    );

                    // Insertar el objeto Medico en Neodatis
                    bd.store(medico);

                    System.out.println("Médico insertado en Neodatis.");
                } else {
                    System.out.println("El médico ya existe en Neodatis.");
                }
            }
        }
    public static void InsertarConsulta(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM Consultas";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(sql);

        while (resul.next()) {
            // Verificar si la consulta ya existe en la base de datos Neodatis
            int idConsulta = resul.getInt(1);
            if (!comprobarConsulta(idConsulta)) {
                // Obtener el ID del paciente y del médico
                int idPaciente = resul.getInt("id_paciente");
                int idMedico = resul.getInt("id_medico");

                // Obtener el paciente correspondiente de Neodatis
                Pacientes paciente = obtenerPaciente(idPaciente);
                Medicos medico = obtenerMedico(idMedico);

                if (paciente != null) {
                    // Crear un objeto Consulta con la información obtenida de MySQL
                    Consultas consulta = new Consulta(
                            idConsulta,
                            paciente, 
                            medico, 
                            resul.getDate("FechaConsulta"),
                            resul.getTime("HoraConsulta"),
                            resul.getString("Sintomas"),
                            resul.getString("Diagnostico"),
                            resul.getString("Tratamiento")
                    );

                    // Insertar el objeto Consulta en Neodatis
                    bd.store(consulta);

                    System.out.println("Consulta insertada en Neodatis.");
                } else {
                    System.out.println("No se encontró el paciente en Neodatis.");
                }
            } else {
                System.out.println("La consulta ya existe en Neodatis.");
            }
        }
    }
    public static void InsertarRecetas(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM Recetas";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(sql);

        while (resul.next()) {
            // Verificar si la consulta ya existe en la base de datos Neodatis
            int idConsulta = resul.getInt(1);
            if (!comprobarConsulta(idConsulta)) {
                // Obtener el ID del paciente y del médico
                int idPaciente = resul.getInt("id_paciente");
              

                // Obtener el paciente correspondiente de Neodatis
                Pacientes paciente = obtenerPaciente(idPaciente);

                if (paciente != null) {
                    // Crear un objeto Consulta con la información obtenida de MySQL
                    Recetas consulta = new Recetas(
                            idConsulta,
                            paciente, 
                            resul.getDate("FechaConsulta"),
                            resul.getTime("HoraConsulta"),
                            resul.getString("Sintomas"),
                            resul.getString("Diagnostico"),
                            resul.getString("Tratamiento")
                    );

                    // Insertar el objeto Consulta en Neodatis
                    bd.store(consulta);

                    System.out.println("Consulta insertada en Neodatis.");
                } else {
                    System.out.println("No se encontró el paciente en Neodatis.");
                }
            } else {
                System.out.println("La consulta ya existe en Neodatis.");
            }
        }

    
    private static boolean comprobarPaciente (int idPaciente) {
    	try{
    		IQuery consulta =new CriteriaQuery(Pacientes.class,Where.equal("idPaciente", idPaciente) );
    	Pacientes obj = (Pacientes)
    	bd.getObjects (consulta).getFirst();
    	return true;
    	}catch (IndexOutOfBoundsException e) {
    	return false; }
}
    private static boolean comprobarConsulta (int idConsulta) {
    	try{
    		IQuery consulta =new CriteriaQuery(Consultas.class,Where.equal("idConsulta", idConsulta) );
    	Pacientes obj = (Pacientes)
    	bd.getObjects (consulta).getFirst();
    	return true;
    	}catch (IndexOutOfBoundsException e) {
    	return false; }
} 
    private static boolean comprobarMedico(int idMedico) {
	try{
		IQuery consulta =new CriteriaQuery(Medicos.class,Where.equal("idMedico", idMedico) );
		Medicos obj = (Medicos)
	bd.getObjects(consulta).getFirst();
	return true;
	}catch (IndexOutOfBoundsException e) {
	return false; }

}   
private static boolean comprobarReceta(int idReceta) {
	try{
		IQuery consulta =new CriteriaQuery(Recetas.class,Where.equal("idReceta", idReceta) );
	Recetas obj = (Recetas)
	bd.getObjects (consulta).getFirst();
	return true;
	}catch (IndexOutOfBoundsException e) {
	return false; }
}
private static boolean comprobarMedicamento(int idMedicamento) {
	try{
		IQuery consulta =new CriteriaQuery(Medicamentos.class,Where.equal("idMedicamento", idMedicamento) );
		Medicamentos obj = (Medicamentos)
	bd.getObjects (consulta).getFirst();
	return true;
	}catch (IndexOutOfBoundsException e) {
	return false; }
}
//Obtenciones 
public static Pacientes obtenerPaciente(int idPaciente) {
      // Realizar una consulta a la base de datos Neodatis para obtener el paciente por su ID
      Objects<Pacientes> pacientes = bd.getObjects(Pacientes.class);
      
      // Recorrer los pacientes encontrados
      while (pacientes.hasNext()) {
          Pacientes paciente = pacientes.next();
          
          if (paciente.getIdPaciente() == idPaciente) {
              return paciente;
          }
         
      }
		return null;


      }
//Obtenciones 
public static Medicos obtenerMedico(int idMedico) {
      // Realizar una consulta a la base de datos Neodatis para obtener el paciente por su ID
      Objects<Medicos> medicos = bd.getObjects(Medicos.class);
      
      // Recorrer los pacientes encontrados
      while (medicos.hasNext()) {
          Medicos medico = medicos.next();
          
          if (medico.getIdMedico() == idMedico) {
              return medico;
          }
         
      }
		return null;


      }
}

