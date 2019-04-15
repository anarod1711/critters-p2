/*
 * CRITTERS Critter1.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Analaura Rodriguez>
 * <ar55665>
 * <16225>
 * <Kevin Han>
 * <kdh2789>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment5;

import java.util.List;

/* Critter1 = The Runner
 * The runner will choose one direction at birth and just forever
 * run in that direction
 * During doTimeStep, it will choose to run if its energy is 70 or above
 *     else it will reproduce
 * During fight, it will choose to run if its energy is above 40
 *     and below 70 as it has hope of staying alive for next time step. 
 *     If its energy is 40 or below, it will choose to fight
 *     as it is already weak :( What's there to lose?
 */

public class Critter1 extends Critter {
	
	private int dir;
	
	/* Constructor: Critter is assigned
	 * a random direction at birth.
	 */
	public Critter1() {
		dir = Critter.getRandomInt(8);
	}

	/* Critter1 runs if energy > 70.
	 * If not, just reproduces.
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() >= 70) {
			run(dir);
		}
		else {
			reproduce(this, Critter.getRandomInt(8));
		}
	}

	/* Critter1 doesn't fight and runs if its energy is
	 * below 70 and above 40, preferably towards an empty
	 * space, as it still has hopes of
	 * living. It fights only if his energy is 40 or 
	 * below (weak energy status).
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponent) {
		if (getEnergy() < 70 && getEnergy() > 40) {
			int dir = 0;
			while (dir < 8) {
				if (look(dir, true) == null) {
					run(dir);
					break;
				}
				dir++;
			}
			return false;
		}
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 1 to represent Critter #1
	 */
	@Override
	public String toString() {
		return "1";
	}


	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}
	

    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }

    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }
    
    public static String runStats(List<Critter> critter1s) {
    	String st = "" + critter1s.size() + " critters as follows -- 1:" + critter1s.size();
    	return st;
    }
}
