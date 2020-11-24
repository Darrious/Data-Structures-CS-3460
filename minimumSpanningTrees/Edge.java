// Darrious Barger

public class Edge implements Comparable<Edge>
{
	private int i;
	private int j;
	private int weight;

    public getI()
    {
    	return i;
    }

    public getJ()
    {
    	return j;
    }

    public getWeight()
    {
    	return weight;
    }

    
    public int compareTo(Edge e)
    {
    	return e.getWeight() - this.getWeight();
    }
}