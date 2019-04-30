  package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

public class Node<City> {
	public City node;
    
	
	public List<Edge> adjList = new ArrayList<Edge>();
	public List<Node<City>> nodeListNode = new ArrayList<>();

	public int nodeValue = Integer.MAX_VALUE;
	//public Edge edg;	
	  	
	  public Node(City node) {
		this.node = node;
	}
	  
	  
	  public Node() {
		  
	  }

		//	@SuppressWarnings("unchecked")
		public void connectToNodeDirected(Node<City>destNode, int distance,int safety, int easy)
	  	{
	  		adjList.add(new Edge((Node<application.City>) destNode,distance,safety,easy));
	  		nodeListNode.add(destNode);
	  	}
	  	
	  //	@SuppressWarnings("unchecked")
		public void connectToNodeUndirected(Node<City>destNode, int distance,int safety, int easy)
	  	{
	  		adjList.add(new Edge((Node<application.City>) destNode,distance,safety,easy));
	  		destNode.adjList.add(new Edge((Node<application.City>) destNode,distance,safety,easy));
	  		nodeListNode.add(destNode);
	  	}
	  }
	  	

//	private void connectCities() {
//		for (Edge edg : ) {
//			edg.startNode.connectCities(edg.destNode);
//		}
//	}
//
//	private void connectRoutes() {
//		for (City c : cities) {
//			c.connectRoutes(routes);
//		}
//	}
//
//	public List<City> getCities() {
//		return cities;
//	}
//	
//	public List<Route> getRoutes() {
//		return routes;
//	}
	
	
	
	
//	public cityRoute getRoute(City from, City to) {
//		cityRoute route = null;
//		for (cityRoute edg : routes) {
//			if (( == startNode || r.fromCity == to) && (r.toCity == from || r.toCity == to)) {
//				route = r;
//				break;
//			}
//		}
//		return route;
//	}

//	public Node(City node) {
//		this.node = node;
//        
//	}
//	
	
	


	
	
	
