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
	private Resultados pantallaResultados;
	private Parametros pantallaParametros;
	
	public VentanaPrincipal() {
		
		modelo = new GestionRed();
		pantallaResultados = new Resultados();
		pantallaParametros = new Parametros();
		presenter = new RedFibraOpticaPresenter(pantallaLocalidades,pantallaResultados, pantallaParametros, modelo, this);
		
		pantallaResultados.setPresenter(presenter);
        pantallaLocalidades.setPresenter(presenter);
        pantallaParametros.setPresenter(presenter);
        
        
        getContentPane().setLayout(cardLayout);
        getContentPane().add(pantallaLocalidades, "localidades");
        getContentPane().add(pantallaResultados, "resultados");
        getContentPane().add(pantallaParametros, "parametros");
        
        cardLayout.show(getContentPane(), "pantallaLocalidades");
        
        propiedadesPorDefecto();
       
    }
	
	private void propiedadesPorDefecto() {
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
    public void mostrarLocalidades() {
        cardLayout.show(getContentPane(), "localidades");
    }
    
    public void mostrarResultado() {
        cardLayout.show(getContentPane(), "resultados");
    }
    
    public void mostrarConfiguracionRed() {
    	 cardLayout.show(getContentPane(), "parametros");
    }

    public void mostrar() {
        EventQueue.invokeLater(() -> setVisible(true));
    }
}
