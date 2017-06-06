
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
	
	public viewview(Controller control, Model dataSet) {
		initialize(control, dataSet);
	}
	
	public abstract void initialize(Controller control, Model dataSet);
	
	abstract public void showloadedFile(int num, Model dataSet);

	public void textpaneListener(JTextArea textpane, Controller control){
		
	
	textPane.addCaretListener(new CaretListener() {               
        public void caretUpdate(CaretEvent e) {
            JTextArea editArea = (JTextArea)e.getSource();
            try {
                int caretpos = editArea.getCaretPosition();
                control.setLineNum(editArea.getLineOfOffset(caretpos));
                control.incereaselineNum();
            }
            catch(Exception ex) { }               
        }            
    });
	}
}