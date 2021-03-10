/*
Name: Anthony Frazier
Date: February 9, 2016
Program Name: Lab05
Description: Lab05
Objective:	Objective:
 
Implement both linear search and binary search, and see which one performs better given an array 1,000 randomly generated whole numbers (between 0-999), 
a number picked to search that array at random, and conducting these tests 20 times.  
Each time the search is conducted the number of checks (IE number of times the loop is ran or the number of times the recursive method is called) 
needs to be counted and at the end the total number of checks should be averaged.
 
 
A few notes
	Each algorithm (linear search and binary search) is ran 20 times
		Each time a new sorted array of whole numbers is created and populated with random values from 0-999
		A value to be searched in the said array is randomly selected from the range 0-999
		Each algorithm must display if that number was successfully found
		Each algorithm must display the number of checks it took to determine the above answer
	It is advisable to create a method that returns the sorted array
		Populate the array with random numbers
		Sort the array next
		Return sorted array
	Implement both searches as a method
		However instead of returning whether or not it found the number it should return the number of checks.
		Whether the value is or is not found can be printed in the method
	Binary search is fairly simple to create using recursion
		Do not count the out of bounds or stopping index as a check

*/
public class Lab05 
{

	public static void main(String[] args) 
	{
		int[] list = new int[1000];
		int x = 0, countLin = 0, countBin = 0, loopCount = 0, avgLin = 0, avgBin = 0;
		System.out.println("Welcome to the search tester. We are going to see which algorithm performs the best out of 20 tests.");
		
		while (loopCount < 20)
		{	
			System.out.println("Generating array, sorting and picking value.");
			list = makeArray(list);
			x = pickValue();
			System.out.println("Searching using linear search.");
			countLin = linearSearch(list, x);
			System.out.println("Searching using binary search.");
			countBin = binarySearch(list, x, 0, 999, 0);		// These values are hard-coded as indexes 0 -999 this would need to be modified for variable sized array
			
			avgLin = avgLin + countLin;
			avgBin = avgBin + countBin;
			System.out.println("Linear Checks Performed: "+countLin);
			System.out.println("Binary Checks Performed: "+countBin);
			System.out.println("");
			loopCount++;
		}
		
		avgBin = avgBin / 20; // hardcoded value based on loopcount
		avgLin = avgLin / 20;
		
		System.out.println("\n\n");
		System.out.println("The average number of checks for each sort type over 20 attempts was:");

		System.out.println("Linear Checks Performed: "+avgLin);
		System.out.println("Binary Checks Performed: "+avgBin);	

	}
	
	public static int[] makeArray(int[] arr)
	{
		double rand = 0.0;
		int value = 0;
		
		for (int i = 0; i < arr.length-1; i++)		// Generate the array
		{
			rand = Math.random() * 1000.0;		
			value = (int)rand;
			arr[i]=value;
		}
		int decreasing = arr.length;
		while ( decreasing >= 1)
		{	
			for ( int i = 1; i < decreasing ; i++)		// Sort the array
			{
				if (arr[i-1] < arr[i])
				{
					value = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = value;
				}
						
			}
			decreasing--;
		}
		
		return arr;
	}
	
	public static int pickValue()
	{
		double rand = 0.0;
		int value = 0;
		
		rand = Math.random() * 1000.0;		
		value = (int)rand;
		return value;
	}

	public static int linearSearch(int[] arr, int x)
	{
		int count = 0;
		
		for (int i = 0; i<arr.length; i++)
		{
			if ( arr[i] == arr.length)
			{
				System.out.println("Not Found.");
				return count;
			}
			
			else if ( arr[i] == x)
			{
				System.out.println("Found!!");
				return count;
			}
			else
			{
				count++;
			}
		}
		System.out.println("Not Found.");
		return count;
	}
	
	public static int binarySearch(int[] arr, int x, int minIndex, int maxIndex, int count)
	{
		int midIndex = (minIndex + maxIndex) / 2;
		
		if (minIndex > maxIndex)
		{
			System.out.println("Not Found.");
			return count;
		}
		else if (arr[midIndex] == x)
		{
			System.out.println("Found!!");
			return count;
		}
		else if (x > arr[midIndex])		// searches top half
		{
			count++;
			return binarySearch(arr, x, midIndex+1, maxIndex, count);
		}
		else	// searches bottom half
		{
			count ++;
			return binarySearch(arr, x, minIndex, midIndex-1, count);
		}
	}
}
