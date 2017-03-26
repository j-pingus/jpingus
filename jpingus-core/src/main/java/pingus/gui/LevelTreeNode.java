package pingus.gui;

import java.io.File;

public class LevelTreeNode {
	File file;

	public LevelTreeNode(File aFile) {
		file = aFile;
	}

	public File getFile() {
		return file;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return file.getName();
	}
}

