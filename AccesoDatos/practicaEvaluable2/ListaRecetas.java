package practicaEvaluable2;

import java.util.List;

public class ListaRecetas {
    private List<Recetas> recetas;

    public ListaRecetas(List<Recetas> recetas) {
        this.recetas = recetas;
    }

    public void agregarReceta(Recetas receta) {
        recetas.add(receta);
    }

    public List<Recetas> getRecetas() {
        return recetas;
    }
}
