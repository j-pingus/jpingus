package gee.imaging.sprites.animated;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;

public class Transparent extends AnimatedSprite {
	float transparency;

	/**
	 * Piece of decor transparent
	 * 
	 * @param name
	 *            the name for the transparent object
	 * @param perc_trans
	 *            value from 1 (opaque) to 0 (invisible) ie:0.5 is semi
	 *            transparent
	 */
	public Transparent(String name, float perc_trans) {
		super(name);
		setActor(false);
		freeze();
		transparency = perc_trans;
		// TODO Auto-generated constructor stub
	}

	public void init(int x, int y, int z) {
		// TODO Auto-generated method stub
		super.init(x, y, 1);
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		Composite c = g.getComposite(); // backup the old composite

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transparency));
		super.render(g);
//		g.setColor(Color.BLACK);
//		g.drawString("t",x(),y());
		g.setComposite(c);

	}

}
