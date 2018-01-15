/*
Name: Anthony Frazier
Date: March 30, 2016
Program Name: Lab10
Description: Lab10
Objective:	Objective:

Download the class called Graph from here. It already has some instance variables and constructors.
It is up to you to finish it by adding the following methods.

addVertex: this method which returns nothing and takes in a string parameter corresponding to the name of the new vertex. 
 It will only add a new vertex if that name is not already in the list of vertices.
 
vertexIsContained: this method returns a true or false if there exists a vertex in the graph that matches the
 name passed in via the parameter.
 
addEdge: this method returns nothing and takes in two string parameters and a weight.  The two string parameters correspond
 to the names of vertices and the weight is the weight of the newly created edge.  The newly created edge is then 
 placed in the neighbor’s edge list inside the first vertex.  If either the first or second vertex doesn’t exist the
 method should not continue.
 
getVertex: this method returns an instance of a vertex if the name (passed in via a parameter) is found in the vertex list.

printDFS: This prints out the vertices name’s in depth first order.  You can find the algorithm the presentation provided.

printBFS: This prints out the vertices name’s in breadth first order.  Like above you can find the algorithm in the presentation.

printLazyDFS: In the class there is an instance variable called maxDist which corresponds to the maximum distance that is allowed 
 to travel.  Write another DFS but make sure it doesn’t traverse edges that are greater than the max distance.



*/
public class GraphTester {
	public static void main(String[] args)
	{
		Graph g = new Graph(25);
		System.out.println("Adding verticies");
		g.addVertex("v1");
		g.addVertex("v2");
		g.addVertex("v3");
		g.addVertex("v4");
		g.addVertex("v5");
		g.addVertex("v6");
		g.addVertex("v7");
		g.addVertex("v8");
		System.out.println("Adding edges");
		g.addEdge("v1", "v2", 10);
		g.addEdge("v1", "v4", 20);
		g.addEdge("v1", "v6", 50);
		
		g.addEdge("v2", "v4", 21);
		g.addEdge("v2", "v5", 25);
		
		g.addEdge("v3", "v8", 50);
		
		g.addEdge("v4", "v7", 7);
		g.addEdge("v4", "v8", 40);
		
		g.addEdge("v5", "v3", 23);
		
		g.addEdge("v6", "v4", 31);
		
		g.addEdge("v7", "v6", 10);
		
		System.out.println("Printing DFS");
		g.printDFS();
		System.out.println("Printing BFS");
		g.printBFS();
		System.out.println("Printing Lazy DFS");
		g.printLazyDFS();
	}
}
