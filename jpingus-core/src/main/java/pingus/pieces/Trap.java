package pingus.pieces;

import java.awt.Graphics2D;

import pingus.PingusSprite;

import gee.imaging.sprites.animated.AnimatedSprite;

public class Trap extends PingusSprite {
	boolean trapping = false;

	public Trap(String name) {
		super(name,"trap");
		freeze();
		setSpeedX(0);
		setSpeedY(0);
		setActor(false);
	}

	public void initTrap() {
		super.initSprite(0, 0);
		trapping = true;
	}

	public void showDone() {
		// TODO Auto-generated method stub
		trapping = false;
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if (trapping)
			super.render(g);
		// g.setColor(Color.RED);
		// g.drawRect(x(), y(), getWidth(), getHeight());
	}

	public String getXml() {
		// TODO Auto-generated method stub
		return "<trap>" + "<type>"+getName()+"</type>" + "<position>"
				+ "<x-pos>"+getX()+"</x-pos>" + "<y-pos>"+getY()+"</y-pos>"
				+ "<z-pos>"+getZ()+"</z-pos>" + "</position>" + "</trap>";
	}

}
