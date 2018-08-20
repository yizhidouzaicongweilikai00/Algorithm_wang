package com.wang.dymaticprogramming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubString {
	
	
	
	/*
	 * 输入为两行字符串（可能包含空格），长度均小于等于50
	 * abcde
	 * abgde
	 * 
	 * 输出为一个整数，表示最长公共连续子串的长度
	 * 
	 * 1.确定子问题
	 * 最长连续子串，如果一个abcde为一个最长公共子串，则去掉e后也是最长公共子串
	 * 
	 * 2.确定状态
	 * 首先我们将问题规模缩小：
	 * 创建一个二维数组dp[n][n]，其中dp[i][j]，表示取到s1[i]和取到s2[j]时的
	 * 最大连续子串长度。
	 * 则d[i-1][j-1]是取到s1[i-1]和取到s2[j-1]时的最大连续子串长度。
	 * 如果s1[i]=s2[j],那么dp[i][j]=dp[i-1][j-1]+1;
	 * 如果s1[i]不等于s2[j],那么dp[i][j]=dp[i-1][j-1]
	 * 
	 * */
	public static void main(String[] args) {
		String str1 = "abcde";
		String str2 = "abgde";
		longestCommonSubString(str1,str2);
	}
	public static int longestCommonSubString(String str1,String str2) {
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int max = 0;
		int[][] dp = new int[ch1.length][ch2.length];
		for(int i=0;i<ch1.length;i++) {
			for(int j=0;j<ch2.length;j++) {
				if(ch1[i]==ch2[j]) {
					if(i>=1&&j>=1) {
						dp[i][j]=dp[i-1][j-1]+1;
					}else {
						dp[i][j]=1;
					}
					if(max<=dp[i][j]) {
						max=dp[i][j];
					}
				}
			}
		}
		for(int i=0;i<ch1.length;i++) {
			for(int j=0;j<ch2.length;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
		//打印最长公共子串
		int index =0;
		ArrayList<ArrayList<Character>> array = new ArrayList<ArrayList<Character>>();
		
		for(int i=0;i<ch1.length;i++) {
			for(int j=0;j<ch2.length;j++) {
				
				if(max==dp[i][j]) {
					ArrayList<Character> list = new ArrayList<Character>();
					for(int k=i-max+1;k<=i;k++) {
						list.add(ch1[k]);
					}
					array.add(list);
				}
				
				
			}
		}
		for(int i=0;i<array.size();i++) {
			for(int j=0;j<array.get(index).size();j++) {
				System.out.print(array.get(i).get(j)+" ");
			}
			System.out.println();
		}
		return 0;
		
	}
}
