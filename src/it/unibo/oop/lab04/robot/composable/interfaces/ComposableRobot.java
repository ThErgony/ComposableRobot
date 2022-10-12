package it.unibo.oop.lab04.robot.composable.interfaces;

import it.unibo.oop.lab04.robot.base.Robot;

public interface ComposableRobot extends Robot{
	
	void plugIn(Gear gear);
	void plugOff(Gear gear);
	void switchOn(Gear gear);
	void switchOff(Gear gear);
	Gear[] getGears();
	boolean rechargeable();
	void command(String command);

}
