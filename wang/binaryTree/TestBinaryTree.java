package com.wang.binaryTree;

public class TestBinaryTree {
	public static void main(String[] args) {
		//����һ�ö�����
		int[] array = {35,17,39,9,28,56,38};//�����������������
		BinaryTree root = new BinaryTree(array[0]);//����Ҫ�Ȳ���һ�����ڵ�
		for(int i=1;i<array.length;i++) {//��������в�������
			root.insert(root, array[i]);
		}
		System.out.println("*********�������**********");
		root.preOrder(root);
		System.out.println();
		System.out.println("*********�������**********");
		root.inOrder(root);
		System.out.println();
		System.out.println("*********�������**********");
		root.afterOrder(root);
		System.out.println();
		System.out.println("�������������ӡ");
		int[][] result = root.printTree(root);
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[i].length;j++) {
				System.out.print(result[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("*******�����������**********");
		root.depthOrderTraversal(root);
		System.out.println("********�����������*********");
		root.widthOrderTraverdal(root);
		System.out.println("********������������������л�**********");
		String str = root.Serialize(root);
		System.out.println("������������������л���Ľ����"+str);
		System.out.println("*�԰�������������л��Ķ������γɵ��ַ������з����л�*");
		BinaryTree newRoot = root.Deserialize(str);
		newRoot.preOrder(newRoot);
		System.out.println("*����α������л�������*");
		str = root.levelSerialize(root);
		System.out.println("���ղ�α������л���Ľ����"+str);
		newRoot = root.levelDeserializemain(str);
		System.out.print("���ղ�α��������л��Ľ���� ");
		newRoot.preOrder(newRoot);
		System.out.println();
		BinaryTree rootTree = root;new BinaryTree(array[0]);//����Ҫ�Ȳ���һ�����ڵ�
		for(int i=1;i<array.length;i++) {//��������в�������
			rootTree.insert(rootTree, array[i]);
		}
		Boolean b = root.isequalBinaryTree(rootTree, root);
		System.out.println("�ж��������Ƿ���ȣ�"+b);
		int[] arr = {1,2,4,5,7,3,6};
		BinaryTree root3 = new BinaryTree(array[0]);
		for(int i=1;i<arr.length;i++) {//��������в�������
			rootTree.insert(rootTree, array[i]);
		}
		System.out.println("�ж����Ƿ���ƽ������"+root3.isBalanced(root3));
		System.out.println("�ж����ǲ��Ƕ�����������"+root.isSearchBinaryTree(root));
		
		System.out.println("�ж��ǲ�����ȫ��������"+root.isCompleteTree(root));
		//��ӡ쳲��Ǳ�����
		root.print(10);
	}
}
