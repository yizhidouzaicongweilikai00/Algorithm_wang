package com.wang.bigData;

import java.util.BitSet;

public class BigMap {
	public static void main(String[] args) {
		//����ǰ�ڴ�
		long beforeMemory = Runtime.getRuntime().totalMemory();
		long start1 = System.currentTimeMillis();
		//����20�ڸ�bitλ
		BitSet set = new BitSet(2000000000);
		for(int i=0;i<2000000000;i++) {
			if(i==898989) {
				set.set(i,true);
			}
		}
		//����20�ڸ�������ռ�ڴ�
		long afterMemory = Runtime.getRuntime().totalMemory();
		long end = System.currentTimeMillis();
		System.out.println("���ڴ�ʹ��"+(afterMemory-beforeMemory)/1024/1024+"MB");
		System.out.println("�����ڴ��ʱ:"+(end-start1)+"����");  
		long start2 = System.currentTimeMillis();  
		boolean isExit1=set.get(898989);  
		boolean isExit2=set.get(900000);    
		long end2 = System.currentTimeMillis();  
		  /*�����20�ڸ������ж�898989�Ƿ����������*/  
		  System.out.println(isExit1);  
		  System.out.println("20������"+(isExit1?"����":"������")+898989);  
		  System.out.println("20������"+(isExit2?"����":"������")+900000);  
		  System.out.println("��ѯ��ʱ:"+(end2 - start2)+"����");  
	   System.out.println(set.get(12));
	}
}
