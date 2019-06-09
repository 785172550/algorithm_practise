package str;

import java.util.Arrays;

/**
 * @ClassName: IPconvert
 * @author: hw83770
 */
public class IPconvert {

    // test case
    private static char[][] samples = new char[][]{
            "172.168.5.1".toCharArray(),
            "255.4.6.125".toCharArray(),
            "  66  . 223.336.6 ".toCharArray(),
            "88.77.2   .4".toCharArray(),
            "0.0.0.0".toCharArray(),
            "22.44.1.3 4".toCharArray()
    };

    public static void main(String[] args) throws Exception {

        /*
         Java dont have unsigned int type, but in the example(172.168.5.1 -> 2896692481),
         the number 2896692481 is over MAX_VALUE of signed integer, will be cast to -1398274815.
         considered this case, if output need a unsigned int, I cast int to long just for print.
         Essentially speaking, their binary value are the same(2896692481 vs -1398274815), just different decode style

         java不支持 unsigned int 类型， 大于 2147483647 MAX_INT 的数会被视为负数，但是本质上来讲两者的二进制码没有区别，
         只是，一个是用的原码，一个是补码来解析。 为了方便展示和示例中的效果，我用long 型装载该值，使得其转换为源码解析。

        */

        // test case
        Arrays.stream(samples).forEach(item -> {
            System.out.println(" ");
            System.out.println("################################################ ");
            int signedInteger = 0;
            try {
                signedInteger = convertString(item); // get converted value
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage() + " '" + new String(item) + "' ");
                return;
            }

            System.out.println("binary string : " + Integer.toBinaryString(signedInteger));
            System.out.println("signed int value:   " + signedInteger);

            System.out.println("--- transformed --- ");

            long unsignedInt = signedInteger & 0x0FFFFFFFFL; // change signedInt to unsigned int
            System.out.println("binary string : " + Long.toBinaryString(unsignedInt));
            System.out.println("unsigned int value: " + unsignedInt);
        });
    }


    /*
     * question:
      * covert  172.168.5.1 -> 2896692481
     *  only one iteraion
     *  check invalid input
     *
     * 4294967294
     *
     * 2896692480
     * 2896692481
     *
     *  1011000010011010000001000000100   1481441796  正
     * 10101100101010000000010100000001  -1398274815  负
     *
       10101100101010000000010100000001
     * 10101100 10101000 00000101 00000001
     *
     * 2147483647 MAX_INT
     *
     */
    public static int convertString(char[] str) throws Exception {

        int dotIndex = 0; // dot count

        byte[] segments = new byte[4]; // 8 bit for a segment
        int sIndex = 0; // segment index

        int segLeft = 0; // last dot index

        char[] chrs = str;

        for (int i = 0; i < chrs.length; i++) { // only one iteration
            if (chrs[i] == ' ') {
                if (!checkFormat(chrs, i)) throw new Exception("invalid input");  // check blank if in two numbers
            }

            if (chrs[i] == '.') {
                if (dotIndex > 2) throw new Exception("invalid input");

                // 根据 '.' 分为四个段区间[k...i-1]， 每个闭区间内的值，合并为一个int值，然后cast为byte
                segments[sIndex] = (byte) mergeToInt(chrs, segLeft, i - 1);
                segLeft = i + 1;
                sIndex++;
                dotIndex++;
            }
        }

        // 最后一个区间
        segments[sIndex] = (byte) mergeToInt(chrs, segLeft, chrs.length - 1);
        return byteArrayToInt(segments);
    }

    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }

    private static int mergeToInt(char[] arr, int left, int right) {
        boolean flag = true;
        int newLeft = left;
        int newRight = right;

        for (int i = left; i < right; i++) {
            if (arr[i] != ' ' && flag) { // filter head blanks
                newLeft = i;
                flag = false;
            }

            if (arr[i + 1] == ' ' && !flag) { // filter tail blanks
                newRight = i;
                break;
            }
        }
        // numStr 等价于数组 char[newLeft...newRight]  e.g.['1','7','2']
        String numStr = new String(arr, newLeft, newRight - newLeft + 1);
        return Integer.parseInt(numStr);
    }

    private static boolean checkFormat(char[] arr, int index) {
        if (index == 0 || index == arr.length - 1) // dont check first and last items
            return true;

        return !(isNum(arr[index + 1]) && isNum(arr[index - 1]));  // return false if this item in two numbers
    }

    private static boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }
}
