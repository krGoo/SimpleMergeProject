import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

public class RightView extends ViewView {
	
	public RightView(Controller control, Model dataSet) {
		super(control, dataSet);
		// TODO Auto-generated constructor stub
	}

	public void initialize(Controller control, Model dataSet) {
		setBounds(14, 12, 468, 503);
		setLayout(null);		
		
	
		loadbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fs = new JFileChooser(new File("c:\\"));
			    fs.setDialogTitle("Open a File");
			    fs.setFileFilter(new FileTypeFilter(".dat", "Data File"));
			    fs.setFileFilter(new FileTypeFilter(".hwp", "Hwp File"));
			    fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
			    
			    int result = fs.showOpenDialog(null);
			    if(result == JFileChooser.APPROVE_OPTION) {
			     File file = fs.getSelectedFile();
			     	
			     try {
			    	 control.loadFile(dataSet, file, 1);
			    	 control.saveStringToModel(dataSet,1);
			    	 showloadedFile(1, dataSet);
			    	 scrollPane.getVerticalScrollBar().setModel(new JScrollBar().getModel());
			     }
			     catch (Exception e) {
			    	 JOptionPane.showMessageDialog(null, e.getMessage());
			     }
			    }
			}
		});
		loadbtn.setBounds(0, 12, 105, 27);
		add(loadbtn);		
		
		
	
		editbtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
			if(textPane.isEditable() == false)
			textPane.setEditable(true);
			else{
				textPane.setEditable(false);
			}
			}
		});
		editbtn.setBounds(232, 0, 109, 78);
		editbtn.setBounds(106, 12, 105, 27);
		add(editbtn);
		
		
		
	
		savebtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
					String content = textPane.getText();
					File file = fs.getSelectedFile();
					try{
						control.saveFile(dataSet, file, content, 1);
					}
					catch (Exception e){
						JOptionPane.showMessageDialog(null,  e.getMessage());
					}
				}
				
			}
		});
		
		savebtn.setBounds(116, 0, 117, 78);
		savebtn.setBounds(206, 12, 105, 27);
		add(savebtn);
		

		scrollPane.setBounds(10, 51, 394, 440);
		add(scrollPane);
		
		scrollPane.setViewportView(textPane);
		
		textpaneListener(textPane, control);
	}
	
	public void showloadedFile(int num, Model dataSet){		
		textPane.setText(dataSet.getString(1));		
	}
}