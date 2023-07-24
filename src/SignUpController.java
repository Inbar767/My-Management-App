import java.time.ZoneId;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void logInPressed(ActionEvent event) {
    	String user = username.getText();
		String pass = password.getText();
		String mail = email.getText();
		if((user == null || user.length() == 0) || (pass == null || pass.length() == 0) ||
				(mail == null || mail.length() == 0) || birthday.getValue() == null)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("One of your logup information is missing!");
			alert.show();
		}
		else {
			if(DBUtils.isUsernameExist(user))
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Provided username is already taken!");
				alert.show();				
			}
			else
			{
	    		Date convertedBirthday = java.util.Date.from(birthday.getValue().atStartOfDay()
	                    .atZone(ZoneId.systemDefault()).toInstant());
				DBUtils.insertUser(user, pass, mail, convertedBirthday);
				User newUser = new User(user, pass, convertedBirthday, mail);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Welcome " + user + "!");
				alert.show();
				FXMLLoader loader = SceneControl.changeScene(event, "CalendarController.fxml", new CalendarController(), true);
				CalendarController calendarController = loader.getController();
				calendarController.initialize(newUser);
			}
		}
    }

    @FXML
    void backSignIn(ActionEvent event) {
    	SceneControl.changeScene(event, "SignInController.fxml", new SignInController(), true);
    }
}