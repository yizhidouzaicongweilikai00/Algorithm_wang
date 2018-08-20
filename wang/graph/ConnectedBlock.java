package com.wang.graph;

import java.util.Scanner;
import java.util.Stack;

/*
南阳理工学院校园里有一些小河和一些湖泊，现在，我们把它们通一看成水池，
假设有一张我们学校的某处的地图，这个地图上仅标识了此处是否是水池，
现在，你的任务来了，请用计算机算出该地图中共有几个水池。

输入
每一组数据都是先输入该地图的行数m(0<m<100)与列数n(0<n<100)，
然后，输入接下来的m行每行输入n个数，表示此处有水还是没水（1表示此处是水池，
0表示此处是地面）

输出
输出该地图中水池的个数。
要注意，每个水池的旁边（上下左右四个位置）如果还是水池的话的话，
它们可以看做是同一个水池。

输入：
5 5
1 1 1 1 0
0 0 1 0 1
0 0 0 0 0
1 1 1 0 0
0 0 1 1 1
输出：
3
 * */
public class ConnectedBlock {
	public static int[][] matrix=null;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();//m行
		int n = scan.nextInt();//n列
		//得到一个矩阵块
		matrix = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j]=scan.nextInt();
			}
		}
		int count =0;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(matrix[i][j]!=0) {
					dfs(i,j);
					count++;
					
				}
			}
		}
		System.out.println(count);
	
		
	}
	//一个点移动的四个方向，x表示行数，表上下，y表示列数，表左右
	//i=0;往下移动；i=1;往上移动；i=2；往右移动；i=3；往左移动。
	//将上下左右为1的元素全部置为0；
	//这里用递归实现深度优先遍历。。
	static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[][] flag = {{0,},};
	private static void dfs(int x, int y) {
		matrix[x][y]=0;
		for(int i=0;i<move.length;i++) {
			int nx=x+move[i][0];
			int ny=y+move[i][1];
			if(nx<0||nx>=matrix.length||ny<0||ny>=matrix[0].length) {
				continue;
			}
			if(matrix[nx][ny]!=0) {
				dfs(nx,ny);
			}
		}	
	}
	
	//如何使用非递归实现深度优先遍历连通块
	
	
}
