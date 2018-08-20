package com.wang.Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/*排序的空间复杂度
 * 插入排序，选择排序，冒泡排序，堆排序，希尔排序都是o(1)
 * 快排（o(logn)）,归并排序o(n)
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
	/* 选择排序：
	 * 以下为简单选择排序的存储状态，其中大括号内为无序区，大括号外为有序序列：
	 * 初始序列：{49 27 65 97 76 12 38}
	 * 第1趟：12与49交换：12{27 65 97 76 49 38}
	 * 第2趟：27不动　   ： 12 27{65 97 76 49 38}
	 * 第3趟：65与38交换：12 27 38{97 76 49 65}
	 * 第4趟：97与49交换：12 27 38 49{76 97 65}
	 * 第5趟：76与65交换：12 27 38 49 65{97 76}
	 * 第6趟：97与76交换：12 27 38 49 65 76 97 完成
	 * 共有a.length趟排序，每一趟找出无序区的最小元素，
	 * 如果无序区的第一个元素不是最小元素，
	 * 则将第一个元素与最小元素进行交换。
	 * */
	public void selectSort(int[] a) {
		int temp=0;
		
		for(int i=0;i<a.length-1;i++) {
			//对数据a进行选择排序，，需要排序a.length-1趟
			int index=i;//记录数组元素最小的位置
			for(int j=i+1;j<a.length;j++) {
				/*a[j]<a[index]是从小到大排序
				 *a[j]>a[index]是从大到小排序
				 * */
				if(a[j]<a[index]) {
					index=j;//查找最小元素所在位置，
				}
			}
			/*若无序区的第一个元素不是无序区的最小元素，
			 * 则将第一个元素与最小元素进行交换*/
			if(index!=i) {
				temp=a[index];
				a[index]=a[i];
				a[i]=temp;
			}
			
		}
	}
	
	/*
	 * 直接插入排序（Straight Insertion Sort）的基本操作是将一个记录插入到
	 * 已经排好序的有序表中，从而得到一个新的有序表。
	 * 从上边的描述我们可以知道，这里对数组进行排序的过程需要两个序列来完成；
	 * 一个待排序的乱序序列，一个是已经排好序的有序序列。我们现在要做的就是把乱序的元素
	 * 一个一个地从乱序序列插入到有序序列。
	 * 
	 *          4 3 6 5 9 乱序序列
	 * 有序序列：3 4 6，我们需要把5插入到4 6之间
	 * 我们让整体序列=局部有序+局部无序
	 * 算法步骤：
	 * 1.默认序列中第0个元素是有序的（因为只有一个元素a[0],所以是有序的）
	 * 2.从下标为1的元素开始，取当前下标i位置处的元素a[i]保存到临时变量waitInsert里
	 * 对前半部分有序序列进行循环遍历，并与waitInsert比较，直到遇到一个比waitInsert小的元素
	 * （默认从小到大排序，）
	 * 3.将前面较大的元素向后移动
	 * 4.令a[j+1]=waitInsert
	 * */
	public void straightInsertSort(int[] a) {
		for(int i=1;i<a.length;i++) {//n-1此扫描，依次向前插入n-1个元素
			int temp=a[i];   
			int j;//每趟将a[i]插入到前面的排序子序列中
			for(j=i-1;j>=0&&temp<a[j];j--)
			{
				//这里有个循环条件是temp<a[j]，如果不成立的话就跳出循环
					a[j+1]=a[j];  //将前面较大的元素向后移动 
				
				
			}
			a[j+1]=temp;      //temp值到达插入位置
		}
	}
	/*
	 * 二分法插入排序
	 * 在直接插入排序的基础上，如果数据量比较大，
	 * 为了减少关键代码的比较次数，可以使用二分插入来寻找插入位置
	 * */
	public void BinaryInsertSort(int[] a) {
		for(int i=1;i<a.length;i++) {
			int left=0;
			int right=i-1;
			int temp=a[i];
			while(left<=right) {//当左边界小于等于右边界时
				int mid = (left+right)/2;//取中点
				if(a[mid]>temp) {//如果中点值大于插入值
					right=mid-1;//向左缩小区间
				}
				else {
					left=mid+1;//向右缩小区间
				}
			}
			for(int j=i-1;j>=left;j--) {
				a[j+1]=a[j];
			}
			a[left]=temp;
		}
	}
	/*
	 * 冒泡排序法：单向冒泡排序：
	 * 1.对于给定的n个记录，从第一个记录开始依次对相邻的两个记录进行比较，
	 * 2.当前边的记录大于后面的记录时，交换位置。
	 * 3.交换第一轮后，n个记录将位于第n位，然后对前（n-1）个记录进行第二轮排序。
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
	 * 归并排序时利用递归和分治技术将数据序列划分成越来越小额半子表，在对半子表排序，
	 * 最后利用递归方法，将排序号的半子表合并成越来越大的有序序列。
	 * 例如[2,6,1,0],会先拆半，分为[2,6][1,0]两个子数组，在将折半数组分离，
	 * 分成[2],[6],[1],[0],并就是将分开的数据按照从小到大或者从大到小的顺序放到一个数组中。
	 * 原理：
	 * 1.首先将每两个相邻的长度为1的子序列进行归并，
	 * 得到n/2个长度为2或1的有序子序列，再将其两两合并，反复执行此过程。
	 * 归并排序：
	 * 1.划分半子表
	 * 2.合并半子表
	 * */
	//传入a,0,a.length-1
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
		int[] L=new int[n1];//把一个数组拆成两个
		int[] R=new int[n2];
		for(i=0,k=left;i<n1;i++,k++) {
			L[i]=a[k];
		}
		for(i=0,k=mid+1;i<n2;i++,k++) {
			R[i]=a[k];
		}
		for(k=left,i=0,j=0;i<n1&&j<n2;k++) {
			if(L[i]<R[j]) {
	/*   此处如果为<小于号，则是从小到大排序，如果是>大于号，则是从大到小排序*/
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
	 * 快速排序：快速排序是一种非常高效的排序算法，采用分而治之的思想，把大的拆分成小的，小的再拆分为更小的；
	 * 
	 * */
	//传入a,0,a.length-1
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
		int temp = a[low];//基准
		while(low<high)
		{
			while((low<high)&&(a[high]<=temp))//如果求从大到小，则a[high]<=temp,改变上下两个的大于小于号方向
			{
				high--;	
			}	
			swap(a,low,high);
			while((low<high)&&(a[low]>=temp))//如果求从大到小，则a[low]>=temp
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
	 * 已知一个几乎有序的数组，如果把数组排好顺序的话，每个元素移动的距离不超过k，并且
	 * k相对于数组长度来说很小，请问选择什么方法对其排序比较好。
	 * 1.时间复杂度为o(n2)的算法：冒泡排序，选择排序，与原始数据顺序无关
	 * 2.插入排序可以得到很好的效果，不会超过N*K
	 * 3.归并排序快排与原始顺序无关。
	 * 
	 * */
	//使用堆排序解决此问题：
	public int[] sortHeapElement(int[] b, int n, int k) {
        // write code here
		int[] heap=Arrays.copyOf(b, k);//最小堆数组
		buildMinHeap(heap,k);//初始最小堆
		for(int i=k;i<n;i++){//堆的长度为k
			b[i-k]=heap[0];
			heap[0]=b[i];
			buildMinHeap(heap,k);
		}
		
       for(;k>0;k--){//堆的长度不足k
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
     * 判断一个数组是否有重复值
     * 1.使用hashmap
     * 2.使用hashset
     * 3.如果不能使用额外空间，如何做
     * 4.使用堆排序
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
    //使用hashSet
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
    //先排序，再判断，不能有额外空间
    public boolean checkIsRepeat3(int[] array) {
    	//可采用快排
    	Sort sort = new Sort();
    	sort.quickSort(array, 0, array.length-1);
    	for(int i=1;i<array.length;i++) {
    		if(array[i]==array[i-1]) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    /*合并两个有序数组
     * 1.两个有序数组都是有序的，从小到大或者从大到小
     * 2.从两个有序数组最小的开始比较，如果a的最小元素小于b的最小元素，则把a的最xiao 元素拷贝到数组第一位
     * 3.然后比较b的最小元素与a的第二小元素
     * 4.首先，如何a中数据全部比b中数据小，则直接拷贝
     * 
     * 如果是两个无序数组，可先使用Arrays.sort();或者其他排序方法进行排序，再进行合并
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
     * 荷兰国旗问题：
     * 给定一个数组，它包括0,1,2三种数，
     * 如何做到0放到数组的左边，1放到数组的中间，2放到数组的右边。
     * 要求使用交换、原地排序，而不是利用计数进行排序。
     * 
     * 现有n个红白蓝三种不同颜色的小球，乱序排列在一起，请通过两两交换任意两个球，
     * 使得从左至右的球依次为红球、白球、蓝球。这个问题之所以叫荷兰国旗，
     * 是因为将红白蓝三色的小球弄成条状物，并有序排列后正好组成荷兰国旗。
     * 
     * 思路：
     * （1）当current指针所指元素为0时，与begin指针所指的元素交换，而后current++，begin++；
     * （2）当current指针所指元素为1时，不做任何交换，而后current++；
     * （3）当current指针所指元素为2时，与end指针所指的元素交换，而后current指针不动，end--.  
     *   为什么current指针不动，因为end指针所指的元素在交换前还没有遍历，所以交换后current
     *   还是需要停留在当前位置，判断这个交换过来的数是0还是1还是2.
     * （4）当current指针与end指针重合，遍历结束
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
     * 二维数组中查找指定的数
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序
     * 。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束。
     * 如果该数字大于要查找的数字，就剔除这个数字所在的列。如果该数字小于要查找的数字，
     * 就剔除这个数字所在的行。
     * 这样通过每次每次删除行或者列，来缩小查找范围。
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
    /*求需要排序的最短子数组问题
     * {1,5,3,4,2,6,7}
     * 我们可以得知，如果是正常升序序列，左边的一定是小于右边的任意数值，
     * 右边的一定大于左边的任意数值。
     * 所以我们从后往前遍历，如果某个元素大于右边最小的元素，就标记，一直遍历到最左边
     * 前往后遍历，如果某个元素小于左边的最大的元素，则标记，一直遍历到最右边
     * 
     * */
    public int shortestSubSequence(int[] A) {
        //假定最大值,最小值
        int max = A[0];
        int min = A[A.length - 1];
        //i和j之间的范围便是需要排序的最短子数组(一开始假定全部范围)
        int p = 0, q = A.length - 1;
        /**
         * 从右向左遍历，找出不合适数的最右范围
         * (遍历过部分的最大值大于当前正在遍历的值,那么当前值就是invalid,
         * 那么真实排序之后,这个最大值在当前位置,或者是更右的位置)
         * 只记录发生这种情况的最右位置
         */
        for(int i=1;i<A.length;i++) {
        	if(max>A[i]) {
        		p=i;
        	}else {
        		max=A[i];
        	}
        }
        /**
         * 从左向右遍历，找出不合适数的最左范围
         * (遍历过部分的最小值小于当前正在遍历的值,那么当前值就是invalid,那么真实排序之后,
         * 这个最小值在当前位置,或者是更左的位置)
         * 只记录发生这种情况的最左位置
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
    //相邻两数的最大差值
    /*有一个整形数组A，请设计一个复杂度为O(n)的算法，算出排序后相邻两数的最大差值。
     * 给定一个int数组A和A的大小n，请返回最大的差值。保证数组元素多于1个。
     * 测试样例：
     * [1,2,5,4,6],5
     * 返回：2
     * 基于桶排序的思想完成，不考虑两个相同的桶内的差值，只考虑该桶的最小值减去上一个桶的最大值，最大的就是最大值。
     * */
    //重点在O(n)上面，桶排序思想
    public int maxGap(int[] nums,int N) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //找出最大值和最小值
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
            bid = bucket(nums[i], len, min, max); // 算出桶号
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];//找出该桶的最小值
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];//找出该桶的最大值
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = 0;
        int i = 0;
        while (i <= len) {
            if (hasNum[i++]) { // 找到第一个不空的桶
                lastMax = maxs[i - 1];
                break;
            }
        }
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);//用该桶的最小值减去上一个桶的最大值得到差值最大
                lastMax = maxs[i];
            }
        }
        return res;
    }
    // 使用long类型是为了防止相乘时溢出
    public int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}

