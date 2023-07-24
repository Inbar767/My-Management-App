import java.util.Iterator;

public class MainServices {
	private final static int NUMBER_OF_POLL =5;
	
	private static void isContains(Queue test, CusomersService member)
	{
		System.out.println("Is Queue Contains " + member + " ? " + test.contains(member));
	}
	
	private static void tests(Queue test, CusomersService test1,  CusomersService test2)
	{
		Iterator serviceIterator = test.iterator();
		System.out.println("Queue Members : ");
		while(serviceIterator.hasNext())
		{
			System.out.println(" -> " + serviceIterator.next());
		}
		System.out.println();
		isContains(test, test1);
		isContains(test, test2);
		System.out.println("Queue Size = " +test.size());
	}
	
	public static void main(String[]args)
	{
		Queue <CusomersService> servicesTest = new Queue<CusomersService>(5);
		servicesTest.add(new CusomersService("Inbar", 123456789, "Buy a car"), 3);
		servicesTest.add(new CusomersService("Levi", 123456789, "Buy a car"), 5);
		servicesTest.add(new CusomersService("Levi", 987654321, "Buy a car"), 3);
		servicesTest.add(new CusomersService("Miri", 521346789, "Get a cat"), 2);
		servicesTest.add(new CusomersService("Shay", 456789123, "Get a dog"), 6);
		servicesTest.add(new CusomersService("Shay", 456789123, "Get a dog"), 1);
		servicesTest.add(new CusomersService("Ortal", 123789456, "Buy a cupcake"), 3);
		
		CusomersService test1 = new CusomersService("Inbar", 123456789, "Buy a car");
		CusomersService test2 = new CusomersService("Libi", 987654321, "Selll my car");
		System.out.println("Queue Before Changes :");
		tests(servicesTest, test1, test2);
		
		System.out.println("\nQueue Changes :");
		for(int i = 0; i < NUMBER_OF_POLL; i++)
		{
			System.out.println("Pop Out The " + (i + 1) + "th = " + servicesTest.poll());
		}
		System.out.println("Can Remove " + test1 + " From Queue ? " + servicesTest.remove(test1));
		System.out.println("Can Remove " + test2 + " From Queue ? " + servicesTest.remove(test2));
		servicesTest.add(test1, 5);
		servicesTest.add(test2, 1);
		
		System.out.println("\nQueue After changes :");
		tests(servicesTest, test1, test2);
	}

}
