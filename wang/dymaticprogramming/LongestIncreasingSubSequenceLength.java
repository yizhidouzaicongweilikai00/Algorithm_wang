package com.wang.dymaticprogramming;

public class LongestIncreasingSubSequenceLength {
	
	//最长递增子序列的长度
		/*
		 * 这里：
		 * 1.确定子问题
		 * 最长递增子序列的长度。如1，2，3，4。
		 * 如果它是一个最长递增子序列，那么1，2，3也是一个最长递增子序列。
		 * 即具有最优子结构
		 * 2.确定状态
		 * 这里我们求得是一个长度，我们以dp[i]表示到达第i个元素时得最长递增子序列得长度
		 * 3.确定状态转移方程
		 * 首先，我们减小问题规模，
		 *   1）当只有一个元素时，最长递增子序列长度dp[1]=1
		 *   2）当只有两个元素时，最长递增子序列得长度有两种情况，
		 *      如果a[2]>=a[1],则dp[2]=dp[1]+1;如果a[2]<a[1],那么dp[2]=1;
		 *      取它们得最大值
		 *   3）当只有三个元素时，最长递增子序列得长度有三种情况：
		 *      如果a[3]>=a[2],则dp[3]=dp[2]+1;
		 *      如果a[3]<a[2],则判断a[3]>=a[1]是否成立，如果成立，则dp[3]=dp[1]+1;
		 *                    如果[3]<a[1],那么dp[3]=1,
		 *      取这三种情况得最大值得dp[3]
		 * 
		 * 
		 * */
		public static void main(String[] args) {
			int[] sequence = {3,3,1,2,4};
			longestIncreasingSubSequenceLength(sequence);
		}
		public static int longestIncreasingSubSequenceLength(int[] sequence) {
			int len = sequence.length;
			int[] dp = new int[len];
			dp[0]=1;	
			for(int i=1;i<len;i++) {
				
				for(int j=i;j>=1;j--) {
					if(sequence[j-1]<=sequence[i]) {
						int temp = dp[j-1]+1;
						dp[i]=Math.max(temp, dp[i]);
					}
					if(j==1) {
						dp[i]=Math.max(dp[i], 1);
					}
				}
				System.out.print(dp[i]+" ");
			}
			System.out.println();
			//找到最长值。
			int max = 0;
			for(int i=0;i<dp.length;i++) {
				if(max<dp[i]) {
					max = dp[i];
				}
			}
			//
			/*因为得到得dp值为各个子序列得最大增长子序列值，
			 * 所以当d[i]为前一个最长子序列得长度加上a[i]得长度得到的，
			 * 所以当dp[i]=max时，我们可以得到a[i]是其中的一个元素
			 * 此时其他的元素最长递增子序列长度为max-1;当某个元素的值为max-1，
			 * 时这个元素也是最长递增子序列中的一个。
			 * 
			 * */
			//这里找的是其中一个，如果要全部找出来，则要得到所有dp[i]=max的位置
			int[] res = new int[max];
			for(int i=sequence.length-1;i>=0;i--) {
				if(dp[i]==max) {
					res[max-1]=sequence[i];
					max--;
				}
			}
			for(int i=0;i<res.length;i++) {
				System.out.print(res[i]+" ");
			}
			return 0;
			
		}
}
