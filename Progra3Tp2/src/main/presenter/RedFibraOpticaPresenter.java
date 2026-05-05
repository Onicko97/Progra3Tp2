package presenter;
import models.domain.*;
import view.*;

public class RedFibraOpticaPresenter {

    private final ILocalidades vistaLocalidades;
    private final GestionRed modelo;
    private final VentanaPrincipal ventana;
    
    public RedFibraOpticaPresenter(ILocalidades vistaLocalidades, GestionRed modelo,VentanaPrincipal ventana) {
        this.vistaLocalidades = vistaLocalidades;
        this.modelo = modelo;
        this.ventana = ventana;
    }

    public void agregarLocalidad() {
        String nombre = vistaLocalidades.getNombre();
        String provincia = vistaLocalidades.getProvincia();
        String lat = vistaLocalidades.getLatitud();
        String lon = vistaLocalidades.getLongitud();

        // valida los parametros
        if (nombre.isEmpty() || provincia.isEmpty() || lat.isEmpty() || lon.isEmpty()) {
            vistaLocalidades.mostrarError("completá todos los campos");
            return;
        }

        double latitud, longitud;
        try {
            latitud  = Double.parseDouble(lat);
            longitud = Double.parseDouble(lon);
        } catch (NumberFormatException e) {
            vistaLocalidades.mostrarError("latitud y longitud deben ser números");
            return;
        }

        modelo.agregarLocalidad(new Localidad(nombre, provincia, latitud, longitud));
        vistaLocalidades.actualizarTabla(modelo.getLocalidades());
        vistaLocalidades.limpiarFormulario();
    }
    
 
//    public void planificar() {
//        if (modelo.getLocalidades().size() < 2) {
//            vistaLocalidades.mostrarError("al menos 2 localidades para crear una red");
//            return;
//        }
//        modelo.planificar();
//        ventana.mostrarResultado();
//    }
}