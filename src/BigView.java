
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BigView {
	Controller control = new Controller();
	private JFrame frame;
	ViewView leftView;
	ViewView rightView;	
	
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
		
						
		leftView = new LeftView(control, dataSet);
		leftView.setSize(452, 896);
		leftView.setLocation(0, 32);
		frame.getContentPane().add(leftView);
		

		rightView = new RightView(control, dataSet);
		rightView.setBounds(466, 32, 658, 896); 
		frame.getContentPane().add(rightView);		
		
				
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					control.setCurrentLeftText(dataSet, leftView);
					control.setCurrentRightText(dataSet, rightView);
					control.compareText(dataSet, leftView, rightView);
					control.remakeText(dataSet, leftView, rightView);
					control.Highlighting(dataSet, leftView, rightView);  
					control.setScrollbarSync(true, leftView, rightView);
					leftView.textPane.setEditable(true);
					rightView.textPane.setEditable(true);
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
					control.copyToRight(dataSet, leftView, rightView);
					
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
					control.copyToLeft(dataSet, leftView, rightView);
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
					control.AllcopyToLeft(dataSet, leftView, rightView);
			
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});
		btnAllCopyToLeft.setBounds(460, 0, 130, 27);
		frame.getContentPane().add(btnAllCopyToLeft);
		
		
		JButton btnAllCopyToRight = new JButton("All Copy to Right");
		btnAllCopyToRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					control.AllcopyToRight(dataSet, leftView, rightView);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null,  e.getMessage());
				}
			}
		});
		btnAllCopyToRight.setBounds(340, 0, 127, 27);
		frame.getContentPane().add(btnAllCopyToRight);
		
		
		
		
	}
}