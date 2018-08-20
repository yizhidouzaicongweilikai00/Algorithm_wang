package com.wang.dymaticprogramming;

import java.util.ArrayList;


public class LongestIncreasingSubSequenceSum {
	public static void main(String[] args) {
		int[] sequence = {3,3,1,2,4};
		longestIncreasingSubSequenceSum(sequence);
		
	}
	/*求解最大递增子序列和，这里要特别注意一点：
	 * 这个题目不要求是连续的最大递增子序列。但是一定要注意是递增的！！
	 * 
	 * 题目思路：
	 *1.确定子问题
	 * 在这一步重点是分析哪些变量是随着问题规模的变小而变小的， 
	 * 哪些变量与问题的规模无关。
	 *  
	 *2.确定状态
	 *根据上面找到的子问题来给你分割的子问题限定状态
	 *
	 *3.推到出状态转移方程
	 *这里要注意你的状态转移方程是不是满足所有的条件， 注意不要遗漏
	 *
	 *4.确定边界条件
	 *先根据题目的限制条件来确定题目中给出的边界条件是否能直接推导出， 
	 *如果不行也可以尝试从边界条件反推（举个例子：a(n)→a(2)有递推关系，
	 *但是a(2)→a(1)不符合上述递推关系， 我们就可以考虑用a(1)来倒推出a(2)， 
	 *然后将递推的终点设置为a(2)）; 
	 *
	 *5.确定实现方式
	 *这个依照个人习惯 就像是01背包的两层for循环的顺序 
	 *
	 *6.确定优化方法
	 *很多时候你会发现走到这里步的时候你需要返回第1步重来。
	 *首先考虑降维问题（优化内存）， 优先队列、四边形不等式（优化时间）等等。
	 */
	/*
	 * 最长递增子序列不一定是最大递增子序列1，2，3，10和7，8，9
	 * 1.确定子问题
	 * 由题可得，给出题目有序列  4 1 2 3 4  ；对于这个序列，它的变量有序列的长度，每个元素的位置，
	 * 如果{1，2，3，4}是一个最大递增子序列，那么那么它的子问题{1，2，3}一定也是最大递增子序列
	 * 也可以这样确定：
	 * 如果只有一个元素 4 ，那么它的最大递增子序列和就是4
	 * 如果有两个元素，第一个元素是4，第二个元素是3，此时我们应判断第二个元素是否比第一个元素大，如果
	 * 第二个元素比第一个元素小，则a[2]<a[1]=4,那么它就不是递增的dp[2]=dp[2-1]=dp[1]=4;
	 * 如果第二个元素比第一个元素大，则a[2]>a[1],那么它是递增的   dp[2]=dp[1]+a[2]
	 * 此时判断第三个元素，如果第三个元素比第二个元素大，则它是递增的
	 * 2.确定状态
	 * 本题要求的是最大递增子序列和，我们用dp[i]表示包含第i个元素的最大递增子序列和
	 * 
	 * 3.确定状态转移方程
	 * dp[i]=max(dp[i-1]+a[i],dp[i-2]+a[i],dp[i-3]+a[i],...,a[i])
	 * 
	 * */
	//最大递增子序列和
	public static int longestIncreasingSubSequenceSum(int[] sequence) {
		int len = sequence.length;
		int[] dp = new int[len];
		dp[0]=sequence[0];
		for(int i=0;i<len;i++) {
			for(int j=i;j>=1;j--){
				if(sequence[j-1]<=sequence[i]) {
					int temp=dp[j-1]+sequence[i];
					dp[i]=Math.max(temp, dp[i]);
				}
				if(j==1) {
					dp[i]=Math.max(sequence[i], dp[i]);
				}
			}
			System.out.print(dp[i]+" ");
		}
		System.out.println();
		//得到最大子序列和
		int sumMax = 0;
		for(int i=0;i<dp.length;i++) {
			if(sumMax<dp[i]) {
				sumMax=dp[i];
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=sequence.length-1;i>=0;i--) {
			if(dp[i]==sumMax) {
				list.add(sequence[i]);
				sumMax=sumMax-sequence[i];
			}
		}
		for(int i=list.size()-1;i>=0;i--) {
			System.out.print(list.get(i)+" ");
		}
		return 0;
	}
	
	/*
	 * 
	 * 
	 * 
	 * */
}

