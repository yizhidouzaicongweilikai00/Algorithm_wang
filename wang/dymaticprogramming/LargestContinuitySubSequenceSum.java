package com.wang.dymaticprogramming;

import java.util.Stack;

public class LargestContinuitySubSequenceSum {
	/*
	 * ��������{ 1, -3, 7, 8, -4, 12, -10, 6 }; ��������������к�
	 * 
	 * ���7, 8, -4, 12,�õ�������������к�
	 * 1.ȷ��������
	 * ������������������������У���һ�������У��ڶ��������������ģ���������������������
	 * ������{1, -3, 7, 8, -4, 12, -10,}��������������к���{ 1, -3, 7, 8, -4, 12, -10, 6 }��������������к͵������⡣
	 * 2.ȷ��״̬
	 * ���Ǽ���dp[i]Ϊ�����i��Ԫ���ǵ���������к͡�
	 * 3.ȷ��״̬ת�Ʒ���
	 * ����Ӷ����·�����dp[i]��ǰi���������е�������������кͣ�dp[i-1]��ǰi-1���������е�������������к͡�
	 * ��ô��a[i]ʱ��dp[i]=dp[i-1]+a[i];����dp[i]=a[i]
	 * 
	 * ����ȷ���С��ģ������
	 *��ֻ��һ��Ԫ��ʱ�� a[1]=1,dp[1]=1
	 *��ֻ������Ԫ��ʱ��temp=dp[1]+a[2]��a[2]
	 *��ֻ������Ԫ��ʱ��temp=dp[2]+a[3]��a[3]
	 *��ֻ���ĸ�Ԫ��ʱ��temp=dp[3]+a[4]��a[4]
	 *��ֻ��i��Ԫ��ʱ��temp=dp[i-1]+a[i]��a[i]
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
		//�ҵ�������������к�
		int max =0 ;
		for(int i=0;i<dp.length;i++) {
			if(max<dp[i]) {
				max=dp[i];
			}
		}
		//��������������к��е�Ԫ��
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
