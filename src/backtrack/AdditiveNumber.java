package backtrack;

/*
累加数是一个字符串，组成它的数字可以形成累加序列。

一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。

说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

示例 1:

输入: "112358"
输出: true
解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
示例 2:

输入: "199100199"
输出: true
解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 */
public class AdditiveNumber {
    boolean result=false;
    public boolean isAdditiveNumber(String num) {
        for(int i=0;i<num.length();i++){
            if(result==true)break;
            String num2="";
            String num1=num.substring(0,i+1);
            if(i!=num.length()-1) {
                num2 = num.substring(i + 1);
            }
            for(int j=0;j<num2.length();j++){
                if(result==true)break;
                String a=num1;
                String b=num2.substring(0,j+1);
                if(b.charAt(0)=='0'&&b.length()!=1)
                    continue;
                int size=Math.max(a.length(),b.length());
                if(i+j+2+size<=num.length()&&StringAdd(a,b).equals(num.substring(i+j+2,i+j+2+size))||
                   i+j+3+size<=num.length()&&StringAdd(a,b).equals(num.substring(i+j+2,i+j+2+size+1))){
                    func(num.substring(i+j+2),num1,num2.substring(0,j+1));
                }
            }
        }
        return result;
    }

    private void func(String num,String num1,String num2){
        if(result)
            return;
        int size=Math.max(num1.length(),num2.length());
        if(size<num.length()&&StringAdd(num1,num2).equals(num.substring(0,size))){
            String temp=num1;
            num1=num2;
            num2=StringAdd(temp,num2);
            num=num.substring(size);
            func(num,num1,num2);
        }else if(size+1<num.length()&&StringAdd(num1,num2).equals(num.substring(0,size+1))){
            String temp=num1;
            num1=num2;
            num2=StringAdd(temp,num2);
            num=num.substring(size+1);
            func(num,num1,num2);
        }else if(size==num.length()&&StringAdd(num1,num2).equals(num.substring(0,size))||
                size+1==num.length()&&StringAdd(num1,num2).equals(num.substring(0,size+1))){
            result=true;
        }

    }

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

    public static void main(String[] args) {
        String s="101";
        AdditiveNumber a=new AdditiveNumber();
        System.out.println(a.isAdditiveNumber(s));
    }


}
