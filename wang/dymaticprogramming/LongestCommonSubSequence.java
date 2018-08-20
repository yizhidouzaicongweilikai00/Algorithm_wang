package com.wang.dymaticprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class LongestCommonSubSequence {
	
	
	/*
	 * �����������
	 * ����1,3,5,4,2,6,8,7������1,4,8,6,7,5
	 * 1.ȷ��������
	 * ���������У�������ġ���1,4,8,7�����ǵ�����������У�
	 * ��ɾ��7��Ҳ�������������
	 *  ���������������������ηֽ�������⣬��A=��a0��a1������am-1����
	 *  B=��b0��b1������bm-1������Z=��z0��z1������zk-1��Ϊ���ǵ�����������С�
	 *  ����֤�����������ʣ�
	 *  (1) ���am-1=bn-1����zk-1=am-1=bn-1���ҡ�z0��z1������zk-2���ǡ�a0��a1������am-2��
	 *       �͡�b0��b1������bn-2����һ������������У�
	 *  (2)���am-1!=bn-1������zk-1!=am-1���̺���z0��z1������zk-1���ǡ�a0��a1������am-2��
	 *       �͡�b0��b1������bn-1����һ������������У�
	 *  (3)���am-1!=bn-1������zk-1!=bn-1���̺���z0��z1������zk-1���ǡ�a0��a1������am-1��
	 *       �͡�b0��b1������bn-2����һ������������С�
	 * 2.ȷ��״̬
	 * dp[i][j]Ϊ������s1[i]��s2[j]������������г���
	 * ���s1[i]=s2[j],dp[i][j]=dp[i-1][j-1]+1,i>0,j>0
	 * ���s1[i]!=s2[j],dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]),i>0,j>0;
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
		//��ӡ�����������
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
