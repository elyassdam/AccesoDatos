package practicaEvaluable2;

import java.util.ArrayList;
import java.util.List;

public class Recetas {
	    private int id;
	    private Pacientes paciente;
	    private List<Medicamentos> medicamentos;

	    public Recetas(int id, Pacientes paciente) {
	        this.id = id;
	        this.paciente = paciente;
	        this.medicamentos = new ArrayList<>();
	    }

	    // Métodos para agregar y obtener medicamentos
	    public void agregarMedicamento(Medicamentos medicamento) {
	        medicamentos.add(medicamento);
	    }

	    public List<Medicamentos> getMedicamentos() {
	        return medicamentos;
	    }

	    // Otros métodos de acceso y modificación de atributos
	}

