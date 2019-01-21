import java.util.LinkedList;

class Node{
	int dest;
	int weight;
	Node(int dest,int weight){
		this.dest=dest;
		this.weight=weight;
	}
}
public class UndirectedGraph{
	int v;
	LinkedList<Node>[] adj;
	UndirectedGraph(int v){
		this.v=v;
		adj=new LinkedList[v];
		for(int i=0;i<v;i++) {
			adj[i]=new LinkedList<Node>();
		}
	}
	void addEdge(int src,int dest,int weight) {
		adj[src].addLast(new Node(dest,weight));
		adj[dest].addLast(new Node(src,weight));
	}
}