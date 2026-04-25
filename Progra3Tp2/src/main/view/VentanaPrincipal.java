package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class VentanaPrincipal extends JFrame {
	
	private JMapViewer mapa = new JMapViewer();

	public VentanaPrincipal() {
		super("");
		propiedadesPorDefecto();
		crearMapa();
	}

	private void propiedadesPorDefecto() {
		this.setBounds(100, 100, 1300, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void crearMapa() {
		this.getContentPane().add(mapa);
	}
	
	public void mostrar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void agregarVerticeAlMapa(String nombre, Coordinate coordenada) { //Coordenadas de San Miguel: (-34.546, -58.719)
		MapMarkerDot vertice = new MapMarkerDot(nombre, coordenada);
		vertice.getStyle().setBackColor(Color.BLUE);
		mapa.addMapMarker(vertice);
	}
	
	public void agregarArista (MapPolygon coordenadas) { //no es ideal, MapPolygon necesita 3 coordenadas y traza un poligono
		mapa.addMapPolygon(coordenadas);
	}

}
