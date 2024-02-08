package practicaEvaluable2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            bd = ODBFactory.open("SanidadNeodatis.db");
            System.out.println("Base de datos Neodatis abierta.");

            // Insertar datos en Neodatis
            /*InsertarMedicamento(conexionMySQL);
            InsertarReceta(conexionMySQL);*/
            InsertarPaciente(conexionMySQL);
            InsertarMedico(conexionMySQL);
            InsertarConsulta(conexionMySQL);

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
        String consulta = "SELECT * FROM Consultas";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(consulta);
        while (resul.next()) {
            if (!comprobarConsulta(resul.getInt(1))) {
                Pacientes paciente = obtenerPaciente(resul.getInt("IDPaciente"));
                Medicos medico = obtenerMedico(resul.getInt("IDMedico"));
                Consultas consultaObj = new Consultas(
                        resul.getInt(1),
                        paciente,
                        medico,
                        resul.getDate("FechaConsulta"),
                        resul.getTime("Horaconsulta"),
                        resul.getString("Sintomas"),
                        resul.getString("Diagnostico"),
                        resul.getString("Tratamiento")
                );
                bd.store(consultaObj);
                System.out.println("Consulta insertada en Neodatis correctamente");
            } else {
                System.out.println("La consulta ya existe en Neodatis");
            }
        }
        bd.commit();
        resul.close();
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

    public static void InsertarReceta(Connection conexion) throws SQLException {
        int cont = 0;
        String sql = "SELECT * FROM Recetas";
        Statement sentencia = conexion.createStatement();
        ResultSet resul = sentencia.executeQuery(sql);

        while (resul.next()) {
            List<Medicamentos> medicamentos = new ArrayList<>();
            
            // Verificar si la receta ya existe en la base de datos Neodatis
            int idReceta = resul.getInt(1);
            if (!comprobarReceta(idReceta)) {
                // Obtener los medicamentos asociados a la receta
                String sqlMedicamentos = "SELECT * FROM Medicamentos WHERE idReceta = " + idReceta;
                Statement sentenciaMedicamentos = conexion.createStatement();
                ResultSet resulMedicamentos = sentenciaMedicamentos.executeQuery(sqlMedicamentos);
                
                while (resulMedicamentos.next()) {
                    Medicamentos medicamento = new Medicamentos(
                            resulMedicamentos.getInt("MedicamentoID"),
                            resulMedicamentos.getString("NombreMedicamento"),
                            resulMedicamentos.getString("descripcion")
                    );
                    medicamentos.add(medicamento);
                }

                // Crear un objeto Receta con la información obtenida de MySQL
                Recetas receta = new Recetas(
                        cont++,
                        resul.getString("fecha"),
                        resul.getString("nota")
                );
                
                // Agregar los medicamentos a la receta
                for (Medicamentos med : medicamentos) {
                    receta.agregarMedicamento(med);
                }

                // Insertar el objeto Receta en Neodatis
                bd.store(receta);

                System.out.println("Receta insertada en Neodatis.");
            } else {
                System.out.println("La receta ya existe en Neodatis.");
            }
        }
        bd.commit();
        resul.close();
    }


    private static boolean comprobarPaciente(int idPaciente) {
        try {
            IQuery consulta = new CriteriaQuery(Pacientes.class, Where.equal("idPaciente", idPaciente));
            return bd.getObjects(consulta).hasNext();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarMedico(int idMedico) {
        try {
            IQuery consulta = new CriteriaQuery(Medicos.class, Where.equal("idMedico", idMedico));
            return bd.getObjects(consulta).hasNext();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarConsulta(int idConsulta) {
        try {
            IQuery consulta = new CriteriaQuery(Consultas.class, Where.equal("ConsultaID", idConsulta));
            return bd.getObjects(consulta).hasNext();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarMedicamento(int idMedicamento) {
        try {
            IQuery consulta = new CriteriaQuery(Medicamentos.class, Where.equal("idMedicamento", idMedicamento));
            return bd.getObjects(consulta).hasNext();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean comprobarReceta(int idReceta) {
        try {
            IQuery consulta = new CriteriaQuery(Recetas.class, Where.equal("idReceta", idReceta));
            return bd.getObjects(consulta).hasNext();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static Pacientes obtenerPaciente(int idPaciente) {
        Objects<Pacientes> pacientes = bd.getObjects(Pacientes.class);
        while (pacientes.hasNext()) {
            Pacientes paciente = pacientes.next();
            if (paciente.getIdPaciente() == idPaciente) {
                return paciente;
            }
        }
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
}
