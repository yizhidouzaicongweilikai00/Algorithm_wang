package com.wang.greedyAlgorithm;

/*
 * ��һ������������������M=150����7����Ʒ;
 * Ҫ�󾡿�����װ�뱳���е���Ʒ�ܼ�ֵ��󣬵����ܳ�����������
 * ��Ʒ A  B  C  D  E  F  G
 * ���� 35  30  60  50  40  10  25
 * ��ֵ 10  40  30  50  35  40  30
 * 
 * ˼·��
 * �������Ʒһ������������װ��Ҫ��װ����е���Ʒ�ܼ�ֵ���Ҫ���ܼ�ֵ���
 * �Ϳ����뵽��ô��һ��������Ʒ�������ܵļ�ֵ�����˿����뵽��������ѡ����Ʒ�ķ�����
 * �����ܵľֲ����Ž⣺
 * �٣�ÿ�ζ�ѡ���ֵ��ߵ�������š�
 * �ڣ�ÿ�ζ�ѡ��������С��������š�
 * �ۣ�ÿ�ζ�ѡ��λ������ֵ��ߵ�������š�
 * �ҵ����ܵľֲ����Ժ󣬷���ÿһ�ֽ��ܲ��ܺ���������������Ž⣬
 * ���������оֲ���һһ������
 * �٣�ѡ���ֵ��ߵģ��ͻ��������������
 * M=50��
 * ��Ʒ1�� ������50����ֵ��40
 * ��Ʒ2�� ������20����ֵ30
 * ��Ʒ3�� ������30����ֵ30
 * ��Ȼ����������������þֲ����в�ͨ��
 * 
 * �ڣ�ѡ��������С�ģ��ͻ�����˼�ֵ��ͬ�ٲ�������
 * ��:�ò�����������װ����е���Ʒ�ܼ�ֵ������Ըò�������ȷ��̰�Ĳ��ԡ�
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
		int length = weight.length;//�������
		double[] price = new double[length];//�Լ۱�
		int[] count = new int[length];//��ʾ�Լ۱ȶ�Ӧ���ǵڼ�����Ʒ
		for(int i=0;i<length;i++) {
			price[i] = (double)value[i]/weight[i];//�õ��Լ۱ȣ�
			count[i]=i;//count�е�i��Ԫ�ض�Ӧ��i����Ʒ
		}
		//���Լ۱������������
		for(int i=0;i<length;i++) {
			for(int j=0;j<j-i-1;j++) {
				if(price[i]<price[i+1]) {
					double temp = price[i];
					price[i]=price[i+1];
					price[i+1]=temp;
					//�����Լ۱�������ٰ���Ž���,����֮��ȡ��
					int tem=count[i];
					count[i]=count[i+1];
					count[i+1]=tem;
				}
				
			}
		}
		//�������ͼ�ֵҲ�����Լ۱ȵ�����˳���Ӧ�ã��浽��������
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
