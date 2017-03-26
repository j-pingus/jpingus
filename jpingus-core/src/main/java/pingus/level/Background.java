/**
 * 
 */
package pingus.level;

import pingus.PingusSprite;
import pingus.ressource.Ressource;
import gee.imaging.background.StaticSpriteBackground;

public class Background extends StaticSpriteBackground {
	
	/*
	 * <surface><resource type="datafile"> <resource-datafile>textures</resource-datafile>
	 * <resource-ident>textures/clouds2</resource-ident> <modifier>ROT0</modifier>
	 * </resource></surface>
	 */

	public String getXml() {
//		String ret = "<background type=\"surface\">\n\t"
//				+ ((Ressource) getSprite()).getXml() 
//				+ "\n\t<color>\n\t\t<red>"
//				+ color_red + "</red>\n\t\t<green>" + color_green + "</green>\n\t\t<blue>"
//				+ color_blue + "</blue>\n\t\t<alpha>" + color_alpha
//				+ "</alpha>\n\t</color>" + "\n\t<scroll-x>" + scrollX
//				+ "</scroll-x>\n\t<scroll-y>" + scrollY + "</scroll-y>\n\t<para-x>"
//				+ paraX + "</para-x>\n\t<para-y>" + paraY + "</para-y>\n\t"
//				+ "<stretch-x>" + stretchX + "</stretch-x>\n\t<stretch-y>"
//				+ stretchY + "</stretch-y>\n\t<keep-aspect>" + keepAspect
//				+ "</keep-aspect>\n\t" 
//				+ "<position>\n\t\t<x-pos>" + x
//				+ "</x-pos>\n\t\t<y-pos>" + y + "</y-pos>\n\t\t<z-pos>" + z
//				+ "</z-pos>\n\t</position>\n" 
//				+ "</background>\n";
		String ret="";
		for (int i = 0; i < getSprites().size(); i++) {
			PingusSprite sp = (PingusSprite) getSprites().elementAt(i);
			ret += sp.getXml();
		}
		return ret;

	}

	private float color_red = 0.1f;

	private float color_green = 0.0f;

	private float color_blue = 0.0f;

	private float color_alpha = 0.3f;

	private int scrollX = -1;

	private int scrollY = 0;

	private float paraX = 0.5f;

	private 	float paraY = 0.5f;

	private float stretchX = 0.0f;

	private float stretchY = 0.0f;

	private boolean keepAspect = false;

	private int x, y, z;

	public int getX() { 
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return 0;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public float getColor_alpha() {
		return color_alpha;
	}

	public void setColor_alpha(float color_alpha) {
		this.color_alpha = color_alpha;
	}

	public float getColor_blue() {
		return color_blue;
	}

	public void setColor_blue(float color_blue) {
		this.color_blue = color_blue;
	}

	public float getColor_green() {
		return color_green;
	}

	public void setColor_green(float color_green) {
		this.color_green = color_green;
	}

	public float getColor_red() {
		return color_red;
	}

	public void setColor_red(float color_red) {
		this.color_red = color_red;
	}

	public boolean isKeepAspect() {
		return keepAspect;
	}

	public void setKeepAspect(boolean keepAspect) {
		this.keepAspect = keepAspect;
	}

	public float getParaX() {
		return paraX;
	}

	public void setParaX(float paraX) {
		this.paraX = paraX;
	}

	public float getParaY() {
		return paraY;
	}

	public void setParaY(float paraY) {
		this.paraY = paraY;
	}

	public int getScrollX() {
		return scrollX;
	}

	public void setScrollX(int scrollX) {
		this.scrollX = scrollX;
	}

	public int getScrollY() {
		return scrollY;
	}

	public void setScrollY(int scrollY) {
		this.scrollY = scrollY;
	}

	public float getStretchX() {
		return stretchX;
	}

	public void setStretchX(float stretchX) {
		this.stretchX = stretchX;
	}

	public float getStretchY() {
		return stretchY;
	}

	public void setStretchY(float stretchY) {
		this.stretchY = stretchY;
	}
}