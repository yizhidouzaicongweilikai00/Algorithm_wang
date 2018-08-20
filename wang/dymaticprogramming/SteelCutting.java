package com.wang.dymaticprogramming;

public class SteelCutting {
	/**
     * 动态规划，自底向上求解。
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @return 最大收益
     */
	public static void main(String[] args) {
		int[] p = {0,1,5,8,9,10,17,17,20,24,30,31,32};//钢条价格表
		int n = 12;//钢条长度
		int[] r = new int[n+1];//一个数组，用r[i]表示长度为i的钢条的最优解，初始值赋为0
		for(int i=0;i<r.length;i++) {
			r[i]=0;
		}
		int q=-1;
		//求长度为j的时候的最优解
		for(int j=1;j<=n;j++) {
			/*当长度为1时：r[1]=r[0]+p[1];
			 *当长度为2时：r[2]=r[1]+p[1],r[2]=p[2];
			 *当长度为3时：r[3]=r[2]+p[1],r[3]=r[1]+p[2],r[3]=p[3];
			 *当长度为4时：r[4]=r[3]+p[1],r[4]=r[2]+p[2],r[4]=r[1]+p[3],r[4]=p[4]+r[0]
			 *当长度为i时：r[i]=r[i-1]+p[1],r[i]=r[i-2]+p[2],r[i]=r[i-3]+p[3],...,r[i]=r[1]+p[i-1]
			 * */
			for(int i=1;i<=j;i++) {
				q=Math.max(q,r[j-i]+p[i]);
			}
			//记录长度为j的最优值
			r[j]=q;
		}
		for(int j=1;j<=n;j++) {
		System.out.println("长度为"+j+"时的最大收益为："+r[j]);
		}		
	}
	
}
