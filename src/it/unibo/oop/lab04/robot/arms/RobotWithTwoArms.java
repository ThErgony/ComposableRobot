package it.unibo.oop.lab04.robot.arms;

import it.unibo.oop.lab04.robot.base.BaseRobot;

public class RobotWithTwoArms extends BaseRobot implements RobotWithArms {

	private static final double CONSUPTION_MOVEMENT_WITH_ITEM = 0.05;
	private static final int PICK_AND_DROP = 1;
	
	private int itemsCarried;
	private BasicArm[] arms;

	public RobotWithTwoArms(String robotName) {
		super(robotName);
		this.itemsCarried = 0;
		this.arms = new BasicArm[] {new BasicArm("left"), new BasicArm("right")};

		}

	@Override
	public boolean pickUp() {
		if (isBatteryEnough(BasicArm.getConsuptionPickDrop())
			&&	getCarriedItemsCount() < this.arms.length) {
			for (BasicArm basicArm : arms) {
				if (!basicArm.isGrabbing()) {
					return itemsPickDown(basicArm, PICK_AND_DROP);
				}
			}
		}
		return false;
	}

	@Override
	public boolean dropDown() {
		if (isBatteryEnough(BasicArm.getConsuptionPickDrop())
			&& getCarriedItemsCount() > 0) {
			for (BasicArm basicArm : arms) {
				if (basicArm.isGrabbing()) {
					return itemsPickDown(basicArm, -PICK_AND_DROP);// "-" for remove item
				}
			}
		}
		return false;
	}

	private boolean itemsPickDown(final BasicArm arm, final int item) {
		consumeBattery(BasicArm.getConsuptionPickDrop());
		if (item == PICK_AND_DROP) {
			arm.pickUp();
		} else {
			arm.dropDown();
		}
		this.itemsCarried += item;
		return true;
	}

	@Override
	public int getCarriedItemsCount() {
		return this.itemsCarried;
	}

	@Override
	protected double getBatteryRequirementForMovement() {
		return super.getBatteryRequirementForMovement() 
				+ getCarriedItemsCount() * CONSUPTION_MOVEMENT_WITH_ITEM;
	}

}
