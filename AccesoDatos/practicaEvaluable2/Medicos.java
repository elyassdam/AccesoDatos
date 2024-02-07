package practicaEvaluable2;

public class Medicos {
	    private int idMedico;
	    private String nombre;
	    private String especialidad;

	    public Medicos(int idMedico, String nombre, String especialidad) {
	        this.idMedico = idMedico;
	        this.nombre = nombre;
	        this.especialidad = especialidad;
	    }

		public int getIdMedico() {
			return idMedico;
		}

		public void setIdMedico(int idMedico) {
			this.idMedico = idMedico;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getEspecialidad() {
			return especialidad;
		}

		public void setEspecialidad(String especialidad) {
			this.especialidad = especialidad;
		}

	    // Otros métodos de acceso y modificación de atributos
	}


