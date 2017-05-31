package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Model {
	
	static File[] file = new File[2];
	
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
	public static String getString(int num) { //file의 내용을 string으로 반환
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
	}

