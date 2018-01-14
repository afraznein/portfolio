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
public class SheepHeap {
		public static final int DEFAULT_SIZE = 15;
		private Sheep[] heap;
		private int size = 0;
		
		public SheepHeap()
		{
			this.heap = (Sheep[]) (new Sheep[DEFAULT_SIZE]);
			size = 0;
		}
		public SheepHeap(int leng)
		{
			this.heap = (Sheep[]) (new Sheep[leng]);
			size = 0;
		}
		public void addSheep(String aName, double aWeight)
		{
			Sheep aSheep = new Sheep();
			if (size >= heap.length)
			{
				System.out.println("Heap is already full. Sorry.");
				return;
			}
			aSheep.setName(aName);
			aSheep.setWeight(aWeight);
			heap[size] = aSheep;	
			climpUp();		// Need to push the lower values up the tree
			size++;
		}
		
		private void climpUp()
		{
			int index = this.size;
			int parentIndex = 0;
			while ( index > 0 )
			{
				
				if (index%2!=0)
				{
					parentIndex = (index-1)/2;		// Go To Parent
				}
				else
				{
					parentIndex = (index-2)/2;		// Go to Parent
				}
				
				if ( parentIndex >= 0 && heap[index].getWeight() < heap[parentIndex].getWeight())
				{
					Sheep temp = new Sheep();
					temp = heap[parentIndex];
					heap[parentIndex] = heap[index];
					heap[index] = temp;
				}
				index = parentIndex;
			}
		}
		
		public Sheep peek()
		{
			if (heap == null)
			{
				System.out.println("Heap is empty.");
				return null;
			}
			return heap[0];		// Else return root value
		}
		
		public Sheep removeSheep()
		{
			Sheep aSheep = new Sheep();
			aSheep = peek();
			heap[0] = heap[size-1];
			heap[size-1] = null;
			size--;
			climbDown();		// Need to push the larger values down the three
			return aSheep;
		}
		
		private void climbDown()
		{
			int index = 0;
			while ( (index*2+1) < size )
			{
				int smallIndex = index*2+1;	// First Assumption is that left child is smaller
				
				if ( (index*2+2) < size && heap[index*2+1].getWeight() > heap[index*2+2].getWeight() )
				{
					smallIndex = (index*2+2);	// Assumption was wrong, right child was smaller
				}
				
				if ( heap[index].getWeight() > heap[smallIndex].getWeight() )	// Parent is larger than child, so swap
				{
					Sheep aSheep = new Sheep();
					aSheep = heap[index];
					heap[index] = heap[smallIndex];
					heap[smallIndex] = aSheep;
				}
				index = smallIndex;
			}
		}
		
		public void sheepRollCall()
		{
			for (int i = 0; i < size; i++ )
			{
				if (heap[i] == null)
				{
					break;
				}
				else
				{
					System.out.println(heap[i].toString());
				}
			}
			//System.out.println();
		}
		
		public void sheepHeapSort()
		{
			SheepHeap tempoHeap = new SheepHeap(heap.length);
			Sheep[] tempo2HeapForYou = heap.clone();
			
			for ( int i=0;i<size;i++)			// Populate our temporary heap with the values from the cloned tempo2HeapForYou
			{
				tempoHeap.addSheep(tempo2HeapForYou[i].getName(), tempo2HeapForYou[i].getWeight());
			}
			
			// Now Actually Sort
			for ( int i = size; i > 0; i-- )
			{
				System.out.print(tempoHeap.removeSheep()+" ");
			}
			System.out.println();
		}
	}
