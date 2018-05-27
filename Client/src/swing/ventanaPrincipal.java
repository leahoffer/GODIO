package swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;


public class ventanaPrincipal {

	private JPanel contentPane;
	private String rol;
    private ventanaPrincipal vent;
	private JFrame ventana;
	private JPanel panel2;
	
	
	public ventanaPrincipal( String user) {
		
		
		try{			  
			  ventana.setDefaultLookAndFeelDecorated(true);			  
			  
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			  //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane = new JPanel();
	    contentPane.setLayout(null);
	    contentPane.setBounds(0, 0, 592, 350);
	    
		
		this.ventana = new JFrame("Administración");
		this.ventana.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.ventana.setPreferredSize(new Dimension(590,410));
		this.ventana.setResizable(false);
		this.ventana.getContentPane().setLayout(null);
		this.ventana.setLocationByPlatform(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.ventana.setVisible(true);
		this.ventana.pack();
		
		
		JMenuBar menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("ABM Clientes / Productos");
		menuBar.add(mnNewMenu);
		mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnNewMenu.setEnabled(true);
	
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta Cliente");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				new AltaCliente (vent);
			}
		});
		
		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
		mnNewMenu.add(mntmModificarCliente);
		mntmModificarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmModificarCliente.addActionListener(new ActionListener() {					
			public void actionPerformed(ActionEvent arg0) {			
		     	new ModificarCliente(vent);
			}
		});
		
		JMenuItem mntmBajaCliente = new JMenuItem("Baja Cliente");
		mnNewMenu.add(mntmBajaCliente);
		mntmBajaCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmBajaCliente.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				new BajaCliente(vent);				
			}
		});
		
		mnNewMenu.addSeparator();
		mnNewMenu.addSeparator();
		
		JMenuItem mntmAltaProducto = new JMenuItem("Alta Producto");
		mnNewMenu.add(mntmAltaProducto);
		mntmAltaProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmAltaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new VentanaAltaProducto(ventana);
			}
		});
		
		JMenuItem mntmBajaProducto = new JMenuItem("Modificar Producto");
		mnNewMenu.add(mntmBajaProducto);
		mntmBajaProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmBajaProducto.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
				//new VentanaModificarProducto(ventana);
			}
		});
		
		JMenuItem mntmBajaProducto_1 = new JMenuItem("Baja Producto");
		mnNewMenu.add(mntmBajaProducto_1);
		mntmBajaProducto_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmBajaProducto_1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
				//new VentanaBajaProducto(ventana);			
			}
		});
			
		JMenu mnNewMenu_1 = new JMenu("Call Center");
		menuBar.add(mnNewMenu_1);
		mnNewMenu_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnNewMenu_1.setEnabled(false);
		
		
		JMenu mnNewMenu_2 = new JMenu("Reclamos");
		mnNewMenu_1.add(mnNewMenu_2);
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Crear Reclamo Simple");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new ReclamosSimples(ventana);
			}
		});
		
		JMenuItem menuItem_6 = new JMenuItem("Crear Reclamo Compuesto");
		mnNewMenu_2.add(menuItem_6);
		menuItem_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new ReclamosCompuestos(ventana);
			}
		});
		
		JMenu menu = new JMenu("Tablero de Reclamos");
		menuBar.add(menu);
		menu.setFont(new Font("Tahoma", Font.BOLD, 11));
		menu.setEnabled(false);
	
		
		JMenuItem menuItem_8 = new JMenuItem("Visualizar Reclamos");
		menu.add(menuItem_8);
		menuItem_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_8.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
			//	new VisualizarReclamos(ventana, user);				
			}
		});
		
		JMenu menu_1 = new JMenu("Reportes/Rankings");
		menuBar.add(menu_1);
		menu_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		menu_1.setEnabled(false);

		
		JMenuItem menuItem_10 = new JMenuItem("Ranking Reclamos Cliente");
		menu_1.add(menuItem_10);
		menuItem_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_10.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
			//	new RankingReclamosClientes(ventana);				
			}
		});
		
		JMenuItem menuItem_11 = new JMenuItem("Ranking Tratamientos Reclamos");
		menu_1.add(menuItem_11);
		menuItem_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_11.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
			//	new RankingTratamientosReclamos(ventana);
			}
		});
		
		JMenuItem menuItem_12 = new JMenuItem("Reclamos por mes");
		menu_1.add(menuItem_12);
		menuItem_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_12.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
			//new ReclamosPorMes(ventana);
			}
		});
		
		JMenuItem menuItem_13 = new JMenuItem("Reporte Tiempo Promedio");
		menu_1.add(menuItem_13);
		menuItem_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuItem_13.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
			//	new ReporteTiempoPromedio(ventana);				
			}
		});
		
		JMenu menu_2 = new JMenu("Salir");
		menuBar.add(menu_2);
		menu_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JMenuItem menuItem_14 = new JMenuItem("Logoff");
		menu_2.add(menuItem_14);
		menuItem_14.setFont(new Font("Tahoma", Font.BOLD, 11));		
		menuItem_14.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
				ventana.dispose();
				//new VentanaLogin();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 340, 239, 21);
		panel.setVisible(true);
		ventana.add(panel);
		
		
		JLabel lblNewLabel_2 = new JLabel();
		if( 1 == 1){
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Administrador</font><html>");
		} else if(rol.equals("call center")){
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Call Center</font><html>");
		} else if(rol.equals("consulta")){
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Consulta</font><html>");
		} else if(rol.equals("distribucion")){
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Distribución</font><html>");
		} else if(rol.equals("facturacion")){
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Facturación</font><html>");
		} else
			lblNewLabel_2.setText("<html>Rol: <font color =\"blue\">Zona</font><html>");
		lblNewLabel_2.setBounds(10, 0, 150, 21);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(247, 340, 239, 21);
		ventana.add(panel_1);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 0, 86, 21);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(496, 340, 88, 21);
		ventana.add(panel_2);
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(10, 0, 86, 21);
		panel_2.add(label_1);
		
		Timer timee = new javax.swing.Timer(0, new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
                java.util.Date now = new java.util.Date(); 
                DateFormat a = new SimpleDateFormat("HH:mm:ss");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String ss = a.format(now); 
                label.setText(dateFormat.format(now));
                label_1.setText(ss); 
                label_1.setToolTipText("Welcome, Today is " + ss);   
            } 
        }); 
        timee.start();
		
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
}