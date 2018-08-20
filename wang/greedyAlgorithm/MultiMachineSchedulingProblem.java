package com.wang.greedyAlgorithm;

/*�����������
 * ����n����������ҵ{1, 2, ��, n}, ��m̨��ͬ�Ļ������мӹ�����. 
 * ��ҵi����ʱ��Ϊt i. Լ��:�κ���ҵ�������κ�һ̨�����ϼӹ�����, 
 * ��δ�깤ǰ�������жϴ���,�κ���ҵ���ܲ�ֳɸ�С������ҵ��
 * Ҫ�����һ����ҵ���ȷ�����ʹ������n ����ҵ�ھ����̵ܶ�ʱ����
 * ��m̨�����ӹ�������ɡ� 
 * ������ʽ���ӳ̶ȵķ�ȷ��������
 * 
 * ��һ�����������ҵ��nС�ڵ��ڻ�����m��ʱ������ҵ�е��ʱ��������ʱ��
 * �ڶ������������ҵ��n���ڵ��ڻ�����m��ʱ��
 * */

public class MultiMachineSchedulingProblem {
	public static void main(String[] args) {
		int[] work = {1,2,3,4,5,6,7};
		int[] time = {2,14,4,16,6,5,3};
		int maxtime = multiMachineSchedulingProblem(work,time,3);
		System.out.println(maxtime);
	}
	public static int multiMachineSchedulingProblem(int[] work,int[] time,int m) {
		int[] machine = new int[m];
		if(work.length<=m) {
			int maxtime=0;
			for(int i=0;i<time.length;i++) {
				if(maxtime<time[i]) {
					maxtime=time[i];
				}
			}
			return maxtime;
		}
		if(work.length>m) {
			for(int i=0;i<work.length-1;i++) {
				for(int j=0;j<work.length-i-1;j++) {
					if(time[j]<time[j+1]) {
						//��ʱ������
						int t = time[j+1];
						time[j+1]=time[j];
						time[j]=t;
					}
				}
			}
			for(int i=0;i<work.length;i++) {
				System.out.println(work[i]+"��ʱ��"+time[i]);
			}
			for(int i=0;i<m;i++) {
				machine[i]=time[i];
			}
			int maxtime = time[0];
			int min_machine=0;
			int min_time =0;
			for(int i=m;i<work.length;i++) {
				min_machine = machine[0];
				min_time=time[0];
				for(int j=0;j<m;j++) {
					if(min_time>machine[j]) {
						min_time=machine[j];
						min_machine=j;
					}
				}
				machine[min_machine]+=time[i];
				if(maxtime<machine[min_machine]) {
					maxtime = machine[min_machine];
				}
			}
			return maxtime;
		}
		return -1;	
	}
}
