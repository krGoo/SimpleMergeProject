
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.easymock.EasyMock;

import junit.framework.TestCase;



public class MergeTest extends TestCase{
	Model testModel;
	Controller testController;
	protected void setUp(){
		testModel = new Model();
		testController = new Controller();
	}
	protected void tearDown(){
		testModel = null;
		testController = null;
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
		
		testModel.load(fileTest_case1, 0);
		testModel.load(fileTest_case2, 1);
		assertEquals(testModel.getfile(0).getName(),"1.txt");
		assertEquals(testModel.getfile(1).getName(),"2.txt");
		
		
		testModel.saveString(0);
		testModel.saveString(1);
		
		for(int i = 1; i <= 7; i++){
			assertEquals(testModel.left_String.get(i-1), Integer.toString(i));
		}
		int sample[] = {2,1,3,8,4,9,5,7,6,8};
		for(int i = 0; i < sample.length; i++){
			assertEquals(testModel.right_String.get(i), Integer.toString(sample[i]));
		}
		
		assertEquals(testModel.getString(0), "1\n2\n3\n4\n5\n6\n7\n");
		assertEquals(testModel.getString(1), "2\n1\n3\n8\n4\n9\n5\n7\n6\n8\n");
		
		
		try {
			testModel.save(saveFileTest, "abc", 0);
			File testFile = new File("D:\\3.txt");
			assertTrue(testFile.exists());
			BufferedReader br = new BufferedReader(new FileReader(testFile));
			String testString = br.readLine();
			assertEquals(testString, "abc");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testController.LCS_algorithm(testModel);
		assertFalse(testModel.getBoolean("left", 0));
		assertTrue(testModel.getBoolean("right", 0));
		assertEquals(testModel.getString("left",0), "1");
		assertEquals(testModel.getString("right",0), "2");
		testModel.setBoolean("left", 0, true);
		testModel.setBoolean("right", 0, false);
		assertTrue(testModel.getBoolean("left", 0));
		assertFalse(testModel.getBoolean("right", 0));
		testModel.setString("left", 0, "2");
		testModel.setString("right", 0, "1");
		assertEquals(testModel.getString("left",0), "2");
		assertEquals(testModel.getString("right",0), "1");
		
	}
	public void testControllerWithMock(){
		ModelInterface mockModel;
		mockModel = EasyMock.createMock(ModelInterface.class);
		EasyMock.expect(mockModel.getString("left",0)).andReturn("abc");
		EasyMock.expect(mockModel.getSize("left")).andReturn(5);
		EasyMock.replay(mockModel);
		assertEquals(testController.testGetSize(mockModel, "left"), 5);
		assertEquals(testController.testGetString(mockModel, "left", 0), "abc");
	}
	public void testController(){
		File fileTest_case1;
		File fileTest_case2;
		fileTest_case1 = new File("D:\\1.txt");
		fileTest_case2 = new File("D:\\2.txt");
		testModel.load(fileTest_case1, 0);
		testModel.load(fileTest_case2, 1);
		testModel.saveString(0);
		testModel.saveString(1);
		
		testController.LCS_algorithm(testModel);
		int[] leftSet = {0,1,1,1,1,0,1};
		int[] rightSet = {1,0,1,0,1,0,1,1,0,0};
		for(int i = 0; i < testModel.left_Boolean.size(); i++){
			if(leftSet[i] == 0)
				assertFalse(testModel.left_Boolean.get(i));
			else
				assertTrue(testModel.left_Boolean.get(i));
		}
		for(int i = 0; i < testModel.right_Boolean.size(); i++){
			if(rightSet[i] == 0)
				assertFalse(testModel.right_Boolean.get(i));
			else
				assertTrue(testModel.right_Boolean.get(i));
		}
		
		LeftView leftTestView = new LeftView(testController, testModel);
		RightView rightTestView = new RightView(testController, testModel);
		testController.remakeText(testModel, leftTestView, rightTestView);
		
		assertEquals("1\n2\n3\n4\n5\n6\n7\n",leftTestView.textPane.getText());
		assertEquals("2\n1\n3\n8\n4\n9\n5\n7\n6\n8\n",rightTestView.textPane.getText());
		
		testController.setSameLining(testModel);
		testController.compareText(testModel, leftTestView, rightTestView);
		testController.remakeText(testModel, leftTestView, rightTestView);
		
		assertEquals("1\n2\n\n3\n\n4\n\n5\n6\n7\n\n\n\n",leftTestView.textPane.getText());
		assertEquals("\n2\n1\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		assertEquals(testModel.getSize("left"), testModel.getSize("right"));
	
		testController.setLineNum(3);
		assertEquals(3, testController.linenum);
		testController.copyToRight(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n\n4\n\n5\n6\n7\n\n\n\n",leftTestView.textPane.getText());
		assertEquals("\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
		testController.setLineNum(9);
		assertEquals(9, testController.linenum);
		testController.copyToLeft(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n\n4\n\n5\n\n7\n\n\n\n",leftTestView.textPane.getText());
		assertEquals("\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
		testController.setLineNum(11);
		assertEquals(11, testController.linenum);
		testController.copyToLeft(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n\n4\n\n5\n\n7\n6\n8\n\n",leftTestView.textPane.getText());
		assertEquals("\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
		testController.setLineNum(1);
		assertEquals(1, testController.linenum);
		testController.copyToRight(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n\n4\n\n5\n\n7\n6\n8\n\n",leftTestView.textPane.getText());
		assertEquals("1\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
		testController.setLineNum(5);
		assertEquals(5, testController.linenum);
		testController.copyToLeft(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n8\n4\n\n5\n\n7\n6\n8\n\n",leftTestView.textPane.getText());
		assertEquals("1\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
		testController.setLineNum(7);
		assertEquals(7, testController.linenum);
		testController.copyToLeft(testModel, leftTestView, rightTestView);
		assertEquals("1\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",leftTestView.textPane.getText());
		assertEquals("1\n2\n\n3\n8\n4\n9\n5\n\n7\n6\n8\n\n",rightTestView.textPane.getText());
		
	}
	public void testleftview(){
		LeftView testview = new LeftView(testController, testModel);
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());

		
	}
	public void testrightview(){
		RightView testview = new RightView(testController, testModel);
		assertEquals(testview.loadbtn.getText(), "Load");
		assertEquals(testview.editbtn.getText(), "Edit");
		assertEquals(testview.savebtn.getText(), "Save");
		assertTrue(testview.scrollPane.isEnabled());
		assertTrue(testview.textPane.isEnabled());
	}
	
}
