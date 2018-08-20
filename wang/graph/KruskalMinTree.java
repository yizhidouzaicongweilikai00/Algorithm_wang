package com.wang.graph;
/**
 * 求最小树的Kruskal算法
 * 算法思想：克鲁斯卡尔算法从另一个途径求网中的最小生成树。假设联通网N=(V,{E})，则令
 * 最小生成树的厨师状态为只有n个顶点而无边的非连通图T=(V,{})，途中每个顶点自称一个连通分量。
 * 在E中选择代价最小的边，若该边衣服的顶点落在T中不同的连通分量上，则将此边加入到T中，否则舍去此边
 * 而选择下一条最小的边。以此类推，直至T中所有的顶点都在同一连通分量上为止。
 * @param V 图中的节点集合
 * @param E 图中边的集合
 */
public class KruskalMinTree {
	public static void main(String[] args) {
		int[] v = {1,2,3,4,5,6};
		
	}
	public static void kruskal() {
		
	}
}
