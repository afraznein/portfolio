/*
Name: Anthony Frazier
Date: November 1, 2014
Program Name: Loop11
Description: Output 3,3 6,3 6 9,3 6 9 12 using while loop
*/

public class Loop11
{

	public static void main(String[] args)
	{
		String answer = "";
		int x = 3;
		while(answer.equals("3 6 9 12 ") == false)
		{
		answer = answer.concat(x+" ");
		x = x+3;
		System.out.println(answer);
		}
	}
}