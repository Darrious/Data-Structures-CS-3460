
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;


/**
 * Test
 *
 * @author Darrious
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
        
        try
        {
            File file = new File(fileName);
            pairs = new Scanner(file);
            
            vertNum = pairs.nextInt(); //First int in the file assigned to vertNum
            
            if (pairs.nextInt() == 1)
            {
                dir = true; // If second int in file is 1, dir is true
            }
              
        }

        catch (Exception e)
        {
            e.printStackTrace();

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
   
   
}
