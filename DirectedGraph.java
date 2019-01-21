import java.util.LinkedList;

public class DirectedGraph extends UndirectedGraph{
	DirectedGraph(int v){
		super(v);
	}
	void addEdge(int src,int dest,int weight) {
		adj[src].addLast(new Node(dest,weight));
		//adj[dest].addLast(new Node(src,weight));
	}
}