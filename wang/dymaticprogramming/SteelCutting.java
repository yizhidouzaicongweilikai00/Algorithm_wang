package com.wang.dymaticprogramming;

public class SteelCutting {
	/**
     * ��̬�滮���Ե�������⡣
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @return �������
     */
	public static void main(String[] args) {
		int[] p = {0,1,5,8,9,10,17,17,20,24,30,31,32};//�����۸��
		int n = 12;//��������
		int[] r = new int[n+1];//һ�����飬��r[i]��ʾ����Ϊi�ĸ��������Ž⣬��ʼֵ��Ϊ0
		for(int i=0;i<r.length;i++) {
			r[i]=0;
		}
		int q=-1;
		//�󳤶�Ϊj��ʱ������Ž�
		for(int j=1;j<=n;j++) {
			/*������Ϊ1ʱ��r[1]=r[0]+p[1];
			 *������Ϊ2ʱ��r[2]=r[1]+p[1],r[2]=p[2];
			 *������Ϊ3ʱ��r[3]=r[2]+p[1],r[3]=r[1]+p[2],r[3]=p[3];
			 *������Ϊ4ʱ��r[4]=r[3]+p[1],r[4]=r[2]+p[2],r[4]=r[1]+p[3],r[4]=p[4]+r[0]
			 *������Ϊiʱ��r[i]=r[i-1]+p[1],r[i]=r[i-2]+p[2],r[i]=r[i-3]+p[3],...,r[i]=r[1]+p[i-1]
			 * */
			for(int i=1;i<=j;i++) {
				q=Math.max(q,r[j-i]+p[i]);
			}
			//��¼����Ϊj������ֵ
			r[j]=q;
		}
		for(int j=1;j<=n;j++) {
		System.out.println("����Ϊ"+j+"ʱ���������Ϊ��"+r[j]);
		}		
	}
	
}
