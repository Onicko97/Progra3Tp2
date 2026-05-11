package test.models.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.domain.GestionRed;
import models.domain.Localidad;

class HaversineTest {

	@Test
	void mismoPuntoGeograficoDebeSerCero() {
		Localidad a = new Localidad("prueba", "prueba", -34.6, -58.3);
		Localidad b = new Localidad("prueba", "prueba", -34.6, -58.3);
		GestionRed gr = new GestionRed();
		assertEquals(gr.calcularDistanciaKm(a, b), 0);
	}
	@Test
	void haversineDistanciaConocida() {
		Localidad a = new Localidad("Buenos Aires", "prueba", -34.6037, -58.3816);
		Localidad b = new Localidad("Cordoba", "prueba", -31.4201, -64.1888);
		GestionRed gr = new GestionRed();
		double distanciaEsperada = 646.0;
		assertEquals(distanciaEsperada, gr.calcularDistanciaKm(a, b), 1.0);
	}
}
