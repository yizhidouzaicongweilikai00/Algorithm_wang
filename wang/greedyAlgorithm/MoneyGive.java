package com.wang.greedyAlgorithm;
/*
 * [����Ǯ����]�����ϰ�Ҫ�Ҹ���99��Ǯ�������������ֵ�ֱ�Ϊ25��10��5��1��Ӳ������
 * Ϊ���Ҹ������ٵ�Ӳ��������ô���ǲ��Ǹ��������أ��ȿ������Ҷ��ٸ�25�ֵģ�
 * ��99��25��3��������3����Ҫ��4���Ļ������ǻ����ٸ��ϰ�һ��1�ֵģ��Ҳ��ɣ�
 * ��ô�ϰ�ֻ�ܸ���3��25�ֵ��������ڻ��ٸ���24�����Ի��ø���2��10�ֵĺ�4��1�֡�
 * */
public class MoneyGive {
	public static void main(String[] args) {
		//����Ǯ
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
