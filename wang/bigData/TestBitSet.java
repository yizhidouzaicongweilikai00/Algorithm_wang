package com.wang.bigData;

import java.util.ArrayList;
import java.util.BitSet;

class TestBitSet extends BitSet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestBitSet() {
		super();
		
	}

	public TestBitSet(int nbits) {
		super(nbits);
		
	}

	public static void main(String[] args) {
		TestBitSet bset = new TestBitSet(20);
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		int[] a = {1,2,2,3,6,7,8,9,9};
		for(int i=0;i<a.length;i++) {
			bset.set(a[i],true);
		}
		for(int i=0;i<20;i++) {
			if(bset.get(i)) {
				System.out.print(i+"  ");
			}
		}
		
		
	}
	
}
