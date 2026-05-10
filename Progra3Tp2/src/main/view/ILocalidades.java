package view;

import java.util.ArrayList;
import java.util.List;

import models.domain.Localidad;

public interface ILocalidades {
	 String getNombre();
	    String getProvincia();
	    String getLatitud();
	    String getLongitud();
	    int    getFilaSeleccionada();
	    
	    void actualizarTabla(List<Localidad> list);
	    void limpiarFormulario();
	    void mostrarError(String mensaje);
}
