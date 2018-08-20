package com.wang.IntegerLinkedList;

import java.util.ArrayList;

public class TestMyLinkedList {
	public static void main(String[] args) {
		MyLinkedList ml = new MyLinkedList(20);
		ml.headInsert(10);
		ml.headInsert(20);
		ml.tailInsert(50);
		ml.tailInsert(60);
		ml.posInsert(2, 90);
		ml.orderList();
		ml.deleteDuplecate();
		ml.show();
		Node m2=ml.findElem(2);
		System.out.println(m2.data);
		ml.ReverseIteratively();
		ml.show();
		System.out.println();
		//找到中间结点
		ArrayList<Integer> mid = ml.SearchMid();
		for(int i=0;i<mid.size();i++) {
			System.out.println(mid.get(i));
		}
		
		//判断是否有环
		boolean b = ml.isLoop();
		System.out.println(b);
		Node n1=new Node(10);
		ml.insertTailNode(n1);
		Node n2=new Node(90);
		ml.insertTailNode(n2);
		Node n3=new Node(80);
		ml.insertTailNode(n3);
		ml.show();
		ml.deleteNode(n1);
		ml.show();
	
		
		
	}
}
