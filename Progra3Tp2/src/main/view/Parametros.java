package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presenter.RedFibraOpticaPresenter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class Parametros extends JPanel implements IParametros {

	private static final long serialVersionUID = 1L;
	private JTextField textCostoKM;
	private JTextField textRecargo;
	private JTextField textCostoFijo;
	private RedFibraOpticaPresenter presenter;

	
	public Parametros() {
	    setLayout(new BorderLayout(0, 0));
	    
	    // titulo
	    JPanel panelTitulo = new JPanel();
	    add(panelTitulo, BorderLayout.NORTH);
	    JLabel lblTitulo = new JLabel("CONFIGURACIÓN DE PARÁMETROS");
	    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
	    lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	    panelTitulo.add(lblTitulo);
	    
	    // formulario
	    JPanel panelForm = new JPanel();
	    add(panelForm, BorderLayout.CENTER);
	    GridBagLayout gbl_panelForm = new GridBagLayout();
	    
	    // Tres columnas con peso 1.0 para que se repartan el ancho equitativamente
	    gbl_panelForm.columnWeights = new double[]{1.0, 1.0, 1.0};
	    gbl_panelForm.rowWeights = new double[]{0.0, 0.0}; 
	    panelForm.setLayout(gbl_panelForm);
	    
	    //para dar aire entre componentes 
	    Insets paddingLabels = new Insets(10, 10, 5, 10);
	    Insets paddingFields = new Insets(0, 10, 10, 10);

	    //COSTOKM 
	    JLabel lblCosto = new JLabel("Costo por KM");
	    GridBagConstraints gbc_lbl = new GridBagConstraints();
	    gbc_lbl.insets = paddingLabels;
	    gbc_lbl.gridx = 0; gbc_lbl.gridy = 0;
	    panelForm.add(lblCosto, gbc_lbl);

	    textCostoKM = new JTextField();
	    GridBagConstraints gbc_txt = new GridBagConstraints();
	    gbc_txt.insets = paddingFields;
	    gbc_txt.fill = GridBagConstraints.HORIZONTAL;
	    gbc_txt.gridx = 0; gbc_txt.gridy = 1;
	    panelForm.add(textCostoKM, gbc_txt);
	    
	    //RECARGO
	    JLabel lblRecargo = new JLabel("Recargo (%)");
	    GridBagConstraints gbc_lblR = new GridBagConstraints();
	    gbc_lblR.insets = paddingLabels;
	    gbc_lblR.gridx = 1; gbc_lblR.gridy = 0;
	    panelForm.add(lblRecargo, gbc_lblR);

	    textRecargo = new JTextField();
	    GridBagConstraints gbc_txtR = new GridBagConstraints();
	    gbc_txtR.insets = paddingFields;
	    gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
	    gbc_txtR.gridx = 1; gbc_txtR.gridy = 1;
	    panelForm.add(textRecargo, gbc_txtR);
	    
	    //COSTOFIJO
	    JLabel lblFijo = new JLabel("Costo fijo");
	    GridBagConstraints gbc_lblF = new GridBagConstraints();
	    gbc_lblF.insets = paddingLabels;
	    gbc_lblF.gridx = 2; gbc_lblF.gridy = 0;
	    panelForm.add(lblFijo, gbc_lblF);

	    textCostoFijo = new JTextField();
	    GridBagConstraints gbc_txtF = new GridBagConstraints();
	    gbc_txtF.insets = paddingFields;
	    gbc_txtF.fill = GridBagConstraints.HORIZONTAL;
	    gbc_txtF.gridx = 2; gbc_txtF.gridy = 1;
	    panelForm.add(textCostoFijo, gbc_txtF);

	    //BOTONES
	    JPanel panelBotones = new JPanel();
	    add(panelBotones, BorderLayout.SOUTH);
	    panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	    
	    JButton btnGuardar = new JButton("Guardar");
	    btnGuardar.addActionListener(e -> presenter.guardarParametros());
	    panelBotones.add(btnGuardar);
	    
	    JButton btnAtras = new JButton("Volver");
	    btnAtras.addActionListener(e -> presenter.volverAtras());
	    panelBotones.add(btnAtras);
	}
	
	 public void setPresenter(RedFibraOpticaPresenter presenter) {
	    	this.presenter = presenter;
	    }
	 @Override
	 public String getCostoPorKm() {
	     return textCostoKM.getText();
	 }

	 @Override
	 public String getPorcentajeRecargo() {
	     return textRecargo.getText();
	 }

	 @Override
	 public String getCostoFijo() {
	     return textCostoFijo.getText();
	 }
	 @Override
	 public void mostrarError(String mensaje) {
	     javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	 }

	 @Override
	 public void mostrarExito(String mensaje) {
	     javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
	 }
}
