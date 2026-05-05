package models.domain;


import java.util.*;

public class GestionRed {
	
	private RedFibraOptica red;
    private double costoPorKm;
    private double porcentajeRecargo;
    private double costoFijo;
    
	public GestionRed() {
		 red = new RedFibraOptica(new ArrayList<>());
    }
	public void setParametros(double costoPorKm, double porcentajeRecargo, double costoFijo) {
        this.costoPorKm = costoPorKm;
        this.porcentajeRecargo = porcentajeRecargo;
        this.costoFijo = costoFijo;
    }
	//clase principal
	public void crearRedFibraOptica() {}
	public void calcularCostos() {}
	public void guardarRedEnArchivo() {}
	
	
	public void agregarLocalidad(Localidad localidad) {
        red.agregarLocalidad(localidad);
    }

    public List<Localidad> getLocalidades() {
        return red.getVertices();
    }
    
}

