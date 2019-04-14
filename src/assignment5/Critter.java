/*
 * CRITTERS Critter.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Spring 2019
 */

package assignment5;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    static int size = 0;
    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    /**
     * Checks whether a specific position is unoccupied
     * 
     * @param int direction, boolean steps
     * @return String, whether position is unoccupied
     */
    protected final String look(int direction, boolean steps) {
        /*
         * critter must pay for looking, whether occupied or not
         * direction: 0-7
         * steps
         *     false = move 1 step
         *     true  = move 2 step
         * 
         * return null if unoccupied
         * return toString() of critter in occupied position
         */
    	energy -= Params.LOOK_ENERGY_COST;
    	int og_x = x_coord;
    	int og_y = y_coord;
    	if (steps) {
    		move(direction, 2);
    	}
    	else {
    		move(direction, 1);
    	}
    	// checking if position occupied by another critter (before doTimeStep())
    	for (int i = 0; i < all_coordinates.size(); i++) {
    		// check if look coordinate is occupied
    		if (x_coord == all_coordinates.get(i)[0] && y_coord == all_coordinates.get(i)[1]) {
    			// revert back, only looking
    			x_coord = og_x;
				y_coord = og_y;
    			return population.get(i).toString();
    		}
    	}
    	// revert back, only looking
    	x_coord = og_x;
		y_coord = og_y;
    	return null;    	
    }

    public static String runStats(List<Critter> critters) {
        // TODO Implement this method
        return null;
    }

    /**
     * Displays grid with current active critters 
     * 
     */
    public static void displayWorld(GridPane pane) {
    	pane.getChildren().clear();
    	paintGridLines(pane);
    	
    	for (Critter critter : population) {
    		Shape s = critter.getIcon(critter.viewShape());
    		pane.add(s, critter.x_coord, critter.y_coord);
    	}
    }
    
    /*
	 * Paint the grid lines in orange.  The purpose is two-fold -- to indicate boundaries of 
	 * icons, and as place-holders for empty cells.  Without placeholders, grid may not display properly.
	 */
	private static void paintGridLines(GridPane grid) {
		if (Params.WORLD_HEIGHT >= Params.WORLD_WIDTH) {
			size = 575/Params.WORLD_HEIGHT;
		}
		else {
			size = 575/Params.WORLD_WIDTH;
		}
		for (int i = 0; i < Params.WORLD_WIDTH; i++) { // columns
			for (int j = 0; j < Params.WORLD_HEIGHT; j++) { // rows
				Shape s = new Rectangle(size, size);
				s.setFill(null);
				s.setStroke(Color.ORANGE);
				grid.add(s, i, j);
			}
		}
	}
	
	/* 
	 * Returns a square or a circle depending on the shapeIndex parameter
	 * 
	 */
	private Shape getIcon(CritterShape shape) {
		Shape s = null;
		
		switch(shape) {
		case CIRCLE: s = new Circle(size/2);
			break;
		case SQUARE: s = new Rectangle(size, size);
			break;
		case TRIANGLE: s = new Polygon();
			((Polygon) s).getPoints().addAll(new Double[]{
			    (double) size-1, (double) size-1,
			    (double) 1, (double) size-1,
			    (double) (size)/2-1, 1.0 });
			break;
		case DIAMOND: s = new Polygon();
			((Polygon) s).getPoints().addAll(new Double[]{
				    (double) size/2, (double) 2,
				    (double) size-1, (double) size/2,
				    (double) (size)/2, (double) size-1,
				    (double) 1, (double) size/2});
				break;
		case STAR: s = new Polygon();
		((Polygon) s).getPoints().addAll(new Double[]{
			    (double) size/2, (double) 1,
			    (double) (size/6)*4, (double) (size/6)*2,
			    (double) size-1, (double) (size/2),
			    (double) (size/6)*4, (double) (size/6)*4,
			    (double) (size/6)*5, (double) size-1,
			    (double) (size/2), (double) (size/6)*5,
			    (double) (size/6), (double) size-1,
			    (double) (size/6)*2, (double) (size/6)*4,
			    (double) 1, (double) (size/2),
			    (double) (size/6)*2, (double) (size/6)*2});
			break;
		default: s = new Circle(size/2);
//		case TRIANGLE: s = new Triangle();
//		break;
//		case DIAMOND: s = new Rectangle(size, size);
//			break;
//		case STAR: s = new Circle(size);
//		break;
		}

		s.setFill(viewFillColor()); 
		s.setStroke(viewOutlineColor());
		return s;
	}
    

	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */

    private int energy = 0;

    private int x_coord;
    private int y_coord;
    private boolean moved; 						// moved in this time step
    private static boolean encounter = false;	// encounter mode or not
    
    private static List<int[]> all_coordinates = new ArrayList<int[]>();
    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
    	String critter_name = myPackage + "." + critter_class_name;
    	try {
    		// creating new critter through reflection
			Class<?> critter_class = Class.forName(critter_name);
			Constructor<?> constructor = critter_class.getConstructor();
			Object new_critter = constructor.newInstance();	
			
			// setting parameters of newly created critter
			population.add((Critter) new_critter);
			population.get(population.size()-1).energy = Params.START_ENERGY;
			population.get(population.size()-1).x_coord = Critter.getRandomInt(Params.WORLD_WIDTH);
			population.get(population.size()-1).y_coord = Critter.getRandomInt(Params.WORLD_HEIGHT);
			population.get(population.size()-1).moved = false;			
		} catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		} 
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
    	String critter_name = myPackage + "." + critter_class_name;
    	Class<?> critter_class;
    	List<Critter> critters = new ArrayList<Critter>();
		try {
			// adds all instances of critter_class_name to collection critters
			critter_class = Class.forName(critter_name);
			for (Critter critter : population) {
	        	if (critter_class.isInstance(critter)) {
	        		critters.add(critter);
	        	}
	        }
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
        return critters;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
    	population.clear();
        babies.clear();
    }

    /**
     * Simulates one time step for every Critter in the critter
     * population (whole world), resolves encounters, if any, 
     * removes dead critters from world, generates new clovers,
     * and adds all babies to the active population.
     * create and initialize a Critter subclass.
     * Throws InvalidCritterException if a critter attempts to
     * reproduce a nonexistent critter.
     *
     * @throws InvalidCritterException
     */
    public static void worldTimeStep() throws InvalidCritterException {
    	/* saving original positions of critters, needed to imitate 
    	 * "simultaneous" movement
    	 * movement of critters
    	 */
    	for (Critter critter : population) {
    		int[] coordinate = new int[2];
    		coordinate[0] = critter.x_coord;
    		coordinate[1] = critter.y_coord;
    		all_coordinates.add(coordinate);
    		critter.doTimeStep();
    	}
    	// removal of critters
    	for (int i = 0; i < population.size(); i++) {
    		if (population.get(i).energy <= 0) {
    			population.remove(i);
    			i--;
    		}
    	}  
    	// checking for encounters between critters
    	encounter = true;
    	doEncounters();
    	encounter = false;
    	// depletion of rest energy cost
    	for (Critter critter : population) {
    		critter.energy -= Params.REST_ENERGY_COST;
    	}
    	// removal of critters
    	for (int i = 0; i < population.size(); i++) {
    		if (population.get(i).energy <= 0) {
    			population.remove(i);
    			i--;
    		}
    	}    	
    	// reset moved flag for all critters before next round
    	for (Critter critter : population) {
    		critter.moved = false;
    	}
    	genClover();
    	population.addAll(babies);
    	babies.clear();
    }

    /** 
     * Different actions for different critters
     */
    public abstract void doTimeStep();

    /**
     * Different fight actions for different critters
     */
    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }
    
    /**
     * Resolves encounters between critters in
     * same position by giving each critter the 
     * option to fight or opt out. If critters want
     * to fight and are still in the same position
     * the critter that roles the higher dice wins
     * the position and absorbs half the energy of
     * losing critter, which dies. 
     *
     */
    private static void doEncounters() {
    	int outer_idx = 0;
    	while (outer_idx < population.size()) {
    		// reset inner_idx
    		int inner_idx = 0;
    		while (inner_idx < population.size()) {
        		if (outer_idx != inner_idx) {
        			// same position
        			if (population.get(outer_idx).x_coord == population.get(inner_idx).x_coord && population.get(outer_idx).y_coord == population.get(inner_idx).y_coord) {
        				// ask critters if they want to fight
        				boolean fight_1 = population.get(outer_idx).fight(population.get(inner_idx).toString());
        				boolean fight_2 = population.get(inner_idx).fight(population.get(outer_idx).toString());
        				// update coordinates
        				all_coordinates.get(outer_idx)[0] = population.get(outer_idx).x_coord;
        				all_coordinates.get(outer_idx)[1] = population.get(outer_idx).y_coord;
        				all_coordinates.get(inner_idx)[0] = population.get(inner_idx).x_coord;
        				all_coordinates.get(inner_idx)[1] = population.get(inner_idx).y_coord;
        				// still in same position with energy
        				if (population.get(outer_idx).energy > 0 && population.get(inner_idx).energy > 0 
        						&& population.get(outer_idx).x_coord == population.get(inner_idx).x_coord 
        						&& population.get(outer_idx).y_coord == population.get(inner_idx).y_coord) {
        					int num_1, num_2;
        					// roll dice (maybe make this into function later)
        					if (fight_1) {
        						num_1 = getRandomInt(population.get(outer_idx).energy);
        					}
        					else {
        						num_1 = 0;
        					}
        					
        					if (fight_2) {
        						num_2 = getRandomInt(population.get(inner_idx).energy);
        					}
        					else {
        						num_2 = 0;
        					}
        					// winner absorbs 1/2 loser energy
        					// loser dies
        					if (num_1 > num_2) { // critter 1 wins
        						population.get(outer_idx).energy += population.get(inner_idx).energy/2;
        						population.get(inner_idx).energy = 0;
        					}
        					// num2 was larger or both were equal
        					// either way, critter2 will always win
        					else {
        						population.get(inner_idx).energy += population.get(outer_idx).energy/2;
        						population.get(outer_idx).energy = 0;
        					}
        				}
        			}
        		}
        		// need to delete dead critters
        		// main critter is dead
        		if (population.get(outer_idx).energy <= 0) {
        			population.remove(outer_idx);
        			outer_idx -= 1;
        			inner_idx = population.size();
        		}
        		// main critter beat other critter
        		if (population.get(inner_idx).energy <= 0) {
        			population.remove(inner_idx);
        			// removing critter, comes before main critter
        			if (inner_idx < outer_idx) {
        				outer_idx -= 1;
        			}
        			// removing critter, comes after main critter
        			else {
        				inner_idx -= 1;
        			}
        		}
        		// increment inner idx
        		inner_idx += 1;
        	}
    		outer_idx += 1;
    	}
    }
    
    /**
     * Generates more clover/food.
     *
     */
    private static void genClover() {
    	for (int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++) {
    		try {
				createCritter("Clover");
			} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    /**
     * Deducts energy each time critter attempts
     * to walk (1 step). Only moves critter's 
     * position if valid (i.e. hasn't moved in 
     * current time step and if in encounter mode, 
     * position desired isn't occupied).
     *
     * @param int direction desired
     */
    protected final void walk(int direction) {
        energy -= Params.WALK_ENERGY_COST;
        checkMove(direction, 1);
    }

    /**
     * Deducts energy each time critter attempts
     * to run (2 steps). Only moves critter's 
     * position if valid (i.e. hasn't moved in 
     * current time step and if in encounter mode, 
     * position desired isn't occupied).
     *
     * @param int direction desired
     */
    protected final void run(int direction) {
    	energy -= Params.RUN_ENERGY_COST;
    	checkMove(direction, 2);
    }
    
    /**
     * Checks whether a critter can walk or run during
     * a time step or encounter. If it can, moves critter.
     * If not, leaves critter in current position.
     *
     * @param int direction desired, int number of steps 
     */
    private void checkMove(int direction, int step) {
        if (!moved) { // hasn't moved in time step
        	if (!encounter) { // not in encounter mode
        		move(direction, step); // can move
        	}
        	// encounter mode, make sure you don't run or walk
        	// to same position as another critter
        	else {
        		int og_x = x_coord;
        		int og_y = y_coord;
        		move(direction, step);
        		for (Critter critter : population) {
        			if (this != critter) {
	        			if (critter.x_coord == x_coord && critter.y_coord == y_coord) {
	        				x_coord = og_x;
	        				y_coord = og_y;
	        				break;
	        			}
        			}
        		}
        	}
        	moved = true; // successful move
        }
    }
    
    private void move(int direction, int steps) {
    	/* wraps x or y coordinate if moving will cause it to go
    	 * out of bounds. If no out of bounds, just moves position
    	 * by "steps."
    	 */
    	if (direction == 0) {		// east
    		if (x_coord + steps >= Params.WORLD_WIDTH) { 
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 							
    			x_coord++;
    		}
    	}
    	else if (direction == 1) {	// north east
    		if (x_coord + steps >= Params.WORLD_WIDTH) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 
    			x_coord++;
    		}
    		
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - Math.abs(y_coord - steps));
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 2) {	// north
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - Math.abs(y_coord - steps));
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 3) {	// north west
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - Math.abs(x_coord - steps));
    		}
    		else { 
    			x_coord--;
    		}
    		
    		if (y_coord - steps < 0) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - Math.abs(y_coord - steps));
    		}
    		else { 
    			y_coord--;
    		}
    	}
    	else if (direction == 4) {	// west
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - Math.abs(x_coord - steps));
    		}
    		else { 
    			x_coord--;
    		}
    	}
    	else if (direction == 5) {	// south west
    		if (x_coord - steps < 0) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - Math.abs(x_coord - steps));
    		}
    		else { 
    			x_coord--;
    		}
    		
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    	else if (direction == 6) { // south
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    	else {						// south east
    		if (x_coord + steps >= Params.WORLD_WIDTH) {
    			x_coord = Math.abs(Params.WORLD_WIDTH - (x_coord + steps));
    		}
    		else { 
    			x_coord++;
    		}
    		
    		if (y_coord + steps >= Params.WORLD_HEIGHT) {
    			y_coord = Math.abs(Params.WORLD_HEIGHT - (y_coord + steps));
    		}
    		else { 
    			y_coord++;
    		}
    	}
    }

    /**
     * Produces offspring of type Critter one
     * step in direction desired.
     *
     * @param Critter to produce, int direction desired
     */
    protected final void reproduce(Critter offspring, int direction) {
    	if (offspring.energy >= Params.MIN_REPRODUCE_ENERGY) {
			try {
				Critter new_critter = offspring.getClass().newInstance();
				// instantiating new critter's parameters
				new_critter.energy = offspring.energy / 2;
				new_critter.x_coord = offspring.x_coord;
				new_critter.y_coord = offspring.y_coord;
				offspring.energy = (int) Math.ceil(offspring.energy / 2);
				// placing new critter position
				new_critter.move(direction, 1);
				// add new critter into babies collection
				babies.add(new_critter);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
