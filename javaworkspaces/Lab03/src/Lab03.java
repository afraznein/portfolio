/*
Name: Anthony Frazier
Date: January 26, 2016
Program Name: Lab03
Description: Lab03
Objective:	
Implement two versions of a generic queue: One using an array and another using a singly linked list.  Remember queues are first in first out (FIFO).  Use the driver to then test each of the queues.
 
Create a class GenArrayQueue which has the following:
Instance Variables
	queue which an array of the generic type T
Class Varaibles (IE constant)
	DEFAULT_SIZE this integer value takes the value of 100 
Constructors
	A default constructor that initializes queue of the default size
	A parameterized constructor which takes in a whole number that sets the size of the array
	HINT:  Both of these needs to be set up as type an Object array, and then cast to a generic T array.  Look up type casting.
Methods
	enqueue – This method returns no value and takes a variable of type T and adds it to the last available spot in the array.
	dequeue – This method removes and returns the first element in the queue
	peek – This method returns the first element of the queue without removing it
	showQueue – Prints out the queue in order
	
Create another class GenLLQueue which has the following:

Internal class ListNode which contains:
	Instance variable data of type T
	Instance variable link of type ListNode
	Default constructor that sets both instance variables to null
Instance Variables
	head which is of type ListNode which points to the first element in the queue
	tail which of type ListNode which points to the last element in the queue
Constructor
	A default constructor that initializes head and tail to null
Methods
	enqueue – This method returns no value and takes a variable of type T and adds it after the tail.  The moves to tail to point to the newly added element.
	dequeue – This method removes and returns the first element in the queue
	peek – This method returns the first element of the queue without removing it
	showQueue – Prints out the queue in order
*/
import java.lang.*;
public class Lab03
{
	public static void main(String[] args)
	{
		System.out.println("Testing things.");
		GenArrayQueueTest();
		GenLLQueueTest();

	}
	
	public static void GenLLQueueTest()
	{
		System.out.println("Testing Generic Linked List Queue");
		System.out.println("Enqueue-ing 10 numbers 0-9");
		GenLLQueue newQueue = new GenLLQueue();		//  Enqueue test
		for (int i=0;i<10;i++)
		{
			newQueue.enqueue(i);
		}
		System.out.println("Testing Peek.");		// Peek test
		newQueue.peek();
		
		System.out.println("Testing show queue");
		
		newQueue.showQueue();
		
		System.out.println("Dequeue-ing all numbers and printing them out.");
		for (int i=0;i<10;i++)
		{
			//Integer printData = newQueue.deQueueList();
			//System.out.println(printData);
		}
		
	}

	public static void GenArrayQueueTest()
	{
		System.out.println("Testing Generic Array Queue");
		System.out.println("Enqueue-ing 10 numbers 0-9");
		GenArrayQueue newQueue = new GenArrayQueue(10);		//  Enqueue test
		for (Integer i = new Integer(0);i<10;i++)
		{
			newQueue.enqueue(i);
		}
		System.out.println("Testing Peek.");		// Peek test
		newQueue.peek();
		
		System.out.println("Testing show queue");
		
		newQueue.showQueue();
		
		System.out.println("Dequeue-ing all numbers and printing them out.");
		for (Integer i = new Integer(0);i<10;i++)
		{
			//Integer printData = new Integer(0);
			//printData = newQueue.deQueueArray();// wtfAmIDoing.jpg
			//System.out.println(printData);
		}
		
	}
}
