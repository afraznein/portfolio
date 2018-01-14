/*Name: Anthony Frazier
Date: March 30, 2016
Program Name: Homework07
Description: Homework07
Objective:	Objective:

You need to organize sheep in a heap.  Fat sheep should go on the bottom so they don’t crush the skinny sheep.
     Sheep have:
  Name
  Weight 
  
  The heap (HINT: min-heap) should have the following methods:
  
 - addSheep: inserts a new instance of a sheep.  The sheep should be added to the bottom of the heap and it is expected to 
    climb up the heap until it’s on top of a heavier sheep but below a light sheep.
    
  -climbUp: used by addSheep to allow the sheep to climb the heap and get in the right place.
  
  -removeSheep: removes the sheep from atop the heap.  Then the sheep on the bottom of the heap, is put on the top and climbs down until it’s where it needs to be.
  
  -climbDown:  Used by remove sheep to move the sheep down to the right place.  Always pick the lighter of the sheep below it and 
    swap if the current sheep is heavier than the lighter one.
    
  -sheepRollCall: Print out the sheep’s name and weight in breadth order
  
  -sheepHeapSort: return a sorted array of sheep
  
  
  Finally write a test file that demonstrates your sheep heaping abilities.
  It should add 15 sheep
  Remove at least 5
  Demonstrate that these work by calling the sheep roll call
  Then show the sheep heap sort works 
*/
import java.util.*;
public class Homework07Driver {
	public static void main(String[] args) 
	{
		System.out.println("This program is designed to create a heap of Sheep. Lighter sheep to the top!");
		SheepHeap heapTest = new SheepHeap();
		Sheep[] heeep = new Sheep[15];
		System.out.println("Populating Heap with Sheep");
		
	/*	for (Sheep i : heeep)
		{
			Sheep aSheep = new Sheep();
			String str = "";
			double weight = 0.0, rand = 0.0;
			int count = 0, value = 0;
			
			
			rand = Math.random() * 20.0;		
			value = (int)rand;
			while ( count < value)
			{
				str = str + "A";
				count++;
				str = str + "b";
				count++;
				str = str + "a";
				count++;
			}
			rand = Math.random() * 100.0;		
			value = (int)rand;
			i.setName(str);
			i.setWeight(value);
			aSheep = i;
		}
 */		
		for (int i = 0; i < heeep.length; i++)
		{
			Sheep aSheep = new Sheep();
			String str = "";
			double weight = 0.0, rand = 0.0;
			int count = 0, value = 0;
			
			
			rand = Math.random() * 20.0;		
			value = (int)rand;
			while ( count < value)
			{
				str = str + "A";
				count++;
				str = str + "b";
				count++;
				str = str + "a";
				count++;
			}
			rand = Math.random() * 100.0;		
			value = (int)rand;
			aSheep.setName(str);
			aSheep.setWeight(value);
			heeep[i] = aSheep;
		}
		
		for (int i = 0; i < heeep.length; i++)
		{
			heapTest.addSheep(heeep[i].getName(), heeep[i].getWeight());
			System.out.println(heeep[i].toString());
		}
		System.out.println("Roll Call!");
		heapTest.sheepRollCall();
		System.out.println("Testing the Sheep Heap Sort");
		heapTest.sheepHeapSort();
		int i = 0;
		while ( i < 5)
		{ 
			System.out.println("Removing a Sheep");
			Sheep aShepe = heapTest.removeSheep();
			System.out.println("The element removed was "+aShepe.toString()+" and the new heap is now:");
			heapTest.sheepRollCall();
			i++;
		}
	}
}
