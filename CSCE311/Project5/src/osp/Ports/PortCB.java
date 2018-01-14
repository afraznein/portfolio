// Modified with permission by Anthony Frazier
// email: anthonyf@email.sc.edu
// CSCE311 Spring 17
// Project Five: PortCB
// Objective: 
package osp.Ports;

import java.util.*;
import osp.IFLModules.*;
import osp.Threads.*;
import osp.Tasks.*;
import osp.Memory.*;
import osp.Utilities.*;

/**
   The students module for dealing with ports. The methods 
   that have to be implemented are do_create(), 
   do_destroy(), do_send(Message msg), do_receive(). 


   @OSPProject Ports
*/

public class PortCB extends IflPortCB
{
    /**
       Creates a new port. This constructor must have

	   super();

       as its first statement.

       @OSPProject Ports
    */
	
	private int bufferLength;
	public static PortCB tempPort;
	public static TaskCB tempTask;
	public static ThreadCB tempThread;
	
	
    public PortCB()
    {
        // your code goes here
    	super();
    }

    /**
       This method is called once at the beginning of the
       simulation. Can be used to initialize static variables.

       @OSPProject Ports
    */
    public static void init()
    {

    }

    /** 
        Sets the properties of a new port, passed as an argument. 
        Creates new message buffer, sets up the owner and adds the port to 
        the task's port list. The owner is not allowed to have more 
        than the maximum number of ports, MaxPortsPerTask.

        @OSPProject Ports
    */
    public static PortCB do_create()
    {
    	tempPort = new PortCB();
    	tempTask = null;
    	
    	try{
    		tempTask = MMU.getPTBR().getTask();
    	} catch (NullPointerException e)
    	{ 	}
    	
    	// Ensure that the current Port count does not equal the max ports per task
    	if (tempTask.getPortCount() == MaxPortsPerTask)
    	{
    		return null;
    	}
    	// If we attempt to add the port and it fails, return null, else
    	if (tempTask.addPort(tempPort) == FAILURE)
    	{
    		return null;
    	}
    	// Assign the port to the task, and set the status

		tempPort.setTask(tempTask);
		tempPort.setStatus(PortLive);

    	tempPort.bufferLength = 0;
    	return tempPort;

    }

    /** Destroys the specified port, and unblocks all threads suspended 
        on this port. Delete all messages. Removes the port from 
        the owners port list.
        @OSPProject Ports
    */
    public void do_destroy()
    {
        // Set the status of the port to PortDestroyed, notify threads, remove the port, and set the owner to null
    	this.setStatus(PortDestroyed);
    	this.notifyThreads();
    	this.getTask().removePort(this);
    	this.setTask(null);
    	
    }

    /**
       Sends the message to the specified port. If the message doesn't fit,
       keep suspending the current thread until the message fits, or the
       port is killed. If the message fits, add it to the buffer. If 
       receiving threads are blocked on this port, resume them all.

       @param msg the message to send.

       @OSPProject Ports
    */
    public int do_send(Message msg)
    {
    	// Verify that the msg sent is well formed. Ensure it is not null and that the length
    	//  is not larger than the buffer length
    	
    	if (msg == null)
    	{
    		return FAILURE;
    	}
    	
    	if (msg.getLength() > PortBufferLength)
    	{
    		return FAILURE;
    	}
    	
    	// Create a new system event using SystemEvent(name). The argument to the constructor
    	//  is a string used to distinguish the event from other events in the system log. Use
    	//  this event to suspend the current thread
    	
    	SystemEvent tempEvent = new SystemEvent("we_are_gonna_need_to_suspend_this");
    	
    	tempThread = null;
    	try{
    		tempThread = MMU.getPTBR().getTask().getCurrentThread();
    	} catch (NullPointerException e)
    	{ 	}
    	
    	//tempThread.suspend(tempEvent);
    	
    	// Now we must ensure there is room in the Port Buffer to accept the message. 
    	//  We initialize a loop that we check repeatedly that accomplishes the following:
    	// We must ensure that the thread is still alive(not killed) and that the port is live.
    	//    
 
    	boolean done = false;
    	while (!done)
    	{
    		/*if(msg.getLength() > this.bufferLength)
    		{
    			tempThread.suspend(tempEvent);
    		}*/
       		if (tempThread.getStatus() == ThreadKill)
    		{
    			this.removeThread(tempThread);
    			return FAILURE;
    		}
    		
    		if(this.getStatus() != PortLive)
    		{
    			tempEvent.notifyThreads();
    			return FAILURE;
    		}
    		if(msg.getLength() <= this.bufferLength)
    		{
    			done = true;
    		}
    		else
    		{
    			tempThread.suspend(tempEvent);
    		}
    	}
    	
    	// Once we have ensured there is enough room in the Port buffer, we
    	//  should append the message. Update the buffer length, notify all 
    	//  threads involved, and return SUCCESS
    	
    	this.appendMessage(msg);
    	this.notifyThreads();
    	this.bufferLength = (this.bufferLength + msg.getLength());
    	if (this.bufferLength > PortBufferLength)
    	{
    		this.bufferLength = PortBufferLength;
    	}
    	tempEvent.notifyThreads();
    	return SUCCESS;
    	
    }

