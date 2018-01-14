import java.lang.*;
public class Homework8_Driver {
	
	public static Homework08 adjMatGrap = new Homework08(8);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("We will determine the Konigsberg Bridge Puzzle");
		System.out.println("Generating Graph based on image provided in lab.");
		addEdgesNaow();
		System.out.println("Graph has four land masses 0, 1, 2 and 3.");
		System.out.println("Graph has seven bridges, labelled 1 - 7. Both bridges are two way bridges.");
	
		System.out.println("Printing bridge attempt");

		for (int i = 0; i < 4; i++)
		{
			System.out.println("Starting from "+i);
			adjMatGrap.printBridgePath(i);
			addEdgesNaow();
			System.out.println();
		}
			
		System.out.println("No valid paths will result in ending at the same spot.");
	}
	
	public static void addEdgesNaow()
	{
		// addEdge(toVertex, fromVertex, weight
		adjMatGrap.addEdge(0,1,1); // Bridge A / 1
		adjMatGrap.addEdge(1,0,1); // Bridge A / 1
		
		adjMatGrap.addEdge(0,1,2); // Bridge B / 2
		adjMatGrap.addEdge(1,0,2); // Bridge B / 2 
		
		adjMatGrap.addEdge(0,2,4); // Bridge C / 4
		adjMatGrap.addEdge(2,0,4); // Bridge C / 4
		
		adjMatGrap.addEdge(1,2,3); // Bridge D / 3
		adjMatGrap.addEdge(2,1,3); // Bridge D / 3
		
		adjMatGrap.addEdge(1,3,5); // Bridge E / 5
		adjMatGrap.addEdge(3,1,5); // Bridge E / 5
		
		adjMatGrap.addEdge(1,3,6); // Bridge F / 6
		adjMatGrap.addEdge(3,1,6); // Bridge F / 6
		
		adjMatGrap.addEdge(3,2,7); // Bridge G / 7
		adjMatGrap.addEdge(2,3,7); // Bridge G / 7
	}

}
