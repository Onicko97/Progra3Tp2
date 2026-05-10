package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.domain.Localidad;
import presenter.RedFibraOpticaPresenter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;

public class Localidades extends JPanel implements ILocalidades {
	
	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textLongitud;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private RedFibraOpticaPresenter presenter;
    
	public Localidades() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formulario = new JPanel();
		formulario.setPreferredSize(new Dimension(450, 280));
		add(formulario, BorderLayout.NORTH);
		GridBagLayout gbl_formulario = new GridBagLayout();
		gbl_formulario.columnWidths = new int[]{64, 64, 0, 149, 64, 64, 64, 0, 80, 38, 0, 0, 0, 0, 0};
		gbl_formulario.rowHeights = new int[]{92, 12, 30, 12, 16, 0, 10, 0, 0};
		gbl_formulario.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_formulario.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		formulario.setLayout(gbl_formulario);
		
		JLabel lblTitulo = new JLabel("CREADOR DE RED DE FIBRA OPTICA");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitulo.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 10;
		gbc_lblTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 2;
		gbc_lblTitulo.gridy = 0;
		formulario.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 3;
		gbc_lblNombre.gridy = 1;
		formulario.add(lblNombre, gbc_lblNombre);
		
		textNombre = new JTextField();
		textNombre.setPreferredSize(new Dimension(0, 24));
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.BOTH;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 4;
		gbc_textNombre.gridy = 1;
		formulario.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel lblLatitud = new JLabel("LATITUD");
		lblLatitud.setFont(new Font("Segoe UI", Font.BOLD, 11));
		GridBagConstraints gbc_lblLatitud = new GridBagConstraints();
		gbc_lblLatitud.fill = GridBagConstraints.BOTH;
		gbc_lblLatitud.insets = new Insets(0, 0, 5, 5);
		gbc_lblLatitud.gridx = 7;
		gbc_lblLatitud.gridy = 1;
		formulario.add(lblLatitud, gbc_lblLatitud);
		
		textLatitud = new JTextField();
		GridBagConstraints gbc_textLatitud = new GridBagConstraints();
		gbc_textLatitud.fill = GridBagConstraints.BOTH;
		gbc_textLatitud.insets = new Insets(0, 0, 5, 5);
		gbc_textLatitud.gridx = 8;
		gbc_textLatitud.gridy = 1;
		formulario.add(textLatitud, gbc_textLatitud);
		textLatitud.setColumns(10);
		
		JLabel lblProvincia = new JLabel("   PROVINCIA");
		lblProvincia.setFont(new Font("Segoe UI", Font.BOLD, 11));
		GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
		gbc_lblProvincia.insets = new Insets(0, 0, 5, 5);
		gbc_lblProvincia.gridx = 3;
		gbc_lblProvincia.gridy = 3;
		formulario.add(lblProvincia, gbc_lblProvincia);
		
		textProvincia = new JTextField();
		GridBagConstraints gbc_textProvincia = new GridBagConstraints();
		gbc_textProvincia.fill = GridBagConstraints.BOTH;
		gbc_textProvincia.insets = new Insets(0, 0, 5, 5);
		gbc_textProvincia.gridx = 4;
		gbc_textProvincia.gridy = 3;
		formulario.add(textProvincia, gbc_textProvincia);
		textProvincia.setColumns(10);
		
		JLabel lblLongitud = new JLabel("LONGITUD");
		lblLongitud.setFont(new Font("Segoe UI", Font.BOLD, 11));
		GridBagConstraints gbc_lblLongitud = new GridBagConstraints();
		gbc_lblLongitud.fill = GridBagConstraints.BOTH;
		gbc_lblLongitud.insets = new Insets(0, 0, 5, 5);
		gbc_lblLongitud.gridx = 7;
		gbc_lblLongitud.gridy = 3;
		formulario.add(lblLongitud, gbc_lblLongitud);
		
		textLongitud = new JTextField();
		GridBagConstraints gbc_textLongitud = new GridBagConstraints();
		gbc_textLongitud.fill = GridBagConstraints.BOTH;
		gbc_textLongitud.insets = new Insets(0, 0, 5, 5);
		gbc_textLongitud.gridx = 8;
		gbc_textLongitud.gridy = 3;
		formulario.add(textLongitud, gbc_textLongitud);
		textLongitud.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setPreferredSize(new Dimension(121, 23));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        onAgregar();
			    }
			});
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.fill = GridBagConstraints.BOTH;
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregar.gridx = 4;
		gbc_btnAgregar.gridy = 5;
		formulario.add(btnAgregar, gbc_btnAgregar);
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.BOTH;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 7;
		gbc_btnEliminar.gridy = 5;
		formulario.add(btnEliminar, gbc_btnEliminar);
		
		JScrollPane tabla = new JScrollPane();
		add(tabla, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, ""},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Nombre", "Provincia", "Latitud", "Longitud"
			}
		));
		tabla.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(
			    new String[]{"Nombre", "Provincia", "Latitud", "Longitud"}, 0) {
			    @Override
			    public boolean isCellEditable(int row, int col) { return false; }
			};
			table.setModel(modeloTabla);
			
		JPanel derecha = new JPanel();
		derecha.setPreferredSize(new Dimension(70, 400));
		add(derecha, BorderLayout.EAST);
		
		JPanel izquierda = new JPanel();
		izquierda.setPreferredSize(new Dimension(70, 400));
		add(izquierda, BorderLayout.WEST);
		
		JPanel boton = new JPanel(new FlowLayout(FlowLayout.CENTER)); // o RIGHT
		add(boton, BorderLayout.SOUTH);
		
		JButton btnCrearRed = new JButton("CREAR RED");
		btnCrearRed.setPreferredSize(new Dimension(169, 23));
		btnCrearRed.addActionListener(e -> presenter.planificar());
		GridBagConstraints gbc_btnCrearRed = new GridBagConstraints();
		gbc_btnCrearRed.gridx = 7;
		gbc_btnCrearRed.gridy = 0;
		boton.add(btnCrearRed, gbc_btnCrearRed);
	}

	    @Override 
	    public String getNombre()    { return textNombre.getText().trim(); }
	    @Override 
	    public String getProvincia() { return textProvincia.getText().trim(); }
	    @Override 
	    public String getLatitud()   { return textLatitud.getText().trim(); }
	    @Override 
	    public String getLongitud()  { return textLongitud.getText().trim(); }
	    @Override
	    public int getFilaSeleccionada() { return table.getSelectedRow(); }
	    @Override
	    public void actualizarTabla(List<Localidad> localidades) {
	        modeloTabla.setRowCount(0);
	        for (Localidad l : localidades) {
	            modeloTabla.addRow(new Object[]{
	                l.getNombre(), l.getProvincia(), l.getLatitud(), l.getLongitud()
	            });
	        }
	    }

	    @Override
	    public void limpiarFormulario() {
	        textNombre.setText("");
	        textProvincia.setText("");
	        textLatitud.setText("");
	        textLongitud.setText("");
	    }

	    @Override
	    public void mostrarError(String msg) {
	        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    private void onAgregar() {
	        presenter.agregarLocalidad();
	    }

	    public void setPresenter(RedFibraOpticaPresenter presenter) {
	        this.presenter = presenter;
	    }
	    
}
	

