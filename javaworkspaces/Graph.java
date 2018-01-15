/*
Name: Anthony Frazier
Date: March 30, 2016
Program Name: Lab10
Description: Lab10
Objective:	Objective:

Download the class called Graph from here. It already has some instance variables and constructors.
It is up to you to finish it by adding the following methods.

-addVertex: this method which returns nothing and takes in a string parameter corresponding to the name of the new vertex. 
 It will only add a new vertex if that name is not already in the list of vertices.
 
-vertexIsContained: this method returns a true or false if there exists a vertex in the graph that matches the
 name passed in via the parameter.
 
-addEdge: this method returns nothing and takes in two string parameters and a weight.  The two string parameters correspond
 to the names of vertices and the weight is the weight of the newly created edge.  The newly created edge is then 
 placed in the neighbor’s edge list inside the first vertex.  If either the first or second vertex doesn’t exist the
 method should not continue.
 
-getVertex: this method returns an instance of a vertex if the name (passed in via a parameter) is found in the vertex list.

-printDFS: This prints out the vertices name’s in depth first order.  You can find the algorithm the presentation provided.

-printBFS: This prints out the vertices name’s in breadth first order.  Like above you can find the algorithm in the presentation.

printLazyDFS: In the class there is an instance variable called maxDist which corresponds to the maximum distance that is allowed 
 to travel.  Write another DFS but make sure it doesn’t traverse edges that are greater than the max distance.



*/
import java.util.*;
public class Graph {
	private class Vertex
	{
		String name;
		ArrayList<Edge> neighbors;
		public Vertex(String aName)
		{
			this.name = aName;
			this.neighbors = new ArrayList<Edge>();
		}
	}
	private class Edge
	{
		Vertex nextVert;
		double weight;
		public Edge(Vertex aV1, double aWeight)
		{
			nextVert = aV1;
			weight = aWeight;
		}
	}
	
	private Vertex origin;
	private ArrayList<Vertex> verticies;
	private ArrayList<Vertex> markedVerts;
	private ArrayList<Vertex> visitedVerts;
	private double maxLength; 
	public Graph(double aLength)
	{
		origin = null;
		verticies = new ArrayList<Vertex>();
		markedVerts = new ArrayList<Vertex>();
		visitedVerts = new ArrayList<Vertex>();
		maxLength = aLength;
	}
	
	public void addVertex(String aName)
	{
		Vertex v = new Vertex(aName);
		verticies.add(v);
		if (origin == null)
		{
			origin = v;
		}
	}
	
	public boolean vertexIsContained(String aName)
	{
		for (Vertex vert: verticies)
		{
			if (vert.name.equals(aName))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addEdge(String fromVert, String toVert, double weight)
	{
		Vertex vOne = getVertex(fromVert);
		Vertex vTwo = getVertex(toVert);
		if (vOne == null || vTwo == null)
		{
			return;
		}
		vOne.neighbors.add(new Edge(vTwo, weight));
	}
	
	public Vertex getVertex(String aName)
	{
		for (Vertex vert: verticies)
		{
			if (vert.name.equals(aName))
			{
				return vert;
			}
		}
		return null;
	}
	
	public void printDFS()
	{
		markedVerts.clear();
		printDFS(origin);
	}
	
	private void printDFS(Vertex vert)
	{
		if (markedVerts.contains(vert))		// if the marked verticies list already contains vert, recursion back up
		{
			return;
		}
		System.out.println(vert.name);
		markedVerts.add(vert);				// Add vert to marked list
		for (Edge ed: vert.neighbors)
		{
			printDFS(ed.nextVert);
		}
	}
	
	public void printBFS()
	{
		markedVerts.clear();
		visitedVerts.clear();
		System.out.println(origin.name);
		printBFS(origin);
	}
	
	private void printBFS(Vertex vert)
	{
		if(markedVerts.contains(vert))	// if the marked verticies list already contains vert, recursion back up
		{
			return;
		}
		markedVerts.add(vert);
		for (Edge ed: vert.neighbors)
		{
			if (visitedVerts.contains(ed.nextVert) || markedVerts.contains(ed.nextVert))
			{
				continue;
			}
			System.out.println(ed.nextVert.name);
			visitedVerts.add(ed.nextVert);
		}
		for (Edge ed: vert.neighbors)
		{
			printBFS(ed.nextVert);
		}
	}
	
	public void printLazyDFS()
	{
		markedVerts.clear();
		printLazyDFS(origin);
	}
	
	private void printLazyDFS(Vertex vert)
	{
		if (markedVerts.contains(vert))		// if the marked verticies list already contains vert, recursion back up
		{
			return;
		}
		System.out.println(vert.name);
		markedVerts.add(vert);				// Add vert to marked list
		for (Edge ed: vert.neighbors)
		{
			if (ed.weight < maxLength)
			{
				printLazyDFS(ed.nextVert);
			}
			
		}
	}
	

}
