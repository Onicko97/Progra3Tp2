package view;

import java.util.List;

import models.domain.Localidad;
import models.domain.Tramo;
import models.grafo.Grafo;
import presenter.RedFibraOpticaPresenter;

public interface IResultados {
	
	    void mostrarResultado(List<Tramo> tramos, List<Localidad> todas, 
	            double total, double km, double rec, double fijo) ;
	    void setPresenter(RedFibraOpticaPresenter presenter);

}
