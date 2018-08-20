package com.wang.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS{
	private int total;
	private String[] nodes;
	private int[][] matirx;
 
	public BFS(int total, String[] nodes) {
		this.total = total;
		this.nodes = nodes;
		this.matirx = new int[total][total];
	}
	
	public static void main(String[] args) {
		String[] nodes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		BFS bfs = new BFS(9,nodes);
		bfs.initGrf();
		bfs.printMatrix();
		
		bfs.bfsrecursive();
	}

	private  void bfsrecursive() {
		boolean[] flag = new boolean[total];
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<this.total;i++) {
			flag[i]=false;
		}
		queue.add(0);
		flag[0]=true;
		System.out.print(this.nodes[0]);
		while(!queue.isEmpty()) {
			int index = queue.poll();
			for(int j=0;j<this.total;j++) {
				if(this.matirx[index][j]==1&&flag[j]==false) {
					queue.add(j);
					flag[j]=true;
					System.out.print(this.nodes[j]);
				}
			}
		}
	}

	private void printMatrix() {
		System.out.println("----------------- matrix -----------------");
		System.out.println("---0-1-2-3-4-5-6-7-8--");
		System.out.println("---A-B-C-D-E-F-G-H-I--");
		for (int i = 0; i < this.total; i++) {
			System.out.print(" " + this.nodes[i] + "|");
			for (int j = 0; j < this.total; j++) {
				System.out.print(this.matirx[i][j] + "-");
			}
			System.out.print("\n");
		}
		System.out.println("----------------- matrix -----------------");
	}
	private void initGrf() {
		// A-B, A-D, A-E
		this.matirx[0][1] = 1;
		this.matirx[1][0] = 1;
		this.matirx[0][3] = 1;
		this.matirx[3][0] = 1;
		this.matirx[0][4] = 1;
		this.matirx[4][0] = 1;
		// B-C
		this.matirx[1][2] = 1;
		this.matirx[2][1] = 1;
		// C-F
		this.matirx[2][5] = 1;
		this.matirx[5][2] = 1;
		// D-E, D-G
		this.matirx[3][4] = 1;
		this.matirx[4][3] = 1;
		this.matirx[3][6] = 1;
		this.matirx[6][3] = 1;
		// E-F, E-H
		this.matirx[4][5] = 1;
		this.matirx[5][4] = 1;
		this.matirx[4][7] = 1;
		this.matirx[7][4] = 1;
		// F-H, F-I
		this.matirx[5][7] = 1;
		this.matirx[7][5] = 1;
		this.matirx[5][8] = 1;
		this.matirx[8][5] = 1;
		// G-H
		this.matirx[6][7] = 1;
		this.matirx[7][6] = 1;
		// H-I
		this.matirx[7][8] = 1;
		this.matirx[8][7] = 1;
		
	}
	
	
}