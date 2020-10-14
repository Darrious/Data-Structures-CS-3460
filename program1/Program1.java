
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;


/**
 * Program1
 *
 * @author Darrious Barger
 * @version 1
 *
 * */

public class Program1
{
    private Queue<Integer> q; // Creating queue
    private AdjacencyLists adjList;
    private Lab3 lab3; //Creating Lab3 object, this will be used to
                       //intialize the adjacency list

    private boolean properties[]; //This array will hold 2 boolean values that represent
                                  //if the graph is connected and if it is cyclic.

    private boolean treeStatus; //Tells us if graph is tree. Since we run the BFS twice, the first pass tells us 
                                //if the graph is a tree. Then, on the second pass we can determine certain properties
                                //about the tree. This variable is used in the BFS. If it is true, we start checking these 
                                //tree properties.

    /**
     * Constructor initializes adjacency list and other variables. Also runs breadth first
     * algorithm with 0 as the root
     *
     * @param fileName name of the file containing an adjacency list
     *
     */
    public Program1(String fileName)
    {
        treeStatus = false;
        lab3 = new Lab3();
        q = new LinkedList();
        adjList = lab3.initList(fileName); // Using Lab3 to initialize adjList

        if(!adjList.status()) // If it is undirected, run breadthFirst algorithm
        {
            properties = breadthFirst(0); // We will pass the breadthFirst method a 0 as the root node. Right now we are trying to determine if it is a tree.
        }
    }


    /**
     * This function runs a breadth first algorithm on an adjacency list.
     * It determines various properties about the graph.
     *
     * @param root is the root node
     * @return a boolean array that contains properties (if it is two-colorable and if it is connected)
     */
	public boolean[] breadthFirst(int root)
	{
        int depth = 0;
        int parentNode = 0;
        int childCount = 0;
        int depthArray[] = new int[adjList.order()];
        int childArray[] = new int[adjList.order()];
        int parentArray[] = new int[adjList.order()];
        boolean visited[] = new boolean[adjList.order()];
        boolean properties[] = {true, true, false}; //This array will hold 3  boolean values that represent
                                             //if the graph is connected, two-colorable, and cyclic.
        boolean colorArray[] = new boolean[adjList.order()];
        depthArray[root] = 0; // The root has depth zero
        colorArray[root] = true;
        q.add(root); // Add root to the queue

        while (q.size() != 0)
        {
            int visCount = 0; // For each node, sees how many of the adjacent nodes have already been seen.
            childCount = 0;  
            root = q.remove(); // Get node from the queue
    
            Iterator<Integer> it = adjList.neighborIterator(root);
           
            // This loop will visit all children of the vertex
            while(it.hasNext())
            {
                // This gets the next child of the current vertex
                int adjNode = it.next();
                
                // If adjacent node hasn't been visited, mark it as visited and put it 
                // on the queue
                if (!visited[adjNode])
                {
                    if (colorArray[root])
                    {
                        colorArray[adjNode] = false;
                    }
                    else 
                    {
                        
                        colorArray[adjNode] = true;
                    }
                    
                    

                    childCount += 1; //This node has another child
                    visited[adjNode] = true; //We mark this node as visited
                    q.add(adjNode); // Add node to queue
                }

                // If the adjacent node has been visited
                else
                {
                    visCount++;
                    if(colorArray[root] == colorArray[adjNode])
                    {
                            properties[1] = false;
                    }

                    parentArray[root] = adjNode; // In a tree, the only nodes in our adjacency list will be
                                                 // the children and the parent. We are marking the only node we have already seen
                                                 // as the parent.
                }
            
            }
            if (visCount > 1) properties[2] = true; // If a node as two or more nodes in its adjacency list that have already been seen
                                                    // it is a cyclic graph
            childArray[root] = childCount; // Store the number of children for this vertex
            depthArray[root] = 1 + depthArray[parentArray[root]]; // Store depth as one more than parent
            visited[root] = true; // Mark vertex as visited

        }

        // Check if graph is connected
        for (boolean x : visited)
        {
            if (x == false) properties[0] = false; //properties[0] represents if graph is connected
        }



        // Displaying colors of each vertex for user
        // I only have it display before treeStatus is decided because if it is a tree, it ends up printing twice
        // since I run my BFS algorithm twice
        /*
        if (!treeStatus)
        {
            System.out.println("\n\n---------------");
            System.out.println("|Vertex Colors|");
            System.out.println("---------------");
            int h = 0;
            for (boolean x : colorArray)
            {
                System.out.print("Vertex: " + h + " | ");
                if (x) System.out.println("Red");
                else System.out.println("Black");

                h++;
            }
        }*/


        // If the graph is a tree, print its properties
        if (treeStatus)
        {
            for (int i = 0; i < adjList.order(); i++)
            {
                System.out.println("\n\n-----------");
                System.out.println("|Vertex: " + i + "|");
                System.out.println("-----------");

                System.out.print("Parent: " + parentArray[i] + " |");
                System.out.print(" Depth: " + (depthArray[i] - 1) + " |");
                System.out.print(" Number of children: " + childArray[i]);

            }
        }

        return properties;

		  
	}

    /**
     * Lets us know if the graph is two-colorable
     *
     * @return if it is two-colorable
     */

    public boolean isTwoColor()
    {
        return properties[1];
    }


    /**
     * Boolean function tells us if graph is connected
     *
     * @return if graph is connected
     */
    public boolean isConnect()
    { 
        return properties[0];
    }
    
    /**
     * Boolean function that tells us if graph is cyclic
     *
     * @return if graph is cyclic
     */ 
    public boolean isCyclic()
    {
        return properties[2];    
    }

    /**
     * Boolean function tells us if is a tree using isConnect() and if number of nodes is one more than
     * number of edges
     *
     * @return if graph is tree
     */    
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


        /* 
        if (!isCyclic()) // Checking this is redundant. If the other two are true, this one has to be as well.
        {
            properties++;
        }*/


        if (properties > 1) treeStatus = true;

        return treeStatus;
    
    }



    /**
     * Main function runs the menu and performs a second BFS if the graph is a tree.
     *
     * @param args command line arguments
     */

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("|Welcome! This program will display some information about an UNDIRECTED graph.| ");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("\nPlease input the name of a data file that contains an adjacency list:");
        
        String file = input.nextLine();

        Program1 program = new Program1(file);

        if (!program.adjList.status()) // Only check if the graph is a tree if it is undirected
        {
            // Asking user for root if adjList is a tree
            if (program.isTree())
            {
                System.out.println("\n------------------");
                System.out.println("|Graph Properties|\n------------------");
                System.out.println("Tree: true");
                System.out.println("Two-colorable: true");
                System.out.println("Connected: true ");
                System.out.println("Cyclic: false");
             
    	        System.out.println("\n\nThis graph is a tree. What is the root node?");
    	        int root = input.nextInt();
                program.breadthFirst(root);
    	    }
            else
            {
                    System.out.println("\n------------------");
                    System.out.println("|Graph Properties|\n------------------");
                    System.out.println("Tree: false");
                    System.out.println("Two-colorable: " + program.isTwoColor());
                    System.out.println("Connected: " + program.isConnect());
                    System.out.println("Cyclic: " + program.isCyclic());
             
            }
        }

        else // If the graph is undirected, inform the user.
        {
            System.out.println("\nThis graph is directed and is NOT a tree.");
        }
    }

}
