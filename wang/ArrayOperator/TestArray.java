package com.wang.ArrayOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArray {
	public static void main(String[] args) {
		ArrayOperator array = new ArrayOperator();
		int[] a = {5,7,9,8,6,4,2,1};
		Arrays.sort(a);//数组原有的排序算法
		System.out.println(a[0]);
		int min = array.findMaxMin(a, 0, a.length-1);
		System.out.println(min);
		System.out.println("************");
		int secMax=array.findSecMax(a);
		System.out.println(secMax);
		System.out.println("************");
		int kthMax=array.findKthNumber(a, 3);
		System.out.println("kthMax:"+kthMax);
		System.out.println("************");
		int[] b= {1,-2,4,8,-4,7,-1,-5};
		int maxSum =array.maxSumOfSubArray(b);
		System.out.println("maxSum:"+maxSum);
		System.out.println("************");
		int[] c = {1,1,1,2,3,6,7,8,8,8,8,8};
		System.out.println("valueOfMaxCal:"+array.findMostFrequentInArray(c));
		System.out.println("************");
		int[] d= {1,7,17,2,6,3,14,20};
		int count = array.findCalOfSumOfTwoNumber1(d, 20);
		System.out.println("count:"+count);
		System.out.println("************");
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result = array.kSum(d, 2, 20);
		for(int i=0;i<result.size();i++)
				System.out.println(result.get(i));
		System.out.println("************");
		int[] s = new int[]{1,7,17,2,6,3,14};
	    System.out.println(" A solution set is: ");
	    List<List<Integer>> listArray = new ArrayList<List<Integer>>();
	    listArray = array.kSum(s,2,20);       
	    for (int i = 0; i < listArray.size(); i++) {
	         System.out.println("result:"+listArray.get(i));
	    }
	    System.out.println("************");
	    List<List<Integer>> res = new ArrayList<>();
	    int [] h = {1,2,3};
	    res = array.combinationSum(d, 20, 0);
	    for(int i=0;i<res.size();i++) {
	    	System.out.println(res.get(i));
	    }
	    System.out.println("************");
	    int[] g = {1,2,3,6,4,5,5,5,8,4,4};
	    List<Integer> list = array.getKFrequent(g, 3);
	    for(int i=0;i<list.size();i++) {
	    	System.out.println(list.get(i));
	    }
	    System.out.println("************");
	    int[] I = {1,2,3,4,5,5,6,7,8,9};
	    System.out.println(array.findDuplicateNumber(I));
	    System.out.println("************");
	    int[] j= {1,4,17,3,2,9};
	    System.out.println(array.getMax1(j));
	    System.out.println("--------------");
	    int[] k= {-10,-5,7,15,50};
	    System.out.println(array.getMinAbsoluteValue(k));
	    System.out.println("***************");
	    int[] l= {1,3,5,6};
	    int index= array.searchInsert(l, 2);
	    System.out.println("insert:"+index);
	    
	}
}
