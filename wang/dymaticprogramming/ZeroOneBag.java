package com.wang.dymaticprogramming;

public class ZeroOneBag {
	//����������ΪWʱ������ֵ��
	//���������
	public int maxSumValue =0 ;//�������㱳�������Ӽ������������õ��ܼ�ֵ����ʼ��Ϊ0��
	public static void main(String[] args) {
		//ÿ����Ʒ������ѡ���ѡ������һ����2^n���Ӽ�������Ϊn��ÿһ�е�����Ϊһ������ʵ��
		//����weight���ÿ����Ʒ�ľ�������
		//����value���ÿ����Ʒ�ļ�ֵ
		//n������n����Ʒ
		//maxweight��ʾ���������ء�
		
		ZeroOneBag bag = new ZeroOneBag();
		int[] weight = {7,3,4,5};
		int[] value = {42,12,40,25};
		
		int[][] a = new int[(int) Math.pow(2, weight.length)][weight.length];
		System.out.println(a.length);
		//��������
		bag.bruteForce(a,weight,value,4,10);
		//��̬�滮��
		int[] weight1 = {2,1,3,2};
		int[] value1 = {12,10,20,15};
		bag.zeroOneBag1(weight1, value1);
		//ǡ��װ�����������
		bag.zeroOneBag(weight1, value1);
		//��ȫ���������һ�ֽⷨ
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
		int index = 0;//��¼����a����һ��ʹ��ֵ���
		int indexWeight = 0;//��¼�������Ƕ���ʱ��ֵ���
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
		System.out.println("��������"+indexWeight+"ʱ����ֵ���Ϊ"+maxSumValue+"��ʱʱ��"+index+"��");
		
		
	}
	
	//��̬�滮����������⣺
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
		System.out.println("�������ش�0��������Ʒ����֮��Ϊ8�ܹ��ﵽ������ֵΪ��");
		for(int i=0;i<F.length;i++) {
			for(int j=0;j<F[0].length;j++) {
				System.out.print(F[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println(F[F.length-1][F[0].length-2]);
	}
	
	
	//ǡ��װ������ΪW�ı���������ֵ��
	public static int MinNum = 0x80000000;//������ʾ������
	public void zeroOneBag(int[] weight,int[] value) {
		int lenRow=weight.length;
		int maxWeight=5;
		int f[][] = new int[lenRow+1][maxWeight+1];
		
		//��ʼ��ʱ���ѳ��˵�һ�е������0����������Ԫ���óɸ����
		for(int i=0;i<=lenRow;i++) {
			for(int j=1;j<=maxWeight;j++) {
				f[i][j]=MinNum;
			}
		}
		for(int i=0;i<=lenRow;i++) {
			f[i][0]=0;//��������Ϊ0ʱ�ǺϷ��ġ�
		}
		
		//���㣬����
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
		System.out.println("ǡ��װ������������ֵΪ��"+f[lenRow][maxWeight]);
		
	}
	//��ȫ��������
	/*
	 * �� N����Ʒ��һ������ΪV �ı���,ÿ����Ʒ�������޼����á�
	 * �����i����Ʒ�ķ�����Ci,��ֵ��Wi��
	 * ���:����Щ��Ʒװ�뱳��,��ʹ��Щ��Ʒ�ĺķѵķ����ܺͲ�������������,�Ҽ�ֵ�ܺ����
	 * */
	//��ȫ������һ�ֽⷨ��
	/*
	 * .01������ѡ��i����Ʒʱ���ݻ���������£�ֻ��2��״̬��ѡ���Ż��ǲ��ţ��ҳ�����ֵ��ѡ��
	 * 
	 * ����ȫ������ѡ��i����Ʒʱ���ݻ���������£�������2������״̬��ѡ����1��������2����3����
	 * ���߲��š��ҳ�����ֵ��ѡ��
	 * ��������k = j/weight[i]��������Էż�����
	 * Ȼ��״̬ת�Ʒ��̸�Ϊ V[i][j] = max(V[i - 1][j - k*weight[m]] + k * value[i]) 
	 * ��0��k����һ��������ֵ����
	 * 
	 * */
	//��ʹ�ò�����maxWeight������µ�����ֵ
	public void totalBag(int[] weight,int[] value) {
		int lenRow = weight.length;
		int maxWeight = 10 ;
		int[][] f = new int[lenRow+1][maxWeight+1];
		//ȫ����ʼ��Ϊ0
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				f[i][j]=0;
			}
		}
		//����
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
		System.out.println("��ȫ������һ�ֽⷨ��"+f[lenRow][maxWeight]);
		for(int i=0;i<=lenRow;i++) {
			for(int j=0;j<=maxWeight;j++) {
				System.out.print(f[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
