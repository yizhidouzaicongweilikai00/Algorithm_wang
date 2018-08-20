package com.wang.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 求图最小生成树的PRIM算法
 * 基本思想：假设N=(V,{E})是联通网，TE是N上的最想生成树中的变得集合。算法从U={u0}(u0属于V)，
 * TE={}开始，重复执行下述操作：在所有的u属于U，v属于V-U的边（u，v）属于E中找到一条代价最小
 * 的边（u0，v0）并入集合TE，同事v0并入U，直至U=V为止。此时TE中必有n-1条边，则T=(V,{TE})
 * 为N的最小生成树。
 * @param  graph  图
 * @param start 开始节点
 * @param n     图中节点数
 */
/*
6 11
1 2 4
1 4 3
1 3 2
2 5 3
2 3 5
2 4 4
3 4 1
3 6 2
4 5 6
4 6 2
5 6 4

案例2
4 4
1 2 5
1 3 2
1 4 6
3 4 2

 * */
public class PrimMinTree {
	static int[][] map = null;
	static int max = 100;
	static HashSet<Integer> set = new HashSet<Integer>();
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();//输入节点总数
		int m = scan.nextInt();//输入边的总数，及边的权值
		map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) {
					map[i][j]=0;
				}else{
					map[i][j]=max;
				}
			}
		}
		for(int i=1;i<=m;i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int temp = scan.nextInt();
			map[a][b]=temp;
			map[b][a]=temp;
		}
		//输入完成
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(map[i][j]+"       ");
			}
			System.out.println();
		}
		int[] low = new int[n+1];//用来存储到达某点的最小路径
		int[] flag = new int[n+1];//用来记录已经记录了哪个点
		for(int i =1;i<=n;i++) {
			low[i]=max;
			flag[i]=0;
		}
		
		//假设从第一个点1出发求最小生成树，则得到一组值，我们取其中最小的点并把它加入到flag中
		low[1]=0;
		int pos=1;
		int minpos = max;
		set.add(pos);
		list.add(pos);
		while(set.size()<n) {
			for(Integer position:set) {
				for(int j=1;j<=n;j++) {
					if(pos!=j&&!set.contains(j)) {
						low[j]=map[position][j]+low[position];	
					}	
				}
				minpos=findMin(low, position);
			}
			set.add(minpos);
			list.add(minpos);
		}
		for(Integer index:list) {
			System.out.print(index+" ");
		}	
		
		
	}

	private static int findMin(int[] low, int position) {
		int min=Integer.MAX_VALUE;
		int pos=0;
		for(int i=1;i<low.length;i++) {
			if(i!=position&&!set.contains(i)) {
				if(low[i]<min) {
					min=low[i];
					pos=i;
				}
			}
		}
		return pos;
	}
}
