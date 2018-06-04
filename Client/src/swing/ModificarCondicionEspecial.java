
	
	package swing;


	import java.awt.Color;
	import java.awt.Font;
	import java.awt.HeadlessException;
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


	public class ModificarCondicionEspecial {

		private ventanaPrincipal ventana;
		private JPanel contentPane;
		private JTextField textField;
		private JLabel textField_1;
		private JLabel textField_2;
		private JTextField textField_3;
		private JLabel textField_4;
		private JLabel textField_5;
		private ClienteDTO cliente;
		private CuentaCorrienteDTO cuenta;
		private ModificarCuentaCorriente ventanaCuenta;
		private JCheckBox radio;
		
		
		public ModificarCondicionEspecial(ventanaPrincipal p ){
		
			ventana = p;
			
			ventana.getPanel().removeAll();
			contentPane = p.getPanel();
		    
		
			
			JLabel lblBuscar = new JLabel("Buscar: ");
			lblBuscar.setForeground(Color.BLACK);
			lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblBuscar.setBounds(39, 129, 57, 21);
			contentPane.add(lblBuscar);
			
			JLabel lblNombre = new JLabel("Razón Social: ");
			lblNombre.setForeground(Color.BLACK);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNombre.setBounds(39, 191, 147, 14);
			contentPane.add(lblNombre);
			
			JLabel lblModificacinCliente = new JLabel("Modificar Cond. Especial");
			lblModificacinCliente.setForeground(Color.BLACK);
			lblModificacinCliente.setFont(new Font("Broadway", Font.BOLD, 20));
			lblModificacinCliente.setBounds(164, 62, 300, 31);
			contentPane.add(lblModificacinCliente);
			
			JLabel lblinsertarDni = new JLabel("(ingresar CUIT)");
			lblinsertarDni.setForeground(Color.BLACK);
			lblinsertarDni.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblinsertarDni.setBounds(339, 132, 147, 14);
			contentPane.add(lblinsertarDni);
			
			JLabel lblNewLabel = new JLabel("Direccion:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(39, 232, 57, 14);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel4 = new JLabel("Cond. Especial:");
			lblNewLabel4.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel4.setBounds(39, 273, 100, 14);
			contentPane.add(lblNewLabel4);
			
			JLabel lblNewLabel_1 = new JLabel("Tel\u00E9fono:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setBounds(296, 232, 65, 14);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_resp = new JLabel("Responsable Inscripto");
			lblNewLabel_resp.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_resp.setBounds(296, 191, 147, 14);
			contentPane.add(lblNewLabel_resp);
			
			
			
			JLabel lblNewLabel_radio = new JLabel();
			lblNewLabel_radio.setBounds(450, 184, 156, 28);
			contentPane.add(lblNewLabel_radio);
			
				
			
			textField = new JTextField();  
			textField.setBounds(120, 129, 156, 28);
			contentPane.add(textField);
			
			
			textField_1 = new JLabel(); 
			textField_1.setBounds(120, 182, 156, 28);
			contentPane.add(textField_1);
			
			textField_2 = new JLabel(); 	
			textField_2.setBounds(120, 224, 156, 28);
			contentPane.add(textField_2);
			
			textField_3 = new JTextField(); 	
			textField_3.setBounds(120, 268, 156, 28);
			contentPane.add(textField_3);
		
			
			textField_5 = new JLabel();  
			textField_5.setBounds(363, 225, 156, 28);
			contentPane.add(textField_5);
	
			
			JButton btnNewButton_1 = new JButton("Ir");
			btnNewButton_1.setBounds(286, 127, 46, 25);
			contentPane.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent arg0) {				
					
						try {
							if(!textField.getText().trim().isEmpty()){
							   cliente = BusinessDelegate.getInstance().traerCliente(textField.getText());
							   if(cliente == null){
									JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
									textField.setText(null);
								
							   }
							   else {
									
									textField_1.setText(cliente.getRazon_social());
								    textField_3.setText(cliente.getCondicionEsp());
									textField_2.setText(cliente.getDireccion());
    								textField_5.setText(cliente.getTelefono());							
								
								}
							}
							else
								JOptionPane.showMessageDialog(null, "Debe ingresar un CUIT", "Warning", JOptionPane.WARNING_MESSAGE);
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
						
							e.printStackTrace();
						} catch (RemoteException e) {
							JOptionPane.showMessageDialog(null, "Error de Conexion", "Error", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						
						} catch (ClienteException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
							
						}
				
							
				}});		
			
			JButton btnNewButton = new JButton("Aceptar");
			btnNewButton.setBounds(437, 302, 104, 31);
			contentPane.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(!textField.getText().trim().isEmpty()){
							ClienteDTO c = new ClienteDTO();
						
							c.setCondicionEsp(textField_3.getText());
							c.setDireccion(textField_2.getText());
							c.setRazon_social(textField_1.getText());
							c.setTelefono(textField_5.getText());
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro que desea modificar el cliente?", "Warning", dialogButton);
							if(dialogResult == JOptionPane.YES_OPTION){
								BusinessDelegate.getInstance().modificarCliente(c);
								JOptionPane.showMessageDialog(null, "Se modificó el cliente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Debe ingresar un CUIT", "Warning", JOptionPane.WARNING_MESSAGE);
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(null, "Fallo en la conexión", "Error", JOptionPane.ERROR_MESSAGE);
					} catch(ClienteException e2){
						JOptionPane.showMessageDialog(null, "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
					}
					textField.setText(null);
					textField_1.setText(null);
					textField_3.setText(null);
					textField_2.setText(null);
					textField_5.setText(null);
				
				}
			});
		
			ventana.getFrame().getContentPane().add(contentPane);
			ventana.getFrame().repaint();
			
		}
		
	
}
