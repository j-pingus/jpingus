package pingus.ressource;

import java.awt.Image;

import gee.imaging.sprites.Sprite;

public class Ressource extends Sprite implements Cloneable{
	String restype;
	String file;
	String ident;
	String modifier;
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	public String getXml(){
		return "\n\t\t<surface>\n\t\t\t<resource type=\""+restype+"\">"+
				"\n\t\t\t\t<resource-datafile>"+file+"</resource-datafile>"+
				"\n\t\t\t\t<resource-ident>"+ident+"</resource-ident>"+
				"\n\t\t\t\t<modifier>ROT0</modifier>\n\t\t\t"+
				"</resource>\n\t\t</surface>";

	}
	public Ressource(Image i,int width,int height,String type, String file, String ident,
			String modifier) {
		super(i,width,height);
		this.restype =type;
		this.file =file;
		this.ident=ident;
		this.modifier=modifier;
		// TODO Auto-generated constructor stub
	}
	public String getFile() {
		return file;
	}
	public String getIdent() {
		return ident;
	}
	public String getModifier() {
		return modifier; 
	}
	public String getResType() {
		return restype;
	}
}
