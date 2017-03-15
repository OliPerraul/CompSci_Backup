/**
* Array implementation of a stack. Serves as the application primary datastructure.
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-02-23 
*/


public class ArrayStack<T> implements StackADT<T>
{
    	/** Attribute:
	* This array will store the data items of the stack
	**/
	private T[] stack;
	
	private final int DEFAULT_CAPACITY = 5;
	
	/** Attribute:
	* This variable will contain the index of the last data item in the array
	**/
	private int top;
	
	
	/**
	* Default constructor (Creates an empty stack, initial capacity of 5)
	**/
	public ArrayStack()
	{
	    top = -1;
	    stack = (T[]) (new Object[DEFAULT_CAPACITY]); //create generic array and cast it
	}
	
	/**
	* First overload of the constructor
	* @param int initialCapacity
	**/
	public ArrayStack(int initialCapacity)
	{
	    top = -1;
	    stack = (T[]) (new Object[initialCapacity]); //create generic array and cast it
	}
	
	/**
	* Adds the dataItem to the top of the stack.
	* @param T dataItem
	**/
	public void push (T dataItem) throws FullStackException
	{
	   top ++;
	   	   
	   if(top == stack.length)//stack full
	   {
	       T[] stack_large;//declare new local stack
	       
	   	if(stack.length<20)
	   	{
	   	    stack_large = (T[]) (new Object[stack.length*2]);
	       
	   	}
	   	else
	   	{
	   	   stack_large = (T[]) (new Object[stack.length+10]);//in any other cases
	       	       
	   	 	   	   
	   	}
	   	
	   	//transfer from small to large
	   	for(int i= 0; i<stack.length; i++)
	   	{
	   	    //take elements from bottom to the top & put it in new stack
	   	    stack_large[i] = stack[i];
	   	}
	   	
	   	stack =  stack_large;//assign ref to new stack
	   	
	   	  if(stack.length > 1000) //max size exceeded
	   	   {
	   	       throw new FullStackException("Full Stack");// throw exception
	   	       
	   	    //We upper bounded the capacity of the stack to prevent your program from 
		    //exhausting the memory of the computer if a bug causes it to enter in an infinite loop.
	   	       
	   	   }
	   	
	   }
	   
	   stack[top] = dataItem; //add on top
	        
	  }//end of method
	
	
	/**
	* Removes and returns the data item at the top of the stack
	* @return T dataItem
	**/
	public T pop() throws EmptyStackException
	{
	   
	    if(top != -1)//if top exists
	    {
		T top_object = stack[top];//saves top object
		stack[top] = null; //removes top object
		top--; //decrement the top
		
		return top_object;//return top object
		
	    }
	    else
	    {
		throw new EmptyStackException("Empty Stack"); //throws error if empty
	    }
	 }
	
	
	
	/**
	* Returns the data item at the top of the stack without removing it
	* @return T dataItem
	**/
	public T peek() throws EmptyStackException
	{
	    
	    if(top != -1)//if top exists
	    {
		T top_object = stack[top];//saves top object
		return top_object;//return top object
		
	    }
	    else
	    {
		throw new EmptyStackException("Empty Stack"); //throws error if empty
	    }
	}
	
	
	
	/**
	* Returns true if the stack is empty and it returns false otherwise
	* @return boolean isEmpty
	**/
	public boolean isEmpty()
	{
	   return (top == -1);
	}
	
	
	/**
	* Returns the number of data items in the stack.
	* @return int num
	**/
	public int size()
	{
	    return top+1; //return index of top+1 which corresponds with size  
	}
	
	
	/**
	* Returns a String representation of the stack.
	* @return String toString
	**/
	public String toString()
	{
	    String s = ""; //string representation
	    	
	    //transfer from small to large
	   	for(int i= 0; i<top; i++)
	   	{
	   	    s+= stack[i].toString();
	   	    if(i+1<top)
	   	    s+= ", ";//insert commas except at the end
	   	}
	   	
	   	return s;
	}
	

    
    
}
