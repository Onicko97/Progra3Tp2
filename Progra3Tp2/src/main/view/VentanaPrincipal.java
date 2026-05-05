package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import models.domain.*;
import presenter.RedFibraOpticaPresenter;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame  {
	
	private JMapViewer mapa = new JMapViewer();
	private GestionRed modelo;
	Localidades pantallaLocalidades = new Localidades();
	RedFibraOpticaPresenter presenter;
	private CardLayout cardLayout = new CardLayout();
	
	public VentanaPrincipal() {
		
		modelo = new GestionRed();
		presenter = new RedFibraOpticaPresenter(pantallaLocalidades, modelo, this);
        pantallaLocalidades.setPresenter(presenter);
        
        getContentPane().setLayout(cardLayout);
        getContentPane().add(pantallaLocalidades, "localidades");
        getContentPane().add(mapa, "mapa");
        
        cardLayout.show(getContentPane(), "localidades");
        
        propiedadesPorDefecto();
       
    }
	
	private void propiedadesPorDefecto() {
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostrarMapa() {
        cardLayout.show(getContentPane(), "mapa");
    }
    
    public void mostrarLocalidades() {
        cardLayout.show(getContentPane(), "localidades");
    }

    public void mostrar() {
        EventQueue.invokeLater(() -> setVisible(true));
    }
	
	public void agregarVerticeAlMapa(String nombre, Coordinate coordenada) { //Coordenadas de San Miguel: (-34.546, -58.719)
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
