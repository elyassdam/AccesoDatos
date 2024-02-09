package practicaEvaluable2;

import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;
import org.neodatis.odb.Objects;

public class Cliente {
public static void main(String[] args) {
	ODB odb =null;
	ODBServer server=null;
	try {
		server = ODBFactory.openServer(8000);
		server.addBase("base","SanidadNeodatis2.db");
		server.startServer(true);
		odb=server.openClient("base");
		VisualizarDatos(odb);
	}
	finally {
		if(odb!=null) {
			server.close();
		}
	}
}
	static void VisualizarDatos(ODB odb) {
		//visualiza recetass 
		Objects<Recetas> recetasObjects = odb.getObjects(Recetas.class);
		System.out.println("Recetas:");
		int i = 1;
		while (recetasObjects.hasNext()) {
		    Recetas receta = recetasObjects.next();
		    System.out.printf("%d. ID Receta: %d, ID Paciente: %d, Fecha: %s, Nota: %s",
		        i++, receta.getIdReceta(), receta.getIdPaciente(), receta.getFecha(), receta.getNota());
		    
		    List<Integer> medicamentos = receta.getMedicamentosIds();
		    if (medicamentos.isEmpty()) {
		        System.out.println(", Medicamentos: [Ninguno asignado]");
		    } else {
		        System.out.print(", Medicamentos: ");
		        for (int idMedicamento : medicamentos) {
		            System.out.print(idMedicamento + " ");
		        }
		        System.out.println();
		    }
		}


	    // Visualizar Pacientes
	    Objects<Pacientes> pacientesObjects = odb.getObjects(Pacientes.class);
	    System.out.println("Pacientes:");
	    int i2 = 1;
	    while (pacientesObjects.hasNext()) {
	        Pacientes paciente = pacientesObjects.next();
	        System.out.printf("%d. ID: %d, Nombre: %s, Apellido: %s, Fecha de Nacimiento: %s, Dirección: %s, Teléfono: %s%n", 
	            i2++, paciente.getIdPaciente(), paciente.getNombre(), paciente.getApellido(), paciente.getFechaNacimiento(), paciente.getDireccion(), paciente.getTelefono());
	    }

	    // Visualizar Consultas
	 // Visualizar Consultas
	    Objects<Consultas> consultasObjects = odb.getObjects(Consultas.class);
	    System.out.println("\nConsultas:");
	    int i3 = 1;
	    while (consultasObjects.hasNext()) {
	        Consultas consulta = consultasObjects.next();
	        Pacientes paciente = consulta.getPaciente();
	        Medicos medico=consulta.getMedico();
	        if (paciente != null && medico != null) {
	            System.out.printf("%d. ID: %d, ID Paciente: %d, ID Médico: %d, Fecha Consulta: %s, Hora Consulta: %s, Síntomas: %s, Diagnóstico: %s, Tratamiento: %s%n", 
	                i3++, consulta.getIdConsulta(), paciente.getIdPaciente(), consulta.getMedico().getIdMedico(), consulta.getFechaConsulta(), consulta.getHoraConsulta(), consulta.getSintomas(), consulta.getDiagnostico(), consulta.getTratamiento());
	        } else {
	            System.out.printf("%d. ID: %d, Paciente: [No asignado], ID Médico: %d, Fecha Consulta: %s, Hora Consulta: %s, Síntomas: %s, Diagnóstico: %s, Tratamiento: %s%n", 
	                i3++, consulta.getIdConsulta(), consulta.getMedico().getIdMedico(), consulta.getFechaConsulta(), consulta.getHoraConsulta(), consulta.getSintomas(), consulta.getDiagnostico(), consulta.getTratamiento());
	        }
	    }

	    // Visualizar Medicamentos
	    Objects<Medicamentos> medicamentosObjects = odb.getObjects(Medicamentos.class);
	    System.out.println("\nMedicamentos:");
	    int i4 = 1;
	    while (medicamentosObjects.hasNext()) {
	        Medicamentos medicamento = medicamentosObjects.next();
	        System.out.printf("%d. ID: %d, Nombre: %s, Descripción: %s%n", i4++, medicamento.getIdMedicamento(), medicamento.getNombre(), medicamento.getDescripcion());
	    }

	    // Visualizar Médicos
	    Objects<Medicos> medicosObjects = odb.getObjects(Medicos.class);
	    System.out.println("\nMédicos:");
	    int i5 = 1;
	    while (medicosObjects.hasNext()) {
	        Medicos medico = medicosObjects.next();
	        System.out.printf("%d. ID: %d, Nombre: %s, Especialidad: %s%n", i5++, medico.getIdMedico(), medico.getNombre(), medico.getEspecialidad());
	    }
	}

}
	
	
