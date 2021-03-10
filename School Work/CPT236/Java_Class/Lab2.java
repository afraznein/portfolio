/*
Name: Anthony Frazier
Date: September 10, 2014; September 11, 2014; September 12, 2014
Program Name: Lab2
Description: Second Java Lab; Recieve user input for six test scores, then average those numbers
*/

//Enter 6 test scores:
//78 90 76 88 34 99
//The average of 78, 90, 76, 88, 34 and 99 is 77.5



import java.util.*;
public class Lab2
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);

	int input1, input2, input3, input4, input5, input6; //Declare variables for user input
	double average;										//Declare variable for average of input

		System.out.println("This program is designed to average six scores. \nPlease input six scores on a 0-100 scale.");	//Request input from user for six scores
	 		input1=input.nextInt();
	 	System.out.println("Please input another score on a 0-100 scale.");
	 		input2=input.nextInt();
	 	System.out.println("Please input another score on a 0-100 scale.");
	 		input3=input.nextInt();
	 	System.out.println("Please input another score on a 0-100 scale.");
	 		input4=input.nextInt();
	 	System.out.println("Please input another score on a 0-100 scale.");
	 		input5=input.nextInt();
	 	System.out.println("Please input one last score on a 0-100 scale.");
	 		input6=input.nextInt();
	average = (double) input1+input2+input3+input4+input5+input6;				//Perform calculations
	average = average / 6;														//Perform calculations
	System.out.println("The average of " +input1 +", " +input2 +", " +input3 +", " +input4 +", " +input5 +" and " +input6 +" is " +average+".");		//Output results
	}
}