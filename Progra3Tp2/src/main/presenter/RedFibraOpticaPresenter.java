package presenter;
import java.util.List;

import models.domain.*;
import view.*;

public class RedFibraOpticaPresenter {

    private final ILocalidades vistaLocalidades;
    private final IResultados vistaResultados; 
    private final IParametros vistaParametros;
    private final GestionRed modelo;
    private final VentanaPrincipal ventana;
    
    public RedFibraOpticaPresenter(VentanaPrincipal ventana, GestionRed modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
    	
        this.vistaLocalidades = ventana.getPantallaLocalidades();
        this.vistaResultados = ventana.getPantallaResultados();
        this.vistaParametros = ventana.getPantallaParametros();
        
        this.vistaLocalidades.setPresenter(this);
        this.vistaResultados.setPresenter(this);
        this.vistaParametros.setPresenter(this);
        
        this.cargarLocalidadesGuardadas();
    }
    
    public void iniciar() {
    	ventana.mostrar();
    }

    public void cargarLocalidadesGuardadas() {
    	modelo.cargarLocalidadesGuardadasJSON();
    	List<Localidad> localidades = modelo.getLocalidades();
    	System.out.println(localidades.size());
    	if(localidades.size() > 0) {
    		
    		vistaLocalidades.actualizarTabla(localidades);
    	}
    }
    
    public void agregarLocalidad() {
        String nombre = vistaLocalidades.getNombre();
        String provincia = vistaLocalidades.getProvincia();
        String lat = vistaLocalidades.getLatitud();
        String lon = vistaLocalidades.getLongitud();

        // valida
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
        modelo.guardarLocalidadJSON(modelo.getLocalidades());
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
	    
	    List<Tramo> listaDeTramos = modelo.obtenerTramosDelResultado();
	    vistaResultados.mostrarResultado(
	    		listaDeTramos,                
	            modelo.getLocalidades(),      
	            modelo.getCostoTotal(),      
	            modelo.getCostoPorKm(),       
	            modelo.getPorcentajeRecargo(),
	            modelo.getCostoFijo()
	    );
	    ventana.mostrarResultado();
	}
   
   public double getDistancia(Localidad origen, Localidad destino) {
	   return modelo.calcularDistanciaKm(origen, destino);
   }

   public void guardarParametros() {
	    
		try {
	        
	        double km = Double.parseDouble(vistaParametros.getCostoPorKm());
	        double recargo = Double.parseDouble(vistaParametros.getPorcentajeRecargo());
	        double fijo = Double.parseDouble(vistaParametros.getCostoFijo());

	        modelo.setParametros(km, recargo, fijo);
	        vistaParametros.mostrarExito("Parámetros actualizados correctamente");
	       
	    } catch (NumberFormatException e) {
	        vistaParametros.mostrarError("Por favor, ingresá valores numéricos válidos");
	    }
	}

}