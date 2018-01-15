public class GenArrayStack <T>
{	
public static final int DEFAULT_SIZE = 100;
private T[] stack;
private int headIndex;

public GenArrayStack()					// default constructor
{
	stack = (T[]) new Object[DEFAULT_SIZE];
	headIndex=0;
}

public GenArrayStack(int size)			// constructor with custom size 
{
	if ( size <= 0 )
	{
		System.out.println("The size was equal to or smaller than zero. Invalid. Stack created at default values.");
		stack = (T[]) new Object[DEFAULT_SIZE];
		headIndex=0;	
	}
	else
	{
		stack = (T[]) new Object[size];
		headIndex=0;
	}
}

public int getHeadIndex() {
	return headIndex;
}

public void setHeadIndex(int headIndex) {
	this.headIndex = headIndex;
}

public void push(T data)		// Pushes data into the stack
{
	if (headIndex+1 >= stack.length)
	{
		System.out.println("The stack is full already. Sorry.");
		return;
	}
	if ( stack[0] == null )		// Empty stack is emptyss
	{
		stack[0] = data;
		System.out.println("Pushed " + data + " to index " + headIndex);
		headIndex++;
		return;
	}
	System.out.println("Pushed " + data + " to index " + headIndex);
	stack[headIndex]=data;
	headIndex++;
	
}

public T pop()		// Pops data off the stack
{
	if (stack[0] == null)
	{
		System.out.println("Sorry, the stack is empty already.");
		return null;
	}
	
	T returnData = stack[headIndex-1];			// Assign data at index headIndex to new variable returnData of type Generic
	headIndex--;
	if (headIndex < 0)
	{
		headIndex = 0;
	}
	return returnData;
}

public T peek(int x)							// Looks at the index of value and returns 
{
	return stack[x];
}

public T peek()							// Looks at headindex
{
	return stack[headIndex];
}

public void showStack()					// Prints out queue in order 0 -> queue.length-1
{
	int x = 0;
	for (T node : stack)
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