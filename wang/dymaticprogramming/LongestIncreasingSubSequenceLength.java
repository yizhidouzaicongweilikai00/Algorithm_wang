package com.wang.dymaticprogramming;

public class LongestIncreasingSubSequenceLength {
	
	//����������еĳ���
		/*
		 * ���
		 * 1.ȷ��������
		 * ����������еĳ��ȡ���1��2��3��4��
		 * �������һ������������У���ô1��2��3Ҳ��һ������������С�
		 * �����������ӽṹ
		 * 2.ȷ��״̬
		 * �������������һ�����ȣ�������dp[i]��ʾ�����i��Ԫ��ʱ������������еó���
		 * 3.ȷ��״̬ת�Ʒ���
		 * ���ȣ����Ǽ�С�����ģ��
		 *   1����ֻ��һ��Ԫ��ʱ������������г���dp[1]=1
		 *   2����ֻ������Ԫ��ʱ������������еó��������������
		 *      ���a[2]>=a[1],��dp[2]=dp[1]+1;���a[2]<a[1],��ôdp[2]=1;
		 *      ȡ���ǵ����ֵ
		 *   3����ֻ������Ԫ��ʱ������������еó��������������
		 *      ���a[3]>=a[2],��dp[3]=dp[2]+1;
		 *      ���a[3]<a[2],���ж�a[3]>=a[1]�Ƿ�����������������dp[3]=dp[1]+1;
		 *                    ���[3]<a[1],��ôdp[3]=1,
		 *      ȡ��������������ֵ��dp[3]
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
			//�ҵ��ֵ��
			int max = 0;
			for(int i=0;i<dp.length;i++) {
				if(max<dp[i]) {
					max = dp[i];
				}
			}
			//
			/*��Ϊ�õ���dpֵΪ���������е��������������ֵ��
			 * ���Ե�d[i]Ϊǰһ��������еó��ȼ���a[i]�ó��ȵõ��ģ�
			 * ���Ե�dp[i]=maxʱ�����ǿ��Եõ�a[i]�����е�һ��Ԫ��
			 * ��ʱ������Ԫ������������г���Ϊmax-1;��ĳ��Ԫ�ص�ֵΪmax-1��
			 * ʱ���Ԫ��Ҳ��������������е�һ����
			 * 
			 * */
			//�����ҵ�������һ�������Ҫȫ���ҳ�������Ҫ�õ�����dp[i]=max��λ��
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
