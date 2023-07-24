import java.util.LinkedList;
import java.util.Iterator;

public class Queue <T> {
	private LinkedList<T> [] data;
	private int maxPriority;
	
	public Queue(int n)
	{
		maxPriority = n;
		data = new LinkedList[n];
	}
	
	public void add(T member, int priority)
	{
		if(priority < 1 || priority > maxPriority)
		{
			priority = maxPriority;
		}
		if (data[priority - 1] == null)
		{
		     data[priority - 1] = new LinkedList<T>();
		}
		data[priority - 1].add(member);
	}
	
	public T poll()
	{
		T pushedOut;
		pushedOut = null;
		for(int i = 0; i < maxPriority - 1; i++)
		{
			if(!data[i].isEmpty())
			{
				pushedOut = data[i].poll();
				break;
			}
		}
		return pushedOut;
	}
	
	public boolean contains(T member)
	{
		for(int i = 0; i < maxPriority; i++)
		{
			if(data[i] != null)
			{
				for(T queueMember : data[i])
				{
					if(queueMember.equals(member))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean remove(T member)
	{
		for(int i = 0; i < maxPriority; i++)
		{
			if(data[i] != null)
			{
				for(T queueMember : data[i])
				{
					if(queueMember.equals(member))
					{
						data[i].remove(queueMember);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int size()
	{
		int count = 0;
		for(int i = 0; i < maxPriority; i++)
		{
			if(data[i] != null)
			{
				count += data[i].size();
			}
			
		}
		return count;
	}
	
	public Iterator iterator()
	{
		LinkedList <T> tmp = new LinkedList<T>();
		for(int i = 0; i < maxPriority; i++)
		{
			if(data[i] != null && !data[i].isEmpty())
			{
				tmp.addAll(data[i]);
			}
		}
		return tmp.iterator();
	}
}
