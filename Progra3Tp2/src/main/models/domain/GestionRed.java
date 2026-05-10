package models.domain;


import java.util.*;

import logic.MST;
import models.grafo.Grafo;

public class GestionRed {
	
	private ArrayList<Localidad> localidades = new ArrayList<>();
	private Grafo<Localidad> resultado;
    private double costoPorKm;
    private double porcentajeRecargo;
    private double costoFijo;
    private double costoTotal;
    
	public GestionRed() {}
		
	public void setParametros(double costoPorKm, double porcentajeRecargo, double costoFijo) {
        this.costoPorKm = costoPorKm;
        this.porcentajeRecargo = porcentajeRecargo;
        this.costoFijo = costoFijo;
    }
	
	//clase principal
	
	public void crearRedFibraOptica() {
       
        RedFibraOptica red = new RedFibraOptica(localidades);
        calcularCostos(red);
        MST<Localidad> mst = new MST<>(red);
        resultado = mst.kruskal();
        costoTotal = mst.getPesoTotal();
    }
//	hay que implementar los parametros para que funcione	
	private void calcularCostos(RedFibraOptica red) {
        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {
                Localidad origen  = localidades.get(i);
                Localidad destino = localidades.get(j);
                double costo = calcularCostoArista(origen, destino);
                red.agregarArista(i, j, costo);
            }
        }
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
// este tambien necesita los parametros
    private double calcularCostoArista(Localidad origen, Localidad destino) {
        double distancia = calcularDistanciaKm(origen, destino);
        double costo = distancia * costoPorKm;

        if (distancia > 300) {
            costo += costo * (porcentajeRecargo / 100);
        }

        if (!origen.getProvincia().equals(destino.getProvincia())) {
            costo += costoFijo;
        }

        return costo;
    }

    private double calcularDistanciaKm(Localidad a, Localidad b) {
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
    
}
    


