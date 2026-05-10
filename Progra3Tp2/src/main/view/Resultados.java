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
import models.grafo.Arista;
import models.grafo.Grafo;

public class Resultados extends JPanel implements IResultados {

    private JMapViewer mapa;
    private DefaultTableModel modeloTabla;
    private JLabel lblCostoTotal;

    public Resultados() {
        setLayout(new BorderLayout(0, 10));

        mapa = new JMapViewer();
        add(mapa, BorderLayout.CENTER);
        add(crearPanelSur(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelSur() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 200));

        modeloTabla = new DefaultTableModel(
            new String[]{"Origen", "Destino", "Distancia (km)", "Costo ($)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable tabla = new JTable(modeloTabla);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        lblCostoTotal = new JLabel("Costo total:");
        lblCostoTotal.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCostoTotal.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        panel.add(lblCostoTotal, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void mostrarResultado(Grafo<Localidad> resultado, List<Localidad> localidades, double costoTotal) {
        mapa.removeAllMapMarkers();
        mapa.removeAllMapPolygons();

        //marcadores 
        for (Localidad l : localidades) {
            MapMarkerDot marcador = new MapMarkerDot(l.getNombre(), 
                new Coordinate(l.getLatitud(), l.getLongitud()));
            marcador.getStyle().setBackColor(Color.BLUE);
            mapa.addMapMarker(marcador);
        }

        //aristas
        modeloTabla.setRowCount(0);
        for (Arista arista : resultado.getAristas()) {
        	
            Localidad origen = localidades.get(arista.getOrigen());
            Localidad destino = localidades.get(arista.getDestino());
            
            Coordinate cOrigen  = new Coordinate(origen.getLatitud(), origen.getLongitud());
            Coordinate cDestino = new Coordinate(destino.getLatitud(), destino.getLongitud());
            MapPolygonImpl linea = new MapPolygonImpl(cOrigen, cDestino, cDestino);
            linea.getStyle().setColor(Color.RED);
            mapa.addMapPolygon(linea);

            //fila en la tabla
            modeloTabla.addRow(new Object[]{
                origen.getNombre(),
                destino.getNombre(),
                
                String.format("$ %.0f", arista.getPeso())
            });
        }

        lblCostoTotal.setText(String.format("Costo total: $ %.0f", costoTotal));

    
    //esto es para que el mapa se centre en los vertices
    if (!localidades.isEmpty()) {
        mapa.setDisplayPosition(
            new Coordinate(localidades.get(0).getLatitud(), localidades.get(0).getLongitud()), 6);
    }
    
    }
    
}