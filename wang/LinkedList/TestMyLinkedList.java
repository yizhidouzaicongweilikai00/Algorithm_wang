package com.wang.LinkedList;

public class TestMyLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> ml = new MyLinkedList<Integer>(null);
		ml.headInsert(10);
		ml.headInsert(20);
		
		ml.tailInsert(50);
		ml.tailInsert(60);
		
		ml.posInsert(2, 90);
		ml.show();
	}
}
