import java.util.*;

public class StronglyConnected {
	public int getUnvisited(int curr,boolean[] visited,DirectedGraph graph) {
		for(Node itr : graph.adj[curr]) {
			if(!visited[itr.dest]) {
				visited[itr.dest]=true;
				return itr.dest;
			}
		}
		return -1;
	}
	public DirectedGraph reverseGraph(DirectedGraph graph) {
		DirectedGraph g=new DirectedGraph(graph.v);
		for(int i=0;i<graph.v;i++) {
			for(Node itr : graph.adj[i]) {
				g.addEdge(itr.dest, i, itr.weight);
			}
		}
		return g;
	}
	public void printComponent(int start,boolean[] visited,DirectedGraph graph) {
		Stack<Integer> st=new Stack<Integer>();
		st.push(start);
		visited[start]=true;
		while(!st.isEmpty()) {
			int curr=st.peek();
			int unvisited=getUnvisited(curr,visited,graph);
			if(unvisited==-1) {
				System.out.print(st.pop()+" ");
			}
			else {
				st.push(unvisited);
			}
		}
	}
	public void find_strongly_connected(DirectedGraph graph){
		int v=graph.v;
		Stack<Integer> first=new Stack<Integer>();
		Stack<Integer> temp=new Stack<Integer>();
		boolean[] visited=new boolean[v];
		temp.push(0);
		visited[0]=true;
		while(!temp.isEmpty()) {
			int curr=temp.peek();
			int unvisited=getUnvisited(curr,visited,graph);
			if(unvisited==-1) {
				first.push(temp.pop());
				if(temp.isEmpty()) {
					for(int i=0;i<visited.length;i++) {
						if(!visited[i]) {
							temp.push(i);
							break;
						}
					}
				}
			}
			else {
				temp.push(unvisited);
			}
		}
		
		temp.clear();
		for(int i=0;i<v;i++) {
			visited[i]=false;
		}
		DirectedGraph rev=reverseGraph(graph);
		while(!first.isEmpty()) {
			int curr=first.pop();
			if(!visited[curr]) {
				printComponent(curr,visited,rev);
				System.out.println();
			}
		}
	}
	
	public static void main(String sd[]) {
		DirectedGraph g=new DirectedGraph(5);
		 g.addEdge(1, 0,1); 
	        g.addEdge(0, 2,1); 
	        g.addEdge(2, 1,1); 
	        g.addEdge(0, 3,1); 
	        g.addEdge(3, 4,1);
	     StronglyConnected ob=new StronglyConnected();
	     ob.find_strongly_connected(g);
	}
}
