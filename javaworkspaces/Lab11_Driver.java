/*
Name: Anthony Frazier
Date: April 6, 2016
Program Name: Lab11
Description: Lab11
Objective:	Objective:

Create a class which constructs an adjacency matrix representation of a graph and performs a few graph operations.

Write an Adjacency Matrix Graph class which has the following:

Two constructors:
	-Default which makes the matrix of a pre-defined size
	-Parameterized which takes in a non-negative or 0 size and creates an empty matrix

-addEdge: this method returns nothing and takes in two string parameters and a weight. 
	The two integer parameters correspond to the names of vertices and the weight is the weight of the newly created edge.  

-printDFS: This prints out the vertices name’s in depth first order.  You can find the algorithm the presentation provided.

-printBFS: This prints out the vertices name’s in breadth first order.  Like above you can find the algorithm in the presentation.

printDFSForAll: For every vertex in the graph it will print its DFS

printBFSForAll: For every vertex in the graph it will print its BFS

 

Write another file that tests each of the methods for this graph.  


*/
public class Lab11_Driver {

	public static void main(String[] args) {
		System.out.println("This lab is designed to demonstrate an Adjacency Matrix Graph.");
		System.out.println("Generating Graph based on image provided in lab.");
		AMG adjMatGrap = new AMG(8);
		// addEdge(toVertex, fromVertex, weight
		adjMatGrap.addEdge(2, 1, 5.0);
		adjMatGrap.addEdge(4, 1, 5.0);
		System.out.println("Edges added from 1->2 and 1->4");
		adjMatGrap.addEdge(4, 2, 5.0);
		System.out.println("Edges added from 2->4");
		adjMatGrap.addEdge(5, 4, 5.0);
		adjMatGrap.addEdge(3, 4, 5.0);
		System.out.println("Edges added from 4->5 and 4->3");
		adjMatGrap.addEdge(1, 3, 5.0);
		adjMatGrap.addEdge(5, 3, 5.0);
		adjMatGrap.addEdge(6, 3, 5.0);
		System.out.println("Edges added from 3->1 and 3->5 and 3->6");
		adjMatGrap.addEdge(6, 5, 5.0);
		adjMatGrap.addEdge(7, 5, 5.0);
		System.out.println("Edges added from 5->6 and 5->7");
		System.out.println();
		System.out.println("Printing DFS");
		adjMatGrap.printDFS();
		System.out.println();
		System.out.println("Printing BFS");
		adjMatGrap.printBFS();
		System.out.println();
		System.out.println("Printing DFS for all");
		for (int i = 1; i < 8; i++)
		{
			System.out.println("DFS: "+i);
			adjMatGrap.printDFSForAll(i);
			System.out.println();
		}
		System.out.println("Printing BFS for all");
		for (int i = 1; i < 8; i++)
		{
			System.out.println("BFS: "+i);
			adjMatGrap.printBFSForAll(i);
			System.out.println();
		}
		
		

	}

}
