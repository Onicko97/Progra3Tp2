package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import models.domain.Localidad;
import models.domain.Tramo;
import models.grafo.Arista;
import models.grafo.Grafo;
import presenter.RedFibraOpticaPresenter;

public class Resultados extends JPanel implements IResultados {

    private JMapViewer mapa;
    private DefaultTableModel modeloTabla;
    private JLabel lblCostoTotal;
    private JLabel lblResumenParametros;
    private RedFibraOpticaPresenter presenter;

    public Resultados() {
        setLayout(new BorderLayout(0, 10));

        mapa = new JMapViewer();
        mapa.setZoomContolsVisible(false);
        mapa.setDisplayToFitMapMarkers();
        add(mapa, BorderLayout.CENTER);
        add(crearPanelSur(), BorderLayout.SOUTH);

    }

    private JPanel crearPanelSur() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 220)); // Aumentamos un poco el alto

        
        modeloTabla = new DefaultTableModel(
            new String[]{"Origen", "Destino", "Distancia (km)", "Costo ($)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable tabla = new JTable(modeloTabla);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

       //esto es para que se puedan ver los parametros que ingresaron
        JPanel panelInfo = new JPanel(new BorderLayout());
        
        lblResumenParametros = new JLabel("Parámetros: ");
        lblResumenParametros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblResumenParametros.setForeground(Color.DARK_GRAY);
        lblResumenParametros.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

        lblCostoTotal = new JLabel("Costo total:");
        lblCostoTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCostoTotal.setBorder(BorderFactory.createEmptyBorder(5, 10, 8, 10));

        panelInfo.add(lblResumenParametros, BorderLayout.NORTH);
        panelInfo.add(lblCostoTotal, BorderLayout.SOUTH);
        
        panel.add(panelInfo, BorderLayout.SOUTH);

        return panel;
    }
    
    @Override
    public void mostrarResultado(List<Tramo> tramos, List<Localidad> todas, 
            double total, double km, double rec, double fijo) {
    	
        limpiarPantalla();
        
        dibujarMarcadores(todas);
        
        dibujarRed(tramos);
        
        actualizarResumen(total, km, rec, fijo);
        
        ajustarVistaMapa(todas);
    }
    
	private void limpiarPantalla() {
		mapa.removeAllMapMarkers();
        mapa.removeAllMapPolygons();
        modeloTabla.setRowCount(0);
	}
	private void dibujarMarcadores(List<Localidad> localidades) {
	    for (Localidad l : localidades) {
	        MapMarkerDot marcador = new MapMarkerDot(l.getNombre(), 
	            new Coordinate(l.getLatitud(), l.getLongitud()));
	        marcador.getStyle().setBackColor(Color.BLUE);
	        mapa.addMapMarker(marcador);
	    }
	}
	private void dibujarRed(List<Tramo> tramos) {
	    for (Tramo tramo : tramos) {
	    	
	        Localidad origen = tramo.getOrigen();
	        Localidad destino = tramo.getDestino();
	        
	        agregarLineaMapa(origen, destino);
	        agregarFilaTabla(origen, destino, tramo.getCosto()); 
	    }
	}

	private void agregarLineaMapa(Localidad o, Localidad d) {
	    Coordinate c1 = new Coordinate(o.getLatitud(), o.getLongitud());
	    Coordinate c2 = new Coordinate(d.getLatitud(), d.getLongitud());
	    MapPolygonImpl linea = new MapPolygonImpl(c1, c2, c2);
	    linea.getStyle().setColor(Color.RED);
	    mapa.addMapPolygon(linea);
	}

	private void agregarFilaTabla(Localidad o, Localidad d, double costo) {
	    double dist = presenter.getDistancia(o, d);
	    modeloTabla.addRow(new Object[]{
	        o.getNombre(),
	        d.getNombre(),
	        String.format("%.2f km", dist),
	        String.format("$ %.2f", costo)
	    });
	}
	private void actualizarResumen(double total, double km, double rec, double fijo) {
	    lblResumenParametros.setText(String.format(
	        "Parámetros: $%.2f/km | Recargo: %.0f%% | Fijo: $%.2f", km, rec, fijo));
	    lblCostoTotal.setText(String.format("Costo total de la red: $ %.2f", total));
	}

	private void ajustarVistaMapa(List<Localidad> localidades) {
	    if (!localidades.isEmpty()) {
	        mapa.setDisplayToFitMapMarkers();
	    }
	}
	
    public void setPresenter(RedFibraOpticaPresenter presenter) {
    	this.presenter = presenter;
    }
    
}