package gee.gui;

import gee.imaging.ImageToolkit;
import gee.imaging.Renderable;
import gee.imaging.animator.Animator;
import gee.imaging.animator.ComponentAnimator;
import gee.imaging.background.DummyBackground;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class AnimatedButton extends Button {
	Animator animator;

//	boolean continueLoop;
	public AnimatedButton(Animator anim) {
		animator = anim;
		ImageToolkit.init(this);
	}

	public AnimatedButton() {
		animator = new ComponentAnimator(this, 1000 / 24, false);
		animator.setBackground(new DummyBackground(this,this.getBackground()));
		animator.setDebug(false);
		ImageToolkit.init(this);
	}

	public void start() {
		animator.start();
//		if (continueLoop)
//			return;
//		continueLoop = true;

//		animator.setWidth(getWidth());
//		animator.setHeight(getHeight());
//		animator.setSight(new Rectangle(0, 0, getWidth(), getHeight()));
//		
//		animator.setBackground(new DummyBackground(this, new Color(0xff,0xff,0,0xff)));
//		animator.setImage(new BufferedImage(getWidth()-4, getHeight()-4,
//				BufferedImage.TYPE_INT_RGB));
//		Thread run = new Thread() {
//			public void run() {
//				try {
//					int noDelays = 0;
//					long startTime = animator.getCurrentTime();
//					long currTime = startTime;
//
//					while (continueLoop) {
//						long elapsedTime = animator.getCurrentTime() - currTime;
//						// System.out.println("Ellapsed:"+elapsedTime);
//						currTime += elapsedTime;
//						animator.setEllapsedTime(elapsedTime);
//						// doMouseActions();
//						animator.update(elapsedTime, false);
//						update(getGraphics());
//						
//						int sleep = animator.getSleepTime();
//						// System.out.println(sleep);
//						if (sleep > 0) {
//							Thread.sleep(sleep);
//						}
//						if (++noDelays >= 100) {
//							// System.out.println("yield");
//							// System.out.println("yield
//							// "+window.getKeyListeners().length);
//							// System.out.println("yield "+window.isEnabled());
//							// System.out.println("yield "+window.isValid());
//							// System.out.println("yield
//							// "+window.isFocusOwner());
//
//							Thread.yield(); // give another thread a
//							// chance to run
//							noDelays = 0;
//						}
//
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//					// TODO: handle exception
//				}
//
//			}
//
//		};
//		run.start();

	}

	public void update(Graphics g) {
		animator.render(g,2,2);
		super.update(g);
	}
	public void addSprite(Renderable sp){
		animator.addSprite(sp);
	}
	public void addSprite(Vector sp){
		animator.addSprite(sp);
	}
	public void removeSprite(Renderable sp){
		animator.removeSprite(sp);
	}

}
