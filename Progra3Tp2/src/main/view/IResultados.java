package view;

import java.util.List;

import models.domain.Localidad;
import models.grafo.Grafo;

public interface IResultados {
    void mostrarResultado(Grafo<Localidad> resultado, List<Localidad> localidades, double costoTotal);
}
