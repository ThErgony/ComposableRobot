package it.unibo.oop.lab04.robot.composable.gears;

import it.unibo.oop.lab04.robot.composable.interfaces.GearCommandable;

public abstract class AbstractGearComandable extends AbstractGear implements GearCommandable {
	
	private static final String ERROR_MSG = "Impossible do operation, ";

	private String[] commands;

	public AbstractGearComandable(final String description, final double consuption) {
		super(description, consuption);
		this.commands = new String[0];
	}
	
	public abstract void command(final String command);

	public String[] getCommands() {
		return this.commands;
	}

	protected void setCommands(String[] commands) {
		this.commands = commands;
	}
	
	public static String getErrorMsg() {
		return ERROR_MSG;
	}
	
}
