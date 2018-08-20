package com.wang.greedyAlgorithm;
/*
 * [找零钱问题]假如老板要找给我99分钱，他有上面的面值分别为25，10，5，1的硬币数，
 * 为了找给我最少的硬币数，那么他是不是该这样找呢，先看看该找多少个25分的，
 * 诶99／25＝3，好像是3个，要是4个的话，我们还得再给老板一个1分的，我不干，
 * 那么老板只能给我3个25分的拉，由于还少给我24，所以还得给我2个10分的和4个1分。
 * */
public class MoneyGive {
	public static void main(String[] args) {
		//找零钱
		int[] m = {25, 10, 5, 1};
		int target = 99;
		int[] num = giveMoney(m,target);
		for(int i=0;i<m.length;i++) {
			System.out.print(num[i]+" ");
		}
	}
	public static int[] giveMoney(int[] m,int target) {
		int len = m.length;
		int[] num = new int[len];
		
		for(int i=0;i<len;i++) {
			while(true) {
				if(target>=m[i]) {
					num[i]++;
					target=target-m[i];
				}else {
					break;
				}
			}
		}
		return num;
		
	}
}
