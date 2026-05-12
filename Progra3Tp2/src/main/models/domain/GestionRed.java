package models.domain;


import java.util.*;
import logic.MST;
import models.grafo.Arista;
import models.grafo.Grafo;

public class GestionRed {
	
	private ArrayList<Localidad> localidades = new ArrayList<>();
	private Grafo<Localidad> resultado;
    private double costoPorKm;
    private double porcentajeRecargo;
    private double costoFijo;
    private double costoTotal;
    private ServicioJson sj;
    
	public GestionRed() {
		this.costoPorKm = 0;
        this.porcentajeRecargo = 0;
        this.costoFijo = 0;
        this.sj = new ServicioJson();
	}
		
	public void setParametros(double costoPorKm, double porcentajeRecargo, double costoFijo) {
        this.costoPorKm = costoPorKm;
        this.porcentajeRecargo = porcentajeRecargo;
        this.costoFijo = costoFijo;
    }
	
	public void guardarLocalidadJSON(List<Localidad> localidadesGuardadas) {
		sj.guardarLocalidades(localidadesGuardadas);
	}
	
	public void crearRedFibraOptica() {
	    RedFibraOptica red = new RedFibraOptica(localidades);
	    
	    //refactor aca para separar el calculo del costo con agregar aristas
	    red.construirGrafoCompleto(this);
	    
	    MST<Localidad> mst = new MST<>(red);
	    resultado = mst.kruskal();
	    costoTotal = mst.getPesoTotal();
	}


	public double obtenerCostoEntre(Localidad origen, Localidad destino) {
	    double distancia = calcularDistanciaKm(origen, destino);
	    //setParametros pone lo que el usuario ingrese
	    double costoBase = distancia * this.costoPorKm;
	    double recargoPorDistancia = 0;
	    double recargoPorProvincia = 0;

	    if (distancia > 300) {
	        recargoPorDistancia = costoBase * (this.porcentajeRecargo / 100);
	    }

	    if (!origen.getProvincia().equalsIgnoreCase(destino.getProvincia())) {
	        recargoPorProvincia = this.costoFijo;
	    }

	    return costoBase + recargoPorDistancia + recargoPorProvincia;
	}
	
	public void guardarRedEnArchivo() {}
	
	
	 public void agregarLocalidad(Localidad localidad) {
	        localidades.add(localidad); 
	    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }
    
    public double getCostoTotal() {
        return costoTotal;
    }
    
    public Grafo<Localidad> getResultado() {
        return resultado;
    }
    
    public void cargarLocalidadesGuardadasJSON() {
    	List<Localidad> locs = sj.cargarLocalidad();
    	if(locs != null && locs.size() > 0 ) {
    		for(Localidad loc: locs) {
    			localidades.add(loc);
    		}
    	}
    }
  
    public double calcularDistanciaKm(Localidad a, Localidad b) {
        //haversine 
        final int R = 6371;
        double lat1 = Math.toRadians(a.getLatitud());
        double lat2 = Math.toRadians(b.getLatitud());
        double dLat = Math.toRadians(b.getLatitud() - a.getLatitud());
        double dLon = Math.toRadians(b.getLongitud() - a.getLongitud());

        double x = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
        return R * c;
    }

	public double getCostoPorKm() {
		
		return costoPorKm;
	}

	public double getPorcentajeRecargo() {
		
		return porcentajeRecargo;
	}

	public double getCostoFijo() {
		
		return costoFijo;
	}
	
	public List<Tramo> obtenerTramosDelResultado() {
	    List<Tramo> tramos = new ArrayList<>();
	    if (resultado == null) return tramos;

	    for (Arista arista : resultado.getAristas()) {
	        Localidad origen = localidades.get(arista.getOrigen());
	        Localidad destino = localidades.get(arista.getDestino());
	        double costo = arista.getPeso();
	        double kms = calcularDistanciaKm(origen, destino);
	        
	        tramos.add(new Tramo(origen, destino, kms, costo));
	    }
	    return tramos;
	}
    
}
    


