/**
 * Edge
 *
 * @author Darrious Barger
 * @version 1
 */
public class Edge implements Comparable<Edge>
{
	private int i;
	private int j;
	private int weight;

    /**
     * Constructor
     *
     * @param i vertice 1
     * @param j vertice 2
     * @param weight weight of edge
     */
    public Edge(int i, int j, int weight)
    {
        this.i = i;
        this.j = j;
        this.weight = weight;
    }

    /**
     * getI
     *
     * @return i
     */
    public int getI()
    {
    	return i;
    }

    /**
     * getJ
     *
     * @return j
     */
    public int getJ()
    {
    	return j;
    }

    /**
     * getWeight
     *
     * @return weight
     */
    public int getWeight()
    {
    	return weight;
    }

    /**
     * compareTo
     * Compares two edges
     * 
     * @param e edge to compare this edge to
     */
    public int compareTo(Edge e)
    {
    	return this.getWeight() - e.getWeight();
    }
}