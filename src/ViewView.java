import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

abstract class ViewView extends JPanel{
	JButton savebtn = new JButton("Save");
	JButton editbtn = new JButton("Edit");
	JButton loadbtn = new JButton("Load");
	JScrollPane scrollPane= new JScrollPane();
	JTextArea textPane = new JTextArea();
	
	public ViewView(Controller control, Model dataSet) {
		initialize(control, dataSet);
		textPane.setEditable(false);
	}
	
	public abstract void initialize(Controller control, Model dataSet);
	
	public abstract void showloadedFile(int num, Model dataSet);

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