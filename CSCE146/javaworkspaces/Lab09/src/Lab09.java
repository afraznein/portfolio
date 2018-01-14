/*
Name: Anthony Frazier
Date: March 23, 2016
Program Name: Lab09
Description: Lab09
Objective:	Objective:

Implement a minimum (min) heap of integers, and write a driver to demonstrate its functionality.

Min heaps work the same way as maximum (max) heaps except the a parent is always smaller than its children.

The class your min heap should have the following methods

-insert: Add a new element to the heap, and bubble up as needed.

-delete: Remove the first element from the heap and then reheapify.

heapSort: Make a copy of the heap, then remove and print each element from the clone.  Make sure this doesn’t modify the current heap.

-print: Prints the elements in the heap in breadth order.


*/
import java.util.*;
public class Lab09
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);		
		System.out.println("This program is designed to create a min heap tree. How large would you like the tree to be?");
		int ln = input.nextInt();
		MinArrayHeap heapTest = new MinArrayHeap(ln);
		int[] heap = new int[ln];
		
		
		System.out.println("Populating Heap with values");
		for (int i = 0; i < ln; i++ )
		{
			double rand = ( Math.random() * 100.0);
			heap[i] = (int) rand;
			heapTest.insert(heap[i]);
			System.out.println(heap[i]);
		}
		System.out.println("Printing the Heap");
		heapTest.print();
		System.out.println("Testing the Heap Sort");
		heapTest.heapSort();
		System.out.println("Deleting an element from the heap.");
		int returnedValue = heapTest.delete();
		System.out.println("The element removed was "+returnedValue+" and the new heap is now:");
		heapTest.print();
	}
}