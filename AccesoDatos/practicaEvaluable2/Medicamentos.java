package practicaEvaluable2;

public class Medicamentos {
	    private int idMedicamento;
	    private String nombre;
	    private String descripcion;

	    public Medicamentos(int idMedicamento, String nombre, String descripcion) {
	        this.idMedicamento = idMedicamento;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	    }

		public int getIdMedicamento() {
			return idMedicamento;
		}

		public void setIdMedicamento(int idMedicamento) {
			this.idMedicamento = idMedicamento;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
}
