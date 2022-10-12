package it.unibo.oop.lab04.robot.composable.interfaces;

public interface Gear {
	
	boolean isUsed();
	boolean isPlugged();
	double getEnergyRequired();
	boolean isOn();
	
	void plugIn(ComposableRobot robot);
	void plugOff();
	void switchOn();
	void switchOff();
	void exaust();
	Gear[] getGears();

}
