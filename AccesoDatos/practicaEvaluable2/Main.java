package practicaEvaluable2;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Main {
    // Atributo de clase static para la base de datos Neodatis
    private static ODB bd;

    public static void main(String[] args) throws ClassNotFoundException {
        // Conectar con la base de datos MySQL
        Connection conexionMySQL = null;
        try {
            // Datos de conexión a la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/sanidad";
            String usuario = "sanidad";
            String contraseña = "sanidad";

            // Establecer la conexión
            conexionMySQL = DriverManager.getConnection(url, usuario, contraseña);

            // Abrir la base de datos Neodatis
            bd = ODBFactory.open("SanidadNeodatis2.db");
            System.out.println("Base de datos Neodatis abierta.");

            
                InsertarPaciente(conexionMySQL);
                InsertarMedicamento(conexionMySQL);
                InsertarReceta(conexionMySQL);
                InsertarMedico(conexionMySQL);
                InsertarConsulta(conexionMySQL);
                llenarListaMedicamentos(conexionMySQL);
               llenarListaRecetas(conexionMySQL);
                llenarSetConsultas(conexionMySQL);
                generarYMostrarInforme();
                     

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos MySQL: " + e.getMessage());
        } finally {
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
     
    }
  //-------------------INSERTACIONES---------------------------------

    public static void InsertarPaciente(Connection conexion) throws SQLException {
        String consulta = "SELECT * FROM Pacientes";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(consulta);
        while (resul.next()) {
            if (!comprobarPaciente(resul.getInt(1))) {
                Pacientes paciente = new Pacientes(
                        resul.getInt(1),
                        resul.getString("Nombre"),
                        resul.getString("Apellido"),
                        resul.getDate("FechaNacimiento"),
                        resul.getString("Direccion"),
                        resul.getString("Telefono"));

                // Obtener recetas asociadas al paciente desde la base de datos MySQL
                List<Recetas> recetas = obtenerRecetasDelPaciente(resul.getInt(1));

                // Obtener consultas asociadas al paciente desde la base de datos MySQL
                Set<Consultas> consultas = obtenerConsultasDePaciente(resul.getInt(1),conexion);

                // Asociar recetas y consultas al paciente
                paciente.setRecetas(recetas);
                paciente.setConsultas(consultas);

                // Almacenar el paciente en Neodatis
                bd.store(paciente);
                System.out.println("Paciente insertado en Neodatis correctamente");
            } else {
                System.out.println("El paciente ya existe en Neodatis");
            }
        }
        bd.commit();
        resul.close();
    }


    public static void InsertarMedico(Connection conexion) throws SQLException {
        String consulta = "SELECT * FROM Medicos";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(consulta);
        while (resul.next()) {
            if (!comprobarMedico(resul.getInt(1))) {
                Medicos medico = new Medicos(
                        resul.getInt(1),
                        resul.getString("Nombre"),
                        resul.getString("Especialidad")
                );
                bd.store(medico);
                System.out.println("Médico insertado en Neodatis correctamente");
            } else {
                System.out.println("El médico ya existe en Neodatis");
            }
        }
        bd.commit();
        resul.close();
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
                int idPaciente = resul.getInt("idPaciente");
                int idMedico = resul.getInt("idMedico");

                // Obtener el paciente correspondiente de Neodatis
                Pacientes paciente = obtenerPaciente(idPaciente);
                Medicos medico = obtenerMedico(idMedico);

                if (paciente != null && medico != null) {
                    // Convertir java.sql.Time a String
                    String horaConsulta = resul.getTime("HoraConsulta").toString();

                    // Crear un objeto Consulta con la información obtenida de MySQL
                    Consultas consulta = new Consultas(
                        idConsulta,
                        idPaciente,
                        paciente,
                        medico,
                        resul.getDate("FechaConsulta"),
                        horaConsulta, // Usar la cadena de la hora en lugar del objeto Time
                        resul.getString("Sintomas"),
                        resul.getString("Diagnostico"),
                        resul.getString("Tratamiento")
                    );

                    // Insertar el objeto Consulta en Neodatis
                    bd.store(consulta);
                    System.out.println("Consulta insertada en Neodatis.");
                } else {
                    System.out.println("No se encontró el paciente o el médico en Neodatis para la consulta con ID: " + idConsulta);
                }
            } else {
                System.out.println("La consulta con ID " + idConsulta + " ya existe en Neodatis.");
            }
        }
    }

            
        

    public static void InsertarMedicamento(Connection conexion) throws SQLException {
        String consulta = "SELECT * FROM Medicamentos";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(consulta);
        while (resul.next()) {
            if (!comprobarMedicamento(resul.getInt(1))) {
                Medicamentos medicamento = new Medicamentos(
                        resul.getInt(1),
                        resul.getString("NombreMedicamento"),
                        resul.getString("descripcion")
                );
                bd.store(medicamento);
                System.out.println("Medicamento insertado en Neodatis correctamente");
            } else {
                System.out.println("El medicamento ya existe en Neodatis");
            }
        }
        bd.commit();
        resul.close();
    }
    public static void InsertarReceta(Connection conexion) {
        String sql = "SELECT * FROM Recetas";
        try (Statement sentencia = conexion.createStatement();
             ResultSet resul = sentencia.executeQuery(sql)) {
            
            while (resul.next()) {
                int idReceta = resul.getInt("idReceta");
                if (!comprobarReceta(idReceta)) {
                    int idPaciente = resul.getInt("idPaciente");
                    String fecha = resul.getString("fecha");
                    String nota = resul.getString("nota");
                    List<Integer> medicamentosIds = obtenerMedicamentosDeReceta(idReceta, conexion);
                    
                    Recetas receta = new Recetas(
                        idReceta,
                        idPaciente,
                        fecha,
                        nota,
                        medicamentosIds
                    );

                    // Insertar la receta en la base de datos Neodatis
                    bd.store(receta);
                    bd.commit();
                    System.out.println("Receta insertada en Neodatis correctamente");
                } else {
                    System.out.println("La receta ya existe en Neodatis");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar receta: " + e.getMessage());
        }
    }

//-------------------COMPROBACIONES---------------------------------
    private static boolean comprobarPaciente(int idPaciente) {
        try {
            IQuery consulta = new CriteriaQuery(Pacientes.class, Where.equal("idPaciente", idPaciente));
            Pacientes obj=(Pacientes)
            		bd.getObjects(consulta).getFirst();
            return true;        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarMedico(int idMedico) {
        try {
            IQuery consulta = new CriteriaQuery(Medicos.class, Where.equal("idMedico", idMedico));
            Medicos obj=(Medicos)
            		bd.getObjects(consulta).getFirst();
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarConsulta(int idConsulta) {
        try {
            IQuery consulta = new CriteriaQuery(Consultas.class, Where.equal("idConsulta", idConsulta));
            Consultas obj=(Consultas)
            		bd.getObjects(consulta).getFirst();
            return true;        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarMedicamento(int idMedicamento) {
        try {
            IQuery consulta = new CriteriaQuery(Medicamentos.class, Where.equal("idMedicamento", idMedicamento));
            Medicamentos obj=(Medicamentos)
            		bd.getObjects(consulta).getFirst();
            return true;
            } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarReceta(int idReceta) {
        try {
            IQuery consulta = new CriteriaQuery(Recetas.class, Where.equal("idReceta", idReceta));
            Recetas obj=(Recetas)
            		bd.getObjects(consulta).getFirst();
            return true;        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static Pacientes obtenerPaciente(int idPaciente) {
        try {
            Objects<Pacientes> pacientes = bd.getObjects(Pacientes.class);
            while (pacientes.hasNext()) {
                Pacientes paciente = pacientes.next();
                if (paciente.getIdPaciente() == idPaciente) {
                    System.out.println("Paciente encontrado en Neodatis: " + paciente);
                    return paciente;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al buscar paciente en Neodatis: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Paciente no encontrado en Neodatis para el ID: " + idPaciente);
        return null;
    }


    public static Medicos obtenerMedico(int idMedico) {
        Objects<Medicos> medicos = bd.getObjects(Medicos.class);
        while (medicos.hasNext()) {
            Medicos medico = medicos.next();
            if (medico.getIdMedico() == idMedico) {
                return medico;
            }
        }
        return null;
    }
    public static List<Recetas> obtenerRecetasDelPaciente(int idPaciente) throws SQLException {
        List<Recetas> recetas = new ArrayList<>();
        Connection conexion = null;
        try {
            // Establecer conexión con la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/sanidad";
            String usuario = "sanidad";
            String contraseña = "sanidad";
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Consultar las recetas asociadas al paciente
            String consulta = "SELECT * FROM Recetas WHERE idPaciente = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idPaciente);
            ResultSet resultado = statement.executeQuery();

            // Iterar sobre los resultados y crear objetos Recetas
            while (resultado.next()) {
                int idReceta = resultado.getInt("idReceta");

                String fecha = resultado.getString("fecha");
                String nota = resultado.getString("nota");
                List<Integer> medicamentosIds = obtenerMedicamentosDeReceta(idReceta,conexion);
                Recetas receta = new Recetas(idReceta, idPaciente,fecha, nota, medicamentosIds);
                recetas.add(receta);
            }
        } finally {
            // Cerrar la conexión
            if (conexion != null) {
                conexion.close();
            }
        }
        return recetas;
    }
    public static List<Integer> obtenerMedicamentosDeReceta(int idReceta, Connection conexion) throws SQLException {
        List<Integer> medicamentosIds = new ArrayList<>();
        try {
            // Consultar los medicamentos asociados a la receta
            String consulta = "SELECT idMedicamento FROM Medicamentos WHERE idReceta = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idReceta);
            ResultSet resultado = statement.executeQuery();

            // Iterar sobre los resultados y obtener los IDs de los medicamentos
            while (resultado.next()) {
                int idMedicamento = resultado.getInt("idMedicamento");
                medicamentosIds.add(idMedicamento);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener medicamentos de la receta: " + e.getMessage());
        }
        return medicamentosIds;
    }
    public static Set<Consultas> obtenerConsultasDePaciente(int idPaciente, Connection conexion) throws SQLException {
        Set<Consultas> consultas = new HashSet<>();
        try {
            // Consultar las consultas asociadas al paciente
            String consulta = "SELECT * FROM Consultas WHERE idPaciente = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idPaciente);
            ResultSet resultado = statement.executeQuery();

            // Iterar sobre los resultados y construir objetos Consultas
            while (resultado.next()) {
                int idConsulta = resultado.getInt("idConsulta");
                int idMedico = resultado.getInt("idMedico");
                String horaConsulta = resultado.getTime("HoraConsulta").toString();

                // Obtener el paciente correspondiente de Neodatis
                Pacientes paciente = obtenerPaciente(idPaciente);

                // Obtener el médico correspondiente de Neodatis
                Medicos medico = obtenerMedico(idMedico);

                // Verificar si el paciente y el médico no son nulos
                if (paciente != null && medico != null) {
                    Consultas consultaObj = new Consultas(
                        idConsulta,
                        idPaciente,
                        paciente,
                        medico,
                        resultado.getDate("FechaConsulta"),
                        horaConsulta,
                        resultado.getString("Sintomas"),
                        resultado.getString("Diagnostico"),
                        resultado.getString("Tratamiento")
                    );
                    // Agregar la consulta al conjunto
                    consultas.add(consultaObj);
                } else {
                    System.out.println("Paciente o médico asociado a la consulta no encontrado en Neodatis.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener consultas del paciente: " + e.getMessage());
        }
        return consultas;
    }

  //-------------------LLENARLISTAS---------------------------------


    private static void llenarListaMedicamentos(Connection conexion) throws SQLException {
        Objects<Medicamentos> objectMedicamento = bd.getObjects(Medicamentos.class);
        List<Medicamentos> listaMedicamentos = new ArrayList<>();

        while (objectMedicamento.hasNext()) {
            Medicamentos med = objectMedicamento.next();
            listaMedicamentos.add(med);
        }

        ListaMedicamentos listaMedicamentosWrapper = new ListaMedicamentos(listaMedicamentos);
        bd.store(listaMedicamentosWrapper);
        bd.commit();
    }

    public static void llenarSetConsultas(Connection conexion) throws SQLException {
        Objects<Consultas> objectConsul = bd.getObjects(Consultas.class);
        Set<Consultas> setConsultas = new HashSet<>();
        
        while (objectConsul.hasNext()) {
            Consultas cons = objectConsul.next();
            
            // Obtener el ID del paciente asociado a la consulta
            int idPaciente = cons.getIdPaciente();
            
            // Obtener el paciente asociado a la consulta y asegurarnos de que no sea nulo
            Pacientes paciente = obtenerPaciente(idPaciente);
            
            if (paciente != null) {
                // Agregar la consulta al conjunto si el paciente no es nulo
                setConsultas.add(cons);
            } else {
                System.out.println("Paciente asociado a la consulta es nulo.");
            }
        }

        // Almacenar el conjunto de consultas en Neodatis
        SetConsultas setConsultasWrapper = new SetConsultas(setConsultas);
        bd.store(setConsultasWrapper);
        bd.commit();
    }


    private static void llenarListaRecetas(Connection conexion) throws SQLException {
        Objects<Recetas> objectRec = bd.getObjects(Recetas.class);
        List<Recetas> listaRecetas = new ArrayList<>();

        System.out.println("Recorriendo objetos Recetas en Neodatis...");
        while (objectRec.hasNext()) {
            Recetas rec = objectRec.next();
            System.out.println("Receta obtenida: " + rec.getIdReceta());
int idPaciente=rec.getIdPaciente();
            // Obtener el paciente asociado a la receta y asegurarnos de que esté en Neodatis
            Pacientes paciente = obtenerPaciente(idPaciente);
            if (paciente != null && comprobarPaciente(paciente.getIdPaciente())&&comprobarReceta(rec.getIdReceta())) {
                System.out.println("Paciente asociado a la receta encontrado en Neodatis.");
                listaRecetas.add(rec);
            } else {
                System.out.println("Paciente asociado a la receta no encontrado en Neodatis.");
            }
        }

        // Imprimir el número de recetas obtenidas para verificar
        System.out.println("Número de recetas obtenidas: " + listaRecetas.size());

        // Si se obtuvieron recetas, almacenarlas en Neodatis
        if (!listaRecetas.isEmpty()) {
            System.out.println("Creando objeto ListaRecetas...");
            ListaRecetas listaRecetasWrapper = new ListaRecetas(listaRecetas);
            System.out.println("Almacenando ListaRecetas en Neodatis...");
            bd.store(listaRecetasWrapper);
            bd.commit();

            System.out.println("Lista de recetas llenada y almacenada en Neodatis correctamente.");
        } else {
            System.out.println("No se obtuvieron recetas para almacenar en Neodatis.");
        }
    }

   

    public static void generarYMostrarInforme() {
        Connection conexionMySQL = null;
        int totalConsultas = 0;
        int totalPacientes = 0;
        int totalRecetas = 0;
        int totalMedicamentos = 0;
        int totalMedicos = 0;
        
        try {
            // Conectar con la base de datos MySQL
            String url = "jdbc:mysql://localhost:3306/sanidad";
            String usuario = "sanidad";
            String contraseña = "sanidad";
            conexionMySQL = DriverManager.getConnection(url, usuario, contraseña);

            Statement statement = conexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Consultas JOIN Pacientes ON Consultas.idPaciente = Pacientes.idPaciente JOIN Medicos ON Consultas.idMedico = Medicos.idMedico");
            System.out.println("\n=========================================================================================================================================================================================================================================================================================================");
            System.out.println("\033[1m                                Informe de Consultas                                 \033[0m");
            System.out.println("===========================================================================================================================================================================================================================================================================================================");



            System.out.println("| ID Consulta | Fecha Consulta | Hora Consulta |       Síntomas        |      Diagnóstico      |        Tratamiento         | ID Paciente | Nombre Paciente | Apellido Paciente | Fecha Nacimiento Paciente |       Dirección Paciente       | Teléfono Paciente | ID Médico | Nombre Médico | Especialidad Médico |");
            System.out.println("|-------------|----------------|---------------|------------------------|------------------------|----------------------------|-------------|-----------------|-------------------|---------------------------|--------------------------------|-------------------|-----------|---------------|---------------------|");

            while (resultSet.next()) {
                totalConsultas++;
                totalPacientes++;
                totalRecetas++;
                totalMedicamentos++;
                totalMedicos++;

                // Resto del código para imprimir las filas
                System.out.printf("| %11s | %14s | %13s | %22s | %22s | %27s | %11s | %15s | %17s | %25s | %30s | %17s | %9s | %13s | %20s |\n",
                        resultSet.getString("idConsulta"),
                        resultSet.getString("fechaConsulta"),
                        resultSet.getString("horaConsulta"),
                        resultSet.getString("sintomas"),
                        resultSet.getString("diagnostico"),
                        resultSet.getString("tratamiento"),
                        resultSet.getString("idPaciente"),
                        resultSet.getString("Nombre"),
                        resultSet.getString("Apellido"),
                        resultSet.getString("fechaNacimiento"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("idMedico"),
                        resultSet.getString("Nombre"),
                        resultSet.getString("especialidad")
                );
            }

            System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
          
            System.out.println("Total Consultas: " + totalConsultas);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos MySQL: " + e.getMessage());
        } finally {
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
    }


        }