/** Phone class
 *  represents a phone listing
 *  @author CS1027 for Lab
 * @param <T>
 */

public class Phone implements Comparable<Phone>{
  
  private String name;
  private String phone;
    
  public Phone(){
    name = "";
    phone = "";
  }
  public Phone(String name, String phone){
    this.name = name;
    this.phone = phone;
  }
  public String getName(){
    return name;
  }
  public String getPhone(){
    return phone;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setPhone(String phone){
    this.phone = phone;
  }
  public String toString(){
    return (name + " " + phone);
  }
  public boolean equals(Phone other)
  {
	  return (name == other.name)&&(phone == other.phone);
	  
  }
  
  // Phone entries are compared by name
  //Returns a negative integer, zero, or a positive integer as this 
  //object is less than, equal to, or greater than the specified object
  public int compareTo(Phone obj) 
  {
      //The result is positive if the first string is lexicographically greater than the
      //second string else the result would be negative.
   
      //by phone
    return (this.getPhone().compareTo(obj.getPhone()));
    	// by name
	 //return (this.getName().compareTo(obj.getName()));
  }
  
  
}