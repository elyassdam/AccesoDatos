package practicaEvaluable2;

import java.sql.Date;

public class Consultas {
	    private int idConsulta;
	    private int idPaciente;
	    private Pacientes paciente;
	    private Medicos medico;
	    private Date fechaConsulta;
	    private String horaConsulta;
	    private String sintomas;
	    private String diagnostico;
	    public int getIdConsulta() {
			return idConsulta;
		}

		public void setIdConsulta(int idConsulta) {
			this.idConsulta = idConsulta;
		}

		public Pacientes getPaciente() {
			return paciente;
		}

		public void setPaciente(Pacientes paciente) {
			this.paciente = paciente;
		}

		public Medicos getMedico() {
			return medico;
		}

		public void setMedico(Medicos medico) {
			this.medico = medico;
		}

		public Date getFechaConsulta() {
			return fechaConsulta;
		}

		public void setFechaConsulta(Date fechaConsulta) {
			this.fechaConsulta = fechaConsulta;
		}

		public String getHoraConsulta() {
			return horaConsulta;
		}

		public void setHoraConsulta(String horaConsulta) {
			this.horaConsulta = horaConsulta;
		}

		public String getSintomas() {
			return sintomas;
		}

		public void setSintomas(String sintomas) {
			this.sintomas = sintomas;
		}

		public String getDiagnostico() {
			return diagnostico;
		}

		public void setDiagnostico(String diagnostico) {
			this.diagnostico = diagnostico;
		}

		public String getTratamiento() {
			return tratamiento;
		}

		public void setTratamiento(String tratamiento) {
			this.tratamiento = tratamiento;
		}

		private String tratamiento;

	    public Consultas(int idConsulta, int idPaciente,Pacientes paciente, Medicos medico, Date fechaConsulta, String horaConsulta, String sintomas, String diagnostico, String tratamiento) {
	        this.idConsulta = idConsulta;
	        this.idPaciente=idPaciente;
	        this.paciente = paciente;
	        this.medico = medico;
	        this.fechaConsulta = fechaConsulta;
	        this.horaConsulta = horaConsulta;
	        this.sintomas = sintomas;
	        this.diagnostico = diagnostico;
	        this.tratamiento = tratamiento;
	    }

		public int getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}

	    // Otros métodos de acceso y modificación de atributos
	}


