package com.wang.graph;

import java.util.Stack;

public class DFS {
	private int total;
	private String[] nodes;
	private int[][] matirx;
 
	public DFS(int total, String[] nodes) {
		this.total = total;
		this.nodes = nodes;
		this.matirx = new int[total][total];
	}
 
	public static void main(String[] args) {
		String[] nodes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		DFS dfs = new DFS(9,nodes);
		dfs.initGrf();
		dfs.printMatrix();
		System.out.println("------ ������ȱ���(�ݹ�)��ʼ ------");
		dfs.resetVisited();
		dfs.dfsRecursive(0);
		System.out.println();
		System.out.println("------ ������ȱ���(�ݹ�)���� ------");
		System.out.println("------ ������ȱ���(ջ)��ʼ ------");
		dfs.resetVisited();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		dfs.matirx[0][0] = 1;
		System.out.print(dfs.nodes[0]);
		dfs.dfsStack(stack);
		System.out.println();
		System.out.println("------ ������ȱ���(ջ)���� ------");
		
		System.out.println("��ȷ��dfs");
		dfs.dfs();
		System.out.println();
		System.out.println("��ȷ�ĵݹ�dfs");
		dfs.recursive();
	}

	private void dfsStack(Stack<Integer> stack) {
		// ����ѷ��ʣ��򷵻�
		int k = -1;
		
		while(!stack.empty()){
			k = stack.peek().intValue();
			boolean needPop = true;
			for(int i = 0; i < this.total; i++){
				if(this.matirx[k][i] == 1 && this.matirx[i][i] == 0){
					stack.push(i);
					this.matirx[i][i] = 1;
					System.out.print(this.nodes[i]);
					needPop = false;
					break;
				}
			}
			if(needPop){
				stack.pop();
			}
		}
	}
	public void dfs() {
		int[][] matrix = this.matirx;
		boolean[] flag = new boolean[total];
		for(int i=0;i<total;i++) {
			flag[i]=false;
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		flag[0]=true;
		System.out.print(this.nodes[0]+" ");
		while(!stack.isEmpty()) {
			int index = stack.peek().intValue();
			boolean needpop = true;
			for(int j=0;j<this.total;j++) {
				if(matrix[index][j]==1&&flag[j]==false) {
					stack.push(j);
					flag[j]=true;
					needpop=false;
					System.out.print(this.nodes[j]+" ");
					break;
				}
			}
			if(needpop) {
				stack.pop();
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
	// ����[i][i]λ�ô���Ԫ��ֵΪ0��0��ʾͼ�еĶ���iδ�����ʣ�1��ʾͼ�еĶ���i�ѱ�����
	private void resetVisited() {
		for (int i = 0; i < this.total; i++) {
			this.matirx[i][i] = 0;
		}
	}
	// ͼ��������ȱ���(�ݹ鷽��)
	private void dfsRecursive(int i) {
		// ����ѷ��ʣ��򷵻�
		if (this.matirx[i][i] == 1) {
			return;
		}
 
		this.matirx[i][i] = 1;
		System.out.print(this.nodes[i]);
 
		for (int j = 0; j < this.total; j++) {
			// i=jʱ��[i][j]��ʾ�ڵ��Ƿ񱻷��ʹ���������dfs
			if (i == j) {
				continue;
			}
 
			if (this.matirx[i][j] == 1) {
				dfsRecursive(j);
			}
		}
	}
	
	//��ȷ�ĵݹ�
	public void recursive() {
		boolean[] flag = new boolean[total];
		for(int i=0;i<total;i++) {
			flag[i]=false;
		}
		dfsRecursive1(0,flag);
	}
	public void dfsRecursive1(int i,boolean[] flag) {
		if(flag[i]==true) {
			return;
		}
		flag[i]=true;
		System.out.print(this.nodes[i]+" ");
		for(int j=0;j<this.total;j++) {
			if(this.matirx[i][j]==1) {
				dfsRecursive1(j,flag);
			}
		}
	}
	//end
	
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