    /** Receive a message from the port. Only the owner is allowed to do this.
        If there is no message in the buffer, keep suspending the current 
	thread until there is a message, or the port is killed. If there
	is a message in the buffer, remove it from the buffer. If 
	sending threads are blocked on this port, resume them all.
	Returning null means FAILURE.

        @OSPProject Ports
    */
    public Message do_receive() 
    {
        // Obtain the current thread, and ensure that the task that owns the thread also
    	//  owns the port. If the tasks != each other, return null
    	tempThread = null;
    	
    	try{
    		tempThread = MMU.getPTBR().getTask().getCurrentThread();
    	} catch (NullPointerException e)
    	{ 	}
    
    	if (this.getTask() != tempThread.getTask())
    	{
    		return null;
    	}
    	
    	// Create another SystemEvent object to suspend the thread, and then suspend the thread
    	
    	SystemEvent tempEvent2 = new SystemEvent("we_are_gonna_need_to_suspend_this_chief");
    	
    	tempThread.suspend(tempEvent2);
    	
    	// Loop over the following: 
    	//  Ensure that there is a message in the port buffer using isEmpty, if there is not, suspend
    	//  When there is finally a message, ensure that the thread was not killed and / or the port destroyed
    	//  If it was, remove  invalid threads, notifyThreads with the tempEvent, and return null
    	boolean done = false;
    	while(!done)
    	{ 
    		if (this.isEmpty())
    		{
    			tempThread.suspend(this);
    		}
    		else
    		{
    			done = true;
    		}
    		if (tempThread.getStatus() == ThreadKill)
    		{
    			this.removeThread(tempThread);
    			tempEvent2.notifyThreads();
    			return null;
    		}
    		if (this.getStatus() != PortLive)
    		{
    			tempEvent2.notifyThreads();
    			return null;
    		}
    	}
    	
    	// Now we can remove the message. Ensure this is caught in a variable we can return. Now, update our variable
    	//  bufferLength based on the message we are removing. Finally, notify threads with and without the system
    	//  event object and return the message.
    	
    	Message returningMessage = this.removeMessage();
    	
    	this.bufferLength = (this.bufferLength + returningMessage.getLength());
    	if (this.bufferLength > PortBufferLength)
    	{
    		this.bufferLength = PortBufferLength;
    	}
    	this.notifyThreads();
    	tempEvent2.notifyThreads();
    	
    	return returningMessage;

    }

    /** Called by OSP after printing an error message. The student can
	insert code here to print various tables and data structures
	in their state just after the error happened.  The body can be
	left empty, if this feature is not used.
	
	@OSPProject Ports
    */
    public static void atError()
    {
        // your code goes here

    }

    /** Called by OSP after printing a warning message. The student
	can insert code here to print various tables and data
	structures in their state just after the warning happened.
	The body can be left empty, if this feature is not used.
     
	@OSPProject Ports
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
