

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.event.*;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Utilities;


public class Controller {
	
	
    int linenum = 1;
   
   
   public  void loadFile(Model dataSet, File ldfile, int num){
      dataSet.load(ldfile, num);
   }
   
   public  void saveFile(Model dataSet, File svfile, String content, int num) throws IOException{
      dataSet.save(svfile, content, num);
   }
   
   public  void saveStringToModel(Model dataSet, int num){
      dataSet.saveString(num);
   }   
   
   public  void copyToLeft(Model dataSet, ViewView left, ViewView right) {      	      
	  findBox(dataSet, 0, linenum);
      remakeText(dataSet, left, right);
      Highlighting(dataSet, left, right); 
   } 
   
   public  void copyToRight(Model dataSet, ViewView left, ViewView right) {
	  findBox(dataSet, 1, linenum);
      remakeText(dataSet, left, right);
      Highlighting(dataSet, left, right);  
   }
   
   public void AllcopyToLeft(Model dataSet, ViewView left, ViewView right) {
	      for(int i=0; i<dataSet.getSize("right"); i++) {
	         dataSet.setString("left", i, dataSet.getString("right",i));
	         dataSet.setBoolean("left", i, dataSet.getBoolean("right", i));
	      }
	      compareText(dataSet, left,right);
	      remakeText(dataSet, left, right);
	      Highlighting(dataSet, left, right);
	   }
	   
	   public void AllcopyToRight(Model dataSet, ViewView left, ViewView right) {
	      for(int i=0; i<dataSet.getSize("left"); i++) {
	         dataSet.setString("right", i, dataSet.getString("left",i));
	         dataSet.setBoolean("right", i, dataSet.getBoolean("left", i));
	      }
	      compareText(dataSet, left,right);
	      remakeText(dataSet, left, right);
	      Highlighting(dataSet, left, right);
	   }
   
   
   public  void compareText(Model dataSet, ViewView left, ViewView right) {
	   // Clean Boolean
	  dataSet.left_Boolean.clear();
	  dataSet.right_Boolean.clear(); 
      
	  LCS_algorithm(dataSet);
	  
      // Lining with same string               
      for(int k=0; k<dataSet.getSize("right") && k<dataSet.getSize("left"); k++) {
         if(dataSet.getBoolean("left", k).equals(false) && dataSet.getBoolean("right", k).equals(true)) {
            dataSet.addString("right", k, "");
            dataSet.addBoolean("right", k, true);
         }
         else if(dataSet.getBoolean("left", k).equals(true) && dataSet.getBoolean("right", k).equals(false) ) {
            dataSet.addString("left", k, "");
            dataSet.addBoolean("left", k, true);
         }            
      }      
            
      // set Same line
      setSameLining(dataSet);      

   // if both lines are empty, their booleans are true
      for(int k=0; k<dataSet.getSize("right"); k++) {
         if(dataSet.getString("left", k).equals("") && dataSet.getBoolean("left", k).equals(false))
            dataSet.setBoolean("left", k, true);
         else if(dataSet.getString("left", k).equals("") && dataSet.getBoolean("left", k).equals(true))
             dataSet.setBoolean("left", k, false);
         
         if(dataSet.getString("right", k).equals("") && dataSet.getBoolean("right", k).equals(false))
             dataSet.setBoolean("right", k, true);   
         else if(dataSet.getString("right", k).equals("") && dataSet.getBoolean("right", k).equals(true))
             dataSet.setBoolean("right", k, false);           
      }
      
      for(int k=0; k<dataSet.getSize("right"); k++) {
         if(!dataSet.getBoolean("left", k).equals(dataSet.getBoolean("right", k))) {
            dataSet.setBoolean("left", k, false);
            dataSet.setBoolean("right", k, false);
         }              
      }  
   }   
   
   
   
   public  void setLineNum(int line){
	   linenum=line;
   }
   public  void incereaselineNum(){
       linenum++;
   }
   
   
   
   public  void setSameLining(Model dataSet) {
      while(dataSet.getSize("left") > dataSet.getSize("right")) {
    	  dataSet.addString("right","");
    	  dataSet.addBoolean("right",false);
      }
      while(dataSet.getSize("left") < dataSet.getSize("right")) {
    	  dataSet.addString("left","");
    	  dataSet.addBoolean("left",false);
      }
   }
   
   public  void findBox(Model dataSet, int num, int line){
	      int up = line - 1;
	      int down = line - 1;
	      
	      while(dataSet.getBoolean("right", up).equals(false) && up>0)	      						// find false up line  
	          up--;
	      while(dataSet.getBoolean("left", down).equals(false) && down< dataSet.getSize("left")-1)	// find false down line         
	          down++;
	      
	      if(num == 0) {
	    	  for(int i=up+1; i<= down+1; i++) {
	    		  dataSet.setString("left", i-1, dataSet.getString("right", i-1));
	    		  dataSet.setBoolean("left", i-1, true);
	    		  dataSet.setBoolean("right", i-1, true);
	    	  }
	      }
	      else {
	    	  for(int i=up+1; i<= down+1; i++) {
	    		  dataSet.setString("right", i-1, dataSet.getString("left", i-1));
	    		  dataSet.setBoolean("right", i-1, true);
	    		  dataSet.setBoolean("left", i-1, true);
	    	  }
	      }
	   }
   
