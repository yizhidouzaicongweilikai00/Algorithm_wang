package com.wang.ArrayOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 1.public int findMaxMin(int[] a,int left,int right) 如何寻找数组中的最小值与最大值
 * 2.public int findSecMax(int[] a)如何找出数组中第二大的数：
 * 3.public int findKthNumber(int[] a,int k)寻找数组中第k大或者第k小的数字。
 * 4.public int maxSubArraySum(int[] a)求最大子数组之和
 * 5.public int findMostFrequentInArray(int[] a)如何找出数组中重复元素最多的数：,字符串中最多的字符，字符数组中最多的字符
 * 6.如何求数组中两两相加等于M的组合种数public int findCalOfSumOfTwoNumber(int[] a,int M)
 * 7.public  List<List<Integer>> kSum(int[] nums, int k,int target)对于一个数组，求从中取出k个数，这k个数的和为target的组合
 * 8. public  List<List<Integer>> combinationSum(int[] candidates,int target,int begin) 找出数组中任意个数字之和等于给定值target的组合
 * 9.public List<Integer> getKFrequent(int[] a,int k)如何找出数组中出现了k次的数字
 * 10.public int getMinAbsoluteValue(int[] a)如何求绝对值最小的数，例如数组{-10，-5，-2，7，15，50}，绝对值最小的是-2；
 * 11.public int getMax(int[] a)如何求数对之差的最大值
 * 12.public int findDuplicateNumber(int[] a)如何找出数组中唯一的重复元素
 * 13.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */

