package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class rightview extends viewview {
	
	public void initialize() {
		setBounds(14, 12, 468, 503);
		setLayout(null);		
		
	
		loadbtn.addActionListener(new ActionListener() { //불러오기
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fs = new JFileChooser(new File("c:\\"));
			    fs.setDialogTitle("Open a File");
			    fs.setFileFilter(new FileTypeFilter(".dat", "Data File"));
			    fs.setFileFilter(new FileTypeFilter(".hwp", "한글 파일"));
			    fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
			    
			    int result = fs.showOpenDialog(null);
			    if(result == JFileChooser.APPROVE_OPTION) {
			     File file = fs.getSelectedFile();
			     	
			     try {
			    	 Controller.loadFile(file, 1); //컨트롤러 호출
			    	 Controller.saveStringToModel(1);
			    	 showloadedFile(1);//모델에서 데이터 get
			     }
			     catch (Exception e) {
			    	 JOptionPane.showMessageDialog(null, e.getMessage());
			     }
			    }
			}
		});
		loadbtn.setBounds(0, 12, 105, 27);
		add(loadbtn);		
		
		
	
		editbtn.addActionListener(new ActionListener() { //저장하기
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
		
		
		
	
		savebtn.addActionListener(new ActionListener() { //저장하기
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
					String content = textPane.getText();
					File file = fs.getSelectedFile();
					try{
						Controller.saveFile(file, content, 1);
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
		
		textpaneListener(textPane);
	}
	
	public void showloadedFile(int num){		
		textPane.setText(Model.getString(1));		
	}
}
