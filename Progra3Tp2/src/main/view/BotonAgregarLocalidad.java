package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class BotonAgregarLocalidad extends JButton{
	
	JFrame frameLocalidad;
	JTextField nombre, provincia, latitud, longitud;
	
	public BotonAgregarLocalidad() {
		
		propiedadesPorDefecto();
		crearFrame();
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameLocalidad.setVisible(true);
			}
		});
	}
	
	private void propiedadesPorDefecto() {
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setBorder(null);
		this.setText("Agregar Localidad");
		this.setPreferredSize(new Dimension(150,100));
		this.setFont(new Font("SansSerif", Font.PLAIN, 15));
	}
	
	private void crearFrame() {
		frameLocalidad = new JFrame();
		frameLocalidad.setTitle("Ingrese la Localidad");
		frameLocalidad.setSize(400, 300);
		frameLocalidad.setBackground(Color.BLACK);
		frameLocalidad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameLocalidad.setLayout(new GridLayout(5, 1));
		settearTextfields();
		JButton boton = new JButton("Enter");
		buttonEvent(boton);
		frameLocalidad.add(boton, BorderLayout.SOUTH);
		frameLocalidad.setVisible(false);
	}
	
	private void settearTextfields() {
		nombreTextfield();
		provinciaTextfield();
		latitudTextfield();
		longitudTextfield();
	}
	
	private void nombreTextfield() {
		nombre = new JTextField("Nombre");
		nombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
		nombre.setBackground(Color.LIGHT_GRAY);
		frameLocalidad.add(nombre);
	}
	
	private void provinciaTextfield() {
		provincia = new JTextField("Provincia");
		provincia.setFont(new Font("SansSerif", Font.PLAIN, 20));
		provincia.setBackground(Color.WHITE);
		frameLocalidad.add(provincia);
	}
	
	private void latitudTextfield() {
		latitud = new JTextField("Latitud");
		latitud.setFont(new Font("SansSerif", Font.PLAIN, 20));
		latitud.setBackground(Color.LIGHT_GRAY);
		frameLocalidad.add(latitud);
	}
	
	private void longitudTextfield() {
		longitud = new JTextField("Longitud");
		longitud.setFont(new Font("SansSerif", Font.PLAIN, 20));
		longitud.setBackground(Color.WHITE);
		frameLocalidad.add(longitud);
	}
	
	private void buttonEvent(JButton boton) {
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(nombre.getText() + " " + provincia.getText() + " " + latitud.getText() + " " + longitud.getText());
			}
		});
	}
	
}
