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
 * 1.public int findMaxMin(int[] a,int left,int right) ���Ѱ�������е���Сֵ�����ֵ
 * 2.public int findSecMax(int[] a)����ҳ������еڶ��������
 * 3.public int findKthNumber(int[] a,int k)Ѱ�������е�k����ߵ�kС�����֡�
 * 4.public int maxSubArraySum(int[] a)�����������֮��
 * 5.public int findMostFrequentInArray(int[] a)����ҳ��������ظ�Ԫ����������,�ַ����������ַ����ַ������������ַ�
 * 6.�����������������ӵ���M���������public int findCalOfSumOfTwoNumber(int[] a,int M)
 * 7.public  List<List<Integer>> kSum(int[] nums, int k,int target)����һ�����飬�����ȡ��k��������k�����ĺ�Ϊtarget�����
 * 8. public  List<List<Integer>> combinationSum(int[] candidates,int target,int begin) �ҳ����������������֮�͵��ڸ���ֵtarget�����
 * 9.public List<Integer> getKFrequent(int[] a,int k)����ҳ������г�����k�ε�����
 * 10.public int getMinAbsoluteValue(int[] a)��������ֵ��С��������������{-10��-5��-2��7��15��50}������ֵ��С����-2��
 * 11.public int getMax(int[] a)���������֮������ֵ
 * 12.public int findDuplicateNumber(int[] a)����ҳ�������Ψһ���ظ�Ԫ��
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
	 * ���Ѱ�������е���Сֵ�����ֵ
	 * ȡ��Ԫ�ط���ά����������min��max����min�����Сֵ��max������ֵ��ÿ��
	 *            ȡ��һ��Ԫ�أ��������ҵ�����Сֵ�Ƚϣ��������ҵ������ֵ�Ƚϣ����ַ���ֻ��Ҫ
	 *            ����һ������
	 * */
	public int findMaxMin(int[] a,int left,int right) {
		/*�����㷨�����ȿ������������
		 * 1.�������Ϊ�գ�ֱ��return��
		 * 2.���ֻ�����ֻ��������ֻ������Ԫ�أ���minΪС���Ǹ���maxΪ����Ǹ�
		 * 1.һ��Ҫ���ҵ���С�����ģʱ����ⷽ����
		 * 2.Ȼ����������������ʱ����ⷽ����
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
	 * ����ҳ������еڶ��������
	 * 1.�������ʵ�ֹ��ܣ������ÿ���ʱ��Ч�ʣ�
	 *   �������ͨ�������㷨�������������Ȼ��ͨ���±�õ��ڶ��������
	 * 2.�������ʱ�临�Ӷ���
	 *   1.��������������һ�����������洢�������Ԫ�أ�һ�����������洢����ڶ���Ԫ��
	 *   2.��ʼ������������һ��������ʼ��Ϊ�����һ��Ԫ�أ�һ��������ʼ��Ϊ-1
	 *   3.�Ƚ�����Ԫ������������ֵ���������Ԫ�ش��������������õڶ������������������
	 *     �����������ڸ�����Ԫ��
	 *     �������Ԫ��С����������ֵ�����жϸ�����Ԫ���Ƿ�ȵڶ�������ֵ��������Ԫ�ص�ֵ
	 *     �ȵڶ�������ֵ������µڶ�������ֵΪ��Ԫ�ص�ֵ��
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
	 * Ѱ�������е�k������֡�
	 * ����1��ֱ�Ӷ������������Ȼ�����һ��Ϳ����ҵ���k������֣����Ӷ�ΪO(N2)
	 * 
	 * ����2�����ÿ�������Ȼ�����һ�飬���Ӷ�ΪO(NlogN)
	 * 
	 * ����3���������������˼��
	 * 1.��������ѡ��һ������Ϊ֧�㣬
	 * 2.������Ϊ֧����������������֧�����ߣ�֧����������м��λ��
	 * 3.��֧�����Ԫ�صĸ���ΪL����ô���Է�Ϊ�������������
	 *   a:��k=Lʱ��ֱ�ӷ���֧�㼴����Ҫ��ĵ�k�������
	 *   b:��k<Lʱ����֧����ߵ����м���Ѱ�ҵ�k�������
	 *   c:��k>Lʱ����֧���ұߵ�����Ѱ�ҵ�k-L������֡�
	 * */
	public int findKthNumber(int[] a,int k) {
		if(a==null||a.length<=0||k<0||k>a.length)
			return 0;
		int low=0;
		k=k-1;
		int high=a.length-1;
		int pos = Partition(a,low,high);
		//�ҵ������һ���ο�Ԫ�ص�λ��,���ǵ�pos���Ԫ��
		while(pos!=k) {//��Ϊ�ǴӴ�С����
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
			while(low<high&&a[high]<=temp) {//������kС�Ļ���a[high]>=temp
				high--;
			}
			a[low]=a[high];
			while(low<high&&a[low]>=temp) {//������kС�Ļ���a[high]>=temp
				low++;
			}
			a[high]=a[low];
		}
		a[low]=temp;//ȷ���ο�Ԫ�ص�λ��
		return low;
	}
	/*
	 * �����������֮�ͣ�
	 * ����������
	 * һ����n��Ԫ�ص����飬��n��Ԫ�ؿ�����������Ҳ�����Ǹ�����������������һ����
	 * ���Ԫ�ؿ������һ�������������顣һ����������ж�����������������飬
	 * ��������͵����ֵ
	 * ���������������Ŀ�����뵽���������������������������������ĺͣ�����
	 * Ϊn�������������Ϊ((n+1��*n)/2��������Ҫ�����Щ������ĺ�����Ҳ��ҪO(N2)��ʱ��
	 * ���Ӷȣ�����ĿҪ����o(n)�����Բ�������Ҫ��
	 * */
	//1.��������������е������飬Ȼ����͡�ʱ�临�Ӷ�o(n2)
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
	//��̬�滮����
	/*
	 * ��sum[i]Ϊ�Ե�i��Ԫ�ؽ�β�Һ��������������顣�������Ԫ��i����������ǰ���Ԫ��
	 * ��β��������ĳ��ȶ��Ѿ���ã���ô�Ե�i��Ԫ�ؽ�β�Һ���������������ʵ���ϣ�
	 * Ҫô���Ե�i-1��Ԫ�ؽ�β�Һ���������������������Ԫ�أ�Ҫô��ֻ������i��Ԫ�أ�
	 * ��sum[i] = max(sum[i-1] + a[i], a[i])��
	 * ����ͨ���ж�sum[i-1] + a[i]�Ƿ����a[i]����ѡ�񣬶���ʵ���ϵȼ���
	 * �ж�sum[i-1]�Ƿ����0������ÿ������ֻ��Ҫǰһ�εĽ����
	 * ��˲�����Ҫ����ͨ�Ķ�̬�滮��������֮ǰ���еļ�������
	 * ֻ��Ҫ������һ�εļ��ɣ�����㷨��ʱ��Ϳռ临�Ӷȶ���С��
	 * */
	public int maxSumOfSubArray(int[] a) {
		int maxSum=0;//��̬�滮��ʼ����
		int curSum=0;//�Ե�ǰԪ�ؽ�β������
		for(int i=0;i<a.length;i++) {
			curSum=curSum+a[i];
			if(curSum<=0) {
				//���cursum<0˵������a[i]������ۺ���Ľ��������Ҫ��0��������
				curSum=0;
			}
			
			if(curSum>maxSum) {
				maxSum=curSum;
			}
		}
		//��������������������е�Ԫ�ؾ�Ϊ�������������������Ԫ��
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
	 * ����ҳ��������ظ�Ԫ����������,�ַ����������ַ����ַ������������ַ�
	 * ʹ��Map����ֵ��Map map = new HashMap();
	 * ��������{1��1��1��2��2��2��5��5��5��6��6��6��7��7��7��7��7��7��7}
	 * 1.����һ��HashMap��
	 * 2.�������飬���a[i]��HashMap�У��������1��������ڣ�����1
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
		//������������꣬�Ͱ������е�Ԫ�ش�����Map�в�����
		
		/*
		 * Map����ʹ��forѭ������������ʹ��Iterator��������
		 * ��ת��ΪSet��Ȼ��ʹ�õ��������е�����
		 * */
		int maxCal=0;//����һ��int�ʹ洢������
		int valueOfMaxCal=0;//����һ��int�洢��������ֵ
		Set<Integer> keySet = map.keySet();//��mapת����Set
		Iterator<Integer> it = keySet.iterator();//����һ��������
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
	 * �����������������ӵ���M���������
	 * ���ַ���
	 * 1.������
	 * 2.����
	 * */
	 /*1.������
	  * ֱ�Ӳ���˫��ѭ�������������ж��������ĺ��Ƿ�ΪM
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
	 *2. ����
	 *���ÿ��ţ��ȶ�����������򣬴�ʱ�㷨�ĸ��Ӷ�ΪO(NlogN)
	 *Ȼ�������������ֱ��ǰ����ʹӺ�ǰ�����������ǰ����������±�Ϊ
	 *begin���Ӻ���ǰ�������±�Ϊend����������������ĺ�a[begin]+a[end]<20,
	 *��a[begin]С�ˣ�������begin=begin+1�������a[begin]+a[end]>20������
	 *end=end-1;
	 *
	 *Array.sort(int[] a);
	 *Array.sort(int[] a, int fromIndex, int toIndex);
	 *���õ��ǿ�������ķ�����ʱ�临�Ӷ�ΪO(nlogn)���������Լ�д�������㷨��Ҫ��
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
	 * ����һ�����飬�����ȡ��k��������k�����ĺ�Ϊtarget�����
	 *����ϴ洢��һ��List������
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
     * �ҳ����������������֮�͵��ڸ���ֵtarget�����
     * 1.�ȶ������������,��������Ŀ����Ϊ�˶��������
     *   �����ܳ��ֵ���������ų��������ڼ��ٲ���ʱ�䣬����֦����
     * 2.���ѭ��������Ԫ�����ν��б��������ν�nums�е�Ԫ�ؼ����м伯��
     *   һ�������������ͽ��м伯����������
     * 3.Ȼ��ÿ�εݹ��а�ʣ�µ�Ԫ��һһ�ӵ�������У����Ұ�Ŀ���ȥ�����Ԫ�أ�Ȼ���ʣ��
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
			//�˴��ݹ�ʱ������Ŀ������һ���Ӽ����ظ�ʹ�������е�Ԫ����ԭ����[1,2,3],target=3;
			//��һ���Ӽ����ظ�ʹ�������е�Ԫ�ؿɵ�[1,1,1]Ҳ������Ľ磬��ݹ�i;
			//�����������һ���Ӽ����ظ�ʹ���������е�Ԫ�أ���ݹ�i+1;
			getResult(resultList, tempList, target-candidates[i], candidates, i+1);
			tempList.remove(tempList.size()-1);
		}
	}
	/*
	 * ����ҳ������г�����k�ε�����
	 * 1.ʹ��Map,�������飬�ó����ǳ��ֵĴ��䣬�Լ�ֵ����ʽ�洢��Map��
	 * 2.����List������Map���ҳ�ֵΪk��key����ӵ�list�У�return list��
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
	 * ����ҳ�������Ψһ���ظ�Ԫ��
	 * ��������������a[n],1~N-1;��N-1���������a[N]�У�����ĳ�����ظ�1�Σ�дһ��������
	 * �ҳ����ظ������֣�Ҫ��ÿ������Ԫ��ֻ�ܷ���1�Σ����Ҳ��ø����洢�ռ䡣
	 * �ⷨ��
	 * 1.����ÿ������Ԫ��ֻ�ܷ���1�Σ��Ҳ��ø����洢�ռ䣻
	 * 2.hi��1�������ظ�1�Σ����������ģ����Զ�������ͣ�Ȼ���ȥ1~N-1�ĺͣ���Ϊ������ظ���
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
	 * ���������֮������ֵ
	 * ���������������е�һ�����ּ�ȥ���ұ��������е�һ�����ֿ��Եõ�һ����ֵ��
	 * �����п��ܵĲ�ֵ�е����ֵ�����磬����{1��4��17��3��2��9}�����Ĳ�ֵ��17-2=15
	 * 
	 * */
	/*����һ���������������ȱ������飬�ҵ����п��ܵĲ�ֵ��Ȼ��Ӳ�ֵ���ҳ����ֵ,
	 * �㷨���Ӷ�o(n2)*/
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
	 * ��̬�滮������⣺
	 * ��̬�滮�㷨ͨ������һ�����ƹ�ʽ��һ��������ʼ״̬��
	 * ��ǰ������Ľ⽫����һ������Ľ��Ƴ���ʹ�ö�̬�滮������ֻ��Ҫ
	 * ����ʽʱ�临�Ӷȡ�������Ȼ��ݷ�����������öࡣ
	 * ����⣺[1,4,17,3,2,9]
	 * max[i]Ϊǰi+1��Ԫ���е����ֵ��diff[i]���������е�i����Ϊ��������������֮������ֵ��
	 * ǰi+1������ɵ������������Ĳ�ֵ���������Ѿ����diff[i],��diff[i+1]�����ֿ���
	 * 1.diff[i] 2.max[i]-a[i+1]
	 * ��ǰ������Ľ��������һ������Ľ��Ƴ���
	 * ����������֪��һ������������Ĳ�ֵΪdiff[i],
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
	 * ��������ֵ��С��������������{-10��-5��-2��7��15��50}������ֵ��С����-2��
	 * ������������һ���������е����飬�����п�����������������0
	 * ���Է�Ϊ3�������
	 * 1.����������һ��Ԫ��Ϊ��������ô����ֵ��С��Ԫ�ؿ϶������һ��Ԫ��
	 * 2.��������һ��Ԫ��Ϊ0������������ô����ֵ��С��Ԫ�ؿ϶��ǵ�һ��Ԫ��
	 * 3.��������м��и�����������������0��
	 * ��ô�����ҵ������͸����ķֽ�㣬����ֽ��Ϊ0�������ֵ��С��Ԫ��Ϊ0.
	 * ����ȽϷֽ����ߵľ���ֵ���ұ�Ԫ�صĴ�С
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
	 * ���������������Ԫ�ص���С����
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
