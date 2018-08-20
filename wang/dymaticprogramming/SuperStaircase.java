package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * ��һ¥�ݹ�M�����տ�ʼʱ���ڵ�һ������ÿ��ֻ�ܿ���һ���������Ҫ���ϵ�M�������ж������߷���
 * ���룺
 * �����������Ȱ���һ������N����ʾ����ʵ���ĸ�����Ȼ����N�����ݣ�ÿ�а���һ������M��1<=M<=40��,��ʾ¥�ݵļ�����
 * 
 * �����
 * ����ÿ������ʵ�����������ͬ�߷�������
 * 
 * 
 * ���⣺
 * ���̨��ֻ��1��������1���߷�
 * ���̨����2��������2���߷�
 * ���̨����3��������3���߷�
 * ���̨����4��������5���߷�
 * ���̨����5��������8���߷�
 * ���ɿɵ�f[i] =f[i-1]+f[i-2]
 * 
 * * */
public class SuperStaircase {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i=0;i<n;i++) {
			int a = scan.nextInt();
			int f[] = new int[41];
			f[1]=1;
			f[2]=2;
			f[3]=3;
			for(int j=4;j<41;j++) {
				f[j]=f[j-1]+f[j-2];//��ǰ1����¥�ݣ���ǰ2����¥��
			}
			System.out.println(f[a]);
		}
	}
}
