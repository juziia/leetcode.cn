package com.juzi.leetcodecn.part1;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。
 * 必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，
 * 小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符
 * @author : yl
 * @date : 2023/11/8
 */
public class 字符串转换整数_8 {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        字符串转换整数_8 solution = new 字符串转换整数_8();
        solution.getNum("-+123", stringBuilder, 0,false);
        System.out.println(stringBuilder);
        System.out.println(solution.myAtoi(stringBuilder.toString()));
    }


    public int myAtoi(String s) {
        StringBuilder sb = new StringBuilder();
        getNum(s, sb, 0,false);
        if (sb.length() == 0) return 0;

        boolean flag = false;
        if (sb.charAt(0) == '+' || (flag = sb.charAt(0) == '-')) {
            sb.deleteCharAt(0);
        }
        String numStr = sb.toString();
        String s1 = String.valueOf(Integer.MAX_VALUE);
        if (numStr.length() >= s1.length()) {
            if (!flag && (numStr.length() > s1.length() || numStr.compareTo(s1) >= 0)) {
                return Integer.MAX_VALUE;
            }
            if (flag) {
                String s2 = "2147483648";
                if (numStr.length() > s1.length() || numStr.compareTo(s2) >= 0) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return flag ? -Integer.parseInt(numStr) : Integer.parseInt(numStr);
    }

    public void getNum(String a, StringBuilder sb, int pos,boolean zero) {
        if (pos >= a.length()) return;
        char c = a.charAt(pos);

        int numLength = sb.length();
        boolean prevNum = numLength <= 0 || isNum(sb.charAt(numLength - 1));

        if (zero && c == ' ') return;

        if ((c == ' ') && prevNum && (numLength == 0 || (numLength == 1 && isFlag(sb.charAt(0))))) {
            getNum(a, sb, pos + 1,false);
            return;
        }

        if ((c == '0') && (numLength == 0 || (numLength == 1 && isFlag(sb.charAt(0))))) {
            getNum(a, sb, pos + 1,true);
            return;
        }

        if (c == ' ') {
            return;
        }

        boolean flag = numLength == 1 && isFlag(sb.charAt(0));
        if (isNum(c) && (prevNum || flag)) {
            if (flag && c == '0') {
                getNum(a, sb, pos + 1,true);
                return;
            }
            sb.append(c);
            getNum(a, sb, pos + 1,false);
        } else {
            if (isFlag(c) && numLength == 0 && (pos == 0 || a.charAt(pos - 1) == ' ')) {
                sb.append(c);
                getNum(a, sb, pos + 1,false);
                if (sb.length() == 1) {
                    sb.deleteCharAt(0);
                }
            }
        }

    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isFlag(char c) {
        return c == '+' || c == '-';
    }

}