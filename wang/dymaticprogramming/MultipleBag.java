package com.wang.dymaticprogramming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//���ر������⣺
	/*
	 * ��֪:��һ������ΪV�ı�����N����Ʒ����i����Ʒ�����Num[i]����
	 * ÿ����Ʒ��������weight[i]��������cost[i]��
	 * 
	 *  ����:�ڲ�������������������£�����ܻ�ö��ټ�ֵ������
	 *  
	 * ��������Ʒ����N = 3����������ΪV = 8���򱳰�����װ�µ�����ֵΪ64.
	 * weight[] = {1,2,2};
	 * value[]  = {6,10,20};
	 * num[]    = {10,5,2};
	 * ���ر�����01��������ȫ���������𣺶��ر�����ÿ����Ʒ�ĸ������Ǹ����ģ�
	 * ���ܲ���һ�������Բ������޸���
	 * 
	 * ��Ϊ���ڵ� i����Ʒ��num[i]+1�ֲ���:ȡ0��,ȡ1��......ȡnum[i]����
	 * f[i][j]��ʾǰi����Ʒǡ����һ������Ϊv�ı���������ֵ,����״̬ת�Ʒ���:
	 * f[i][j]=max{f[i-1][j-k*weight[i]]+k*weight[i]|0<=k<=n[i]}
	 * 
	 * ��1�������i����Ʒ������Weight[i] * ��Ʒ�ĸ���Num[i] >= ����������V�����Բ��ò�֡�
	 * ��2�������i����Ʒ������Weight[i] * ��Ʒ�ĸ���Num[i] < ����������V�����Բ��ò�֡�
	 * ��ʵ���𲻲�֣��Ϳ�����Ʒ�ܲ���������ȫ����������������������Ʒ�ܲ�����������Ӧ��
	 * 
	 * 
	 * */
public class MultipleBag{
	public static void main(String[] args) {
		int[] weight= {2,3,2,5};
		int[] value = {3,4,5,6};
		int[] num = {2,1,2,1};
		MultipleBag bag = new MultipleBag();
		bag.multiplePack(weight, value, num);
		System.out.println("***************************");
		bag.ChangeMutipleToZeroOnePack(weight, value, num);
		
		
	}

	public void multiplePack(int[] weight,int[] value,int[] num) {
		int lenRow = weight.length;
		int maxWeight = 10;
		int[][] f = new int[lenRow+1][maxWeight+1];
		ArrayList<Integer> list = new ArrayList<Integer>();
//		for(int i=0;i<=lenRow;i++) {
//			f[i][0]=0;
//		}
//		for(int i=0;i<=maxWeight;i++) {
//			f[0][i]=0;
//		}
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				f[i][j]=0;
			}
		}
		for(int i=1;i<=lenRow;i++) {
			for(int j=1;j<=maxWeight;j++) {
				list.clear();
				for(int k=0;(k<=num[i-1])&&(k>=0);k++) {
					if(j>=k*weight[i-1]) {
						int temp = Math.max(f[i-1][j-k*weight[i-1]]+k*value[i-1], f[i-1][j]);
						list.add(temp);
					}
				}
				f[i][j]=Collections.max(list);
			}
		}
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				System.out.print(f[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
	
	
	//���ر���ת����01������Ȼ�����
	public void ChangeMutipleToZeroOnePack(int[] w,int[] v,int[] num) {
		int sumLen = 0;
		for(int i=0;i<num.length;i++) {
			sumLen = sumLen+num[i];
		}
		int[] weight = new int[sumLen];
		int[] value  = new int[sumLen];
		int k = 0;
		//�����ر�������ת��Ϊ01�������⡣
		for(int i=0;i<num.length;i++) {
			while(num[i]>=1) {
				weight[k]=w[i];
				value[k]=v[i];
				k++;
				num[i]--;
			}
		}
		zeroOneBag1(weight, value);
		
		
	}
		//��̬�滮����������⣺
	public void zeroOneBag1(int[] weight,int[] value) {
		int lenRow = weight.length;
		int maxWeight = 10;
		int[][] F = new int[lenRow+1][maxWeight+1];
		for(int j=0;j<=maxWeight;j++) {
			F[0][j]=0;
		}
		for(int i=0;i<=lenRow;i++) {
			F[i][0]=0;
		}
		for(int i=1;i<=weight.length;i++) {
			for(int j=1;j<=maxWeight;j++) {
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
		System.out.println("�������ش�0��������Ʒ����֮��Ϊ8�ܹ��ﵽ������ֵΪ��");
		for(int i=0;i<F.length;i++) {
			for(int j=0;j<F[0].length;j++) {
				System.out.print(F[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println(F[F.length-1][F[0].length-2]);
	}	
}		
		
		
		
		
		
//	public void multipleBag(int[] weight,int[] value,int[] num) {
//		int lenRow = weight.length;
//		int maxWeight = 10;
//		
//		int[][] f = new int[lenRow+1][maxWeight+1];
//		
//		//��ʼ��
//		for(int i=0;i<=lenRow;i++) {
//			for(int j=0;j<=maxWeight;j++) {
//				f[i][j]=0;
//			}
//		}
//		
//		//���ƹ�ʽf[i][j]=max{f[i-1][j-k*weight[i]]+k*weight[i]|0<=k<=n[i]}
//		int n=1;
//		int nCount=0;
//		for(int i=1;i<=lenRow;i++) {
//			for(int j=1;j<=maxWeight;j++) {
//					if(num[i-1]*weight[i-1]>=maxWeight) {
//						f[i][j]=completePack(weight[i-1],value[i-1],f,i,j);
//					}else {
//						n = 1;
//						nCount = num[i-1];
//						while(n<=nCount) {
//							f[i][j]=zeroOnePack(n*weight[i-1],n*value[i-1],f,i,j);
//							nCount=nCount-n;
//							n=n*2;
//						}
//						f[i][j]=zeroOnePack(nCount*weight[i-1],nCount*value[i-1],f,i,j);
//					}
//				}	
//		}
//		System.out.println("���ر��������һ�ֽⷨ��"+f[lenRow][maxWeight]);
//		for(int i=0;i<=lenRow;i++) {
//			for(int j=0;j<=maxWeight;j++) {
//				System.out.print(f[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
//	}
//	public int completePack(int weight,int value,int[][] f,int i,int j) {
//		
//			int m = j/weight;
//			for(int k=0;k<=m;k++) {
//				if(f[i-1][j-k*weight]+k*value>=f[i][j]) {
//					f[i][j]=f[i-1][j-k*weight]+k*value;
//				}
//			
//		}
//		return f[i][j];
//	}
//	public int zeroOnePack(int weight,int value,int[][] F,int i,int j) {
//		if(j>=weight) {
//			if(F[i-1][j-weight]+value<F[i-1][j]) {
//				F[i][j]=F[i-1][j];
//			}else {
//				F[i][j]=F[i-1][j-weight]+value;
//			}
//		}
//		return F[i][j];
//	}
	
