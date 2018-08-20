package com.wang.graph;

import java.util.Scanner;
/*
 * 
���·���C����·�����⣺

�������������1�ų��е�5�ų��е����·������ 
����ͼ��
5 8
1 2 2
1 5 10
2 3 3
2 5 7
3 1 4
3 4 4
4 5 5
5 3 3
*/

/*
5 20
1 2 4
1 3 8
1 4 4
1 5 5
2 1 4
2 3 8
2 4 2
2 5 5
3 1 8 
3 2 8
3 4 8
3 5 8
4 1 4
4 2 2
4 3 8
4 5 5
5 1 5
5 2 5
5 3 8
5 4 5



 * 
 * */
public class MinPath {
	static int min = Integer.MAX_VALUE;
	static int[][] edges = null;
	static int[] book = null;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		edges = new int[n+1][n+1];
		book = new int[n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) {
					edges[i][j]=0;
				}else {
					edges[i][j]=Integer.MAX_VALUE;
				}
				
			}
		}
		for(int i=1;i<=m;i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			edges[a][b]=c;
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(edges[i][j]+"  ");
			}
			System.out.println();
		}
		book[1]=1;
		dfs(1,0);
		System.out.println(min);
	}
	private static void dfs(int curnode, int distance) {
		 /**
         * �����ǰ·������֮ǰ�ҵ�����Сֵ����ֱ�ӷ���
         * */
		if(distance>min) {
			return;
		}
		
		if(curnode==edges.length-1) {
			if(distance<min) {
				min=distance;
				return;
			}
		}
		/**
         * ��ǰ�㵽��������֮�����ͨ���ǻ�δ��ӽ���ʱ������ִ��
         * */
		for(int i=1;i<edges.length;i++) {
			if(edges[curnode][i]!=Integer.MAX_VALUE&&book[i]==0) {
				book[i]=1;
				dfs(i,distance+edges[curnode][i]);
				/*
				 * ����
				 * */
				book[i]=0;
			}
		}
		return;
	}
}
