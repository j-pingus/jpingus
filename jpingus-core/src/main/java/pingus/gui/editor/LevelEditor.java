package pingus.gui.editor;

import java.io.File;

import gee.imaging.ImageToolkit;
 
public class LevelEditor {
	public LevelEditor() throws Exception{
		FrameLevelEditor level= new FrameLevelEditor();
		ImageToolkit.init(level);
		//level.initLevel(xml.toString());
		level.setVisible(true);
//		level.selectNextLevel();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
				new LevelEditor();
		
	}

}
