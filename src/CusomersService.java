
public class CusomersService {
	private String name;
	private long id;
	private String details;
	
	public CusomersService(String name, long id, String details)
	{
		this.name = name;
		this.id = id;
		this.details = details;
	}
	
	public boolean equals(Object another)
	{
		if(another instanceof CusomersService)
		{
			CusomersService tmp = (CusomersService)another;
			if(this.id == tmp.id && this.details.equals(tmp.details))
			{
				return true;
			}
		}
		return false;
	}
	
	public String toString()
	{
		return "<Name = " + name + ", Id = " + id + ", details = " + details + ">";
	}
	

}
