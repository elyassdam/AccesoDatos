package practicaEvaluable2;

import java.util.ArrayList;
import java.util.List;

public class Recetas {
    private int idReceta;
    private int idPaciente; // Nuevo atributo para el ID del paciente
    private String fecha;
    private String nota;
    private List<Integer> medicamentosIds;
    private Pacientes paciente;

    public Recetas(int idReceta, int idPaciente, String fecha, String nota, List<Integer> medicamentosIds) {
        this.idReceta = idReceta;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.nota = nota;
        this.medicamentosIds = new ArrayList<>(medicamentosIds);
    }

    // Otros métodos de acceso y modificación de atributos

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public List<Integer> getMedicamentosIds() {
        return medicamentosIds;
    }

    public void setMedicamentosIds(List<Integer> medicamentosIds) {
        this.medicamentosIds = new ArrayList<>(medicamentosIds);
    }
    
    public void agregarIdMedicamento(int idMedicamento) {
        medicamentosIds.add(idMedicamento);
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }
}
