import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void logInPressed(ActionEvent event) {
    	String pass = password.getText();
    	String user = username.getText();
		if((user == null || user.length() == 0) || (pass == null || pass.length() == 0))
    	{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("One of your login information is missing!");
			alert.show();
    	}
		else
		{
			User enteredUser = DBUtils.getUser(user, pass);
    		if(enteredUser != null)
    		{
				FXMLLoader loader = SceneControl.changeScene(event, "CalendarController.fxml", new CalendarController(), true);
				CalendarController calendarController = loader.getController();
				calendarController.initialize(enteredUser);    		
			}
    		else
    		{
    			System.out.println("User Not Found In The Database !");
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setContentText("Provided Credentials are incorrect!");
    			alert.show();
    		}
    	}
    }

    @FXML
    void signUpPressed(ActionEvent event) {
		SceneControl.changeScene(event, "SignUpController.fxml", new SignUpController(), true);

    }
}