   public  void remakeText(Model dataSet, ViewView left, ViewView right) {
	// set new textPane
	      String x = "";
	      String y = "";
	      
	      for(int k=0; k<dataSet.getSize("left"); k++) 
	 	     x += dataSet.getString("left", k) +  "\n";
	     
	      
	      for(int k=0; k<dataSet.getSize("right"); k++) 
	 	      y += dataSet.getString("right", k) +  "\n";
	     
	      left.textPane.setText(x);
	      right.textPane.setText(y);
   }
   public  void Highlighting(Model dataSet, ViewView left, ViewView right, int start, int end) {
	   			// initialize Highlighter
		      DefaultHighlighter Lhighlighter =  (DefaultHighlighter)left.textPane.getHighlighter();
		      DefaultHighlighter Rhighlighter =  (DefaultHighlighter)right.textPane.getHighlighter();
		      
		      Highlighter.HighlightPainter paint = new DefaultHighlighter.DefaultHighlightPainter(Color.WHITE);
		   
		      // hi
		      Lhighlighter.setDrawsLayeredHighlights(false);
		      Rhighlighter.setDrawsLayeredHighlights(false);
		       
		      try {         
		    	  int n=0;
		    	  int m=0;
		    	  
			         for(int k=start; k<end; k++) {			            
			            if(dataSet.getBoolean("left", k).equals(false)) {
			               Lhighlighter.addHighlight(start, end, paint);
			            }            
			               start += dataSet.getString("left", k).length() + 1;
			               end = start +  dataSet.getString("left", k).length();
			               n++;
			               
			          }
			         
			         for(int k=start; k<end; k++) {
			            if(dataSet.getBoolean("right", k).equals(false)){
			               Rhighlighter.addHighlight(start, end, paint);
			            }
			               start += dataSet.getString("right", k).length() + 1;   
			               end = start +  dataSet.getString("right", k).length();
			               m++;
			         }         
			   
			      } catch (BadLocationException e) {
			         e.printStackTrace();
			      }
   }
		      
		      
		      
		      
   public  void Highlighting(Model dataSet, ViewView left, ViewView right) {
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
	         
	         for(l=0; l<dataSet.getSize("left"); l++) {
	            int leftStart=left.textPane.getLineStartOffset(n);
	            int leftEnd = left.textPane.getLineEndOffset(n);
	            
	            if(dataSet.getBoolean("left", l).equals(false)) {
	               Lhighlighter.addHighlight(leftStart, leftEnd, paint);
	            }            
	               leftStart += dataSet.getString("left", l).length() + 1;
	               leftEnd = leftStart +  dataSet.getString("left", l).length();
	               n++;
	               
	          }
	         
	         for(r=0; r<dataSet.getSize("right"); r++) {
	            
	            int rightStart= right.textPane.getLineStartOffset(m);
	            int rightEnd  = right.textPane.getLineEndOffset(m);
	            
	            if(dataSet.getBoolean("right", r).equals(false)){
	               Rhighlighter.addHighlight(rightStart, rightEnd, paint);
	            }
	               rightStart += dataSet.getString("right", r).length() + 1;   
	               rightEnd = rightStart +  dataSet.getString("right", r).length();
	               m++;
	         }         
	   
	      } catch (BadLocationException e) {
	         e.printStackTrace();
	      }
   }
   
   
   public  void LCS_algorithm(Model dataSet) {
	   int rightSize = dataSet.getSize("right") + 1;
	      int leftSize = dataSet.getSize("left") + 1;
	      int lcs[][] = new int[leftSize][rightSize];      
	      
	      //initialize Boolean
	      for(int i=0; i<rightSize; i++) 
	    	  dataSet.addBoolean("right", false);  
	      for(int i=0; i<leftSize; i++)
	    	  dataSet.addBoolean("left", false);  

	      // add "0" for easy LCS algorithm
	      dataSet.addString("right", 0, "0"); 
	      dataSet.addString("left", 0, "0");     
	      
	      // LCS algorithm with ignoring empty line
	      for(int i=1; i<leftSize; i++) {
	         
	         for(int j=1; j<rightSize; j++) {
	            
	            if(i == 0 || j == 0) {
	               lcs[i][j] = 0;
	               continue;
	            }
	            
	            if((!dataSet.getString("left",i).equals("") || !dataSet.getString("right",j).equals("")) 
	            		&& dataSet.getString("left",i).equals(dataSet.getString("right",j))) {
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
	        	dataSet.setBoolean("left", i, true);
	        	dataSet.setBoolean("right", j, true);         
	            i--;
	            j--;
	         }
	      }      
	      
	      // remove "0"
	      dataSet.right_String.remove(0);   dataSet.right_Boolean.remove(0);
	      dataSet.left_String.remove(0);   dataSet.left_Boolean.remove(0);
   }
   
   public void setScrollbarSync(boolean isSync, ViewView left, ViewView right) {
	   if(isSync == true) {
		   left.scrollPane.getVerticalScrollBar().setModel(right.scrollPane.getVerticalScrollBar().getModel());
	   } else {
		   left.scrollPane.getVerticalScrollBar().setModel(new JScrollBar().getModel());
		   right.scrollPane.getVerticalScrollBar().setModel(new JScrollBar().getModel());
		   
	   }
	   
   }
   
}
   