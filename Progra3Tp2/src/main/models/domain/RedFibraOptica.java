package models.domain;

import java.util.List;

import models.grafo.Grafo;

public class RedFibraOptica extends Grafo<Localidad> {
   
	public RedFibraOptica(List<Localidad> localidades) {
		super(localidades);
	}
	
	public void agregarLocalidad(Localidad l) {
		//hay que verificar que el dato es correcto
		vertices.add(l);
	} // vertice
	
	public void conectarLocalidades() {} //arista
	public void existeRuta() {} //existe ruta entre 2 localidades?

}
