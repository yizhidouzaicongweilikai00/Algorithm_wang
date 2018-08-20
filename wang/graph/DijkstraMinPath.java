package com.wang.graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 Dijkstra�㷨������Ǵ�Ȩ�ص�����ͼ�ϵ�Դ���·�����⣬���㷨Ҫ�����бߵ�Ȩ�ض�Ϊ�Ǹ�ֵ��
 �㷨�ظ��ӽ�㼯 V-S��ѡ�����·��������С�Ľ�� u ���� u ���뵽���� S ��Ȼ�������
 �� u �����ı߽���
�ɳڲ������൱�ڱ���ѡ����СȨֵ����ʹ��һ����С���ȶ��� Q �������㼯�ϡ�
������ʵ���У�����һ��������飩���Ͽ�˹���㷨ʹ���˹�����������㷨��
�㷨�����������ͼ�е���Դ�㵽������������·�����⡣

�Ͽ�˹���㷨�����ڹ�������㷨��Ҳ�����ڼ�����С�������� Prim �㷨��
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
		int n = scan.nextInt();//������
		int m = scan.nextInt();//�ߵĸ���
		int start =1;//���
		int end = 5;//�յ�
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
		//��ӡ·��
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
	 * matrix:Ϊ�ڽӾ���
	 * n:Ϊ�ڵ����
	 * s:Ϊ��ʼ�ڵ�
	 * t:Ϊ�յ�
	 * path:Ϊ·��
	 * 
	 * 
	 * */
	static ArrayList<Integer> list = new ArrayList();
	static int dijkstra(int[][] matrix,int n,int s,int t,int[] path) {
		int i,j,mindistance;//����
		int w;//��ʱ���
		int[] d = new int[n];//��������
		int[] mark = new int[n];//�������
		//��ʼ���������
		for(i=0;i<n;i++) {
			mark[i]=0;
		}
		//��ʼ�����������path����
		for(i=0;i<n;i++) {
			d[i]=matrix[s][i];
			if(matrix[s][i]<Integer.MAX_VALUE) {
				path[i]=s;
			}else {
				path[i]=-1;
			}
		}
		//��s��㿪ʼ����
		mark[s]=1;//���s���
		path[s]=s;
		
		d[s]=0;//s��s�ľ���Ϊ0��
		for(i=1;i<n;i++) {
			mindistance = Integer.MAX_VALUE;
			w=0;
			//�ҵ�s������5����֮��������Сֵ
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
