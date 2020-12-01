import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;



/**
 * AdjacencyLists.
 * 
 * @author Darrious Barger
 * @version 1
 * 
 */
public class AdjacencyLists 
{
     private ArrayList<LinkedList<Integer>> adjList;
     private boolean status;
     private int edges; 


 
    public AdjacencyLists(String fileName)
    {
        ListFunctions lf = new ListFunctions();
        lf.initList(fileName);
    }

    /**
     * Creates AdjacencyLists for an undirected graph with the specified 
     * number of vertices and no edges.
     * 
     * @param order 
     *            the number of vertices in the graph 
     *
     * Allocates memory for an ArrayList of LinkedList of Integer, and
     *      makes an empty list for each vertex.
     */
    public AdjacencyLists(int order)
    {
        adjList = new ArrayList<LinkedList<Integer>>(order);
        status = false;


        for (int i = 0; i < order; i++)
        {
            LinkedList<Integer> row = new LinkedList<Integer>();
            adjList.add(row);

        }

    }

    /**
     * Creates AdjacencyLists for a graph with the specified 
     * number of vertices and no edges.
     * 
     * @param order 
     *            the number of vertices in the graph 
     * @param directed 
     *            true if the graph is directed, false otherwise 
     *
     * Allocates memory for an ArrayList of LinkedList of Integer, and
     *      makes an empty list for each vertex.
     */
    public AdjacencyLists(int order, boolean directed)
    {
        adjList = new ArrayList<LinkedList<Integer>>(order);
        status = directed;

        for (int i = 0; i < order; i++)
        {
            LinkedList<Integer> row = new LinkedList<Integer>();
            adjList.add(row);
        }



    }
   

    /**
     * Adds an edge x, y to the graph. 
     * 
     * @param x
     * @param y 
     *      x,y are the endpoints of the edge
     * 
     *  If the graph is undirected, x is added to y's list and y is
     *     added to x's list.  
     *  If the graph is directed, y is added to x's list
     *
     *  Throws an IndexOutOfBoundsException if 
     *    x >= order or y >= order() 
     */

    public void addEdge(int x, int y)
    {
        
        if (y >= order() || x >= order())
        {
            throw new IndexOutOfBoundsException("ERROR: Make sure the number of vertices in your file is correct.");
        
        }

        
        edges++; // Increment edges
        if (status)
        {
             adjList.get(x).add(y); // If directed
        }
        else 
        {

            adjList.get(x).add(y); // If undirected
            adjList.get(y).add(x);

        } 

    }

    /**
     * Access the neighbors of a given vertex.
     * 
     * @param vertex
     *    a vertex in the graph 
     * 
     * @return an iterator to the neighbors of vertex, throws
     *   an IndexOutOfBoundsException if vertex >= order() 
     *
     */
    
    public Iterator<Integer> neighborIterator(int vertex)
    {
        Iterator<Integer> it = adjList.get(vertex).iterator();
        return it;
    }

    public LinkedList<Integer> getVertexList(int vert)
    {
        try
        {
           return adjList.get(vert);
        }

        catch (Exception e)
        {
            System.out.println("ERROR");
            return null;
        }


    }

    /**
     * Accessor method for the number of vertices.
     * 
     * @return the number of vertices in the graph
     */
    public int order()
    {
        return adjList.size();
    }

    /**
     * Accessor method for the number of edges.
     * 
     * @return the number of edges in the graph
     */
    public int size()
    {
        return edges;
    }

    /**
     * Accessor method for if the graph is directed or not
     *
     * @return if graph is directed
     */
    public boolean status()
    {
        return status;
    }


     /**
     * Test
     *
     * @author Darrious
     * @version 1
     */
    private class ListFunctions
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
}  

