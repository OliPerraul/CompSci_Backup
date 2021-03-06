import javax.xml.soap.Node;


/**
* This class represents the nodes of the doubly linked list used to implement the priority queue
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-03-15 
*/


public class DLinkedPriorityQueue<T> implements PriorityQueueADT<T> 
{
    /** Attribute:
     * Head of the queue
    **/
    private DPriorityNode<T> front;
    
    /** Attribute:
     * rear of the queue
    **/
    private DPriorityNode<T> rear;
    
    /** Attribute:
     * count of how many nodes
    **/
    private int count;
    
    
    /**
     * constructor (Creates an empty priority queue, with null data and zero priority.)
     **/
    public DLinkedPriorityQueue()
    {
	front = null;
	rear = null;
	count = 0;
	
     }
    
       	/**
	* Add element in the correct positio
	* @param T element
	**/
	public void add(T element, double priority)    ///smallest value have the highest priority
	{
	    
	    DPriorityNode<T> node =  new DPriorityNode<T>(element, priority); 
	
	    
	    if(isEmpty())
	    {
		//initialize front, rear & the pointers
		front = node;
		rear = node;
		front.setNext(node);
		front.setPrev(node);
		rear.setNext(node);
		rear.setPrev(node);
		
		//set node pointers to itself
		node.setNext(node); 
		node.setPrev(node);
	    }
	    else
	    {
		if(priority<=front.getPriority()) //if priority of my node is smaller: add at the front
		{
		    //adjust pointers
		    node.setNext(rear);
		    node.setPrev(front);
		    
		    //place node in the list
		    front.setNext(node);
		    
		    //update rear and front
		    front = node;
		    rear.setPrev(node);
		}
		else//in any other case we'll need to iterate through the list
		{
		    DPriorityNode<T> it = front; //iterator
		    boolean flag = false; //flag if found
		    
		    do
		    {
			it = it.getPrev(); //iterate until suitable position if found
			
			if(priority<=it.getPriority())
			{
			flag = true;
			break; //pos found: break out of loop
			}
			
														
		    } while(!it.equals(rear));
		    
		    	if(!flag)//If new node has highest number: goes at the end
			{
		       	//Adjust pointers
		    	node.setNext(it);
		    	node.setPrev(front);
		    	
		    	//place in the list
		    	it.setPrev(node);
			rear = node;//adjust new rear
			front.setNext(node); //adjust front
			}
		    	else //suitable position is found
		    	{
		       	//adjust pointers
			node.setNext(it.getNext());
			
			node.setPrev(it);
			//place in the list
			it.getNext().setPrev(node);
			it.setNext(node);
		    	}
		  }
	    }
	    
	    count++; //increment count
	}

	/**
	* Removes and returns the data item in the priority queue with smallest priority
	* @throws EmptyPriorityQueueException
	* @return T contained_element
	**/
	public T removeMin() throws EmptyPriorityQueueException 
	{
	    if(count == 0)
		throw new EmptyPriorityQueueException ("Empty Queue"); //throws error if empty
	    if(count == 1)
	    {
		T contained_element = front.getElement();
		front = null;
		rear = null;
		
		count--;
		return contained_element;
	    }
	    else
	    {
	    
	    T contained_element = front.getElement();
    
	    DPriorityNode<T> prev = front.getPrev();//save link to previous node to assign as new front
	    DPriorityNode<T> next = front.getNext();//save link to previous node to assign as new front
	     
	    //remove node from curr position by removing link in the queue
	    prev.setNext(next);
	    next.setPrev(prev);
	    front = prev;//assign new front 
	    
	        
	     count--; //decrement count
	     return contained_element;
	    }
	}

	/**
	* Update the element priority in the queue
	* @param T element
	* @param double priority
	* @throws InvalidElementException 
	**/
	public void updatePriority(T element, double newPriority) throws InvalidElementException 
	{
	    if(isEmpty()) //if the queue is empty, then element is not in the queue
	    throw new InvalidElementException("Element not in the queue");
	    else if(front.getElement().equals(element)) //if 1st element in the list
            {
		add(removeMin(), newPriority); //add first node to desired spot
		return;
            }
	    ///////////////////////////////1. Find desired node and change priority////////////////////////////////
	    DPriorityNode<T> node = null; //initially null
	    DPriorityNode<T> it;
	    DPriorityNode<T> prev;
	    DPriorityNode<T> next;
	  	
	    if (rear.getElement().equals(element)) //////////////////////////////////////////if last element in the list
	    {
		 node = rear; //saves a copy
		 
		 prev = rear.getPrev();//save link to previous node to assign as new front
		 next = rear.getNext();//save link to previous node to assign as new front
		 
		 //remove node from curr position by removing link in the queue
		 prev.setNext(next);
		 next.setPrev(prev);
		 rear = next;//assign new front
	    }
	    else				      //////////////////////////////////////////if in the middle of the list
	    {
	    it = front.getPrev(); //initialize iterator as front.getPrev();
	    while(!it.equals(front)) //iterate through the queue until it loops back to the front
	    {
	       if(it.getElement().equals(element))
	       {
		 node = it; //saves a copy
				 
		 prev = it.getPrev();//save pointers
		 next = it.getNext();
		 
		 //remove node from curr position by removing link in the queue
		 prev.setNext(next);
		 next.setPrev(prev);
		 
		 break; //if element is found: break out
	       }
		
	         //////
		it = it.getPrev();//iterate through the queue
	    }
	    }    
	    ////////////////////////////////2. add node with correct priority//////////////////////////////////////////
	    
	   if(node == null)//the element was never found
	    throw new InvalidElementException("Element not in the queue");
	   //if we have found a node
	    //--> add in at correct position
	   add(node.getElement(), newPriority);
	   
	       
	 }//end method

	/**
	* Check if the queue is empty
	* @return boolean isEmpty
	**/
	public boolean isEmpty() 
	{
	    return (count == 0);
	}

	
	/**
	* Check size
	* @return int size
	**/
	public int size() 
	{
	    return count;
	}
	
	
	/**
	* Give a string representation of the queue (from head to rear)
	* @return String s
	**/
	public String toString() 
	{
	   String s = "";
	    
	   DPriorityNode<T> it = front;
	   while(!it.getPrev().equals(front))
	   {
	       s += it.getElement().toString()+" ("+Double.valueOf(it.getPriority())+"), " ;
	       
	       it = it.getPrev();//iterate through the queue
	  }
	   	s += it.getElement().toString()+" ("+Double.valueOf(it.getPriority())+")" ; //retrieves the last element
	    	    
	    return s;
	}
	
	
	
	    

	

}
