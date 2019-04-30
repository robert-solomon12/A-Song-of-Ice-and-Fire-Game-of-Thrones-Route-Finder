package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistancePath {

	
	
	
	public int pathDistance=0;
	public int pathSafety=0;
	public int pathEasy=0;
	public List<Node<City>> pathList = new ArrayList<>();
	City[] cityList ;
	Node<City>[] nodeList;
	


public static <City> DistancePath findShortestPathDijkstra(Node<City> startNode, City lookingfor)
{
	DistancePath dp=new DistancePath();
	List<Node<City>> encountered = new ArrayList<>(),unencountered=new ArrayList<>();
	startNode.nodeValue=0; 
	unencountered.add(startNode);
	Node<application.City> currentNode; 
	
	
	do {
		currentNode=(Node<application.City>) unencountered.remove(0);
		encountered.add((Node<City>) currentNode);
		
		if(currentNode.node.equals(lookingfor))
		{
			dp.pathList.add(currentNode);
			dp.pathDistance = ((Node<application.City>) currentNode).nodeValue;
			
			while(currentNode!=startNode) {
				boolean foundPrevPathNode=false;
				for(Node<City> n : encountered) { 
					for(Edge e : n.adjList)
						if(e.destNode== currentNode &&  currentNode.nodeValue-e.distance==n.nodeValue){
							dp.pathList.add(0, (Node<application.City>) n);
							currentNode = (Node<application.City>) n;
							foundPrevPathNode=true;
							break;
						}
					if(foundPrevPathNode) break; 
				}
			}
			
			for(Node<City> n : encountered) n.nodeValue=Integer.MAX_VALUE; 
			for(Node<City> n : unencountered) n.nodeValue=Integer.MAX_VALUE; 
			return dp;
			
		}
		
		for(Edge e : currentNode.adjList)
			if(!encountered.contains(e.destNode)) {
				e.destNode.nodeValue=Integer.min(e.destNode.nodeValue,currentNode.nodeValue+e.distance);
			    unencountered.add((Node<City>) e.destNode);
			}
		Collections.sort(unencountered,(n1,n2)->n1.nodeValue-n2.nodeValue);
		
		
		
	}while(!unencountered.isEmpty());
	return null;
}


public static <City> DistancePath findSafestPathDijkstra(Node<City> startNode, City lookingfor)
{
	DistancePath dp=new DistancePath();
	List<Node<City>> encountered = new ArrayList<>(),unencountered=new ArrayList<>();
	startNode.nodeValue=0; 
	unencountered.add(startNode);
	Node<application.City> currentNode; 
	
	
	do {
		currentNode=(Node<application.City>) unencountered.remove(0);
		encountered.add((Node<City>) currentNode);
		
		if(currentNode.node.equals(lookingfor))
		{
			dp.pathList.add(currentNode);
			dp.pathSafety = ((Node<application.City>) currentNode).nodeValue;
			
			while(currentNode!=startNode) {
				boolean foundPrevPathNode=false;
				for(Node<City> n : encountered) { 
					for(Edge e : n.adjList)
						if(e.destNode== currentNode &&  currentNode.nodeValue-e.safety==n.nodeValue){
							dp.pathList.add(0, (Node<application.City>) n);
							currentNode = (Node<application.City>) n;
							foundPrevPathNode=true;
							break;
						}
					if(foundPrevPathNode) break; 
				}
			}
			
			for(Node<City> n : encountered) n.nodeValue=Integer.MAX_VALUE; 
			for(Node<City> n : unencountered) n.nodeValue=Integer.MAX_VALUE; 
			return dp;
			
		}
		
		for(Edge e : currentNode.adjList)
			if(!encountered.contains(e.destNode)) {
				e.destNode.nodeValue=Integer.min(e.destNode.nodeValue,currentNode.nodeValue+e.safety);
			    unencountered.add((Node<City>) e.destNode);
			}
		Collections.sort(unencountered,(n1,n2)->n1.nodeValue-n2.nodeValue);
		
		
		
	}while(!unencountered.isEmpty());
	return null;
}


public static <City> DistancePath findEasiestPathDijkstra(Node<City> startNode, City lookingfor)
{
	DistancePath dp=new DistancePath();
	List<Node<City>> encountered = new ArrayList<>(),unencountered=new ArrayList<>();
	startNode.nodeValue=0; 
	unencountered.add(startNode);
	Node<application.City> currentNode; 
	
	
	do {
		currentNode=(Node<application.City>) unencountered.remove(0);
		encountered.add((Node<City>) currentNode);
		
		if(currentNode.node.equals(lookingfor))
		{
			dp.pathList.add(currentNode);
			dp.pathEasy = ((Node<application.City>) currentNode).nodeValue;
			
			while(currentNode!=startNode) {
				boolean foundPrevPathNode=false;
				for(Node<City> n : encountered) { 
					for(Edge e : n.adjList)
						if(e.destNode== currentNode &&  currentNode.nodeValue-e.easy==n.nodeValue){
							dp.pathList.add(0, (Node<application.City>) n);
							currentNode = (Node<application.City>) n;
							foundPrevPathNode=true;
							break;
						}
					if(foundPrevPathNode) break; 
				}
			}
			
			for(Node<City> n : encountered) n.nodeValue=Integer.MAX_VALUE; 
			for(Node<City> n : unencountered) n.nodeValue=Integer.MAX_VALUE; 
			return dp;
			
		}
		
		for(Edge e : currentNode.adjList)
			if(!encountered.contains(e.destNode)) {
				e.destNode.nodeValue=Integer.min(e.destNode.nodeValue,currentNode.nodeValue+e.easy);
			    unencountered.add((Node<City>) e.destNode);
			}
		Collections.sort(unencountered,(n1,n2)->n1.nodeValue-n2.nodeValue);
		
		
		
	}while(!unencountered.isEmpty());
	return null;
}
}
