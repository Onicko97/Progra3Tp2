package test.models.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.domain.GestionRed;
import models.domain.Localidad;

public class GestionRedTest {

    private GestionRed gestion;
    private Localidad bsas;
    private Localidad laPlata; // < 300km, misma provincia
    private Localidad mendoza; // > 300km, distinta provincia

    @BeforeEach
    public void setUp() {
        gestion = new GestionRed();
        //parametros 
        gestion.setParametros(100.0, 20.0, 5000.0);
        
        bsas = new Localidad("CABA", "Buenos Aires", -34.6037, -58.3816);
        laPlata = new Localidad("La Plata", "Buenos Aires", -34.9214, -57.9545);
        mendoza = new Localidad("Mendoza", "Mendoza", -32.8895, -68.8458);
    }

    @Test
    public void costoBaseMismaProvinciaTest() {
        
        double distancia = gestion.calcularDistanciaKm(bsas, laPlata);
        double esperado = distancia * 100.0; 
        
        double actual = gestion.obtenerCostoEntre(bsas, laPlata);
        
        
        assertEquals(esperado, actual, 0.1, "El costo base en la misma provincia falló");
    }

    @Test
    public void costoConRecargoPorDistanciaTest() {
        //deberia aplicar el 20%
        Localidad lejosMismaProv = new Localidad("Lejos", "Buenos Aires", -38.0, -60.0);
        double dist = gestion.calcularDistanciaKm(bsas, lejosMismaProv);
        
        assertTrue(dist > 300);
        
        double costoBase = dist * 100.0;
        double esperado = costoBase + (costoBase * 0.20);
        
        double actual = gestion.obtenerCostoEntre(bsas, lejosMismaProv);
        assertEquals(esperado, actual, 0.1);
    }

    @Test
    public void costoConCostoFijoDistintaProvinciaTest() {
        
        Localidad otraProvCerca = new Localidad("Cerca", "Entre Rios", -34.0, -58.5);
        double dist = gestion.calcularDistanciaKm(bsas, otraProvCerca);
        
        double costoBase = dist * 100.0;
        double esperado = costoBase + 5000.0;         
        double actual = gestion.obtenerCostoEntre(bsas, otraProvCerca);
        assertEquals(esperado, actual, 0.1);
    }

    @Test
    public void costoCombinadoTodoTest() {
        // mendoza está a > 300km 
        double dist = gestion.calcularDistanciaKm(bsas, mendoza);
        double costoBase = dist * 100.0;
        double recargoDistancia = costoBase * 0.20;
        double esperado = costoBase + recargoDistancia + 5000.0;
        
        double actual = gestion.obtenerCostoEntre(bsas, mendoza);
        assertEquals(esperado, actual, 0.1);
    }
    
}
