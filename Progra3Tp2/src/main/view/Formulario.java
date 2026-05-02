package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formulario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombreLocalidad;
	private JTextField textFieldLatitud;
	private JTextField textFieldLongitud;
	private String[] provincias = {"--SELECCIONE PROVINCIA--","Ciudad Autonoma de Buenos aires","Provincia de Buenos Aires","Catamarca",
									"Chaco", "Chubut", "Cordoba", "Corrientes", "Entre rios", "Formosa", "Jujuy", "La Pampa","La rioja",
									"Mendoza","Misiones","Neuquen","Rio negro","Salta","San Juan","San Luis","Santa Cruz","Santa Fe",
									"Santiago del Estero","Tierra del Fuego","Tucuman"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Formulario dialog = new Formulario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Formulario() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldNombreLocalidad = new JTextField();
		textFieldNombreLocalidad.setBounds(144, 30, 204, 20);
		contentPanel.add(textFieldNombreLocalidad);
		textFieldNombreLocalidad.setColumns(10);
		
		JLabel labelLocalidad = new JLabel("Nombre de localidad: ");
		labelLocalidad.setBounds(21, 33, 113, 14);
		contentPanel.add(labelLocalidad);
		
		JComboBox comboBoxProvincias = new JComboBox();
		comboBoxProvincias.setBounds(144, 61, 204, 22);
		comboBoxProvincias.setModel(new DefaultComboBoxModel(provincias));
		contentPanel.add(comboBoxProvincias);
		
		JLabel labelProvincia = new JLabel("Seleccione provincia: ");
		labelProvincia.setBounds(21, 65, 113, 14);
		contentPanel.add(labelProvincia);
		
		JLabel labelLatitud = new JLabel("Ingrese latitud: ");
		labelLatitud.setBounds(21, 106, 113, 14);
		contentPanel.add(labelLatitud);
		
		JLabel labelLongitud = new JLabel("Ingrese longitud: ");
		labelLongitud.setBounds(21, 144, 113, 14);
		contentPanel.add(labelLongitud);
		
		textFieldLatitud = new JTextField();
		textFieldLatitud.setBounds(144, 106, 204, 20);
		contentPanel.add(textFieldLatitud);
		textFieldLatitud.setColumns(10);
		
		textFieldLongitud = new JTextField();
		textFieldLongitud.setBounds(144, 141, 204, 20);
		contentPanel.add(textFieldLongitud);
		textFieldLongitud.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//logica para llamar al presenter aca y pasarle los datos ingresados
						//ejemplo de como obtener los valores ingresados:
						System.out.println("Localidad: " + textFieldNombreLocalidad.getText());
						System.out.println("Provincia: " + provincias[comboBoxProvincias.getSelectedIndex()]);
						System.out.println("Latitud: " + textFieldLatitud.getText());
						System.out.println("Longitud: " + textFieldLongitud.getText());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
