package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class VentanaPrincipal extends JFrame {
	
	private JPanel panel;
	private JMapViewer mapa;

	public VentanaPrincipal() {
		super("");
		propiedadesPorDefecto();
		crearPanel();
		crearMapa();
	}

	private void propiedadesPorDefecto() {
		this.setBounds(100, 100, 1300, 800);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void crearPanel() {
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(200, 0));
		this.getContentPane().add(panel, BorderLayout.EAST);
	}
	
	public void crearMapa() {
		mapa = new JMapViewer();
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
	
	public void agregarVerticeAlMapa(String nombre, Coordinate coordenada) {
		MapMarkerDot vertice = new MapMarkerDot(nombre, coordenada);
		vertice.getStyle().setBackColor(Color.BLUE);
		mapa.addMapMarker(vertice);
	}
	
	public void agregarArista (MapPolygon poligono) {
		poligono.getStyle().setColor(Color.RED);
		mapa.addMapPolygon(poligono);
		//no es ideal, MapPolygon necesita 3 coordenadas y traza un poligono, para que trace una linea hay que repetir una coordenada
	}
	
	public void probarVerticesAristas() {//para probar, despues hay que borrarlo
		Coordinate coor1 = new Coordinate(-34.546, -58.719); //Coordenadas de San Miguel: (-34.546, -58.719)
		Coordinate coor2 = new Coordinate(-34.546, -58.739);
		Coordinate coor3 = new Coordinate(-34.546, -58.739);
		
		MapPolygonImpl poligon = new MapPolygonImpl(coor1, coor2, coor3);
		agregarVerticeAlMapa("1", coor1);
		agregarVerticeAlMapa("2", coor2);
		agregarArista(poligon);
	}

}
