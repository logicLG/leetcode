package util;

public class StringOp {
    //字符串相加
    private static String StringAdd(String str1, String str2) {
        //任何一个字符串为null或空字符串，都不需要相加了
        if (str1 == null || "".equals(str1)) {
            return str2;
        }
        if (str2 == null || "".equals(str2)) {
            return str1;
        }
        int maxLength = Math.max(str1.length(), str2.length());
        //定义一个存贮结果的字符串，长度要比最大长度字符串还长一位，用于存储可能出现的进位
        StringBuffer result = new StringBuffer(maxLength + 1);

        //翻转两个字符串
        str1 = new StringBuffer(str1).reverse().toString();
        str2 = new StringBuffer(str2).reverse().toString();

        //反转后的结果分别为：
        //954321
        //321
        int minLength = Math.min(str1.length(), str2.length());
        //进位
        int carry = 0;
        //当前位上的数值
        int currentNum = 0;
        //循环变量
        int i = 0;
        for (; i < minLength; i++) {
            //分别获取两个字符对应的字面数值，然后相加，再加上进位
            currentNum = str1.charAt(i) + str2.charAt(i) - 2 * '0' + carry;
            //获取进位
            carry = currentNum / 10;
            //处理当前位的最终值
            currentNum %= 10;
            //保存当前位的值到最终的字符缓冲区中
            result.append(String.valueOf(currentNum));
        }
        if (str1.length() < str2.length()) {
            //选择
            str1 = str2;
        }
        for (; i < str1.length(); i++) {
            //分别获取两个字符对应的字面数值，然后相加，再加上进位
            currentNum = str1.charAt(i) - '0' + carry;
            //获取进位
            carry = currentNum / 10;
            //处理当前位的最终值
            currentNum %= 10;
            //保存当前位的值到最终的字符缓冲区中
            result.append(String.valueOf(currentNum));
        }
        //处理最后一个的进位(当循环结束后，是不是还可能会有一个进位)
        if (carry > 0) {
            result.append(String.valueOf(carry));
        }
        //最后翻转恢复字符串，再返回
        return result.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }

        if ("1".equals(num2)) {
            return num1;
        }
        int num1Length = num1.length();
        int num2Length = num2.length();
        int sumLength = num1Length + num2Length;//规律，比如123*456，有三个积，每次左移一位，都处理成同长的数组，多出来的位当成0，然后相加。三位数*三位数则同长数组最长是6位
        int[] result = new int[sumLength];//存储结果
        int leftMulti, rightMulti, product, inc = 0, startIndex = 0, index = 0;
        for (int i = num1Length - 1; i >= 0; i--) {
            leftMulti = num1.charAt(i) - '0';
            for (int j = num2Length - 1; j >= 0; j--) {
                index = i + j + 1;
                rightMulti = num2.charAt(j) - '0';
                product = leftMulti * rightMulti + inc + result[index];//去除对product<10,>=10的判断，直接相加再/10,%10即可
                inc = product / 10;
                result[index] = product % 10;
                if (j == 0 && inc != 0) {
                    result[index - 1] = inc;//前面那一位
                    inc = 0;//进位已向左前进1位，别忘了置为0
                }
            }//for
        }//for
        if (inc != 0) {
            result[0] = inc;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (sb.length() != 0 || result[i] != 0) {//对取结果数值的代码做优化
                sb.append(result[i]);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }//multiply


}
