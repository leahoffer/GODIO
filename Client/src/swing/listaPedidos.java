package swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;




public class listaPedidos {

	
private JPanel contentPane;
private String rol;
private listaPedidos vent;
private JTextField saldo;
private JTextField limite;
private JFrame ventana;
private JPanel panel2;
private DefaultListModel<Integer> listaPedido;
private List<PedidoDTO> pedidos;
private PedidoDTO pedidoDTO;
private JList itemList;

public listaPedidos (){


	try{			  
		  ventana.setDefaultLookAndFeelDecorated(true);			  
		  
		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	//	  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		  //UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel");
	//	  UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel");
		//  UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel");
		 // UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
		  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	} catch (Exception e) {
		e.printStackTrace();
	}
	contentPane = new JPanel();
    contentPane.setLayout(null);
    
    contentPane.setBounds(0, 0, 300, 400);
    
    
    
    try {
	pedidos = BusinessDelegate.getInstance().listarPedidosPendientes();
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Error de Conexión", "Error", JOptionPane.ERROR_MESSAGE);
	}
    
    listaPedido = new DefaultListModel();
    
	
	this.ventana = new JFrame("Pedidos");
	this.ventana.setFont(new Font("Tahoma", Font.BOLD, 11));
	this.ventana.setPreferredSize(new Dimension(300,400));
	this.ventana.setResizable(false);
	this.ventana.getContentPane().setLayout(null);
	this.ventana.setLocationByPlatform(true);
	this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

	this.ventana.setVisible(true);
	this.ventana.pack();
	
	
	for (PedidoDTO ped : pedidos)
	{
		listaPedido.addElement(ped.getNroPedido());
	}
	
	  itemList = new JList(listaPedido);
      itemList.setVisibleRowCount(10);
      itemList.setFixedCellHeight(20);
      itemList.setFixedCellWidth(140);
      itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      JScrollPane list1 = new JScrollPane(itemList);
      list1.setBounds(70,50, 140, 200);
      contentPane.add(list1);    
	

	 JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.setBounds(150,280, 104, 31);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!itemList.isSelectionEmpty())
				try{
				    
					for (PedidoDTO ped : pedidos)
					{
						if ( ped.getNroPedido() == (Integer)itemList.getSelectedValue())
						{
							pedidoDTO = ped;
						}
							
					}
					vent.getFrame().dispose();	
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ingresar con el formato 1200.00", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Elija un pedido", "Warning", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		
		JButton btnNewButton2 = new JButton("Cancelar");
		btnNewButton2.setBounds(40, 280, 104, 31);
		contentPane.add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vent.getFrame().dispose();
			}
		});
	 
	 	
     ventana.repaint();
     ventana.getContentPane().add(contentPane);
	 vent = this;
	 
	
}

public JFrame getFrame(){
	return ventana;
}


public JPanel getPanel(){
	return contentPane;
}

public String getRol(){
	return rol;
}

public PedidoDTO getPedido()
{	
	return pedidoDTO;
}

}



