
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

    private boolean properties[]; //This array will hold 2 boolean values that represetn
                                  //if the graph is connected and if it is cyclic.
    private boolean treeStatus;


    public Program1(String fileName)
    {
        treeStatus = false;
        lab3 = new Lab3();
        q = new LinkedList();
        adjList = lab3.initList(fileName); // Using Lab3 to initialize adjList
        properties = breadthFirst(0); // We will pass the breadthFirst method a 0 as the root node. Right now we are trying to determine if it is a tree.
    }

	public boolean[] breadthFirst(int root)
	{
        int depth = 0;
        int parentNode = 0;
        int childCount = 0;
        int depthArray[] = new int[adjList.order()];
        int childArray[] = new int[adjList.order()];
        int parentArray[] = new int[adjList.order()];
        boolean visited[] = new boolean[adjList.order()];
        boolean properties[] = {true, false, true}; //This array will hold 2 boolean values that represetn
                                                    //if the graph is connected and if it is cyclic.
        boolean colorArray[] = new boolean[adjList.order()];

        depthArray[root] = 0; // The root has depth zero
        colorArray[root] = true;
        q.add(root); // Add root to the queue

        

        while (q.size() != 0)
        {
            
            childCount = 0;  
            root = q.remove(); // Get node from the queue

            Iterator<Integer> it = adjList.neighborIterator(root);
           
            // This loop will visit all children of the vertex
            while(it.hasNext())
            {
                // This gets the next child of the current vertex
                int adjNode = it.next();
               

                // If node (child) hasn't been visited, mark it as visited and put it 
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
                else
                {
                    if(colorArray[root] == colorArray[adjNode])
                    {
                            properties[2] = false;
                    }

                    parentArray[root] = adjNode; // In a tree, the only nodes in our adjacency list will be
                                                 // the children and the parent. We are marking the only node we have already seen
                                                 // as the parent.
                }
            
            }

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
        if (!treeStatus) // I only have it display before treeStatus is decided because if it is a tree, it ends up printing twice
                         // since I run my BFS algorithm twice
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
        }


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

    public boolean isTwoColor()
    {
        return properties[2];
    }

    public boolean isConnect()
    { 
        //System.out.println("Connected: " + properties[0]);
        return properties[0];
    }

    public boolean isCyclic()
    {
        //System.out.println("Cyclic: " + properties[1]);

        return properties[1];
    }

    public boolean isTree()
    {
        int properties = 0;


        if (adjList.order() - adjList.size() == 1) 
        {
            //System.out.println("Property 1: true");
            properties++;
        } 

        if (isConnect())
        {
            properties++;
        }
        /**
        if (!isCyclic())
        {
            properties++;
        }*/


        if (properties > 1) treeStatus = true;
        return treeStatus;
    
    }




    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome! This program will display some information about a graph. \nPlease input the name of a data file that contains an adjacency list:");
        String file = input.nextLine();

        Program1 program = new Program1(file);

        // Asking user for root if adjList is a tree
        if (program.isTree())
        {
	        System.out.println("\n\nThis graph is a tree and is two-colorable. What is the root node?");
	        int root = input.nextInt();
            program.breadthFirst(root);
	    }
        else
        {
            System.out.println("\nThis graph is NOT a tree.");
            if (program.isTwoColor())
            {
                System.out.println("This graph is two-colorable.");

            }

            else
            {
                System.out.println("This graph is NOT two-colorable.");
            }
            


        }
    }




}
