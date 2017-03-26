package gee.imaging.sprites.animated;

import gee.imaging.background.MovingBackground;
import gee.imaging.sprites.Sprite;

public abstract class BackgroundImpacter extends AnimatedSprite {
	protected MovingBackground background;

	private int delay = 0;

	private long animTime = 0;

	public BackgroundImpacter(String name, MovingBackground background) {
		super(name);
		this.background = background;
		// TODO Auto-generated constructor stub
	}

	public void setSprite(Sprite sprite) {
		super.setSprite(sprite);
	}

	public boolean update(long elapsed) {
		// TODO Auto-generated method stub
		boolean ret=super.update(elapsed);
		animTime+=elapsed;
		if(delay>0)
			while(animTime>delay){
				animTime-=delay;
				impactBackground(background);
			}
		return ret;
	}

	public abstract void impactBackground(MovingBackground background);

	public void setDelay(int delay) {
		this.delay = delay;
		this.animTime=0;
	}

	public void terminated() {
		// TODO Auto-generated method stub
		super.terminated();
		this.delay=0;
		this.animTime=0;
	}

}
