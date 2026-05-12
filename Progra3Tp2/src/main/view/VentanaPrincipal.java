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
	
	private final GestionRed modelo;
    private final RedFibraOpticaPresenter presenter;
    
	private final Localidades pantallaLocalidades;
	private final Resultados pantallaResultados;
	private final Parametros pantallaParametros;
	private final CardLayout cardLayout = new CardLayout();
	
	
	public VentanaPrincipal() {
	    //inicializa el modelo
	    modelo = new GestionRed();
	    
	    //inicializa las vistas
	    pantallaLocalidades = new Localidades();
	    pantallaResultados = new Resultados();
	    pantallaParametros = new Parametros();
	    
	    //inicializa el presenter 
	    presenter = new RedFibraOpticaPresenter(pantallaLocalidades, pantallaResultados, pantallaParametros, modelo, this);
	    
	    //para desacoplar sirve
	    pantallaResultados.setPresenter(presenter);
	    pantallaLocalidades.setPresenter(presenter);
	    pantallaParametros.setPresenter(presenter);
	    
	    
	    configurarLayout();
	    
	    //asi arranca 
	    propiedadesPorDefecto();
	    mostrarLocalidades(); 
	}

	private void configurarLayout() {
		getContentPane().setLayout(cardLayout);
	    getContentPane().add(pantallaLocalidades, "localidades");
	    getContentPane().add(pantallaResultados, "resultados");
	    getContentPane().add(pantallaParametros, "parametros");
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
