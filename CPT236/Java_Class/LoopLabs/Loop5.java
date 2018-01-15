/*
Name: Anthony Frazier
Date: October 30, 2014
Program Name: Loop5
Description: Output 2 4 8 16 32 on new lines using while loop
*/

public class Loop5
{

	public static void main(String[] args)
	{
		int counter = 2;

		while(counter<=32)
		{
		System.out.println(counter);
		counter = counter * 2;
		}
	}
}

