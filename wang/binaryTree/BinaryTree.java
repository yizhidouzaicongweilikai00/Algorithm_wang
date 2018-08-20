package com.wang.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
	public BinaryTree leftChild;//������
	public BinaryTree rightChild;//������
	public int data;//������
	
	
	//��д���캯����
	public BinaryTree(int data) {
		super();
		this.leftChild=null;
		this.rightChild=null;
		this.data=data;
	}
	//��д���캯��
	public BinaryTree() {
		super();
	}
	//���봴��������
	public void insert(BinaryTree root,int data) {
		if(data>root.data) {
			//�������ý����ڸ����
			if(root.rightChild==null) {
			//���������Ϊ�գ��Ͳ��룬�����Ϊ�գ����ٴ���һ�����
				root.rightChild=new BinaryTree(data);//�ͰѲ���Ľڵ�����ұ�
			}else {
				this.insert(root.rightChild, data);
			}
		}else {//�������ýڵ�С�ڵ��ڸ��ڵ�
			if(root.leftChild==null) {
			//���������Ϊ�գ��Ͳ��룬�����Ϊ�վ��ٴ���һ���ڵ�
				root.leftChild = new BinaryTree(data);//�ͰѲ���ýڵ�������
			}else {
				this.insert(root.leftChild, data);
			}
		}
	}
	
	/*����������*/
	
	//�������
	public void preOrder(BinaryTree root) {
		
		if(root!=null) {
			System.out.print(root.data+"  ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}
	//�������
	public void inOrder(BinaryTree root) {
		if(root!=null) {
			inOrder(root.leftChild);
			System.out.print(root.data+" ");
			inOrder(root.rightChild);
		}
	}
	//�������,�ݹ�˼��
	public void afterOrder(BinaryTree root) {
		if(root!=null) {
			afterOrder(root.leftChild);
			afterOrder(root.rightChild);
			System.out.print(root.data+" ");
		}
	}
	/*
	 * ��һ�ö������������һ���㷨�����ղ�δ�ӡ��Ŷ�����
	 * �����������ø��ڵ㣬�뷵�ش�ӡ������������ÿһ��һ��������д洢
	 * ���������˳���ղ����������£���ÿһ���������Ԫ�ذ��մ����������С�
	 * ��֤�����С�ڵ���500��
	 * ˼·��
	 * ����һ�����У��Բ�α�����˼������𣬹ؼ����ڣ��ж�ʲôʱ��û�����
	 * 1.��lastָ��ǰ���ڴ�ӡ���е����ұߵýڵ㣬����������������ýڵ�
	 * 2.��nlastָ��ǰ��ӵýڵ�
	 * 3.�Ӹ���㿪ʼ���������Һ����Բ�α����ķ�ʽ��ӡ�ÿ���һ��Ԫ�أ�����nLastָ�����Ԫ�أ�
	 * ������Ԫ��temp���ӵ�һ��ArrayList�У���temp==lastʱ������û����ˣ�Ҳ���ǽ���ǰArrayList
	 * �ŵ�һ��ArrayLists�У��ٽ���ǰ��ArrayList��ա�Ȼ��last=nLast��
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
				queue.add(temp.leftChild);//������������
				nlast=temp.leftChild;//��nlast�����¼����ֵ
			}
			if(temp.rightChild!=null) {
				queue.add(temp.rightChild);//������������
				nlast=temp.rightChild;//��nlast�����¼����ֵ
			}
			if(temp==last) {
				arrays.add(array);
				array=new ArrayList<>();
				last=nlast;
			}
		}
		//�ϱ߲��轫�������ݴ洢��ArrayList����
		result = new int[arrays.size()][];
		for(int i=0;i<arrays.size();i++) {
			result[i]=new int[arrays.get(i).size()];//���¶���������Ĵ�С
			for(int j=0;j<arrays.get(i).size();j++) {
				result[i][j] = arrays.get(i).get(j);
			}
		}
		return result;
	}
	/*
	 * ����һ�Ŷ������������������(Depth First Search)������������ȱ������Ľڵ㣬
	 * ����������������ķ�֧�������������Ϊ�����������������˳��Ϊ��ABDECFG��
	 * ��ôʵ�����˳���� ��
	 * 1.��������������������ȷ��ʸ���㣬Ȼ����������������Ǳ�����������
	 * ������ǿ������ö�ջ���Ƚ�������ص㣬�ֽ�������ѹջ���ٽ�������ѹջ��������������λ��ջ����
	 * ���Ա�֤����������������������������
	 * 
	 * 
	 *2. �����������(Breadth First Search),�ֽп�������������������������
	 * �ǴӸ���㿪ʼ�������Ŀ����������������������ı���˳��Ϊ��ABCDEFG.
	 * �������ö���ʵ�ֹ������������
	 * 
	 * */
	//��������������൱������������ǵݹ飬ʹ��ջʵ�֣�������
	public void depthOrderTraversal(BinaryTree root) {
		if(root==null) {
			return;
		}
		Stack<BinaryTree> stack = new Stack<BinaryTree>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTree node = stack.pop();//���ȵ���������root
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
	//��������������ǵݹ飬ʹ�ö���ʵ�֣������ȳ�
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
	/*���������л����־û����ͷ����л�
	 * ���������л���ָ����һ�ö���������ĳ�ֱ�����ʽ�Ľ��
	 * ��ĳ�ָ�ʽ����Ϊ�ַ������Ӷ�ʹ���ڴ��н��������Ķ��������Գ־ñ��档
	 * ���л����Ի��� �������򡢺��򡢰��� �Ķ�����������ʽ�������޸ġ�
	 * ԭ����һ���ģ�������˳��ͬ���ѣ���ÿ�����Ĵ�����һ���ģ���
	 * ���л��Ľ����һ���ַ��������л�ʱͨ��  ĳ�ַ��ű�ʾ�սڵ㣨#����
	 * �� �� ��ʾһ�����ֵ�Ľ�����value!����
	 * ��������������ķ�ʽ�������л�������
	 * �������л�������==����һ��stringbuilder�������й����еĽ����
	 * �������������ʽ�����������������ǿ���� "���ֵ!" append��builder�У�
	 * ���������  "#!" append��builder�У������builder�����ַ����������л������    
	 * */
	//������������л�������
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
	//��������������л��������������л����ַ��������л�Ϊ��������
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
	//��������������������л�������
	public String levelSerialize(BinaryTree root) {
		if(root==null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<BinaryTree> list = new ArrayList<>();
		int count = (1 << treeDepth(root)) - 1;//�������õ���������Ⱥ�����Ӧ��ȫ�������ڵ���
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
	//���㷴���л�
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
	
	/*�ж������������Ƿ����*/
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
	//��������������
	/*
	 * �ٶ����������κ�һ���ڵ�Ϊͷ�����������ƶ�����������
	 * */
	/*
	 * ƽ���������
	 * 1.������ƽ�������
	 * 2.���һ������Ϊ�գ������������е�������������Ե����������������ĸ߶Ȳ������1
	 * 
	 * */
	//�ж��Ƿ���ƽ���������
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
	
	
	
	//����������������һ���ڵ㣬������������ֵ�ȸ��ڵ�С����������ֵ�ȸ��ڵ��ֵ��
	//�ж�һ�����Ƿ���������������
	/*
	 * ��д����������������������������������ĵ�ǰֵһֱ��ǰһ��������ֵ����ô��������������
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
	
	/*�ж��ǲ�����ȫ������
	 * ʲô����ȫ��������
	 * ��ȫ��������ָ�������һ��֮�⣬����ÿһ���ѹ��Ľڵ㶼�����ģ����һ�����Ҳ���ˣ���һ������������
	 * Ҳ����ȫ����������������һ�㲻����ȱ�ٵĽڵ�Ҳȫ���������ұߣ���Ҳ��һ����ȫ������
	 * ����˼·��
	 * 1.���ð��������˼�룬��ÿ���������ұ����α������нڵ�
	 * 2.�����ǰ�ڵ�������������û����������ֱ�ӷ���false��
	 * 3.�����ǰ�ڵ������ӣ���û���к��ӣ�ֱ�ӷ���false��
	 * 4.����������Ӷ�û�У���
	 * */
	public boolean isCompleteTree(BinaryTree root) {
			if(root==null){
				System.out.println("������");
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
	//�ϱ�����ж���ȫ�������Ǵ����
	
	
	/*
	 * ��̽ڵ㣺�������ʱ����һ���ڵ�
	 * ǰ����㣺�������ʱ����һ���ڵ�
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
