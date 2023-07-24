import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskManage extends Application{ 
	
	public void start(Stage stage) throws Exception{ 
		// Loads CalendarController.fxml and configures the CalendarController
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("SignInController.fxml")); 
		SceneControl.firstScene(stage, root);
	} 
	
	//Main method
	public static void main(String[] args) { 
		launch(args); // Create a CalendarController object and call its start method
	} 
}
