import java.io.FileNotFoundException;
import java.io.IOException;


/**
* FindExit class serves as main entry point for the class. 
* It also impements a basic pathfinding algorithm through a hexagonal maze.
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-02-23 
*/

public class FindExit 
{
    	/** Attribute:
	* Stack containing viable options that we have skipped
	**/
    private ArrayStack stack_skipped;
    
    	/** Attribute:
  	* Determine whether the exit is reacheable
  	**/
      private boolean exit_reached= false;
    	
      	/**
	* First overload of the constructor
	* @param String fileName (file containing the description of the dungeon)
	**/
	public FindExit(String fileName)
	{
	   int step_count = 0;
	    
	    ////try algorithm
	   try{
	   Dungeon d = new Dungeon(fileName);
	   d.setTimeDelay(10);
	   
	   ArrayStack stack = new ArrayStack();//new array stack
	   stack_skipped = new ArrayStack(); //init stack_skipped
	   
	   
	   Hexagon start = d.start; //starting chamber
	   Hexagon curr = start; //set curr as start initially
	   
	   stack.push(start);
	   start.markPushed(); //choose right value from enum
	   
   	    //while the stack is not empty, and the exit has not been found perform the following steps:
	   while(!stack.isEmpty() && !curr.isExit())
	   {
	      step_count++;
	      //System.out.println(step_count); //debug
	       
	       curr = (Hexagon) stack.peek();
	       
	       if(curr.isExit()) //Exit found
	       {
		   exit_reached = true; //flag exit found
		   curr.markExit();
		   break; //If the current chamber is the exit, then the algorithm exits the loop.
	       }
	       
	       if(adjacentToDragon(curr)) ////If any of the neighbouring chambers has a dragon,return true, pop a chamber and mark as popped
	       {
		  ((Hexagon) stack.pop()).markPopped();//pop a chamber and mark as popped
	       }
	       else
	       {
		   //look for best unmarked chamber relatively to current position
		   Hexagon bestChamber = bestChamber(curr);
		   
		   if(bestChamber != null)//check if unmarked exists
		   {
		   //Push this chamber into the stack and then mark it as pushed.
		   stack.push(bestChamber);
		   bestChamber.markPushed(); //choose right value from enum
		   }
		   else
		   {
		       //if there are no unmarked neighbouring chambers, pop the top chamber from the stack. 
		       //Mark the popped chamber as popped.
		       ((Hexagon) stack.pop()).markPopped();//pop a chamber and mark as popped
		       
		   }
		
	       }
		    	      
	       
	       	       
	   }
	   
	   
	   if(exit_reached)
	   System.out.print("You have reached the exit in " + String.valueOf(step_count-1)+" steps!");
	   else
	   System.out.print("The exit is impossible to reach..");
	   
	    
   
	   }
	   catch(FileNotFoundException fnfe){System.out.println(fnfe.getMessage());}//handles exception if file not found
	   catch(IOException e){System.out.println(e.getMessage());}//handles exception if file not found
	   catch(InvalidDungeonCharacterException e){System.out.println(e.getMessage());}//handles exception if invalidDungeonCharacterException
	  // finally{System.out.println("an error occured");}//misc error occured
	    	   
	   	    
	}//end FindExit constructor
	
	
	/**
	* Main entry point
	**/
	public static void main (String[] args)
	{
	    //check that the user has entered an argument.
	    try
	    {
		if(args.length < 1)
		throw new IllegalArgumentException("Please provide a file as a command line argument");
		//String[] args allows the program to read in the text supplied on the command line
		String dungeonFileName = args[0];

		FindExit findExit = new FindExit(dungeonFileName);
		
	    }
	    catch(IllegalArgumentException e){System.out.println(e.getMessage());};
	    
	    
	        
	}
    
	   
	
