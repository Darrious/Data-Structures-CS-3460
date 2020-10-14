
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;


/**
 * Test
 *
 * @author Darrious Barger
 * @version 1
 */
public class Lab3
{


    /**
     * Accepts a file name as an argument.
     * This file should contain integer pairs on each line that represent edges.
     *      
     * @param fileName The path of a file
     * @return The adjacency list that was initialized using the pairs in the file
     */
    public AdjacencyLists initList(String fileName)
    {
        // Variable
        Boolean dir = false;                        // Directed or undirected
        int vertNum = 0;                            // Number of vertices
        int edgeCount = 0;
        Scanner pairs = new Scanner(System.in);     // We will use this scanner to read the file
        AdjacencyLists list;                        // We will return this list
        File file = null; 
        
         
        try
        {
            file = new File(fileName);


            System.out.println("Exists: " + file.exists());
            System.out.println("Readable: " + file.canRead());
           
            pairs = new Scanner(file);
            
            vertNum = pairs.nextInt(); //First int in the file assigned to vertNum
            
            if (pairs.nextInt() == 1)
            {
                dir = true; // If second int in file is 1, dir is true
            }
              
        }

        catch (Exception e)
        {
            System.out.println("ERROR: Make sure the file name/path are valid.");
            e.printStackTrace();
            pairs.close();
        }

        list = new AdjacencyLists(vertNum, dir); // Create adjacency list using variables


        // This loop adds all int pairs to the adjacency list. Exits when it finds (-1, -1)
        int i = 0;
        int j = 0;        
        
        while (true)
        {
            i = pairs.nextInt();
            j = pairs.nextInt();
                
            if (i == -1 && j == -1)
            {
                break;
            }

            list.addEdge(i, j);
            
        }


        return list;

    }

    /**
     * Prints out list using AdjacencyLists neighborIterator function.
     *
     * @param list The adjacency list we initialized earlier using the input files int pairs.
     *
     *
     */ 
    public void printList(AdjacencyLists list)
    {
        Iterator<Integer> it; 

        for (int i = 0; i < list.order(); i++)
        {
            it = list.neighborIterator(i);
            System.out.print("\n" + (i) + ": "); // Prints current vertex

            while (it.hasNext())
            {
                System.out.print(it.next() + " "); // Prints edges of current vertex

            }

        }


        System.out.println("\nThere are " + list.size() + " edges.");

    }
   
   
}
