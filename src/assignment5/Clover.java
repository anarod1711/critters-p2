/*
 * CRITTERS Clover.java
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

import assignment5.Critter.TestCritter;

public class Clover extends TestCritter {

    public String toString() {
        return "@";
    }

    public boolean fight(String opponent) {
        // same species as me!
        if (toString().equals(opponent)) {
            /* try to move away */
            walk(Critter.getRandomInt(8));
        }
        return false;
    }

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT);
    }

    public CritterShape viewShape() {
        return CritterShape.CIRCLE;
    }

    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.GREEN;
    }
}
