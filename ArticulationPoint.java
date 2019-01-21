import java.util.*;
public class ArticulationPoint {
	int time=0;
	//a recursive function that find articulation points using dfs
	//u-->the vertex to be visited next
	//visited[] --> keep track of visited vertex
	//disc[] --> stores discovery times of visited vertex
	//parent[] --> stores parent vertices in dfs tree
	//ap[] --> stores articulation points
	void util(UndirectedGraph graph,int u,boolean visited[],int disc[],int[] low,int parent[],boolean ap[]) {
		//count of children in dfs tree
		int children=0;
		//mark the current node as visited
		visited[u]=true;
		//initialize discovery time and low value
		disc[u]=low[u]=++time;
		Iterator<Node> i=graph.adj[u].iterator();
		while(i.hasNext()) {
			int v=i.next().dest;//v is current adjacent of u
			//if v is not visited yet,then make it a child of u
			//in dfs tree and recur for it
			if(!visited[v]) {
				children++;
				parent[v]=u;
				util(graph,v,visited,disc,low,parent,ap);
				
				//check if the subtree rooted with v has a connection to
				//one of the ancestors of u
				low[u]=Math.min(low[u], low[v]);
				
				//u is an articulation point in following cases
				
				//(1) u is root of dfs tree and has two or more children
				if(parent[u]==-1 && children>1) {
					ap[u]=true;
				}
				//(2) if u is not root and low values of one of its child 
				//is more than discovery value of u
				if(parent[u]!=-1 && low[v]>=disc[u]) {
					ap[u]=true;
				}
			}
			else if(v!=parent[u]) {
				low[u]=Math.min(low[u] ,low[v]);
			} 
			
		}
	}
	void AP(UndirectedGraph graph){
		int v=graph.v;
		boolean visited[]=new boolean[v];
		int disc[]=new int[v];
		int low[]=new int[v];
		int parent[]=new int[v];
		boolean ap[]=new boolean[v];//to store articulation points
		
		//initialize parent and visited and ap(articulation point) arrays
		for(int i=0;i<v;i++) {
			parent[i]=-1;
			visited[i]=false;
			ap[i]=false;
		}
		//call the recursive helper function to find articulation points in 
		//dfs tree rooted with vertex 'i'
		for(int i=0;i<v;i++) {
			if(visited[i]==false) {
				util(graph,i,visited,disc,low,parent,ap);
			}
		}
		//now ap[] contains articulation points
		//print them
		for(int i=0;i<v;i++) {
			if(ap[i])
				System.out.print(i+" ");
		}
		System.out.println();
	}
	public static void main(String sd[]) {
		ArticulationPoint ob=new ArticulationPoint();
		// Create graphs given in above diagrams 
        System.out.println("Articulation points in first graph "); 
        UndirectedGraph g1 = new UndirectedGraph(5); 
        g1.addEdge(1, 0,1); 
        g1.addEdge(0, 2,1); 
        g1.addEdge(2, 1,1); 
        g1.addEdge(0, 3,1); 
        g1.addEdge(3, 4,1); 
        ob.AP(g1); 
        System.out.println(); 
  
        System.out.println("Articulation points in Second graph"); 
        UndirectedGraph g2 = new UndirectedGraph(4);
        g2.addEdge(0, 1,1); 
        g2.addEdge(1, 2,1); 
        g2.addEdge(2,3,1); 
        ob.AP(g2); 
        System.out.println(); 
  
        System.out.println("Articulation points in Third graph "); 
        UndirectedGraph g3 = new UndirectedGraph(7); 
        g3.addEdge(0, 1,1); 
        g3.addEdge(1, 2,1); 
        g3.addEdge(2, 0,1); 
        g3.addEdge(1, 3,1); 
        g3.addEdge(1, 4,1); 
        g3.addEdge(1, 6,1); 
        g3.addEdge(3, 5,1); 
        g3.addEdge(4, 5,1); 
        ob.AP(g3); 
	}
}
