import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
	private Map<Long,Node> map=new HashMap<>();
	class Node{
		long data;
		Node parent;
		int rank;
	}
	public void makeSet(long data) {
		Node node=new Node();
		node.data=data;
		node.parent=node;
		node.rank=0;
		map.put(data, node);
	}
	//returns true if data1 and data2 are in different set before union else false
	public boolean union(long data1,long data2) {
		Node node1=map.get(data1);
		Node node2=map.get(data2);
		
		Node parent1=findSet(node1);
		Node parent2=findSet(node2);
		
		if(parent1.data == parent2.data) {
			return false;
		}
		//else whoever's rank is higher becomes parent of other
		if(parent1.rank >= parent2.rank) {
			//increment rank only when if both sets have same rank
			parent1.rank=(parent1.rank==parent2.rank)? parent1.rank+1:parent1.rank;
			parent2.parent=parent1;
		}
		else {
			parent1.parent=parent2;
		}
		return true;
	}
	private Node findSet(Node node) {
		Node parent=node.parent;
		if(parent==node) {
			return parent;
		}
		node.parent=findSet(node.parent);
		return node.parent;
	}
}
