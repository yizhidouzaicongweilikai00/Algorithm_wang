package com.wang.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.wang.binaryTree.BinaryTree;

public class StringOperator {
	/*
	 * 给定彼此独立的两棵树头节点分别为t1，t2，判断t1中是否有与t2树拓扑结构完全相同的子树
	 * 解题思路：
	 * 拿第二个树的每个节点去和第一个树做匹配，如果某个节点匹配成功，就接着往下匹配，
	 * 否则重新从第二个树的的根节点开始。
	 * 看题目有没有明确空树是否算是子树，
	 * */
	BinaryTree root=new BinaryTree();
	public boolean HasSubTree(BinaryTree root1,BinaryTree root2) {
		//递归的解决方案
		/*先找到与root2中相同值的节点，再调用子树判断函数判断是否相等*/
		BinaryTree temp = root1;
		//若可能的子树为空
		if(root2==null) {
			return true;
		}
		//若被比较的树为空
		if(temp==null) {
			return false;
		}
		//若两个值相等
		if(temp.data==root2.data) {
			if(isSameTree(temp,root2)) {
				return true;
			}
		} //若不相等，滑动指针
		if(temp.leftChild!=null&&temp.rightChild==null) {
			temp=temp.leftChild;
			return HasSubTree(temp, root2);
		}
		if(temp.rightChild!=null&&temp.leftChild==null) {
			temp=temp.rightChild;
			return HasSubTree(temp, root2);
		}
		if(temp.leftChild!=null&&temp.rightChild!=null) {
			return HasSubTree(temp.leftChild, root2)||HasSubTree(temp.rightChild, root2);
		}
		return false;
		
	}
	public boolean isSameTree(BinaryTree root1,BinaryTree root2) {
		//调用函数是已经判断了root1与root2是相等的，所以如果root2左右节点都为空，那么
		//root2就是root1的子树
		if(root2.leftChild==null&&root2.rightChild==null) {
			return true;
		}
		//要以子树为条件，去匹配大的那棵树
		if(root2.leftChild!=null&&root2.rightChild==null) {
			if(root1.leftChild==null) {
				return false;
			}
			if(root2.leftChild.data==root1.leftChild.data) {
				return isSameTree(root1.leftChild,root2.leftChild);
			}
		}
		if(root2.rightChild!=null&&root2.leftChild==null) {
			if(root1.rightChild==null) {
				return false;
			}
			if(root2.rightChild.data==root1.rightChild.data) {
				return isSameTree(root1.rightChild, root2.rightChild);
			}
		}
		if(root2.rightChild!=null&&root.leftChild!=null) {
			if(root1.leftChild==null||root1.rightChild==null) {
				return false;
			}
			if(root2.leftChild.data==root1.leftChild.data&&root2.rightChild.data==root1.rightChild.data) {
				return isSameTree(root1.leftChild, root2.leftChild) && isSameTree(root1.rightChild, root2.rightChild);
			}
		}
		return false;
		
	}
	/*解法二：
	 * 将二叉树t1序列化，将二叉树t2序列化，则判断子树问题就变成了，再str中是否包含子串str2
	 * 
	 * */
	/*给定两个字符串str1和str2，如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，那么str1与
	 * str2互为变形词。例如str1="123",str2="231",返回true*/
	//使用hash表实现
	public boolean isDeformation(String str1,String str2) {
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();
		if(str1==null||str2==null||str1.length()!=str2.length()) {
			return false;
		}
		//对char1存储到hash中
		HashMap<Character,Integer> map1 = new HashMap();
		for(int i=0;i<char1.length;i++) {
			if(map1.containsKey(char1[i])) {
				map1.put(char1[i], map1.get(char1[i])+1);
			}else {
				map1.put(char1[i], 1);
			}
		}
		//对char2存储到hash中
		HashMap<Character,Integer> map2 = new HashMap<Character, Integer>();
		for(int i=0;i<char1.length;i++) {
			if(map2.containsKey(char2[i])) {
				map2.put(char2[i], map1.get(char2[i])+1);
			}else {
				map2.put(char2[i], 1);
			}
		}
		//对map进行遍历，比较
		Iterator<Entry<Character, Integer>> iter = map1.entrySet().iterator();
		while(iter.hasNext()) {
			
			Entry<Character, Integer> entry = iter.next();
			char key = entry.getKey();
			int value = entry.getValue();
			if(!map2.containsKey(key)||map2.get(key)!=value) {
				return false;
			}
		}
		return true;
		
	}
	/*
	 * KMP算法，字符串匹配算法
	 * 找出长字符串str中是否包含子串p，并返回它的位置。
	 * 比如ABCDEFG,找出它中是否包含ABCE
	 * */
	//首先暴力破解遍历长字符串的每一个值，判断从该值开始是否与子串相等，如果相等，则返回
	//如果不相等，则则遍历长字符串的下一个值
	public int matchString(String str,String subStr) {
		char[] charStr = str.toCharArray();
		char[] charSubStr = subStr.toCharArray();
		int i=0;
		int j=0;
		if(str==null||subStr==null||str.length()<subStr.length()) {
			return -1;
		}
		while(i<charStr.length&&j<charSubStr.length) {
			if(charStr[i]==charSubStr[j]) {
				//当两个字符相同时，就比较下一个
				i++;
				j++;
			}else {
				//一旦不匹配
				i=i-j+1;
				j=0;
			}
		}
		if(j==charSubStr.length) {
			return i-j;
		}else {
			return -1;
		}		
	}
	/*kmp算法*/
	
