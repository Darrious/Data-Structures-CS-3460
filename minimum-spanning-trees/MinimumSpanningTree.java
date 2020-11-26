import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.File;


/**
 * MinimumSpanningTree
 * 
 * @author Darrious Barger
 * @version 1
 */
public class MinimumSpanningTree
{
	private DisjointSets sets;
	private ArrayList<Edge> edgeList;
	private int verts;

	/** Constructor that reads a file of edges and weights and 
	 * puts them in the edges ArrayList
	 *
	 * @param fileName name of file containing edges and weights
	 */
	public MinimumSpanningTree(String fileName)
	{
		try
		{
			// Read file with scanner
			File file = new File(fileName);
			Scanner fileScan = new Scanner(file);

			verts = fileScan.nextInt(); // get # of vertices
			int edges = fileScan.nextInt(); //get # of edges

			
			edgeList = new ArrayList<Edge>();


			// Read every int from file
			while (fileScan.hasNext())
			{
				int i = fileScan.nextInt(); // vertice 1
				int j = fileScan.nextInt(); // vertice 2
				int weight = fileScan.nextInt(); // weight of edge

				edgeList.add(new Edge(i, j, weight));

			}
	
			fileScan.close();
		}

		catch(Exception e)
		{
			System.out.println("Error: Could not read file");
			System.exit(0);
		}	
		
		// Call kruskals algorithm using edges ArrayList and number of vertices
		kruskal(edgeList, verts);		
	}

	/**
	 * kruskal
	 * Uses kruskal's algorithm to find the minimum spanning tree of a graph
	 *
	 * @param edges is an ArrayList of the edges
	 * @param verts is the number of vertices
	 *
	 * @return minimum spanning tree ArrayList 
	 */
	public ArrayList<Edge> kruskal(ArrayList<Edge> edges, int verts)
	{
		// Pass edges to priority queue. This will allow us to retrieve the smallest edge later
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
		ArrayList<Edge> mst = new ArrayList<Edge>();
		sets = new DisjointSets(verts); // Create disjointSet. We will use this to add vertices to the tree and detect cycles

		while (mst.size() != verts - 1)
		{
			Edge e = pq.poll(); // Get smallest edge
			
			int i = sets.find(e.getI()); // Get vertices parent
			int j = sets.find(e.getJ());
	
			if (i!=j) // if vertices do not have the same parent, union them and add to msp arrayList
			{
				sets.union(i, j);
				mst.add(e);
			}
		}

		// Print our result
		sets.print();
		System.out.println("\nEdges Used:\n----------");
		for (int i = 0; i < mst.size(); i++)
		{
			System.out.println(mst.get(i).getI() + "<-->" + mst.get(i).getJ() + " |  Weight:" + mst.get(i).getWeight());
		}

		return mst;
	}


	/**
	 * Main
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a file name/path:");
		String fileName = input.nextLine();
		
		MinimumSpanningTree tree = new MinimumSpanningTree(fileName);
	}
}