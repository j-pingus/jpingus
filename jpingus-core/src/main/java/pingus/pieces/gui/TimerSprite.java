package pingus.pieces.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import pingus.PingusSprite;

import gee.imaging.animator.Animator;
import gee.imaging.sprites.animated.AnimatedSprite;

public class TimerSprite extends PingusSprite {
	BufferedImage img;
	long time;
	Animator anim;
	public TimerSprite(Animator anim) {
		super("Timer","timer");
		int width=80;
		int height=15;
		this.anim=anim;
		setWidth(width);
		setHeight(height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		setUseOffset(false);
		init(640-5-width,25,1000);
		setActor(false);
		freeze();
		initTime();
	}
	private void initTime() {
		time=anim.getAnimatorTime();
	}
	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		boolean ret= super.update(elapsed);
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,img.getWidth(),img.getHeight());

		g.setColor(Color.BLACK);
		g.drawString("t:"+((anim.getAnimatorTime()-time)/1000)+" s"+anim.getAnimatorTime(),5,10);

		return ret;
	}
	public String getXml(String name, String type, String addon) {
		// TODO Auto-generated method stub
		return "";
	}
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
	public Image getCurrentImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
