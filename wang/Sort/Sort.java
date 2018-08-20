package com.wang.Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/*����Ŀռ临�Ӷ�
 * ��������ѡ������ð�����򣬶�����ϣ��������o(1)
 * ���ţ�o(logn)��,�鲢����o(n)
 * 1.public void printArray(int[] a);
 * 2.public void selectSort(int[] a);
 * 3.public void straightInsertSort(int[] a);
 * 4.public void BinaryInsertSort(int[] a);
 * 5.public void BubbleSort(int[] a);
 * 6.public void MergeSort(int[] a,int left,int right);
 * 7.public void quickSort(int[] a,int low,int high);
 * 
 * */

public class Sort {
	
	
	public void printArray(int[] a) {
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+"  ");
		}
	}
	
	public void insertSort(int[] a) {
		for(int i=1;i<a.length;i++) {
			int key=a[i];
			int j=i-1;
			while(j>=0&&a[j]>key) {
				a[j+1]=a[j];
				j=j-1;
			}
			a[j+1]=key;
			
		}
	}
	/* ѡ������
	 * ����Ϊ��ѡ������Ĵ洢״̬�����д�������Ϊ����������������Ϊ�������У�
	 * ��ʼ���У�{49 27 65 97 76 12 38}
	 * ��1�ˣ�12��49������12{27 65 97 76 49 38}
	 * ��2�ˣ�27������   �� 12 27{65 97 76 49 38}
	 * ��3�ˣ�65��38������12 27 38{97 76 49 65}
	 * ��4�ˣ�97��49������12 27 38 49{76 97 65}
	 * ��5�ˣ�76��65������12 27 38 49 65{97 76}
	 * ��6�ˣ�97��76������12 27 38 49 65 76 97 ���
	 * ����a.length������ÿһ���ҳ�����������СԪ�أ�
	 * ����������ĵ�һ��Ԫ�ز�����СԪ�أ�
	 * �򽫵�һ��Ԫ������СԪ�ؽ��н�����
	 * */
	public void selectSort(int[] a) {
		int temp=0;
		
		for(int i=0;i<a.length-1;i++) {
			//������a����ѡ�����򣬣���Ҫ����a.length-1��
			int index=i;//��¼����Ԫ����С��λ��
			for(int j=i+1;j<a.length;j++) {
				/*a[j]<a[index]�Ǵ�С��������
				 *a[j]>a[index]�ǴӴ�С����
				 * */
				if(a[j]<a[index]) {
					index=j;//������СԪ������λ�ã�
				}
			}
			/*���������ĵ�һ��Ԫ�ز�������������СԪ�أ�
			 * �򽫵�һ��Ԫ������СԪ�ؽ��н���*/
			if(index!=i) {
				temp=a[index];
				a[index]=a[i];
				a[i]=temp;
			}
			
		}
	}
	
	/*
	 * ֱ�Ӳ�������Straight Insertion Sort���Ļ��������ǽ�һ����¼���뵽
	 * �Ѿ��ź����������У��Ӷ��õ�һ���µ������
	 * ���ϱߵ��������ǿ���֪��������������������Ĺ�����Ҫ������������ɣ�
	 * һ����������������У�һ�����Ѿ��ź�����������С���������Ҫ���ľ��ǰ������Ԫ��
	 * һ��һ���ش��������в��뵽�������С�
	 * 
	 *          4 3 6 5 9 ��������
	 * �������У�3 4 6��������Ҫ��5���뵽4 6֮��
	 * ��������������=�ֲ�����+�ֲ�����
	 * �㷨���裺
	 * 1.Ĭ�������е�0��Ԫ��������ģ���Ϊֻ��һ��Ԫ��a[0],����������ģ�
	 * 2.���±�Ϊ1��Ԫ�ؿ�ʼ��ȡ��ǰ�±�iλ�ô���Ԫ��a[i]���浽��ʱ����waitInsert��
	 * ��ǰ�벿���������н���ѭ������������waitInsert�Ƚϣ�ֱ������һ����waitInsertС��Ԫ��
	 * ��Ĭ�ϴ�С�������򣬣�
	 * 3.��ǰ��ϴ��Ԫ������ƶ�
	 * 4.��a[j+1]=waitInsert
	 * */
	public void straightInsertSort(int[] a) {
		for(int i=1;i<a.length;i++) {//n-1��ɨ�裬������ǰ����n-1��Ԫ��
			int temp=a[i];   
			int j;//ÿ�˽�a[i]���뵽ǰ���������������
			for(j=i-1;j>=0&&temp<a[j];j--)
			{
				//�����и�ѭ��������temp<a[j]������������Ļ�������ѭ��
					a[j+1]=a[j];  //��ǰ��ϴ��Ԫ������ƶ� 
				
				
			}
			a[j+1]=temp;      //tempֵ�������λ��
		}
	}
	/*
	 * ���ַ���������
	 * ��ֱ�Ӳ�������Ļ����ϣ�����������Ƚϴ�
	 * Ϊ�˼��ٹؼ�����ıȽϴ���������ʹ�ö��ֲ�����Ѱ�Ҳ���λ��
	 * */
	public void BinaryInsertSort(int[] a) {
		for(int i=1;i<a.length;i++) {
			int left=0;
			int right=i-1;
			int temp=a[i];
			while(left<=right) {//����߽�С�ڵ����ұ߽�ʱ
				int mid = (left+right)/2;//ȡ�е�
				if(a[mid]>temp) {//����е�ֵ���ڲ���ֵ
					right=mid-1;//������С����
				}
				else {
					left=mid+1;//������С����
				}
			}
			for(int j=i-1;j>=left;j--) {
				a[j+1]=a[j];
			}
			a[left]=temp;
		}
	}
	/*
	 * ð�����򷨣�����ð������
	 * 1.���ڸ�����n����¼���ӵ�һ����¼��ʼ���ζ����ڵ�������¼���бȽϣ�
	 * 2.��ǰ�ߵļ�¼���ں���ļ�¼ʱ������λ�á�
	 * 3.������һ�ֺ�n����¼��λ�ڵ�nλ��Ȼ���ǰ��n-1������¼���еڶ�������
	 * 
	 * */
	public void BubbleSort(int[] a) {
		int temp=0;
		for(int i=0;i<a.length-1;i++) {
			for(int j=0;j<a.length-i-1;j++) {
				if(a[j]>a[j+1]) {
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}
	/*
	 * �鲢����ʱ���õݹ�ͷ��μ������������л��ֳ�Խ��ԽС����ӱ��ڶ԰��ӱ�����
	 * ������õݹ鷽����������ŵİ��ӱ�ϲ���Խ��Խ����������С�
	 * ����[2,6,1,0],���Ȳ�룬��Ϊ[2,6][1,0]���������飬�ڽ��۰�������룬
	 * �ֳ�[2],[6],[1],[0],�����ǽ��ֿ������ݰ��մ�С������ߴӴ�С��˳��ŵ�һ�������С�
	 * ԭ��
	 * 1.���Ƚ�ÿ�������ڵĳ���Ϊ1�������н��й鲢��
	 * �õ�n/2������Ϊ2��1�����������У��ٽ��������ϲ�������ִ�д˹��̡�
	 * �鲢����
	 * 1.���ְ��ӱ�
	 * 2.�ϲ����ӱ�
	 * */
	//����a,0,a.length-1
	public void MergeSort(int[] a,int left,int right) {
		
		if(left<right) {
			int mid=(left+right)/2;
			MergeSort(a,left,mid);
			MergeSort(a,mid+1,right);
			Merge(a,left,mid,right);
		
		}
	}
	private void Merge(int[] a, int left, int mid, int right) {
		// TODO Auto-generated method stub
		int i,j,k,n1,n2;
		n1=mid-left+1;
		n2=right-mid;
		int[] L=new int[n1];//��һ������������
		int[] R=new int[n2];
		for(i=0,k=left;i<n1;i++,k++) {
			L[i]=a[k];
		}
		for(i=0,k=mid+1;i<n2;i++,k++) {
			R[i]=a[k];
		}
		for(k=left,i=0,j=0;i<n1&&j<n2;k++) {
			if(L[i]<R[j]) {
	/*   �˴����Ϊ<С�ںţ����Ǵ�С�������������>���ںţ����ǴӴ�С����*/
				a[k]=L[i];
				i++;
			}else {
				a[k]=R[j];
				j++;
			}
		}
		if(i<n1) {
			for(j=i;j<n1;j++,k++) {
				a[k]=L[j];
			}
		}
		if(j<n2) {
			for(i=j;i<n2;i++,k++) {
				a[k]=R[i];
			}
		}
	}
	/*   end MergeSort*/
	
	
	/*
	 * �������򣺿���������һ�ַǳ���Ч�������㷨�����÷ֶ���֮��˼�룬�Ѵ�Ĳ�ֳ�С�ģ�С���ٲ��Ϊ��С�ģ�
	 * 
	 * */
	//����a,0,a.length-1
	public void quickSort(int[] a,int low,int high) {
		if(low<high)
		{
			int tmp = partition_down(a,low,high);
			quickSort(a,low,tmp-1);
			quickSort(a,tmp+1,high);		
		}
	}
	int partition_down(int[] a,int low,int high)
	{
		int temp = a[low];//��׼
		while(low<high)
		{
			while((low<high)&&(a[high]<=temp))//�����Ӵ�С����a[high]<=temp,�ı����������Ĵ���С�ںŷ���
			{
				high--;	
			}	
			swap(a,low,high);
			while((low<high)&&(a[low]>=temp))//�����Ӵ�С����a[low]>=temp
			{
				low++;	
			}
			swap(a,low,high);
		}
		return low;
	}
	void swap(int[] a,int i,int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	/*
	 * ��֪һ��������������飬����������ź�˳��Ļ���ÿ��Ԫ���ƶ��ľ��벻����k������
	 * k��������鳤����˵��С������ѡ��ʲô������������ȽϺá�
	 * 1.ʱ�临�Ӷ�Ϊo(n2)���㷨��ð������ѡ��������ԭʼ����˳���޹�
	 * 2.����������Եõ��ܺõ�Ч�������ᳬ��N*K
	 * 3.�鲢���������ԭʼ˳���޹ء�
	 * 
	 * */
	//ʹ�ö������������⣺
	public int[] sortHeapElement(int[] b, int n, int k) {
        // write code here
		int[] heap=Arrays.copyOf(b, k);//��С������
		buildMinHeap(heap,k);//��ʼ��С��
		for(int i=k;i<n;i++){//�ѵĳ���Ϊk
			b[i-k]=heap[0];
			heap[0]=b[i];
			buildMinHeap(heap,k);
		}
		
       for(;k>0;k--){//�ѵĳ��Ȳ���k
    	   b[n-k]=heap[0];
    	   heap[0]=heap[k-1];
    	   buildMinHeap(heap,k);
       }
        return b;
    }
    
   
    public void buildMinHeap(int [] a,int heapsize){
        for(int i=(heapsize-1)/2;i>=0;i--){
            minHeapify(a,heapsize,i);
        }
    }
    public void minHeapify(int [] a,int heapsize,int i){
        int min=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<heapsize&&a[left]<a[i]) min=left;
        if(right<heapsize&&a[right]<a[min]) min=right;
        if(min!=i){
            int temp=a[i];
            a[i]=a[min];
            a[min]=temp;
            minHeapify(a,heapsize,min);
        }
    }
    /*
     * �ж�һ�������Ƿ����ظ�ֵ
     * 1.ʹ��hashmap
     * 2.ʹ��hashset
     * 3.�������ʹ�ö���ռ䣬�����
     * 4.ʹ�ö�����
     * */
    public boolean checkIsRepeat(int[] array) {
    	HashMap<Integer,Integer>  map = new HashMap<Integer,Integer>();
    	for(int i=0;i<array.length;i++) {
    		if(map.containsKey(array[i])) {
    			map.put(array[i], map.get(array[i])+1);
    		}else {
    			map.put(array[i], 1);
    		}
    	}
    	if(map.size()<array.length) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public boolean checkIsRepeat1(int[] array) {
    	HashMap<Integer,Integer>  map = new HashMap<Integer,Integer>();
    	for(int i=0;i<array.length;i++) {
    		if(map.containsKey(array[i])) {
    			map.put(array[i], map.get(array[i])+1);
    		}else {
    			map.put(array[i], 1);
    		}
    	}
    	Iterator itera = map.entrySet().iterator();
    	while(itera.hasNext()) {
    		Map.Entry<Integer, Integer> entry = (Entry<Integer, Integer>) itera.next();
    		if(entry.getValue()!=1) {
    			return true;
    		}
    	}
    	return false;
    }
    //ʹ��hashSet
    public boolean checkIsRepeat2(int[] array) {
    	HashSet hset = new HashSet();
    	for(int i=0;i<array.length;i++) {
    		boolean b = hset.add(array[i]);
    		if(!b) {
    			return b;
    		}
    	}
		return true;    	
    }
    //���������жϣ������ж���ռ�
    public boolean checkIsRepeat3(int[] array) {
    	//�ɲ��ÿ���
    	Sort sort = new Sort();
    	sort.quickSort(array, 0, array.length-1);
    	for(int i=1;i<array.length;i++) {
    		if(array[i]==array[i-1]) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    /*�ϲ�������������
     * 1.�����������鶼������ģ���С������ߴӴ�С
     * 2.����������������С�Ŀ�ʼ�Ƚϣ����a����СԪ��С��b����СԪ�أ����a����xiao Ԫ�ؿ����������һλ
     * 3.Ȼ��Ƚ�b����СԪ����a�ĵڶ�СԪ��
     * 4.���ȣ����a������ȫ����b������С����ֱ�ӿ���
     * 
     * ����������������飬����ʹ��Arrays.sort();�����������򷽷����������ٽ��кϲ�
     * */
    public int[] mergeOrderArray(int[] a,int[] b) {
    	int alen=a.length;
    	int blen=b.length;
    	int [] array = new int[alen+blen];
    	if(a[alen-1]<=b[0]) {
    		for(int i=0;i<a.length;i++) {
    			array[i]=a[i];
    		}
    		for(int i=a.length;i<array.length;i++) {
    			array[i]=b[i-a.length];
    		}
    	}else if(a[0]>b[blen-1]) {
    		for(int i=0;i<b.length;i++) {
    			array[i]=b[i];
    		}
    		for(int i=b.length;i<array.length;i++) {
    			array[i]=a[i-b.length];
    		}
    	}else {
    		int i=0,j=0,k=0;
    		
    		while(i<alen&&j<blen) {
    			if(a[i]<b[j]) {
    				array[k]=a[i];
    				k++;
    				i++;
    			}else if(a[i]>=b[j]) {
    				array[k]=b[j];
    				k++;
    				j++;
    			}
    		}
    		if(i==alen) {
    			while(j<blen) {
    				array[k]=b[j];
    				k++;
    				j++;
    			}
    		}else if(j==blen) {
    			while(i<alen) {
    				array[k]=a[i];
    				k++;
    				i++;
    			}
    		}
    	}
    	
		return array;
    	
    }
    /*
     * �����������⣺
     * ����һ�����飬������0,1,2��������
     * �������0�ŵ��������ߣ�1�ŵ�������м䣬2�ŵ�������ұߡ�
     * Ҫ��ʹ�ý�����ԭ�����򣬶��������ü�����������
     * 
     * ����n����������ֲ�ͬ��ɫ��С������������һ����ͨ��������������������
     * ʹ�ô������ҵ�������Ϊ���򡢰��������������֮���Խк������죬
     * ����Ϊ���������ɫ��С��Ū����״����������к�������ɺ������졣
     * 
     * ˼·��
     * ��1����currentָ����ָԪ��Ϊ0ʱ����beginָ����ָ��Ԫ�ؽ���������current++��begin++��
     * ��2����currentָ����ָԪ��Ϊ1ʱ�������κν���������current++��
     * ��3����currentָ����ָԪ��Ϊ2ʱ����endָ����ָ��Ԫ�ؽ���������currentָ�벻����end--.  
     *   Ϊʲôcurrentָ�벻������Ϊendָ����ָ��Ԫ���ڽ���ǰ��û�б��������Խ�����current
     *   ������Ҫͣ���ڵ�ǰλ�ã��ж������������������0����1����2.
     * ��4����currentָ����endָ���غϣ���������
     * */
    public int[] HollandFlagProblem(int[] array) {
    	if(array==null||array.length==0)
    		return null;
    	int begin=0;
    	int current=0;
    	int end =array.length-1;
    	while(current<=end) {
    		if(array[current]==1) {
    			current++;
    		}else if(array[current]==0) {
    			array[begin]=array[begin]^array[current];
    			array[current]=array[begin]^array[current];
    			array[begin]=array[begin]^array[current];
    			current++;
    			begin++;
    		}else if(array[current]==2) {
    			array[end]=array[end]^array[current];
    			array[current]=array[end]^array[current];
    			array[end]=array[end]^array[current];
    			end--;
    		}
    	}
		return array;
    }
    /*
     * ��ά�����в���ָ������
     * ��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳������
     * �������һ������������������һ����ά�����һ���������ж��������Ƿ��и�������
     * ����ѡȡ���������Ͻǵ����֡���������ֵ���Ҫ���ҵ����֣����ҹ��̽�����
     * ��������ִ���Ҫ���ҵ����֣����޳�����������ڵ��С����������С��Ҫ���ҵ����֣�
     * ���޳�����������ڵ��С�
     * ����ͨ��ÿ��ÿ��ɾ���л����У�����С���ҷ�Χ��
     * */
    public boolean findTwoDensionArray(int[][] array,int target) {
    	int row=0;
    	int col=array[0].length-1;
    	while(row<=array.length-1&&col>=0) {
    		if(target==array[row][col]) {
    			return true;
    		}else if(target<array[row][col]) {
    			col--;
    		}else {
    			row++;
    		}
    	}
    	return false;
    }
    /*����Ҫ������������������
     * {1,5,3,4,2,6,7}
     * ���ǿ��Ե�֪������������������У���ߵ�һ����С���ұߵ�������ֵ��
     * �ұߵ�һ��������ߵ�������ֵ��
     * �������ǴӺ���ǰ���������ĳ��Ԫ�ش����ұ���С��Ԫ�أ��ͱ�ǣ�һֱ�����������
     * ǰ������������ĳ��Ԫ��С����ߵ�����Ԫ�أ����ǣ�һֱ���������ұ�
     * 
     * */
    public int shortestSubSequence(int[] A) {
        //�ٶ����ֵ,��Сֵ
        int max = A[0];
        int min = A[A.length - 1];
        //i��j֮��ķ�Χ������Ҫ��������������(һ��ʼ�ٶ�ȫ����Χ)
        int p = 0, q = A.length - 1;
        /**
         * ��������������ҳ��������������ҷ�Χ
         * (���������ֵ����ֵ���ڵ�ǰ���ڱ�����ֵ,��ô��ǰֵ����invalid,
         * ��ô��ʵ����֮��,������ֵ�ڵ�ǰλ��,�����Ǹ��ҵ�λ��)
         * ֻ��¼�����������������λ��
         */
        for(int i=1;i<A.length;i++) {
        	if(max>A[i]) {
        		p=i;
        	}else {
        		max=A[i];
        	}
        }
        /**
         * �������ұ������ҳ���������������Χ
         * (���������ֵ���СֵС�ڵ�ǰ���ڱ�����ֵ,��ô��ǰֵ����invalid,��ô��ʵ����֮��,
         * �����Сֵ�ڵ�ǰλ��,�����Ǹ����λ��)
         * ֻ��¼�����������������λ��
         */
        for(int i=A.length-2;i>=0;i--) {
        	if(min<A[i]) {
        		q=i;
        	}else {
        		min=A[i];
        	}
        }
        if(p==0&&q==A.length-1) {
        	return 0;
        }

    	return p-q+1;
    }
    //��������������ֵ
    /*��һ����������A�������һ�����Ӷ�ΪO(n)���㷨������������������������ֵ��
     * ����һ��int����A��A�Ĵ�Сn���뷵�����Ĳ�ֵ����֤����Ԫ�ض���1����
     * ����������
     * [1,2,5,4,6],5
     * ���أ�2
     * ����Ͱ�����˼����ɣ�������������ͬ��Ͱ�ڵĲ�ֵ��ֻ���Ǹ�Ͱ����Сֵ��ȥ��һ��Ͱ�����ֵ�����ľ������ֵ��
     * */
    //�ص���O(n)���棬Ͱ����˼��
    public int maxGap(int[] nums,int N) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //�ҳ����ֵ����Сֵ
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max); // ���Ͱ��
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];//�ҳ���Ͱ����Сֵ
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];//�ҳ���Ͱ�����ֵ
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = 0;
        int i = 0;
        while (i <= len) {
            if (hasNum[i++]) { // �ҵ���һ�����յ�Ͱ
                lastMax = maxs[i - 1];
                break;
            }
        }
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);//�ø�Ͱ����Сֵ��ȥ��һ��Ͱ�����ֵ�õ���ֵ���
                lastMax = maxs[i];
            }
        }
        return res;
    }
    // ʹ��long������Ϊ�˷�ֹ���ʱ���
    public int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}

