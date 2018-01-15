/*
Name: Anthony Frazier
Date: January 20, 2016
Program Name: Lab2
Description: Lab2
Objective: 
	The class IntDoubleLinkedListTester which demonstrates that you double linked list works as expected.
	 It must demonstrated each one of the method described in a main method

*/
import java.util.Scanner;
public class IntDoubleLinkedListTester {

	public static void main(String[] args) {
		
		IntDoubleLinkedList list = new IntDoubleLinkedList();		// New List Created
																	// This is not working yet.
		Scanner input = new Scanner(System.in);	
		
		System.out.println("New list created, what data do you want to enter?");
		int x = input.nextInt();
		System.out.println("Data set");
		list.setDataAtCurrent(x);
		System.out.println("Showing list");
		list.showList();
		System.out.println("Inserting a node. What data to insert?");
		x = input.nextInt();
		list.insertNodeAfterCurrent(x);
		list.showList();
		System.out.println("Going to previous node.");
		list.goToPrev();
		System.out.println("Data at the current node?");
		list.getDataAtCurrent();
		list.showList();
		System.out.println("Showing list.");
		list.goToNext();
		System.out.println("Going to next node");
		x = input.nextInt();
		list.setDataAtCurrent(x);
		list.getDataAtCurrent();
		System.out.println("What data do you want to check for?");
		x = input.nextInt();
		list.inList(x);
		System.out.println("Deleting a node.");
		list.deleteCurrentNode();
		list.showList();
	}

}