	/**
	* If any of the neighbouring chambers has a dragon,return true, pop a chamber and mark as popped
	* @param Hexagon chamber (chamber where the warrior currently is)
	* @return boolean adjacentToDragon
	**/
	private boolean adjacentToDragon(Hexagon chamber)
	{
	    	    
	    //looks for neighbour w/ dragon
	    for(int i= 0; i<6; i++)
	    {
			
		Hexagon neighbour = chamber.getNeighbour(i); //TODO: throw invalid neighboutException
	    	
		if(neighbour != null) //if such neighbour is defined
		{
		if(neighbour.isDragon())
	    	return true; //If any of the neighbouring chambers has a dragon
		}
	    
	    }
	    
	    return false; //if not found
	}
	
	
	/**
	* Finds the best option among neighbouring hex
	* @param Hexagon chamber (chamber where the warrior currently is)
	* @return Hexagon bestChamber
	**/
	private Hexagon bestChamber(Hexagon chamber)
	{
	    Hexagon bestNeighbour = null; //default best neighbour
	    
	     //iterate through all neighbours
	    for(int i= 0; i<6; i++)
	    {
						
		Hexagon neighbour = chamber.getNeighbour(i);
		
		if(neighbour != null) //if such neighbour is defined
		{
		
		if(!neighbour.isMarked())//if neighbour is unvisited
		{
		    if(neighbour.isExit()) //First best (neighbour is exit)
		    {
			bestNeighbour = neighbour; //exit found
			break;//break outside the loop			
		    }
		    else if(neighbour.isEmpty()) //2nd best
		    {
			 if(bestNeighbour != null)//if bestNeighbour exists
			 {
			 if(bestNeighbour.isEmpty())//if current bestNeighbour is also empty
			 stack_skipped.push(bestNeighbour);//add it to the stack of skipped but equally viable options
			 }
			    
			bestNeighbour = neighbour;
			
		    }
		    else if (neighbour.isCacti()) //3rd best
		    {
			if(bestNeighbour != null)//if bestNeighbour exists
			{
			if(bestNeighbour.isCacti())//if current bestNeighbour is also cacti
		        stack_skipped.push(bestNeighbour);//add it to the stack of skipped but equally viable options
			}
			
			if(!skippedBetterHex(neighbour)&& compareHex(neighbour,bestNeighbour))  //check if we skipped better options & and whether curr neighbour is better than the curr bestNeighbour
			bestNeighbour = neighbour;
					
			
		    }
		    else if (neighbour.isLava()) //4th best
		    {
			if(bestNeighbour != null)//if bestNeighbour exists
			{
			if(bestNeighbour.isLava())//if current bestNeighbour is also lava
			stack_skipped.push(bestNeighbour);//add it to the stack of skipped but equally viable options
			}
			
			if(!skippedBetterHex(neighbour) && compareHex(neighbour,bestNeighbour))  //check if we skipped better options & and whether curr neighbour is better than the curr bestNeighbour
			bestNeighbour = neighbour;
						
		    }
		    
		    
		}
			
		
		}
	    
	    	
	    }
		    
	      return bestNeighbour; //return best option
	}
	
	/**
	* Verify stack_skipped for better options
	* @param Hexagon chamber (that we are comparing)
	* @return Boolean skipped (have we skipped better hex)
	**/
	private boolean skippedBetterHex(Hexagon currBest)
	{
	    while(!stack_skipped.isEmpty())
	    {
		if(((Hexagon)stack_skipped.peek()).isEmpty())
		return true;
		else if(((Hexagon)stack_skipped.peek()).isCacti() && currBest.isLava())
		return true;
		
		stack_skipped.pop();
	    }
	    return false;
	    	      
	}
	
	/**
	* Compare two hex for the better option
	* @param Hexagon chamber1 (that we are comparing)
	* @param Hexagon chamber2 (that we are comparing)
	* @return Boolean firstIsBetter (Whether first option is better)
	**/
	private boolean compareHex(Hexagon chamber1, Hexagon chamber2)
	{
	  
	  if(chamber1 == null)//if no value is assign then second is greater by default
	  {
	      return false;
	  }
	  
	  if(chamber2 == null)//opposite
	  {
	      return true;
	  }
	  
	    
	  int hex1_val = 0;//assign value to chamber in order of viability
	  int hex2_val = 0;
	  
	  if(chamber1.isExit())  //first chamber value
	  hex1_val = 4;
	  else if(chamber1.isEmpty())
	  hex1_val = 3;
	  else if(chamber1.isCacti())
          hex1_val = 2;
	  else if(chamber1.isLava())
	  hex1_val = 1;
	  
	  if(chamber2.isExit())  //first chamber value
	  hex2_val = 4;
	  else if(chamber2.isEmpty())
	  hex2_val = 3;
	  else if(chamber2.isCacti())
          hex2_val = 2;
	  else if(chamber2.isLava())
	  hex2_val = 1;
	  
	  return (hex1_val > hex2_val);//return wheter chamber 1 is better then 2
	    
	}
	
}
