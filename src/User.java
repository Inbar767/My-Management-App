import java.util.Date;

public class User {
	String username;
	String passwrod;
	Date birthday;
	String email;
	
	public User(String username, String passwrod, Date birthday, String email)
	{
		this.username = username;
		this.passwrod = passwrod;
		this.birthday = birthday;
		this.email = email;
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return passwrod;
	}
	
	public Date getBirthday()
	{
		return birthday;
	}
	
	public String getEmail()
	{
		return email;
	}
}
