package com.wang.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
	public BinaryTree leftChild;//左子树
	public BinaryTree rightChild;//右子树
	public int data;//数据域
	
	
	//重写构造函数：
	public BinaryTree(int data) {
		super();
		this.leftChild=null;
		this.rightChild=null;
		this.data=data;
	}
	//重写构造函数
	public BinaryTree() {
		super();
	}
	//插入创建二叉树
	public void insert(BinaryTree root,int data) {
		if(data>root.data) {
			//如果插入得结点大于根结点
			if(root.rightChild==null) {
			//如果右子树为空，就插入，如果不为空，就再创建一个结点
				root.rightChild=new BinaryTree(data);//就把插入的节点放在右边
			}else {
				this.insert(root.rightChild, data);
			}
		}else {//如果插入得节点小于等于根节点
			if(root.leftChild==null) {
			//如果左子树为空，就插入，如果不为空就再创建一个节点
				root.leftChild = new BinaryTree(data);//就把插入得节点放在左边
			}else {
				this.insert(root.leftChild, data);
			}
		}
	}
	
	/*二叉树遍历*/
	
	//先序遍历
	public void preOrder(BinaryTree root) {
		
		if(root!=null) {
			System.out.print(root.data+"  ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}
	//中序遍历
	public void inOrder(BinaryTree root) {
		if(root!=null) {
			inOrder(root.leftChild);
			System.out.print(root.data+" ");
			inOrder(root.rightChild);
		}
	}
	//后序遍历,递归思想
	public void afterOrder(BinaryTree root) {
		if(root!=null) {
			afterOrder(root.leftChild);
			afterOrder(root.rightChild);
			System.out.print(root.data+" ");
		}
	}
	/*
	 * 有一棵二叉树，请设计一个算法，按照层次打印这颗二叉树
	 * 给定二叉树得根节点，请返回打印结果，结果按照每一层一个数组进行存储
	 * 所有数组的顺序按照层数从上往下，且每一层的数组内元素按照从左往右排列。
	 * 保证结点数小于等于500。
	 * 思路：
	 * 借用一个队列：以层次遍历得思想来解答，关键在于，判断什么时候该换层了
	 * 1.用last指向当前正在打印得行得最右边得节点，即队列中最先输入得节点
	 * 2.用nlast指向当前入队得节点
	 * 3.从根结点开始，将其左右孩子以层次遍历的方式入队。每入队一个元素，即让nLast指向这个元素，
	 * 将队首元素temp出队到一个ArrayList中，当temp==last时，代表该换行了，也就是将当前ArrayList
	 * 放到一个ArrayLists中，再讲当前的ArrayList清空。然后last=nLast；
	 * 
	 * */
	public int[][]printTree(BinaryTree root){
		if(root == null) {
			return null;
		}
		int[][] result = null;
		BinaryTree last = root;
		BinaryTree nlast = null;
		BinaryTree temp = null;
		ArrayList<Integer> array  =  new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> arrays = new  ArrayList<>();
		LinkedList<BinaryTree> queue = new LinkedList<>();
		queue.add(last);
		while(!queue.isEmpty()) {
			temp=queue.poll();
			array.add(temp.data);
			if(temp.leftChild!=null) {
				queue.add(temp.leftChild);//把左子树加入
				nlast=temp.leftChild;//让nlast等于新加入得值
			}
			if(temp.rightChild!=null) {
				queue.add(temp.rightChild);//把右子树加入
				nlast=temp.rightChild;//让nlast等于新加入的值
			}
			if(temp==last) {
				arrays.add(array);
				array=new ArrayList<>();
				last=nlast;
			}
		}
		//上边步骤将各行数据存储到ArrayList中了
		result = new int[arrays.size()][];
		for(int i=0;i<arrays.size();i++) {
			result[i]=new int[arrays.get(i).size()];//重新定义了数组的大小
			for(int j=0;j<arrays.get(i).size();j++) {
				result[i][j] = arrays.get(i).get(j);
			}
		}
		return result;
	}
	/*
	 * 对于一颗二叉树，深度优先搜索(Depth First Search)是沿着树的深度遍历树的节点，
	 * 尽可能深的搜索树的分支。以上面二叉树为例，深度优先搜索的顺序为：ABDECFG。
	 * 怎么实现这个顺序呢 ？
	 * 1.深度优先搜索二叉树是先访问根结点，然后遍历左子树接着是遍历右子树，
	 * 因此我们可以利用堆栈的先进后出的特点，现将右子树压栈，再将左子树压栈，这样左子树就位于栈顶，
	 * 可以保证结点的左子树先与右子树被遍历。
	 * 
	 * 
	 *2. 广度优先搜索(Breadth First Search),又叫宽度优先搜索或横向优先搜索，
	 * 是从根结点开始沿着树的宽度搜索遍历，上面二叉树的遍历顺序为：ABCDEFG.
	 * 可以利用队列实现广度优先搜索。
	 * 
	 * */
	//深度优先搜索，相当于先序遍历，非递归，使用栈实现，先入后出
	public void depthOrderTraversal(BinaryTree root) {
		if(root==null) {
			return;
		}
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTree node = stack.pop();//首先弹出来的是root
			System.out.print(node.data+" ");
			if(node.rightChild!=null) {
				stack.push(node.rightChild);
			}
			if(node.leftChild!=null) {
				stack.push(node.leftChild);
			}
		}
		System.out.print("\n"); 
	}
	//广度优先搜索，非递归，使用队列实现，先入先出
	public void widthOrderTraverdal(BinaryTree root) {
		if(root==null) {
			return;
		}
		Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
		queue.add(root);
		while(!queue.isEmpty()) {
			BinaryTree newNode = queue.remove();
			System.out.print(newNode.data+" ");
			if(newNode.leftChild!=null) {
				queue.add(newNode.leftChild);
			}
			if(newNode.rightChild!=null) {
				queue.add(newNode.rightChild);
			}
		}
		System.out.print("\n"); 
	}
	/*二叉树序列化（持久化）和反序列化
	 * 叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果
	 * 以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
	 * 序列化可以基于 先序、中序、后序、按层 的二叉树遍历方式来进行修改。
	 * 原理都是一样的（即遍历顺序不同而已，对每个结点的处理都是一样的），
	 * 序列化的结果是一个字符串，序列化时通过  某种符号表示空节点（#），
	 * 以 ！ 表示一个结点值的结束（value!）。
	 * 这里以先序遍历的方式进行序列化举例：
	 * 先序序列化二叉树==定义一个stringbuilder保存序列过程中的结果：
	 * 按照先序遍历方式遍历二叉树，若结点非空则把 "结点值!" append到builder中；
	 * 若结点空则把  "#!" append到builder中；最后用builder生成字符串就是序列化结果。    
	 * */
	//按先序遍历序列化二叉树
	public String Serialize(BinaryTree root) {
		StringBuilder stringBuilder = new StringBuilder();
		if(root==null) {
			stringBuilder.append("#!");
			return stringBuilder.toString();
		}
		stringBuilder.append(root.data+"!");
		stringBuilder.append(Serialize(root.leftChild));
		stringBuilder.append(Serialize(root.rightChild));
		return stringBuilder.toString();
	}
	//按先序遍历反序列化二叉树（将序列化成字符串反序列化为二叉树）
	public static int index = -1;
	public BinaryTree Deserialize(String str) {
		index++;
		String[] deseralize = str.split("!");
		BinaryTree node = null;
		if(!deseralize[index].equals("#")) {
			node = new BinaryTree(Integer.valueOf(deseralize[index]));
			node.leftChild=Deserialize(str);
			node.rightChild=Deserialize(str);
		}
		return node;
	}
	//按层序遍历二叉树，序列化二叉树
	public String levelSerialize(BinaryTree root) {
		if(root==null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<BinaryTree> list = new ArrayList<>();
		int count = (1 << treeDepth(root)) - 1;//计数，拿到此树的深度后计算对应完全二叉树节点数
		list.add(root);
		count--;
		BinaryTree temp=null;
		while(list.size()>0&&count>=0) {
			temp = list.remove(0);
			if(temp!=null) {
				sb.append(temp.data+"!");
				list.add(temp.leftChild);
				list.add(temp.rightChild);
			}else {
				sb.append("#!");
				list.add(null);
				list.add(null);
			}
			count--;
		}
		return sb.toString();
	}
	private int treeDepth(BinaryTree root) {
		int depth = 0;
        if(root == null){
            return depth;
        }else{
            int lDepth = treeDepth(root.leftChild) + 1;
            int rDepth = treeDepth(root.rightChild) + 1;
            return lDepth > rDepth ? lDepth : rDepth;
        }
	}
	//按层反序列化
	public BinaryTree levelDeserializemain(String str) {
		if(str==null||str.length()==0) {
			return null;
		}
		String[] st = str.split("!");
		return levelDeserialize(str.split("!"), 0);
	}
	public BinaryTree levelDeserialize(String[] str,int index) {
		BinaryTree newNode = null;
		
		if(index < str.length) {
			if(!str[index].equals("#")) {
				newNode = new BinaryTree(Integer.parseInt(str[index]));
				newNode.leftChild=levelDeserialize(str, index*2+1);
				newNode.rightChild=levelDeserialize(str, 2 * index + 2);
			}
		}
		return newNode;
	}
	
	/*判断两个二叉树是否相等*/
	public boolean isequalBinaryTree(BinaryTree root1,BinaryTree root2) {
		if(root1==null&&root2==null) {
			return true;
		}else if(root1==null||root2==null) {
			return false;
		}
		if(root1!=null&&root2!=null) {
			if(root1.data!=root2.data) {
				return false;
			}
			else {
				return isequalBinaryTree(root1.leftChild, root2.leftChild)&&
						isequalBinaryTree(root1.rightChild, root2.rightChild);
			}
		}
		return false;
		
	}
	//二叉树的子树：
	/*
	 * 再二叉树中以任何一个节点为头部的整棵树称二叉树的子树
	 * */
	/*
	 * 平衡二叉树：
	 * 1.空树是平衡二叉树
	 * 2.如果一棵树不为空，并且其中所有的子树都满足各自的左子树与右子树的高度差都不超过1
	 * 
	 * */
	//判断是否是平衡二叉树：
	private boolean isBalanced=true;
	public boolean isBalanced(BinaryTree root) {
		getDepth(root);
		return isBalanced;
	}
	private int getDepth(BinaryTree root) {
		if(root==null) {
			return 0;
		}
		int left = getDepth(root.leftChild);
		int right = getDepth(root.rightChild);
		if(Math.abs(left-right)>1) {
			isBalanced = false;
		}
		return right>left?right+1:left+1;	
	}
	
	
	
	//搜索二叉树：任意一个节点，它的左子树的值比根节点小，右子树的值比根节点的值大。
	//判断一棵树是否是搜索二叉树；
	/*
	 * 改写二叉树的中序遍历，中序遍历如果遍历到的当前值一直比前一个遍历的值大，那么就是搜索二叉树
	 * */
	private static ArrayList<Integer> arrlist = new ArrayList();
	public boolean isSearchBinaryTree(BinaryTree root) {
		if(root==null) {
			return false;
		}
		for(int i=1;i<arrlist.size();i++) {
			if(arrlist.get(i)<arrlist.get(i-1)) {
				return false;
			}
		}
		return true;	
	}
	public void inOrderChange(BinaryTree root) {
		if(root!=null) {
			inOrderChange(root.leftChild);
			arrlist.add(root.data);
			inOrderChange(root.rightChild);
		}
	}
	
	/*判断是不是完全二叉树
	 * 什么是完全二叉树：
	 * 完全二叉树是指除了最后一层之外，其他每一层难过的节点都是满的，最后一层如果也满了，是一颗满二叉树；
	 * 也是完全二叉树，如果，最后一层不满，缺少的节点也全部集中再右边，那也是一颗完全二叉树
	 * 解题思路：
	 * 1.采用按层遍历的思想，从每层的左边向右边依次遍历所有节点
	 * 2.如果当前节点有右子树，但没有左子树，直接返回false；
	 * 3.如果当前节点有左孩子，但没有有孩子，直接返回false；
	 * 4.如果两个孩子都没有，则
	 * */
	public boolean isCompleteTree(BinaryTree root) {
			if(root==null){
				System.out.println("空树！");
				return true;
			}
			Queue<BinaryTree> queue= new LinkedList<BinaryTree>();
			queue.add(root);
			BinaryTree temp;
			int flag=0;
			while(queue.size()>0){
				temp=queue.poll();
				if(null!=temp.leftChild){
					if(1==flag){
						return false;
					}
					queue.add(temp.leftChild);
					if(null!=temp.rightChild){
						queue.add(temp.rightChild);	
					}else{
						flag=1;
					}
				}else{
					if(null!=temp.rightChild){
						return false;	
					}
					flag=1;
				}
				
			}
		return true;
		
	}	
	//上边这个判断完全二叉树是错误的
	
	
	/*
	 * 后继节点：中序遍历时的下一个节点
	 * 前驱结点：中序遍历时的上一个节点
	 * */
	public void print(int n) {
		for(int i=1;i<=n;i++) {
			System.out.print(fib(i)+" ");
		}
	}
	public int fib(int n) {
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 1;
		}
		return fib(n-2)+fib(n-1);
		
	}
}
