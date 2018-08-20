package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * 一只小蜜蜂：
 * 一只经过训练的蜜蜂只能爬向右侧相邻的蜂房，不能反向爬行。请编程计算蜜蜂从蜂房a爬到蜂房b的可能路线数。
 * 蜂房形状为：
 *    1   3   5   7   9   11  
 *      2   4   6   8   10
 * 
 *  Input
 *  输入数据的第一行是一个整数N,表示测试实例的个数，然后是N 行数据，每行包含两个整数a和b(0<a<b<50)
 *  
 *   Output
 *   对于每个测试实例，请输出蜜蜂从蜂房a爬到蜂房b的可能路线数，每个实例的输出占一行。
 *   
 *   
 *   由题：从原点到各点的可能路线如下：
 *   1   2   3   4   5   6   7   8   9
 *   1   1   2   3   5   8   13  21  34
 *   可以看出从远点到个点的可能路线的条数f[i]=f[i-1]+f[i-2]
 *   斐波那契数列
 * */


public class Bee {
	//简单递推计数：
	
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
