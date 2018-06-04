package swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dto.CuentaCorrienteDTO;

public class AltaCuentaCorriente {
	
	private JPanel contentPane;
	private String rol;
    private AltaCuentaCorriente vent;
    private JTextField saldo;
    private JTextField limite;
	private JFrame ventana;
	private JPanel panel2;
	private CuentaCorrienteDTO cuenta;
	
	
	public AltaCuentaCorriente() {
		
		
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
	    
	    contentPane.setBounds(0, 0, 300, 200);
	    
		cuenta = new CuentaCorrienteDTO();
		
		this.ventana = new JFrame("Cuenta Corriente");
		this.ventana.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.ventana.setPreferredSize(new Dimension(300,200));
		this.ventana.setResizable(false);
		this.ventana.getContentPane().setLayout(null);
		this.ventana.setLocationByPlatform(true);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		this.ventana.setVisible(true);
		this.ventana.pack();
		
		JLabel lblNombre = new JLabel("<html>Saldo: <html>");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(32, 54, 61, 35);
		contentPane.add(lblNombre);
		
		JLabel lblNombre2 = new JLabel("<html>Limite: <html>");
		lblNombre2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre2.setBounds(32, 94, 61, 35);
		contentPane.add(lblNombre2);
		
		 
		 saldo = new JTextField(); //saldo
		 saldo.setBounds(95, 54, 156, 28);
		 contentPane.add(saldo);
		 
		 limite = new JTextField(); //limite
		 limite.setBounds(95, 94, 156, 28);
		 contentPane.add(limite);
	
		 JButton btnNewButton = new JButton("Guardar");
			btnNewButton.setBounds(150, 130, 104, 31);
			contentPane.add(btnNewButton);
			
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (!limite.getText().trim().isEmpty() & !saldo.getText().trim().isEmpty())
					try {
						cuenta.setLimite(Float.parseFloat(limite.getText().toString()));
						cuenta.setSaldo(Float.parseFloat(saldo.getText().toString()));
						vent.getFrame().dispose();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Ingresar con el formato 1200.00", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Faltan datos", "Warning", JOptionPane.WARNING_MESSAGE);
					
				}
			});
			
			JButton btnNewButton2 = new JButton("Cancelar");
			btnNewButton2.setBounds(40, 130, 104, 31);
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
	
	public CuentaCorrienteDTO getCuenta()
	{
		return cuenta;
	}
	public JPanel getPanel(){
		return contentPane;
	}
	
	public String getRol(){
		return rol;
	}
}


