package it.unibo.oop.lab04.robot.composable.gears;

public class AtomicBattery extends AbstractGear {

	private int counter;
	
	public AtomicBattery(String description, double consuption) {
		super(description, consuption);
		this.counter = 0;
	}

	@Override
	public double getEnergyRequired() {
		return this.getConsuption();
	}
	
	@Override 
	protected int setLimitUse() {
		return (this.getLimit() * 6);
	}

	public void setCounter() {
		this.counter++;
	}
	
	public boolean checkForRecharge() {
		return isOn() && this.counter <= getLimitUse();
	}
	
	public boolean checkForExhaust() {
		return isOn() && this.counter >= getLimitUse();
	}
}
