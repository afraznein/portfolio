/*
Name: Anthony Frazier
Date: March 16, 2016
Program Name: Lab08
Description: Lab08
Objective:	Objective:

The class IntBSTree is given fill in the following methods

insert: This method returns nothing and takes in an integer value that is then placed as a new node in the tree based 
	on the binary tree properties.  A reminder values greater than the parent go to the right subtree and values smaller go to the left subtree.
	Also it may be a good idea to use a recursive method in order to place these values.

printInorder: This method which returns nothing and has no parameters prints the in order traversal of the tree.
 	For in order traversal each left subtree must be visited, then the value is printed out, and finally each of the right subtrees is visited.
 	It is a good idea to make a recursive method to assist with this.  Also if done correctly in-order traversals print out each of
 	
 	the integers in ascending order.
printBreadthOrder:  This method which returns nothing and has no parameters prints the breadth order traversal of the tree.
  	For this one each element in a row (in other words it and all of its siblings) are printed from left to right.
  	
getDepth: The depth of a node is the number of edges from the root to that number.  
	This method returns the depth and takes in a parameter corresponding to the integer value of a node whose depth is returned. 
	If the value is not in the tree a -1 should be returned.  Again a recursive helper method may be useful to solve this.



*/
public class IntBSTree{
	public static int depthNum = 0;
	private static final int DEFAULT_SIZE = 100;
	private int dataArr[];
	
	public IntBSTree()
	{
		dataArr = new int[DEFAULT_SIZE];
		//for(int i : dataArr)		// Fill array with sentinel value of -1
		//{
		//	dataArr[i] = -1;
		//}
		for (int i = 0; i < DEFAULT_SIZE; i++ )
		{
			dataArr[i] = -1;
		}
	}
	
	public void insert(int data)
	{
		if (dataArr[0] == -1)		// Sent value of -1, if index 0 = -1, tree is empty
		{
			dataArr[0] = data;
		}
		else						// Call recursive
		{
			insert(0, data);
		}
	}
	
	private int insert (int index, int data)
	{
		if (dataArr[index] == -1)		// Found the empty spot
		{
			dataArr[index] = data;
		}
		else if ( data < dataArr[index] )
		{
			if ( ((index * 2) + 1) < dataArr.length )	// Verify no index out of bounds
			{
				dataArr[((index * 2) + 1)] = insert( ((index * 2) + 1), data);	// Go Left
			}
		}
		else if ( data >= dataArr[index] )
		{
			if ( ((index * 2) + 2) < dataArr.length )	// Verify no index out of bounds
			{
				dataArr[((index * 2) + 2)] = insert( ((index * 2) + 2), data);	// Go Right
			}
		}
		return dataArr[index];
	}
	
	public void printInorder()
	{
		printInOrder(0);
	}
	
	public void printInOrder(int index)
	{
		if (index == dataArr.length)
		{
			return;
		}
		if ( ((index * 2) + 1) < dataArr.length )	// Verify no index out of bounds
		{
			if (dataArr[((index * 2) + 1)] != -1)	// Left Child
			{
				//System.out.println(dataArr[((index * 2) + 1)]);
				printInOrder(((index * 2) + 1));
			}
		}
		System.out.println(dataArr[index]);
		if ( ((index * 2) + 2) < dataArr.length )	// Verify no index out of bounds
		{
			if(dataArr[((index * 2) + 2)] != -1)	// Right Child
			{
				//System.out.println(dataArr[((index * 2) + 2)]);
				printInOrder(((index * 2) + 2));
			}
		}
	}

	public int getDepth(int value)
	{
		depthNum = 0;
		int retNum = getDepth(0, value, depthNum);
		return retNum;
	}
	
	private int getDepth(int index, int value, int depthNum)
	{
		if (dataArr[index] == -1)	// Reached end of tree without finding value
		{
			depthNum = -1;
			return depthNum;
		}
		if (dataArr[index] == value)	// Found the value, return depth
		{
			return depthNum;
		}
		if (value < dataArr[index])		// Value is less than Left Child, go left
		{
			depthNum++;
			if ( ((index * 2) + 1) < dataArr.length )	// Verify no index out of bounds
			{
				return getDepth( ((index * 2) + 1), value, depthNum);
			}
			else
			{
				return -1;
			}
		}
		else if (value > dataArr[index])		// Value is greater than Right Child, go right
		{
			depthNum++;
			if ( ((index * 2) + 2) < dataArr.length )	// Verify no index out of bounds
			{
				return getDepth( ((index * 2) + 2), value, depthNum);
			}
			else
			{
				return -1;
			}
		}
		else			// Didn't find the value
		{
			depthNum = -1;
			return depthNum;
		}
	}
	
	public void printBreadthOrder()
	{
		printBreadthOrder(0);
	}
	
	public void printBreadthOrder(int index)
	{
		if (index == dataArr.length)
		{
			return;
		}
		while (index != ((index * 2) + 1) && index < dataArr.length)
		{
			if (dataArr[index] != -1 )
			System.out.println(dataArr[index]);
			index++;
		}
		
	}


}

