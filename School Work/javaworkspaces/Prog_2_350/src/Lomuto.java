// Modified with permission by Anthony Frazier
// CSCE350 Spring 17
// Project Two: Lomuto Partioning
// Objective: Using the Lomuto algorithm to sort a subarray and return the position of the pivot for further partioning
public class Lomuto {

	public static int partition(int[] A, int low, int high){
		int p = 0;
		int s = 0;
		int temp = 0;
		
		
		p = A[low];
		s = low;
		for (int i = low+1; i <= high; i++)
		{
			// If A[i] < p, we need to increase the s and swap
			if (A[i] < p)
			{
				s = s + 1;
				temp = A[s];
				A[s] = A[i];
				A[i] = temp;
			} 
		}
		temp = A[low];
		A[low] = A[s];
		A[s] = temp;
		return s;
	}
	
}
