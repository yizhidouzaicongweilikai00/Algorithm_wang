package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * һֻС�۷䣺
 * һֻ����ѵ�����۷�ֻ�������Ҳ����ڵķ䷿�����ܷ������С����̼����۷�ӷ䷿a�����䷿b�Ŀ���·������
 * �䷿��״Ϊ��
 *    1   3   5   7   9   11  
 *      2   4   6   8   10
 * 
 *  Input
 *  �������ݵĵ�һ����һ������N,��ʾ����ʵ���ĸ�����Ȼ����N �����ݣ�ÿ�а�����������a��b(0<a<b<50)
 *  
 *   Output
 *   ����ÿ������ʵ����������۷�ӷ䷿a�����䷿b�Ŀ���·������ÿ��ʵ�������ռһ�С�
 *   
 *   
 *   ���⣺��ԭ�㵽����Ŀ���·�����£�
 *   1   2   3   4   5   6   7   8   9
 *   1   1   2   3   5   8   13  21  34
 *   ���Կ�����Զ�㵽����Ŀ���·�ߵ�����f[i]=f[i-1]+f[i-2]
 *   쳲���������
 * */


public class Bee {
	//�򵥵��Ƽ�����
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int result = bee(n);
		System.out.println(result);
	}
	public static int bee(int n) {
		
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 1;
		}
		return bee(n-2)+bee(n-1);
	}
}
