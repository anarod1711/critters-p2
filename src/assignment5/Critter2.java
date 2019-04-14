/*
 * CRITTERS Critter2.java
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

/* Critter2 = The Mother
 * Will stay in 1 position
 * Will always choose to reproduce in doTimeStep()
 * Will always fight in encounter
 *     Will not fight if it is another Mother
 */

public class Critter2 extends Critter {

	/* Critter2 always reproduces in its
	 * time step
	 */
	@Override
	public void doTimeStep() {
		reproduce(this, Critter.getRandomInt(8));		
	}
	
	
	/* Critter2 fights all the time, unless it is 
	 * another Critter2.
	 * 
	 * @param opponent to fight as a string
	 * @return true if wants to fight, false if doesn't
	 */
	@Override
	public boolean fight(String oponnent) {
		if (oponnent.equals(toString())) {
			return false;
		}
		return true;
	}
	
	/* String representation of Critter1.
	 * 
	 * @return String 2 to represent Critter #2
	 */
	@Override
	public String toString() {
		return "2";
	}


	@Override
	public CritterShape viewShape() {
		return CritterShape.TRIANGLE;
	}
	

	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.YELLOW;
    }

	@Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.RED;
    }
    
    public static String runStats(List<Critter> critter2s) {
    	String st = "" + critter2s.size() + " critters as follows -- 2:" + critter2s.size();
    	return st;
    }
}
