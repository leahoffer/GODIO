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



public class AutorizarPedido {
	
	private ventanaPrincipal ventana;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_3;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	
	public AutorizarPedido(ventanaPrincipal p){
		
		ventana = p;
		ventana.getPanel().removeAll();
		contentPane = p.getPanel();

		
		JLabel lblCamposObligatorios = new JLabel("Pedido:");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCamposObligatorios.setForeground(Color.BLACK);
		lblCamposObligatorios.setBounds(256, 191, 147, 14);
		contentPane.add(lblCamposObligatorios);
		
		JButton btnNewButton = new JButton("Autorizar");
		btnNewButton.setBounds(437, 302, 104, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBounds(327, 302, 104, 31);
		contentPane.add(btnNewButton_2);
		
		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBuscar.setBounds(39, 135, 57, 21);
		contentPane.add(lblBuscar);
		
		JLabel lblNombre = new JLabel("Razón Social: ");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(39, 191, 87, 14);
		contentPane.add(lblNombre);
		
		JLabel lblModificacinCliente = new JLabel("Autorizar Pedido");
		lblModificacinCliente.setForeground(Color.BLACK);
		lblModificacinCliente.setFont(new Font("Broadway", Font.BOLD, 20));
		lblModificacinCliente.setBounds(211, 56, 279, 31);
		contentPane.add(lblModificacinCliente);
		
		JLabel lblinsertarDni = new JLabel("(ingresar Pedido)");
		lblinsertarDni.setForeground(Color.BLACK);
		lblinsertarDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblinsertarDni.setBounds(339, 132, 147, 14);
		contentPane.add(lblinsertarDni);
		
		JLabel lblNewLabel = new JLabel("Crédito:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(39, 232, 57, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(257, 232, 65, 14);
		contentPane.add(lblNewLabel_1);
		

		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(39, 273, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(120, 129, 156, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(120, 191, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		label = new JLabel("Nombre");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label.setBounds(120, 232, 86, 14);
		contentPane.add(label);
		
		
		label_2 = new JLabel("Nombre");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_2.setBounds(317, 191, 86, 14);
		contentPane.add(label_2);
		
		label_3 = new JLabel("Nombre");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_3.setBounds(317, 232, 86, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("Nombre");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_4.setBounds(120, 273, 86, 14);
		contentPane.add(label_4);
		
		
		JButton btnNewButton_1 = new JButton("Ir");
		btnNewButton_1.setBounds(286, 127, 46, 25);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*try {
					if(!textField.getText().trim().isEmpty()){
						Cliente cl = Controlador.getInstancia().buscarCliente(textField.getText());
						if(cl != null){
							ClienteVO c = cl.toView();
							lblNewLabel_3.setText(c.getNombre());
							label_1.setText(c.getApellido());
							label.setText(c.getDomicilio());
							label_1.setText(c.getMail());
							label_2.setText(c.getApellido());
							label_3.setText(c.getTelefono());
						}
						else{
							JOptionPane.showMessageDialog(null, "El cliente no existe", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							textField.setText(null);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Debe ingresar un dni", "Warning", JOptionPane.WARNING_MESSAGE);
				} catch (ConnectionException e1) {
					JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ClienteException e1) {
					JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}*/
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {					
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().trim().isEmpty())
		/*		{
					try {					
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro que desea borrar el cliente?", "Warning", dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
						//	Controlador.getInstancia().darBajaCliente(textField.getText());
							JOptionPane.showMessageDialog(null, "El cliente ha sido dado de baja", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						}
						textField.setText(null);
						lblNewLabel_3.setText(null);
						label_1.setText(null);
						label.setText(null);
						label_1.setText(null);
						label_2.setText(null);
						label_3.setText(null);
					} catch (ClienteException e1){
						JOptionPane.showMessageDialog(null, "Fallo con el cliente", "Error", JOptionPane.ERROR_MESSAGE);
					} catch(ConnectionException e2){
						JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}*/
					JOptionPane.showMessageDialog(null, "El cliente ha sido dado de baja", "Éxito", JOptionPane.INFORMATION_MESSAGE); //Borrar Luego.
				else
					JOptionPane.showMessageDialog(null, "Debe ingresar un CUIT", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		ventana.getFrame().getContentPane().add(contentPane);
		ventana.getFrame().repaint();
	}

}