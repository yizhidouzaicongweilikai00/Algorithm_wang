package com.wang.dymaticprogramming;

public class AssemblyLineScheduling {
	public static void main(String[] args) {
		int n = 6 ;//一共有6个站
		//进站耗费时间
		int[] e = {2,4};
		//每个站处理所耗费的时间
		int[][] a = {{7,9,3,4,8,4},{8,5,6,4,5,7}};
		//从line1到line2或从line2到line1所用的时间
		int[][] t = {{2,3,1,3,4},{2,1,2,2,1}};
		//各自出站所用时间
		int[] x = {3,2};
		//调用函数
		Object[] obj = fastestWay(a,t,e,x,n);
		int[][] l = (int[][]) obj[0];
		int lr = (int) obj[1];
		printStations(l,lr,n);
		
		
	}

	private static void printStations(int[][] l, int lr, int n) {
		//lr表示到达j点时，现在出站是line1还是line2
		int i =lr;
		System.out.println("line"+i+","+"station"+n);
		for(int j=n;j>=2;j--) {
			i=l[i-1][j-1];
			System.out.println("line"+i+","+"station"+(j-1));
		}
		
	}

	private static Object[] fastestWay(int[][] a, int[][] t, int[] e, int[] x, int n) {
		//f[][]表示到达某个站时用的最短时间
		int[][] f=new int[n][n];
		//l[][]表示到达某个站时，前一个是从line1还是line2
		int[][] l=new int[2][n];
		//初始值
		f[0][0]=e[0]+a[0][0];
		f[1][0]=e[1]+a[1][0];
		//初始值
		l[0][0]=1;
		l[1][0]=2;
		int fr=0,lr=0;
		Object[] obj = new Object[2];
		//本题有两种情况在line1处j点的最短时间和在line2处j点的最短时间。分情况计算
		for(int i=1;i<n;i++) {
			if(f[0][i-1]+a[0][i]<f[1][i-1]+t[1][i-1]+a[0][i]) {
				f[0][i]=f[0][i-1]+a[0][i];
				l[0][i]=1;
			}else {
				f[0][i]=f[1][i-1]+t[1][i-1]+a[0][i];
				l[0][i]=2;
			}
			
			if(f[1][i-1]+a[1][i]<f[0][i-1]+t[0][i-1]+a[1][i]) {
				f[1][i]=f[1][i-1]+a[1][i];
				l[1][i]=2;
			}else {
				f[1][i] = f[0][i-1]+t[0][i-1]+a[1][i];
				l[1][i]=1;
			}
		}
		//计算最后结束时的最优解
		if(f[0][n-1]+x[0]<f[1][n-1]+x[1]) {
			fr=f[0][n-1]+x[0];
			lr=1;
		}else {
			fr=f[1][n-1]+x[1];
			lr=2;
		}
		System.out.println("最短时间："+fr+","+"以line"+lr+" 出口");
		for(int j=0;j<n;j++) {
			System.out.print(f[0][j]+" ");
		}
		System.out.println();
		for(int j=0;j<n;j++) {
			System.out.print(f[1][j]+" ");
		}
		System.out.println();
		obj[0]=l;
		obj[1]=lr;
		return obj;
	}
	
}
