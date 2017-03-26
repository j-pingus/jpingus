package pingus;

import pingus.ressource.Ressource;
import gee.imaging.sprites.animated.AnimatedSprite;

public class PingusSprite extends AnimatedSprite {
	String tagName;

	public PingusSprite(String name,String tagName) {
		super(name);
		this.tagName=tagName;
		// TODO Auto-generated constructor stub
	}

	public String getXml(String name, String type, String addon) {
		return "<"
				+ (name == null ? this.tagName : name)
				+ (type != null ? " type=\"" + type + "\"" : "")
				+ ">\n\t"
				+ ((Ressource) getSprite()).getXml()
				+ "\n\t"
				+ addon
				+ "\n\t<position>\n\t\t"
				+ "<x-pos>"
				+ getX()
				+ "</x-pos>\n\t\t"
				+ "<y-pos>"
				+ getY()
				+ "</y-pos>\n\t\t"
				+ "<z-pos>"
				+ getZ()
				+ "</z-pos>\n\t"
				+ "</position>\n\t"
//				+"<speed>-1</speed>\n\t<parallax>1</parallax>\n"
				+ "</" + (name == null ? this.tagName : name) + ">";
	}

	public String getXml() {
		return getXml(tagName, tagName, "<type>"+ tagName + "</type>");
	}
}
