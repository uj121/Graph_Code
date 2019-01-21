import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Kruskal {
	class Edge{
		int start;
		int end;
		int weight;
		Edge(int start,int end,int weight){
			this.start=start;
			this.end=end;
			this.weight=weight;
		}
	}
	class Comp implements Comparator<Edge>{
		public int compare(Edge a,Edge b) {
			return a.weight-b.weight;
		}
	}
	ArrayList<Edge> allEdges(UndirectedGraph graph){
		ArrayList<Edge> edges=new ArrayList<Edge>();
		for(int i=0;i<graph.adj.length;i++) {
			for(Node itr:graph.adj[i]) {
				edges.add(new Edge(i,itr.dest,itr.weight));
			}
		}
		return edges;
	}
	void mst(UndirectedGraph graph) {
		int n=graph.v;
		ArrayList<Edge> edges=allEdges(graph);
		Collections.sort(edges, new Comp());
		DisjointSet ob=new DisjointSet();
		for(int i=0;i<n;i++) {
			ob.makeSet(i);
		}
		ArrayList<Edge> res=new ArrayList<Edge>();
		for(int i=0;i<edges.size();i++) {
			Edge curr=edges.get(i);
			if(ob.union(curr.start,curr.end)){
				res.add(curr);
			}
		}
		for(int i=0;i<res.size();i++) {
			System.out.println(res.get(i).start+"-"+res.get(i).end);
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
        
        Kruskal ob=new Kruskal();
        ob.mst(e);
	}
}
