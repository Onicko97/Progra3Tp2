package view;

import java.util.ArrayList;
import java.util.List;

import models.domain.Localidad;
import presenter.RedFibraOpticaPresenter;

public interface ILocalidades {
	 String getNombre();
	    String getProvincia();
	    String getLatitud();
	    String getLongitud();
	    int    getFilaSeleccionada();
	    
	    void actualizarTabla(List<Localidad> list);
	    void limpiarFormulario();
	    void mostrarError(String mensaje);
		void setPresenter(RedFibraOpticaPresenter presenter);
}
