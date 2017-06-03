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
	      findBox(linenum);
	      compareText(left,right);
	   } 
   
   public static void copyToRight(viewview left, viewview right) {
      
      for(int i=linenum; i<Model.Lswap_Boolean.size(); i++) {
      
         if(Model.Lswap_Boolean.get(i-1).equals(false)) {
         
            Model.right_String.set(i-1, Model.left_String.get(i-1));
            continue;
         }
         else
            break;
      }
      
      Model.Lswap_Boolean.clear();
      compareText(left,right);
   }
   
   public static void AllcopyToLeft(viewview left, viewview right) {
      left.textPane.setText(right.textPane.getText());
   }
   
   public static void AllcopyToRight(viewview left, viewview right) {
      right.textPane.setText(left.textPane.getText());
   }
   
   public static void compareText(viewview left, viewview right) {
	   // Clean Boolean
	  Model.left_Boolean.clear();
	  Model.right_Boolean.clear();
	  
      int rightSize = Model.getSize("right") + 1;
      int leftSize = Model.getSize("left") + 1;
      int lcs[][] = new int[leftSize][rightSize];      
      
      //initialize Boolean
      for(int i=0; i<rightSize; i++) 
         Model.right_Boolean.add(false);      
      for(int i=0; i<leftSize; i++)
         Model.left_Boolean.add(false);

      // add "0" for easy LCS algorithm
      Model.left_String.add(0,"0");
      Model.right_String.add(0,"0");      
      
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
      
      
      // Lining with same string               
      for(int k=0; k<Model.getSize("right") && k<Model.getSize("left"); k++) {
         if(Model.getBoolean("left", k).equals(false) && Model.getBoolean("right", k).equals(true)) {
            Model.right_String.add(k,"");
            Model.right_Boolean.add(k,false);
         }
         else if(Model.getBoolean("left", k).equals(true) && Model.getBoolean("right", k).equals(false) ) {
            Model.left_String.add(k,"");
            Model.left_Boolean.add(k,false);
         }            
      }      
            
      // set Same line
      setSameLining();      

      // if both lines are empty, their booleans are true
      for(int k=0; k<Model.getSize("right"); k++) {
    	  if(Model.getString("left", k).equals(Model.getString("right", k))) {
    		  Model.left_Boolean.set(k, true);
    		  Model.right_Boolean.set(k, true);
    	  }
      }
      
      // set Text new
      remakeText(left, right);            
      
      // Highlighting false line
      Highlighting(left, right);      
      
      // Copy boolean
      Model.Lswap_Boolean.addAll(Model.left_Boolean);
      Model.Rswap_Boolean.addAll(Model.right_Boolean);
            
   }   
   
   public static void setLineNum(int line){
	   linenum=line;
   }
   public static void incereaselineNum(){
      linenum++;
   }
   
   public static void setSameLining() {
      while(Model.left_Boolean.size() > Model.right_Boolean.size()) {
         Model.right_String.add("");
         Model.right_Boolean.add(false);
      }
      while(Model.left_Boolean.size() < Model.right_Boolean.size()) {
         Model.left_String.add("");
         Model.left_Boolean.add(false);
      }
   }
   
   public static void findBox(int line){
	      int pivot = line;
	      int up = line;
	      int down = line;
	      
	      while(true){
	         
	         if(Model.right_Boolean.get(up) == false && up>0)
	            up--;
	         else
	            break;
	      }
	      while(true){
	         
	         if(Model.right_Boolean.get(down) == false && down< Model.getSize("left")-1)
	            down++;
	         else
	            break;
	      }
	      
	      for(int i=up+1; i<= down+1; i++){
	         Model.left_String.set(i-1, Model.right_String.get(i-1));
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
	         
	         for(l=0; l<Model.left_Boolean.size(); l++) {
	            int leftStart=left.textPane.getLineStartOffset(n);
	            int leftEnd = left.textPane.getLineEndOffset(n);
	            
	            if(Model.getBoolean("left", l).equals(false)) {
	               Lhighlighter.addHighlight(leftStart, leftEnd, paint);
	            }            
	               leftStart += Model.getString("left", l).length() + 1;
	               leftEnd = leftStart +  Model.getString("left", l).length();
	               n++;
	               
	          }
	         
	         for(r=0; r<Model.right_Boolean.size(); r++) {
	            
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
   
}
   