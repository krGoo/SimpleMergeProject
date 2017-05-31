package view;

import java.io.File;
import java.io.IOException;

public class Controller {
	
	
	public static void loadFile(File ldfile, int num){//모델의 load 호출
		Model.load(ldfile, num);
	}
	public static void saveFile(File svfile, String content, int num) throws IOException{//모델의 save호출
		Model.save(svfile, content, num);
	}
}
