package practicaEvaluable2;

import java.util.List;
import java.util.ArrayList;

public class ListaMedicamentos {
    private List<Medicamentos> medicamentos;

    public ListaMedicamentos(List<Medicamentos> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void agregarMedicamento(Medicamentos medicamento) {
        medicamentos.add(medicamento);
    }

    public List<Medicamentos> getMedicamentos() {
        return medicamentos;
    }
}
