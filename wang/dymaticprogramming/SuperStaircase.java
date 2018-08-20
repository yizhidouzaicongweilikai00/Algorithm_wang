package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * 有一楼梯共M级，刚开始时你在第一级，若每次只能跨上一级或二级，要走上第M级，共有多少种走法？
 * 输入：
 * 输入数据首先包含一个整数N，表示测试实例的个数，然后是N行数据，每行包含一个整数M（1<=M<=40）,表示楼梯的级数。
 * 
 * 输出：
 * 对于每个测试实例，请输出不同走法的数量
 * 
 * 
 * 由题：
 * 如果台阶只有1级，则有1中走法
 * 如果台阶有2级，则有2种走法
 * 如果台阶有3级，则有3中走法
 * 如果台阶有4级，则有5中走法
 * 如果台阶有5级，则有8中走法
 * 归纳可得f[i] =f[i-1]+f[i-2]
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
				f[j]=f[j-1]+f[j-2];//从前1级上楼梯，从前2级上楼梯
			}
			System.out.println(f[a]);
		}
	}
}
