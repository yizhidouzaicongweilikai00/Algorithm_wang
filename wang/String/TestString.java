package com.wang.String;

import com.wang.binaryTree.BinaryTree;

public class TestString {
	public static void main(String[] args) {
	//�ж��жϵڶ����Ƿ��ǵ�һ����������	
		int[] arr = {8,8,9,2,4,7};
		int[] subArray = {9};
		BinaryTree root1=new BinaryTree(arr[0]);
		for(int i=1;i<arr.length;i++) {//��������в�������
			root1.insert(root1, arr[i]);
		}
		BinaryTree root2=new BinaryTree(subArray[0]);
		for(int i=1;i<subArray.length;i++) {//��������в�������
			root2.insert(root2, subArray[i]);
			}
		root1.preOrder(root1);
		System.out.println();
		root2.preOrder(root2);
		System.out.println();
		System.out.println("********�жϵڶ����Ƿ��ǵ�һ����������********");
		StringOperator strO = new StringOperator();
		boolean b = strO.HasSubTree(root1,root2);
		System.out.println(b);
		//�ж������ַ����Ƿ��Ǳ��δ�
		String str1="123";
		String str2="312";
		System.out.println("�ж������ַ����ǲ��Ǳ��δʣ�"+strO.isDeformation(str1, str2));
		//�ж�һ���ַ����������һ���ַ�����������
		System.out.println("�ж�һ���ַ����Ƿ������һ���ַ�����"+strO.matchString(str1, str2));
		//kmp�㷨ʵ��
		System.out.println("ʹ��kmp�㷨�жϣ�"+strO.kmp(str1, str2));
		//�ж��ǲ�����ת��
		System.out.println("�ж��Ƿ�Ϊ��ת�ʣ�"+strO.isRotation(str1, str2));
		//���ַ�������
		String str= "you are me";
		System.out.println("�ַ�������"+strO.ReverseString(str));
		//������ظ��ַ���
		System.out.println("������ظ��ַ�����"+strO.lengthOfLongestSubString("adbeabcabb"));
	}
}