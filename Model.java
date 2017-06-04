package view;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Model {
	
	static File[] file = new File[2];
	static ArrayList<String> left_String = new ArrayList<String>();
	static ArrayList<String> right_String = new ArrayList<String>();
	static ArrayList<Boolean> left_Boolean = new ArrayList<Boolean>();
	static ArrayList<Boolean> right_Boolean = new ArrayList<Boolean>();
	
	public static void load(File ldfile, int num){
	
		file[num] = ldfile;
		
	}
	
	public static void save(File svfile, String content, int num) throws IOException{
		
		FileWriter fw = new FileWriter(svfile.getPath() + ".txt");
		fw.write(content);
		fw.flush();
		fw.close();
		
	}	
	
	public static File getfile(int num){
		return file[num];
	}
	
	public static String getString(int num) { //file�쓽 �궡�슜�쓣 string�쑝濡� 諛섑솚
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
	
	public static void saveString(int num) { //file�쓽 �궡�슜�쓣 string�쑝濡� 諛섑솚	
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
	
	public static Boolean getBoolean(String s, int num) {
		if(s.equals("left"))
			return left_Boolean.get(num);
		else
			return right_Boolean.get(num);			
	}
	
	public static String getString(String s, int num) {
		if(s.equals("left"))
			return left_String.get(num);
		else
			return right_String.get(num);
			
	}
	
	public static void setBoolean(String s, int num, Boolean bool) {
		if(s.equals("left"))
			left_Boolean.set(num,  bool);
		else
			right_Boolean.set(num,  bool);
	}	
	
	public static void setString(String s, int num, String str) {
		if(s.equals("left"))
			left_String.set(num,  str);
		else
			right_String.set(num,  str);
	}
	
	public static int getSize(String s) {
		if(s.equals("left"))
			return left_String.size();
		else
			return right_String.size();
	}

}

