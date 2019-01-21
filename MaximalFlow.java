import java.util.*;

public class MaximalFlow {
	public boolean bfs(int[][] graph,int s,int d,int[] parent) {
		if(s==d) {
			return true;
		}
		int n=graph.length;
		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visited=new boolean[n];
		q.add(s);
		visited[s]=true;
		parent[s]=-1;
		while(!q.isEmpty()) {
			int curr=q.poll();
			//System.out.println(curr);
			for(int i=0;i<n;i++) {
				if(!visited[i] && graph[curr][i]>0) {
					q.add(i);
					parent[i]=curr;
					visited[i]=true;
				}
			}
		}
		return visited[d];
	}
	public int maximal(int[][] graph,int s,int d) {
		int u,v,n=graph.length;
		int[][] rgraph=new int[n][n];
		for(u=0;u<n;u++) {
			for(v=0;v<n;v++) {
				rgraph[u][v]=graph[u][v];
				//System.out.print(rgraph[u][v]);
			}
		}
		int[] parent=new int[n];
		int max_flow=0;
		while(bfs(rgraph,s,d,parent)) {
			//System.out.println("------------------------------------");
			int flow=Integer.MAX_VALUE;
			for(v=d;v!=s;v=parent[v]) {
				flow=Math.min(flow, graph[parent[v]][v]);
			}
			max_flow+=flow;
			for(v=d;v!=s;v=parent[v]) {
				rgraph[v][parent[v]]+=flow;
				rgraph[parent[v]][v]-=flow;
			}
			//System.out.println(flow);
		}
		return max_flow;
		
	}
	public static void main(String sd[]) {
		int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0}, 
            {0, 0, 10, 12, 0, 0}, 
            {0, 4, 0, 0, 14, 0}, 
            {0, 0, 9, 0, 0, 20}, 
            {0, 0, 0, 7, 0, 4}, 
            {0, 0, 0, 0, 0, 0} 
          }; 
          MaximalFlow ob=new MaximalFlow();
          System.out.println(ob.maximal(graph, 0, 5));
	}
}
