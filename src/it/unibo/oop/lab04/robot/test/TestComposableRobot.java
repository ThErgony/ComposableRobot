package it.unibo.oop.lab04.robot.test;

import it.unibo.oop.lab04.robot.composable.RobotComposable;
import it.unibo.oop.lab04.robot.composable.gears.AtomicBattery;
import it.unibo.oop.lab04.robot.composable.gears.NavigationSystem;
import it.unibo.oop.lab04.robot.composable.gears.TwoArms;
import it.unibo.oop.lab04.robot.composable.interfaces.ComposableRobot;
import it.unibo.oop.lab04.robot.composable.interfaces.Gear;

/**
 * Utility class for testing composable robots
 * 
 */
public final class TestComposableRobot {

    //private static final int CYCLES = 200;

    private TestComposableRobot() {
    }

    public static void main(final String[] args) {

        final ComposableRobot r0 = new RobotComposable("GetterOne");
        final Gear battery = new AtomicBattery("atomic battery", 0);
        final Gear navigator = new NavigationSystem("border navigator", 0.15);
        final Gear twoArms = new TwoArms("two arms", 0.05);
        
        System.out.println(r0.getBatteryLevel());
        r0.plugIn(battery);
		r0.plugIn(navigator);
		r0.switchOn(battery);
		//r0.moveUp();
//		r0.moveRight();
		r0.switchOn(navigator);
        r0.plugIn(twoArms);
        r0.switchOn(twoArms);
        r0.command("pick");
        r0.command("pick");
        r0.command("pick");
        r0.command("cielo");
        r0.command("drop");
        r0.command("drop");
        r0.command("drop");
        System.out.println(r0.getBatteryLevel());
		for (Gear elem : r0.getGears()) {
			System.out.println(elem);
		}
		
		final ComposableRobot r1 = new RobotComposable("GetterTwo");
        final Gear battery1 = new AtomicBattery("atomic battery", 0);
        final Gear navigator1 = new NavigationSystem("border navigator", 0.15);
        final Gear twoArms1 = new TwoArms("two arms", 0.05);
        
        System.out.println(r1.getBatteryLevel());
        r1.moveUp();
        r1.plugIn(battery1);
		r1.plugIn(navigator1);
		r1.switchOn(battery1);
        r1.plugIn(twoArms1);
        r1.switchOn(twoArms1);
        r1.command("pick");
        r1.command("pick");
        r1.command("pick");
        r1.switchOn(navigator1);
        r1.command("cielo");
        r1.command("drop");
        r1.command("drop");
        r1.command("drop");
        System.out.println(r1.getBatteryLevel());
		for (Gear elem : r1.getGears()) {
			System.out.println(elem);
		}
		/*
         * Write your own test.
         * 
         * You will need a robot with an atomic battery, two arms, and a
         * navigator system. Turn on the battery only when the level is below
         * 50%.
         */

    }
}
