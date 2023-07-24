import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	public static boolean isUsernameExist(String username)
	{
		if(getUser(username, null) != null)
		{
			return true;
		}
		return false;
	}
	
	public static User getUser(String username, String password)
	{
		String sql;
		if(password == null)
		{
			sql = "SELECT * FROM users WHERE username = '" + username+ "';";
		}
		else
		{
			sql = "SELECT * FROM users WHERE username = '" + username + "' and password = '" + password
					+ "';";
		}
		List<Map<String, Object>> resultsList = getFromTable(sql);
		if(resultsList!= null && resultsList.size() > 0)
		{
			for (Map<String, Object> rows : resultsList) {
				java.sql.Date birthday = (java.sql.Date)rows.get("birthday");
				String email = (String)rows.get("email");
				return new User(username, password, new java.util.Date(birthday.getTime()), email);
			}
		}
		return null;
	}
	
	public static List<Task> getTasks(String username, java.util.Date date)
	{
		String sql = "SELECT * FROM tasks WHERE username = '" + username+ "' and date = '" +
				(new java.sql.Date(date.getTime())) +"';";
		List<Map<String, Object>> resultsList = getFromTable(sql);
		List<Task> tasksList = new ArrayList<>();
		if(resultsList!= null && resultsList.size() > 0)
		{
			
			for (Map<String, Object> rows : resultsList) {
				String taskTime = (String)rows.get("time");
				String taskDescription = (String)rows.get("task");
				tasksList.add(new Task(date, taskTime, taskDescription));
			}
		}
		return tasksList;
	}
	
	public static void removeTask(String username, Task task)
	{
		String sql = "DELETE FROM tasks WHERE username = '" + username + 
				"' and date = '" + (new java.sql.Date(task.getDate().getTime())) + "' and time = '"
				+  task.getTimeColumn() + "' and task = '" + task.getTaskColumn() + "';";
		changeTable(sql);
	}
	
	public static void insertTask(String username, Task task)
	{
		String sql = "INSERT INTO tasks (username, date, time, task) VALUES('" + username + "','"
				+ (new java.sql.Date(task.getDate().getTime())) + "','" + task.getTimeColumn() + "','" + 
				task.getTaskColumn() + "');";
		changeTable(sql);
	}
	
	public static void insertUser(String username, String password, String email, java.util.Date birthday)
	{
		String sql = "INSERT INTO users (username, password, birthday, email) VALUES('"+ username + "','"
				+ password + "','" + (new java.sql.Date(birthday.getTime())) + "','" + email + "');";
		changeTable(sql);
	}
	
	private static void changeTable(String sql)
	{
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(connection != null)
			{
				try {
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private static List<Map<String, Object>> getFromTable(String query) {
	    try (
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);
	    ) {
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        String[] name = new String[columnCount];
	        for (int i = 0; i < columnCount; i++)
	            name[i] = metaData.getColumnLabel(i + 1);
	        List<Map<String, Object>> rows = new ArrayList<>();
	        while (resultSet.next()) {
	            Map<String, Object> row = new LinkedHashMap<>();
	            for (int i = 0; i < columnCount; i++)
	                row.put(name[i], resultSet.getObject(i + 1));
	            rows.add(row);
	        }
	        return rows;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
}