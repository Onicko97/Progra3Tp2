package logic;

import models.grafo.Grafo;

public class MST<T> {
	
	public void kruskal(Grafo<T> g, T inicio) {
		int[] verticesPadre = makeSet(g.getVertices().size());
		Grafo<T> arbolGeneradorMinimo = new Grafo<T>(null);
	}
	
	public void union() {}
	public void find() {}
	// inicializamos cada componentes - 
	private int[] makeSet(int cantVertices) {
		int[] verticesPadre = new int[cantVertices];
		for(int i = 0; i < cantVertices; i++ ) {
			verticesPadre[i] = i;
		}
		return verticesPadre;
	}
	// se crea un diccionario con <Arista, double> con peso de menor a mayor
	//public HashMap<Arista, double> ordenarAristas() {}
	
}
