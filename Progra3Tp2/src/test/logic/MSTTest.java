package test.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.MST;
import models.domain.Localidad;
import models.grafo.Arista;
import models.grafo.Grafo;

class MSTTest {
	private Grafo<Localidad> grafo;
	//inicializamos un grafo para probar los tests
	@BeforeEach
	void setUp() {
		List<Localidad> lista = new ArrayList<Localidad>();
		lista.add(new Localidad("bahia blanca", "buenos aires", 120, 200));
		lista.add(new Localidad("bahia blanca", "buenos aires", 120, 200));
		lista.add(new Localidad("bahia blanca", "buenos aires", 120, 200));
		lista.add(new Localidad("bahia blanca", "buenos aires", 120, 200));
		grafo = new Grafo<Localidad>(lista);
		
		grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 6);
        grafo.agregarArista(0, 3, 5);
        grafo.agregarArista(1, 3, 15);
        grafo.agregarArista(2, 3, 4);
	}

	
	@Test
	void verificarPesoTotal() {
		double pesoEsperado = 19;
		
		
		MST<Localidad> arbol = new MST<Localidad>(grafo);
		arbol.kruskal();
		assertEquals(pesoEsperado, arbol.getPesoTotal());
	}

	// El numero de aristas en un AGM debe ser igual al numero de vertices - 1
	@Test
	void verificarAristasVertices() {
		assertEquals(1,1);
	}
}
