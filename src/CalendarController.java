import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.*;
import javafx.event.EventHandler;

public class CalendarController {
	private final static int MAX_WEEK = 6;
    private final static int MAX_DAY = 7;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label month;
    @FXML
    private TableView <Task> tasksTable;
    
    @FXML
    private TableColumn<Task, String> taskColumn;
    
    @FXML
    private TableColumn<Task, String> timeColumn;

    @FXML
    private Label year;
    private User mainUser;
	private Calendar date;  
	private Button[] buttons; //Array of buttons for days of month
	private Button selectedButton;
	
	  //initialize method - when program starts running it draws Calendar of current month
    public void initialize(User mainUser)
    {
    	this.mainUser = mainUser;
    	taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskColumn"));
    	timeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("timeColumn"));
    	date = Calendar.getInstance();
    	date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);
    	selectedButton = null;
    	setDaysButton();
    }

    @FXML
    void logOffPressed(ActionEvent event) {
    	SceneControl.changeScene(event, "SignInController.fxml", new SignInController(), true);
    }

    @FXML
    void monthDown(ActionEvent event) {
    	if(date.get(Calendar.MONTH) == 0)
    	{
    		date.set(date.get(Calendar.YEAR) - 1 , 11, 1);
    	}
    	else
    	{
    		date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 1);
    	}
    	setDaysButton();
    }

    @FXML
    void monthUp(ActionEvent event) {
    	if(date.get(Calendar.MONTH) == 11)
    	{
    		date.set(date.get(Calendar.YEAR) + 1, 0, 1);
    	}
    	else
    	{
    		date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 1);
    	}
    	setDaysButton();
    }

    @FXML
    void removeTask(ActionEvent event) {
    	Task selectedTask = tasksTable.getSelectionModel().getSelectedItem();
    	int selectedIndex = tasksTable.getSelectionModel().getSelectedIndex();
    	if(selectedTask != null)
    	{
    		tasksTable.getItems().remove(selectedIndex);
    		DBUtils.removeTask(mainUser.getUserName(), selectedTask);
    	}
    }
    
    @FXML
    void addTask(ActionEvent event) {
    	tasksTable.getItems().add(new Task(getSelectedDate()));
    	callTaskController(event, tasksTable.getItems().size() - 1);
    }

    @FXML
    void updateTask(ActionEvent event) {
    	Task selectedTask = tasksTable.getSelectionModel().getSelectedItem();
    	if(selectedTask != null)
    	{
        	callTaskController(event, tasksTable.getItems().indexOf(selectedTask));
    	}
    }
    
    private void callTaskController(ActionEvent event, int taskIndex)
    {
    	FXMLLoader loader = SceneControl.changeScene(event, "TaskController.fxml", new TaskController(), false);
    	TaskController taskController = loader.getController();
    	taskController.initialize(mainUser, tasksTable.getItems(),taskIndex);
    }
    
    private void selectDay(ActionEvent e)
    {
    	Button selectedButton = (Button) e.getSource();
    	if(selectedButton.getText() != null)
    	{
    		showDay(selectedButton);
    	}
    }
    
    private void showDay(Button newDate)
    {
    	if(selectedButton != null)
    	{
    		selectedButton.setStyle("-fx-background-color: #ffffff; ");
    	}
    	selectedButton = newDate;
    	selectedButton.setStyle("-fx-background-color: #C9C0BB; ");
    	Date dayDate = getSelectedDate();
    	if(dayDate != null)
    	{
    		
    		List <Task> returnedList = DBUtils.getTasks(mainUser.getUserName(), getSelectedDate());
    		ObservableList <Task> tasksList = FXCollections.observableArrayList(returnedList);
    		Task birthdayTask = addBirthday();
    		if(birthdayTask != null)
    		{
    			tasksList.add(birthdayTask);
    		}
    		tasksList.sort(Comparator.comparing(Task::getTimeColumn));
    		tasksTable.setItems(tasksList);
    	}
    }
    
    private Task addBirthday()
    {
    	LocalDate birthdayLocal = mainUser.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	int birthdayDay = birthdayLocal.getDayOfMonth();
    	if(birthdayLocal.getMonthValue() == (date.get(Calendar.MONTH) + 1) &&
    			birthdayDay == Integer.parseInt(selectedButton.getText()))
    	{
    		return new Task(getSelectedDate(), "00:00", "Happy Birthday " + mainUser.getUserName() + "!");
    	}
    	return null;
    }
    
    private Date getSelectedDate()
    {
    	int year = Integer.parseInt(this.year.getText());
    	String buttonString = selectedButton.getText();
    	if(buttonString.length() > 2)
    	{
    		buttonString = buttonString.substring(0, buttonString.indexOf(2));
    	}
    	int day = Integer.parseInt(buttonString);
    	return new GregorianCalendar(year, date.get(Calendar.MONTH), day).getTime();
    }
    
    private void setDaysButton()
    {
    	int maxDay = date.getActualMaximum(Calendar.DATE);
    	buttons = new Button[maxDay];
    	gridPane.getChildren().clear();
    	month.setText(date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
    	year.setText(String.valueOf(date.get(Calendar.YEAR))); 
    	int gridStartIndex = date.get(Calendar.DAY_OF_WEEK) - 1;
    	for(int i = 0; i < buttons.length; i++)
    	{
    		buttons[i] = new Button();
    		buttons[i].setPrefSize(gridPane.getPrefWidth() / MAX_DAY,
    				gridPane.getPrefHeight() / MAX_WEEK);
    		buttons[i].setStyle("-fx-background-color: #ffffff; ");
    		buttons[i].setText(String.valueOf(i + 1));
    		buttons[i].setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent e) {
    				selectDay(e);
    				}});
    		gridPane.add(buttons[i], (i + gridStartIndex) % MAX_DAY, (i + gridStartIndex)/ (MAX_WEEK + 1));
    	}
    	Calendar cal = Calendar.getInstance();
    	int currentDay = cal.get(Calendar.DAY_OF_MONTH);
    	if(currentDay > maxDay)
    	{
    		currentDay = maxDay;
    	}
    	showDay(buttons [currentDay - 1]);

    }
}