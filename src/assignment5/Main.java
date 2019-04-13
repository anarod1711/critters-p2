package assignment5;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.*;


public class Main extends Application{
	
	private static List<String> critter_classes;
	private static String myPackage;
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    static {
    	// printing all classes that are of type Critter
    	critter_classes = new ArrayList<String>();
    	ClassFinder.findClasses(new Visitor<String>() {
		    @Override
		    public boolean visit(String class_name) {
		    	try {
		    		if (class_name.substring(0, myPackage.length()).equals(myPackage)){
		    			Object critter_sub = Class.forName(class_name).newInstance();
						if (critter_sub instanceof Critter) {
							System.out.println(class_name.substring(myPackage.length()+1));
							critter_classes.add(class_name.substring(myPackage.length()+1));
						}
		    		}
				} catch (ClassNotFoundException | IllegalArgumentException | SecurityException 
						| InstantiationException | IllegalAccessException e) {
				}
		        return true; // return false if you don't want to see any more classes
		    }
		});
    }

    public static void main(String[] args) {
    	
    	// launching javafx
        launch(args);
    }

    @Override
	public void start(Stage primaryStage) { // primaryStage is created by Java VM
		try {
			/*
			 * Create control panel
			 * Things that I need
			 * 1. view world
			 * 2. ability to create critters, even test critters ## DONE ##
			 * 3. ability to do time steps ## DONE ##
			 * 4. ability to runStats ** example does not have this
			 * 5. ability to set seed ## DONE ##
			 * 6. ability to terminate the program ## DONE ##
			 * 7. "run" --> the speed of animation
			 */
			GridPane grid = new GridPane();
			// adding header for control
			Text header = new Text();
	    	header.setText("Welcome to Critters");
	    	header.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
	    	header.setTranslateX(5);
	    	header.setTranslateY(10);
	    	// Create Critter
	    	Text critter_name = new Text(); // title for type of critters
	    	critter_name.setText("Choose a Critter:");
	    	critter_name.setFont(Font.font("Verdana", 12));
	    	critter_name.setTranslateX(5);
	    	critter_name.setTranslateY(40);
	    	ChoiceBox<String> critter_choices = new ChoiceBox(); // choices of critters
	    	critter_choices.getItems().addAll(critter_classes);
	    	critter_choices.setTranslateX(150);
	    	critter_choices.setTranslateY(40);
	    	Text critter_num = new Text(); // title for number of critters
	    	critter_num.setText("Choose # of Critters:");
	    	critter_num.setFont(Font.font("Verdana", 12));
	    	critter_num.setTranslateX(5);
	    	critter_num.setTranslateY(70);
	    	TextField critter_num_input = new TextField(); // user input for num of critters
	    	critter_num_input.setTranslateX(150);
	    	critter_num_input.setTranslateY(70);
	    	Button critter_add_btn = new Button("Add Critters"); // button to add critters
	    	critter_add_btn.setTranslateX(380);
	    	critter_add_btn.setTranslateY(70);
	    	Text critters_added = new Text(); // message when added critters
	    	critters_added.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
	    	critters_added.setTranslateX(150);
	    	critters_added.setTranslateY(90);
	    	critters_added.setFill(Color.RED);
	    	// run the world (time steps)
	    	Text timestep_num = new Text(); // title for time steps
	    	timestep_num.setText("Choose # of Steps:");
	    	timestep_num.setFont(Font.font("Verdana", 12));
	    	timestep_num.setTranslateX(5);
	    	timestep_num.setTranslateY(130);
	    	TextField timestep_input = new TextField(); // user input for num of steps
	    	timestep_input.setTranslateX(150);
	    	timestep_input.setTranslateY(130);
	    	Button timestep_btn = new Button("Add Steps"); // button to add steps
	    	timestep_btn.setTranslateX(380);
	    	timestep_btn.setTranslateY(130);
	    	Text timestep_added = new Text(); // message when added critters
	    	timestep_added.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
	    	timestep_added.setTranslateX(150);
	    	timestep_added.setTranslateY(150);
	    	timestep_added.setFill(Color.RED);
	    	// set the seed
	    	Text seed_num = new Text(); // title for time steps
	    	seed_num.setText("Choose Seed:");
	    	seed_num.setFont(Font.font("Verdana", 12));
	    	seed_num.setTranslateX(5);
	    	seed_num.setTranslateY(180);
	    	TextField seed_input = new TextField(); // user input for num of steps
	    	seed_input.setTranslateX(150);
	    	seed_input.setTranslateY(180);
	    	Button seed_btn = new Button("Add Seed"); // button to add steps
	    	seed_btn.setTranslateX(380);
	    	seed_btn.setTranslateY(180);
	    	Text seed_added = new Text(); // message when added critters
	    	seed_added.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
	    	seed_added.setTranslateX(150);
	    	seed_added.setTranslateY(200);
	    	seed_added.setFill(Color.RED);
	    	// terminate the program
	    	Button quit_btn = new Button("Quit Critters"); // button to add steps
	    	quit_btn.setTranslateX(415);
	    	quit_btn.setTranslateY(570);
	    	// view the world
	    	Button viewWorld_btn = new Button("Display World"); // button to add steps
	    	viewWorld_btn.setTranslateX(5);
	    	viewWorld_btn.setTranslateY(570);
	    	
	    	// adding nodes to grid
	    	grid.getChildren().add(header);
	    	grid.getChildren().add(critter_name);
	    	grid.getChildren().add(critter_num);
	    	grid.getChildren().add(critter_choices);
	    	grid.getChildren().add(critter_num_input);
	    	grid.getChildren().add(critter_add_btn);
	    	grid.getChildren().add(critters_added);
	    	grid.getChildren().add(timestep_num);
	    	grid.getChildren().add(timestep_input);
	    	grid.getChildren().add(timestep_btn);
	    	grid.getChildren().add(timestep_added);
	    	grid.getChildren().add(seed_num);
	    	grid.getChildren().add(seed_input);
	    	grid.getChildren().add(seed_btn);
	    	grid.getChildren().add(seed_added);
	    	grid.getChildren().add(quit_btn);
	    	grid.getChildren().add(viewWorld_btn);
	    	
	    	// setting the scene and displaying to the stage
			Scene scene = new Scene(grid, 500, 600);
			primaryStage.setX(20);
			primaryStage.setY(20);
			primaryStage.setTitle("Critter Control");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*
			 * Create the second window to display world
			 */
			Stage worldStage = new Stage(); // creates a second stage for the button.
			worldStage.setX(700);
			worldStage.setY(50);
			worldStage.setTitle("World");	
			GridPane worldGrid = new GridPane();
			Scene secondScene = new Scene(worldGrid, 500, 500); // creates a second scene object with the Stackpane
			worldStage.setScene(secondScene); // puts the scene onto the second stage 
			
			/*
			 * Action to be performed when view world button is pressed.
			 */
			viewWorld_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					Critter.displayWorld(worldGrid);
					worldStage.show(); // display the stage with the scene
				}
			});
			
			/*
			 * Action to be performed when seed button is pressed.
			 */
			quit_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					System.exit(0);
				}
			});
			
			/*
			 * Action to be performed when seed button is pressed.
			 */
			seed_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					// creating critters
					grid.getChildren().remove(seed_added);
					grid.getChildren().add(seed_added);
					long count = 0;
					try {
						if (seed_input.getText().equals("")) {
							count = 1;
						}
						else {
							count = Long.parseLong(seed_input.getText());
						}
						Critter.setSeed(count);
						seed_added.setText("Seed set to " + count);
					} catch (NumberFormatException e) {
						seed_added.setText("error processing: " + seed_input.getText());
					}
				}
			});
			
			/*
			 * Action to be performed when time step button is pressed.
			 */
			timestep_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					// creating critters
					grid.getChildren().remove(timestep_added);
					grid.getChildren().add(timestep_added);
					int count = 0;
					try {
						if (timestep_input.getText().equals("")) {
							count = 1;
						}
						else {
							count = Integer.parseInt(timestep_input.getText());
							if (count <= 0) {
								count = 1;
							}
						}
						for (int i = 0; i < count; i++) {
							Critter.worldTimeStep();
						}
						timestep_added.setText(count + " steps added");
					} catch (NumberFormatException | InvalidCritterException e) {
						timestep_added.setText("error processing: " + timestep_input.getText());
					}
				}
			});
			
			/*
			 * Action to be performed when critter button is pressed.
			 */
			critter_add_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					// creating critters
					grid.getChildren().remove(critters_added);
					grid.getChildren().add(critters_added);
					int count = 0;
					try {
						if (critter_choices.getValue() == null) {
							critters_added.setText("Please select a Critter");
						}
						else {
							if (critter_num_input.getText().equals("")) {
								count = 1;
							}
							else {
								count = Integer.parseInt(critter_num_input.getText());
								if (count <= 0) {
									count = 1;
								}
							}
							for (int i = 0; i < count; i++) {
								Critter.createCritter(critter_choices.getValue());
							}
							critters_added.setText(count + " " + critter_choices.getValue() + " added");
						}
					} catch (NumberFormatException | InvalidCritterException e) {
						critters_added.setText("error processing: " + critter_num_input.getText());
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