public class ArrayOperator {
	/*
	 * 如何寻找数组中的最小值与最大值
	 * 取单元素法：维持两个变量min和max，，min标记最小值，max标记最大值，每次
	 *            取出一个元素，先于已找到的最小值比较，再与已找到的最大值比较，此种方法只需要
	 *            遍历一次数组
	 * */
	public int findMaxMin(int[] a,int left,int right) {
		/*分治算法，首先考虑最后的情况，
		 * 1.如果数组为空，直接return；
		 * 2.如果只有最后只有数组中只有两个元素，则min为小的那个，max为大的那个
		 * 1.一定要先找到最小问题规模时的求解方法，
		 * 2.然后考虑随着问题增大时的求解方法。
		 * */
		int min=a[left];
		int min1=a[left];
		if(a==null||a.length<=0)
			return 0;
		if(right-left<=1) {
			if(a[left]>a[right]) {
				min=a[right];	
			}else {
				min=a[left];
			}
		}
		if(right-left>1) {
			int mid=(left+right)/2;
			min=findMaxMin(a, left, mid);
			min1=findMaxMin(a, mid+1, right);
			
		}
		return min>min1?min1:min;
	}
	/*
	 * 如何找出数组中第二大的数：
	 * 1.如果仅仅实现功能，而不用考虑时间效率，
	 *   则可以先通过排序算法对数组进行排序，然后通过下标得到第二大的数。
	 * 2.如果考虑时间复杂度则：
	 *   1.定义两个变量，一个变量用来存储数组最大元素，一个变量用来存储数组第二大元素
	 *   2.初始化两个变量，一个变量初始化为数组第一个元素，一个变量初始化为-1
	 *   3.比较数组元素与最大变量的值，如果数组元素大于最大变量，则让第二大变量等于最大变量，
	 *     让最大变量等于该数组元素
	 *     如果数组元素小于最大变量的值，则判断该数组元素是否比第二大数的值大，若数组元素的值
	 *     比第二大数的值大，则更新第二大数的值为该元素的值。
	 * 
	 * */
	public int findSecMax(int[] a) {
		int max=a[0];
		int secMax=-1;
		for(int i=1;i<a.length;i++) {
			if(a[i]>max) {
				secMax=max;
				max=a[i];
			}else {
				if(a[i]>secMax) {
					secMax=a[i];
				}
			}
		}
		return secMax;
	}
	/*
	 * 寻找数组中第k大的数字。
	 * 方法1：直接对数组进行排序，然后遍历一遍就可以找到第k大的数字，复杂度为O(N2)
	 * 
	 * 方法2：利用快速排序，然后遍历一遍，复杂度为O(NlogN)
	 * 
	 * 方法3：借助快速排序的思想
	 * 1.在数组中选择一个数作为支点，
	 * 2.将比作为支点数大的数放在这个支点的左边，支点放在数组中间的位置
	 * 3.设支点左边元素的个数为L，那么可以分为以下三种情况：
	 *   a:当k=L时，直接返回支点即是所要求的第k大的数字
	 *   b:当k<L时，再支点左边的数中继续寻找第k大的数字
	 *   c:当k>L时，在支点右边的数中寻找第k-L大的数字。
	 * */
	public int findKthNumber(int[] a,int k) {
		if(a==null||a.length<=0||k<0||k>a.length)
			return 0;
		int low=0;
		k=k-1;
		int high=a.length-1;
		int pos = Partition(a,low,high);
		//找到随机的一个参考元素的位置,他是第pos大的元素
		while(pos!=k) {//因为是从大到小排列
			if(pos<k) {
				low=pos+1;
				pos=Partition(a, low, high);
			}
			if(pos>k) {
				high=pos-1;
				pos=Partition(a, low, high);
			}
			
		}
		return a[pos];
	}
	private int Partition(int[] a, int low, int high) {
		// TODO Auto-generated method stub
		int temp=a[low];
		while(low<high) {
			while(low<high&&a[high]<=temp) {//如果求第k小的话是a[high]>=temp
				high--;
			}
			a[low]=a[high];
			while(low<high&&a[low]>=temp) {//如果求第k小的话是a[high]>=temp
				low++;
			}
			a[high]=a[low];
		}
		a[low]=temp;//确定参考元素的位置
		return low;
	}
	/*
	 * 求最大子数组之和：
	 * 问题描述：
	 * 一个有n个元素的数组，这n个元素可以是正数，也可以是负数，数组中连续的一个或
	 * 多个元素可以组成一个连续的子数组。一个数组可能有多个这种连续的子数组，
	 * 求子数组和的最大值
	 * 分析：看到这个题目首先想到的是求出这个整形数组的所有连续子数组的和，长度
	 * 为n的数组的子数组为((n+1）*n)/2个，所以要求出这些子数组的和最少也需要O(N2)的时间
	 * 复杂度，但题目要求是o(n)，所以不能满足要求：
	 * */
	//1.蛮力法：求出所有的子数组，然后求和。时间复杂度o(n2)
	public int maxSubArraySum(int[] a) {
		int maxSum=0;
		int thisSum=0;
		for(int i=0;i<a.length;i++) {
			thisSum=0;
			for(int j=i;j<a.length;j++) {
				thisSum=thisSum+a[j];
				if(thisSum>maxSum) {
					maxSum=thisSum;
				}
			}
		}
		return maxSum;
	}
	//动态规划法：
	/*
	 * 设sum[i]为以第i个元素结尾且和最大的连续子数组。假设对于元素i，所有以它前面的元素
	 * 结尾的子数组的长度都已经求得，那么以第i个元素结尾且和最大的连续子数组实际上，
	 * 要么是以第i-1个元素结尾且和最大的连续子数组加上这个元素，要么是只包含第i个元素，
	 * 即sum[i] = max(sum[i-1] + a[i], a[i])。
	 * 可以通过判断sum[i-1] + a[i]是否大于a[i]来做选择，而这实际上等价于
	 * 判断sum[i-1]是否大于0。由于每次运算只需要前一次的结果，
	 * 因此并不需要像普通的动态规划那样保留之前所有的计算结果，
	 * 只需要保留上一次的即可，因此算法的时间和空间复杂度都很小。
	 * */
	public int maxSumOfSubArray(int[] a) {
		int maxSum=0;//动态规划初始条件
		int curSum=0;//以当前元素结尾的最大和
		for(int i=0;i<a.length;i++) {
			curSum=curSum+a[i];
			if(curSum<=0) {
				//如果cursum<0说明加上a[i]后会拖累后面的结果。所以要从0重新算起。
				curSum=0;
			}
			
			if(curSum>maxSum) {
				maxSum=curSum;
			}
		}
		//特殊情况处理：若是数组中的元素均为负数，则输出里面的最大元素
		if(maxSum==0) {
			maxSum=a[0];
			for(int i=1;i<a.length;i++) {
				if(maxSum<a[i]) {
					maxSum=a[i];
				}
			}
		}
		return maxSum;
	}
	/*
	 * 如何找出数组中重复元素最多的数：,字符串中最多的字符，字符数组中最多的字符
	 * 使用Map表，键值对Map map = new HashMap();
	 * 对于数组{1，1，1，2，2，2，5，5，5，6，6，6，7，7，7，7，7，7，7}
	 * 1.创建一个HashMap，
	 * 2.遍历数组，如果a[i]在HashMap中，则计数加1，如果不在，则置1
	 * */
	public int findMostFrequentInArray(int[] a) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<a.length;i++) {
			if(map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i])+1);
			}else {
				map.put(a[i], 1);
			}
		}
		//上述程序遍历完，就把数组中的元素存入了Map中并计数
		
		/*
		 * Map不能使用for循环迭代，必须使用Iterator迭代器，
		 * 先转化为Set，然后使用迭代器进行迭代。
		 * */
		int maxCal=0;//创建一个int型存储最大计数
		int valueOfMaxCal=0;//创建一个int存储计数最大的值
		Set<Integer> keySet = map.keySet();//将map转化成Set
		Iterator<Integer> it = keySet.iterator();//创建一个迭代器
		while(it.hasNext()) {
			Integer key=it.next();
			int value = map.get(key);
			if(value>maxCal) {
				maxCal=value;
				valueOfMaxCal=key;
			}
		}
		return valueOfMaxCal;
	}
	
	/*
	 * 如何求数组中两两相加等于M的组合种数
	 * 两种方法
	 * 1.蛮力法
	 * 2.排序法
	 * */
	 /*1.蛮力法
	  * 直接采用双重循环遍历数组来判断两个数的和是否为M
	  * */
	public int findCalOfSumOfTwoNumber(int[] a,int M) {
		int count=0;
		List<Integer> list_i=new ArrayList<Integer>();
		List<Integer> list_j=new ArrayList<Integer>();
		for(int i=0;i<a.length;i++) {
			for(int j=i+1;j<a.length;j++) {
				if(a[i]+a[j]==M) {
					count++;
					list_i.add(a[i]);
					list_j.add(a[j]);
				}
			}
		}
		return count;
	}
	/*
	 *2. 排序法
	 *采用快排，先对数组进行排序，此时算法的复杂度为O(NlogN)
	 *然后对排序后的数组分别从前到后和从后到前遍历，假设从前往后遍历的下标为
	 *begin，从后往前遍历的下标为end，如果存在两个数的和a[begin]+a[end]<20,
	 *则a[begin]小了，所以让begin=begin+1，，如果a[begin]+a[end]>20，则令
	 *end=end-1;
	 *
	 *Array.sort(int[] a);
	 *Array.sort(int[] a, int fromIndex, int toIndex);
	 *采用的是快速排序的方法，时间复杂度为O(nlogn)，比我们自己写的排序算法还要快
	 * */
	public int findCalOfSumOfTwoNumber1(int[] a,int M) {
		int count=0;
		Arrays.sort(a);
		int begin=0;
		int end=a.length-1;
		while(begin<end) {
			if(a[begin]+a[end]<M) {
				begin++;
			}else if(a[begin]+a[end]>M) {
				end--;
			}else {
				count++;
				begin++;
				end--;
			}
		}
		return count;
	}
	/*
	 * 对于一个数组，求从中取出k个数，这k个数的和为target的组合
	 *将组合存储到一个List集合里
	 * */
	public  List<List<Integer>> kSum(int[] nums, int k,int target) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        result = recursionRoutin(nums,0,k,target);
        return result;
    }

    public  List<List<Integer>> recursionRoutin(int[] nums,int begin,int k,int target){
        HashSet<List<Integer>> elementSet = new HashSet<List<Integer>>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<List<Integer>> subResult = new ArrayList<List<Integer>>();
        //Recursion Base
        if(k == 2){
            int left = begin;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum == target){
                    List<Integer> taplet = new ArrayList<Integer>();
                    taplet.add(nums[left]);
                    taplet.add(nums[right]);
                    //Avoid reduplication 
                    if(!elementSet.contains(taplet)){
                        result.add(taplet);
                        elementSet.add(taplet);
                    }
                    left ++;
                    right --;
                }else if(sum < target){
                    left ++;
                }else{
                    right --;
                }
            }
            return result;
        }else{

            for(int i = begin;i < nums.length - k - 1;i ++){
                subResult = recursionRoutin(nums,i + 1,k - 1,target - nums[i]);
                //System.out.println(k + " " + subResult);
                if(!subResult.isEmpty()){
                    for(int j = 0;j < subResult.size();j ++){
                        subResult.get(j).add(nums[i]);
                        result.add(subResult.get(j));
                    }
                }
            }
        }
        return result;
    }
		/*end KSum*/
    
    /* Combination Sum
     * 找出数组中任意个数字之和等于给定值target的组合
     * 1.先对数组进行排序,这样做的目的是为了对数组后面
     *   不可能出现的情况进行排除，有利于减少查找时间，即剪枝操作
     * 2.外层循环对数组元素依次进行遍历，依次将nums中的元素加入中间集，
     *   一旦满足条件，就将中间集加入结果集。
     * 3.然后每次递归中把剩下的元素一一加到结果集中，并且把目标减去加入的元素，然后把剩下
     * */
    public  List<List<Integer>> combinationSum(int[] candidates,int target,int begin){
    	Arrays.sort(candidates);
    	List<List<Integer>> resultList = new ArrayList<>();
    	List<Integer> tempList = new ArrayList<>();
    	getResult(resultList,tempList,target,candidates,0);
    	return resultList;
    }
	private void getResult(List<List<Integer>> resultList, List<Integer> tempList, 
				               int target, int[] candidates,int j) {
			// TODO Auto-generated method stub
		if(target==0) {
			resultList.add(new ArrayList<>(tempList));
		}
		for(int i=j;i<candidates.length&&target>=candidates[i];i++) {
			tempList.add(candidates[i]);
			//此处递归时，若题目允许在一个子集中重复使用数组中的元素如原数组[1,2,3],target=3;
			//在一个子集中重复使用数组中的元素可得[1,1,1]也是数组的界，则递归i;
			//如果不允许在一个子集中重复使用你数组中的元素，则递归i+1;
			getResult(resultList, tempList, target-candidates[i], candidates, i+1);
			tempList.remove(tempList.size()-1);
		}
	}
	/*
	 * 如何找出数组中出现了k次的数字
	 * 1.使用Map,遍历数组，得出他们出现的此输，以键值对形式存储到Map中
	 * 2.创建List，遍历Map，找出值为k的key，添加到list中，return list；
	 * */
	public List<Integer> getKFrequent(int[] a,int k){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<a.length;i++) {
			if(map.containsKey(a[i])) {
				map.put(a[i], map.get(a[i])+1);
			}else {
				map.put(a[i], 1);
			}
		}
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			Integer key = it.next();
			if(map.get(key)==k){
				list.add(key);
			}
		}
		return list;
		
	}
	/*
	 * 如何找出数组中唯一的重复元素
	 * 问题描述：数组a[n],1~N-1;这N-1个数存放在a[N]中，其中某个数重复1次，写一个函数，
	 * 找出被重复的数字，要求每个数组元素只能访问1次，并且不用辅助存储空间。
	 * 解法：
	 * 1.由于每个数组元素只能访问1次，且不用辅助存储空间；
	 * 2.hi有1个数字重复1次，又是连续的，所以对数组求和，然后减去1~N-1的和，即为所求的重复数
	 * */
	public int findDuplicateNumber(int[] a) {
		int sumA=0;
		int sumN=0;
		for(int i=0;i<a.length;i++) {
			sumA=sumA+a[i];
			sumN=sumN+i;
		}
		
		return sumA-sumN;
		
	}
	/*
	 * 如何求数对之差的最大值
	 * 问题描述：数组中的一个数字减去它右边子数组中的一个数字可以得到一个差值，
	 * 求所有可能的差值中的最大值，例如，数组{1，4，17，3，2，9}中最大的差值是17-2=15
	 * 
	 * */
	/*方法一、蛮力法：，首先遍历数组，找到所有可能的差值，然后从差值中找出最大值,
	 * 算法复杂度o(n2)*/
	public int getMax(int[] a) {
		if(a==null||a.length<=0) {
			return 0;
		}
		int max=0;
		for(int i=0;i<a.length;i++) {
			for(int j=i+1;j<a.length;j++) {
				if(a[i]-a[j]>max) {
					max=a[i]-a[j];
				}
			}
		}
		return max;
	}
	/*
	 * 动态规划方法求解：
	 * 动态规划算法通常基于一个递推公式及一个或多个初始状态，
	 * 当前子问题的解将由上一子问题的解推出，使用动态规划来解题只需要
	 * 多项式时间复杂度。因此它比回溯法、暴力法快得多。
	 * 如此题：[1,4,17,3,2,9]
	 * max[i]为前i+1个元素中的最大值，diff[i]是以数组中第i个数为减数的所有数对之差的最大值（
	 * 前i+1个数组成的子数组中最大的差值），假设已经求得diff[i],则diff[i+1]有两种可能
	 * 1.diff[i] 2.max[i]-a[i+1]
	 * 当前子问题的解可以由上一子问题的解推出：
	 * 即：假设已知上一子数组求得最大的差值为diff[i],
	 * 
	 * */
	public int getMax1(int[] a) {
		int[] max=new int[a.length];
		int[] diff=new int[a.length];
		max[0]=a[0];
		diff[0]=0;
		for(int i=1;i<a.length;i++) {
			if(max[i-1]-a[i]>diff[i-1]) {
				diff[i]=max[i-1]-a[i];
			}else {
				diff[i]=diff[i-1];
			}
			
			if(max[i-1]<a[i]) {
				max[i]=a[i];
			}else {
				max[i]=max[i-1];
			}
			
		}
		return diff[a.length-1];
	}
	/*end getMax1*/
	
	/*
	 * 如何求绝对值最小的数，例如数组{-10，-5，-2，7，15，50}，绝对值最小的是-2；
	 * 问题描述：有一个升序排列的数组，数组中可能有正数、负数或0
	 * 可以分为3中情况：
	 * 1.如果数组最后一个元素为负数，那么绝对值最小的元素肯定是最后一个元素
	 * 2.如果数组第一个元素为0或者正数，那么绝对值最小的元素肯定是第一个元素
	 * 3.如果数组中既有负数又有正数，又有0；
	 * 那么首先找到正数和负数的分界点，如果分界点为0，则绝对值最小的元素为0.
	 * 否则比较分界点左边的绝对值与右边元素的大小
	 * */
	public int getMinAbsoluteValue(int[] a) {
		int len=a.length;
		int min=a[0];
		if(a==null||a.length<=0) {
			min = 0;
		}
		if(a[0]>=0) {
			min = a[0];
		}else if(a[len-1]<0) {
			min = a[len-1];		
		}
		int begin=0;
		int end=len-1;
		System.out.println("end");
		while(begin<end) {
			int mid=(begin+end)/2;
			System.out.println("mid:"+mid);
			if(a[mid]==0) {
				min = a[mid];
			}else if(a[mid]>0) {
				end=mid-1;
			}else if(a[mid]<0) {
				begin=mid+1;
			}
		}
		System.out.println("begin:"+begin);
		if(a[begin]==0||a[begin-1]==0) {
			min = 0;
		}
		if(a[begin-1]<0&&a[begin]>0) {
			if(Math.abs(a[begin-1])>a[begin]) {
				min = a[begin];
			}else {
				min = a[begin-1];
			}
		}
		if(a[begin]<0&&a[begin-1]<0) {
			min=a[begin];
		}
		if(a[begin]>0&&a[begin-1]>0) {
			min=a[begin-1];
		}
		return min;	
	}
	
	/*
	 * 如何求数组中两个元素的最小距离
	 * */
	public int searchInsert(int[] nums, int target) {
        int begin=0;
        int end=nums.length-1;
        int index=-1;
        if(nums==null || nums.length<=0){
            return -1;
        }
        while(nums[begin]<nums[end]){
            if(nums[begin]==target){
                index = begin;
            }else if(nums[end]==target){
                index = end;
            }else{
                begin++;
                end--;
            }
        }
        System.out.println(begin);
        System.out.println(end);
        index=begin;
        return index;
    }
	
}
