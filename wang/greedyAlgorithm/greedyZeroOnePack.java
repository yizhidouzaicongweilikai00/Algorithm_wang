package com.wang.greedyAlgorithm;

/*
 * 有一个背包，背包容量是M=150。有7个物品;
 * 要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
 * 物品 A  B  C  D  E  F  G
 * 重量 35  30  60  50  40  10  25
 * 价值 10  40  30  50  35  40  30
 * 
 * 思路：
 * 让你把物品一个个的往包里装，要求装入包中的物品总价值最大，要让总价值最大，
 * 就可以想到怎么放一个个的物品才能让总的价值最大，因此可以想到如下三种选择物品的方法，
 * 即可能的局部最优解：
 * ①：每次都选择价值最高的往包里放。
 * ②：每次都选择重量最小的往包里放。
 * ③：每次都选择单位重量价值最高的往包里放。
 * 找到可能的局部解以后，分析每一种解能不能合起来变成总体最优解，
 * 对以上三中局部解一一分析：
 * ①：选择价值最高的，就会忽略了重量，若
 * M=50，
 * 物品1： 重量：50，价值：40
 * 物品2： 重量：20，价值30
 * 物品3： 重量：30，价值30
 * 显然，对于上述情况，该局部解行不通。
 * 
 * ②：选择重量最小的，就会忽略了价值，同①策略类似
 * ③:该策略总是能让装入包中的物品总价值最大，所以该策略是正确的贪心策略。
 * */

public class greedyZeroOnePack {
	public static void main(String[] args) {
		int max_weight=150;
		int[] weight = {7,3,4,5};
		int[] value = {42,12,40,25};
		int result = greedyZeroOnePack(10,weight,value);
		System.out.println(result);
	}
	public static int greedyZeroOnePack(int max_weight,int[] weight,int[] value) {
		int length = weight.length;//物件总数
		double[] price = new double[length];//性价比
		int[] count = new int[length];//表示性价比对应的是第几个物品
		for(int i=0;i<length;i++) {
			price[i] = (double)value[i]/weight[i];//得到性价比，
			count[i]=i;//count中第i个元素对应第i个物品
		}
		//对性价比数组进行排序，
		for(int i=0;i<length;i++) {
			for(int j=0;j<j-i-1;j++) {
				if(price[i]<price[i+1]) {
					double temp = price[i];
					price[i]=price[i+1];
					price[i+1]=temp;
					//交换性价比排序后，再吧序号交换,方便之后取数
					int tem=count[i];
					count[i]=count[i+1];
					count[i+1]=tem;
				}
				
			}
		}
		//把质量和价值也按照性价比的排序顺序对应好，存到新数组里
		int[] new_weight = new int[length];
		int[] new_value = new int[length];
		for(int i=0;i<length;i++) {
			new_value[i]=value[count[i]];
			new_weight[i]=weight[count[i]];
		}
		int maxValue = 0;
		for(int i=0;i<length;i++) {
			if(max_weight>=new_weight[i]) {
				max_weight=max_weight-new_weight[i];
				maxValue=maxValue+new_value[i];
			}
		}
		return maxValue;
		
	}
}
