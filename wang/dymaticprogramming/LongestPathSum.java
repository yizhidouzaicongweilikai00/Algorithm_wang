package com.wang.dymaticprogramming;

import java.util.Scanner;

public class LongestPathSum {
	
	public static void main(String[] args) {
		int[][] maxSum = null;//��ʾ�������ϵ��Ƶ���õ��·�����͡�
		int  n=0;//����
		int[][] mat = null;//ʹ�ö�ά����洢������	
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();//������
		mat = new int[n+1][n+1];
		maxSum = new int[n+1][n+1];
		//�������
		System.out.println("�����룺");
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				mat[i][j]=scan.nextInt();//��������е�i�е�j��λ�õ�ֵ
				if(i==n){
					//��n�еĵ������·��������
					maxSum[i][j] = mat[i][j];//��ʼ����
				}
			}
		}
		//��̬�滮��ʽ�����ÿ�����·�����ͣ���Ȼʹ�õݹ鹫ʽ������Ӧ�ý�����̬ת�ƹ�ʽ
		for(int i=n-1;i>=1;i--) {//�ӵ�n-1�п�ʼ���ϵ���
			for(int j=1;j<=i;j++) {
				//����ʱ�����ε��Ƶ�ǰ�е�ÿ����
				maxSum[i][j]=Math.max(maxSum[i+1][j], maxSum[i+1][j+1])+mat[i][j];
			}
		}
		//������
		System.out.println(maxSum[1][1]);
		
	}
}
