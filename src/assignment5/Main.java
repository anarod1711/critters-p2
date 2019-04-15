/*
 * CRITTERS Main.java
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


public class Main extends Application{
	
	private static List<String> critter_classes;
	private static Integer[] anim_speeds = {1,2,5,10,20,50,100};
	private static int critterName_len = 0;
	private static String myPackage;
	private static boolean run_active = true;
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
							if (class_name.substring(myPackage.length()+1).length() > critterName_len) {
								critterName_len = class_name.substring(myPackage.length()+1).length();
							}
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
			BorderPane root = new BorderPane();
			
			GridPane grid = new GridPane();
			
			GridPane center = new GridPane();
			center.setMinWidth(1000);
			
			ScrollPane scrollpane = new ScrollPane();
			scrollpane.setContent(center);
			scrollpane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			scrollpane.setMaxHeight(300);
			scrollpane.setTranslateY(70);
			
			
			root.setTop(grid);			
			root.setCenter(scrollpane);
			
			
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
	    	critter_num.setTranslateY(80);
	    	TextField critter_num_input = new TextField(); // user input for num of critters
	    	critter_num_input.setMinWidth(225);
	    	critter_num_input.setMaxWidth(225);
	    	critter_num_input.setTranslateX(150);
	    	critter_num_input.setTranslateY(80);
	    	Button critter_add_btn = new Button("Add Critters"); // button to add critters
	    	critter_add_btn.setTranslateX(380);
	    	critter_add_btn.setTranslateY(80);
	    	Text critters_added = new Text(); // message when added critters
	    	critters_added.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
	    	critters_added.setTranslateX(150);
	    	critters_added.setTranslateY(100);
	    	critters_added.setFill(Color.RED);
	    	
	    	
	    	// run the world (time steps)
	    	Text timestep_num = new Text(); // title for time steps
	    	timestep_num.setText("Choose # of Steps:");
	    	timestep_num.setFont(Font.font("Verdana", 12));
	    	timestep_num.setTranslateX(5);
	    	timestep_num.setTranslateY(130);
	    	TextField timestep_input = new TextField(); // user input for num of steps
	    	timestep_input.setMinWidth(225);
	    	timestep_input.setMaxWidth(225);
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
	    	seed_input.setMinWidth(225);
	    	seed_input.setMaxWidth(225);
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
	    	
	    	
	    	// runStats
	    	int y = 0;
	    	Collections.sort(critter_classes);
	    	for (int i = 0; i < critter_classes.size(); i++) {
	    		Text checkboxName = new Text(); // title for time steps
		    	checkboxName.setText(critter_classes.get(i));
		    	checkboxName.setFont(Font.font("Verdana", 12));
		    	checkboxName.setTranslateX(5);
		    	checkboxName.setTranslateY(y);
		    	CheckBox checkbox = new CheckBox();
		    	checkbox.setTranslateX(75);
		    	checkbox.setTranslateY(y);
		    	
		    	Text test = new Text();
		    	test.setFont(Font.font("Verdana", 12));
            	test.setTranslateX(100);
		    	test.setTranslateY(y);
		    	
		    	y += 20;
		    	String critter = critter_classes.get(i);
		    	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
		    		  
	                public void handle(ActionEvent e) 
	                { 
	                	startTask(checkbox, grid, test, critter);
	                }
	            }; 
	  
	            // set event to checkbox 
	            checkbox.setOnAction(event);	    	
		    	
		    	center.getChildren().add(checkboxName);
		    	center.getChildren().add(checkbox);
		    	center.getChildren().add(test);
	    	}    	
	    	
	    	
	    	// animation
	    	AnchorPane animation_grid = new AnchorPane(); // new gridpane, for disabling
	    	ChoiceBox<Integer> run_choices = new ChoiceBox(); // choices of critters
	    	run_choices.getItems().addAll(anim_speeds);
	    	run_choices.getSelectionModel().selectFirst();
	    	run_choices.setTranslateX(5);
	    	run_choices.setTranslateY(-10);
	    	Button run_btn = new Button("Run Animation"); // button to add steps
	    	run_btn.setTranslateX(70);
	    	run_btn.setTranslateY(-10);
	    	
	    	
	    	
	    	
	    	// terminate the program
	    	Button quit_btn = new Button("Quit Critters"); // button to add steps
	    	quit_btn.setTranslateX(400);
	    	quit_btn.setTranslateY(10);
	    	// view the world
	    	Button viewWorld_btn = new Button("Display World"); // button to add steps
	    	viewWorld_btn.setTranslateX(250);
	    	viewWorld_btn.setTranslateY(10);
	    	
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
	    	animation_grid.getChildren().add(run_btn);
	    	animation_grid.getChildren().add(run_choices);
	    	
	    	// setting the scene and displaying to the stage
	    	root.setBottom(animation_grid);
			Scene scene = new Scene(root, 500, 600);
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
			Scene secondScene = new Scene(worldGrid, 600, 600); // creates a second scene object with the Stackpane
			worldStage.setScene(secondScene); // puts the scene onto the second stage 
			
			
			
			/*
			 * Action to be performed when run button is pressed.
			 */
			run_btn.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					if (run_active) {
						run_active = false;
						run_btn.setText("Stop Animation");
						startTask(run_choices, worldGrid, grid);
					}
					else {
						run_active = true;
						run_btn.setText("Run Animation");
						grid.setDisable(false);
					}
	            }
			});
			
			
			
			
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
					Critter.displayWorld(worldGrid);
					worldStage.show(); // display the stage with the scene
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
					Critter.displayWorld(worldGrid);
					worldStage.show(); // display the stage with the scene
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void startTask(ChoiceBox run_cb, GridPane worldGrid, GridPane grid) 
    {
        // Create a Runnable
        Runnable task = new Runnable()
        {
            public void run()
            {
                runTask(run_cb, worldGrid, grid);
            }
        };
 
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }  
    
    public void runTask(ChoiceBox run_cb, GridPane worldGrid, GridPane grid) {
        while(!run_active) {
            try {
            	//System.out.println(run_cb.getValue());
            	grid.setDisable(true);
            	for (int i = 0; i < (int) run_cb.getValue(); i++) {
            		//System.out.println((int) run_cb.getValue());
            		Critter.worldTimeStep();
            	}
            	Platform.runLater(new Runnable() {
            	    @Override
            	    public void run() {
            	    	Critter.displayWorld(worldGrid);
            	    }
            	});
                Thread.sleep(500);
            }
            catch (InterruptedException | InvalidCritterException e) {
                //e.printStackTrace();
            }
        }
    }
    
    // attempt to continuously display run stats
    public void startTask(CheckBox run_stats,  GridPane grid, Text test, String critter) 
    {
        // Create a Runnable
        Runnable task1 = new Runnable()
        {
            public void run()
            {
                runTask(run_stats, grid, test, critter);
            }
        };
 
        // Run the task in a background thread
        Thread backgroundThread1 = new Thread(task1);
        // Terminate the running thread if the application exits
        backgroundThread1.setDaemon(true);
        // Start the thread
        backgroundThread1.start();
    }  
    
    public void runTask(CheckBox run_stats,  GridPane grid, Text test, String critter) {
        while(run_stats.isSelected()) {
            try {
            	try {
            		List<Critter> critters = Critter.getInstances(critter);
                    if (run_stats.isSelected()) {
                    	Class<?> critterClass = Class.forName(myPackage + "." + critter);
						Constructor<?> constructor = critterClass.getConstructor();
						Object new_critter = constructor.newInstance();
						Method m = new_critter.getClass().getDeclaredMethod("runStats", List.class);
						String str_stats = ((String) m.invoke(critterClass, critters));
						str_stats = str_stats.replace("\n", "");
						str_stats = str_stats.trim().replaceAll(" +", " ");
						test.setText(str_stats);
                    }	                    	
                    else {
                        test.setText("");
                    }
            	} 
                catch (ClassNotFoundException 
						| NoSuchMethodException | SecurityException 
						| IllegalAccessException | InvalidCritterException
						| IllegalArgumentException | InvocationTargetException 
						| InstantiationException k) {
					try {
						List<Critter> critters = Critter.getInstances(critter);
						critters = Critter.getInstances(critter);
						test.setText(((String) Critter.runStats(critters)));
					} catch (InvalidCritterException e1) {
					}
				}
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
            }
        }
        test.setText("");
    }
}
