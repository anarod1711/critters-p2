/*
 * CRITTERS Critter3.java
 * EE422C Project 5 submission by
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

/* Critter 3 = The Fighter
 * If it has more than enough energy, then it will reproduce
 *     otherwise, it will walk
 * During an encounter, it will always choose to fight
 */

public class Critter3 extends Critter {

	/* Reproduces if energy > 200. Otherwise, 
	 * it walks.
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > 200) {
			reproduce(this, Critter.getRandomInt(8));
		}
		else{
			walk(Critter.getRandomInt(8));
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
	 * @return String 3 to represent Critter #3
	 */
	@Override
	public String toString() {
		return "3";
	}
	

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}
	

	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.ORANGE;
    }

	@Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.GREEN;
    }

    public static String runStats(List<Critter> critter3s) {
    	String st = "" + critter3s.size() + " critters as follows -- 3:" + critter3s.size();
    	return st;
    }
}
