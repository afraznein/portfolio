/*
Create another class GenLLQueue which has the following:

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

public class GenLLQueue <T>
{
	private class ListNode
	{
		private T data;
		private ListNode link;
		
		public ListNode()						// default constructor for Nodes
		{
			this.link = null;
			this.data = null;
		}
	}
	
	private ListNode head;
	private ListNode tail;
	
	public GenLLQueue()							// Default constructor for Generic Linked List Queue
	{
		head = null;
		tail = null;
	}


	public void enqueue(T newData)				// Adds data in a new node to the end of the queue
	{
		ListNode newNode = new ListNode();
		newNode.data = newData;
		
		if (head == null) 						// queue is empty
		{
			head = newNode;
			tail = newNode;
			return;
		}
		tail.link = newNode;					// If queue isn't empty, point the tail.link to the new node, and then set tail = tail.link
		tail = tail.link;
	}

	public T dequeue()
	{
		if (head == null)
		{
			System.out.println("Sorry, the queue is empty already.");
			return null;
		}
		
		T returnData = head.data;
		head = head.link;
		return returnData;
		
	}

	public T peek()							// Just looks at the index 0 and returns value
	{
		return head.data;
	}

	public void showQueue()					// Prints out queue in order head -> tail
	{
		int x = 0;
		ListNode moveZig = new ListNode();		// Creates a new node moveZig that starts at the head, and daisy chains until it finds a null value.
		for (moveZig = head;moveZig != null; moveZig = moveZig.link)
		{
			if (moveZig.data == null)
			{
				break;
			}
			moveZig = moveZig.link;
			
			System.out.println("Data "+x+": "+moveZig.data.toString() );		// Prints data at each node
			x++;
		}
	}

}	// End Class GenLLQueue

