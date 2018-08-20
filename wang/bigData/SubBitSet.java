package com.wang.bigData;

//ȥ���ظ�������
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Gavenyeah
 * @date Time: 2016��4��15������9:20:21
 * @des:
 */
public class SubBitSet {
    int ARRNUM = 800;
    int LEN_INT = 32;
    int mmax = 9999;
    int mmin = 1000;
    int N = mmax - mmin + 1;

    public static void main(String args[]) {
         new SubBitSet().findDuplicate();
        new SubBitSet().findDup_jdk();
    }

    public void findDup_jdk() {
        System.out.println("*******����JDK�еĿⷽ��--��ʼ********");
        BitSet bitArray = new BitSet(N);
        int[] array = getArray(ARRNUM);
        for (int i = 0; i < ARRNUM; i++) {
            bitArray.set(array[i] - mmin);
        }
        int count = 0;
        for (int j = 0; j < bitArray.length(); j++) {
            if (bitArray.get(j)) {
                System.out.print(j + mmin + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("�����������СΪ��" + count );
        System.out.println("*******����JDK�еĿⷽ��--����********");
    }

    public void findDuplicate() {
        int[] array = getArray(ARRNUM);
        int[] bitArray = setBit(array);
        printBitArray(bitArray);
    }

    public void printBitArray(int[] bitArray) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getBit(bitArray, i) != 0) {
                count++;
                System.out.print(i + mmin + "\t");
            }
        }
        System.out.println();
        System.out.println("ȥ�������������СΪ��" + count);
    }

    public int getBit(int[] bitArray, int k) {// 1���� k % 32λ ���� �����±�Ϊ k/32 λ�õ�ֵ
        return bitArray[k / LEN_INT] & (1 << (k % LEN_INT));
    }

    public int[] setBit(int[] array) {// ����ȡ������λ���±� i/32, Ȼ�� ����
                                        // �ڸ�λ��int������ֵ��bitλ��i % 32
        int m = array.length;
        int bit_arr_len = N / LEN_INT + 1;
        int[] bitArray = new int[bit_arr_len];
        for (int i = 0; i < m; i++) {
            int num = array[i] - mmin;
            bitArray[num / LEN_INT] |= (1 << (num % LEN_INT));
        }
        return bitArray;
    }

    public int[] getArray(int ARRNUM) {

        @SuppressWarnings("unused")
        int array1[] = { 1000, 1002, 1032, 1033, 6543, 9999, 1033, 1000 };

        int array[] = new int[ARRNUM];
        System.out.println("�����С��" + ARRNUM);
        Random r = new Random();
        for (int i = 0; i < ARRNUM; i++) {
            array[i] = r.nextInt(N) + mmin;
        }

        System.out.println(Arrays.toString(array));
        return array;
    }
}