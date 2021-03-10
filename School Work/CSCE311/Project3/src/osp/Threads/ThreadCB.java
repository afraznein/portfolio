// Modified with permission by Anthony Frazier
// email: anthonyf@email.sc.edu
// CSCE311 Spring 17
// Project Three: ThreadCB
// Objective: Implement the methods provided in ThreadCB and TimerInteruptHandler.
//   The overall objective of this class is to handle all actions related to threads, 
//   including: creating, killing, dispatching, resuming, and suspending threads.
package osp.Threads;
import java.util.Vector;
import java.util.Enumeration;
import java.util.LinkedList;

import osp.Utilities.*;
import osp.IFLModules.*;
import osp.Tasks.*;
import osp.EventEngine.*;
import osp.Hardware.*;
import osp.Devices.*;
import osp.Memory.*;
import osp.Resources.*;

public class ThreadCB extends IflThreadCB 
{	
	public static GenericList ReadyQ;
	public static GenericList TempQ;
	public static GenericList[] Active;
	public static GenericList[] Expired;
	public static GenericList[] Temp;
	public static final int DEFAULT_PRIORITY = 4;
	//public static ThreadCB newThread;
	//public static ThreadCB tempThread;
	
	// Thread constructor.
    public ThreadCB()
    {
    	super();
    	//this.setPriority(0);
    	//this.setStatus(0);
    	//this.setTask(null);
    }

    // Initialize static variables here
    public static void init()
    {
    	ReadyQ = new GenericList();
    	Active = new GenericList[9];
    	Expired = new GenericList[9];
    	Temp = new GenericList[9];
    	//newThread = new ThreadCB();
    	//tempThread = new ThreadCB();
    }
    
    // Creates a new thread if: task is != null, thread count !>= MaxThreadsPerTask
    // If the thread is successfully created, then add to the expired array
    
    static public ThreadCB do_create(TaskCB task)
    {
  
    	ThreadCB newThread = new ThreadCB();
    	// Check to see if task is null. If it is, call dispatch and return null.
    	if (task == null)
    	{
    		dispatch();
    		return null;
    	}
    	// If count > Max, call dispatch and return null, else, create thread
    	int count = task.getThreadCount();
    	if (count > MaxThreadsPerTask)
    	{
    		dispatch();
    		return null;    		
    	}
    	// Set thread priority to default priority (4), set status to ThreadReady, and 
    	//  associate this thread with the task. Assign this thread to the 
    	//  expired array at priority (4).
    	newThread.setPriority(DEFAULT_PRIORITY);
		newThread.setStatus(ThreadReady);
		newThread.setTask(task);
		// If adding this thread to this task fails, call dispatch and return null
		if (task.addThread(newThread) == FAILURE)
		{
			dispatch();
			return null;
    	}
		/* **NOTE**Do we still need to append to ReadyQ in addition to TempQ?
    	// Add new thread to the Ready Queue, call the dispatcher, and return thread
    	ReadyQ.append(newThread);
    	dispatch();
    	return newThread;
    	*/
		// Place the new thread in the expired array queue for default priority.
		TempQ = new GenericList();
    	TempQ = Expired[DEFAULT_PRIORITY];
    	try{
    		TempQ.append(newThread);	
    	}
    	catch(NullPointerException e)
    	{}	
    	Expired[DEFAULT_PRIORITY] = TempQ;
    	dispatch();
    	return newThread;
    }
    
    // Kills a specific thread. If thread was on the ReadyQ, remove it.
    // If thread was running, idle it, and resume a waiting thread.
    
    public void do_kill()
    {
    	ThreadCB tempThread = null;
    	// Get the status of the thread.
    	// If status = ThreadReady, remove it from ReadyQ
    	if (this.getStatus() == ThreadReady)
    	{
    		ReadyQ.remove(this);
    		//Expired[this.getPriority()].remove(this);
    	}
    	// If status = ThreadRunning, preempt the thread
    	if (this.getStatus() == ThreadRunning)
    	{
    		if (MMU.getPTBR() == null)
    		{
    			// How did you get here if status = ThreadRunning?
    		}
    		// Ensure to catch null pointer problems
    		try {
    		tempThread = MMU.getPTBR().getTask().getCurrentThread();
    		}
    		catch (NullPointerException e)
    		{}
    		if (this == tempThread)
    		{
    			MMU.setPTBR(null);
    		}
    		this.setStatus(ThreadWaiting);
    		this.getTask().setCurrentThread(null);
    		MMU.setPTBR(null);
    	}
    	// Remove the thread task from the thread, and set status to ThreadKill
    	this.getTask().removeThread(this);
    	this.setStatus(ThreadKill);
    	
    	// Loop through device table to purge any IORB using cancelPendingIO 
    	for(int i = 0; i < Device.getTableSize(); i++)
    	{
    		Device.get(i).cancelPendingIO(this);
    	}
  
    	// Release resources currently in use and call dispatch
    	ResourceCB.giveupResources(this);
    	this.dispatch();
    	// If the task does not have any threads left, kill the task
    	if (this.getTask().getThreadCount() == 0)
    	{
    		this.getTask().kill();
    	}
    }
    
