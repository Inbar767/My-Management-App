import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneControl {

	public static void firstScene(Stage stage, Parent root)
	{ 
		Scene scene = new Scene(root); // Attach scene graph to scene
		stage.setTitle("My Management Application"); // Displayed in window's title bar
		stage.setScene(scene); // Attach scene to stage
		stage.show(); // Display the stage */
	} 
	
	public static FXMLLoader changeScene(ActionEvent event, String fxmlFile, Object fileClass, boolean isReplacing)
	{			
		Stage app_stage;
		FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneControl.class.getResource(fxmlFile)));
		try {
		    Parent pane = loader.load();
			if(!isReplacing)
			{
				app_stage = new Stage();
				app_stage.setScene(new Scene(pane));        
				app_stage.show();
			}
			else
			{
				app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();			
				app_stage.getScene().setRoot(pane);
				app_stage.getScene().getWindow().sizeToScene();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return loader;
	}
	
	public static void backToLastScene(ActionEvent event) {
	     Node source = (Node) event.getSource();
	    final Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();  
	}
}
