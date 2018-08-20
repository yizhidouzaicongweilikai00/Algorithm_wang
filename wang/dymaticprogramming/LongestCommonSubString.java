package com.wang.dymaticprogramming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubString {
	
	
	
	/*
	 * ����Ϊ�����ַ��������ܰ����ո񣩣����Ⱦ�С�ڵ���50
	 * abcde
	 * abgde
	 * 
	 * ���Ϊһ����������ʾ����������Ӵ��ĳ���
	 * 
	 * 1.ȷ��������
	 * ������Ӵ������һ��abcdeΪһ��������Ӵ�����ȥ��e��Ҳ��������Ӵ�
	 * 
	 * 2.ȷ��״̬
	 * �������ǽ������ģ��С��
	 * ����һ����ά����dp[n][n]������dp[i][j]����ʾȡ��s1[i]��ȡ��s2[j]ʱ��
	 * ��������Ӵ����ȡ�
	 * ��d[i-1][j-1]��ȡ��s1[i-1]��ȡ��s2[j-1]ʱ����������Ӵ����ȡ�
	 * ���s1[i]=s2[j],��ôdp[i][j]=dp[i-1][j-1]+1;
	 * ���s1[i]������s2[j],��ôdp[i][j]=dp[i-1][j-1]
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
		
		//��ӡ������Ӵ�
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
