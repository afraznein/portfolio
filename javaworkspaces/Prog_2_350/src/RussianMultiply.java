// Modified with permission by Anthony Frazier
// CSCE350 Spring 17
// Project Two: Russian Peasant Multiplication
// Objective: Using the Russian Peasant Algorithm, calculate the multiplication of two integers.
//  Use modulus to generate remainders, and increment and decrement our integers by half until n = 1
import java.lang.Math;

public class RussianMultiply {

public static int multiply(int n, int m)
{
	int rem = 0;
	int sum = 0;
	
	if (n == m && n == 1 || m == 1)
	{
		if (n > m)
		return n;
		else
		return m;
	}
	
	while (n != 1)
	{
		// Check to see if n is even, if so, do the following math
		if (n % 2 == 0)
		{
			n = n / 2;
			m = 2 * m;
		}
		else // n is odd, we need to update the sum and need to use the floor for the next pass
		{
			sum = sum + m;
			n = (int) Math.floor((double) n / 2);
			m = 2 * m;
		}
	}
	// Must account for the final remainder.
	sum = sum + m;
	return sum;
}
	
}
