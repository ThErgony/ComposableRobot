package it.unibo.oop.lab04.robot.composable.gears;

import it.unibo.oop.lab04.robot.composable.interfaces.ComposableRobot;
import it.unibo.oop.lab04.robot.composable.interfaces.Gear;

public abstract class AbstractGear implements Gear {

	@SuppressWarnings("unused")
	private static final int LIMIT = 1;
	
	private final String description;
	private final double consuption;
	
	private boolean used;
	private boolean plugged;
	private boolean on;
	private int limitUse;
	private ComposableRobot robot;
	
	public AbstractGear(String description, double consuption) {
		super();
		this.description = description;
		this.consuption = consuption;
		plugOff();
		this.used = false;
		switchOff();
		this.limitUse = setLimitUse();
		this.robot = null;
	}

	public String getDescription() {
		return this.description;
	}

	public double getConsuption() {
		return this.consuption;
	}

	public int getLimitUse() {
		return this.limitUse;
	}
	
	protected abstract int setLimitUse();

	@Override
	public void plugIn(ComposableRobot robot) {
		this.plugged = true;
		this.robot = robot;
	}

	@Override
	public void plugOff() {
		this.plugged = false;
	}

	@Override
	public void switchOn() {
		this.on = true;
	}

	@Override
	public void switchOff() {
		this.on = false;
	}

	@Override
	public boolean isUsed() {
		return this.used;
	}

	@Override
	public boolean isPlugged() {
		return this.plugged;
	}

	@Override
	public abstract double getEnergyRequired();

	@Override
	public boolean isOn() {
		return this.on;
	}

	@Override
	public String toString() {
		return "[" + description + ": consuption=" + consuption + ", used=" + used
				+ ", plugged=" + plugged + ", on=" + on + ", limitUse=" + limitUse + "]";
	}

	public int getLimit() {
		return LIMIT;
	}

	@Override
	public Gear[] getGears() {
		// for my help test override in abstract class, nothing use, only for robot
		return null;
	}
	
	public ComposableRobot getRobot() {
		return this.robot;
	}
	
	public void exaust() {
		this.used = true;
	}
}
