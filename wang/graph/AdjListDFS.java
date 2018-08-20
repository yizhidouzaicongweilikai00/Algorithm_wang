package com.wang.graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{ 
    Vertex vex; // ͷ���
    Node next; 
}

/*
 * ������
 * 
 * */

class Vertex{   
    String name;
    Node root; // �����������Ϊ����ͷ�ڵ� 
    boolean visited = false;

}
public class AdjListDFS {
    Vertex[] vts;  // ���㼯��
    Node[] nodes; // ������
    ArrayList<Vertex> path ;  // ����·��
    int n = 0;
    Scanner sc = new Scanner(System.in);
    /**
     * ���붥������
     */
    public void  Getvts() {
        System.out.println("���붥���������");
        n = sc.nextInt();
        vts = new Vertex[n];
        nodes = new Node[n];
        sc.nextLine();
        for(int i = 0; i < n; i ++) {
            System.out.println("����� �� " +(i + 1) +"�����������");
            vts[i] = new Vertex();
            nodes[i] = new  Node();
            vts[i].name = sc.nextLine();    
            nodes[i].vex = vts[i];
            vts[i].root = nodes[i];
        }
    }


    /**
     *  ����ͼ
     */
    public void CreateGra() {
        int i, j;
        String str = null;
        String[] strarr = null;

        for (i = 0; i < n; i ++) {
            System.out.println("����  " +vts[i].name + "���������ӵĶ��� �±�(�ÿո�տ�) ע�� ��һ�������±��� 0" );
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
     *  ��ӡͼ
     */
    public void PrinGraph() {

        for(int i = 0; i < nodes.length; i++) {
            Node p = nodes[i];
            System.out.println("���Ƕ��㣺 " + nodes[i].vex.name + " ������ : " );
            while(p!=null) {
                System.out.print(  p.vex.name + "-> "   );
                p = p.next;
            }
            System.out.println();
        }   
    }



    /**
     * 
     * ��һ��for ���ڱ������еĶ��� ֻ��ȫ���ڵ㶼�����ʹ����п��ܷ���
     * ��������:
     *      1. ����ýڵ�δ�����ʹ����������������������
     *      2. ��������ÿһ��Ԫ�ص����µ�����ͷ���еݹ������
     *      3. path ���ڼ�¼����˳��
     * 
     * �ܽ� ��
     *      ��һ��for ȷ�����нڵ㶼�����ʹ�
     *      Dfsvisit ��ȷ��ÿ�������������� ����һ�����в�����
     * 
     */
    public void Dfs() {
        path = new ArrayList<>();
        int i;
        for (i = 0; i < vts.length; i++ ) {  // ȷ�����нڵ㱻���ʹ��� �ó��Ľ����ȷ����ӹ����

            if (vts[i].visited == false) {  // �жϽڵ��Ƿ񱻷��ʹ�
                vts[i].visited = true;  // ���ýڵ��Ƿ��ʹ�
                path.add(vts[i]);  // ��ӵ�·����
                Dfsvisit(vts[i].root);  // �Ըýڵ��������з���
            }

        }
    }
    private void Dfsvisit(Node node) {
        Node p = node.next;  // ȡ������ĵ�һԪ��
        while(p != null) {  // ��Ϊ����δ�������ʹ�
            if(  p.vex.visited == false) {
                p.vex.visited = true;  // ����Ϊ���ʹ�
                path.add(p.vex); // ����·��
                Dfsvisit(p.vex.root);  // �Ե�һ��Ԫ�����ڵ�������з���
            }else {
                p = p.next;
            }
        }
    }
    /**
     * Ҫ�㣺  
     *    ͨ���������У� ʵ��Bfs�㷨��
     *   1. �Ƚ�һ�������ͷ�ڵ������С� 
     *   2. ͨ��while  ������ÿһ���ڵ�����Ӧ�������ͷ�����������
     *   3. �ı�ڵ��״̬�� ���ڵ���뵽·���м���
     * 
     * 
     */
    public void Bfs() {
        path = new ArrayList<>();
        Queue<Node> qn = new LinkedList<Node>();  // ���츨������
        qn.add(nodes[0]);  // ���һ�������ͷ���  �ɻ�
        while(!qn.isEmpty()) { 
            Node p = qn.remove();  // �ӵ�һ����ʼ  ����
            while(p != null) {   // ���while ���ڽ����и�������� �ڵ� ���뵽������
                if(p.vex.visited == false) {
                    p.vex.visited = true;  // ״̬�ı�
                    path.add(p.vex);  // ���뵽·����
                    p = p.next;   // �¸��ڵ�
                    if (p != null)
                        qn.add(p.vex.root);  // ���ýڵ��������������뵽������

                }else {
                    p = p.next;  // ����ýڵ��ѱ����ʹ�  �� ������һ���ڵ�
                }

            }   
        }
    }


    /**
     *  ������·����ӡ����
     */
    public void Prinpath() {
        System.out.print("����·���� "+path.get(0).name);
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
//        System.out.println("ִ��DFS����� ");
//        gp.Dfs();
//        gp.Prinpath();
        
      System.out.println("ִ��BFS����� "); // ���Ե�ʱ��DFS��BFSҪ�ֿ���  ��Ϊ �����Ƿ��ʹ��� 
      gp.Bfs();
      gp.Prinpath();
    }

}

