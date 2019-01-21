class Solution{
	private final static int M=(int)Math.pow(10, 9);
	public int calc(int[] x,int[] y,int p, int mask) {
		if(p==0) {
			return mask==0 ? 0:M;
		}
		int res=M;
		for(int i=0;i<x.length-1;i++) {
			if((mask & 1<<i)==1) {
				int dist=Math.abs(x[i]-x[p])+Math.abs(y[i]-y[p]);
				res=Math.min(res, calc(x,y,i,mask^(1<<i))+dist);
			}
		}
		return res;
	}
	public static void main(String sd[]) {
		int n=6;
		Solution ob=new Solution();
		int[] xx= {100,70,30,10,90,50,0};
		int[] yy= {100,40,10,5,70,20,0};
		System.out.println((1<<2)-1);
		System.out.println(ob.calc(xx, yy, 6, (1<<6)-1));
	}
}