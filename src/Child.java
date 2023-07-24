import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.Arrays;
import java.util.Iterator;
import java.util.*;

public class Child {
	private ArrayList <Lock> a = new ArrayList<Lock>();
	public Integer num ;
	ArrayList<String> s;
	 public String st = "B";
	 public Child() {
		// apply();
	 }

	 public static void xxx()
	 {
		 System.out.println("g");
	 }
	 public String getString(Integer i )
	 {
		 System.out.println("B");
		 return null;
	 }
	 
	 
	 public void apply() {
		 System.out.println("A");
	 }
	 
	 public int getNum() {
		 try{
			 throw new RuntimeException ();
			 }
		 catch(Exception e) { 
			 System.out.println("here"); 
			 throw e;
		} 
		 System.out.println("2123");
		 return 1;
		 }
	 
	 public boolean equals(Child o){                   if (! (o instanceof Child)) return false;return ((Child)o).num == num;}
	 
	 
	 public static <y> void func(int []arr1, int []arr2) {
		 if(arr1==arr2)
			 arr1[0] = 10;
		 
		 arr1 = new int[]{3,3,3};
		 System.out.println(Arrays.toString(arr1));
		 if(arr1!=arr2)
			 arr1[2] = 10;
		 System.out.println(Arrays.toString(arr1));
	 }
	 
	 public void foo(){System.out.println("vf");}
	 
	 public Iterator<String> it() {return s.iterator();}
	 
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
		//	 private  void xxx() {System.out.println("vd");}
		 };
		 c.getNum();
		 Child[] arr = null;
		// c.vdsvsd();
		System.out.println(("hello dd dsvsv vcds").replaceAll("\\s", ""));
		 int [] arr1 = new int[] {1,1,1};
		 int []arr2=arr1;
		 func(arr1,arr2);
		 System.out.println(Arrays.toString(arr1));
		 System.out.println(Arrays.toString(arr2));
		 
	 }
	 
}
