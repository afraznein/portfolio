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
import java.util.*;
public class IntBSTreeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Int BST Tester!");
		System.out.println("Creating tree");
		IntBSTree testTree = new IntBSTree();
		System.out.println("Populating Tree with values");
		int[] valArr = {4,8,10,2,1,7,3,5,9,6};
		for(int i : valArr)
		{
			testTree.insert(i);
		}
		System.out.println("Testing insertion by in-order traversal");
		testTree.printInorder();
		System.out.println("Getting depth of 6");
		System.out.println(testTree.getDepth(6));
		System.out.println("Getting depth of 14");
		System.out.println(testTree.getDepth(14));
		
	}

}
