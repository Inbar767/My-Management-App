import java.util.Date;

public class Task implements Comparable<Task>{
	private final static char HOUR_MINUTE_SEPERATE = ':';
	private final static int HOUR_MINUTE_SEPERATE_POSITION = 2;
	private final static int TIME_LENGTH = 5;
	
	String timeColumn;
	String taskColumn;
	Date date;
	
	public Task(Date date)
	{
		this(date, "00:00", "");
	}
	
	public Task(Date date, String time, String taskDescription)
	{
		this.date = date;
		if(timeCheck(time))
		{
			this.timeColumn = time;
		}
		else
		{
			this.timeColumn = "00:00";
		}
		this.taskColumn = taskDescription;
	}
	
	public Task(Date date, int hour, int minute, String taskDescription)
	{
		this(date, hour + ":" + minute, taskDescription);
		timeColumn = hour + ":" + minute;
		if(hour < 10)
			timeColumn = '0' + timeColumn;
		if(minute < 10)
			timeColumn += '0';
	}
	
	public Task(Task task)
	{
		this(task.date, task.timeColumn, task.taskColumn);
	}
	
	public int getHour()
	{
		return Integer.parseInt(timeColumn.substring(0, timeColumn.indexOf(HOUR_MINUTE_SEPERATE)));
	}
	
	public int getMinute()
	{
		return Integer.parseInt(timeColumn.substring(timeColumn.indexOf(HOUR_MINUTE_SEPERATE) + 1,
				timeColumn.length()));
	}
	
	public String getTimeColumn()
	{
		return timeColumn;
	}
	
	public String getTaskColumn()
	{
		return taskColumn;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public void setTaskColumn(String taskColumn)
	{
		this.taskColumn = taskColumn;
	}
	
	public void setTimeColumn(String timeColumn)
	{
		if(timeCheck(timeColumn))
		{
			this.timeColumn = timeColumn;
		}
		else
		{
			this.timeColumn = "00:00";
		}
	}

	@Override
	public int compareTo(Task another) {
		return this.timeColumn.compareTo(taskColumn);
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Task)
		{
			Task task = (Task)o;
			if(task.timeColumn.equals(timeColumn) && task.taskColumn.equals(taskColumn)
					&& (!(task.date.after(date)) && !(date.after(task.date))))
				return true;
		}
		return false;
	}
	
	private boolean timeCheck(String timeToCheck)
	{
		if(timeToCheck == null || timeToCheck.length() != TIME_LENGTH || timeToCheck.charAt(HOUR_MINUTE_SEPERATE_POSITION) != HOUR_MINUTE_SEPERATE)
			return false;
		for(int i=0; i < timeToCheck.length(); i++)
		{
			if(i != HOUR_MINUTE_SEPERATE_POSITION && !Character.isDigit(timeToCheck.charAt(0)))
			{
				return false;
			}
		}
		return true;		
	}
}