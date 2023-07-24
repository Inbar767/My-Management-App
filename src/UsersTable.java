import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.DatePicker;

public class UsersTable {
	public static boolean isUsernameExist(String username)
	{
		return isUserExist(username, null);
	}
	
	public static boolean isUserExist(String username, String password)
	{
		String sql = "SELECT * FROM users WHERE username = ?";
		List <Object> parameterList = new ArrayList<>();
		parameterList.add(username);
		if(password != null)
		{
			parameterList.add(password);
		}	
		ResultSet result= getFromTable(parameterList, sql);
		try {
			if(result.isBeforeFirst())
			{
				return true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if(result != null)
			{
				try {
					result.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static java.util.Date getBirthday(String username)
	{
		String sql = "SELECT birthday FROM users WHERE username = ? ";
		List <Object> parameterList = new ArrayList<>();
		parameterList.add(username);
		ResultSet result= getFromTable(parameterList, sql);
		try {
			if (result.next()) {
			    Date date = result.getDate(1);
			    return new java.util.Date(date.getTime());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if(result != null)
			{
				try {
					result.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String getEmail(String username)
	{
		String sql = "SELECT email FROM users WHERE username = ? ";
		List <Object> parameterList = new ArrayList<>();
		parameterList.add(username);
		ResultSet result= getFromTable(parameterList, sql);
		try {
			if (result.next()) {
			    String email = result.getString(1);
			    return email;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if(result != null)
			{
				try {
					result.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void insertTask(String username, Task task)
	{
		String sql = "INSERT INTO tasks (username, date, time, task) VALUES(?,?,?,?)";
		List <Object> parameterList = new ArrayList<>();
		parameterList.add(username);
		parameterList.add(new java.sql.Date(task.getDate().getTime()));
		parameterList.add(task.getTimeColumn());
		parameterList.add(task.getTaskColumn());
		insertToTable(parameterList, sql);
	}
	
	public static void insertUser(String username, String password, String email, DatePicker birthday)
	{
		String sql = "INSERT INTO users (username, password, birthday, email) VALUES(?,?,?,?)";
		List <Object> parameterList = new ArrayList<>();
		parameterList.add(username);
		parameterList.add(password);
		parameterList.add(convertDateSQL(birthday));
		parameterList.add(email);
		insertToTable(parameterList, sql);
	}
	
	private static void insertToTable(List<Object> parameterList, String sql)
	{
		Connection connection = null;
		PreparedStatement psInsert = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
			psInsert = connection.prepareStatement(sql);
			for(int i = 0; i < parameterList.size(); i++)
			{
				if(parameterList.get(i) instanceof String)
				{
					psInsert.setString(i+1,(String) parameterList.get(i));
				}
				else if(parameterList.get(i) instanceof Date)
				{
					psInsert.setDate(i+1,(Date) parameterList.get(i));
				}
			}
			psInsert.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(psInsert != null)
			{
				try {
					psInsert.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private static ResultSet getFromTable(List<Object> parameterList, String sql)
	{
		Connection connection = null;
		PreparedStatement psGetExists = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
			psGetExists = connection.prepareStatement(sql);
			for(int i = 0; i < parameterList.size(); i++)
			{
				if(parameterList.get(i) instanceof String)
				{
					psGetExists.setString(i+1,(String) parameterList.get(i));
				}
				else if(parameterList.get(i) instanceof Date)
				{
					psGetExists.setDate(i+1,(Date) parameterList.get(i));
				}
			}
			result = psGetExists.executeQuery();
			return result;
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(psGetExists != null)
			{
				try {
					psGetExists.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static List<Task> getTasks(String username, java.util.Date date)
	{
		Connection connection = null;
		PreparedStatement psCheckTasksExists = null;
		ResultSet result = null;
		List <Task> tasksList = new ArrayList<>();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
			psCheckTasksExists = connection.prepareStatement("SELECT * FROM tasks WHERE username = ? and date = ?");
			psCheckTasksExists.setString(1, username);
			psCheckTasksExists.setDate(2, sqlDate);
			result = psCheckTasksExists.executeQuery();
			while(result.next())
			{
				String time = result.getString("time");
				String task = result.getString("task");
				tasksList.add(new Task(date, time, task));
			}
			return tasksList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(result != null)
			{
				try {
					result.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(psCheckTasksExists != null)
			{
				try {
					psCheckTasksExists.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void removeTask(String username, Task task)
	{
		Connection connection = null;
		PreparedStatement psCheckTasksExists = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks_manage", "root", "Inbar2023");
			psCheckTasksExists = connection.prepareStatement("DELETE FROM tasks WHERE username = ? and date = ? and time = ? and task = ? ");
			psCheckTasksExists.setString(1, username);
			psCheckTasksExists.setDate(2, new java.sql.Date(task.getDate().getTime()));
			psCheckTasksExists.setString(3, task.getTimeColumn());
			psCheckTasksExists.setString(4, task.getTaskColumn());
			psCheckTasksExists.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(psCheckTasksExists != null)
			{
				try {
					psCheckTasksExists.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private static Date convertDateSQL(DatePicker oldDate)
	{
		return Date.valueOf(oldDate.getValue());	
	}
}
