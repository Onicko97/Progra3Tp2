package models.domain;



import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {
	
	private String nombre;
	private String provincia;
	private double latitud;
	private double longitud;
	
	
	public Localidad() {}
	public Localidad(String nombre, String provincia, double latitud, double longitud) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		
		
	}

	public Coordinate toCoordinate() {
        return new Coordinate(this.latitud, this.longitud);
    }
	
	
    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public double getLatitud() {
    	Coordinate coordenadas = this.toCoordinate();
    	return coordenadas.getLat();
    }

    public double getLongitud() {
    	Coordinate coordenadas = this.toCoordinate();
    	return coordenadas.getLat();
    }
}
