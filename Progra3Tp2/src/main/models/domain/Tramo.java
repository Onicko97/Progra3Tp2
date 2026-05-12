package models.domain;

public class Tramo {
    private final Localidad origen;
    private final Localidad destino;
    private final double distancia;
    private final double costo; 

    public Tramo(Localidad origen, Localidad destino, double distancia, double costo) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.costo = costo;
    }

    
    public Localidad getOrigen() { return origen; }
    public Localidad getDestino() { return destino; }
    public double getDistancia() { return distancia; }
    public double getCosto() { return costo; }

    @Override
    public String toString() {
        return origen.getNombre() + " a " + destino.getNombre() + " (" + String.format("%.2f", distancia) + " km)";
    }
}