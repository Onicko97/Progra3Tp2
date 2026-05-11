package presenter;
import models.domain.*;
import view.*;

public class RedFibraOpticaPresenter {

    private final ILocalidades vistaLocalidades;
    private final IResultados vistaResultados; 
    private final GestionRed modelo;
    private final VentanaPrincipal ventana;
    
    
    public RedFibraOpticaPresenter(ILocalidades vistaLocalidades,IResultados vistaResultados, GestionRed modelo,VentanaPrincipal ventana) {
        this.vistaLocalidades = vistaLocalidades;
        this.vistaResultados= vistaResultados;
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
    
 public void configuracionRed() {
	 ventana.mostrarConfiguracionRed(); 
 }
 
 public void volverAtras() {
	 ventana.mostrarLocalidades();
 }
    
   public void planificar() {
    if (modelo.getLocalidades().size() < 2) {
        vistaLocalidades.mostrarError("Necesitás al menos 2 localidades.");
        return;
    }
      modelo.crearRedFibraOptica();
    vistaResultados.mostrarResultado(
        modelo.getResultado(),
        modelo.getLocalidades(),
        modelo.getCostoTotal()
    );
    ventana.mostrarResultado();
   }
   
   public double getDistancia(Localidad origen, Localidad destino) {
	   return modelo.calcularDistanciaKm(origen, destino);
   }

}