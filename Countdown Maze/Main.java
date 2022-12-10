/**
 * @ Author
 * Abhimanyu Karki
 * CS 196
 */

package application;
	
import java.awt.event.ActionEvent;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Main extends Application {
	
	// Array to track the movements of the player.
	//.double []pathArray = new double[1000];
	PathTracker beginPath = new PathTracker();
    PathTracker tempp = new PathTracker();
    
    Button viewPath;
    
	// counter variable for iteration through pathArray.
	//.int counter = 2;
	
	// This line is used to show the direction of the movements to the player after the game completes.
	Line pathLine;
	
	
	private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTTIME;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// The starting point of the path should be the starting point of the player.
			beginPath.x = (30/2);
			beginPath.y = (40/2);
			beginPath.next = tempp;
			
			
			Group congratsRoot = new Group();
			// Scene for congratulations.
			Scene congratsScene = new Scene(congratsRoot, 600, 600, Color.BLACK);
			
			Group timeOutRoot = new Group();
			// Scene for time run out.
			Scene timeOutScene = new Scene(timeOutRoot, 600, 600, Color.BLACK);
			
			/* Array for control movements.
			 * 0 - UP
			 * 1 - DOWN
			 * 2 - LEFT
			 * 3 - RIGHT
			 */
			
			// Initialize the controlArray.
			int [] controlArray = {0, 1, 2, 3};
			
			Group entryRoot = new Group();
			// Entry Scene for the Application.
			Scene entryScene = new Scene(entryRoot, 600, 600, Color.BLACK);
			
			// 'START' button to initiate the game.
			Button startButton = new Button("START");
			startButton.setLayoutX(250);
			startButton.setLayoutY(190);
			startButton.setPrefHeight(50);
			startButton.setPrefWidth(100);
			startButton.setStyle("-fx-font-size:20");
			startButton.setStyle("-fx-font-family:Verdana");
			startButton.setStyle("-fx-background-color:Cyan");
			
			// 'CONTROLS' button to customize the control keys.
			Button controlButton = new Button("CONTROLS");
			controlButton.setLayoutX(250);
			controlButton.setLayoutY(290);
			controlButton.setPrefHeight(50);
			controlButton.setPrefWidth(100);
			controlButton.setStyle("-fx-font-size:20");
			controlButton.setStyle("-fx-font-family:Verdana");
			controlButton.setStyle("-fx-background-color:Cyan");
			
			// Exit button.
			Button exitButton = new Button("EXIT");
			exitButton.setLayoutX(250);
			exitButton.setLayoutY(390);
			exitButton.setPrefHeight(50);
			exitButton.setPrefWidth(100);
			exitButton.setStyle("-fx-font-size:20");
			exitButton.setStyle("-fx-font-family:Verdana");
			exitButton.setStyle("-fx-background-color:Cyan");
			entryRoot.getChildren().add(exitButton);
			
			exitButton.setOnAction(event -> Platform.exit());
			
			Group controlRoot = new Group();
			// Window for customizing the controls.
			Scene controlScene = new Scene(controlRoot, 600, 600, Color.BLACK);
			// Change the scene.
			controlButton.setOnAction(e -> primaryStage.setScene(controlScene));
			
			// 'Directions' text.
			Text direction = new Text();
			direction.setText("Directions");
			direction.setX(476);
			direction.setY(40);
			direction.setFill(Color.ANTIQUEWHITE);
			
			// Options for selecting directions of movement.
			ComboBox<String> menu = new ComboBox();
			// Add menuitems.
			menu.getItems().add("UP");
			menu.getItems().add("Down");
			menu.getItems().add("Right");
			menu.getItems().add("Left");
			// Set the position of the menu.
			menu.setPrefHeight(30);
			menu.setPrefWidth(100);
			menu.setLayoutX(450);
			menu.setLayoutY(50);
			
			// 'OK' button to set the changes.
			Button okDirection = new Button("OK");
			okDirection.setLayoutX(555);
			okDirection.setLayoutY(53);
			okDirection.setPrefHeight(20);
			okDirection.setPrefWidth(40);
			okDirection.setStyle("-fx-font-size:20");
			okDirection.setStyle("-fx-font-family:Verdana");
			okDirection.setStyle("-fx-background-color:Cyan");
			
			// 'Upward Arrow Key' button. It allows to customize the movement associated with
			// upward arrow key button.
			Button controlUp = new Button("Upward Arrow Key");
			controlUp.setLayoutX(110);
			controlUp.setLayoutY(40);
			controlUp.setPrefHeight(50);
			controlUp.setPrefWidth(130);
			controlUp.setStyle("-fx-font-size:20");
			controlUp.setStyle("-fx-font-family:Verdana");
			controlUp.setStyle("-fx-background-color:Cyan");
			
			// Intializing Temp array with direction codes.
			int [] tempArray = {0, 1, 2, 3};
			
			controlRoot.getChildren().add(controlUp);
			controlUp.setOnAction(e -> {
				// Add the selection elements to the window when the button is pressed.
				controlRoot.getChildren().add(direction);
				controlRoot.getChildren().add(okDirection);
				controlRoot.getChildren().add(menu);
				
				// When the 'OK' button is clicked, the user's selection is stored in the temp array.
				okDirection.setOnAction(event -> {
					// Get the user selected option from the list.
					String value = (String) menu.getValue();
					if (value != null) {
						// Initialize the 0 index of the tempArray.
						if(value.equals("UP")) {
							tempArray[0] = 0;
						}
						if(value.equals("Down")) {
							tempArray[0] = 1;
						}
						if(value.equals("Left")) {
							tempArray[0] = 2;
						}
						if(value.equals("Right")) {
							tempArray[0] = 3;
						}
					}
					// Remove the selection elements after customization is made.
					controlRoot.getChildren().remove(direction);
					controlRoot.getChildren().remove(okDirection);
					controlRoot.getChildren().remove(menu);
				});
			});
			
			// 'Downward Arrow Key' button. It allows to customize the movement associated with
			// Downward arrow key button.
			Button controlDown = new Button("Downward Arrow Key");
			controlDown.setLayoutX(110);
			controlDown.setLayoutY(200);
			controlDown.setPrefHeight(50);
			controlDown.setPrefWidth(130);
			controlDown.setStyle("-fx-font-size:20");
			controlDown.setStyle("-fx-font-family:Verdana");
			controlDown.setStyle("-fx-background-color:Cyan");
			
			controlRoot.getChildren().add(controlDown);
			controlDown.setOnAction(e -> {
				// Add the selection elements to the window when the button is pressed.
				controlRoot.getChildren().add(direction);
				controlRoot.getChildren().add(okDirection);
				controlRoot.getChildren().add(menu);
				
				// When the 'OK' button is clicked, the user's selection is stored in the temp array.
				okDirection.setOnAction(event -> {
					// Get the user selected option from the list.
					String value = (String) menu.getValue();
					if (value != null) {
						// Set the 1 index of the tempArray.
						if(value.equals("UP")) {
							tempArray[1] = 0;
						}
						if(value.equals("Down")) {
							tempArray[1] = 1;
						}
						if(value.equals("Left")) {
							tempArray[1] = 2;
						}
						if(value.equals("Right")) {
							tempArray[1] = 3;
						}
					}
					// remove the selection elements, once the customization is done by the user.
					controlRoot.getChildren().remove(direction);
					controlRoot.getChildren().remove(okDirection);
					controlRoot.getChildren().remove(menu);
				});
			});
			
			// 'Right Arrow Key' button. It allows to customize the movement associated with
			// Right arrow key button.
			Button controlRight = new Button("Right Arrow Key");
			controlRight.setLayoutX(235);
			controlRight.setLayoutY(120);
			controlRight.setPrefHeight(50);
			controlRight.setPrefWidth(102);
			controlRight.setStyle("-fx-font-size:20");
			controlRight.setStyle("-fx-font-family:Verdana");
			controlRight.setStyle("-fx-background-color:Cyan");
			
			controlRoot.getChildren().add(controlRight);
			controlRight.setOnAction(e -> {
				// Add the selection elements to the window when the button is pressed.
				controlRoot.getChildren().add(direction);
				controlRoot.getChildren().add(okDirection);
				controlRoot.getChildren().add(menu);
				// When the 'OK' button is clicked, the user's selection is stored in the temp array.
				okDirection.setOnAction(event -> {
					// Get the user selected option from the list.
					String value = (String) menu.getValue();
					if (value != null) {
						if(value.equals("UP")) {
							tempArray[3] = 0;
						}
						if(value.equals("Down")) {
							tempArray[3] = 1;
						}
						if(value.equals("Left")) {
							tempArray[3] = 2;
						}
						if(value.equals("Right")) {
							tempArray[3] = 3;
						}
						// Remove the selection elements once the customization is done by the user.
						controlRoot.getChildren().remove(direction);
						controlRoot.getChildren().remove(okDirection);
						controlRoot.getChildren().remove(menu);
					}
				});
			});
			
			// 'Left Arrow Key' button. It allows to customize the movement associated with
			// Left arrow key button.
			Button controlLeft = new Button("Left Arrow Key");
			controlLeft.setLayoutX(20);
			controlLeft.setLayoutY(120);
			controlLeft.setPrefHeight(50);
			controlLeft.setPrefWidth(102);
			controlLeft.setStyle("-fx-font-size:20");
			controlLeft.setStyle("-fx-font-family:Verdana");
			controlLeft.setStyle("-fx-background-color:Cyan");
			
			controlRoot.getChildren().add(controlLeft);
			controlLeft.setOnAction(e -> {
				// Add the selection elements to the window when the button is pressed.
				controlRoot.getChildren().add(direction);
				controlRoot.getChildren().add(okDirection);
				controlRoot.getChildren().add(menu);
				
				// When the 'OK' button is clicked, the user's selection is stored in the temp array.
				okDirection.setOnAction(event -> {
					// Getting the value selected by player from the menu.
					String value = (String) menu.getValue();
					if (value != null) {
						// Updating the tempArray as per the directions selected by the player.
						if(value.equals("UP")) {
							tempArray[2] = 0;
						}
						if(value.equals("Down")) {
							tempArray[2] = 1;
						}
						if(value.equals("Left")) {
							tempArray[2] = 2;
						}
						if(value.equals("Right")) {
							tempArray[2] = 3;
						}
						// Remove the selection elements, once the customization is done by the user.
						controlRoot.getChildren().remove(direction);
						controlRoot.getChildren().remove(okDirection);
						controlRoot.getChildren().remove(menu);
					}
				});
			});
			
			// Add the buttons to the window.
			entryRoot.getChildren().add(startButton);
			entryRoot.getChildren().add(controlButton);

			// Set the entry Scene (Main Menu).
			primaryStage.setScene(entryScene);
			
			
			// 'GO BACK' button to go back to the main menu.
			Button goBackButton = new Button("GO BACK");
			goBackButton.setLayoutX(250);
			goBackButton.setLayoutY(490);
			goBackButton.setPrefHeight(50);
			goBackButton.setPrefWidth(100);
			goBackButton.setStyle("-fx-font-size:20");
			goBackButton.setStyle("-fx-font-family:Verdana");
			goBackButton.setStyle("-fx-background-color:Cyan");

			// When the 'GO BACK' button is pressed, the Main menu is displayed.
			goBackButton.setOnAction(e -> {
				//Copy the control directions from tempArray to controlArray.
				// This array is used to know the user selected controls while moving the player.
				for (int i = 0; i < 4; i ++) {
					controlArray[i] = tempArray[i]; 
				} 
				
				// Go back to the main menu.
				primaryStage.setScene(entryScene);
			});
			
			controlRoot.getChildren().add(goBackButton);
			
			
			
			// Main program.
			Group root = new Group();
			Scene scene = new Scene(root, 600, 600, Color.BLACK);
			
			Line line = new Line(20, 30, 580, 30);
			line.setStroke(Color.RED);
			line.setStrokeWidth(2.0);
			root.getChildren().add(line);

			Line line1 = new Line(20, 30, 20, 570);
			line1.setStroke(Color.RED);
			line1.setStrokeWidth(2.0);
			root.getChildren().add(line1);
			
			Line line2 = new Line(20, 570, 580, 570);
			line2.setStroke(Color.RED);
			line2.setStrokeWidth(2.0);
			root.getChildren().add(line2);

			Line line3 = new Line(580, 30, 580, 570);
			line3.setStroke(Color.RED);
			line3.setStrokeWidth(2.0);
			root.getChildren().add(line3);
			
			
			Line newLine = null;
			int [] array1 = {40, 100, 120, 500, 520, 560};
			for (int i = 0 ; i <= 4 ; i += 2) {
				newLine = new Line(array1[i], 50, array1[i + 1], 50);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array2 = {40, 100, 120, 500, 520, 560};
			for (int i = 0; i <= 4; i +=2) {
				newLine = new Line(array2[i], 550, array2[i + 1], 550);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array3 = {50, 110, 130, 490, 510, 550};
			for (int i = 0; i <= 4; i += 2) {
				newLine = new Line(40, array3[i] , 40 , array3[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array4 = {50, 110, 130, 490, 510, 550};
			for (int i = 0; i <= 4; i += 2) {
				newLine = new Line(560, array4[i] , 560 , array4[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int [] array11 = {60, 200, 220, 540};
			for (int i = 0 ; i <= 2; i += 2) {
				newLine = new Line(array11[i], 70, array11[i + 1], 70);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array21 = {60, 200, 220, 540};
			for (int i = 0; i <= 2; i +=2) {
				newLine = new Line(array21[i], 530, array21[i + 1], 530);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array31 = {70, 210, 230, 530};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(60, array31[i] , 60 , array31[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array41 = {70, 210, 230, 530};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(540, array41[i] , 540 , array41[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int [] array12 = {80, 160, 180, 400, 420, 520};
			for (int i = 0 ; i <= 4 ; i += 2) {
				newLine = new Line(array12[i], 90, array12[i + 1], 90);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array22 = {80, 160, 180, 400, 420, 520};
			for (int i = 0; i <= 4; i +=2) {
				newLine = new Line(array22[i], 510, array22[i + 1], 510);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array32 = {90, 250, 270, 410, 430, 510};
			for (int i = 0; i <= 4; i += 2) {
				newLine = new Line(80, array32[i] , 80 , array32[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array42 = {90, 250, 270, 410, 430, 510};
			for (int i = 0; i <= 4; i += 2) {
				newLine = new Line(520, array42[i] , 520 , array42[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int [] array13 = {100, 240, 260, 500};
			for (int i = 0 ; i <= 2; i += 2) {
				newLine = new Line(array13[i], 110, array13[i + 1], 110);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array23 = {100, 460, 480, 500};
			for (int i = 0; i <= 2; i +=2) {
				newLine = new Line(array23[i], 490, array23[i + 1], 490);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			newLine = new Line(100, 110, 100, 490);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(500, 110, 500, 490);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			int [] array14 = {120, 300, 320, 480};
			for (int i = 0 ; i <= 2; i += 2) {
				newLine = new Line(array14[i], 130, array14[i + 1], 130);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array24 = {120, 300, 320, 480};
			for (int i = 0; i <= 2; i +=2) {
				newLine = new Line(array24[i], 470, array24[i + 1], 470);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array34 = {130, 310, 330, 470};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(120, array34[i] , 120 , array34[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array44 = {130, 310, 330, 470};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(480, array44[i] ,480 , array44[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array51 = {140, 360, 380, 460};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array51[i], 150, array51[i + 1], 150);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array52 = {140, 360, 380, 460};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array52[i], 450, array52[i + 1], 450);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array53 = {150, 210, 230, 450};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(140, array53[i], 140, array53[i + 1]);
				newLine.setStrokeWidth(2.0);
				newLine.setStroke(Color.RED);
				root.getChildren().add(newLine);
			}
			
			int []array54 = {150, 210, 230, 450};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(460, array54[i], 460, array54[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array61 = {160, 240, 260, 440};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array61[i], 170, array61[i + 1], 170);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array62 = {160, 240, 260, 440};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array62[i], 430, array62[i + 1], 430);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array63 = {170, 370, 390, 430};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(160, array63[i], 160, array63[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array64 = {170, 370, 390, 430};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(440, array64[i], 440, array64[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			 
			int []array71 = {180, 320, 340, 420};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array71[i], 190, array71[i + 1], 190);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array72 = {180, 320, 340, 420};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(array72[i], 410, array72[i + 1], 410);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array73 = {190, 310, 330, 410};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(180, array73[i], 180, array73[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			int []array74 = {190, 310, 330, 410};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(420, array74[i], 420, array74[i + 1]);
				newLine.setStroke(Color.RED);
				newLine.setStrokeWidth(2.0);
				root.getChildren().add(newLine);
			}
			
			newLine = new Line(200, 210, 400, 210);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(200, 210, 200, 230);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(200, 250, 200, 390);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(200, 390, 400, 390);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(400, 210, 400, 230);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			newLine = new Line(400, 250, 400, 390);
			newLine.setStroke(Color.RED);
			newLine.setStrokeWidth(2.0);
			root.getChildren().add(newLine);
			
			int []array91 = {220, 320, 340, 380};
			for (int i = 0; i <= 2; i += 2 ) {
				newLine = new Line(array91[i], 230, array91[i + 1], 230);
				newLine.setStroke(Color.GREEN);
				newLine.setStrokeWidth(3.0);
				root.getChildren().add(newLine);
			}
			
			int []array92 = {220, 320, 340, 380};
			for (int i = 0; i <=2; i += 2) {
				newLine = new Line(array92[i], 370, array92[i + 1], 370);
				newLine.setStroke(Color.GREEN);
				newLine.setStrokeWidth(3.0);
				root.getChildren().add(newLine);
			}
			
			int []array93 = {230, 330, 350, 370};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(220, array93[i], 220, array93[i + 1]);
				newLine.setStroke(Color.GREEN);
				newLine.setStrokeWidth(3.0);
				root.getChildren().add(newLine);
			}
			
			int []array94 = {230, 330, 350, 370};
			for (int i = 0; i <= 2; i += 2) {
				newLine = new Line(380, array94[i], 380, array94[i + 1]);
				newLine.setStroke(Color.GREEN);
				newLine.setStrokeWidth(3.0);
				root.getChildren().add(newLine);
			}
			
			
			// Player.
			Circle player = new Circle(10.0f);
			// Set the position of the player.
			player.setCenterX(30);
			player.setCenterY(40);
			// Set the color of the player.
			player.setFill(Color.GREY);
			// Add player to the screen.
			root.getChildren().add(player);

						
			// Change the player's positions when keys are pressed as defined below.
			scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					// Get the current position of the player.
					double positionX = player.getCenterX();
					double positionY = player.getCenterY();
					int dir = -1;
					if (event.getCode() == KeyCode.UP) {
						dir = controlArray[0];
					}
					if (event.getCode() == KeyCode.DOWN) {
						dir = controlArray[1];
					}
					if (event.getCode() == KeyCode.LEFT) {
						dir = controlArray[2];
					}
					if (event.getCode() == KeyCode.RIGHT) {
						dir = controlArray[3];
					}
					
					switch(dir) {
					case 0:
						// Check if the upward move is eligible.
						if(isMovableUp(positionX, positionY) == true) {
						// Move the player up.
						player.setCenterY(player.getCenterY() - 20);
						// Copy the position of player to the pathArray.
						tempp.x = player.getCenterX()/2;
						tempp.y = player.getCenterY()/2;
						tempp.next = new PathTracker();
						tempp = tempp.next;
						// Increment of the counter Variable.
						//.counter += 2;
						
						}
						break;
						
					case 1:
						// Check if the Downward move is eligible.
						if(isMovableDown(positionX, positionY) == true) {
						// Move the player down.
						player.setCenterY(player.getCenterY() + 20);
						// Copy the position of player to the pathArray.
						//.pathArray[counter] = player.getCenterX()/2;
						//.pathArray[counter + 1] = player.getCenterY()/2;
						tempp.x = player.getCenterX()/2;
						tempp.y = player.getCenterY()/2;
						tempp.next = new PathTracker();
						tempp = tempp.next;
						// Increment of the counter Variable.
						//.counter += 2;
						
						}
						break;
					
					case 2:
						// Check if the Left move is eligible.
						if(isMovableLeft(positionX, positionY) == true) {
						// Move the player Left.
						player.setCenterX(player.getCenterX() - 20);
						// Copy the position of player to the pathArray.
						//.pathArray[counter] = player.getCenterX()/2;
						//.pathArray[counter + 1] = player.getCenterY()/2;
						tempp.x = player.getCenterX()/2;
						tempp.y = player.getCenterY()/2;
						tempp.next = new PathTracker();
						tempp = tempp.next;
						// Increment of the counter Variable.
						//.counter += 2;
						
						}
						break;
					
					case 3:	
						// Check if the Right move is eligible.
						if(isMovableRight(positionX, positionY) == true) {
							// Move the player Right.
							player.setCenterX(player.getCenterX() + 20);
							// Copy the position of player to the pathArray.
							//.pathArray[counter] = player.getCenterX()/2;
							//.pathArray[counter + 1] = player.getCenterY()/2;
							tempp.x = player.getCenterX()/2;
							tempp.y = player.getCenterY()/2;
							tempp.next = new PathTracker();
							tempp = tempp.next;
							// Increment of the counter Variable.
							//.counter += 2;
							
						}
						break;
					}
					
					
			// Change the scene to 'Congratulations' scene when player reaches the middle portion of the window.
				   if (player.getCenterX() >= 230 && player.getCenterX() <= 370
						&& player.getCenterY() >= 240 && player.getCenterY() <= 360 ) {
					  // Change to congratsScene.
						primaryStage.setScene(congratsScene);
						timeline.stop();
					}
						
			
				}
			});
			
			//Congratulations Scene After player completes the game.
			
			Text congratsText = new Text();
			congratsText.setText("Congratulations!");
			congratsText.setX(200);
			congratsText.setY(50);
			congratsText.setStyle("-fx-font-size:25");
			congratsText.setFill(Color.ANTIQUEWHITE);
			congratsRoot.getChildren().add(congratsText);

					   
			// Button to go back to the main menu.
			Button goBackToMenuButton = new Button("GO BACK TO MENU");
			goBackToMenuButton.setLayoutX(200);
			goBackToMenuButton.setLayoutY(490);
			goBackToMenuButton.setPrefHeight(30);
			goBackToMenuButton.setPrefWidth(200);
			goBackToMenuButton.setStyle("-fx-font-size:90");
			goBackToMenuButton.setStyle("-fx-font-family:Verdana");
			goBackToMenuButton.setStyle("-fx-background-color:Cyan");
			
			// Change the scene when 'GO BACK TO MENU' is pressed.
			goBackToMenuButton.setOnAction(e -> {
				tempp = new PathTracker();
				beginPath.next = tempp;
				primaryStage.setScene(entryScene);
				// Clear the everything and add back everything except the path.
				congratsRoot.getChildren().clear();
				congratsRoot.getChildren().add(viewPath);
				congratsRoot.getChildren().add(congratsText);
				congratsRoot.getChildren().add(goBackToMenuButton);
			}
			);
			
			congratsRoot.getChildren().add(goBackToMenuButton);

			// Button to view the path of the player.
				       viewPath = new Button("VIEW PATH");
				       viewPath.setLayoutX(200);
				       viewPath.setLayoutY(390);
				       viewPath.setPrefHeight(30);
				       viewPath.setPrefWidth(200);
				       viewPath.setStyle("-fx-font-size:90");
				       viewPath.setStyle("-fx-font-family:Verdana");
				       viewPath.setStyle("-fx-background-color:Cyan");
					   congratsRoot.getChildren().add(viewPath);
					   
					   
					   
		     // Time Run out scene when player runs out of time.
					   Text runOutText = new Text();
					   runOutText.setText("YOU RAN OUT OF TIME !");
					   runOutText.setX(175);
					   runOutText.setY(50);
					   runOutText.setStyle("-fx-font-size:25");
					   runOutText.setFill(Color.ANTIQUEWHITE);
					   timeOutRoot.getChildren().add(runOutText);
		     // Add the 'Go back to menu' button.
					   timeOutRoot.getChildren().add(goBackToMenuButton);
		     
					   
					   
			
		    // when the 'VIEW PATH' button is clicked, the path is showed in the window.
			viewPath.setOnAction(event1 ->{
				PathTracker temp1 = beginPath;
				// Loop through the linked list drawing the path of the player.
				while(temp1.next.x != -1) {
					pathLine = new Line(temp1.x + 230, temp1.y + 70, temp1.next.x + 230, temp1.next.y + 70);
					pathLine.setStroke(Color.RED);
					pathLine.setStrokeWidth(2.0);
					// Add the pathLine to the scene.
					congratsRoot.getChildren().add(pathLine);
					temp1 = temp1.next;
				};	
				
			});
			
			Label zeroLabel = new Label("00:  ");
			zeroLabel.setStyle("-fx-font-size: 4em;");
			zeroLabel.setTextFill(Color.RED);
			zeroLabel.setLayoutX(245);
			zeroLabel.setLayoutY(260);
			root.getChildren().add(zeroLabel);
			
			// Add the countdown timer.
			timerLabel.setText(timeSeconds.toString());
	        timerLabel.setTextFill(Color.RED);
	        timerLabel.setStyle("-fx-font-size: 4em;");
	        timerLabel.setLayoutX(310);
	        timerLabel.setLayoutY(260);
			root.getChildren().add(timerLabel);
			
			// Change the scene when start button is clicked.
			startButton.setOnAction(new EventHandler() {

				@Override
				public void handle(Event event) {
					if (timeline != null) {
			            timeline.stop();
			        }
			        timeSeconds = STARTTIME;
			 
			        // update timerLabel
			        timerLabel.setText(timeSeconds.toString());
			        timeline = new Timeline();
			        timeline.setCycleCount(Timeline.INDEFINITE);
			        timeline.getKeyFrames().add(
			        		// Duration of one second.
			                new KeyFrame(Duration.seconds(1),
			                  new EventHandler() {
			                    // KeyFrame event handler
			                    public void handle(Event event1) {
			                        timeSeconds--;
			                        // update timerLabel
			                        timerLabel.setText(
			                              timeSeconds.toString());
			                        if (timeSeconds <= 0) {
			                            timeline.stop();
			                            primaryStage.setScene(timeOutScene);
			                        }
			                      }
			                }));
			        timeline.playFromStart();
			        
					primaryStage.setScene(scene);
					// Initialize the position of the player when start button is clicked.
					player.setCenterX(30);
					player.setCenterY(40);
					
				}
			});
			
			// Set Title to the Application.
			primaryStage.setTitle("Countdown Maze");
			
			primaryStage.show();

				
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param x x coordinate of the center of the player.
	 * @param y y coordinate of the center of the player
	 * @return true if upward move is possible.
	 * 		   false if upward move is not possible.
	 */
	public boolean isMovableUp(double x, double y) {
		
		// Movement restriction for specific points.
		if ((y == 560) && (x == 110 || x == 510)){
			return true;
		}
		if ((y == 60) && (x == 110 || x == 510)) {
			return true;
		}
		if ((x == 210) && ( y == 80 || y == 540)) {
			return true;
		}
		if ((y == 100) && (x == 170 || x == 410)) {
			return true;
		}
		if ((y == 520) && (x == 170 || x == 410)) {
			return true;
		}
		if ((y == 120 && x == 250) || (y == 500 && x == 470)) {
			return true;
		}
		if ((y == 140 && x == 310) || (y == 480 && x == 310)) {
			return true;
		}
		if ((y == 160 && x == 370) || (y == 460 && x == 370)) {
			return true;
		}
		if ((y == 180 && x == 250) || (y == 440 && x == 250)) {
			return true;
		}
		if ((y == 200 && x == 330) || (y == 420 && x == 330)) {
			return true;
		}
		if ((y == 240 && x == 330) || (y == 380 && x == 330)) {
			return true;
		}
		
		// Up movement on the first layer from the left.
		if (x == 30 && y > 40 && y <= 560) {
			return true;
		}
		// Up movement on the first layer from the right.
		if (x == 570 && y > 40 && y <= 560) {
			return true;
		}
		// Up movement on the second layer from the left.
		if ((x == 50) && y > 60 && y <= 540) {
			return true;
		}
		// Up movement on the second layer from the right.
		if ((x == 550) && y > 60 && y <= 540) {
			return true;
		}
		// Up movement on the third layer from the left.
		if (x == 70 && y > 80 && y <= 520) {
			return true;
		}
		// Up movement on the third layer from the right.
		if (x == 530 && y > 80 && y <= 520) {
			return true;
		}
		// Up movement on the fourth layer from the left.
		if (x == 90 && y > 100 && y <=  500) {
			return true;
		}
		// Up movement on the fourth layer from the right.
		if (x == 510 && y > 100 && y <= 500) {
			return true;
		}
		// Up movement on the fifth layer from the left.
		if (x == 110 && y > 120 && y <= 480) {
			return true;
		}
		// Up movement on the fifth layer from the right.
		if (x == 490 && y > 120 && y <= 480) {
			return true;
		}
		// Up movement on the sixth layer from the left.
		if ( x == 130 && y > 140 && y <= 460) {
			return true;
		}
		// Up movement on the sixth layer from the right.
		if (x == 470 && y > 140 && y <= 460) {
			return true;
		}
		// Up movement on the seventh layer from the left.
		if (x == 150 && y > 160 && y <= 440) {
			return true;
		}
		// Up movement on the seventh layer from the right.
		if (x == 450 && y > 160 && y <= 440) {
			return true;
		}
		// Up movement on the eighth layer from the left.
		if (x == 170 && y > 180 && y <= 420) {
			return true;
		}
		// Up movement on the eighth layer from the right.
		if (x == 430 && y > 180 && y <= 420) {
			return true;
		}
		// Up movement on the ninth layer from the left.
		if (x == 190 && y > 200 && y <= 400) {
			return true;
		}
		// Up movement on the ninth layer from the right.
		if (x == 410 && y > 200 && y <= 400) {
			return true;
		}
		// Up movement on the tenth layer from the left.
		if (x == 210 && y > 220 && y <= 380) {
			return true;
		}
		// Up movement on the tenth layer from the right.
		if (x == 390 && y > 220 && y <= 380) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param x x coordinate of the center of the player.
	 * @param y y coordinate of the center of the player
	 * @return true if Downward move is possible.
	 * 		   false if Downward move is not possible.
	 */
	public boolean isMovableDown(double x, double y) {
		
		// Movement restrictions for specific points.
		if ((y == 40) && (x == 110 || x == 510)){
			return true;
		}
		if ((y == 540) && (x == 110 || x == 510)) {
			return true;
		}
		if ((x == 210) && (y == 60 || y == 520)) {
			return true;
		}
		if ((y == 80) && (x == 170 || x == 410)) {
			return true;
		}
		if ((y == 500) && (x == 170 || x == 410)) {
			return true;
		}
		if ((y == 100 && x == 250) || (y == 480 && x == 470)){
			return true;
		}
		if ((y == 120 && x == 310) || (y == 460 && x == 310)) {
			return true;
		}
		if ((y == 440 && x == 370) || (y == 140 && x == 370)){
			return true;
		}
		if ((y == 160 && x == 250) || (y == 420 && x == 250)) {
			return true;
		}
		if ((y == 180 && x == 330) || (y == 400 && x == 330)) {
			return true;
		}
		if ((y == 220 && x == 330) || (y == 360 && x == 330)) {
			return true;
		}
		
		// Down movement on the first layer from the left.
		if (x == 30 && y >= 40 && y < 560){
			return true;
		}
		// Down movement on the first layer from the right.
		if (x == 570 && y >= 40 && y < 560) {
			return true;
		}
		// Down movement on the second layer from the left.
		if (x == 50 && y >= 60 && y < 540) {
			return true;
		}
		// Down movement on the second layer from the right.
		if (x == 550 && y >= 60 && y < 540) {
			return true;
		}
		// Down movement on the third layer from the left.
		if (x == 70 && y >= 80 && y < 520) {
			return true;
		}
		// Down movement on the third layer from the right.
		if (x == 530 && y >= 80 && y < 520) {
			return true;
		}
		// Down movement on the fourth layer from the left.
		if (x == 90 && y >= 100 && y < 500) {
			return true;
		}
		// Down movement on the fourth layer from the right.
		if (x == 510 && y >= 100 && y < 500) {
			return true;
		}
		// Down movement on the fifth layer from the left.
		if (x == 110 && y >= 120 && y < 480) {
			return true;
		}
		// Down movement on the fifth layer from the right.
		if (x == 490 && y >= 120 && y < 480) {
			return true;
		}
		// Down movement on the sixth layer from the left.
		if (x == 130 && y >= 140 && y < 460) {
			return true;
		}
		// Down movement on the sixth layer from the right.
		if (x == 470 && y >= 140 && y < 460) {
			return true;
		}
		// Down movement on the seventh layer from the left.
		if (x == 150 && y >= 160 && y < 440) {
			return true;
		}
		// Down movement on the seventh layer from the right.
		if (x == 450 && y >= 160 && y < 440) {
			return true;
		}
		// Down movement on the eighth layer from the left.
		if (x == 170 && y >= 180 && y < 420) {
			return true;
		}
		// Down movement on the eighth layer from the right.
		if (x == 430 && y >= 180 && y < 420) {
			return true;
		}
		// Down movement on the ninth layer from the left.
		if (x == 190 && y >= 200 && y < 400) {
			return true;
		}
		// Down movement on the ninth layer from the right.
		if (x == 410 && y >= 200 && y < 400) {
			return true;
		}
		// Down movement on the tenth layer from the left.
		if (x == 210 && y >= 220 && y < 380) {
			return true;
		}
		// Down movement on the tenth layer from the right.
		if (x == 390 && y >= 220 && y < 380) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param x x coordinate of the center of the player.
	 * @param y y coordinate of the center of the player
	 * @return true if Left move is possible.
	 * 		   false if Left move is not possible.
	 */
	public boolean isMovableLeft(double x, double y) {
			
		// Movement restrictions for specific points.
		if ((x == 570) && (y == 120 || y == 500)) {
			return true;
		}
		
		if ((x == 50) && (y == 120 || y == 500)) {
			return true;
		}
		if ((y == 220) && (x == 70 || x == 550)) {
			return true;
		}
		if ((y == 260) && (x == 90 || x == 530)){
			return true;
		}
		if ((y == 420) && (x == 90 || x == 530)) {
			return true;
		}
		if ((y == 320 && x == 130) || (y == 320 && x == 490)) {
			return true;
		}
		if ((y == 220 && x == 150) || (y == 220 && x == 470)) {
			return true;
		}
		if ((y == 380 && x == 170) || (y == 380 && x == 450)) {
			return true;
		}
		if ((y == 320 && x == 190) || (y == 320 && x == 430)) {
			return true;
		}
		if ((y == 240 && x == 410) || (y == 240 && x == 210)){
			return true;
		}
		if ((y == 340 && x == 230) || (y == 340 && x == 390)) {
			return true;
		}
		
		// Left movement on the first layer from the top.
		if (y == 40 && x > 30 && x <= 570 ) {
			return true;
		}
		// Left movement on the first layer from the bottom.
		if (y == 560 && x > 30 && x <= 570) {
			return true;
		}
		// Left movement on the second layer from the top.
		if ((y == 60) && (x > 50 && x <= 550)) {
			return true;
		}
		// Left movement on the second layer from the botom.
		if ((y == 540) && (x > 50 && x <= 550)) {
			return true;
		}
		// Left movement on the third layer from the top.
		if((y == 80 ) && x > 70 && x <= 530) {
			return true;
		}
		// Left movement on the third layer from the bottom.
		if (y == 520 && x > 70 && x <= 530) {
			return true;
		}
		// Left movement on the fourth layer from the top.
		if (y == 100 && x > 90 && x <= 510) {
			return true;
		}
		// Left movement on the fourth layer from the bottom.
		if (y == 500 && x > 90 && x <= 510) {
			return true;
		}
		// Left movement on the fifth layer from the top.
		if (y == 120 && x > 110 && x <= 490) {
			return true;
		}
		// Left movement on the fifth layer from the bottom.
		if (y == 480 && x > 110 && x <= 490) {
			return true;
		}
		// Left movement on the sixth layer from the top.
		if (y == 140 && x > 130 && x <= 470) {
			return true;
		}
		// Left movement on the sixth layer from the bottom.
		if (y == 460 && x > 130 && x <= 470) {
			return true;
		}
		// Left movement on the seventh layer from the top.
		if (y == 160 && x > 150 && x <= 450) {
			return true;
		}
		// Left movement on the seventh layer from the bottom.
		if (y == 440 && x > 150 && x <= 450) {
			return true;
		}
		// Left movement on the eighth layer from the top.
		if (y == 180 && x > 170 && x <= 430) {
			return true;
		}
		// Left movement on the eighth layer from the bottom.
		if (y == 420 && x > 170 && x <= 430) {
			return true;
		}
		// Left movement on the ninth layer from the top.
		if (y == 200 && x > 190 && x <= 410) {
			return true;
		}
		// Left movement on the ninth layer from the bottom.
		if (y == 400 && x > 190 && x <= 410) {
			return true;
		}
		// Left movement on the tenth layer from the top.
		if (y == 220 && x > 210 && x <= 390) {
			return true;
		}
		// Left movement on the tenth layer from the bottom.
		if (y == 380 && x > 210 && x <= 390) {
			return true;
		}
		return false;
		}
	
	/**
	 * 
	 * @param x x coordinate of the center of the player.
	 * @param y y coordinate of the center of the player
	 * @return true if Right move is possible.
	 * 		   false if Right move is not possible.
	 */
	public boolean isMovableRight(double x, double y) {
		
		// Movement restrictions for specific points.
		if ((x == 30) && (y == 120 || y == 500)) {
			return true;
		}
		if ((x == 550) && (y == 120 || y == 500)) {
			return true;
		}
		if ((y == 220) && (x == 50 || x == 530)) {
			return true;
		}
		if ((y == 260) && (x == 70 || x == 510)) {
			return true;
		}
		if ((y == 420) && (x == 70 || x == 510)) {
			return true;
		}
		if ((y == 320 && x == 110) || (y == 320 && x == 470)) {
			return true;
		}
		if ((y == 220 && x == 130) || (y == 220 && x == 450)) {
			return true;
		}
		if ((y == 380 && x == 150) || (y == 380 && x == 430)) {
			return true;
		}
		if ((y == 320 && x == 170) || (y == 320 && x == 410)) {
			return true;
		}
		if ((y == 240 && x == 390) || (y == 240 && x == 190)) {
			return true;
		}
		if ((y == 340 && x == 210) || (y == 340 && x == 370)) {
			return true;
		}
		
		// Right movement on the first layer from the top
		if (y == 40 && x >= 30 && x < 570 ) {
			return true;
		}
		// Right movement on the first layer from the bottom.
		if (y == 560 && x >= 30 && x < 570) {
			return true;
		}
		//Right movement on the second layer from the top.
		if ((y == 60) && (x >= 50  && x < 550)) {
			return true;
		}
		//Right movement on the second layer from the bottom.
		if ((y == 540 && (x >= 50 && x < 550))) {
			return true;
		}
		// Right movement on the third layer from the top.
		if ((y == 80) && ( x >= 70 && x < 530)) {
			return true;
		}
		// Right movement on the third layer from the bottom.
		if (y == 520 && x >= 70 && x < 530) {
			return true;
		}
		// Right movement on the fourth layer from the top.
		if (y == 100 && x >= 90 && x < 510) {
			return true;
		}
		// Right movement on the fourth layer from the bottom.
		if (y == 500 && x >= 90 && x < 510) {
			return true;
		}
		// Right movement on the fifth layer from the top.
		if (y == 120 && x >= 110 && x < 490) {
			return true;
		}
		// Right movement on the fifth layer from the bottom.
		if (y == 480 && x >= 110 && x < 490) {
			return true;
		}
		// Right movement on the sixth layer from the top.
		if (y == 140 && x >= 130 && x < 470) {
			return true;
		}
		// Right movement on the sixth layer from the bottom.
		if (y == 460 && x >= 130 && x < 470) {
			return true;
		}
		// Right movement on the seventh layer from the top.
		if (y == 160 && x >= 150 && x < 450) {
			return true;
		}
		// Right movement on the seventh layer from the bottom.
		if (y == 440 && x >= 150 && x < 450) {
			return true;
		}
		// Right movement on the eighth layer from the top.
		if (y == 180 && x >= 170 && x < 430) {
			return true;
		}
		// Right movement on the eighth layer from the bottom.
		if (y == 420 && x >= 170 && x < 430) {
			return true;
		}
		// Right movement on the ninth layer from the top.
		if (y == 200 && x >= 190 && x < 410) {
			return true;
		}
		// Right movement on the ninth layer from the bottom.
		if (y == 400 && x >= 190 && x < 410) {
			return true;
		}
		// Right movement on the tenth layer from the top.
		if (y == 220 && x >= 210 && x < 390) {
			return true;
		}
		// Right movement on the tenth layer from the bottom.
		if (y == 380 && x >= 210 && x < 390) {
			return true;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}