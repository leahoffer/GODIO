package swing;


import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;



public class AutorizarPedido {
	
	private ventanaPrincipal ventana;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_3; 
	private JLabel label;
	@SuppressWarnings("unused")
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private listaPedidos ventanaPedidos;
	private PedidoDTO pedido;
	
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
		
		JLabel lblBuscar = new JLabel("CUIT: ");
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
		
	/*	JLabel lblinsertarDni = new JLabel("(ingresar Pedido)");
		lblinsertarDni.setForeground(Color.BLACK);
		lblinsertarDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblinsertarDni.setBounds(399, 132, 147, 14);
		contentPane.add(lblinsertarDni);*/
		
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
		
		
		
		label_5 = new JLabel("Nombre");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_5.setBounds(120, 138, 86, 14);
		contentPane.add(label_5);
		
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
		label_3.setBounds(317, 232, 186, 14);
		contentPane.add(label_3);
		
		label_4 = new JLabel("Nombre");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_4.setBounds(120, 273, 86, 14);
		contentPane.add(label_4);
		
	
		
		
		JButton btnNewButton_1 = new JButton("Seleccionar");
		btnNewButton_1.setBounds(286, 127, 106, 25);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ventanaPedidos = 	new listaPedidos();
		
				/*
				try {
					if(!textField.getText().trim().isEmpty()){
					   pedido = BusinessDelegate.getInstance().listarPedidosPendientes();*/
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
				
				
				try {
					if (pedido != null)
					{
						try {
							BusinessDelegate.getInstance().autorizarPedido(pedido.getNroPedido());
							JOptionPane.showMessageDialog(null, "Se ha Autorizado El pedido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							pedido = null;
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						
						
					}
				}  catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No se seleccionó ningun Pedido", "Warning", JOptionPane.WARNING_MESSAGE);
				}
		
				
			}
		});
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton boton3 = new JButton("Refresh");
		boton3 .setBounds(399, 127, 106, 25);
		contentPane.add(boton3 );
		
		boton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			
			
				try {
					if (ventanaPedidos.getPedido() != null)
					{
						pedido = ventanaPedidos.getPedido();
						label.setText(String.valueOf(pedido.getCliente().getCuentaCorriente().getSaldo()));
						lblNewLabel_3.setText(pedido.getCliente().getRazon_social());
						label_4.setText(String.valueOf(pedido.getTotal_bruto()));
						label_2.setText(String.valueOf(pedido.getNroPedido()));
						label_3.setText(pedido.getEstado());
						label_5.setText(pedido.getCliente().getCuit());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No se seleccionó ningun Pedido", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			
			

			}
		});
		
		
		
		
	
		ventana.getFrame().getContentPane().add(contentPane);
		ventana.getFrame().repaint();
	}

}