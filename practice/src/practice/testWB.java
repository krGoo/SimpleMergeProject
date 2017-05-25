package practice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.GroupLayout.Alignment;

public class testWB extends JFrame {

	private JPanel contentPane;
	private JTextField txtTestFile;
	private JTextField txtTestFile_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testWB frame = new testWB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testWB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		txtTestFile = new JTextField();
		txtTestFile.setText("Test File 1");
		panel_1.add(txtTestFile);
		txtTestFile.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
		txtTestFile_1 = new JTextField();
		txtTestFile_1.setText("Test File 2");
		panel.add(txtTestFile_1);
		txtTestFile_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		Dimension dim = new Dimension(50,30);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_2 = new JButton("<-");
		panel_2.add(btnNewButton_2);
		btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnNewButton_1 = new JButton("Compare");
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		
		JButton btnNewButton = new JButton("->");
		panel_2.add(btnNewButton);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

}
