package test.models.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.domain.GestionRed;
import models.domain.Localidad;
import models.domain.RedFibraOptica;

import java.util.ArrayList;
import java.util.List;

class RedFibraOpticaTest {
    
    private RedFibraOptica red;

    @BeforeEach
    void setUp() {
       
        GestionRed gestion = new GestionRed();
        gestion.setParametros(100, 10, 500);

        gestion.agregarLocalidad(new Localidad("CABA", "BsAs", -34.6, -58.4));
        gestion.agregarLocalidad(new Localidad("La Plata", "BsAs", -34.9, -57.9));
        gestion.agregarLocalidad(new Localidad("Rosario", "Santa Fe", -32.9, -60.6));

        red = new RedFibraOptica(gestion.getLocalidades());
    }

    @Test
    void testRedInicialNoEsValida() {
        assertFalse(red.esRedValida(), "Una red sin aristas no debería ser conexa");
    }

    @Test
    void testExisteRutaDirecta() {
        red.agregarArista(0, 1, 50.0);
        assertTrue(red.existeRuta(0, 1));
        assertFalse(red.existeRuta(0, 2), "No debería haber ruta al nodo 2");
    }

    @Test
    void testConstruirGrafoCompleto() {

        GestionRed gestion = new GestionRed(); 
        gestion.setParametros(100, 10, 500); // 100 por km, 10 recargo, 500 fijo
        
        red.construirGrafoCompleto(gestion);
        
        //debe tener 3 aristas
        assertEquals(3, red.getAristas().size());
        assertTrue(red.esRedValida(), "Un grafo completo siempre debe ser conexo");
    }
    
}