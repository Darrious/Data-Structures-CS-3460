/**
 * OutOfOrder.java
 * 
 */

 
/**
 * Counting out-of-order pairs in an array.
 * 
 * This class implements two different algorithms for
 * computing the number of out-of-order pairs in an int array.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
    

public class OutOfOrder
{

    /**
     * Computes number out-of-order by checking every pair.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder1(int [] a)
    {
        int count = 0;
        
        for (int i = 0; i < a.length; i++)
        {
            for (int j = (i+1); j < a.length; j++)
            {
                if (a[i] > a[j])
                {
                    count++;
                }
            }
        }



        return count;
    }

    /**
     * Computes number out-of-order while doing a mergesort.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder2(int [] a)
    {
        for(int i = 0; i < a.length; i++)
        {
            //System.out.print(a[i] + " ");
        }
        
       // System.out.println("\n");
        


        int count = mergeSort(a, a.length);

        
        
        return count;
        
    }

    
    //Mergesort algorithm copied from www.baeldung.com/java-merge-sort
	public static int mergeSort(int[] a, int n) 
	{
        int count = 0;
		
	    if (n < 2)
	    {
	        return 0;
	    }

	    int mid = n/2;
	    int[] l = new int[mid];
	    int[] r = new int[n - mid];
	 
	    for (int i = 0; i < mid; i++)
	    {
	        l[i] = a[i];
	    }

	    for (int i = mid; i < n; i++)
	    {
	        r[i - mid] = a[i];
	    }

	    
	    count = mergeSort(l, mid);
	    count += mergeSort(r, n - mid);
	 
	    count+=merge(a, l, r, mid, n - mid);

        
        return count;
    }

    
    	
	public static int merge(
		int[] a, int[] l, int[] r, int left, int right)
	{
        int count = 0;
	 
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right)
	    {
	        if (l[i] <= r[j])
	        {
	            a[k++] = l[i++];
               
	        }
	        else 
	        {
	        	if (l[i] != r[j])
	        	{
	         		count += left - i;
	        	}

	            a[k++] = r[j++];
                
	        }
	    }


	    while (i < left)
	    {
	        a[k++] = l[i++];
	    }


	    while (j < right) 
	    {
	        a[k++] = r[j++];
	    }


        return count;
	}

    
    public static void main(String[] args)
    {
        OutOfOrder o = new OutOfOrder();

        int[] a = {5,1,-3,8,2,10,4,3,2,16};

        System.out.println("Algo 1: " + o.countOutOfOrder1(a));
        System.out.println("Algo 2: " + o.countOutOfOrder2(a));
    }
    

}
