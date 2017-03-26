package pingus.ressource;

import java.io.File;
import java.io.PrintWriter;

public class RessourceEntry implements Comparable {
	String section;

	String name;

	String file;

	int width, height;

	public String toString() {
		// TODO Auto-generated method stub
		return (section + "/" + name).toUpperCase();
	}
	public void save(PrintWriter out){
		//selector = ../images/themes/level_selector.png (type=surface, x=0, y=0, width=64, height=64, array=1x2);
		out.print("      ");
		out.print(name);
		out.print(" = ");
		out.print(file);
		out.print(" (type=surface, x=0, y=0, ");
		out.print("width="+width+", ");
		out.print("height="+height); 
		out.println(" );");
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return toString().compareTo(o.toString());
	}

	public String getFile() {
		return file;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public String getSection() {
		return section;
	}

	public int getWidth() {
		return width;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public void setFile(File file) {
		String n = file.getAbsolutePath();
		if(n.indexOf("images")>-1){
			n= "../"+n.substring(n.indexOf("images")).replace(File.separatorChar,'/');
		}else{
			n= "../images/"+file.getName();
		}
		setFile(n);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setName(String name) {
		this.name = name.replace('.','_').trim().toLowerCase();
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
