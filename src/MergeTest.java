
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;



import java.awt.Button;
import java.awt.event.*;


public class MergeTest extends TestCase{
	//ComponentTester guiTester = new ComponentTester();
	protected void setUp(){
		Model.file = new File[2];
		Model.left_String = new ArrayList<String>();
		Model.right_String = new ArrayList<String>();
		Model.left_Boolean = new ArrayList<Boolean>();
		Model.right_Boolean = new ArrayList<Boolean>();
	}
	protected void tearDown(){
		
	}
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
		File saveFileTest;
		fileTest_case1 = new File("D:\\1.txt");
		fileTest_case2 = new File("D:\\2.txt");
		saveFileTest = new File("D:\\3");
		
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
		
		
		try {
			Model.save(saveFileTest, "abc", 0);
			File testFile = new File("D:\\3.txt");
			assertTrue(testFile.exists());
			BufferedReader br = new BufferedReader(new FileReader(testFile));
			String testString = br.readLine();
			assertEquals(testString, "abc");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller.LCS_algorithm();
		assertFalse(Model.getBoolean("left", 0));
		assertTrue(Model.getBoolean("right", 0));
		assertEquals(Model.getString("left",0), "1");
		assertEquals(Model.getString("right",0), "2");
		Model.setBoolean("left", 0, true);
		Model.setBoolean("right", 0, false);
		assertTrue(Model.getBoolean("left", 0));
		assertFalse(Model.getBoolean("right", 0));
		Model.setString("left", 0, "2");
		Model.setString("right", 0, "1");
		assertEquals(Model.getString("left",0), "2");
		assertEquals(Model.getString("right",0), "1");
		
	}
	public void testController(){
		File fileTest_case1;
		File fileTest_case2;
		fileTest_case1 = new File("D:\\1.txt");
		fileTest_case2 = new File("D:\\2.txt");
		Model.load(fileTest_case1, 0);
		Model.load(fileTest_case2, 1);
		
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
		
		LeftView leftTestView = new LeftView();
		RightView rightTestView = new RightView();
		Controller.remakeText(leftTestView, rightTestView);
		
		assertEquals("1\n2\n3\n4\n5\n6\n7\n",leftTestView.textPane.getText());
		assertEquals("2\n1\n3\n8\n4\n9\n5\n7\n6\n8\n",rightTestView.textPane.getText());
		
		Controller.setSameLining();
		
		assertEquals(Model.getSize("left"), Model.getSize("right"));
		
		assertEquals(1, Controller.linenum);
		Controller.setLineNum(4);
		assertEquals(4, Controller.linenum);
		Controller.incereaselineNum();
		assertEquals(5, Controller.linenum);
		Controller.setLineNum(1);
		assertEquals(1, Controller.linenum);
		
		
	}
	public void testleftview(){
		LeftView testview = new LeftView();
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());
		//ButtonTest guiTest = new ButtonTest("loadbtn");
		//guiTest.testButton();
		
	}
	public void testrightview(){
		RightView testview = new RightView();
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());
	}
	/*
	public class ButtonTest extends ComponentTestFixture{
		private String clickType;
		public ButtonTest (String name) {super(name);}
		private ComponentTester tester;
	    protected void setUp() {
	        tester = new ComponentTester();
	    }
	    protected void tearDown() {

	        tester = null;
	    }
	    public void testClick() {
	        ActionListener al = new ActionListener() {
	            public void actionPerformed(ActionEvent ev) {
	                clickType = ev.getActionCommand();                            
	            }
	        };
	    }
	    public void testButton(){
	    	setUp();
	    	leftview testview = new leftview();
	    	//tester.click(testview.loadbtn);
	    	assertEquals(clickType, Button.ABORT);
	    	tearDown();
	    }
	}
	*/
	
}
