package com.wang.dymaticprogramming;
/*
 * 有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，
 * 每年年初也生一头小母牛。请编程实现在第n年的时候，共有多少头母牛？
 * 
 * 输入：
 * 输入数据由多个测试实例组成，每个测试实例占一行，包括一个整数n(0<n<55)，n的含义如题目中描述。
 * n=0表示输入数据的结束，不做处理。
 * 
 * 输出：
 * 对于每个测试实例，输出在第n年的时候母牛的数量。每个输出占一行。
 * 例如：
 * 输入
 * 2 4 5 0
 * 输出：
 * 2 4 6
 * 
 * */

/*
 * 本题可以先模拟：
 * 第一年总牛数：1
 * 第二年总牛数：2
 * 第三年总牛数：3
 * 第四年总牛数：4
 * 第五年总牛数：6
 * 第六年总牛数：9
 * 第七年总牛数：13
 * 则可以得到f[i]=f[i-1]+f[i-3]
 * 即前三年已经存在的牛，在今年一定会产仔。
 * */
public class StoryOfCows {
	public static void main(String[] args) {
		int cow_sum = cows(7);
		System.out.println(cow_sum);
	}
	
	//简单递推计数：
	public static int cows(int n) {
		int[] f = new int[n+1];
		if(n==0) {
			return 0;
		}
		if(n<=4) {
			f[n]=n;
		}
		if(n>4) {
			f[n]=cows(n-1)+cows(n-3);
		}
		return f[n];
		
	}

}
