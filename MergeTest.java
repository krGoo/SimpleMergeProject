package view;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.File;
import junit.framework.TestCase;

public class MergeTest extends TestCase{
	public void testFileTypeFilter(){
		FileTypeFilter testUnit = new FileTypeFilter(".txt","Text File");
		assertEquals(testUnit.getDescription(), "Text File (*.txt)");
		File fileTest;
		fileTest = new File("D:\\1.txt");
		assertTrue(testUnit.accept(fileTest));
		fileTest = new File("");
		assertFalse(testUnit.accept(fileTest));
		
	}
	public void testModel(){
		
		File fileTest_case1;
		File fileTest_case2;
		fileTest_case1 = new File("D:\\1.txt");
		fileTest_case2 = new File("D:\\2.txt");
		
		Model.load(fileTest_case1, 0);
		Model.load(fileTest_case2, 1);
		assertEquals(Model.getfile(0).getName(),"1.txt");
		assertEquals(Model.getfile(1).getName(),"2.txt");
		
		
		Model.saveString(0);
		Model.saveString(1);
		
		for(int i = 1; i <= 7; i++){
			assertEquals(Model.left_String.get(i-1), Integer.toString(i));
		}
		int sample[] = {2,1,3,8,4,9,5,7,6,8};
		for(int i = 0; i < sample.length; i++){
			assertEquals(Model.right_String.get(i), Integer.toString(sample[i]));
		}
		
		assertEquals(Model.getString(0), "1\n2\n3\n4\n5\n6\n7\n");
		assertEquals(Model.getString(1), "2\n1\n3\n8\n4\n9\n5\n7\n6\n8\n");
		
	}
	public void testController(){
		Controller.LCS_algorithm();
		int[] leftSet = {0,1,1,1,1,0,1};
		int[] rightSet = {1,0,1,0,1,0,1,1,0,0};
		for(int i = 0; i < Model.left_Boolean.size(); i++){
			if(leftSet[i] == 0)
				assertFalse(Model.left_Boolean.get(i));
			else
				assertTrue(Model.left_Boolean.get(i));
		}
		for(int i = 0; i < Model.right_Boolean.size(); i++){
			if(rightSet[i] == 0)
				assertFalse(Model.right_Boolean.get(i));
			else
				assertTrue(Model.right_Boolean.get(i));
		}
		
		leftview leftTestView = new leftview();
		rightview rightTestView = new rightview();
		Controller.remakeText(leftTestView, rightTestView);
		
		assertEquals(leftTestView.textPane.getText(), "1\n2\n3\n4\n5\n6\n7\n");
		assertEquals(rightTestView.textPane.getText(), "2\n1\n3\n8\n4\n9\n5\n7\n6\n8\n");
		
		Controller.setSameLining();
		
		assertEquals(Model.getSize("left"), Model.getSize("right"));
		
		assertEquals(Controller.linenum, 1);
		Controller.setLineNum(4);
		assertEquals(Controller.linenum, 4);
		Controller.incereaselineNum();
		assertEquals(Controller.linenum, 5);
		Controller.setLineNum(1);
		assertEquals(Controller.linenum, 1);
		
		
	}
	public void testleftview(){
		leftview testview = new leftview();
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());
		//testview.loadbtn.doClick();
		//testview.loadbtn.getActionListeners()[0].actionPerformed(null);
		
	}
	public void testrightview(){
		rightview testview = new rightview();
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());
	}
	
}
