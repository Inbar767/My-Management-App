import java.util.HashMap;
import java.util.Stack;

public class StackCheck {
	private String n;
	private static int number = 0;
	public StackCheck(String vsl) throws Exception{
		if(number >= 10)
			throw new Exception();
		if(vsl.length() < 5 || vsl.length() > 20)
		{
			throw new Exception();
		}
		this.n = vsl;
		number++;
	}
	
	public void finalize()
	{
		number--;
	}
	
	public static <T extends Comparable<T>, E> HashMap <T,E> grep(HashMap <T,E> t, T key)
	{
		if(key ==null || t == null)
			return t;
		HashMap <T,E> tmp = new HashMap <T,E>();
		for(T k :t.keySet())
			if(k.compareTo(key) > 0)
				tmp.put(k, t.get(k));
		return tmp;
	}
	
	public static void main(String[]args)
	{
		StackCheck h1, h2, h3;
			try {
				 new StackCheck("h");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			
			try {
				 h1 = new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 h2 =  new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 h3 =  new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			
			h1 = null;
			h2 = null;
			h3 = null;
			
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			try {
				 new StackCheck("hfrreer");
				System.out.println("Success");
			}catch (Exception e)
			{
				System.out.println("false");
			}
			
			HashMap <Integer, Integer> h = new HashMap <Integer, Integer>();
			int x = 5;
			for(int i = -3 ; i < 20; i++)
			{
				h.put(i, i + 10);
			}
			HashMap <Integer, Integer> p = grep(h, x);
			for(Integer k : p.keySet())
				System.out.println(k + " " + p.get(k));
	}
}
