package practicaEvaluable2;

import java.sql.Date;
import java.sql.Time;

public class Consultas {
	    private int id;
	    private Pacientes paciente;
	    private Medicos medico;
	    private Date fechaConsulta;
	    private Time horaConsulta;
	    private String sintomas;
	    private String diagnostico;
	    private String tratamiento;

	    public Consultas(int id, Pacientes paciente, Medicos medico, Date fechaConsulta, Time horaConsulta, String sintomas, String diagnostico, String tratamiento) {
	        this.id = id;
	        this.paciente = paciente;
	        this.medico = medico;
	        this.fechaConsulta = fechaConsulta;
	        this.horaConsulta = horaConsulta;
	        this.sintomas = sintomas;
	        this.diagnostico = diagnostico;
	        this.tratamiento = tratamiento;
	    }

	    // Otros métodos de acceso y modificación de atributos
	}


