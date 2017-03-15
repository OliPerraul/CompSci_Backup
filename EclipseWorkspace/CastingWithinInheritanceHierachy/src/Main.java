
public class Main 
{

    public static void main(String[] args) 
    {
	Parent p = new Parent();
	((Child)p).doSomething();
	

    }

}


class Parent
{
    //base class
}

class Child extends Parent
{
 
    
    public void doSomething()
    {
	//print
	System.out.print("hello");
	
    }
    
}
