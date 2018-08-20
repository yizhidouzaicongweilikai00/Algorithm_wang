package com.wang.dymaticprogramming;

public class ZeroOneBag {
	//容量最大承重为W时的最大价值。
	//蛮力法解决
	public int maxSumValue =0 ;//定义满足背包问题子集的最大承重所得的总价值，初始化为0；
	public static void main(String[] args) {
		//每个物品都可以选择或不选择，所以一共有2^n个子集，列数为n，每一行的排列为一个背包实例
		//数组weight存放每个物品的具体重量
		//数组value存放每个物品的价值
		//n代表共有n个物品
		//maxweight表示背包最大承重。
		
		ZeroOneBag bag = new ZeroOneBag();
		int[] weight = {7,3,4,5};
		int[] value = {42,12,40,25};
		
		int[][] a = new int[(int) Math.pow(2, weight.length)][weight.length];
		System.out.println(a.length);
		//蛮力法：
		bag.bruteForce(a,weight,value,4,10);
		//动态规划法
		int[] weight1 = {2,1,3,2};
		int[] value1 = {12,10,20,15};
		bag.zeroOneBag1(weight1, value1);
		//恰好装满背包的情况
		bag.zeroOneBag(weight1, value1);
		//完全背包问题第一种解法
		int[] weight2 = {2,3,4,7};
		int[] value2 = {1,3,5,9};
		bag.totalBag(weight2, value2);
		
		int[] weight3 = {35,30,60,50,40,10,25};
		int[] value3 = {10,40,30,50,35,40,30};
		bag.zeroOneBag(weight3, value3);
		
		
	}
	private void bruteForce(int[][] a, int[] weight, int[] value, int n, int maxweight) {
		for(int i=0;i<Math.pow(2, n);i++) {
			int temp1 = i;
			for(int j=0;j<n;j++) {
				int temp2 = temp1%2;
				a[i][j]=temp2;
				temp1=temp1/2;
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		int sumValue = 0;
		int sumWeight = 0;
		int index = 0;//记录数组a的哪一行使得值最大
		int indexWeight = 0;//记录最大承重是多少时价值最大
		for(int i=0;i<a.length;i++) {
			sumValue=0;
			sumWeight=0;
			for(int j=0;j<n;j++) {
				sumValue = sumValue+a[i][j]*value[j]; 
				sumWeight = sumWeight+a[i][j]*weight[j];
				if(sumWeight<=maxweight) {
					if(sumValue>maxSumValue) {
						maxSumValue=sumValue;
						index =i;
						indexWeight = sumWeight;
					}
				}
			}
			
		}
		System.out.println("最大承重是"+indexWeight+"时，价值最大为"+maxSumValue+"此时时第"+index+"行");
		
		
	}
	
	//动态规划解决背包问题：
	public void zeroOneBag1(int[] weight,int[] value) {
		int lenRow = weight.length;
		int lenColumn = 0;
		for(int i=0;i<weight.length;i++) {
			lenColumn = lenColumn + weight[i];
		}
		System.out.println(lenColumn);
		int[][] F = new int[lenRow+1][lenColumn+1];
		for(int j=0;j<=lenColumn;j++) {
			F[0][j]=0;
		}
		for(int i=0;i<=lenRow;i++) {
			F[i][0]=0;
		}
		for(int i=1;i<=weight.length;i++) {
			for(int j=1;j<=lenColumn;j++) {
				if(j<weight[i-1]) {
					F[i][j]=F[i-1][j];
				}else if(j>=weight[i-1]) {
					if(F[i-1][j-weight[i-1]]+value[i-1]<F[i-1][j]) {
						F[i][j]=F[i-1][j];
					}else {
						F[i][j]=F[i-1][j-weight[i-1]]+value[i-1];
					}
				}
			}
		}
		System.out.println("背包承重从0到所有物品重量之和为8能够达到的最大价值为：");
		for(int i=0;i<F.length;i++) {
			for(int j=0;j<F[0].length;j++) {
				System.out.print(F[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println(F[F.length-1][F[0].length-2]);
	}
	
	
	//恰好装满承重为W的背包的最大价值。
	public static int MinNum = 0x80000000;//用来表示负无穷
	public void zeroOneBag(int[] weight,int[] value) {
		int lenRow=weight.length;
		int maxWeight=5;
		int f[][] = new int[lenRow+1][maxWeight+1];
		
		//初始化时，把除了第一列的情况置0，其他所有元素置成负无穷。
		for(int i=0;i<=lenRow;i++) {
			for(int j=1;j<=maxWeight;j++) {
				f[i][j]=MinNum;
			}
		}
		for(int i=0;i<=lenRow;i++) {
			f[i][0]=0;//背包容量为0时是合法的。
		}
		
		//计算，递推
		for(int i=1;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
//				System.out.println(f[i-1][j]);
//				System.out.println(f[i-1][j-weight[i-1]]);
//				System.out.println(value[i-1]);
				if(j>=weight[i-1]) {
					f[i][j]=Math.max(f[i-1][j-weight[i-1]]+value[i-1], f[i-1][j]);
				}else if(j<weight[i-1]) {
					f[i][j]=f[i-1][j];
				}
			}
		}
		System.out.println("恰好装满背包的最大价值为："+f[lenRow][maxWeight]);
		
	}
	//完全背包问题
	/*
	 * 有 N种物品和一个容量为V 的背包,每种物品都有无限件可用。
	 * 放入第i种物品的费用是Ci,价值是Wi。
	 * 求解:将哪些物品装入背包,可使这些物品的耗费的费用总和不超过背包容量,且价值总和最大。
	 * */
	//完全背包第一种解法：
	/*
	 * .01背包在选第i个物品时，容积够用情况下，只有2种状态可选，放还是不放，找出最大价值的选择
	 * 
	 * 而完全背包在选第i种物品时，容积够用情况下，可能有2种以上状态可选，放1个，或者2个，3个，
	 * 或者不放。找出最大价值的选择
	 * 可以利用k = j/weight[i]算出最多可以放几个，
	 * 然后状态转移方程改为 V[i][j] = max(V[i - 1][j - k*weight[m]] + k * value[i]) 
	 * 从0到k遍历一遍求出最大值即可
	 * 
	 * */
	//求使得不超过maxWeight的情况下的最大价值
	public void totalBag(int[] weight,int[] value) {
		int lenRow = weight.length;
		int maxWeight = 10 ;
		int[][] f = new int[lenRow+1][maxWeight+1];
		//全部初始化为0
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				f[i][j]=0;
			}
		}
		//递推
		for(int i=1;i<=lenRow;i++) {
			for(int j=1;j<=maxWeight;j++) {
				if(j<weight[i-1]) {
					f[i][j]=f[i-1][j];
				}else {
					int m = j/weight[i-1];
					for(int k=0;k<=m;k++) {
						if(f[i-1][j-k*weight[i-1]]+k*value[i-1]>=f[i][j]) {
							f[i][j]=f[i-1][j-k*weight[i-1]]+k*value[i-1];
						}
					}
				}
			}
		}
		System.out.println("完全背包第一种解法："+f[lenRow][maxWeight]);
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				System.out.print(f[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
