package it.unibo.oop.lab04.robot.composable.gears;

import it.unibo.oop.lab04.robot.arms.BasicArm;

public class TwoArms extends AbstractGearComandable {

	private static final int PICK_AND_DROP = 1;
	
	private int itemsCarried;
	private int pickCounter;
	private int dropCounter;
	
	private BasicArm[] arms;
	
	public TwoArms(String description, double consuption) {
		super(description, consuption);
		this.setCommands(new String[] {"pick", "drop"});
		this.itemsCarried = 0;
		this.pickCounter = 0;
		this.dropCounter = 0;
		this.arms = new BasicArm[] {new BasicArm("left"), new BasicArm("right")};
	}

	@Override
	protected int setLimitUse() {
		return this.getLimit() * 2;
	}

	@Override
	public double getEnergyRequired() {
		return this.getConsuption() * this.itemsCarried;
	}

	public double getEnergyRequiredPickDrop() {
		return BasicArm.getConsuptionPickDrop();
	}
	
	public BasicArm[] getArms() {
		return arms;
	}
	
	public boolean pickUp() {
		if (checkPickDrop() &&	getCarriedItemsCount() < this.arms.length 
			&& this.checkOnPlugged() && this.pickCounter != this.getLimitUse()) {
			for (BasicArm basicArm : arms) {
				if (!basicArm.isGrabbing()) {
					this.pickCounter++;
					return itemsPickDown(basicArm, getPickAndDrop());
				}
			}
		}
		if (checkForExhaust() && checkOnPlugged()) {
			this.exaust();
			System.out.println(message());
		}
		System.out.println(message());
		return false;
	}

	public boolean dropDown() {
		if (checkPickDrop()	&& getCarriedItemsCount() > 0 
			&& this.checkOnPlugged() && this.dropCounter != this.getLimitUse()) {
			for (BasicArm basicArm : arms) {
				if (basicArm.isGrabbing()) {
					this.dropCounter++;
					return itemsPickDown(basicArm, -getPickAndDrop());// "-" for remove item
				}
			}
		}
		if (checkForExhaust() && checkOnPlugged()) {
			this.exaust();
			System.out.println(message());
		}
		System.out.println(message());
		return false;
	}

	private boolean checkForExhaust() {
		return this.pickCounter == this.getLimitUse() 
				&& this.dropCounter == this.getLimitUse();
	}

	private boolean checkPickDrop() {
		return getRobot().getBatteryLevel() > BasicArm.getConsuptionPickDrop();
	}
	
	private boolean checkOnPlugged() {
		return this.isOn() && this.isPlugged();
	}

	private boolean itemsPickDown(final BasicArm arm, final int item) {
		if (item == getPickAndDrop()) {
			arm.pickUp();
			System.out.print("\nRobot pick item ");
		} else {
			arm.dropDown();
			System.out.print("\nRobot drop item corretcly, item remain ");

		}
		this.itemsCarried += item;
		System.out.println(this.itemsCarried);
		return true;
	}
	
	public static int getPickAndDrop() {
		return PICK_AND_DROP;
	}

	public int getPickCounter() {
		return this.pickCounter;
	}

	public int getDropCounter() {
		return this.dropCounter;
	}

	public int getCarriedItemsCount() {
		return this.itemsCarried;
	}

	@Override
	public void command(String command) {
		boolean find = false;
		for (String  cmd : this.getCommands()) {
			if(cmd == command) {
				find = true;
				doOperation(cmd);
			} 
		}
		if (!find) {
			System.out.println("Command " + command + " not supported");
		}
	}

	private void doOperation(String cmd) {
		switch (cmd) {
			case "pick":
				pickUp();
				break;
			case "drop":
				dropDown();
				break;	
			default:
				System.out.println("Command not supported");
		}	
	}
	
	private String message() {
		if (!checkPickDrop()) {
			return getErrorMsg() + "energy exhaust";
		}
		if (!checkOnPlugged()) {
			return getErrorMsg() + "gear unplugged or switched off";
		}
		if (!checkForExhaust()) {
			return getErrorMsg() + "gear exhaust";
		}
		if (dropCounter == getLimitUse() || getCarriedItemsCount() == 0) {
			return getErrorMsg() + "gear can not drop item limit reach or hands empty";
		}
		if (pickCounter == getLimitUse() || getCarriedItemsCount() >= this.arms.length) {
			return getErrorMsg() + "gear can not pick item limit reach or hands full";
		}
		return "other";
	}

}
