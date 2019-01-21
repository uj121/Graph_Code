import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;


public class TopologicalSort{
	public int unvisited(int src,DirectedGraph graph,boolean[] visited) {
		
		for(Node itr : graph.adj[src]) {
			if(!visited[itr.dest]) {
				//System.out.println("un"+itr.dest);
				return itr.dest;
			}
		}
		return -1;
	}
	public void sort(DirectedGraph graph){
		int n=graph.v;
		boolean[] visited=new boolean[n];
		Stack<Integer> st=new Stack<Integer>();
		Stack<Integer> res=new Stack<Integer>();
		st.push(1);
		visited[1]=true;
		while(!st.isEmpty()) {
			int curr=st.peek();
			int unvis=unvisited(curr,graph,visited);
			if(unvis==-1) {
				res.push(st.pop());
				if(st.isEmpty()) {
					for(int i=0;i<n;i++) {
						if(!visited[i]) {
							st.push(i);
							visited[i]=true;
							break;
						}
					}
				}
			}
			else{
				visited[unvis]=true;
				st.push(unvis);
			}
		}
		while(!res.isEmpty()){
			System.out.print(res.pop()+" ");
		}
	}
	public static void main(String sd[]) {
		int v=8;
		DirectedGraph e=new DirectedGraph(v);
		e.addEdge( 0,2,1); 
	    e.addEdge( 1,2,1); 
	    e.addEdge( 1,3,1); 
	    e.addEdge( 2,4,1);
	    e.addEdge( 3,5,1); 
	    e.addEdge( 4,5,1); 
	    e.addEdge( 5,6,1); 
	    e.addEdge( 5,7,1); 
	    
	    
	    TopologicalSort ob=new TopologicalSort();
	    ob.sort(e);
	}
}
