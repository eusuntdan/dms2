package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class PrintKFC extends Application {
	
public static void printAlbums(GridPane gridPane) {
		
		JSONParser parser = new JSONParser();
		
	try(FileReader reader = new FileReader("albums.json"))
	{
		// read
		Object obj = parser.parse(reader);
		JSONArray albumList = (JSONArray) obj;
		
		// create a table
		Label nameLabel = new Label("Name");
        gridPane.add(nameLabel, 0, 1);
        Label artistLabel = new Label("Artist");
        gridPane.add(artistLabel, 1, 1);
        Label priceLabel = new Label("Price");
        gridPane.add(priceLabel, 2, 1);
		// iterate
		albumList.forEach(album -> parseUserObject((JSONObject)album, (GridPane) gridPane));
	}	
			
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	}
	
	static String testUser = "anca";
	
	static boolean foundUser = false;
	
	static int i = 2;
	
	private static void parseUserObject(JSONObject album, GridPane gridPane)
	{
		JSONObject userObj = (JSONObject) album.get("album");
		/// get username, password, user type
		String readName = (String) userObj.get("name");
		String readArtist = (String) userObj.get("artist");
		String readPrice = (String) userObj.get("price");
		{
			if(testUser.equals(readArtist) == true)
			{
				foundUser = true;
				Label nameLabel = new Label(readName);
            	gridPane.add(nameLabel, 0, i);
            	Label artistLabel = new Label(readArtist);
            	gridPane.add(artistLabel, 1, i);
            	Label priceLabel = new Label(readPrice);
            	gridPane.add(priceLabel, 2, i);
            	i++;
			}
			return;
		}
		
			
		//System.out.println(readUser + readPass + readType);
	}
	
	public static String artistName = "anca";
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Danca Music Shop");

        // Create the form grid pane
        GridPane gridPane = createFormPane();
        // Add UI controls to the form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("List of albums for " + artistName);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        
        
        // Add Submit Button
        Button submitButton = new Button("See songs");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 1, 1, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {                
                
            	submitButton.setVisible(false);
                printAlbums(gridPane);
         	
                showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Succes!", "Here are the albums!");
                
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}