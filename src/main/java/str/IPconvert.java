package com.wh.test;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName: Main
 *
 * @author: hw83770
 */
public class Main {

  int[] space = new int[100];
  int sindex = 0;

  public static void main(String[] args) {
    System.out.println("----------");
    //    long ll = 2896692481L;
    //    int t = (int) (ll & 0xFFFFFFFFL);
    //    Integer a = new Integer(t);
    //    System.out.println(Integer.toBinaryString(a));
    //    System.out.println(Integer.valueOf("11111111111111111111111111111110", 2).toString());

    //    byte[] all = new byte[4];

    //    String ss = "335  ";
    //    System.out.println(mergeToInt(ss.toCharArray(), 0, ss.length() - 1));

    int a = convertString("172.168.5.3");
    System.out.println("int value: " + a);
    long ll = a & 0xFFFFFFFFl;
    System.out.println("unsigned: "+ ll);
  }

 
  /**
   * 
   * 172.168.5.1 -> 2896692481
   * 
   * 4294967294
   *
   * 2896692480 2896692481 2896692481
   *
   * 2147483647 
   * 
   * 10101100
   *
   * 10101100101010000000010100000001 
   * 10101100101010000000010100000001
   *
   * 10101100 10101000 00000101 00000001
   *
   * 1010110010101000000001010
   *
   * @param str
   */
  public static int convertString(String str) {

    int dotIndex = 0; // dot numbers

    byte[] res = new byte[4];
    int bIndex = 0;

    int k = 0; // last dot index.

    char[] chrs = str.toCharArray();

    for (int i = 0; i < chrs.length; i++) {
      if (chrs[i] == ' ') {
        if (!check(chrs, i)) throw new RuntimeException("invalid");
      }

      if (chrs[i] == '.') {
        if (dotIndex > 2) throw new RuntimeException("out of bound");

        res[bIndex] = (byte) mergeToInt(chrs, k, i - 1);
        k = i + 1;
        bIndex++;
        dotIndex++;
      }
    }

    res[bIndex] = (byte) mergeToInt(chrs, k, chrs.length - 1);

    int haha = byteArrayToInt(res);

    return haha;
  }

  public static int byteArrayToInt(byte[] b) {
    return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
  }

  public static int mergeToInt(char[] arr, int left, int right) {
    boolean flag = true;
    int newLeft = left;
    int newRight = right;
    for (int i = left; i < right; i++) {
      if (arr[i] != ' ' && flag) {
        newLeft = i;
        flag = false;
      }

      if (arr[i + 1] == ' ' && !flag) {
        newRight = i;
        break;
      }
    }

    String snum = new String(arr, newLeft, newRight - newLeft + 1);
    Integer resInteger = Integer.parseInt(snum);

    System.out.println("binary: " + Integer.toBinaryString(resInteger));
    return resInteger;
  }

  public static boolean check(char[] arr, int index) {
    if (index == 0 || index == arr.length - 1) {
      return true;
    }

    if (isNum(arr[index + 1]) && isNum(arr[index - 1])) {
      return false;
    }
    return true;
  }

  public static boolean isNum(char c) {
    if (c >= '0' && c <= '9') return true;
    return false;
  }
}
