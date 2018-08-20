package com.wang.dymaticprogramming;

import java.util.Scanner;

/*
 * 折线分割平面
 * 
 * 我们要求的是n条折线分割平面的最大数目。比如，一条折线可以将平面分成两部分，两条折线最多可以将平面分成7部分，具体如下所示。
 * 
 * 解题思路：１递推递推，先分析下直线分割平面的情况，增加第n条直线的时候，
 * 跟之前的直线最多有n-1个交点，此时分出的部分多出了（n-1）+1；
 * 
 * 折线也是同理，f(1)=2,f(2)=7,先画好前面n-1条折线，当增加第n条拆线时，
 * 此时与图形新的交点最多有2*2（n-1）个，所以分出的部分多出了２*２（n-1）+1　
 * 所以推出f(n)=f(n-1)+4*(n-1)+1,n>=3
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
