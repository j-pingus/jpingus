package pingus;

import pingus.macro.ActionsRecorder;

public enum Actions {

	ACTION_DIGGER("digger"), ACTION_BOMBER("bomber"), ACTION_BASHER("basher"), ACTION_FLOATER(
			"floater"), ACTION_JUMPER("jumper"), ACTION_MINER("miner"), ACTION_BLOCKER(
			"blocker"), ACTION_CLIMBER("climber"), ACTION_BRIDGER("bridger"), ACTION_WALKER(
			"walker");
	private String tagname;
	private static ActionsRecorder recorder;
	private Actions(String name) {
		this.tagname = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tagname;
	}

	public void doAction(JPingus pingus) {
		if(recorder!=null)
			recorder.record(this, pingus);
		switch (this) {
		case ACTION_WALKER:
			pingus.initWalker();
			break;
		case ACTION_DIGGER:
			pingus.initDigger();
			break;
		case ACTION_BOMBER:
			pingus.initBomber();
			break;
		case ACTION_BASHER:
			pingus.initBasher();
			break;
		case ACTION_FLOATER:
			pingus.initFloater();
			break;
		case ACTION_JUMPER:
			pingus.initJumper();
			break;
		case ACTION_MINER:
			pingus.initMiner();
			break;
		case ACTION_BLOCKER:
			pingus.initBlocker();
			break;
		case ACTION_CLIMBER:
			pingus.initClimber();
			break;
		case ACTION_BRIDGER:
			pingus.initBridger();
			break;

		}
	}
	public static Actions getAction(String action){
		for(Actions a:Actions.values()){
			if(a.toString().equals(action)){
				return a;
			}
		}
		return null;
	}
	public static void setRecorder(ActionsRecorder recorder) {
		Actions.recorder = recorder;
	}

	public String getTagname() {
		return tagname;
	}
}
