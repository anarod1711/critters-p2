/*
 * CRITTERS Critter4.java
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

/* Critter4 = Wise Guy
 * doTimeStep():
 *     Will walk if energy is above 70, no rush
 *     Will run if energy is 70 or below, starting to panic
 *     Will stay put if energy is below 40, need to reserve energy to fight
 *     Will never reproduce, doesn't want to waste 1/2 of its energy
 * fight():
 *     Will fight no matter what
 */

public class Critter4 extends Critter {

	/* Will walk if energy is above 70, no rush,
	 * preferably towards an empty spot (i.e. uses look)
	 * Will run if energy is 70 or below, starting to panic
	 * Will stay put if energy is below 40, need to 
	 * reserve energy to fight
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > 70) {
			int dir = 0;
			while (dir < 8) {
				if (look(dir, false) == null) {
					walk(dir);
					break;
				}
				dir++;
			}
		}
		else if (getEnergy() >= 40) {
			run(Critter.getRandomInt(8));
		}
	}

	/* Always fights
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 4 to represent Critter #4
	 */
	@Override
	public String toString() {
		return "4";
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}

	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.YELLOW;
    }

	@Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.BLACK;
    }

    public static String runStats(List<Critter> critter4s) {
    	String st = "" + critter4s.size() + " critters as follows -- 4:" + critter4s.size();
    	return st;
    }
}
