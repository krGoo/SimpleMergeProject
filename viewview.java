package view;


import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

abstract class viewview extends JPanel{
	JButton savebtn = new JButton("Save");
	JButton editbtn = new JButton("Edit");
	JButton loadbtn = new JButton("Load");
	JScrollPane scrollPane= new JScrollPane();
	JTextArea textPane = new JTextArea();

	ArrayList<String> file_String = new ArrayList<String>();
	ArrayList<Boolean> file_Boolean = new ArrayList<Boolean>();
	
	public viewview() {
		initialize();
	}
	
	abstract public void initialize();
	
	abstract public void showloadedFile(int num);

	public void textpaneListener(JTextArea textpane){
		
	
	textPane.addCaretListener(new CaretListener() {               
        public void caretUpdate(CaretEvent e) {
            JTextArea editArea = (JTextArea)e.getSource();
            try {
                int caretpos = editArea.getCaretPosition();
                Controller.setLineNum(editArea.getLineOfOffset(caretpos));
                Controller.incereaselineNum();
            }
            catch(Exception ex) { }               
        }            
    });
	}
}