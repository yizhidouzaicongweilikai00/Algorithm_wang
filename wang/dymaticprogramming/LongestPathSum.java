package com.wang.dymaticprogramming;

import java.util.Scanner;

public class LongestPathSum {
	
	public static void main(String[] args) {
		int[][] maxSum = null;//表示从下往上递推到达该点的路径最大和。
		int  n=0;//行数
		int[][] mat = null;//使用二维数组存储三角形	
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();//总行数
		mat = new int[n+1][n+1];
		maxSum = new int[n+1][n+1];
		//输入矩阵
		System.out.println("请输入：");
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				mat[i][j]=scan.nextInt();//输入矩阵中第i行第j列位置的值
				if(i==n){
					//第n行的点就是其路径的最大和
					maxSum[i][j] = mat[i][j];//起始条件
				}
			}
		}
		//动态规划公式，求出每个点的路径最大和，依然使用递归公式，这里应该叫做动态转移公式
		for(int i=n-1;i>=1;i--) {//从第n-1行开始向上递推
			for(int j=1;j<=i;j++) {
				//递推时，依次递推当前行的每个点
				maxSum[i][j]=Math.max(maxSum[i+1][j], maxSum[i+1][j+1])+mat[i][j];
			}
		}
		//输出结果
		System.out.println(maxSum[1][1]);
		
	}
}
