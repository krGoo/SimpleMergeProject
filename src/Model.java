import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Model {
	
	 File[] file = new File[2];
	 ArrayList<String> left_String = new ArrayList<String>();
	 ArrayList<String> right_String = new ArrayList<String>();
	 ArrayList<Boolean> left_Boolean = new ArrayList<Boolean>();
	 ArrayList<Boolean> right_Boolean = new ArrayList<Boolean>();
	
	public  void load(File ldfile, int num){
	
		file[num] = ldfile;
		
	}
	
	public  void save(File svfile, String content, int num) throws IOException{
		
		FileWriter fw = new FileWriter(svfile.getPath() + ".txt");
		fw.write(content);
		fw.flush();
		fw.close();
		
	}	
	
	public  File getfile(int num){
		return file[num];
	}
	
	public  String getString(int num) { 
		String s = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(file[num]));
		      String line = "";
		      while((line = br.readLine()) != null)
		      {
		       s += line + "\n";
		      }
		      if (br != null)
		       br.close();
		     }
			catch(Exception e)
		     {
			      JOptionPane.showMessageDialog(null, e.getMessage());
			     }
			return s;
	}
	
	public  void saveString(int num) { 
		if(num == 0) 
    		left_String.clear();
    	else
    		right_String.clear();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file[num]));
		    String line = "";
		    
		    while((line = br.readLine()) != null) {
		    	if(num == 0) 
		    		left_String.add(line);
		    	else
		    		right_String.add(line);
		    }
		      
		    if (br != null)
		       br.close();		    
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}	
	
	public  Boolean getBoolean(String s, int num) {
		if(s.equals("left"))
			return left_Boolean.get(num);
		else
			return right_Boolean.get(num);			
	}
	
	public  String getString(String s, int num) {
		if(s.equals("left"))
			return left_String.get(num);
		else
			return right_String.get(num);			
	}
	
	public  void setBoolean(String s, int num, Boolean bool) {
		if(s.equals("left"))
			left_Boolean.set(num,  bool);
		else
			right_Boolean.set(num,  bool);
	}	
	
	public  void setString(String s, int num, String str) {
		if(s.equals("left"))
			left_String.set(num,  str);
		else
			right_String.set(num,  str);
	}
	
	public  int getSize(String s) {
		if(s.equals("left"))
			return left_String.size();
		else
			return right_String.size();
	}
	
	public  void addString(String s, int num, String str) {
		if(s.equals("left"))
			left_String.add(num,  str);
		else
			right_String.add(num,  str);
	}
	
	public  void addBoolean(String s, int num, Boolean bool) {
		if(s.equals("left"))
			left_Boolean.add(num,  bool);
		else
			right_Boolean.add(num,  bool);
	}
	
	public  void addBoolean(String s, Boolean bool) {
		if(s.equals("left"))
			left_Boolean.add(bool);
		else
			right_Boolean.add(bool);
	}
	
	public  void addString(String s, String str) {
		if(s.equals("left"))
			left_String.add(str);
		else
			right_String.add(str);
	}

}
