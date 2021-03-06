/**
* This class represents a doubly linked list implementation of a priority queue
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-03-15 
*/


public class DPriorityNode<T> 
{
    /** Attribute:
    * Object contained in the node
    **/
    private T element;
    
    /** Attribute:
    * Pointer to the next node
    **/
    private DPriorityNode<T> next;
    
    /** Attribute:
     * Pointer to the prev node
     **/
    private DPriorityNode<T> prev;
    
    
    /** Attribute:
    * Relative priority in the queue
    **/
    private double priority;
    
    
     /**
     * 1st overload of the constructor (Creates an empty node, with null data and zero priority.)
     **/
     public DPriorityNode ()
     {
 	element = null;
 	priority = 0f;
 	
     }
    
    /**
    * 2nd overload of the constructor
    * @param T data (object contained)
    * @param double priority (object contained)
    **/
    public DPriorityNode (T data, double prio)
    {
	element = data;
	priority = prio;
	
    }
    
    //Accessors and mutators
    //getPriority, getElement, getNext, getPrev, setElement, setNext, setPrev, setPriority.
    public double getPriority(){return priority;}
    public void setPriority(double pr){priority= pr;}
    public T getElement(){return element;}
    public void setElement(T el){element= el;}
    public DPriorityNode<T> getNext(){return next;}
    public void setNext(DPriorityNode<T> nxt){next = nxt;}
    public DPriorityNode<T> getPrev(){return prev;}
    public void setPrev(DPriorityNode<T> prv){prev = prv;}
    
       

}
