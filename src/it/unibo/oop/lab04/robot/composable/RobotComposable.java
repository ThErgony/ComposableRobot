package it.unibo.oop.lab04.robot.composable;

import java.util.Arrays;
import it.unibo.oop.lab04.robot.base.BaseRobot;
import it.unibo.oop.lab04.robot.composable.gears.AtomicBattery;
import it.unibo.oop.lab04.robot.composable.gears.NavigationSystem;
import it.unibo.oop.lab04.robot.composable.gears.TwoArms;
import it.unibo.oop.lab04.robot.composable.interfaces.ComposableRobot;
import it.unibo.oop.lab04.robot.composable.interfaces.Gear;
import it.unibo.oop.lab04.robot.composable.interfaces.GearCommandable;

public class RobotComposable extends BaseRobot implements ComposableRobot, GearCommandable {

	private static final int ERROR_GEAR_NOT_PRESENT = -1;

	private Gear[] gears;
		
	public RobotComposable(String robotName) {
		super(robotName);
		this.gears = new Gear[0];
	}

	@Override
	public void command(String command) {
		for (int i = 0; i < gears.length; i++) {
			if (this.gears[i] instanceof TwoArms) {
				TwoArms arms = (TwoArms) gears[i] ;
				int dropStart = arms.getDropCounter();
				int pickStart = arms.getPickCounter();
				arms.command(command);
				if (dropStart != arms.getDropCounter() 
					|| pickStart != arms.getPickCounter()) {
					consumeBattery(arms.getEnergyRequiredPickDrop());
				}		
			}		
		}
	}

	@Override
	public void plugIn(Gear gear) {
		if (isGearPresent(gear) < 0 || this.gears.length == 0) {
			this.gears = Arrays.copyOf(this.gears, this.gears.length + 1);
			this.gears[this.gears.length - 1] = gear;
			this.gears[this.gears.length - 1].plugIn(this);
			System.out.println("plugin " + gear);
		} else {
			System.out.println("Error, impossible plug in, gear already present");
		}
	}
	
	@Override
	public void plugOff(Gear gear) {
		int index = isGearPresent(gear);
		if (gear.isPlugged()) {
			this.gears[index].plugOff();
			if (index == 0) {
				this.gears[index] = null;
			}
				for (int j = index; j < this.gears.length - 1; j++) {
				this.gears[j] = this.gears[j + 1];
				this.gears[j + 1] = null;
			}
			System.out.println("plugoff " + gear);
			this.gears = Arrays.copyOf(this.gears, this.gears.length - 1);
		} else {
			System.out.println("error impossible plug off, gear not present");
		}
	}

	@Override
	public void switchOn(Gear gear) {
		System.out.println("gearpresent "+ isGearPresent(gear));
		if (gear.isPlugged()) {
			this.gears[isGearPresent(gear)].switchOn();
			System.out.println("switchon " + gear);			
		} else {
				System.out.println("error impossible swtich on, gear not present");
		}
	}

	@Override
	public void switchOff(Gear gear) {
		if (gear.isPlugged()) {
			this.gears[isGearPresent(gear)].switchOff();
			System.out.println("switchoff " + gear);
			} else {
				System.out.println("error impossible swtich off, gear not present");
			}
	}
	
	public Gear[] getGears() {
		return this.gears;
	}

	private int isGearPresent(Gear gear) {
		int index = ERROR_GEAR_NOT_PRESENT;
		for (int i = 0; i < this.gears.length; i++) {
			if (this.gears[i] instanceof AtomicBattery
				&& gear instanceof AtomicBattery) {
				index = i;
			}
			if (this.gears[i] instanceof NavigationSystem
				&& gear instanceof NavigationSystem) {
				index = i;
			}
			if (this.gears[i] instanceof TwoArms
				&& gear instanceof TwoArms) {
				index = i;
			}
		}	
		return index;
	}
	
	public boolean rechargeable() {
		for (int i = 0; i < this.gears.length; i++) {
			if (this.gears[i] instanceof AtomicBattery) {
				 if (getBatteryLevel() < BATTERY_FULL /2 
					&& ((AtomicBattery)gears[i]).checkForRecharge()) {
					((AtomicBattery)gears[i]).setCounter();
					return true;
				} else if (((AtomicBattery)gears[i]).checkForExhaust()) {
					((AtomicBattery)gears[i]).exaust();
				}
			}
		}
		return false;
	}
	
	@Override
	protected double getBatteryRequirementForMovement() {
		double gearConsuption = 0;
		for (Gear gear : gears) {
			if (gear.isOn()) {
				gearConsuption += gear.getEnergyRequired();
			}	
		}
		return super.getBatteryRequirementForMovement() 
				+ gearConsuption;
	}
}
