package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BIgview {

	private JFrame frame;
	viewview panel;
	viewview panel2;	
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
		
		
		panel = new leftview();
		panel.setSize(452, 896);
		panel.setLocation(0, 32);
		frame.getContentPane().add(panel);
		

		panel2 = new rightview();
		panel2.setBounds(466, 32, 658, 896); 
		frame.getContentPane().add(panel2);		
		
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Controller.compareText(panel, panel2);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  "hi");
				}
			}
		});		
		btnCompare.setBounds(0, 0, 105, 27);
		frame.getContentPane().add(btnCompare);
		
		
		
		JButton btnCopyToRight = new JButton("Copy to Right");
		btnCopyToRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Controller.copyToRight(panel, panel2);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});		
		btnCopyToRight.setBounds(102, 0, 123, 27);
		frame.getContentPane().add(btnCopyToRight);
		
		
		
		JButton btnCopyToLeft = new JButton("Copy to Left");
		btnCopyToLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Controller.copyToLeft(panel, panel2);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});
		btnCopyToLeft.setBounds(219, 0, 123, 27);
		frame.getContentPane().add(btnCopyToLeft);
		
		
		JButton btnAllCopyToLeft = new JButton("All Copy to Left");
		btnAllCopyToLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Controller.AllcopyToLeft(panel, panel2);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});
		btnAllCopyToLeft.setBounds(340, 0, 123, 27);
		frame.getContentPane().add(btnAllCopyToLeft);
		
		
		JButton btnAllCopyToRight = new JButton("All Copy to Right");
		btnAllCopyToRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Controller.AllcopyToRight(panel, panel2);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});
		btnAllCopyToRight.setBounds(460, 0, 130, 27);
		frame.getContentPane().add(btnAllCopyToRight);
		
		
		
	}
}
