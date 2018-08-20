package com.wang.Sort;

public class TestSort {
	public static void main(String[] args) {
		int[] a = {5,9,7,1,3};
		Sort sort = new Sort();
		sort.insertSort(a);
//		sort.printArray(a);
		int[] B = {2,1,4,3,6,5,8,7,10,9,11,12,14,13};
		int[] b = sort.sortHeapElement(B, B.length, 3);
		sort.printArray(b);
		System.out.println();
		boolean bool = sort.checkIsRepeat3(B);
		System.out.println(bool);
		int[] a1 = {1,3,5,9};
		int[] b1 = {2,4,6,6,7,10};
		int[] result = sort.mergeOrderArray(a1, b1);
		sort.printArray(result);
		System.out.println();
		int[] a2 = {1,0,0,1,1,2,1,2,2};
		int[] array = sort.HollandFlagProblem(a2);
		sort.printArray(array);
		int[][] a3= {{1,2,3},{2,3,4},{4,5,6}};
		System.out.println();
		boolean b3 = sort.findTwoDensionArray(a3, 8);
		System.out.println(b3);
		int[] a4 = {1,5,4,6,7,8,6,5,4,9};
		System.out.println(sort.shortestSubSequence(a4));
		System.out.println(sort.maxGap(a4, a4.length));
		
	}
}
