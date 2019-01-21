
public class Bellman {
	void sssp(DirectedGraph graph,int src) {
		int n=graph.v;
		int[] distance=new int[n];
		for(int i=0;i<n;i++) {
			distance[i]=Integer.MAX_VALUE;
		}
		distance[src]=0;
		//to rum for v-1 times
		//if run for v times , find -ve weight cycle
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<graph.adj.length;j++) {
				if(distance[j]!=Integer.MAX_VALUE) {
					for(Node itr : graph.adj[j]) {
						if(distance[itr.dest]>(distance[j]+itr.weight)) {
							distance[itr.dest]=distance[j]+itr.weight;
						}
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			System.out.println(src+"->"+i+" weight="+distance[i]);
		}
	}
	public static void main(String sd[]) {
		int v=9;
		DirectedGraph e=new DirectedGraph(v);
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
        
        Bellman ob=new Bellman();
        ob.sssp(e,0);  //single source shortest path
	}
}
