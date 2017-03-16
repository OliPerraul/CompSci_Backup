import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
* This class will implement the algorithm to compute a shortest path from the initial chamber to the exit
*
* @author  Olivier Perrault (250890527)
* @version 1.0
* @since   2017-03-15 
*/

public class FindShortestPath 
{
      
    /**
     * Main entry point for the program
     * @param String[] args
     **/
    public static void main (String[] args)
    {
	
	FindShortestPath f_sh = new FindShortestPath("dungeon3.txt");
			
	//check that the user has entered an argument.
//	    try
//	    {
//		if(args.length < 1)
//		throw new IllegalArgumentException("Please provide a file as a command line argument");
//		//String[] args allows the program to read in the text supplied on the command line
//		String dungeonFileName = args[0];
//
//		FindShortestPath f_sh = new FindShortestPath(dungeonFileName);
//		
//	    }
//	    catch(IllegalArgumentException e){System.out.println(e.getMessage());};
	
    }
    
    /**
     * constructor
     **/
    public FindShortestPath(String filename)
    {
	//Your program must print the number of chambers in the path from the initial chamber to the exit. 
	//For example, for the dungeon of the above figure the algorithm must print a message indicating that 
	//the path has length 6. If there is no path from the initial chamber to the exit, your program must print
	//an appropriate message. You might add any private methods and instance variables that you want in this class.
	
	//Your program must catch any exceptions that might be thrown. For each exception thrown an appropriate 
	//message must be printed. The message must explain what caused the exception to be thrown instead of just a 
	//generic message saying that an exception was thrown.
	
		    
	////try algorithm
	try
	{
	    
	    boolean exit_found = false; 
	    
	    Dungeon d = new Dungeon(filename);
	    DLinkedPriorityQueue<Hexagon> queue = new DLinkedPriorityQueue<Hexagon>();
	    
	    //The algorithm will start at the initial chamber
	    Hexagon start = d.getStart(); //starting chamber
	    Hexagon curr = start; //set curr as start initially
	    
	    queue.add(curr, 0);
	    curr.markEnqueued();//mark enqueued
	    
	        	    
	    //while the queue is not empty, and the exit 
	    //has not been found perform the following steps:
	    while(!queue.isEmpty() && !curr.isExit())
	    {
		curr = queue.removeMin();
		curr.markDequeued();
		
		if(curr.isExit())
		{exit_found = true; break;}  //if curr is the exit break out of loop
		
		if(!adjacentToDragon(curr)) //if dragon ignore the following steps and consider other options
		{
		    considerNeighbours(curr,d, queue); //consider possibilities and add them to the queue
		}
		
	    }
	    
	    
	    if(exit_found) //print end message
		System.out.print("You have reached the exit");
	    else 
		System.out.print("Could not find an exit");
	    
	    
	 }
    	catch(FileNotFoundException fnfe){System.out.println(fnfe.getMessage());}//handles exception if file not found
	catch(IOException IOe){System.out.println(IOe.getMessage());}//handles exception if file not found
	catch(InvalidDungeonCharacterException idce){System.out.println(idce.getMessage());}//handles exception if invalidDungeonCharacterException
	
	
    }
    
    
    	/**
	* Picks neighbour among the possible ones and stores them in the queue
	* @param Hexagon chamber (chamber where the warrior currently is)
	* @param Dungeon d
	* @param DLinkedPriorityQueue<Hexagon> q
	* @return Hexagon bestChamber
	**/
	private void considerNeighbours(Hexagon curr_hex, Dungeon d, DLinkedPriorityQueue<Hexagon> queue)
	{
		    	    
	    Hexagon neighbour = null;
	    int dist_frm_start = curr_hex.getDistanceToStart()+1;
	    
	    for(int i= 0; i<6; i++)//iterate through neighbours
	    {
		neighbour = curr_hex.getNeighbour(i); //picks a neighbour
		boolean flag = false; // flag if previously enqueued room's priority is changed
		
		if(neighbour != null)
		{
	  	if(!neighbour.isMarkedDequeued() && !neighbour.isWall()) //check if neighbour is viable
		{
	  	    //check if distance to start > that curr dist meaning that the 
	  	    //previous value stored in neighbour as 
	  	    //the distance from it to the initial chamber was incorrect. 
	  	    if(neighbour.getDistanceToStart()> dist_frm_start)
	  	    {
	  		  neighbour.setDistanceToStart(dist_frm_start);
	  		  flag = true;
	  		  
	  		    //The predecessor values will allow the algorithm to reconstruct
		  	    //the path from the entrance to the exit, once the exit has been reached.
		  	    neighbour.setPredecessor(curr_hex);
	  		  	  		  
	   	    }  
	  	    
	  	  	  	     	    
	  	    //marked as enqueued and the distance from neighbour to the initial chamber was
	  	    //modified in the previous step, then invoke the updatePriority 
	  	    //method to update the priority that neighbour has in the priority queue.
	  	    if(neighbour.isMarkedEnqueued() && flag)
	  	    {
		  	 queue.updatePriority(neighbour, neighbour.getDistanceToStart()+neighbour.getDistanceToExit(d));	  	    
	  	    }
	  	    else
	  	    {
	  		queue.add(neighbour, neighbour.getDistanceToStart()+neighbour.getDistanceToExit(d));
	  		neighbour.markEnqueued(); //mark neighbour enqueued
	  	    }
	  	    
	  	   	  	    	  		  
	  	 }
	    	}
	  	
	  	
	    }
	    
	  }
    
        
    
    
    	/**
	* If any of the neighbouring chambers has a dragon,return true
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
    
    
    
        
}
