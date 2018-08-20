package com.wang.LinkedList;

public class MyLinkedList<T> {
	Node<T> head = null;//链表头的引用，这里没有实例化
	
	/*
	 * 获得链表头head
	 * */
	public Node<T> getHead(){
		return head;
	}
	/*
	 * 设置链表头head
	 * */
	public void setHead(Node<T> head) {
		this.head=head;
	}
	/*
	 * 单链表初始化,重写MyLinkedList构造方法
	 * 创建时会返回一个链表头为data的链表
	 * */
	public MyLinkedList(T data) {
		this.head=new Node<T>(data);
	}
	/*
	 * 向链表中插入数据
	 * param d:插入数据的内容插入到链表的头部
	 * */
	public void headInsert(T data) {
		Node<T> node = new Node<T>(data);//首先创建一个新的node
		//等价于node=head.next;
		node.next=head.next;
		head.next=node;
	}
	/*
	 * 尾插法,插入到链表的尾部
	 * */
	public void tailInsert(T data) {
		Node<T> newNode = new Node<T>(data);
		Node<T> tempNode = head;
		while(tempNode.next!= null) {
			tempNode = tempNode.next;
		}
		tempNode.next=newNode;
		newNode.next=null;
	}
	/*
	 * 获取链表的长度
	 * */
	public int getLength() {
		Node<T> tempNode = head;
		int length = 0;
		while(tempNode!=null) {
			length++;
			tempNode=tempNode.next;
		}
		
		return length;
	}
	/*
	 * 指定位置插入数据
	 * */
	public void posInsert(int pos,T data) {
		if(pos<0||pos>getLength()) {
			return;
		}
		Node<T> newNode = new Node<T>(data);
		Node<T> tempNode = head;
		for(int i=0;i<=pos-1;i++) {
			tempNode = tempNode.next;
		}
		//采用头插法插入制定位置
		newNode.next=tempNode.next;
		tempNode.next=newNode;
	}
	/*
	 * 删除
	 * */
	public void pop(T data) {
		Node<T> newNode =head;
		Node<T> tempNode = newNode.next;
		
		while(tempNode!=null) {
			if(tempNode.data==data) {
				newNode.next=tempNode.next;
				break;
			}
			newNode = newNode.next;
			tempNode = newNode.next;
			
		}
	}
	/*
	 * 遍历打印链表
	 * */
	public void show() {
		Node<T> newNode = head;
		while(newNode!=null) {
			System.out.println("data:"+newNode.data);
			newNode=newNode.next;
		}
	}
//	/*
//	 * 对链表进行排序，返回头结点
//	 * */
//	public Node<T> orderList(){
//		Node<Integer> nextNode=null;
//		int temp=0;
//		int t = (Integer)head.data;
//		System.out.println("head.data"+t);
//		Node<T> newHead = (Node<T>)head;
//		
//		Node<T> curNode = newHead;
//		while(curNode.next!=null) {
//			curNode.data = (T)curNode.data;
//		}
//		while(curNode.next!=null) {
//			nextNode = curNode.next;
//			while(nextNode!=null) {
//				if(curNode.getData()>nextNode.getData()) {
//					temp = curNode.data;
//					curNode.data = nextNode.data;
//					curNode.data = temp;
//				}
//				nextNode = nextNode.next;
//			}
//			curNode = curNode.next;
//		}
//		return newHead;
//	}
	
}
