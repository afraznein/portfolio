package osp.Resources;

import java.util.*;

import osp.Utilities.*;
import osp.IFLModules.*;
import osp.Threads.*;

/**
   The studends module for dealing with resource management. The methods 
   that have to be implemented are do_grant().

   @OSPProject Resources
*/

public class RRB extends IflRRB
{
    /** 
        constructor of class RRB 
        Creates a new RRB object. This constructor must have
        super() as its first statement.

        @OSPProject Resources
    */   
    public RRB(ThreadCB thread, ResourceCB resource, int quantity)
    {
        // your code goes here
    	super(thread, resource, quantity);
    }

    /**
       This method is called when we decide to grant an RRB.
       The method must update the various resource quantities
       and notify the thread waiting on the granted RRB.

        @OSPProject Resources
    */
    public void do_grant()
    {
        // Update Available and Allocated based on requests
    	//  and set status to granted. Notify threads to resume
    	//  the thread waiting on this
    	
    	//ResourceCB tempR = new ResourceCB();
    	ResourceCB tempResource = this.getResource();
    	int tempQty = 0;
    	tempQty = tempResource.getAvailable() - this.getQuantity();
    	tempResource.setAvailable(tempQty);
    	
    	tempQty = tempResource.getAllocated(this.getThread()) - this.getQuantity();
    	tempResource.setAllocated(this.getThread(), tempQty);
    	
    	this.setStatus(Granted);
    	this.notifyThreads();
    	

    }


    /*
       Feel free to add methods/fields to improve the readability of your code
    */

}

/*
      Feel free to add local classes to improve the readability of your code
*/
