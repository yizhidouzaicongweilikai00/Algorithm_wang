package com.wang.LinkedList;

public class MyLinkedList<T> {
	Node<T> head = null;//����ͷ�����ã�����û��ʵ����
	
	/*
	 * �������ͷhead
	 * */
	public Node<T> getHead(){
		return head;
	}
	/*
	 * ��������ͷhead
	 * */
	public void setHead(Node<T> head) {
		this.head=head;
	}
	/*
	 * �������ʼ��,��дMyLinkedList���췽��
	 * ����ʱ�᷵��һ������ͷΪdata������
	 * */
	public MyLinkedList(T data) {
		this.head=new Node<T>(data);
	}
	/*
	 * �������в�������
	 * param d:�������ݵ����ݲ��뵽�����ͷ��
	 * */
	public void headInsert(T data) {
		Node<T> node = new Node<T>(data);//���ȴ���һ���µ�node
		//�ȼ���node=head.next;
		node.next=head.next;
		head.next=node;
	}
	/*
	 * β�巨,���뵽�����β��
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
	 * ��ȡ����ĳ���
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
	 * ָ��λ�ò�������
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
		//����ͷ�巨�����ƶ�λ��
		newNode.next=tempNode.next;
		tempNode.next=newNode;
	}
	/*
	 * ɾ��
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
	 * ������ӡ����
	 * */
	public void show() {
		Node<T> newNode = head;
		while(newNode!=null) {
			System.out.println("data:"+newNode.data);
			newNode=newNode.next;
		}
	}
//	/*
//	 * ������������򣬷���ͷ���
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
