package view;


import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.event.*;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Utilities;


public class Controller {
   static int linenum = 1;
   
   
   public static void loadFile(File ldfile, int num){
      Model.load(ldfile, num);
   }
   
   public static void saveFile(File svfile, String content, int num) throws IOException{
      Model.save(svfile, content, num);
   }
   
   public static void saveStringToModel(int num){
      Model.saveString(num);
   }   
   
   public static void copyToLeft(viewview left, viewview right) {      	      
	  findBox(0, linenum);
	  compareText(left,right);
   } 
   
   public static void copyToRight(viewview left, viewview right) {
	  findBox(1, linenum);
      compareText(left,right);
   }
   
   public static void AllcopyToLeft(viewview left, viewview right) {
      left.textPane.setText(right.textPane.getText());
      right.textPane.setText(left.textPane.getText());
   }
   
   public static void AllcopyToRight(viewview left, viewview right) {
      right.textPane.setText(left.textPane.getText());
      left.textPane.setText(right.textPane.getText());
   }
   
   
   public static void compareText(viewview left, viewview right) {
	   // Clean Boolean
	  Model.left_Boolean.clear();
	  Model.right_Boolean.clear(); 
      
	  LCS_algorithm();
	  
      // Lining with same string               
      for(int k=0; k<Model.getSize("right") && k<Model.getSize("left"); k++) {
         if(Model.getBoolean("left", k).equals(false) && Model.getBoolean("right", k).equals(true)) {
            Model.addString("right", k, "");
            Model.addBoolean("right", k, false);
         }
         else if(Model.getBoolean("left", k).equals(true) && Model.getBoolean("right", k).equals(false) ) {
            Model.addString("left", k, "");
            Model.addBoolean("left", k, false);
         }            
      }      
            
      // set Same line
      setSameLining();      

      // if both lines are empty, their booleans are true
      for(int k=0; k<Model.getSize("right"); k++) {
    	  if(Model.getString("left", k).equals(Model.getString("right", k))) {
    		  Model.setBoolean("left", k, true);
    		  Model.setBoolean("right", k, true);
    	  }
      }
      
      // set Text new
      remakeText(left, right);            
      
      // Highlighting false line
      Highlighting(left, right);       
   }   
   
   
   
   public static void setLineNum(int line){
	   linenum=line;
   }
   public static void incereaselineNum(){
       linenum++;
   }
   
   
   
   public static void setSameLining() {
      while(Model.getSize("left") > Model.getSize("right")) {
    	  Model.addString("right","");
    	  Model.addBoolean("right",false);
      }
      while(Model.getSize("left") < Model.getSize("right")) {
    	  Model.addString("left","");
    	  Model.addBoolean("left",false);
      }
   }
   
   public static void findBox(int num, int line){
	      int up = line - 1;
	      int down = line - 1;
	      
	      while(Model.getBoolean("right", up).equals(false) && up>0)	      						// find false up line  
	          up--;
	      while(Model.getBoolean("left", down).equals(false) && down< Model.getSize("left")-1)	// find false down line         
	          down++;
	      
	      if(num == 0) {
	    	  for(int i=up+1; i<= down+1; i++)
	    		  Model.setString("left", i-1, Model.getString("right", i-1));
	      }
	      else {
	    	  for(int i=up+1; i<= down+1; i++)
	    		  Model.setString("right", i-1, Model.getString("left", i-1));
	      }
	   }
   
   public static void remakeText(viewview left, viewview right) {
	// set new textPane
	      String x = "";
	      String y = "";
	      
	      for(int k=0; k<Model.getSize("left"); k++) 
	 	     x += Model.getString("left", k) +  "\n";
	     
	      
	      for(int k=0; k<Model.getSize("right"); k++)
	 	      y += Model.getString("right", k) +  "\n";
	     
	      left.textPane.setText(x);
	      right.textPane.setText(y);
   }
   
