package com.wang.sort2;
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;


    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; // 灏嗘暟鎹鍒跺埌杈呭姪鏁扮粍
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];

            } else if (j > h) {
                nums[k] = aux[i++];

            } else if (aux[i].compareTo(nums[j]) <= 0) {
                nums[k] = aux[i++]; // 鍏堣繘琛岃繖涓�姝ワ紝淇濊瘉绋冲畾鎬�

            } else {
                nums[k] = aux[j++];
            }
        }
    }
}
