package practicaEvaluable2;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Recetas {
    private int id;
    private String fecha;
    private String nota;
    private List<Medicamentos> medicamentos;

    public Recetas(int id, String fecha, String nota,List<Medicamentos> medicamentos) {
        this.id = id;
        this.fecha = fecha;
        this.nota = nota;
        this.medicamentos = new ArrayList<>();
    }

   
	public Recetas(int id, String fecha, String nota) {

	}


	// Métodos para agregar y obtener medicamentos
    public void agregarMedicamento(Medicamentos medicamento) {
        medicamentos.add(medicamento);
    }

    public List<Medicamentos> getMedicamentos() {
        return medicamentos;
    }

    // Otros métodos de acceso y modificación de atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
