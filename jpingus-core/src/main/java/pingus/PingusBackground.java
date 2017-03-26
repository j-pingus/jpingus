package pingus;

import java.awt.Graphics2D;

import pingus.ressource.Ressource;

public class PingusBackground extends PingusSprite {
	public PingusBackground(Ressource r,int width, int height) {
		super("Background","background");
		
		// TODO Auto-generated constructor stub
		setSprite(r);
		freeze();
		
		init(0,0,-150);
		r.setTiled(width,height,true);
	}
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		getSprite().paint(g,x(),y());
	}
	public int getType() {
		// TODO Auto-generated method stub
		return STATIC;
	}
	public String getXml() {
		return getXml("background", "surface", "");
	}
}
