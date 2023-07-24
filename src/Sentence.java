
public class Sentence implements Flippable<Sentence>{

	public String [] arr;
	
	public Sentence(String [] arr)
	{
		this.arr= arr;
	}
	
	public Sentence flip()
	{
		if(arr == null)
			return null;
		String [] newArr = new String [arr.length];
		int lastCheckedIndex;
		if(arr.length % 2 == 0)
		{
			lastCheckedIndex = arr.length / 2 - 1;
		}else {
			lastCheckedIndex = arr.length / 2;
		}
		for(int i = 0; i <= lastCheckedIndex; i++)
		{
			newArr[i] = arr[arr.length - 1 - i];
			newArr[arr.length - 1 - i] = arr[i];
		}
		return new Sentence(newArr);
	}
	
	public static void main(String[]args)
	{
		String [] a = new String [] {"1 ", "2 ", "3 ", "4 ","5 ","6 ","7 ","8 ","9 "};
		for(String st : a)
		System.out.print(st);
		Sentence s = new Sentence(a);
		Sentence n = s.flip();
		System.out.println();
		for(String st : n.arr)
		System.out.print(st);
		
	}
}
