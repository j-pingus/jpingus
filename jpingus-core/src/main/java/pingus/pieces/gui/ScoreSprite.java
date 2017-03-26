package pingus.pieces.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import gee.imaging.sprites.animated.AnimatedSprite;

public class ScoreSprite extends AnimatedSprite {
	BufferedImage img;
//	String second="";
//	long sec=0;
//	public boolean update(long elapsed) {
//		// TODO Auto-generated method stub
//		sec+=elapsed;
//		second=""+(sec/1000);
//		return super.update(elapsed);
//		
//	}
	public ScoreSprite(int width,int height) {
		super("Score");
		setWidth(width);
		setHeight(height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		setUseOffset(false);
		init(640-5-width,5,1000);
		setActor(false);
		freeze();
		// TODO Auto-generated constructor stub
	}
	public void setScore(int tobereleased, int released,int objective,int saved,int killed){
		if(tobereleased==0)return;
		int p_saved=img.getWidth()*saved/tobereleased;
		int p_objective=img.getWidth()*objective/tobereleased;
		int p_killed=img.getWidth()*killed/tobereleased;
		int p_released=img.getWidth()*released/tobereleased;
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,img.getWidth(),img.getHeight());
		g.setColor(Color.GREEN);
		g.fillRect(1,1,p_saved-1,img.getHeight()-2);
		g.setColor(Color.BLUE);
		g.fillRect(1,0,p_released-2,1);
		g.fillRect(1,img.getHeight()-1,p_released-2,1);
		g.setColor(Color.RED);
		g.fillRect(img.getWidth()-p_killed,1,p_killed-1,img.getHeight()-2);
		g.setColor(Color.BLUE);
		g.fillRect(p_objective,1,2,img.getHeight()-2);
//		g.setColor(Color.BLACK);
//		g.drawString(second,10,10);
	}
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
