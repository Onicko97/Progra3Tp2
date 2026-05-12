package view;

import java.util.List;

import models.domain.Localidad;
import models.grafo.Grafo;
import presenter.RedFibraOpticaPresenter;

public interface IResultados {
	
	void mostrarResultado(Grafo<Localidad> resultado, List<Localidad> localidades, 
            double costoTotal, double costoKm, double recargo, double costoFijo);
	
	void setPresenter(RedFibraOpticaPresenter presenter);
}