    // Suspends the current thread on the processor on the specified event.
    public void do_suspend(Event event)
    {
    	int tempStatus = this.getStatus();
    	
    	// If the thread is running, suspend it
    	if (tempStatus == ThreadRunning)
    	{
    		this.getTask().setCurrentThread(null);
    		this.setStatus(ThreadWaiting);
    	}
    	// If the thread is already waiting, then make it wait some more by incrementing
    	if (tempStatus >= ThreadWaiting)
    	{
    		this.setStatus(tempStatus+1);
    	}
    	// Ensure the ReadyQ no longer contains this thread, add it to the event queue, and call dispatch
    	//Expired[this.getPriority()].remove(this);
    	ReadyQ.remove(this);
    	event.addThread(this);
    	dispatch();

    }
    
    // Resume a thread that is currently waiting 
    public void do_resume()
    {	
    	int tempStatus = this.getStatus();

    	// If the status is ThreadWaiting, change the status to ThreadReady
    	if (tempStatus == ThreadWaiting)
    	{
    		this.setStatus(ThreadReady);
    		//ReadyQ.append(this);
    		// Get the priority of the thread. If it is not already highest priority(0)
    		// then 'increase' the priority by decrementing the priority. Ensure we 
    		// remove the thread from the old priority queue, and append it to the new,
    		// higher priority queue.
    		int tempP = this.getPriority();
    		if (tempP > 0)
    		{
    			Expired[tempP].remove(this);
    			this.setPriority(tempP - 1);
    			Expired[tempP-1].append(this);
    		}
    	}
    	// Otherwise, decrement the status
    	else
    	{
    		this.setStatus(tempStatus - 1);
    	}
    	dispatch();
    }
    
    // Select a thread from the expired array and dispatch it. Update the PTBR, and watch for edge cases
    public static int do_dispatch()
    {
    	ThreadCB tempThread = null;
    	TaskCB tempTask = null;
    	
    	try {
    	tempTask = MMU.getPTBR().getTask();
    	tempThread = tempTask.getCurrentThread();
    	}
    	catch(NullPointerException e)
    	{}
    	// If a thread is already running, Set current thread to null, set PTBR to null, change thread
    	// status to ready, and place it in the rear of the expiredQ based on priority
    	
    	// If the thread is being preempted due to an expired quantum, lower its priority
    	// If the quantum has not expired, then keep current priority and place it in the 
    	// queue of the corresponding expired array
    	

    	if(tempThread != null && tempThread.getStatus() == ThreadRunning)
    	{
    		tempTask.setCurrentThread(null);
    		tempThread.setStatus(ThreadReady);
    		int tempPriority = tempThread.getPriority();
    		MMU.setPTBR(null);
    		/*
    		if (tempThread.getPriority() <= 4)
    		{
    			HTimer.set(40);
    		}
    		else // range 5+
    		{
    			HTimer.set(20);
    		}
    		*/
    		if (HTimer.get() < 1)
    		{
    			tempThread.setPriority(tempPriority + 1);
    			Expired[tempPriority].remove(tempThread);
    			Expired[tempPriority+1].append(tempThread);
    		}
    		else
    		{
    			Expired[tempPriority].append(tempThread);
    		}
    		//ReadyQ.append(tempThread);
    	}
    	// Get the thread from the head of the ReadyQ. If this is null, the queue is empty, return failure.
    	
    	boolean done = false;
    	int count = 0;
    	while (!done)
    	{
    		if (count > 1)
    		{
        		MMU.setPTBR(null);
        		return FAILURE;
    		}
    		else
    		{	
	    		for (int i = 0; i < 9; i++)
	    		{
	    			try{
	    				tempThread = (ThreadCB) Active[i].getHead();
	    			}
	    			catch(NullPointerException e)
	    			{}
	    			
	    			if (tempThread == null)
	    			{
	    				i++;
	    			}
	    			else
	    			{
	    				ReadyQ = Active[i];
	    				tempThread = (ThreadCB) ReadyQ.removeHead();
	    	    		MMU.setPTBR(tempThread.getTask().getPageTable());
	    	    		MMU.getPTBR().getTask().setCurrentThread(tempThread);
	    	    		MMU.getPTBR().getTask().getCurrentThread().setStatus(ThreadRunning);
	    			}
	    		}
    		}
    	Temp = Expired;
    	Expired = Active;
    	Active = Temp;
    	count++;
    	}
    	return SUCCESS;
    	/*tempThread = (ThreadCB) ReadyQ.removeHead();
    	if (tempThread == null)
    	{
    		MMU.setPTBR(null);
    		return FAILURE;
    	}
    	// If the ReadyQ is not empty, set the PTBR to the page table of the item at the head
    	else
    	{
    		MMU.setPTBR(tempThread.getTask().getPageTable());
    		MMU.getPTBR().getTask().setCurrentThread(tempThread);
    		MMU.getPTBR().getTask().getCurrentThread().setStatus(ThreadRunning);
    		return SUCCESS;
    	}
    	*/
    }


    public static void atError()
    {
        /**
        Called by OSP after printing an error message. The student can
        insert code here to print various tables and data structures in
        their state just after the error happened.  The body can be
        left empty, if this feature is not used.

        @OSPProject Threads
     */

    }


    public static void atWarning()
    {
        /** Called by OSP after printing a warning message. The student
        can insert code here to print various tables and data
        structures in their state just after the warning happened.
        The body can be left empty, if this feature is not used.
       
        @OSPProject Threads
     */

    }
}
