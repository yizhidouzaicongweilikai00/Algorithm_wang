package com.wang.String;

import com.wang.binaryTree.BinaryTree;

public class TestString {
	public static void main(String[] args) {
	//判断判断第二树是否是第一个树的子树	
		int[] arr = {8,8,9,2,4,7};
		int[] subArray = {9};
		BinaryTree root1=new BinaryTree(arr[0]);
		for(int i=1;i<arr.length;i++) {//向二叉树中插入数据
			root1.insert(root1, arr[i]);
		}
		BinaryTree root2=new BinaryTree(subArray[0]);
		for(int i=1;i<subArray.length;i++) {//向二叉树中插入数据
			root2.insert(root2, subArray[i]);
			}
		root1.preOrder(root1);
		System.out.println();
		root2.preOrder(root2);
		System.out.println();
		System.out.println("********判断第二树是否是第一个树的子树********");
		StringOperator strO = new StringOperator();
		boolean b = strO.HasSubTree(root1,root2);
		System.out.println(b);
		//判断两个字符串是否是变形词
		String str1="123";
		String str2="312";
		System.out.println("判断两个字符串是不是变形词："+strO.isDeformation(str1, str2));
		//判断一个字符串否包含另一个字符串，暴力法
		System.out.println("判断一个字符串是否包含另一个字符串："+strO.matchString(str1, str2));
		//kmp算法实现
		System.out.println("使用kmp算法判断："+strO.kmp(str1, str2));
		//判断是不是旋转词
		System.out.println("判断是否为旋转词："+strO.isRotation(str1, str2));
		//将字符串逆序
		String str= "you are me";
		System.out.println("字符串逆序："+strO.ReverseString(str));
		//最大无重复字符串
		System.out.println("最大无重复字符串："+strO.lengthOfLongestSubString("adbeabcabb"));
	}
}