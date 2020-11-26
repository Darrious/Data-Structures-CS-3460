import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;



/**
 * AdjacencyLists.
 * 
 * @author Darrious
 * @version 1
 * 
 */
public class AdjacencyLists 
{
     private ArrayList<LinkedList<Integer>> adjList;
     private boolean status;
     private int edges; 


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
            throw new IndexOutOfBoundsException("Invalid Index");
        
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

}  

