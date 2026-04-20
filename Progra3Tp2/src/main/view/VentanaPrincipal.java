package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class VentanaPrincipal extends JFrame {
	
	private JMapViewer mapa = new JMapViewer();;

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

}
