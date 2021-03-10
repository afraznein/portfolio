/*
Name: Anthony Frazier
Date: November 1, 2014
Program Name: Loop12
Description: Output 3,3 6,3 6 9,3 6 9 12 using do while loop
*/

public class Loop12
{

	public static void main(String[] args)
	{
		String answer = "";
		int x = 3;
		do
		{
			answer = answer.concat(+x+" ");
			x = x+3;
			System.out.println(answer);

		}while(answer.equals("3 6 9 12 ") == false);

	}
}