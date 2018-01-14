public class GenArrayQueue <T>
{	
public static final int DEFAULT_SIZE = 100;
private T[] queue;
private int tailIndex;

public GenArrayQueue()					// default constructor
{
	queue = (T[]) new Object[DEFAULT_SIZE];
	tailIndex=0;
}

public GenArrayQueue(int size)			// constructor with custom size 
{
	queue = (T[]) new Object[size];
	tailIndex=0;
}

public void enqueue(T data)		// Adds data to the end of the queue
{
	if (tailIndex >= queue.length)		// If the index is at the 'end' of the queue, queue is full
	{
		System.out.println("The queue is full. Sorry");
		return;
	}
	queue[tailIndex] = data;
	tailIndex++;
}

public T dequeue()
{
	if (queue[0] == null)
	{
		System.out.println("Sorry, the queue is empty already.");
		return null;
	}
	
	T returnData = queue[0];			// Assign data at index 0 to new variable returnData of type Generic
	for (int i=0;i<queue.length-1;i++)
	{
		queue[i]=queue[i+1];			// Shift all current objects in queue one to the left
	}
	queue[queue.length-1]=null;			// Assign the end of the line a null value
	tailIndex--;
	return returnData;					// Return data from previous index 0
}

public T peek()							// Just looks at the index 0 and returns value
{
	return queue[0];
}

public void showQueue()					// Prints out queue in order 0 -> queue.length-1
{
	int x = 0;
	for (T node : queue)
	{
		if (node == null)
		{
			break;
		}
		System.out.println("Data "+x+": "+node.toString() );
		x++;
	}
}

}	// End Class GenArrayQueue
