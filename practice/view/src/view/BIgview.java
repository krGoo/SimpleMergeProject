package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BIgview {

	private JFrame frame;
	JPanel panel;
	JPanel panel2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BIgview window = new BIgview();
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
	public BIgview() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1026, 987);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		//small1= new smallview();
		//small2= new smallview();
		//small1.setVisible(true);
		//small2.setVisible(true);
		
		panel = new viewview();
		panel.setSize(452, 896);
		panel.setLocation(0, 32);
		frame.getContentPane().add(panel);
		

		panel2 = new viewview();
		panel2.setBounds(466, 32, 658, 896); 
		frame.getContentPane().add(panel2);
		
		
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(0, 0, 105, 27);
		frame.getContentPane().add(btnCompare);
		
		JButton btnCopyToRight = new JButton("Copy to Right");
		btnCopyToRight.setBounds(102, 0, 123, 27);
		frame.getContentPane().add(btnCopyToRight);
		
		JButton btnNewButton = new JButton("Copy to Left");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(219, 0, 123, 27);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
