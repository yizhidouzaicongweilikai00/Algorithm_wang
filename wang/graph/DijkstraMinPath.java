package com.wang.graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 Dijkstra算法解决的是带权重的有向图上单源最短路径问题，该算法要求所有边的权重都为非负值。
 算法重复从结点集 V-S中选择最短路径估计最小的结点 u ，将 u 加入到集合 S ，然后对所有
 从 u 出发的边进行
松弛操作（相当于遍历选出最小权值）。使用一个最小优先队列 Q 来保存结点集合。
（代码实现中：设置一个标记数组）。迪科斯彻算法使用了广度优先搜索算法。
算法解决的是有向图中单个源点到其他顶点的最短路径问题。

迪克斯拉算法类似于广度优先算法，也类似于计算最小生成树的 Prim 算法。
6 8
0 1 1
0 2 5
0 3 2
1 2 3
1 4 7
2 5 6
3 5 8
4 5 4
 * */
public class DijkstraMinPath {
	public static int[][] matrix = null;
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int n = scan.nextInt();//结点个数
		int m = scan.nextInt();//边的个数
		int start =1;//起点
		int end = 5;//终点
		matrix = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j) {
					matrix[i][j]=0;
				}else {
					matrix[i][j]=Integer.MAX_VALUE;
				}
			}
		}
		for(int i=0;i<m;i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int temp = scan.nextInt();
			matrix[a][b]=temp;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(matrix[i][j]+"     ");
			}
			System.out.println();
		}
		int[] path = new int[n];
		int minpath = dijkstra(matrix,n,start,end,path);
		System.out.println(minpath);
		for(int i=0;i<path.length;i++) {
			System.out.print(path[i]+" ");
		}
		System.out.println();
		//打印路径
		printPath(start,end,path);
	}
	private static void printPath(int x, int y,int[] path) {
		Stack<Integer> stack = new Stack<Integer>();
		int temp=Integer.MAX_VALUE;
		temp=y;
		stack.push(y);
		while(temp!=x) {
			temp=path[temp];
			stack.push(temp);
			
		}
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		
		
	}
	/*
	 * matrix:为邻接矩阵
	 * n:为节点个数
	 * s:为起始节点
	 * t:为终点
	 * path:为路径
	 * 
	 * 
	 * */
	static ArrayList<Integer> list = new ArrayList();
	static int dijkstra(int[][] matrix,int n,int s,int t,int[] path) {
		int i,j,mindistance;//定义
		int w;//临时结点
		int[] d = new int[n];//距离数组
		int[] mark = new int[n];//标记数组
		//初始化标记数组
		for(i=0;i<n;i++) {
			mark[i]=0;
		}
		//初始化距离数组和path数组
		for(i=0;i<n;i++) {
			d[i]=matrix[s][i];
			if(matrix[s][i]<Integer.MAX_VALUE) {
				path[i]=s;
			}else {
				path[i]=-1;
			}
		}
		//从s结点开始出发
		mark[s]=1;//标记s结点
		path[s]=s;
		
		d[s]=0;//s到s的距离为0；
		for(i=1;i<n;i++) {
			mindistance = Integer.MAX_VALUE;
			w=0;
			//找到s到其余5个点之间距离的最小值
			for(j=0;j<n;j++) {
				if(mark[j]==0&&mindistance>=d[j]) {
					mindistance=d[j];
					w=j;
				}
			}
			mark[w]=1;
			
			
			int temp=0;
			for(j=0;j<n;j++) {
				if(mark[j]==0&&matrix[w][j]!=Integer.MAX_VALUE&&d[j]>d[w]+matrix[w][j]) {
					d[j]=d[w]+matrix[w][j];
					path[j]=w;
				
				}
				
			}
		
			
		}
		return d[t];
		
	}
}
