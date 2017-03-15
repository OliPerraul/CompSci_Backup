import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString){
       
		if (inString.length() > 0)		// if string is not empty
		{
		
	
		  
		   char c = inString.charAt(inString.length()-1); //retrieves last char
		   System.out.print(c);	
		
		   //String outString = inString.substring(0,inString.length()-1); 
		   reversePrint(inString.substring(0,inString.length()-1));
		 


		}
		 //return null as base case
		
    }
    
    
    	public static String reverseString(String inString)
    	{
    	String outString = "";
    	    	if(inString.length() > 0)	
    	    	{
		  outString =  inString.charAt(inString.length()-1)+ reverseString(inString.substring(0,inString.length()-1)); //retrieves last char
		 
	

    		}
		return outString; //return null as base case
	 }
    
   public static boolean checkPalindrome(String s) 
   {
       
      return (s.equals(reverseString(s))? true: false);
       
   }

	    
    public static void main(String[] args){
        String inString = "hello";

		// test reversePrint
		reversePrint(inString);	
		
		
    }
}
