/*
Name: Anthony Frazier
Date: March 2, 2016
Program Name: Lab07
Description: Lab07
Objective:	Objective:

The class IntBSTree is given fill in the following methods
insert: This method returns nothing and takes in an integer value that is then placed as a new node in the tree based 
	on the binary tree properties.  A reminder values greater than the parent go to the right subtree and values smaller go
	to the left subtree.  Also it may be a good idea to use a recursive method in order to place these values.
	
printInorder: This method which returns nothing and has no parameters prints the in order traversal of the tree. 
 	For in order traversal each left subtree must be visited, then the value is printed out, and finally each of the right subtrees is visited.
 	It is a good idea to make a recursive method to assist with this.  Also if done correctly in-order traversals print out each of 
 	the integers in ascending order.
 	
getDepth: The depth of a node is the number of edges from the root to that number.  
	This method returns nothing and takes in a parameter corresponding to the integer value of a node whose depth is returned.
	If the value is not in the tree a -1 should be returned.  Again a recursive helper method may be useful to solve this.



*/
public class IntBSTree{
	public static int depthNum = 0;
	private class Node
	{
		private int data;
		private Node leftChild;
		private Node rightChild;
		public Node(int aData)
		{
			this.data = aData;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
	private Node root;

	public IntBSTree()
	{
		root = null;
	}
	
	public void insert(int data)
	{
		Node newNode = new Node(data);
		
		if (root == null)
		{
			root = newNode;
		}
		else
		{
			insert(root,data);
		}
	}
	
	private Node insert (Node aNode, int data)
	{
		if (aNode == null)
		{
			Node newNode = new Node(data);
			aNode = newNode;
		}
		else if ( data < aNode.data )
		{
			aNode.leftChild = insert(aNode.leftChild, data);
		}
		else if ( data >= aNode.data )
		{
			aNode.rightChild = insert(aNode.rightChild, data);
		}
		return aNode;
	}
	
	public void printInorder()
	{
		printInOrder(root);
	}
	
	public void printInOrder(Node aNode)
	{
		if (aNode == null)
		{
			return;
		}
		if (aNode.leftChild != null)
		{
			printInOrder(aNode.leftChild);
		}
		System.out.println(aNode.data);
		if(aNode.rightChild != null)
		{
			printInOrder(aNode.rightChild);
		}
		
	}

	public int getDepth(int value)
	{
		depthNum = 0;
		int retNum = getDepth(root, value, depthNum);
		return retNum;
	}
	
	private int getDepth(Node aNode, int value, int depthNum)
	{
		if (aNode == null)	// Reached end of tree without finding value
		{
			depthNum = -1;
			return depthNum;
		}
		if (aNode.data == value)	// Found the value, return depth
		{
			return depthNum;
		}
		//else if (aNode.rightChild.data == value || aNode.leftChild.data == value)		// Found the value in a child
		//{
		//	return depthNum++;
		//}
		if (value < aNode.data)		// Value is less than Left Child, go left
		{
			depthNum++;
			return getDepth(aNode.leftChild, value, depthNum);
		}
		else if (value > aNode.data)		// Value is greater than Right Child, go right
		{
			depthNum++;
			return getDepth(aNode.rightChild, value, depthNum);
		}
		else			// Didn't find the value
		{
			depthNum = -1;
			return depthNum;
		}
	}

}

