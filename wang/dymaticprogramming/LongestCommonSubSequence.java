package com.wang.dymaticprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class LongestCommonSubSequence {
	
	
	/*
	 * 最长公共子序列
	 * 序列1,3,5,4,2,6,8,7和序列1,4,8,6,7,5
	 * 1.确定子问题
	 * 公共子序列，且是最长的。若1,4,8,7是它们的最长公共子序列，
	 * 则删除7后，也是最长公共子序列
	 *  考虑最长公共子序列问题如何分解成子问题，设A=“a0，a1，…，am-1”，
	 *  B=“b0，b1，…，bm-1”，并Z=“z0，z1，…，zk-1”为它们的最长公共子序列。
	 *  不难证明有以下性质：
	 *  (1) 如果am-1=bn-1，则zk-1=am-1=bn-1，且“z0，z1，…，zk-2”是“a0，a1，…，am-2”
	 *       和“b0，b1，…，bn-2”的一个最长公共子序列；
	 *  (2)如果am-1!=bn-1，则若zk-1!=am-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-2”
	 *       和“b0，b1，…，bn-1”的一个最长公共子序列；
	 *  (3)如果am-1!=bn-1，则若zk-1!=bn-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-1”
	 *       和“b0，b1，…，bn-2”的一个最长公共子序列。
	 * 2.确定状态
	 * dp[i][j]为当到达s1[i]和s2[j]的最长公共子序列长度
	 * 如果s1[i]=s2[j],dp[i][j]=dp[i-1][j-1]+1,i>0,j>0
	 * 如果s1[i]!=s2[j],dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]),i>0,j>0;
	 * 
	 * */
	public static void main(String[] args) {
		char[] ch1= {'a','f','h','b' };
		char[] ch2= {'a','b','h'};
		longestCommonSubSequence(ch1, ch2);
	}
	private static int longestCommonSubSequence(char[] ch1, char[] ch2) {
		int[][] dp = new int[ch1.length+1][ch2.length+1];
		int[][] path = new int[ch1.length+1][ch2.length+1];
		HashSet<Stack<Character>> hashSet = new HashSet<Stack<Character>>();
		int max =0;
		for(int i=0;i<=ch1.length;i++) {
			dp[i][0]=0;
		}
		for(int j=0;j<=ch2.length;j++) {
			dp[0][j]=0;
		}
		for(int i=1;i<=ch1.length;i++) {
			for(int j=1;j<=ch2.length;j++) {
				if(ch1[i-1]==ch2[j-1]) {
					dp[i][j]=dp[i-1][j-1]+1;
					path[i][j]=1;
				}else {
					if(dp[i-1][j]>=dp[i][j-1]) {
						dp[i][j]=dp[i-1][j];
						path[i][j]=2;
					}else {
						dp[i][j]=dp[i][j-1];
						path[i][j]=3;
					}
				}
				if(max<dp[i][j]) {
					max=dp[i][j];
				}
			}
		}
		for(int i=0;i<=ch1.length;i++) {
			for(int j=0;j<=ch2.length;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		for(int i=0;i<=ch1.length;i++) {
			for(int j=0;j<=ch2.length;j++) {
				if(max==dp[i][j]) {
					Stack stack = printLCS(i,j,ch1,ch2,dp,path);
					hashSet.add(stack);
				}
			}
		}
		Iterator iter  = hashSet.iterator();
		while(iter.hasNext()) {
			Stack stack = (Stack) iter.next();
			while(!stack.isEmpty()) {
				System.out.print(stack.pop()+" ");
			}
			System.out.println();
		}
		return 0;
		
	}
	
	public static Stack printLCS(int row,int column,char[] ch1,char[] ch2,int[][] dp,int[][] path) {
		//打印所有最长子序列
		Stack<Character> stack = new Stack<Character>();
				
		for(int i=row;i>=1;i--) {
			for(int j=column;j>=1;j--) {		
					if(path[i][j]==1) {
						stack.push(ch1[i-1]);
					}else if(path[i][j]==2) {
						break;
					}else if(path[i][j]==3) {
						continue;
					}
				}			
			}
		return stack;
				
	}
}
