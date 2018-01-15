/*
Name: Anthony Frazier
Date: March 25, 2016
Program Name: Homework6
Description: Homework6
Objective:	Objective:

Write a program which creates a binary search tree of different shapes from a file.

     The comparison is based on the shape’s area
     There are 3 shapes
     
  Rectangle
  Circle
  Right Triangle
  
  The file is tab delimited format goes as follows
  
  Rectangle \t side 1 \t side 2
  Circle \t radius
  Right Triangle \t side 1 \t side 2
  
  The binary search tree needs to have the following methods
  
  -insert: inserts a new shape into the tree
  -delete: deletes the shape instance.  Keep in mind that in order for a shape to be equal it must have the same same type and area, but the sides can be interchangeable.
  -print pre-order traversal: Print the data.  Next explore the left subtree.  Finally right explore subtree.
  -print in-order traversal:  Explore the left subtree. Print the data.  Finally explore the right subtree.
  -print post-order traversal:  Explore the left subtree. Then explore the right subtree.  Finally print out the data.
  -max area: return the maximum area in the tree.  Use the properties of the tree to make it efficient.
  delete areas greater than value: For a given value all shapes with an area that’s strictly greater than those values should be deleted.
  Use the principle of a binary search tree to help make it efficient.
  Finally write a test file that demonstrates THE POWER OF TREES!!! SHAPES!!! By reading a shape file.
 
HINTS: 
Inheritance and polymorphism makes this problem not so difficult, so interfaces and base classes are a good idea.
Yes there will be many different class files.
Recursion is your friend.


*/
public class ShapeBST {
	public static int depthNum = 0;
	private class Node
	{
		private Shape data;
		private Node leftChild;
		private Node rightChild;
		public Node(Shape aData)
		{
			this.data = aData;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
	private Node root;

	public ShapeBST()
	{
		root = null;
	}
	
	public void insert(Shape data)		// Inserts a shape into a newNode
	{
		Node newNode = new Node(data);
		
		if (root == null)				// If root is null, insert node at root
		{
			root = newNode;
		}
		else
		{
			insert(root,data);
		}
	}
	
	private Node insert (Node aNode, Shape data)
	{
		if (aNode == null)					
		{
			Node newNode = new Node(data);
			aNode = newNode;
		}
		else if ( data.compareTo(aNode.data) < 0 )			// If the new data's area is less than the parent node's area, insert as left child
		{
			aNode.leftChild = insert(aNode.leftChild, data);
		}
		else if ( data.compareTo(aNode.data) >= 0 )			// Else insert as right child
		{
			aNode.rightChild = insert(aNode.rightChild, data);
		}
		return aNode;
	}
	public Shape maxArea()
	{
		Shape retShape = new Shape();
		retShape = maxArea(root);
		return retShape;
	}
	private Shape maxArea(Node aNode)
	{
		Shape retShape = new Shape();
		if (aNode.rightChild == null)
		{
			retShape = aNode.data;
			return retShape;
		}
		if(aNode.rightChild != null)
		{
			return maxArea(aNode.rightChild);
		}
		else
		{
			return null;
		}
	}
	
	public void printPostOrder()
	{
		printPostOrder(root);
	}
	
	public void printPostOrder(Node aNode)
	{
		Shape newShape = new Shape();
		if (aNode == null)
		{
			return;
		}
		if (aNode.leftChild != null)
		{
			printPostOrder(aNode.leftChild);
		}
		if(aNode.rightChild != null)
		{
			printPostOrder(aNode.rightChild);
		}
		newShape = aNode.data;
		System.out.println(newShape.toString());
		
	}
	
	public void printInOrder()
	{
		printInOrder(root);
	}
	
	public void printInOrder(Node aNode)
	{
		Shape newShape = new Shape();
		if (aNode == null)
		{
			return;
		}
		if (aNode.leftChild != null)
		{
			printInOrder(aNode.leftChild);
		}
		newShape = aNode.data;
		
		System.out.println(newShape.toString());
		if(aNode.rightChild != null)
		{
			printInOrder(aNode.rightChild);
		}
		
	}
	
	public void printPreOrder()
	{
		printPreOrder(root);
	}
	
	public void printPreOrder(Node aNode)
	{
		Shape newShape = new Shape();
		if (aNode == null)
		{
			return;
		}
		newShape = aNode.data;
		System.out.println(newShape.toString());
		if (aNode.leftChild != null)
		{
			printPreOrder(aNode.leftChild);
		}
		if(aNode.rightChild != null)
		{
			printPreOrder(aNode.rightChild);
		}
		
	}
	public void deleteAreasGreaterThanValue(double value)
	{
		Node aNode;
		aNode = root;
		while (aNode.data.getArea() < value)		// Go right (greater than) until we find the first value larger than our searched for value
		{
			aNode = aNode.rightChild;
		}
		Node temp = aNode;
		while (aNode.rightChild != null)			// Delete all right children (greater than)
		{
			Node temp2 = aNode.rightChild;
			aNode.rightChild = null;
		}
		aNode = temp;
		if (aNode.leftChild != null)				// If parent had one left child
		{
			aNode = aNode.leftChild;				// Go left one
			while (aNode.rightChild != null)
			{
				if (aNode.rightChild != null && aNode.rightChild.data.getArea() > value)	// Delete all values greater in this subtree
				{
					aNode.rightChild = null;
				}
				if (aNode.leftChild != null && aNode.leftChild.data.getArea() > value)
				{
					aNode.leftChild = null;
				}
				if (aNode.rightChild != null)
				{	
					aNode = aNode.rightChild;
				}
			}
		}
		if (aNode.data.getArea() > value)
		{
			aNode = null;
		}
	}
	public void delete(Shape value)
	{
		root = delete(root, value);
	}
	public Node delete(Node aNode, Shape value)
	{
		if (aNode == null)
		{
			return null;
		}
		if (value.compareTo(aNode.data) < 0)			// Go Left
		{
			aNode.leftChild = delete(aNode.leftChild, value);
		}
		else if (value.compareTo(aNode.data) > 0)		// Go Right
		{
			aNode.rightChild = delete(aNode.rightChild, value);
		}
		else											// Found value
		{
			if (aNode.rightChild == null)				// Check for no right child
			{
				return aNode.leftChild;
			}
			if (aNode.leftChild == null)				// Check for no left child
			{
				return aNode.rightChild;
			}
			// If program reaches here, has two children
			Node temp = aNode;
			//ToDo Find min in tree, delete min in right subtree, link new node to left child
			return aNode;
		}
		return aNode;
	}
	private Node findMinInTree(Node aNode)
	{
		if(aNode == null)
		{
			return null;
		}
		if (aNode.leftChild == null)
		{
			return aNode;
		}
		else
		{
			return findMinInTree(aNode.leftChild);
		}
	}
	private Node deleteMinFromTree(Node aNode)
	{
		if (aNode == null)
		{
			return null;
		}
		if (aNode.leftChild == null)
		{
			return aNode.rightChild;
		}
		aNode.leftChild = deleteMinFromTree(aNode.leftChild);
		return aNode;
	}
}
