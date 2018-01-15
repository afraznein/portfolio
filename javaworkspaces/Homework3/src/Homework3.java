import java.util.Scanner;

/*
Name: Anthony Frazier
Date: February 3, 2016
Program Name: Homework3
Description: Homework3
Objective:	
A stack is used to calculate values in post-fix notation.  
Numbers are pushed onto the stack, and when an operator is reached it pops off the last two numbers and then pushes the resulting value back on the stack.
This program must:
Calculate values using the algorithm mentioned above and in the slides for addition, subtraction, multiplication, and division  (+,-,*,/)
Error message if the user tries to divide by zero
Take in a string and process each digit and operator one at a time assuming white space delimits each
Make sure to check if there’s at least two items on the stack before when an operator is encountered or else it’s not a properly formatted post-fix expression
Finish when the program reaches the end of the string and it has only one value left on the stack, then that value is popped off and returned.  
Otherwise it was not properly formatted
Use your own created stack and not java’s built in stack
You may use any implementation so either an array or a linked structure.
HINTS!  First when you’re processing the inputed string you can use a Scanner and call the method next() to get each token. 
 From there you can evaluate if that token is an operator, numeric value, or an error. 
 If it’s a numeric value then you may parse that string using Integer.parseInt() to get the integer value.  
 You may also call Integer.parseInt() in a try-catch block.  If the token is not an integer an exception will be raised and should be immediately halted.
 
 
*/

import java.util.*;
public class Homework3 {

	public static void main(String[] args)
	{
		GenArrayStack <Integer> stack = new GenArrayStack();
		Scanner input = new Scanner(System.in);
		String s = "";
		while ( s != "quit")
		{
			System.out.println("Enter a reverse polish expression or 'quit' to quit");		// take input from user
			s = input.nextLine();
			s = s.toLowerCase();
			if (s == "quit")
			{
				break;
			}
			
			parseArray(s, stack);		// send input and stack to parseArray method
		}
	}
	
	public static void parseArray(String s, GenArrayStack <Integer> stack)
	{
		Scanner scan = new Scanner(s);
		int x = 0;
		int count = stack.getHeadIndex();
		boolean hasOperands = true;
		String wrangle = "";
		String str = "";
		System.out.println("Head Index"+count);
		while ( count < 1 )		// Need to have at least two numbers before we get an operand
		{	
			wrangle = scan.next();
			hasOperands = findOperands(wrangle); 
			
			if ( hasOperands )
			{
				System.out.println("Invalid format.");
				System.exit(0);
			}
			else
			{
				x = Integer.parseInt(wrangle);
				stack.push(x);
				count = stack.getHeadIndex();
				System.out.println("Head Index"+count);
			}
		}
		
		while ( !hasOperands )
		{
			wrangle = scan.next();
			hasOperands = findOperands(wrangle);
			if (hasOperands)
			{
				break;
			}
			x = Integer.parseInt(wrangle);
			stack.push(x);
			System.out.println("Head Index"+count);
		}
		// once it finds an operand, then...
		calculatePolish(stack, wrangle);	
	}
	
	public static boolean findOperands(String ret)			// find the operands
	{
		if (ret.charAt(0) == '+' )
		{
			return true;
		}
		else if (ret.charAt(0) == '-' )
		{
			return true;
		}
		else if (ret.charAt(0) == '*' )
		{
			return true;
		}
		else if (ret.charAt(0) == '/' )
		{
			return true;
		}
		else if (ret.charAt(0) == 'x' )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void calculatePolish( GenArrayStack <Integer> stack, String ret)		// assumed that it found an operand at the next value
	{
		int x = 0, y = 0, z = 0;
		ret.trim();
		
		if (ret.charAt(0) == '+' )
		{
			x = stack.pop();
			y = stack.pop();
			
			z = x + y;
			
			System.out.println(z);
			stack.push(z);
			//return ret;
		}
		else if (ret.charAt(0) == '-' )
		{
			x = stack.pop();
			y = stack.pop();
			
			z = x - y;
			
			System.out.println(z);
			stack.push(z);
			//return ret;
		}
		else if (ret.charAt(0) == '*' )
		{
			x = stack.pop();
			y = stack.pop();
			
			z = x * y;
			
			System.out.println(z);
			stack.push(z);
			//return ret;
		}
		else if (ret.charAt(0) == '/' )
		{
			x = stack.pop();
			y = stack.pop();
			
			z = x / y;
			
			System.out.println(z);
			stack.push(z);
			//return ret;
		}
		else if (ret.charAt(0) == 'x' )
		{
			x = stack.pop();
			y = stack.pop();
			
			z = x * y;
			
			System.out.println(z);
			stack.push(z);
			//return ret;
		}
		else
		{
			System.out.println("Invalid operator. Denied");
			//return ret;
		}
	}
}