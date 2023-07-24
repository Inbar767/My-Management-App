import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class TaskController {
    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private Label monthString;

    @FXML
    private CheckBox reminder;
    
    @FXML
    private Spinner<Integer> hour;

    @FXML
    private Spinner<Integer> minute;

    @FXML
    private TextField time; 
    private User mainUser;
    private ObservableList<Task> tasksList;
    private int taskIndex;

    @FXML
    void addTask(ActionEvent event) {
    	String newDescription = description.getText();
    	if(newDescription != null && newDescription.length() > 0 && date.getValue() != null)
    	{
    		Task oldTask = tasksList.get(taskIndex);
    		Date newDate = java.util.Date.from(date.getValue().atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant());
    		Task newTask = new Task(newDate, hour.getValue(), minute.getValue(), newDescription);
       		if(!(newDate.after(oldTask.getDate())) && !(oldTask.getDate().after(newDate)))
        	{
       			tasksList.add(newTask);
    		} 
       		if(oldTask.getTaskColumn() != "")
    		{
        		DBUtils.removeTask(mainUser.getUserName(), tasksList.get(taskIndex));
        		tasksList.remove(taskIndex);
        	}
       		DBUtils.insertTask(mainUser.getUserName(), newTask);
       		if(reminder.isSelected())
       		{
       			EmailSender.send(mainUser.getEmail(), mainUser.getUserName(), newTask);
       		}
    		SceneControl.backToLastScene(event);
    	}
    	else
    	{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("You have to fill information!");
			alert.show();	
    	}
    }
    
    public void initialize(User mainUser, ObservableList<Task> tasksList, int taskIndex)
    {
    	this.mainUser = mainUser;
    	this.tasksList = tasksList;
    	this.taskIndex = taskIndex;
    	loadInformation();
    }
    
    private void loadInformation()
    {
    	System.out.println(taskIndex);
    	Task task = tasksList.get(taskIndex);
    	if(task.getTaskColumn() != "")
    	{
    		description.setText(task.getTaskColumn());
    	}
    	LocalDate localDate = task.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	this.date.setValue(localDate);
    	SpinnerValueFactory<Integer> hourValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23);
    	SpinnerValueFactory<Integer> minuteValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
    	hourValue.setValue(task.getHour());
    	minuteValue.setValue(task.getMinute());
    	hour.setValueFactory(hourValue);
    	minute.setValueFactory(minuteValue);
    }
}