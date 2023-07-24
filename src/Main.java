import java.util.Iterator;

public class Main {
	private final static int NUMBER_OF_POLL =5;
	
	private static void isContains(Queue test, String member)
	{
		System.out.println("Is Queue Contains '" + member + "' ? " + test.contains(member));
	}
	
	private static void tests(Queue test, String test1,  String test2)
	{
		Iterator stringIterator = test.iterator();
		System.out.println("Queue Members : ");
		while(stringIterator.hasNext())
		{
			System.out.print(" -> " + stringIterator.next());
		}
		System.out.println();
		isContains(test, test1);
		isContains(test, test2);
		System.out.println("Queue Size = " +test.size());
	}
	
	public static void main(String[]args)
	{
		Queue <String> stringTest = new Queue<String>(5);
		stringTest.add("Nooni", 3);
		stringTest.add("hello", 1);
		stringTest.add("house", 2);
		stringTest.add("ortal", 4);
		stringTest.add("Inbar", 10);
		stringTest.add("morning", 1);
		stringTest.add("1", 2);
		stringTest.add("day", 1);
		
		String test1 = "day";
		String test2 = "Java";
		
		System.out.println("Queue Before Changes :");
		tests(stringTest, test1, test2);

		System.out.println("\nQueue Changes :");
		for(int i = 0; i < NUMBER_OF_POLL; i++)
		{
			System.out.println("Pop Out The " + (i + 1) + "th String = " + stringTest.poll());
		}
		System.out.println("Can Remove 'day' From Queue ? " + stringTest.remove("day"));
		System.out.println("Can Remove 'java' From Queue ? " + stringTest.remove("java"));
		
		stringTest.add("day", 5);
		stringTest.add("Java", 1);
		
		System.out.println("\nQueue After changes :");
		tests(stringTest, test1, test2);
	}

}
