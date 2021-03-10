/*
Name: Anthony Frazier
Date: November 1, 2014
Program Name: Loop13
Description: Create a program to print 4 table in the format given below
4*1=4
4*2=8
.
..
4*10=40

*/

public class Loop13
{
	public static void main(String[] args)
	{
		int counter = 1;
		int x = 4;

		while(counter<=10)
		{
		System.out.println(x+" * "+counter+" = "+(x*counter));
		counter++;
		}

	}
}



