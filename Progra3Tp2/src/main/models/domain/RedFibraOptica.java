package models.domain;

import java.util.List;

import models.grafo.BFS;
import models.grafo.Grafo;

public class RedFibraOptica extends Grafo<Localidad> {
   
	public RedFibraOptica(List<Localidad> localidades) {
		super(localidades);
	}
	
	public boolean esRedValida() {
        return BFS.esConexo(this);
    }
	
	public boolean existeRuta(int origen, int destino) {
	    return BFS.alcanzables(this, origen).contains(destino);
	}

	public void construirGrafoCompleto(GestionRed gestion) {
	    int n = vertices.size();
	    for (int i = 0; i < n; i++) {
	        for (int j = i + 1; j < n; j++) {
	        	
	            double costo = gestion.obtenerCostoEntre(vertices.get(i), vertices.get(j));
	            this.agregarArista(i, j, costo);
	        }
	    }
	}
}
