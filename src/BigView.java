

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class BigView {
	Controller control = new Controller();
	private JFrame frame;
	ViewView panel;
	ViewView panel2;	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		Controller control = new Controller();
		Model dataSet = new Model();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					BigView window = new BigView(control, dataSet);
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
	public BigView(Controller control, Model dataSet) {
		initialize(control, dataSet);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controller control, Model dataSet) {		
		frame = new JFrame();
		frame.setBounds(100, 100, 1026, 987);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
						
		panel = new LeftView(control, dataSet);
		panel.setSize(452, 896);
		panel.setLocation(0, 32);
		frame.getContentPane().add(panel);
		

		panel2 = new RightView(control, dataSet);
		panel2.setBounds(466, 32, 658, 896); 
		frame.getContentPane().add(panel2);		
		
		//synchronize left panel and right panel
		JScrollBar scrLeft = panel.scrollPane.getVerticalScrollBar();
		JScrollBar scrRight = panel2.scrollPane.getVerticalScrollBar();
		scrLeft.setModel(scrRight.getModel());
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					control.compareText(dataSet, panel, panel2);
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
					control.copyToRight(dataSet, panel, panel2);
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
					control.copyToLeft(dataSet, panel, panel2);
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
					control.AllcopyToLeft(dataSet, panel, panel2);
			
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
					control.AllcopyToRight(dataSet, panel, panel2);
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