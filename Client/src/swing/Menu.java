package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Menu {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 250);
		frame.getContentPane().add(tabbedPane);
		
		JPanel Alta = new JPanel();
		tabbedPane.addTab("Alta Cliente", null, Alta, null);
		Alta.setLayout(null);
		
		JLabel LBLcuit = new JLabel("CUIT");
		LBLcuit.setFont(new Font("Tahoma", Font.BOLD, 14));
		LBLcuit.setBounds(83, 45, 36, 14);
		Alta.add(LBLcuit);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setBounds(55, 70, 67, 14);
		Alta.add(lblDireccion);
		
		JLabel lblRESP = new JLabel("Responsable Inscripto");
		lblRESP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRESP.setBounds(30, 145, 163, 17);
		Alta.add(lblRESP);
		
		JLabel lblrazonsocial = new JLabel("Raz\u00F3n Social");
		lblrazonsocial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblrazonsocial.setBounds(30, 95, 92, 14);
		Alta.add(lblrazonsocial);
		
		JLabel lblphone = new JLabel("Tel\u00E9fono");
		lblphone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblphone.setBounds(59, 120, 60, 14);
		Alta.add(lblphone);
		
		textField = new JTextField();
		textField.setBounds(132, 44, 219, 20);
		Alta.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 69, 219, 20);
		Alta.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(132, 94, 219, 20);
		Alta.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(132, 119, 219, 20);
		Alta.add(textField_3);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBounds(199, 144, 41, 23);
		Alta.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(242, 144, 41, 23);
		Alta.add(rdbtnNo);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
	}
}
