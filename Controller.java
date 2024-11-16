import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller implements ActionListener, DocumentListener{
	private GUI gui;
	private Model model;
	
	public Controller(GUI gui, Model model) {
		this.gui = gui;
		this.model = model;
		gui.setActionListener(this);
		//gui.setDocumentListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//JButton source = (JButton) e.getSource();
		//System.out.println("Button pressed: " + source.getText());
		
		switch(command) {
		
		case "TableInput":
			System.out.println("You clicked TableInput");
			gui.createTableInputPanel();
	
			break;
			
		case "RecordManagement":
			System.out.println("You clicked TableManagement");
		
			break;
		
		case "Reports":
			System.out.println("You clicked Reports");
			
			break;
			
		case "EXIT":
			System.exit(0);
			break;
			
		case "Home":
			System.out.println("return to main");
			gui.createMainMenuPanel();
			break;
			
		}
	}
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}


}
