
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;


/**
 * program1
 *
 * @author Darrious
 * @version 1
 *
 * */

public class Program1
{
    private Queue<Integer> q; // Creating queue
    private AdjacencyLists adjList;
    private Lab3 lab3; //Creating Lab3 object, this will be used to
                       //intialize the adjacency list
    private boolean properties[];

    public Program1(String fileName)
    {
        lab3 = new Lab3();
        q = new LinkedList();
        adjList = lab3.initList(fileName); // Using aLab3 to initialize adjList
        properties = breadthFirst();
    }

	public boolean[] breadthFirst()
	{
        int node = 0;
        boolean visited[] = new boolean[adjList.order()];
        boolean properties[] = {true, true};

        visited[0] = true;

        q.add(0);

        while (q.size() != 0)
        {
            node = q.remove();
            System.out.println(node + " ");

            Iterator<Integer> it = adjList.neighborIterator(node);
            while(it.hasNext())
            {
                int adjNode = it.next();

                if (!visited[adjNode])
                {
                    visited[adjNode] = true;
                    q.add(adjNode);
                }
            }

        }

        // Check if graph is connected
        for (boolean x : visited)
        {
            //System.out.println(x);
            if (x == false) properties[0] = false;
        }

        return properties;

		  
	}

    public boolean isTwoColor()
    {
        return false;
    }

    public boolean isConnect()
    { 
        System.out.println("Connected: " + properties[0]);
        return (properties[0]);
    }

    public boolean isCyclic()
    {
        return false;
    }

    public boolean isTree()
    {
        int properties = 0;

        if (adjList.order() - adjList.size() == 1) 
        {
            properties++;
        }

        if (isConnect())
        {
            properties++;
        }

        if (!isCyclic())
        {
            properties++;
        }


        return properties > 1;
    
    }

    /**
     * Prints out list using AdjacencyLists neighborIterator function.
     *
     * @param list The adjacency list we initialized earlier using the input files int pairs.
     *
     *
     */ 
    public void printList()
    {
        Iterator<Integer> it; 
        
        for (int i = 0; i < adjList.order(); i++)
        {
            it = adjList.neighborIterator(i);
            System.out.print("\n" + (i) + ": "); // Prints current vertex
            
            while (it.hasNext())
            {
                System.out.print(it.next() + " "); // Prints edges of current vertex
            }            
        }

        System.out.println("\nThere are " + adjList.size() + " edges.");
    }


    public static void main(String[] args)
    {
       
        // Creating objects
        Program1 program = new Program1("somegraph.dat");
        Scanner input = new Scanner(System.in);

        program.isTree(); // Print adjacency list

        // Asking user for root if adjList is a tree
        /*if (program.isTree())
        {
	        System.out.println("Please enter the root node:");
	        int root = input.nextInt();
	    }*/
    }




}