   public static void Highlighting(viewview left, viewview right) {
	// initialize Highlighter
	      DefaultHighlighter Lhighlighter =  (DefaultHighlighter)left.textPane.getHighlighter();
	      DefaultHighlighter Rhighlighter =  (DefaultHighlighter)right.textPane.getHighlighter();
	      
	      Highlighter.HighlightPainter paint = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
	   
	// highlighting
	      int r=0;
	      int l=0;
	      
	      Lhighlighter.setDrawsLayeredHighlights(false);
	      Rhighlighter.setDrawsLayeredHighlights(false);
	       
	      try {         
	         int n=0;
	         int m=0;
	         
	         for(l=0; l<Model.getSize("left"); l++) {
	            int leftStart=left.textPane.getLineStartOffset(n);
	            int leftEnd = left.textPane.getLineEndOffset(n);
	            
	            if(Model.getBoolean("left", l).equals(false)) {
	               Lhighlighter.addHighlight(leftStart, leftEnd, paint);
	            }            
	               leftStart += Model.getString("left", l).length() + 1;
	               leftEnd = leftStart +  Model.getString("left", l).length();
	               n++;
	               
	          }
	         
	         for(r=0; r<Model.getSize("right"); r++) {
	            
	            int rightStart= right.textPane.getLineStartOffset(m);
	            int rightEnd  = right.textPane.getLineEndOffset(m);
	            
	            if(Model.getBoolean("right", r).equals(false)){
	               Rhighlighter.addHighlight(rightStart, rightEnd, paint);
	            }
	               rightStart += Model.getString("right", r).length() + 1;   
	               rightEnd = rightStart +  Model.getString("right", r).length();
	               m++;
	         }         
	   
	      } catch (BadLocationException e) {
	         e.printStackTrace();
	      }
   }
   
   
   public static void LCS_algorithm() {
	   int rightSize = Model.getSize("right") + 1;
	      int leftSize = Model.getSize("left") + 1;
	      int lcs[][] = new int[leftSize][rightSize];      
	      
	      //initialize Boolean
	      for(int i=0; i<rightSize; i++) 
	    	  Model.addBoolean("right", false);  
	      for(int i=0; i<leftSize; i++)
	    	  Model.addBoolean("left", false);  

	      // add "0" for easy LCS algorithm
	      Model.addString("right", 0, "0"); 
	      Model.addString("left", 0, "0");     
	      
	      // LCS algorithm with ignoring empty line
	      for(int i=1; i<leftSize; i++) {
	         
	         for(int j=1; j<rightSize; j++) {
	            
	            if(i == 0 || j == 0) {
	               lcs[i][j] = 0;
	               continue;
	            }
	            
	            if((!Model.getString("left",i).equals("") || !Model.getString("right",j).equals("")) 
	            		&& Model.getString("left",i).equals(Model.getString("right",j))) {
	               lcs[i][j] = lcs[i-1][j-1] + 1;
	            }
	            else {
	               if(lcs[i-1][j] > lcs[i][j-1])
	                  lcs[i][j] = lcs[i-1][j];
	               else
	                  lcs[i][j] = lcs[i][j-1];
	            }   
	         }
	      }      
	      
	      // backtracking for LCS
	      int i = leftSize - 1;
	      int j = rightSize - 1;      
	      while(lcs[i][j] != 0) {
	         
	         if(lcs[i][j] == lcs[i][j-1]) {
	            j--;
	         }
	         else if(lcs[i][j] == lcs[i-1][j]) {
	            i--;
	         }
	         else if(lcs[i][j] - 1 == lcs[i-1][j-1]) {
	        	Model.setBoolean("left", i, true);
	        	Model.setBoolean("right", j, true);         
	            i--;
	            j--;
	         }
	      }      
	      
	      // remove "0"
	      Model.right_String.remove(0);   Model.right_Boolean.remove(0);
	      Model.left_String.remove(0);   Model.left_Boolean.remove(0);
   }
   
}
   