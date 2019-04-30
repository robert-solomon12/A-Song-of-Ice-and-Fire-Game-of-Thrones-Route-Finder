package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	private City[] cityList  = new City[10];
	@SuppressWarnings("unchecked")
	private Node<City> nodelist [] = new Node[10];
   
//	private static Controller control;

	public void setUp() {
	City a = new City("Castle Black", 963.635818309064, 530.110264371092);
	City b = new City("The Dreadfort", 1026.68305627889, 808.213014253298);
	City c = new City("Winterfell",820,850);
	City d = new City("Barrow Town", 670.564197559144, 1027.94592708038);
	City e = new City("White Harbor",930,1050);

	City f = new City("Moat Cailin",820,1100);

	City g = new City("The Twins",790,1320);

	City h = new City("The Eyrie",1040,1390);
	City i = new City("Riverrun",750,1490);
	City j = new City("Harrenhal", 910.50243570366, 1538.13039134556);
	
	
	cityList[0] = a;
	cityList[1] = b;
	cityList[2] = c;
	cityList[3] = d;
	cityList[4] = e;
	cityList[5] = f;
	cityList[6] = g;
	cityList[7] = h;
	cityList[8] = i;
	cityList[9] = j;
	
	Node<City> a1 = new Node<City>(a);
	Node<City> b1 = new Node<City>(b);
	Node<City> c1 = new Node<City>(c);
	Node<City> d1 = new Node<City>(d);
	Node<City> e1 = new Node<City>(e);
	Node<City> f1 = new Node<City>(f);
	Node<City> g1 = new Node<City>(g);
	Node<City> h1 = new Node<City>(h);
	Node<City> i1 = new Node<City>(i);
	Node<City> j1 = new Node<City>(j);
	
	
	nodelist [0] = a1;
	nodelist [1] = b1;
	nodelist [2] = c1;
	nodelist [3] = d1;
	nodelist [4] = e1;
	nodelist [5] = f1;
	nodelist [6] = g1;
	nodelist [7] = h1;
	nodelist [8] = i1;
	nodelist [9] = j1;
	
	
	a1.connectToNodeUndirected(b1,5,1,2);
	b1.connectToNodeUndirected(c1,10,5,5);
	b1.connectToNodeUndirected(d1,10,5,5);
	b1.connectToNodeUndirected(e1,10,5,5);
	c1.connectToNodeUndirected(d1,6,1,1);
	c1.connectToNodeUndirected(e1,6,1,1);
	d1.connectToNodeUndirected(e1,3,1,1);
	e1.connectToNodeUndirected(f1,13,4,5);
	f1.connectToNodeUndirected(g1,13,4,5);
	g1.connectToNodeUndirected(i1,13,4,5);
	g1.connectToNodeUndirected(j1,13,4,5);
	g1.connectToNodeUndirected(h1,6,4,5);
	
	}

	@Test
	public void testNumberOfNodes() {
		assertEquals(9,nodelist.length);
	}
	
	@Test
	public void testNumberOfFalseNodes() {
		assertEquals(3,nodelist.length);
	}
	
	@Test
	public void testNumberOfCityNodes() {
		assertEquals(7,cityList.length);
	}
	
	@Test
	public void testNumberOfFalseCityNodes() {
		assertEquals(10,cityList.length);
	}

}

	
