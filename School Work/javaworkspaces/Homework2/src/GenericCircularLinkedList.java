
public class GenericCircularLinkedList <G>		// Create new Generic Circular Linked List of type 'G' (Generic)
{
	private class ListNode
	{
		private G data;
		private ListNode link;
		
		public ListNode()		// default constructor for Nodes
		{
			this.link = null;
			this.data = null;
		}
	}
	
	private ListNode head;
	private ListNode current;
	private ListNode tail;
	public int numPlayers = 0;
	
	public GenericCircularLinkedList()	// default constructor for LinkedList
	{
		this.head = null;
		this.current = null;
		this.tail = null;
	}
	
	public void create (G data)		// Create a node
	{
		ListNode newNode = new ListNode();
		newNode.data = data;
		
		if ( this.head == null)		// If there is no head, create the head. Point current and the tail at the head.
		{
			head = newNode;
			current = head;
			tail = head;
			numPlayers++;
			return;
		}
		if (current == head)
		{
			current = newNode;
			head.link = newNode;
			tail = current;
			tail.link = head;
			numPlayers++;
			return;
		}
		current.link = newNode;
		current = newNode;
		tail = current;
		tail.link = head;
		numPlayers++;
	}
	
	public void currentReset()
	{
		current = head;
	}
	
	public void currentShift()
	{
		current = current.link;
	}
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	public G getCurrent()
	{
		return current.data;
	}
	
}
