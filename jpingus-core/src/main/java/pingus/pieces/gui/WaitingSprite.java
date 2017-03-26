package pingus.pieces.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import pingus.ressource.Ressource;
import pingus.ressource.RessourceLocator;
import gee.imaging.animator.Animator;
import gee.imaging.sprites.animated.AnimatedSprite;

public class WaitingSprite extends AnimatedSprite {
	Ressource spriteWalker = RessourceLocator.findRessource("datafile",
			"pingus", "Pingus/walker0", "ROT0");

	String message = "";

	Animator anim;

	int x, y, w = 200, h = 50;

	public WaitingSprite(Animator anim) {
		super("Waiting");
		this.anim = anim;
		setSprite(spriteWalker);
		initSize(200, 50);
		initSprite(0, 1);
		setActor(false);
		// freeze();
		setSpeedX(40);
		setSpeedY(0);
		x = (anim.getWindow().getWidth() - getWidth()) / 2;
		y = (anim.getWindow().getHeight() - getHeight()) / 2;
		init(x, y, 2000);
		anim.addSprite(this);
	}

	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
		if (getImage() != null) {
			g.drawImage(getImage(), x() + 5, y() + 5, null);
		}
		g.setColor(Color.BLUE);
		g.drawString(message, x + 5, y() + 45);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void finish() {
		anim.removeSprite(this);
	}
}
