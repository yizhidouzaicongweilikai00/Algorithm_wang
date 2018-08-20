package com.wang.graph;

import java.util.Scanner;
import java.util.Stack;

/*
������ѧԺУ԰����һЩС�Ӻ�һЩ���������ڣ����ǰ�����ͨһ����ˮ�أ�
������һ������ѧУ��ĳ���ĵ�ͼ�������ͼ�Ͻ���ʶ�˴˴��Ƿ���ˮ�أ�
���ڣ�����������ˣ����ü��������õ�ͼ�й��м���ˮ�ء�

����
ÿһ�����ݶ���������õ�ͼ������m(0<m<100)������n(0<n<100)��
Ȼ�������������m��ÿ������n��������ʾ�˴���ˮ����ûˮ��1��ʾ�˴���ˮ�أ�
0��ʾ�˴��ǵ��棩

���
����õ�ͼ��ˮ�صĸ�����
Ҫע�⣬ÿ��ˮ�ص��Աߣ����������ĸ�λ�ã��������ˮ�صĻ��Ļ���
���ǿ��Կ�����ͬһ��ˮ�ء�

���룺
5 5
1 1 1 1 0
0 0 1 0 1
0 0 0 0 0
1 1 1 0 0
0 0 1 1 1
�����
3
 * */
public class ConnectedBlock {
	public static int[][] matrix=null;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();//m��
		int n = scan.nextInt();//n��
		//�õ�һ�������
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
	//һ�����ƶ����ĸ�����x��ʾ�����������£�y��ʾ������������
	//i=0;�����ƶ���i=1;�����ƶ���i=2�������ƶ���i=3�������ƶ���
	//����������Ϊ1��Ԫ��ȫ����Ϊ0��
	//�����õݹ�ʵ��������ȱ�������
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
	
	//���ʹ�÷ǵݹ�ʵ��������ȱ�����ͨ��
	
	
}
