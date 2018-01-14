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
public class MinArrayHeap 
{
	public static final int DEFAULT_SIZE = 100;
	private int[] heap;
	private int size;
	
	public MinArrayHeap()
	{
		this.heap = new int[DEFAULT_SIZE];
		size = 0;
	}
	public MinArrayHeap(int heapSize)
	{
		this.heap = new int[heapSize];
		size = 0;
	}	
	public void insert(int value)
	{
		if (size >= heap.length)
		{
			System.out.println("Heap is already full. Sorry.");
			return;
		}
		heap[size] = value;	
		bubbleUp();		// Need to push the lower values up the tree
		size++;
	}
	
	private void bubbleUp()
	{
		int index = this.size;
		int parentIndex = 0;
		while ( index > 0 )
		{
			
			if (index%2!=0)
			{
				parentIndex = (index-1)/2;		// Go To Parent
			}
			else
			{
				parentIndex = (index-2)/2;		// Go to Parent
			}
			
			if ( parentIndex >= 0 && heap[index] < heap[parentIndex])
			{
				int temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
			}
			index = parentIndex;
		}
	}
	
	public int peek()
	{
		if (heap == null)
		{
			System.out.println("Heap is empty.");
			return -1;
		}
		return heap[0];		// Else return root value
	}
	
	public int delete()
	{
		int returnValue = peek();
		heap[0] = heap[size-1];
		heap[size-1] = -1;
		size--;
		bubbleDown();		// Need to push the larger values down the three
		return returnValue;
	}
	
	private void bubbleDown()
	{
		int index = 0;
		while ( (index*2+1) < size )
		{
			int smallIndex = index*2+1;	// First Assumption is that left child is smaller
			
			if ( (index*2+2) < size && heap[index*2+1] > heap[index*2+2] )
			{
				smallIndex = (index*2+2);	// Assumption was wrong, right child was smaller
			}
			
			if ( heap[index] > heap[smallIndex] )	// Parent is larger than child, so swap
			{
				int temp = heap[index];
				heap[index] = heap[smallIndex];
				heap[smallIndex] = temp;
			}
			index = smallIndex;
		}
	}
	
	public void print()
	{
		for (int i = 0; i < size; i++ )
		//for (int i : heap)
		{
			if (heap[i] == -1)
			{
				break;
			}
			else
			{
				System.out.print(heap[i]+" ");
			}
		}
		System.out.println();
	}
	
	
	public void heapSort()
	{
		MinArrayHeap tempoHeap = new MinArrayHeap(heap.length);
		int[] tempo2HeapForYou = heap.clone();
		
		for ( int i=0;i<size;i++)			// Populate our temporary heap with the values from the cloned tempo2HeapForYou
		{
			tempoHeap.insert(tempo2HeapForYou[i]);
		}
		
		// Now Actually Sort
		for ( int i = size; i > 0; i-- )
		{
			System.out.print(tempoHeap.delete()+" ");
		}
		System.out.println();
	}
}
