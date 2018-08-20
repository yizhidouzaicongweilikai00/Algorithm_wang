package com.wang.dymaticprogramming;

import java.util.ArrayList;

public class Triangle {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list.add(2);
		triangle.add(list);
		
		list1.add(3);list1.add(4);
		triangle.add(list1);
		
		list2.add(6);list2.add(5);list2.add(7);
		triangle.add(list2);
		
		list3.add(4);list3.add(1);list3.add(8);list3.add(3);
		triangle.add(list3);
		mumTotal(triangle);
		
	}
	public static int mumTotal(ArrayList<ArrayList<Integer>> triangle) {
        //找到最短路径和，设path[i][j]为从最底端到第i行第j列的最短路径
        //则path[i][j]=path[i+1][j]+triangle.get(i).get(j)或者path[i+1][j+1]+trangle.get(i).get(j)
		int[][] path = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
		for(int i=0;i<triangle.size();i++) {
			
			for(int j=0;j<triangle.get(i).size();j++) {
				
				if(i==triangle.size()-1) {
					path[i][j]=triangle.get(i).get(j);
				}else {
					path[i][j]=0;
				}
			}
		}
		for(int i=triangle.size()-2;i>=0;i--) {
			for(int j=0;j<triangle.get(i).size();j++) {
				path[i][j]=Math.min(path[i+1][j]+triangle.get(i).get(j), path[i+1][j+1]+triangle.get(i).get(j));
			}
		}
		return path[0][0];
	}
}
