package gee.imaging.animator;

import java.awt.Component;

import sun.misc.Perf;

public class ComponentAnimator extends Animator{
	int speed;
	long ellapsed;
	Perf perf = Perf.getPerf();
	long countFreq = perf.highResFrequency();


	public ComponentAnimator(Component comp, int speed,boolean step) {
		
		super(comp,step);
		this.speed=speed;
		
		// TODO Auto-generated constructor stub
	}
	public int getPercentTimeUsed() {
		// TODO Auto-generated method stub
		return (int)((ellapsed * 100) / speed);
	}
	public long getEllapsedTime() {
		// TODO Auto-generated method stub
		return ellapsed;
	}
	public long getCurrentTime() {
		// TODO Auto-generated method stub
		return perf.highResCounter()*1000L/countFreq;
	}
	public void setEllapsedTime(long ellapsed) {
		// TODO Auto-generated method stub
		this.ellapsed=ellapsed;
		
	}
	public int getSleepTime() {
		// TODO Auto-generated method stub
		if(speed-ellapsed>5)
			return (int) (speed-ellapsed);
		return 0;
	}
}	
