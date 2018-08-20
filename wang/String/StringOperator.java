package com.wang.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.wang.binaryTree.BinaryTree;

public class StringOperator {
	/*
	 * �����˴˶�����������ͷ�ڵ�ֱ�Ϊt1��t2���ж�t1���Ƿ�����t2�����˽ṹ��ȫ��ͬ������
	 * ����˼·��
	 * �õڶ�������ÿ���ڵ�ȥ�͵�һ������ƥ�䣬���ĳ���ڵ�ƥ��ɹ����ͽ�������ƥ�䣬
	 * �������´ӵڶ������ĵĸ��ڵ㿪ʼ��
	 * ����Ŀ��û����ȷ�����Ƿ�����������
	 * */
	BinaryTree root=new BinaryTree();
	public boolean HasSubTree(BinaryTree root1,BinaryTree root2) {
		//�ݹ�Ľ������
		/*���ҵ���root2����ֵͬ�Ľڵ㣬�ٵ��������жϺ����ж��Ƿ����*/
		BinaryTree temp = root1;
		//�����ܵ�����Ϊ��
		if(root2==null) {
			return true;
		}
		//�����Ƚϵ���Ϊ��
		if(temp==null) {
			return false;
		}
		//������ֵ���
		if(temp.data==root2.data) {
			if(isSameTree(temp,root2)) {
				return true;
			}
		} //������ȣ�����ָ��
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
		//���ú������Ѿ��ж���root1��root2����ȵģ��������root2���ҽڵ㶼Ϊ�գ���ô
		//root2����root1������
		if(root2.leftChild==null&&root2.rightChild==null) {
			return true;
		}
		//Ҫ������Ϊ������ȥƥ�����ǿ���
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
	/*�ⷨ����
	 * ��������t1���л�����������t2���л������ж���������ͱ���ˣ���str���Ƿ�����Ӵ�str2
	 * 
	 * */
	/*���������ַ���str1��str2�����str1��str2�г��ֵ��ַ�����һ����ÿ���ַ����ֵĴ���Ҳһ������ôstr1��
	 * str2��Ϊ���δʡ�����str1="123",str2="231",����true*/
	//ʹ��hash��ʵ��
	public boolean isDeformation(String str1,String str2) {
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();
		if(str1==null||str2==null||str1.length()!=str2.length()) {
			return false;
		}
		//��char1�洢��hash��
		HashMap<Character,Integer> map1 = new HashMap();
		for(int i=0;i<char1.length;i++) {
			if(map1.containsKey(char1[i])) {
				map1.put(char1[i], map1.get(char1[i])+1);
			}else {
				map1.put(char1[i], 1);
			}
		}
		//��char2�洢��hash��
		HashMap<Character,Integer> map2 = new HashMap<Character, Integer>();
		for(int i=0;i<char1.length;i++) {
			if(map2.containsKey(char2[i])) {
				map2.put(char2[i], map1.get(char2[i])+1);
			}else {
				map2.put(char2[i], 1);
			}
		}
		//��map���б������Ƚ�
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
	 * KMP�㷨���ַ���ƥ���㷨
	 * �ҳ����ַ���str���Ƿ�����Ӵ�p������������λ�á�
	 * ����ABCDEFG,�ҳ������Ƿ����ABCE
	 * */
	//���ȱ����ƽ�������ַ�����ÿһ��ֵ���жϴӸ�ֵ��ʼ�Ƿ����Ӵ���ȣ������ȣ��򷵻�
	//�������ȣ�����������ַ�������һ��ֵ
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
				//�������ַ���ͬʱ���ͱȽ���һ��
				i++;
				j++;
			}else {
				//һ����ƥ��
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
	/*kmp�㷨*/
	
	public int kmp(String str,String subStr) {
		//�õ�next����
		int[] next=new int[subStr.length()];
		next[0]=0;//����Ϊ0
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
	/*�ж�һ���ַ����ǲ�����һ���ַ�������ת�ʣ�
	 * ���һ���ַ���str�����ַ���strǰ�����ⲿ��Ų�����ȥ�γɵ��ַ�������str����ת�ʡ�
	 * ����str='1234',��'1234''2341''3412'�ȶ���������ת��
	 * 
	 * ����˼·����
	 * ���Ը���һ���ַ������ӵ���str�ĺ��棬Ȼ���жϳ��ַ������Ƿ������һ���ַ������ɡ�
	 * */
	public boolean isRotation(String str1,String str2) {
		if(str1==null||str2==null||str1.length()!=str2.length()) {
			return false;
		}
		//���������ַ���
		String longStr = str1+str1;
		int a = kmp(longStr,str2);
		if(a!=-1) {
			return true;
		}else {
			return false;
		}		
	}
	/*
	 * �ַ����������
	 * �Ƚ������ַ�������Ȼ���տո�ָ��ÿ��С�ַ������򼴿�
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
	//����һ���ַ���str������str������ظ��Ӵ��ĳ���
	//������
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
	//o(n)ʱ�临�Ӷȵ�
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
