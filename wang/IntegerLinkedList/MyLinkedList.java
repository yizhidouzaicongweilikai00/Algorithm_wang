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
	Node head = null;//链表头的引用，这里没有实例化
	
	/*
	 * 获得链表头head
	 * */
	public Node getHead(){
		return head;
	}
	/*
	 * 设置链表头head
	 * */
	public void setHead(Node head) {
		this.head=head;
	}
	/*
	 * 单链表初始化,重写MyLinkedList构造方法
	 * 创建时会返回一个链表头为data的链表
	 * */
	public MyLinkedList(int data) {
		this.head=new Node(data);
	}
	/*
	 * 直接插入一个结点
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
	 * 向链表中插入数据
	 * param d:插入数据的内容插入到链表的头部
	 * */
	public void headInsert(int data) {
		Node node = new Node(data);//首先创建一个新的node
		//等价于node=head.next;
		node.next=head.next;
		head.next=node;
	}
	/*
	 * 尾插法,插入到链表的尾部
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
	 * 获取链表的长度
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
	 * 指定位置插入数据
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
		//采用头插法插入制定位置
		newNode.next=tempNode.next;
		tempNode.next=newNode;
	}
	/*
	 * 删除
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
	 * 遍历打印链表
	 * */
	public void show() {
		Node newNode = head;
		while(newNode!=null) {
			System.out.println("data:"+newNode.data);
			newNode=newNode.next;
		}
	}
	/*
	 * 对链表进行排序，返回头结点
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
	 * 如何从链表中删除重复元素
	 * 进行双重循环遍历，外循环正常遍历，假设外循环当前节点为cur，内循环从cur开始遍历，
	 * 若碰到与cur所指向结点值相等，则删除这个重复结点。
	 * 时间复杂度较高
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
	//第二种方法，时间复杂度较低，空间复杂度较高
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
	 * 找倒数第k个元素
	 * 3种解法：
	 * 1.首先遍历一遍链表，求出单链表总长度，然后将倒数第k个，转化成正数第k个
	 * 2.从头到尾的方向的某个元素开始，遍历k个元素后刚好到达链表尾部，那么该元素就是要找的倒数第k个元素
	 * 3.定义两个指针，让其中一个指针比另一个指针先前移k步，然后两个指针同时移动，则先行指针为null时，后行指针即为要找的位置
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
	 * 如何实现链表的反转
	 * 为了反转一个链表，需要调整指针的指向，
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
	 * 从尾到头输出单链表
	 * 利用递归
	 * */
	public void printListReversely(Node newNode) {
		if(newNode!=null) {
			printListReversely(newNode.next);//递归后边的输出不是不进行，而是先存储着，等到达递归终止条件，会返回来一条一条的执行递归后边的程序。
			System.out.println(newNode.data);
		}
	}
	/*
	 * 如何寻找单链表的重点：
	 * 如果时双向链表，可以首尾并行，利用两个指针，一个从头到尾遍历，一个从尾到头遍历，
	 * 当两个指针相遇时，就找到了中间元素。以此思想为基础，如果是单链表，也可以采用双指针的方式来实现中间节点的快速查找；
	 * 具体而言，第一步，有两个指针同时从头开始遍历，第二步，一个快指针一次走两步，一个慢指针一次走一步，
	 * 第三步，快指针先到链表尾部，而慢指针则恰好到达链表中部。
	 * 当链表长度为奇数时，慢指针指向的时链表中间指针，
	 * 当链表长度为偶数时，慢指针指向的结点和慢指针指向的结点的下一个结点都是链表的中间结点
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
	 * 如何检测一个链表是否有环
	 * 单链表有环是指链表的最后一个结点的next重新指向了head结点
	 * 解法：定义两个指针fast和slow，其中，fast为快指针，slow为慢指针
	 * slow每前进一步，fast前进两步，两个指针同时向前移动，，快指针每移动一次都要跟
	 * 慢指针比较，直到当快指针等于 慢指针为止，就证明这个链表是带环的单向链表，否则，证明
	 * 这个链表是不带环的循环链表（fast先行到达尾部为null，则为无环链表）
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
	 * 如果有环，如何找到环的入口地址，
	 * 1.先判断是否有环，
	 * 2.然后再找出入口点
	 * 
	 * 解法：
	 * 在链表头和相遇点分别设一个指针，每次各走一步，两个指针必定相遇，且相遇
	 * 第一点为环入口点。
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
		//上边判断是否有环
		slow=head;
		while(slow!=fast) {
			fast=fast.next;
			slow=slow.next;
		}
		return slow;
	}
	
	/*
	 * 如何在不知道头指针的情况下删除指定结点
	 * 1.若待删除结点为链表的尾节点，则无法删除，因为删除后无法使前驱结点的next指向null;
	 * 2.若待删除的结点不是尾节点，则可以通过交换这个结点与其后继节点的值，然后删除后继节点
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
	 * 如何判断两个链表是否相交
	 * 两个链表相交是指相交点之间可能不同，但相交点之后链表的结点相同
	 * ：所以实现思路是：
	 * 分别遍历两个链表，记录他们的尾节点，那么这两个链表相交，否则不相交。
	 * 
	 * */
	public boolean isIntersect(Node first,Node second) {
		if(first==null||second==null) {
			return false;
		}
		//找到第一个链表的尾节点
		while(first.next!=null) {
			first=first.next;
		}
		//找到第二个链表的尾节点
		while(second.next!=null) {
			second=second.next;
		}
		return first==second;
	}
	/*
	 * 如果两个链表相交，如何找到他们相交的第一个结点
	 * 解决方法：
	 * 1.首先分别计算两个链表的长度len1和len2，假设len1>len2，、
	 * 2.接着先对链表大的那个遍历（len1-len2）个结点到结点p
	 * 3.此时结点p和head2到相交结点的距离相同，
	 * 4.此时同时遍历两个链表，直到遇到相同的结点为止，这个结点就是他们相交的结点
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
		//找出较长的链表，先遍历len1-len2个结点
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
