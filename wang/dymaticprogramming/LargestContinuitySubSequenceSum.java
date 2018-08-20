package com.wang.dymaticprogramming;

import java.util.Stack;

public class LargestContinuitySubSequenceSum {
	/*
	 * 对于序列{ 1, -3, 7, 8, -4, 12, -10, 6 }; 求最大连续子序列和
	 * 
	 * 如果7, 8, -4, 12,得到最大连续子序列和
	 * 1.确定子问题
	 * 本题描述的是最大连续子序列，第一是子序列，第二子序列是连续的；第三连续子序列有最大和
	 * 子问题{1, -3, 7, 8, -4, 12, -10,}的最大连续子序列和是{ 1, -3, 7, 8, -4, 12, -10, 6 }的最大连续子序列和的子问题。
	 * 2.确定状态
	 * 我们假设dp[i]为到达第i个元素是的最大子序列和。
	 * 3.确定状态转移方程
	 * 如果从顶向下分析：dp[i]是前i个子序列中的最大连续子序列和，dp[i-1]是前i-1个子序列中的最大连续子序列和。
	 * 那么当a[i]时，dp[i]=dp[i-1]+a[i];或者dp[i]=a[i]
	 * 
	 * 如果先分析小规模子问题
	 *当只有一个元素时， a[1]=1,dp[1]=1
	 *当只有两个元素时，temp=dp[1]+a[2]或a[2]
	 *当只有三个元素时，temp=dp[2]+a[3]或a[3]
	 *当只有四个元素时，temp=dp[3]+a[4]或a[4]
	 *当只有i个元素时，temp=dp[i-1]+a[i]或a[i]
	 * 
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		int[] sequence = { 1, -3, 7, 8, -4, 12, -10, 6 };
		largestContinuitySubSequenceSum(sequence);
	}
	public static int largestContinuitySubSequenceSum(int[] sequence) {
		int len = sequence.length;
		int[] dp = new int[len];
		
		dp[0]=sequence[0];
		for(int i=1;i<len;i++) {
			int temp = dp[i-1]+sequence[i];
			dp[i]=Math.max(temp, sequence[i]);
		}
		for(int i=0;i<len;i++) {
			System.out.print(sequence[i]+" ");
		}
		System.out.println();
		for(int i=0;i<len;i++) {
			System.out.print(dp[i]+" ");
		}
		System.out.println();
		Stack<Integer> stack = new Stack<Integer>();
		//找到最大连续子序列和
		int max =0 ;
		for(int i=0;i<dp.length;i++) {
			if(max<dp[i]) {
				max=dp[i];
			}
		}
		//求最大连续子序列和中的元素
		for(int i=len-1;i>=0;i--) {
			if(dp[i]==max) {
				stack.push(sequence[i]);
				max=max-sequence[i];
			}
		}
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			System.out.print(temp+" ");
		}
		return 0;
		
	}
}