	public int kmp(String str,String subStr) {
		//得到next数组
		int[] next=new int[subStr.length()];
		next[0]=0;//定义为0
		for(int i=1,j=0;i<subStr.length();i++) {
			while(j>0&&subStr.charAt(j)!=subStr.charAt(i)) {
				j=next[j-1];
				
			}
			if(subStr.charAt(i)==subStr.charAt(j)) {
				j++;
			}
			next[i]=j;
		}
		//end next
		for(int i=0,j=0;i<str.length();i++) {
			while(j>0&&str.charAt(i)!=subStr.charAt(j)) {
				j=next[j-1];
				
			}
			if(str.charAt(i)==subStr.charAt(j)) {
				j++;
			}
			if(j==subStr.length()) {
				return i-j+1;
			}
		}
		return -1;
	}
	/*判断一个字符串是不是另一个字符串的旋转词；
	 * 如果一个字符串str，把字符串str前面任意部分挪到后边去形成的字符串叫做str的旋转词。
	 * 比如str='1234',则'1234''2341''3412'等都是它的旋转词
	 * 
	 * 解题思路，：
	 * 可以复制一个字符串连接到该str的后面，然后判断长字符串中是否包含另一个字符串即可。
	 * */
	public boolean isRotation(String str1,String str2) {
		if(str1==null||str2==null||str1.length()!=str2.length()) {
			return false;
		}
		//连接两个字符串
		String longStr = str1+str1;
		int a = kmp(longStr,str2);
		if(a!=-1) {
			return true;
		}else {
			return false;
		}		
	}
	/*
	 * 字符串逆序操作
	 * 先将整个字符串逆序，然后按照空格分割，把每个小字符串逆序即可
	 * 
	 * */
	public String Reverse(String str) {
		char[] Rchar = str.toCharArray();
		int begin=0;
		int end=Rchar.length-1;
		char temp=0;
		while(begin<end) {
			temp=Rchar[end];
			Rchar[end]=Rchar[begin];
			Rchar[begin]=temp;
			begin++;
			end--;
			
		}	
		return String.valueOf(Rchar);
		
	}
	public String ReverseString(String str) {
		String longString = Reverse(str);
		
		String[] shortString = longString.split(" ");
		String result ="";
		for(int i=0;i<shortString.length;i++) {
			shortString[i]=Reverse(shortString[i]);
		}
		for(int i=0;i<shortString.length;i++) {
			result=result+" "+shortString[i];
		}
		return result;
		
	}
	//给定一个字符串str，返回str的最长无重复子串的长度
	//暴力法
	public int lengthOfLongestSubString(String str) {
		if(str==null||str.length()==0) {
			return 0;
		}
		int tempLength=0,max=0;
		int i=0,j=0;
		ArrayList<Character> list = new ArrayList<Character>();
		ArrayList<Character> templist = new ArrayList<Character>();
		HashSet<Character> set = new HashSet<Character>();
		while(i<str.length()) {
			while(true) {
				if(set.add(str.charAt(j))) {
					list.add(str.charAt(j));
					tempLength++;
					j++;
				}else {
					if(max<tempLength) {
						max=tempLength;
						templist=list;
					}
					j=0;
					break;
				}
				
			}
			i++;
		}
		return max;
	}
	//o(n)时间复杂度的
	public int lengthOfLongestSubString1(String str) {
		if(str==null||str.length()==0) {
			return 0;
		}
		StringBuilder maxSubString = new StringBuilder("");
		char[] strCharArr=str.toCharArray();
		HashMap<Character, Integer> charsIndex = new HashMap<Character, Integer>();
		int startIndex = -1, oriStartIndex = startIndex, maxLen = 0;
		for(int index=0;index<strCharArr.length;index++) {
			if(charsIndex.containsKey(strCharArr[index])) {
				int oriIndex = charsIndex.get(strCharArr[index]);
				if(oriIndex>startIndex) {
					startIndex=oriIndex;
				}
			}
			if(index - startIndex > maxLen) {
				maxLen = index - startIndex;
				oriStartIndex = startIndex;
			}
			charsIndex.put(strCharArr[index], index);
		}
		for(int index =  oriStartIndex + 1; index < oriStartIndex + maxLen + 1; index++) {
			maxSubString.append(strCharArr[index]);
		}
		return maxSubString.length();
		
	}
	
}
