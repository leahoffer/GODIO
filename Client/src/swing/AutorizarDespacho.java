package swing;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AutorizarDespacho {

	private ventanaPrincipal ventana;
	private JPanel contentPane;
	private JLabel textField;
	
	private JButton buttonAutorizar;
	
	@SuppressWarnings("rawtypes")
	private JList itemList, listaDespachosList;
	 @SuppressWarnings("rawtypes")
	private DefaultListModel listaDespachos, items;
   private  JButton buttonin, buttonout;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AutorizarDespacho(ventanaPrincipal p){
		
		ventana = p;
		ventana.getPanel().removeAll();
		contentPane = p.getPanel();
		
		
		  // Create the final Panel.
      
        // Instantiate the List Models.
        listaDespachos = new DefaultListModel();
        items = new DefaultListModel();
        

        // Things to be in the list.
        String listaDespachosItems[] = {"Milk", "Cheese", "Bread", "Butter", "Beans",
        "Soup", "Bacon", "Chicken", "Curry Sauce", "Chocolate","Chocolate","Chocolate","Chocolate"};

        // Using a for loop, we add every item in the String array
        // into the ListModel.

        for(int i = 0; i < listaDespachosItems.length; i++)
        {
            listaDespachos.addElement(listaDespachosItems[i]);
        }

        // Creation of the list.
        // We set the cells in the list to be 20px x 140px.
        textField = new JLabel("Despachar Pedidos");
        textField.setFont(new Font("Broadway", Font.BOLD, 20));
        textField.setBounds(180, 10, 280, 56);
		contentPane.add(textField);
        
        
        itemList = new JList(listaDespachos);
        itemList.setVisibleRowCount(10);
        itemList.setFixedCellHeight(20);
        itemList.setFixedCellWidth(140);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        // We then add them to a JScrollPane.
        // This means when we remove items from the JList
        // it will not shrink in size.
        JScrollPane list1 = new JScrollPane(itemList);
        
        listaDespachosList = new JList(items);
        listaDespachosList.setVisibleRowCount(10);
        listaDespachosList.setFixedCellHeight(20);
        listaDespachosList.setFixedCellWidth(140);
        listaDespachosList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
    
        JScrollPane list2 = new JScrollPane(listaDespachosList);
        list1.setBounds(90,50, 140, 240);
        list2.setBounds(350, 50, 140, 240);

    
        buttonin = new JButton(">>");
        buttonin.setBounds(265,100,50,20);
        buttonin.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 int i = 0;
				 int[] fromindex = itemList.getSelectedIndices();
		            @SuppressWarnings("deprecation")
					Object[] from = itemList.getSelectedValues();

		            for(i = 0; i < from.length; i++)
		            {
		                items.addElement(from[i]);
		            }
		       
		            for(i = (fromindex.length-1); i >=0; i--)
		            {
		                listaDespachos.remove(fromindex[i]);
		            }
				
			}
		});
        
        contentPane.add(buttonin);
     
        buttonout = new JButton("<<");
        buttonout.setBounds(265,125, 50,20);
        buttonout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int i = 0;
				 @SuppressWarnings("deprecation")
				Object[] to = listaDespachosList.getSelectedValues();
		            int[] toindex = listaDespachosList.getSelectedIndices();
		            
		          
		            for(i = 0; i < to.length; i++)
		            {
		                listaDespachos.addElement(to[i]);
		            }
		            
		         
		            for(i = (toindex.length-1); i >=0; i--)
		            {
		                items.remove(toindex[i]);
		            }
			}
		});
        contentPane.add(buttonout);

        buttonAutorizar = new JButton("Autorizar");
        buttonAutorizar.setBounds(243,300, 100,20);
        buttonAutorizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
        contentPane.add(buttonAutorizar);
        
        
        contentPane.add(list1);       
        contentPane.add(list2);      
        contentPane.setOpaque(true);
    	ventana.getFrame().getContentPane().add(contentPane);
		ventana.getFrame().repaint();
    }

    // In this method, we create a square JPanel of a colour and set size
    // specified by the arguments.

    @SuppressWarnings("unused")
	private JPanel createSquareJPanel(Color color, int size) {
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(color);
        tempPanel.setMinimumSize(new Dimension(size, size));
        tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        return tempPanel;
    }

    // valueChanged is the method that deals with a ListSelectionEvent.
    // This simply changes the boxes that are selected to true.

    

  
    

}