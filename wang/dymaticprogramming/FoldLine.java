package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * ���߷ָ�ƽ��
 * 
 * ����Ҫ�����n�����߷ָ�ƽ��������Ŀ�����磬һ�����߿��Խ�ƽ��ֳ������֣��������������Խ�ƽ��ֳ�7���֣�����������ʾ��
 * 
 * ����˼·�������Ƶ��ƣ��ȷ�����ֱ�߷ָ�ƽ�����������ӵ�n��ֱ�ߵ�ʱ��
 * ��֮ǰ��ֱ�������n-1�����㣬��ʱ�ֳ��Ĳ��ֶ���ˣ�n-1��+1��
 * 
 * ����Ҳ��ͬ��f(1)=2,f(2)=7,�Ȼ���ǰ��n-1�����ߣ������ӵ�n������ʱ��
 * ��ʱ��ͼ���µĽ��������2*2��n-1���������Էֳ��Ĳ��ֶ���ˣ�*����n-1��+1��
 * �����Ƴ�f(n)=f(n-1)+4*(n-1)+1,n>=3
 * */
public class FoldLine {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int[] f = new int[n+1];
		f[0]=1;
		f[1]=2;
		f[2]=7;
		for(int i=3;i<=n;i++) {
			f[i]=f[i-1]+4*(n-1)+1;
		}
		System.out.println(f[n]);
		
	}
}
