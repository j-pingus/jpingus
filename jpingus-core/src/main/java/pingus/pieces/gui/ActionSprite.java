package pingus.pieces.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;

import pingus.Actions;
import pingus.PingusSprite;
import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;
import gee.imaging.sprites.animated.AnimatedSprite;

public class ActionSprite extends PingusSprite  {
	private static int y2 = 10;
	private Actions action;
	Ressource normal, selected;
	public String getXml(){
		return action.toString();
	}
	static public void reinit() {
		y2 = 10;
	}

	int count;

	public ActionSprite(Actions action, int amount) {
		super(action.toString(),action.toString());
		this.action = action;
		this.count = amount;
		init(5, y2, 1000);
		
		try {
			normal = RessourceLocator.findRessource("datafile", "pingus", "Pingus/"
					+ action + "0", "RT0");
			selected = RessourceLocator.findRessource("datafile", "pingus",
					"Pingus/" + action + "1", "RT0");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		y2 += normal.getHeight()+7;
		setSprite(normal);
		setSpeedX(0);
		setSpeedY(0);
		freeze();
	}

	public void select() {
		setSprite(selected);
	}

	public void unselect() {
		setSprite(normal);
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		Composite c = g.getComposite( );  // backup the old composite

	    g.setComposite( AlphaComposite.getInstance(
	                             AlphaComposite.SRC_OVER, 0.75f) );
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(getX() - 1, getY() - 5, getWidth() + 2,
				getHeight() + 6, 3, 3);
	    g.setComposite( c );
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(getX(), getY() - 4, getWidth(), getHeight() + 5, 3, 3);
		super.render(g);
		g.setColor(Color.WHITE);
		g.drawString("" + count, getX() + 3, getY() + 8);
		g.setColor(Color.BLACK);
		g.drawString("" + count, getX() + 2, getY() + 7);
	}

	public Actions getAction() {
		return action;
	}

	public boolean getOne() {
		if (count > 0) {
			count--;
			return true;
		}
		return false;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
}
