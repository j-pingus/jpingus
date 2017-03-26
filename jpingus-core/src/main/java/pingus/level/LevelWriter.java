package pingus.level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LevelWriter {
	public static void write(Level level){
		try {
			File xml = level.getXmlFile();
			File back =new File(level.getXmlFile()+".bak");
			if(!back.exists()&&xml.exists()){
				xml.renameTo(back);
			}
			PrintWriter outs = new PrintWriter(new FileWriter(xml));
			
			outs.println(level.getXml());
			outs.close();
			System.out.println("Level "+level+" saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
