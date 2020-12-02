import java.util.ArrayList;
import java.util.PriorityQueue;

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
	public MinimumSpanningTree(int v, int e)
	{
		verts = v;
		edgeList = new ArrayList<Edge>();		
	}

	public void addEdge(int i, int j, int weight)
	{
		edgeList.add(new Edge(i, j, weight));
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
	public ArrayList<Edge> kruskal()
	{
		// Pass edges to priority queue. This will allow us to retrieve the smallest edge later
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edgeList);
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

		/* Print our result
		sets.print();
		System.out.println("\nEdges Used:\n----------");
		for (int i = 0; i < mst.size(); i++)
		{
			System.out.println(mst.get(i).getI() + "<-->" + mst.get(i).getJ() + " |  Weight:" + mst.get(i).getWeight());
		}
		*/
		return mst;
	}
}