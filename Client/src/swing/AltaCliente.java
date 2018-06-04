package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessdelegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.CuentaCorrienteDTO;
import exception.ClienteException;


public class AltaCliente {

	private ventanaPrincipal ventana;
	private AltaCuentaCorriente ventanaCuenta;
	private CuentaCorrienteDTO cuenta;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JCheckBox radio;

	public AltaCliente(ventanaPrincipal p) {


		ventana = p;
		ventana.getPanel().removeAll();
		contentPane = p.getPanel();

		JLabel lblAltaCliente = new JLabel("Alta Cliente");
		contentPane.add(lblAltaCliente);

		JLabel lblNewLabel = new JLabel("Alta cliente");
		lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 20));
		lblNewLabel.setBounds(206, 20, 213, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNombre = new JLabel("<html>Razón Social: <font color = \"red\">*</font><html>");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(32, 117, 61, 35);
		contentPane.add(lblNombre);

		JLabel lblDomicilio = new JLabel("<html>Domicilio: <font color = \"red\">*</font><html>");
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDomicilio.setBounds(32, 175, 177, 35);
		contentPane.add(lblDomicilio);

		JLabel lblMail = new JLabel("<html>Cond. Especial: <font color = \"red\">*</font><html>");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMail.setBounds(32, 237, 61, 35);
		contentPane.add(lblMail);

		JLabel lblApellido = new JLabel("<html>CUIT: <font color = \"red\">*</font><html>");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(269, 117, 177, 35);
		contentPane.add(lblApellido);

		JLabel lblTelfono = new JLabel("<html>Teléfono: <font color = \"red\">*</font><html>");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelfono.setBounds(269, 175, 177, 35);
		contentPane.add(lblTelfono);

		JLabel lblDNI = new JLabel("<html>Responsable Inscripto:<font color = \"red\">*</font><html>");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(269, 237, 74, 35);
		contentPane.add(lblDNI);

		textField = new JTextField(); //Razon Social
		textField.setBounds(95, 124, 156, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); //CUIT
		textField_1.setColumns(10);
		textField_1.setBounds(335, 124, 156, 28);
		contentPane.add(textField_1);

		textField_2 = new JTextField(); //domicilio
		textField_2.setColumns(10);
		textField_2.setBounds(95, 182, 156, 28);
		contentPane.add(textField_2);

		textField_3 = new JTextField(); //telefono
		textField_3.setColumns(10);
		textField_3.setBounds(335, 182, 156, 28);
		contentPane.add(textField_3);

		textField_4 = new JTextField(); //COND
		textField_4.setColumns(10);
		textField_4.setBounds(95, 244, 156, 28);
		contentPane.add(textField_4);


		radio = new JCheckBox();
		radio.setBounds(350, 244, 25, 25);
		contentPane.add(radio);



		JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCamposObligatorios.setForeground(Color.RED);
		lblCamposObligatorios.setBounds(59, 314, 147, 14);
		contentPane.add(lblCamposObligatorios);

		JButton btnNewButton2 = new JButton("Cta Corriente");
		btnNewButton2.setBounds(437, 242, 104, 31);
		contentPane.add(btnNewButton2);
		cuenta = new  CuentaCorrienteDTO();
		btnNewButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ventanaCuenta = new AltaCuentaCorriente ();



			}
		});

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(437, 302, 104, 31);
		contentPane.add(btnNewButton);



		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().trim().isEmpty() & !textField_1.getText().trim().isEmpty() & 
						!textField_2.getText().trim().isEmpty() & !textField_3.getText().trim().isEmpty()
						& !textField_4.getText().trim().isEmpty() ){
					try {
						cuenta = ventanaCuenta.getCuenta();

						try {
							ClienteDTO cliente=new ClienteDTO();
							cliente.setCondicionEsp(textField_4.getText());
							cliente.setCuentaCorriente(cuenta);
							cliente.setCuit(textField_1.getText());
							cliente.setDireccion(textField_2.getText());
							if (radio.isSelected())
							cliente.setR_inscripto(true);
							else
								cliente.setR_inscripto(false);
							
							cliente.setRazon_social(textField.getText());
							cliente.setTelefono(textField_3.getText());
							BusinessDelegate.getInstance().crearCliente(cliente);
							JOptionPane.showMessageDialog(null, "Se dio de alta el cliente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						}
						catch (RemoteException e2) {
							JOptionPane.showMessageDialog(null, "Fallo en la conexión", "Error", JOptionPane.ERROR_MESSAGE);
							textField.setText(null);
							textField_1.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
							textField_4.setText(null);
							
							e2.printStackTrace();
						} catch (ClienteException e2) {

							JOptionPane.showMessageDialog(null, "Error al crear el Cliente", "Error", JOptionPane.ERROR_MESSAGE);
							textField.setText(null);
							textField_1.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
							textField_4.setText(null);
							

							e2.printStackTrace();
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Por favor Ingresar Cuenta Corriente", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
				else
					JOptionPane.showMessageDialog(null, "Faltan datos", "Warning", JOptionPane.WARNING_MESSAGE);	
			}
		});

		ventana.getFrame().getContentPane().add(contentPane);
		ventana.getFrame().repaint();

	}

}