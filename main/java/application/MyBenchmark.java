/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

@Measurement(iterations = 5,time=1)
@Warmup(iterations = 5,time=1)
@Fork(value=1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)

public class MyBenchmark {
	private City myCity [] = new City[10];
	@SuppressWarnings("unchecked")
	private Node<City> nodeList [] = new Node[10];
	@Setup
	public void initilize() {
		
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

		myCity[0] = a;
		myCity[1] = b;
		myCity[2] = c;
		myCity[3] = d;
		myCity[4] = e;
		myCity[5] = f;
		myCity[6] = g;
		myCity[7] = h;
		myCity[8] = i;
		myCity[9] = i;

		Node<City> a1 = new Node<>(a);
		Node<City> b1 = new Node<>(b);
		Node<City> c1 = new Node<>(c);
		Node<City> d1 = new Node<>(d);
		Node<City> e1 = new Node<>(e);
		Node<City> f1 = new Node<>(f);
		Node<City> g1 = new Node<>(g);
		Node<City> h1 = new Node<>(h);
		Node<City> i1 = new Node<>(i);

		nodeList[0] = a1;
		nodeList[1] = b1;
		nodeList[2] = c1;
		nodeList[3] = d1;
		nodeList[4] = e1;
		nodeList[5] = f1;
		nodeList[6] = g1;
		nodeList[7] = h1;
		nodeList[8] = i1;
		nodeList[9] = i1;

	}

	public static void main(String[]args) throws RunnerException, IOException{
		Main.main(args);
	}
	
	
	public static  List<Node<City>> findPathDepthFirst(Node<City> from, List<Node<City>> encountered, City lookingfor){
		List<Node<City>> result;
		if(from.node.equals(lookingfor)) { //Found it
			result=new ArrayList<>(); //Create new list to store the path info (any List implementation could be used)
			result.add(from); //Add the current node as the only/last entry in the path list
			return result; //Return the path list
		}

		if(encountered==null) 
			encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from);

		for(Edge ed : from.adjList) {
			if(!encountered.contains(ed.destNode)) {
				result=findPathDepthFirst(ed.destNode,encountered,lookingfor);
				if(result!=null) { //Result of the last recursive call contains a path to the solution node
					result.add(0,from); //Add the current node to the front of the path list
					return result; //Return the path list
				}
			}
		}
		return null;
	}
	
	@Benchmark
	public void findingPathDepthFirst() {
		Node<City> n = new Node<City>();
		City c = new City("Harrenhal", 910.50243570366, 1538.13039134556);
		for(int i= 0;i<nodeList.length;i++) {
			n = nodeList[0];
		}
		List<Node<City>> path = findPathDepthFirst(n,null,c);
		if( path != null) {
			for(Node<City> route : path) {
				//drawPath(d,path,s);
				System.out.println(route.node.getCityName() + " ->");
			}
		}

	}
		
	public static void traverseGraphDepthFirst(Node<City> from, List<Node<City>> encountered ){
		System.out.println(from.node.cityName);
		if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from);
		for(Node<City> adjNode : encountered)
			if(!encountered.contains(adjNode)) traverseGraphDepthFirst(adjNode, encountered );
	}
	
	/*
	 * Testing for access every node object that is a city attribute stored in ArrayList and performing some operations like printing them.
	 */
	
	//=============BENCHMARKING FOR TRAVERSINGGRAPH TO STILL WRITE\

	/*
	 * 
	 */
	@Benchmark
	public void testFindAllPathsDepthFirst() {
		Node<City> nc = new Node<City>();
		City n = new City("Harrenhal", 910.50243570366, 1538.13039134556);
		List<Node<City>> encountered = null;

		for(int i= 0;i<nodeList.length;i++) {
			nc = nodeList[0];
		}

		findAllPathsDepthFirst(nc,encountered,n);

	}
	
	public static List<List<Node<City>>> findAllPathsDepthFirst(Node<City> from, List<Node<City>> encountered, City lookingfor){
		List<List<Node<City>>> result=null, temp2;
		if(from.node.equals(lookingfor)) { //Found it
			List<Node<City>> temp=new ArrayList<>(); //Create new single solution path list
			temp.add(from); //Add current node to the new single path list
			result=new ArrayList<>(); //Create new "list of lists" to store path permutations
			result.add(temp); //Add the new single path list to the path permutations list
			return result; //Return the path permutations list
		}
		if(encountered==null) encountered=new ArrayList<>(); //First node so create new (empty) encountered list
		encountered.add(from); //Add current node to encountered list
		for(Edge ed : from.adjList){
			if(!encountered.contains(ed.destNode)) {
				temp2=findAllPathsDepthFirst(ed.destNode,new ArrayList<>(encountered),lookingfor); //Use clone of encountered list
				//for recursive call!
				if(temp2!=null) { //Result of the recursive call contains one or more paths to the solution node
					for(List<Node<City>> x : temp2) //For each partial path list returned
						x.add(0,from); //Add the current node to the front of each path list
					if(result==null) result=temp2; //If this is the first set of solution paths found use it as the result
					else result.addAll(temp2); //Otherwise append them to the previously found paths
				}

			}

		}
		return result;
	}
	}

