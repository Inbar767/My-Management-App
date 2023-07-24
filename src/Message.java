import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Message extends Application {
	
	public void start(Stage stage) throws Exception{ 
		// Loads CalendarController.fxml and configures the CalendarController
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("Message.fxml")); 
		Scene scene = new Scene(root); // Attach scene graph to scene
		stage.setTitle("Message"); // Displayed in window's title bar
		stage.setScene(scene); // Attach scene to stage
		stage.show(); // Display the stage */
	} 
	
	//Main method
	public static void main(String[] args) { 
		launch(args); // Create a CalendarController object and call its start method
	} 
}
