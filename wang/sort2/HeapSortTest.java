package com.wang.sort2;
import org.junit.Assert;
import org.junit.Test;

/**
 * 鍫嗘帓搴忚姹傛暟缁勭 0 涓厓绱犱笉浣跨敤
 * 鍥犳娴嬭瘯鐢ㄤ緥鍜屽叾瀹冩帓搴忔柟娉曚笉鍚�
 * 闇�瑕佸崟鐙垱寤轰竴涓祴璇曠被
 */
public class HeapSortTest {

    @Test
    public void sort() {

        Integer[] numsBefore = {0, 2, 3, 6, 5, 4, -1, -2, 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        Integer[] numsAfter = {0, Integer.MIN_VALUE, -2, -1, 0, 2, 3, 4, 5, 6, Integer.MAX_VALUE};

        HeapSort<Integer> sort = new HeapSort<>();
        sort.sort(numsBefore);

        Assert.assertArrayEquals(numsBefore, numsAfter);
    }
}