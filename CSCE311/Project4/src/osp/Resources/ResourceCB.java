package osp.Resources;

import java.util.*;
import osp.IFLModules.*;
import osp.Tasks.*;
import osp.Threads.*;
import osp.Utilities.*;
import osp.Memory.*;

/**
    Class ResourceCB is the core of the resource management module.
    Students implement all the do_* methods.
    @OSPProject Resources
*/
public class ResourceCB extends IflResourceCB
{
    /**
       Creates a new ResourceCB instance with the given number of 
       available instances. This constructor must have super(qty) 
       as its first statement.

       @OSPProject Resources
    */
	
	private static RRB nullRRB;
	private static Hashtable<ThreadCB,RRB> threadRRBTable;
	private static ThreadCB tempT;
	
    public ResourceCB(int qty)
    {
        // your code goes here
    	super(qty);
    }

    /**
       This method is called once, at the beginning of the
       simulation. Can be used to initialize static variables.

       @OSPProject Resources
    */
    public static void init()
    {
        // your code goes here
    	nullRRB = new RRB(null, null, 0);
    	threadRRBTable = new Hashtable<ThreadCB,RRB>();

    }

    /**
       Tries to acquire the given quantity of this resource.
       Uses deadlock avoidance or detection depending on the
       strategy in use, as determined by ResourceCB.getDeadlockMethod().

       @param quantity
       @return The RRB corresponding to the request.
       If the request is invalid (quantity+allocated>total) then return null.

       @OSPProject Resources
    */
    public RRB  do_acquire(int quantity) 
    {
    	// Obtain the current thread and store into a temp thread
    	// Make sure to try catch this to catch null pointer errors

    	//tempT = new ThreadCB();	
	
    	try{
    		tempT = MMU.getPTBR().getTask().getCurrentThread();;	
    	}
    	catch(NullPointerException e)
    	{}	
    	
    	// Ensure that the quantity needed doesn't exceed our total
    	if (quantity + this.getAllocated(tempT) > this.getTotal())
    	{
    		return null;
    	}
    	
    	// Check to see if the thread is already in the hash table. 
    	// If not, add it. Check to see if the quantity of resources
    	// needed is available. If it is, do_grant and return, else
    	// check the status of the thread. If it is waiting, suspend it
    	if (!threadRRBTable.containsKey(tempT))
    	{
    		threadRRBTable.put(tempT, nullRRB);
    		RRB newRRB = new RRB(tempT, this, quantity);
        	if (quantity <= this.getAvailable())
        	{
        		newRRB.do_grant();
        		return newRRB;
        	}
        	else if (tempT.getStatus() != ThreadWaiting)
        	{
        		newRRB.setStatus(Suspended);
        		tempT.suspend(newRRB);
        	}
        	return newRRB;
    	}
    	return null;
    }
    
    public void do_release(int quantity)
    {
    	// Obtain the current thread and store into a temp thread
    	// Make sure to try catch this to catch null pointer errors
    	//ThreadCB tempT = new ThreadCB();
    	try{
    		tempT = MMU.getPTBR().getTask().getCurrentThread();;	
    	}
    	catch(NullPointerException e)
    	{}	
    	
    	// Obtain the amount allocated and ensure that the qty
    	// we are releasing does not exceed the allocation
    	// If it does not, update the allocated and available #s
    	int tempQty = this.getAllocated(tempT);
    	if ((quantity <= tempQty))
    	{
    		tempQty = tempQty - quantity;
    		if (tempQty < 0)
    		{
    			tempQty = 0;
    		}
    		this.setAllocated(tempT, tempQty);
    		if (this.getTotal() - this.getAllocated(tempT) < 0)
    		{
    			this.setAvailable(0);
    		}
    		else
    		{
    		this.setAvailable(this.getTotal()-this.getAllocated(tempT));
    		}
    	}
    	
    	// Iterate over the values in threadRRBTable
    	// If we encounter any available amounts that we 
    	// are looking for, set the status to granted, 
    	// ensure that the thread status != THreadkill
    	// and grant the request. Ensure that we update
    	// the hash table with a null RRB
    	Collection c = threadRRBTable.values();
    	for(int i = 0; i < c.size(); i++)
    	{
    		if (quantity <= this.getAvailable())
    		{
    			((IflRRB) c).setStatus(Granted);
    			if (tempT.getStatus() != ThreadKill)
    			{
    				((RRB) c).do_grant();
    				threadRRBTable.put(tempT, nullRRB);
    			}
    		}
    	}
    	

    }

    /**
       Performs deadlock detection.
       @return A vector of ThreadCB objects found to be in a deadlock.

       @OSPProject Resources
    */
    public static Vector do_deadlockDetection()
    {
    	//---Step One: Identify threads in deadlock
    	
    	// Copy the amount of resources available and assign to
    	// an array named Work
        int availResources = ResourceTable.getSize();
        int Work[] = new int[availResources];
        for (int i = 0; i < availResources; i++)
        {
        	Work[i] = ResourceTable.getResourceCB(i).getAvailable();
        }
        
        boolean Finish[] = new boolean[availResources];
        
        for (int i = 0; i < Work.length; i++)
        {
        	ThreadCB tempT = new ThreadCB();
        	try{
        		tempT = MMU.getPTBR().getTask().getCurrentThread();;	
        	}
        	catch(NullPointerException e)
        	{}	
        	if (ResourceTable.getResourceCB(i).getAllocated(tempT) != 0)
        	{
        		Finish[i] = false;
        	}
        	else
        	{
        		Finish[i] = true;
        	}
        	
        	//---Step Two: Deadlock Recovery

        	Enumeration keys = threadRRBTable.keys();
        	int threadIndx = 0;
        	while (keys.hasMoreElements())
        	{
        		ThreadCB tempT2 = (ThreadCB) keys.nextElement();
        		if (Finish[threadIndx])
        		{
        			//keys.
        		}
        		threadIndx++;
        	}
        }
                
        //---Step Three: Return the vector of deadlocked threads
        Vector aVec = new Vector();
        return aVec;
    }

    /**
       When a thread was killed, this is called to release all
       the resources owned by that thread.

       @param thread -- the thread in question

       @OSPProject Resources
    */
    public static void do_giveupResources(ThreadCB thread)
    {
    	// Release all resources for the given thread
    	for (int i = 0; i < ResourceTable.getSize(); i++)
    	{
    		ResourceTable.getResourceCB(i).setAllocated(thread, 0);
    	}
    	

    }

    /**
        Release a previously acquired resource.

	@param quantity

        @OSPProject Resources
    */


    /** Called by OSP after printing an error message. The student can
	insert code here to print various tables and data structures
	in their state just after the error happened.  The body can be
	left empty, if this feature is not used.
	
	@OSPProject Resources
    */
    public static void atError()
    {
        // your code goes here

    }

    /** Called by OSP after printing a warning message. The student
	can insert code here to print various tables and data
	structures in their state just after the warning happened.
	The body can be left empty, if this feature is not used.
     
	@OSPProject Resources
    */
    public static void atWarning()
    {
        // your code goes here

    }


    /*
       Feel free to add methods/fields to improve the readability of your code
    */

}

/*
      Feel free to add local classes to improve the readability of your code
*/
