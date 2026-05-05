package models.domain;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {
	
	private Coordinate coordenadas;
	private String nombre;
	private String provincia;
	
	public Localidad(String nombre, String provincia, double latitud, double longitud) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.coordenadas = new Coordinate(latitud, longitud);
	}

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public double getLatitud() {
        return coordenadas.getLat();
    }

    public double getLongitud() {
        return coordenadas.getLon();
    }
}
