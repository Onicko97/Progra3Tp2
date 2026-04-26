package models.grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo<T>
{
	private int n; // numero de vertices
	private Double[][] A;
	protected List<T> vertices; // para mapear indice -> Objeto T
	// necesito lista de aristas?
	protected List<Arista> aristas;
	
	public Grafo(List<T> vertices)
	{
		this.n = vertices.size();
		this.vertices = vertices;
		this.A = new Double[n][n];
		this.aristas = new ArrayList<Arista>();
		
	}
	public List<Arista> getAristas() {
		return aristas;
	}
	
	public List<T> getVertices() {
		System.out.println("hello");
		return vertices;
	}
	// Agregado de aristas
	
	public void agregarArista(int i, int j, double peso)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = peso;
		A[j][i] = peso;
		Arista arista = new Arista(i, j, peso);
		aristas.add(arista);
	}
	
	
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = null;
		A[j][i] = null;
	}

	// Informa si existe la arista especificada
	public Double existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return A.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) != null )
				ret.add(j);
		}
		
		return ret;		
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
}
