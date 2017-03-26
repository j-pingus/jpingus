package pingus.pieces.ground;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pingus.PingusSprite;
import pingus.ressource.Ressource;

import gee.imaging.sprites.animated.AnimatedSprite;

/**
 * Simply a marker class
 * 
 * @author Gérald
 * 
 */
public class Liquid extends PingusSprite {
	int width_liquid;
	public Liquid(String name) {
		super(name,"liquid");
	}
	public void setSprite(Ressource sprite,int width) {
		super.setSprite(sprite);
//		sprite.setTiled(width,sprite.getHeight(),true);
		width_liquid=width;
	}
	public Rectangle getBounds(boolean offset) {
		// TODO Auto-generated method stub
		Rectangle r= super.getBounds(offset);
		r.width=width_liquid;
		return r;
	}
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		Composite c = g.getComposite( );  // backup the old composite

	    g.setComposite( AlphaComposite.getInstance(
	                             AlphaComposite.SRC_OVER, 0.75f) );
//	    getSprite().paint(g,x(), y());
		if (getSprite() != null) { 
			for(int x=0;x()+x<width_liquid;x+=getSprite().getWidth()){
				if(x==0)
					g.drawImage(getImage(), x()+x, y(), null);
				else
					g.drawImage(getCurrentImage(), x()+x, y(), null);
			}
		}
	    g.setComposite(c);
		
//		g.setColor(Color.RED);
//		g.draw3DRect(0, y(), width_liquid, getHeight(), true);
	}
	public String getXml() {
		// TODO Auto-generated method stub
		return getXml("liquid","liquid","<type>liquid</type>");
	}
	
}
