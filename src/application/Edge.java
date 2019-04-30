package application;

public class Edge {

	public Node<City> destNode;
	public int distance;
	public int safety;
	public int easy;
	
	public Edge(Node<City>destNode, int distance,int safety,int easy)
	{
		this.destNode=destNode;
		this.distance=distance;
		this.safety=safety;
		this.easy=easy;
	}
}
