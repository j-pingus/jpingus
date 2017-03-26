package gee.imaging.animator;

import java.applet.Applet;


public class AppletAnimator extends Animator {
	int sleepTime;
	long ellapsed;
	int yield;
	int speed=1000/24;
	public AppletAnimator(Applet applet,int sleep,boolean step) {
		super(applet,step);
		this.sleepTime=sleep;
		// TODO Auto-generated constructor stub
	}
	public long getEllapsedTime() {
		// TODO Auto-generated method stub
		return ellapsed;
	}
	public long getCurrentTime() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis();
	}
	public void setEllapsedTime(long ellapsed) {
		// TODO Auto-generated method stub
		this.ellapsed = ellapsed;
	}
	public int getSleepTime() {
		// TODO Auto-generated method stub
		
		if(speed-ellapsed>5){
			return (int)(speed-ellapsed);
		}else{
			if(yield++>20){
				yield=0;
				Thread.yield();
				return 10;
			}
			return 0;
		}
	}
	public int getPercentTimeUsed() {
		// TODO Auto-generated method stub
		return (int)(ellapsed/speed)*100;
	}
}
