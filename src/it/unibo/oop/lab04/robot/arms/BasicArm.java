package it.unibo.oop.lab04.robot.arms;

public class BasicArm {

	private static final double CONSUPTION_PICK_DROP = 0.2;
	
	private final String arm;
	private boolean grabbing;
	
	public BasicArm(String arm) {
		super();
		this.arm = arm;
		this.grabbing = false;
	}
	
	public boolean isGrabbing() {
		return this.grabbing;		
	}
	
	public void pickUp() {
		if (!this.grabbing) {
			this.grabbing = true;	
		}
	}
	
	public void dropDown() {
		if (this.grabbing) {
			this.grabbing = false;
		}
	}

	public static double getConsuptionPickDrop() {
		return CONSUPTION_PICK_DROP;
	}

	@Override
	public String toString() {
		return "BasicArm [" + arm + ", " + (isGrabbing()? "get one" : "nothing") + " item]";
	}
	
}
