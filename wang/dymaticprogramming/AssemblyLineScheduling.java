package com.wang.dymaticprogramming;

public class AssemblyLineScheduling {
	public static void main(String[] args) {
		int n = 6 ;//һ����6��վ
		//��վ�ķ�ʱ��
		int[] e = {2,4};
		//ÿ��վ�������ķѵ�ʱ��
		int[][] a = {{7,9,3,4,8,4},{8,5,6,4,5,7}};
		//��line1��line2���line2��line1���õ�ʱ��
		int[][] t = {{2,3,1,3,4},{2,1,2,2,1}};
		//���Գ�վ����ʱ��
		int[] x = {3,2};
		//���ú���
		Object[] obj = fastestWay(a,t,e,x,n);
		int[][] l = (int[][]) obj[0];
		int lr = (int) obj[1];
		printStations(l,lr,n);
		
		
	}

	private static void printStations(int[][] l, int lr, int n) {
		//lr��ʾ����j��ʱ�����ڳ�վ��line1����line2
		int i =lr;
		System.out.println("line"+i+","+"station"+n);
		for(int j=n;j>=2;j--) {
			i=l[i-1][j-1];
			System.out.println("line"+i+","+"station"+(j-1));
		}
		
	}

	private static Object[] fastestWay(int[][] a, int[][] t, int[] e, int[] x, int n) {
		//f[][]��ʾ����ĳ��վʱ�õ����ʱ��
		int[][] f=new int[n][n];
		//l[][]��ʾ����ĳ��վʱ��ǰһ���Ǵ�line1����line2
		int[][] l=new int[2][n];
		//��ʼֵ
		f[0][0]=e[0]+a[0][0];
		f[1][0]=e[1]+a[1][0];
		//��ʼֵ
		l[0][0]=1;
		l[1][0]=2;
		int fr=0,lr=0;
		Object[] obj = new Object[2];
		//���������������line1��j������ʱ�����line2��j������ʱ�䡣���������
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
		//����������ʱ�����Ž�
		if(f[0][n-1]+x[0]<f[1][n-1]+x[1]) {
			fr=f[0][n-1]+x[0];
			lr=1;
		}else {
			fr=f[1][n-1]+x[1];
			lr=2;
		}
		System.out.println("���ʱ�䣺"+fr+","+"��line"+lr+" ����");
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
