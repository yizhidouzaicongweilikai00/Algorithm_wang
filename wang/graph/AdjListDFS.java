package com.wang.graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{ 
    Vertex vex; // 头结点
    Node next; 
}

/*
 * 顶点类
 * 
 * */

class Vertex{   
    String name;
    Node root; // 以这个顶点作为链表头节点 
    boolean visited = false;

}
public class AdjListDFS {
    Vertex[] vts;  // 顶点集合
    Node[] nodes; // 链表集合
    ArrayList<Vertex> path ;  // 访问路径
    int n = 0;
    Scanner sc = new Scanner(System.in);
    /**
     * 输入顶点数据
     */
    public void  Getvts() {
        System.out.println("输入顶点的数量！");
        n = sc.nextInt();
        vts = new Vertex[n];
        nodes = new Node[n];
        sc.nextLine();
        for(int i = 0; i < n; i ++) {
            System.out.println("输入第 ： " +(i + 1) +"个顶点的名称");
            vts[i] = new Vertex();
            nodes[i] = new  Node();
            vts[i].name = sc.nextLine();    
            nodes[i].vex = vts[i];
            vts[i].root = nodes[i];
        }
    }


    /**
     *  创建图
     */
    public void CreateGra() {
        int i, j;
        String str = null;
        String[] strarr = null;

        for (i = 0; i < n; i ++) {
            System.out.println("输入  " +vts[i].name + "顶点所连接的顶点 下标(用空格空开) 注意 第一个顶点下标是 0" );
            Node p ;
            p = nodes[i];
            str = sc.nextLine();        
            if (!str.equals("")) {
                strarr= str.split(" ");
                for (j = 0; j < strarr.length; j++) {   
                    p.next = new Node();
                    p.next.vex = vts[Integer.valueOf(strarr[j])];
                    p = p.next;
                }
            }

        }
        sc.close(); 
    }

    /**
     *  打印图
     */
    public void PrinGraph() {

        for(int i = 0; i < nodes.length; i++) {
            Node p = nodes[i];
            System.out.println("这是顶点： " + nodes[i].vex.name + " 的链表 : " );
            while(p!=null) {
                System.out.print(  p.vex.name + "-> "   );
                p = p.next;
            }
            System.out.println();
        }   
    }



    /**
     * 
     * 第一个for 用于遍历所有的顶点 只有全部节点都被访问过才有可能返回
     * 遍历步骤:
     *      1. 如果该节点未被访问过，则接下来访问它的链表。
     *      2. 将链表中每一个元素当做新的链表头进行递归操作。
     *      3. path 用于记录访问顺序
     * 
     * 总结 ：
     *      第一个for 确保所有节点都被访问过
     *      Dfsvisit 则确保每条链表都被检查过， 但不一定进行操作。
     * 
     */
    public void Dfs() {
        path = new ArrayList<>();
        int i;
        for (i = 0; i < vts.length; i++ ) {  // 确保所有节点被访问过， 得出的结果正确性毋庸置疑

            if (vts[i].visited == false) {  // 判断节点是否被访问过
                vts[i].visited = true;  // 将该节点标记访问过
                path.add(vts[i]);  // 添加到路径中
                Dfsvisit(vts[i].root);  // 对该节点的链表进行访问
            }

        }
    }
    private void Dfsvisit(Node node) {
        Node p = node.next;  // 取得链表的第一元素
        while(p != null) {  // 不为空且未曾被访问过
            if(  p.vex.visited == false) {
                p.vex.visited = true;  // 设置为访问过
                path.add(p.vex); // 加入路径
                Dfsvisit(p.vex.root);  // 对第一个元素所在的链表进行访问
            }else {
                p = p.next;
            }
        }
    }
    /**
     * 要点：  
     *    通过辅助队列， 实现Bfs算法。
     *   1. 先将一个链表的头节点加入队列。 
     *   2. 通过while  链表内每一个节点所对应的链表的头结点加入队列中
     *   3. 改变节点的状态， 将节点加入到路径中即可
     * 
     * 
     */
    public void Bfs() {
        path = new ArrayList<>();
        Queue<Node> qn = new LinkedList<Node>();  // 构造辅助队列
        qn.add(nodes[0]);  // 添加一个链表的头结点  可换
        while(!qn.isEmpty()) { 
            Node p = qn.remove();  // 从第一个开始  出队
            while(p != null) {   // 这个while 用于将所有该链表里的 节点 加入到队列中
                if(p.vex.visited == false) {
                    p.vex.visited = true;  // 状态改变
                    path.add(p.vex);  // 加入到路径里
                    p = p.next;   // 下个节点
                    if (p != null)
                        qn.add(p.vex.root);  // 将该节点所代表的链表加入到队列中

                }else {
                    p = p.next;  // 如果该节点已被访问过  则 进入下一个节点
                }

            }   
        }
    }


    /**
     *  将访问路径打印出来
     */
    public void Prinpath() {
        System.out.print("搜索路径： "+path.get(0).name);
        for (int i = 1; i < path.size(); i++) {

            System.out.print(" -> "+path.get(i).name);

        }
        System.out.println();
    }
    public static void main(String[] args) {
        AdjListDFS gp = new AdjListDFS();
        gp.Getvts();
        gp.CreateGra();
        gp.PrinGraph();
//        System.out.println("执行DFS输出： ");
//        gp.Dfs();
//        gp.Prinpath();
        
      System.out.println("执行BFS输出： "); // 测试的时候DFS跟BFS要分开测  因为 顶点是访问过的 
      gp.Bfs();
      gp.Prinpath();
    }

}

