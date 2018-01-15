/*
Name: Anthony Frazier
Date: October 30, 2014
Program Name: Loop6
Description: Output 2 4 8 16 32 on new lines using do while loop
*/

public class Loop6
{

	public static void main(String[] args)
	{
		int counter = 2;

		do
		{
			System.out.println(counter);
			counter = counter * 2;
		}while(counter<=32);
	}
}