package com.wang.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * ��ͼ��С��������PRIM�㷨
 * ����˼�룺����N=(V,{E})����ͨ����TE��N�ϵ������������еı�ü��ϡ��㷨��U={u0}(u0����V)��
 * TE={}��ʼ���ظ�ִ�����������������е�u����U��v����V-U�ıߣ�u��v������E���ҵ�һ��������С
 * �ıߣ�u0��v0�����뼯��TE��ͬ��v0����U��ֱ��U=VΪֹ����ʱTE�б���n-1���ߣ���T=(V,{TE})
 * ΪN����С��������
 * @param  graph  ͼ
 * @param start ��ʼ�ڵ�
 * @param n     ͼ�нڵ���
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

����2
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
		int n = scan.nextInt();//����ڵ�����
		int m = scan.nextInt();//����ߵ����������ߵ�Ȩֵ
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
		//�������
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(map[i][j]+"       ");
			}
			System.out.println();
		}
		int[] low = new int[n+1];//�����洢����ĳ�����С·��
		int[] flag = new int[n+1];//������¼�Ѿ���¼���ĸ���
		for(int i =1;i<=n;i++) {
			low[i]=max;
			flag[i]=0;
		}
		
		//����ӵ�һ����1��������С����������õ�һ��ֵ������ȡ������С�ĵ㲢�������뵽flag��
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
