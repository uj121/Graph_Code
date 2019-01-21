import java.util.PriorityQueue;
import java.util.Comparator;

class Node1{
	int key;
	int vertex;
}
class Comp implements Comparator<Node1>{
	public int compare(Node1 a,Node1 b) {
		return a.key-b.key;
	}
}

public class Dijkstra {
	
	public void sssp(UndirectedGraph graph,int src) {
		int n=graph.v;
		boolean[] included=new boolean[n];
		int[] parent=new int[n];
		Node1[] e=new Node1[n];
		
		for(int i=0;i<n;i++) {
			e[i]=new Node1();
			e[i].key=Integer.MAX_VALUE;
			e[i].vertex=i;
			parent[i]=-1;
		}
		
		parent[src]=src;
		e[src].key=0;
		
		PriorityQueue<Node1> queue=new PriorityQueue<Node1>(n,new Comp());
		for(int i=0;i<n;i++) {
			queue.add(e[i]);
		}
		
		while(!queue.isEmpty()) {
			Node1 temp=queue.poll();
			included[temp.vertex]=true;
			for(Node itr : graph.adj[temp.vertex]) {
				 if(!included[itr.dest]) {
					 if((temp.key+itr.weight)<e[itr.dest].key) {
						 queue.remove(e[itr.dest]);
						 e[itr.dest].key=temp.key+itr.weight;
						 queue.add(e[itr.dest]);
						 parent[itr.dest]=temp.vertex;
					 }
				 }
			}
		}
		for(int i=0;i<n;i++) {
			System.out.println(src+"->"+i+" weight="+e[i].key);
		}
	}
	public static void main(String sd[]) {
		int v=9;
		UndirectedGraph e=new UndirectedGraph(v);
		e.addEdge( 0, 1, 4); 
	    e.addEdge( 0, 7, 8); 
	    e.addEdge( 1, 2, 8); 
	    e.addEdge( 1, 7, 11); 
	    e.addEdge( 2, 3, 7); 
	    e.addEdge( 2, 8, 2); 
	    e.addEdge( 2, 5, 4); 
	    e.addEdge( 3, 4, 9); 
	    e.addEdge( 3, 5, 14); 
	    e.addEdge( 4, 5, 10); 
	    e.addEdge( 5, 6, 2); 
	    e.addEdge( 6, 7, 1); 
	    e.addEdge( 6, 8, 6); 
	    e.addEdge( 7, 8, 7); 
        
        Dijkstra ob=new Dijkstra();
        ob.sssp(e,0);  //single source shortest path
	}
}
