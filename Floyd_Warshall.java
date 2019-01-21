import java.util.Stack;

public class Floyd_Warshall {
	final int INF=1000;
	private int[][] dist;
	private int[][] path;
	public void apsp(UndirectedGraph graph) {
		int n=graph.v;
		//Initialize distance and path matrix
		dist=new int[n][n];
		path=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				//System.out.println(i+" "+j);
				if(i==j) {					
					dist[i][j]=0;
					continue;
				}
				dist[i][j]=INF;
				path[i][j]=-1;
			}
		}
		for(int i=0;i<graph.adj.length;i++) {
			for(Node itr : graph.adj[i]) {
				dist[i][itr.dest]=itr.weight;
				path[i][itr.dest]=i;
			}
		}
		
		//calculating all pair shortest distance
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(dist[i][j]>(dist[i][k]+dist[k][j])) {
						dist[i][j]=dist[i][k]+dist[k][j];
						path[i][j]=k;
					}
				}
			}
		}
	}
	public void printPath(int src,int dest) {
//		for(int i=0;i<9;i++) {
//			for(int j=0;j<9;j++) {
//				System.out.print(dist[i][j]+" ");
//			}
//			System.out.println();
//		}
		int temp=path[src][dest];
		Stack<Integer> st=new Stack<Integer>();
		st.push(dest);
		st.push(temp);
		temp=path[src][temp];
		while(temp!=src) {
			st.push(temp);
			temp=path[src][temp];
		}
		st.push(src);
		while(!st.isEmpty()) {
			System.out.print(st.pop());
			if(!st.isEmpty()) {
				System.out.print("->");
			}
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
        
        Floyd_Warshall ob=new Floyd_Warshall();
        ob.apsp(e);
        ob.printPath(0, 3);
        System.out.println();
        ob.printPath(0, 8);
	}
}
