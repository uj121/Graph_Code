import java.util.PriorityQueue;
import java.util.Comparator;


public class Prims{
	UndirectedGraph graph;
	Prims(UndirectedGraph g){
		graph=g;
	}
	class Node1{
		int key;
		int vertex;
	}
	class Comp implements Comparator<Node1>{
		public int compare(Node1 a,Node1 b) {
			return a.key-b.key;
		}
	}
	void prim_mst(int src) {
		int n=graph.v;
		boolean[] mstset=new boolean[n];
		Node1[] e=new Node1[n];
		int[] parent=new int[n];
		
		for(int i=0;i<n;i++) {
			e[i]=new Node1();
		}
		for(int i=0;i<n;i++){
			mstset[i]=false;
			e[i].key=Integer.MAX_VALUE;
			e[i].vertex=i;
			parent[i]=-1;
		}
		
		mstset[src]=true;
		e[src].key=0;
		parent[src]=src;
		
		PriorityQueue<Node1> queue=new PriorityQueue<Node1>(n,new Comp());
		for(int i=0;i<n;i++) {
			queue.add(e[i]);
		}
		while(!queue.isEmpty()) {
			Node1 t=queue.poll();
			mstset[t.vertex]=true;
			for(Node itr : graph.adj[t.vertex]) {
				if(!mstset[itr.dest]) {
					if(e[itr.dest].key>itr.weight) {
						queue.remove(e[itr.dest]);
						e[itr.dest].key=itr.weight;
						queue.add(e[itr.dest]);
						parent[itr.dest]=t.vertex;
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			if(i==src) {
				continue;
			}
			System.out.println(parent[i]+"-"+i);
		}
	}
	public static void main(String[] sd) {
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
        
        Prims ob=new Prims(e);
        ob.prim_mst(0);
	}
}