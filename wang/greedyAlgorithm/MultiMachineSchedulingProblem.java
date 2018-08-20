package com.wang.greedyAlgorithm;

/*多机调度问题
 * 设有n个独立的作业{1, 2, …, n}, 由m台相同的机器进行加工处理. 
 * 作业i所需时间为t i. 约定:任何作业可以在任何一台机器上加工处理, 
 * 但未完工前不允许中断处理,任何作业不能拆分成更小的子作业。
 * 要求给出一种作业调度方案，使所给的n 个作业在尽可能短的时间内
 * 由m台机器加工处理完成。 
 * 即多项式复杂程度的非确定性问题
 * 
 * 第一种情况：当作业数n小于等于机器数m的时候，则作业中的最长时间就是最短时间
 * 第二种情况：当作业数n大于等于机器数m的时候，
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
						//对时间排序
						int t = time[j+1];
						time[j+1]=time[j];
						time[j]=t;
					}
				}
			}
			for(int i=0;i<work.length;i++) {
				System.out.println(work[i]+"耗时："+time[i]);
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
