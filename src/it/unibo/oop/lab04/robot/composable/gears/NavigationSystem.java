package it.unibo.oop.lab04.robot.composable.gears;

public class NavigationSystem extends AbstractGear {

	public NavigationSystem(String description, double consuption) {
		super(description, consuption);
	}

	@Override
	protected int setLimitUse() {
		return this.getLimit();
	}

	@Override
	public double getEnergyRequired() {
		return this.getConsuption();
	}

	@Override
	public void switchOn() {
		super.switchOn();
		final int xStart = getRobot().getPosition().getX();
		final int yStart = getRobot().getPosition().getY();
		while (getRobot().moveUp()) {
			checkChargeBattery();
			getRobot().moveUp();
		}
		while (getRobot().moveRight()) {
			checkChargeBattery();
			getRobot().moveRight();
		}
		while (getRobot().moveDown()) {
			checkChargeBattery();
			getRobot().moveDown();
		}
		while (getRobot().moveLeft()) {
			checkChargeBattery();
			getRobot().moveLeft();
		}
		if (xStart > 0) {
			while (getRobot().moveUp()) {
				checkChargeBattery();
				getRobot().moveUp();
			}
			while (getRobot().moveRight() 
						&& getRobot().getPosition().getX() < xStart) {
					checkChargeBattery();
					getRobot().moveRight();
			} 
		} else if (yStart > 0) {
			while (getRobot().moveUp() 
					&& getRobot().getPosition().getY() < yStart) {
				checkChargeBattery();
				getRobot().moveUp();
			}
		}
		exaust();
	}

	private void checkChargeBattery() {
		if (getRobot().rechargeable()) {
			getRobot().recharge();
			System.out.println("recharge");
		}
	}

}
