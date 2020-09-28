
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * program1
 *
 * @author Darrious
 * @version 1
 *
 * */

public class Program1
{
    private Queue<Integer> q; 
    private AdjacencyLists graph;
    private Lab3 list;

    public Program1(String fileName)
    {
        q = new LinkedList();
        list = new Lab3();
        graph = list.initList(fileName);

    }

	public void breadthFirst()
	{
        

		  
	}

    public boolean isTwoColor()
    {
        return false;
    }

    public boolean isConnect()
    { 
       return false;
    }

    public boolean isCyclic()
    {
        return false;
    }

    public boolean isTree()
    {
        int properties = 0;

        if (graph.order() - graph.size() == 1) 
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

    public void printAdjList()
    {
        list.printList(graph);
    }


    public static void main(String[] args)
    {
       
        // Creating objects
        Program1 program = new Program1("somegraph.dat");
        Scanner input = new Scanner(System.in);

        program.printAdjList(); // Print adjacency list

        // Asking user for root if graph is a tree
        if (program.isTree())
        {
	        System.out.println("Please enter the root node:");
	        int root = input.nextInt();
	    }
    }




}
