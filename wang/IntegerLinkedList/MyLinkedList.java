package com.wang.IntegerLinkedList;

import java.util.ArrayList;
import java.util.Hashtable;
/*
 * 
 * 1.public Node getHead();
 * 2.public void setHead(Node head);
 * 3.public Node insertTailNode(Node newNode);
 * 4.public void headInsert(int data);
 * 5.public void tailInsert(int data);
 * 6.public int getLength();
 * 7.public int getLength();
 * 8.public void posInsert(int pos,int data);
 * 9.public void pop(int data);
 * 10.public void show();
 * 11.public Node orderList();
 * 12.public void deleteDuplecate();
 * 13.public Node findElem(int k);
 * 14.public void ReverseIteratively();
 * 15.public void printListReversely(Node newNode);
 * 16.public ArrayList<Integer> SearchMid();
 * 17.public boolean isLoop();
 * 18.public Node findLoopPort();
 * 19.public boolean deleteNode(Node n);
 * 20.public boolean isIntersect(Node first,Node second);
 * 21.public Node getFirstMeetNode(Node head1,Node head2)
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */




public class MyLinkedList {
	Node head = null;//����ͷ�����ã�����û��ʵ����
	
	/*
	 * �������ͷhead
	 * */
	public Node getHead(){
		return head;
	}
	/*
	 * ��������ͷhead
	 * */
	public void setHead(Node head) {
		this.head=head;
	}
	/*
	 * �������ʼ��,��дMyLinkedList���췽��
	 * ����ʱ�᷵��һ������ͷΪdata������
	 * */
	public MyLinkedList(int data) {
		this.head=new Node(data);
	}
	/*
	 * ֱ�Ӳ���һ�����
	 * */
	public Node insertTailNode(Node newNode) {
		Node tempNode=head;
		while(tempNode.next!= null) {
			tempNode = tempNode.next;
		}
		tempNode.next=newNode;
		newNode.next=null;
		return head;
		
	}
	/*
	 * �������в�������
	 * param d:�������ݵ����ݲ��뵽�����ͷ��
	 * */
	public void headInsert(int data) {
		Node node = new Node(data);//���ȴ���һ���µ�node
		//�ȼ���node=head.next;
		node.next=head.next;
		head.next=node;
	}
	/*
	 * β�巨,���뵽�����β��
	 * */
	public void tailInsert(int data) {
		Node newNode = new Node(data);
		Node tempNode = head;
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
		Node tempNode = head;
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
	public void posInsert(int pos,int data) {
		if(pos<0||pos>getLength()) {
			return;
		}
		Node newNode = new Node(data);
		Node tempNode = head;
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
	public void pop(int data) {
		Node newNode =head;
		Node tempNode = newNode.next;
		
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
		Node newNode = head;
		while(newNode!=null) {
			System.out.println("data:"+newNode.data);
			newNode=newNode.next;
		}
	}
	/*
	 * ������������򣬷���ͷ���
	 * */
	public Node orderList(){
		Node nextNode=null;
		int temp=0;
		Node curNode = head;
		while(curNode.next!=null) {
			nextNode = curNode.next;
			while(nextNode!=null) {
				if(curNode.data>nextNode.data) {
					temp = curNode.data;
					curNode.data = nextNode.data;
					nextNode.data = temp;
				}
				nextNode = nextNode.next;
			}
			curNode = curNode.next;
		}
		return head;
	}
	/*
	 * ��δ�������ɾ���ظ�Ԫ��
	 * ����˫��ѭ����������ѭ������������������ѭ����ǰ�ڵ�Ϊcur����ѭ����cur��ʼ������
	 * ��������cur��ָ����ֵ��ȣ���ɾ������ظ���㡣
	 * ʱ�临�ӶȽϸ�
	 * */
	public void deleteDuplecate() {
		// TODO Auto-generated method stub
		Node p = head;
		while(p!=null) {
			Node q=p;
			while(q.next!=null) {
				if(p.data==q.data) {
					q.next=q.next.next;
				}
				else {
					q=q.next;
				}
				p=p.next;
			}
		}
	}
	//�ڶ��ַ�����ʱ�临�ӶȽϵͣ��ռ临�ӶȽϸ�
	public void deleteDuplecate1() {
		Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
		Node temp = head;
		Node pre = null;
		while(temp!=null) {
			if(table.containsKey(temp.data)) {
				pre.next=temp.next;
			}else {
				table.put(temp.data, 1);
				pre=temp;
			}
			temp=temp.next;
		}
	}
	/*
	 * �ҵ�����k��Ԫ��
	 * 3�ֽⷨ��
	 * 1.���ȱ���һ����������������ܳ��ȣ�Ȼ�󽫵�����k����ת����������k��
	 * 2.��ͷ��β�ķ����ĳ��Ԫ�ؿ�ʼ������k��Ԫ�غ�պõ�������β������ô��Ԫ�ؾ���Ҫ�ҵĵ�����k��Ԫ��
	 * 3.��������ָ�룬������һ��ָ�����һ��ָ����ǰ��k����Ȼ������ָ��ͬʱ�ƶ���������ָ��Ϊnullʱ������ָ�뼴ΪҪ�ҵ�λ��
	 * 
	 * */
	public Node findElem(int k) {
		if(k<0||k>this.getLength()) {
			return null;
		}
		Node p1=head;
		Node p2=head;
		for(int i=0;i<k;i++) {
			p1=p1.next;
		}
		while(p1!=null) {
			p1=p1.next;
			p2=p2.next;
		}
		return p2;
	}
	/*
	 * ���ʵ������ķ�ת
	 * Ϊ�˷�תһ��������Ҫ����ָ���ָ��
	 * */
	public void ReverseIteratively() {
		Node pReverseHead = head;
		Node pNode = head;
		Node pPrev = null;
		while(pNode!=null) {
			Node pNext = pNode.next;
			if(pNext==null) {
				pReverseHead=pNode;
			}
			pNode.next=pPrev;
			pPrev=pNode;
			pNode=pNext;
		}
		this.head=pReverseHead;
	}
	/*
	 * ��β��ͷ���������
	 * ���õݹ�
	 * */
	public void printListReversely(Node newNode) {
		if(newNode!=null) {
			printListReversely(newNode.next);//�ݹ��ߵ�������ǲ����У������ȴ洢�ţ��ȵ���ݹ���ֹ�������᷵����һ��һ����ִ�еݹ��ߵĳ���
			System.out.println(newNode.data);
		}
	}
	/*
	 * ���Ѱ�ҵ�������ص㣺
	 * ���ʱ˫������������β���У���������ָ�룬һ����ͷ��β������һ����β��ͷ������
	 * ������ָ������ʱ�����ҵ����м�Ԫ�ء��Դ�˼��Ϊ����������ǵ�����Ҳ���Բ���˫ָ��ķ�ʽ��ʵ���м�ڵ�Ŀ��ٲ��ң�
	 * ������ԣ���һ����������ָ��ͬʱ��ͷ��ʼ�������ڶ�����һ����ָ��һ����������һ����ָ��һ����һ����
	 * ����������ָ���ȵ�����β��������ָ����ǡ�õ��������в���
	 * ��������Ϊ����ʱ����ָ��ָ���ʱ�����м�ָ�룬
	 * ��������Ϊż��ʱ����ָ��ָ��Ľ�����ָ��ָ��Ľ�����һ����㶼��������м���
	 * */
	public ArrayList<Integer> SearchMid() {
		Node fast=head;
		Node slow=head;
		ArrayList<Integer> list = new ArrayList<Integer>();
		int k = getLength();
		System.out.println("****");
		System.out.println(k);
		System.out.println("****");
		while(fast!=null&&fast.next!=null&&fast.next.next!=null) {
			fast=fast.next.next;
			slow=slow.next;
		}
		if(k%2==0) {
			list.add(slow.data);
			list.add(slow.next.data);
			return list;
		}else {
			list.add(slow.data);
			return list;
		}
	}
	/*
	 * ��μ��һ�������Ƿ��л�
	 * �������л���ָ��������һ������next����ָ����head���
	 * �ⷨ����������ָ��fast��slow�����У�fastΪ��ָ�룬slowΪ��ָ��
	 * slowÿǰ��һ����fastǰ������������ָ��ͬʱ��ǰ�ƶ�������ָ��ÿ�ƶ�һ�ζ�Ҫ��
	 * ��ָ��Ƚϣ�ֱ������ָ����� ��ָ��Ϊֹ����֤����������Ǵ����ĵ�����������֤��
	 * ��������ǲ�������ѭ������fast���е���β��Ϊnull����Ϊ�޻�����
	 * */
	public boolean isLoop() {
		Node fast=this.head;
		Node slow=this.head;
		if(fast==null) {
			return false;
		}
		while(fast!=null&&fast.next!=null&&fast.next.next!=null) {
			fast=fast.next.next;
			slow=slow.next;
			if(fast==slow) {
				return true;
			}
		}
		return !(fast==null||fast.next==null||fast.next.next==null);
	}
	/*
	 * ����л�������ҵ�������ڵ�ַ��
	 * 1.���ж��Ƿ��л���
	 * 2.Ȼ�����ҳ���ڵ�
	 * 
	 * �ⷨ��
	 * ������ͷ��������ֱ���һ��ָ�룬ÿ�θ���һ��������ָ��ض�������������
	 * ��һ��Ϊ����ڵ㡣
	 * */
	public Node findLoopPort() {
		Node slow=head;
		Node fast=head;
		if(fast==null) {
			return null;
		}
		while(fast!=null&&fast.next!=null&&fast.next.next!=null) {
			fast=fast.next.next;
			slow=slow.next;
			if(fast==slow) {
				break;
			}
		}
		if(fast==null||fast.next==null||fast.next.next==null) {
			return null;
		}
		//�ϱ��ж��Ƿ��л�
		slow=head;
		while(slow!=fast) {
			fast=fast.next;
			slow=slow.next;
		}
		return slow;
	}
	
	/*
	 * ����ڲ�֪��ͷָ��������ɾ��ָ�����
	 * 1.����ɾ�����Ϊ�����β�ڵ㣬���޷�ɾ������Ϊɾ�����޷�ʹǰ������nextָ��null;
	 * 2.����ɾ���Ľ�㲻��β�ڵ㣬�����ͨ�����������������̽ڵ��ֵ��Ȼ��ɾ����̽ڵ�
	 * */
	public boolean deleteNode(Node n) {
		if(n==null||n.next==null) {
			return false;
		}
		int temp=n.data;
		n.data = n.next.data;
		n.next.data=temp;
		n.next=n.next.next;
		return true;
	}
	/*
	 * ����ж����������Ƿ��ཻ
	 * ���������ཻ��ָ�ཻ��֮����ܲ�ͬ�����ཻ��֮������Ľ����ͬ
	 * ������ʵ��˼·�ǣ�
	 * �ֱ��������������¼���ǵ�β�ڵ㣬��ô�����������ཻ�������ཻ��
	 * 
	 * */
	public boolean isIntersect(Node first,Node second) {
		if(first==null||second==null) {
			return false;
		}
		//�ҵ���һ�������β�ڵ�
		while(first.next!=null) {
			first=first.next;
		}
		//�ҵ��ڶ��������β�ڵ�
		while(second.next!=null) {
			second=second.next;
		}
		return first==second;
	}
	/*
	 * ������������ཻ������ҵ������ཻ�ĵ�һ�����
	 * ���������
	 * 1.���ȷֱ������������ĳ���len1��len2������len1>len2����
	 * 2.�����ȶ��������Ǹ�������len1-len2������㵽���p
	 * 3.��ʱ���p��head2���ཻ���ľ�����ͬ��
	 * 4.��ʱͬʱ������������ֱ��������ͬ�Ľ��Ϊֹ����������������ཻ�Ľ��
	 * */
	public Node getFirstMeetNode(Node head1,Node head2) {
		if(head1==null||head2==null) {
			return null;
		}
		Node tail1=head1;
		int len1=0;
		while(tail1.next!=null) {
			tail1=tail1.next;
			len1++;
		}
		Node tail2=head2;
		int len2=0;
		while(tail2.next!=null) {
			tail2=tail2.next;
			len2++;
		}
		if(tail1!=tail2) {
			return null;
		}
		Node tempNode1=head1;
		Node tempNode2=head2;
		//�ҳ��ϳ��������ȱ���len1-len2�����
		if(len1>len2) {
			int d=len1-len2;
			while(d!=0) {
				tempNode1=tempNode1.next;
				d--;
			}
		}else {
			int d=len2-len1;
			while(d!=0) {
				tempNode2=tempNode2.next;
				d--;
			}
		}
		while(tempNode1!=tempNode2) {
			tempNode1=tempNode1.next;
			tempNode2=tempNode2.next;
		}
		return tempNode1;
	}
	
}
