/*
Name: Anthony Frazier
Date: January 20, 2016
Program Name: Lab2
Description: Lab2
Objective: Write a class called IntDoubleLinkedList which is an integer double linked list, 
and then test it with a IntDoubleLinkedListTester.  This link list is similar to the single linked list that was shown in class except that each node 
in addition to having data and nextLink (which was originally called link) now has prevLink.  Write the following classes:
	The class IntDoubleLinkedList needs to have the following:
		Internal class ListNode which has
		Instance Variables
		data of type int
		nextLink of type ListNode
		prevLink of type ListNode
		Constructors
			Default
			Parameterized
		Instance Variables
			head of type ListNode which always points to the beginning of the linked list
			current of type ListNode which moves around pointing to one of the nodes
		Constructor
			A default constructor that initializes head to an empty ListNode and sets current to point at the head.
		Methods
			goToNext – This moves the current node forward in the list by one node.  It doesn’t move forward if that node is null
			goToPrev – This moves the current node backwards in the list by one node.  It doesn’t move backwards if that node is null.
			getDataAtCurrent – returns the data at the current node as long as the current isn’t null
			setDataAtCurrent – takes in a parameter and sets the data at the current node to that value as long as current is not null
			insertNodeAfterCurrent – creates a new node based on data that is passed in by a parameter and puts that node after the current position
			deleteCurrentNode – removes the current node from the list by resetting the links
			showList – prints out the contents of the list line-by-line
			inList – returns a true or false value based on whether or not the data passed in via a parameter is in the list
			
	The class IntDoubleLinkedListTester which demonstrates that you double linked list works as expected.
	 It must demonstrated each one of the method described in a main method
	
	


*/
public class IntDoubleLinkedList {
	private class ListNode{
	private int data;													// Instance Variables
	private ListNode nextLink;
	private ListNode prevLink;
		
	public ListNode()													// Default Constructor
	{
		this.data=0;
		this.nextLink = null;
		this.prevLink = null;
	}
	public ListNode(int data, ListNode nextLink, ListNode prevLink) {	// Parameterized Constructor
		super();
		this.data = data;
		this.nextLink = nextLink;
		this.prevLink = prevLink;
	}
	}	// End ListNode Class
	
	
	
	private static ListNode head;
	private static ListNode current;
	
	public IntDoubleLinkedList(){
		this.head=null;
		this.current=head;
	}
	
	public void goToNext(){				// goToNext – This moves the current node forward in the list by one node.  It doesn’t move forward if that node is null
		if ( (current.nextLink) != (null) )
			this.current.data = current.nextLink.data;
		else
			System.out.println("That node is null");
	}
	public void goToPrev(){		//goToPrev – This moves the current node backwards in the list by one node.  It doesn’t move backwards if that node is null.
		if ( (current.prevLink) != (null) )
			this.current.data = current.prevLink.data;
		else
			System.out.println("That node is null");
	}
	public int getDataAtCurrent(){		//getDataAtCurrent – returns the data at the current node as long as the current isn’t null
		return this.current.data;
	}
	public void setDataAtCurrent(int aData){		//setDataAtCurrent – takes in a parameter and sets the data at the current node to that value as long as current is not null
		current.data = aData;
	}
	public void insertNodeAfterCurrent(int newData){		//insertNodeAfterCurrent – creates a new node based on data that is passed in by a parameter and puts that node after the current position
		ListNode node = new ListNode();
		node.data = newData;
		if ( current != null)
		{
			node.nextLink = current.nextLink;
			current.nextLink = node;
		}
	}
	public void deleteCurrentNode(){		//deleteCurrentNode – removes the current node from the list by resetting the links
		if ( current != null )
		{
			current.prevLink = current.nextLink;
			current = current.prevLink;			
		}
	}
	public static void showList(){		//showList – prints out the contents of the list line-by-line
		System.out.println(head.data);
		while ( current.nextLink != null)
		{
			current.nextLink = current;
			System.out.println(current.data);
		}
		
	}
	public static boolean inList(int aData){		//inList – returns a true or false value based on whether or not the data passed in via a parameter is in the list
		if ( aData == head.data)
		{
			return true;
		}
		while ( current.nextLink != null)
		{
			if ( aData ==  current.data )
			{
				return true;
			}
			current.nextLink = current;
		}
		return false;
	}

}	// End IntDoubleLinkedList Class
