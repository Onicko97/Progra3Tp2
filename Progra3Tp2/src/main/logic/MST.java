package logic;

import models.grafo.Grafo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import models.grafo.Arista;
public class MST<T> {
	private Grafo<T> g;
	private int[] A;
	private double pesoTotal;
	public MST(Grafo<T> g) {
		this.pesoTotal = 0;
		this.g = g;
		System.out.println(g);
		this.A = makeSet(g.getVertices().size());
		
	}
	
	public Grafo<T> kruskal() {
		 
		List<Arista> aristasOrdenadas = (List<Arista>) ordenarAristas(g.getAristas());
		Grafo<T> arbolGeneradorMinimo = new Grafo<T>(g.getVertices());
		
		for(Arista ar: aristasOrdenadas ) {
			int origen = ar.getOrigen();
			int destino = ar.getDestino();
			double peso = ar.getPeso();
			if(!mismaComponenteConexa( origen, destino)) {
				pesoTotal += peso;
				union(origen, destino);
				arbolGeneradorMinimo.agregarArista(origen, destino, peso);
			}
		}
		return arbolGeneradorMinimo;
	}
	
	// se verifica si estan en la misma componente conexa
	private boolean mismaComponenteConexa(int i,int j) {
		return find(A, i) == find (A, j);
	}
	public void union(int i, int j) {
		int raiz1 = find(A, i);
		int raiz2 = find(A, j);
		//actaulizamos la raiz de uno para que sea la raiz del otro
		A[raiz1] = raiz2;
	}
	//encuentra la raiz del vertice
	public int find(int[] A, int i) {
		if (i == A[i] ) {
			return i;
		}
		return find ( A, A[i]);
	}
	// inicializamos cada componentes - 
	private int[] makeSet(int cantVertices) {
		System.out.println("hello");
		int[] verticesPadre = new int[cantVertices];
		for(int i = 0; i < cantVertices; i++ ) {
			verticesPadre[i] = i;
		}
		return verticesPadre;
	}
	// se crea un diccionario con <Arista, double> con peso de menor a mayor

	private List<Arista> ordenarAristas(List<Arista> list) {
		return list.stream()	
				.sorted(Comparator.comparingDouble(Arista::getPeso))
				.collect(Collectors.toList());
		}
	
	public double getPesoTotal() {
		return this.pesoTotal;
	}
	
}
