package com.juzi.leetcodecn.part1;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
 * https://leetcode.cn/problems/integer-to-roman/description/
 *
 * @author : yl
 * @date : 2023/11/9
 */
public class 整数转罗马数字_12 {


    public static void main(String[] args) {
        整数转罗马数字_12 instance = new 整数转罗马数字_12();
        System.out.println(instance.intToRoman(80));
        System.out.println(instance.intToRoman(90));
        System.out.println(instance.intToRoman(44));
        System.out.println(instance.intToRoman(1));
        System.out.println(instance.intToRoman(9));
        System.out.println(instance.intToRoman(7));
        System.out.println(instance.intToRoman(67));
        System.out.println(instance.intToRoman(400));
    }

    /**
     * 使用StringBuilder 拼接打败 100%
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int temp = num;
        int factor = 1;
        while (temp != 0) {
            temp /= 10;
            factor *= 10;
        }

        return getMatchLetter(num, factor);
    }

    /**
     * 思路：
     *  从大到小，如果当前值大于阈值(9,5,4) 返回与之对应的值进行拼接，然后将当前值删除阈值
     */
    public String getMatchLetter(int num, int factor) {
        if (num <= 0) {
            return "";
        }
        if (num < factor) {
            return getMatchLetter(num, factor / 10);
        }
        switch (factor) {
            case 1:
                if (num == 9) {
                    return "IX";
                }
                if (num >= 5) {
                    return "V" + getMatchLetter(num - 5, 1);
                }
                if (num == 4) {
                    return "IV";
                }
                return "I" + getMatchLetter(num - 1, 1);
            case 10:
                if (num >= 90) {
                    return "XC" + getMatchLetter(num - 90, 1);
                }
                if (num >= 50) {
                    return "L" + getMatchLetter(num - 50, num - 50 >= factor ? factor : 1);
                }
                if (num >= 40) {
                    return "XL" + getMatchLetter(num - 40, 1);
                }
                return "X" + getMatchLetter(num - 10, num - 10 >= factor ? factor : 1);
            case 100:
                if (num >= 900) {
                    return "CM" + getMatchLetter(num - 900, 10);
                }
                if (num >= 500) {
                    return "D" + getMatchLetter(num - 500, num - 500 >= factor ? factor : 10);
                }
                if (num >= 400) {
                    return "CD" + getMatchLetter(num - 400, 10);
                }
                return "C" + getMatchLetter(num - 100, num - 100 >= factor ? factor : 10);
            case 1000:
                if (num > 1000) {
                    return "M" + getMatchLetter(num - 1000, num - 1000 >= factor ? factor : 100);
                }
                return "M";
            default:
                return "";
        }
    }


}
