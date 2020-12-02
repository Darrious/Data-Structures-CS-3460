import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * TSP
 * This class implements an attempted solution to the traveling salesman problem.
 * I produce a minimum spanning tree using the cordinates and distances of graph. 
 * From the MSP, I get the preorder traversal and use this as the best tour.
 * 
 * @author Darrious Barger
 * @version 1
 */
public class TSP
{
	private int[][] cordinates;
	private double[][] distances;
	private MinimumSpanningTree msp;
	private AdjacencyLists adjlist;
	private ArrayList<Edge> minTree;
    private int numCities;

	/**
	 * Constructor
	 * 
	 * @param fileName name of file containing cords
	 * @param n number of cities to visit
	 */
	public TSP(String fileName)
	{
		try
		{
			// Opening file
			File file = new File(fileName);
			Scanner fileScan = new Scanner(file);

			// First line of file is # of cities
			int numC = fileScan.nextInt();
			numCities = numC;

            // Initialize MinimumSpanningTree
			msp = new MinimumSpanningTree(numCities, (numCities*numCities)-numCities);

			cordinates = new int[numCities][2];
			distances = new double[numCities][numCities];

			readCords(fileScan);       // Read cordinates from file
            calculateDistances();      // Calculate distances (weights) between cities (nodes)
                                       // We also add these values to the MSP in this method

            minTree = msp.kruskal();   // Run kruskals algorithms on cordinates and weights to produce MSP
			initAdjList(minTree);      // Add values from MSP to an adjacency list
			DFS dfs = new DFS();       
            
            // Run a depth first search algorithm on the adjacency list
			ArrayList<Integer> dfsResults = dfs.dfs(adjlist, 0, numCities);
			getTourResults(dfsResults); // Print preorder traversal of the minimum spanning tree startubg at node 0
			
			dfsResults = dfs.dfs(adjlist, numCities-1, numCities);
            getTourResults(dfsResults); // Print results starting at the last node
            
            fileScan.close();
		}

        // Catch exceptios=ns
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR: Invalid file name/path");
			System.exit(0);
		}

		catch(Exception e)
		{
			System.out.println("ERROR: Cannot open/read file");
			e.printStackTrace();
			System.exit(0);
		}
		
    }
    
    /**
     * getTourResults
     * Takes the preorder traversal of the minimum spanning tree and prints it. This is the optimal tour we have chosen.
     * 
     * @param dfsResults results of a depth first search (preorder traversal)
     */
	public void getTourResults(ArrayList<Integer> dfsResults)
	{
		double sum = 0;

		System.out.println("\n\nBest tour\n----------");
		for (int i = 0; i < dfsResults.size(); i++)
		{   
			if (i+1 < dfsResults.size())
			{					
				int x = dfsResults.get(i);
				int y = dfsResults.get(i+1);

				sum += distances[x][y];

				System.out.println(x + "->" + y + " | Weight: " + distances[x][y]);
			}
			else
			{
				int x = dfsResults.get(i);
				int y = dfsResults.get(0);

				sum += distances[x][y];
				System.out.println(x + "->" + y + " | Weight: " + distances[x][y]);

			}
        }
        
		System.out.println("Sum: " + sum);
	}


    /**
     * initAdjList
     * Initializes AdjacencyList with values from min spanning tree. The adjacency list is used in the depth
     * first search. The min spanning tree is aquired from the cordinates and weights.
     * 
     * @param mst is the minimum spanning tree of the cities graph
     */
	public void initAdjList(ArrayList<Edge> mst)
	{
		adjlist = new AdjacencyLists(numCities);

		for (int i = 0; i < mst.size(); i++)
		{
			adjlist.addEdge(mst.get(i).getI(), mst.get(i).getJ());
		}
		//printList(adjlist);

	}
	
	/**
     * printList
     * Prints out list using AdjacencyLists neighborIterator function.
     *
     * @param list The adjacency list we initialized earlier using the input files int pairs.
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
	

	/**
	 * readCords
	 *
	 * Reads cordinates from a file into a 2d array
	 *
	 * @param fileScan scanner for the file containing the cordinates
	 */
	public void readCords(Scanner fileScan)
	{
        int i = 0;
        
		// Reading in cordinates
		while (fileScan.hasNext())
		{
			if (fileScan.hasNextInt())
			{
				// If there is an int, there will always be two
				cordinates[i][0] = fileScan.nextInt();
				cordinates[i][1] = fileScan.nextInt();

				//System.out.println(i+". " + cordinates[i][0] + " " + cordinates[i][1]);
				i++;
			}

			else
			{
				fileScan.nextLine();
			}				
		}
	}
	
	/**
	 * calculateDistances
	 *
	 * Calculates the distances between two cities and stores the result
	 * in a 2d array
	 */
	public void calculateDistances()
	{
		for (int i = 0; i < cordinates.length; i++)
		{
			double xi = cordinates[i][0];
			double yi = cordinates[i][1];

			for (int j = 0; j < cordinates.length; j++)
			{
				double xj = cordinates[j][0];
				double yj = cordinates[j][1];

				distances[i][j] = Math.sqrt((xi-xj)*(xi-xj) + (yi-yj)*(yi-yj));
				if (i != j) msp.addEdge(i, j, (int)distances[i][j]);

				//System.out.println(i + " " + j + " " + distances[i][j]);
			}	
		}
	}	

	/**
	 * Main
	 * 
	 * @param args coommand line arguments
	 */
	public static void main(String[] args)
	{	
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nPlease enter a file name containing cordinates..");
        System.out.println("This program will attempt to find two of the best tours:");
        String fileName = input.nextLine();
        
        new TSP(fileName);
        
        input.close();
	}
}