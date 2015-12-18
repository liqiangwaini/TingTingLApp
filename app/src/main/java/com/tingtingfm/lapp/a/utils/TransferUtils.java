package com.tingtingfm.lapp.a.utils;

/**
 * Created by lenovo on 2015/12/17.
 */
public class TransferUtils {
    /**
     * 将给定的数字按照一定的规则进行转换输出<br>
     * 规则如下：<br>
     * 长度大于4位，单位(万)，小数点后留1位<br>
     *
     * 长度大于8位，单位(亿)，小数点后留1位<br>
     */
    public static String getNum(int value) {
        if (value  < (int) Math.pow(10, 4)) {
            return String.valueOf(value);
        } else if (value < (int) Math.pow(10, 8)) {
            return value / 10000 + "." + (value % 10000) / 1000 + "万";
        } else {
            return value / 100000000 + "." + (value % 100000000) / 10000000 + "亿";
        }
    }
}
