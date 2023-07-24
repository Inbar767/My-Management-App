import java.util.Arrays;

public class Grand extends Child{
	private String st;

	
public static void func(Parent a, Child b) {System.out.println("first");}

public static void func(Child a, Parent b) {System.out.println("first");}

public static void ig(int x) {System.out.println("first");}

public static void main(String[]args) {
	/* Child n = new Child();
	 n.apply();
	 Child a1 = new Grand("abc");
	 int x;
	// System.out.println(x);
	 int len = (a1.toString()).length();

	/* System.out.println(aa.st);
	 System.out.println(ab.st);
	 System.out.println(bb.st);
	 System.out.println(aa.getString());
	 System.out.println(ab.getString());
	 System.out.println(bb.getString());*/
	 Child c = new Child() {
		 public void huhh() {System.out.println("vdv");
	 };
	// c.apply();
	 int [] arr1 = new int[] {1,1,1};
	 int []arr2=arr1;
	 func(arr1,arr2);
	 System.out.println(Arrays.toString(arr1));
	 System.out.println(Arrays.toString(arr2));
	 

	Grand g= new Grand ();
	ig(3);
}

	public void apply() {
		st = st.substring(0, st.length() - 1);
		System.out.println(st);
	}
	
	public String toString()
	{
		return st;
	}
	
	public boolean equals(Object o){if (! (o instanceof Parent)) return false;else if ( (o instanceof Grand)) return (((Grand)o).st).equals(st) ;else if ( (o instanceof Child)) return (((Child)o).getNum())==st.length() ; else return false;}


}
