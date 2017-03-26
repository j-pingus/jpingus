package pingus.macro;

import pingus.Actions;
import pingus.JPingus;
import gee.imaging.animator.Animator;
public class ActionsRecorder {
	Animator animator;
	PingusIdentifier identifier;
	public ActionsRecorder(Animator animator,PingusIdentifier identifier){
		this.animator=animator;
		this.identifier=identifier;
	}
	public void record(Actions action, JPingus pingus){
		System.out.println("Recorded:"+action+" on pingus("+identifier.getId(pingus)+") at "+animator.getAnimatorTime());
	}
}
