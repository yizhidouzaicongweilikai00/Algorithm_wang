package com.wang.bigData;
class BitMapCheckDuplicate {   
    private static final byte MAX = 127;   
    
    public static void main(String[] args) throws InterruptedException {   
        int m = 100000000 ;       
        BitMapCheckDuplicate hm = new BitMapCheckDuplicate(m) ;              
        hm.add(155555) ;              
        System.out.println(hm.contains(15));   
    }     
    
    public BitMapCheckDuplicate() {   
        bytes = new byte[12500000];   
    }     
    public BitMapCheckDuplicate(int size) {   
        bytes = new byte[size];   
    }     
    private byte[] bytes = null;     
    public void add(int i) {   
        int r = i / 8;   
        int c = i % 8;   
        bytes[r] = (byte) (bytes[r] | (1 << c));   
    }     
    public boolean contains(int i) {   
        int r = i / 8;   
        int c = i % 8;   
        if (((byte) ((bytes[r] >>> c)) & 1) == 1) {   
            return true;   
        }   
        return false;   
    }     
    public void remove(int i) {   
        int r = i / 8;   
        int c = i % 8;   
        bytes[r] = (byte) (bytes[r] & (((1 << (c + 1)) - 1) ^ MAX));   
    }    
}
