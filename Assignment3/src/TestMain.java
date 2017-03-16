
public class TestMain 
{

    public static void main(String[] args) 
    {
	DLinkedPriorityQueue<Integer> qu = new DLinkedPriorityQueue<Integer>();
	
	qu.add(1, 5);
		
	qu.add(2, 23);
	
	qu.add(3, 13);
	
	qu.add(4, 44);
//	
	qu.add(5, 1);
	
	qu.add(11, -551);
//	
	//qu.add("student5", 1);
	
	System.out.println(qu.toString());

	qu.updatePriority(3, -3209);
	
	System.out.println(qu.toString());
	
	
	qu.updatePriority(11, 3209);
	
	System.out.println(qu.toString());
	
	Integer in = qu.removeMin();
	
	System.out.println(in);
	
	in = qu.removeMin();
	
	System.out.println(in);
	
	in = qu.removeMin();
	
	System.out.println(in);
	
	in = qu.removeMin();
	
	System.out.println(in);
    	
	in = qu.removeMin();
	
	System.out.println(in);
	
	in = qu.removeMin();
	
	System.out.println(in);
	
//	
//	qu.updatePriority("student2", 1);
//	
//	System.out.println(qu.toString());
	

    }

}
