package swing;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ModificarCliente {

	private ventanaPrincipal ventana;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	
	public ModificarCliente(ventanaPrincipal p ){
	
		ventana = p;
		
		ventana.getPanel().removeAll();
		contentPane = p.getPanel();
	    
		JLabel lblCamposObligatorios = new JLabel("Apellido:");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCamposObligatorios.setForeground(Color.BLACK);
		lblCamposObligatorios.setBounds(296, 191, 147, 14);
		contentPane.add(lblCamposObligatorios);
		
		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscar.setBounds(59, 129, 57, 21);
		contentPane.add(lblBuscar);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(59, 191, 147, 14);
		contentPane.add(lblNombre);
		
		JLabel lblModificacinCliente = new JLabel("Modificaci\u00F3n Cliente");
		lblModificacinCliente.setForeground(Color.BLACK);
		lblModificacinCliente.setFont(new Font("Broadway", Font.BOLD, 20));
		lblModificacinCliente.setBounds(164, 62, 279, 31);
		contentPane.add(lblModificacinCliente);
		
		JLabel lblinsertarDni = new JLabel("(ingresar DNI)");
		lblinsertarDni.setForeground(Color.BLACK);
		lblinsertarDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblinsertarDni.setBounds(339, 132, 147, 14);
		contentPane.add(lblinsertarDni);
		
		JLabel lblNewLabel = new JLabel("Domicilio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(59, 232, 57, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(296, 232, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mail:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(59, 268, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();  //dni
		textField.setBounds(120, 129, 156, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(); //nombre
		textField_1.setColumns(10);
		textField_1.setBounds(120, 188, 156, 28);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();  //domicilio
		textField_2.setColumns(10);
		textField_2.setBounds(120, 229, 156, 28);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField(); //mail
		textField_3.setColumns(10);
		textField_3.setBounds(120, 265, 156, 28);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();  //apellido
		textField_4.setColumns(10);
		textField_4.setBounds(363, 184, 156, 28);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();  //telefono
		textField_5.setColumns(10);
		textField_5.setBounds(363, 225, 156, 28);
		contentPane.add(textField_5);
		
		JButton btnNewButton_1 = new JButton("Ir");
		btnNewButton_1.setBounds(286, 127, 46, 25);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
	/*			try {
					if(!textField.getText().trim().isEmpty()){
					   Cliente cl = Controlador.getInstancia().buscarCliente(textField.getText());
					   if(cl == null){
							JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
							textField.setText(null);
					   }
					   else {
							c = cl.toView();
							textField_1.setText(c.getNombre());
							textField_4.setText(c.getApellido());
							textField_2.setText(c.getDomicilio());
							textField_5.setText(c.getTelefono());
							textField_3.setText(c.getMail());
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Debe ingresar un dni", "Warning", JOptionPane.WARNING_MESSAGE);
				} catch (ConnectionException e1) {
					JOptionPane.showMessageDialog(null, "Fallo en la conexión", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(ClienteException e2){
					JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}*/
			}			
		});		
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(437, 302, 104, 31);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				/*try {
					if(!textField.getText().trim().isEmpty()){
						c = new ClienteVO();
						c.setDni(textField.getText());
						c.setNombre(textField_1.getText());
						c.setApellido(textField_4.getText());
						c.setDomicilio(textField_2.getText());
						c.setMail(textField_3.getText());
						c.setTelefono(textField_5.getText());						
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro que desea modificar el cliente?", "Warning", dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							Controlador.getInstancia().modificarCliente(c);
							JOptionPane.showMessageDialog(null, "Se modificó el cliente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Debe ingresar un dni", "Warning", JOptionPane.WARNING_MESSAGE);
				} catch (ConnectionException e1) {
					JOptionPane.showMessageDialog(null, "Fallo en la conexión", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(ClienteException e2){
					JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}*/
				textField.setText(null);
				textField_1.setText(null);
				textField_4.setText(null);
				textField_2.setText(null);
				textField_5.setText(null);
				textField_3.setText(null);
			}
		});
	
		ventana.getFrame().getContentPane().add(contentPane);
		ventana.getFrame().repaint();
		
	}
	
}