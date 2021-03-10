/*
Name: Anthony Frazier
Date: November 1, 2014
Program Name: Loop10
Description: Output 3,3 6,3 6 9,3 6 9 12 using for loop
*/

public class Loop10
{

	public static void main(String[] args)
	{
		String answer = "";
		for(int x=3;x<=12;x=x+3)
		{
		answer = answer.concat(x+" ");
		System.out.println(answer);
		}
	}
}