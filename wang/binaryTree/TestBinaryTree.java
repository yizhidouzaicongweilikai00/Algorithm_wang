package com.wang.binaryTree;

public class TestBinaryTree {
	public static void main(String[] args) {
		//创建一棵二叉树
		int[] array = {35,17,39,9,28,56,38};//定义二叉树所存数据
		BinaryTree root = new BinaryTree(array[0]);//首先要先插入一个根节点
		for(int i=1;i<array.length;i++) {//向二叉树中插入数据
			root.insert(root, array[i]);
		}
		System.out.println("*********先序遍历**********");
		root.preOrder(root);
		System.out.println();
		System.out.println("*********中序遍历**********");
		root.inOrder(root);
		System.out.println();
		System.out.println("*********后序遍历**********");
		root.afterOrder(root);
		System.out.println();
		System.out.println("按层遍历，并打印");
		int[][] result = root.printTree(root);
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[i].length;j++) {
				System.out.print(result[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("*******深度优先搜索**********");
		root.depthOrderTraversal(root);
		System.out.println("********宽度优先搜索*********");
		root.widthOrderTraverdal(root);
		System.out.println("********按先序遍历二叉树序列化**********");
		String str = root.Serialize(root);
		System.out.println("按先序遍历二叉树序列化后的结果："+str);
		System.out.println("*对按照先序遍历序列化的二叉树形成的字符串进行反序列化*");
		BinaryTree newRoot = root.Deserialize(str);
		newRoot.preOrder(newRoot);
		System.out.println("*按层次遍历序列化二叉树*");
		str = root.levelSerialize(root);
		System.out.println("按照层次遍历序列化后的结果："+str);
		newRoot = root.levelDeserializemain(str);
		System.out.print("按照层次遍历反序列化的结果： ");
		newRoot.preOrder(newRoot);
		System.out.println();
		BinaryTree rootTree = root;new BinaryTree(array[0]);//首先要先插入一个根节点
		for(int i=1;i<array.length;i++) {//向二叉树中插入数据
			rootTree.insert(rootTree, array[i]);
		}
		Boolean b = root.isequalBinaryTree(rootTree, root);
		System.out.println("判断两个树是否相等："+b);
		int[] arr = {1,2,4,5,7,3,6};
		BinaryTree root3 = new BinaryTree(array[0]);
		for(int i=1;i<arr.length;i++) {//向二叉树中插入数据
			rootTree.insert(rootTree, array[i]);
		}
		System.out.println("判断树是否是平衡树："+root3.isBalanced(root3));
		System.out.println("判断树是不是二叉搜索树："+root.isSearchBinaryTree(root));
		
		System.out.println("判断是不是完全二叉树："+root.isCompleteTree(root));
		//打印斐波那比数列
		root.print(10);
	}
}
