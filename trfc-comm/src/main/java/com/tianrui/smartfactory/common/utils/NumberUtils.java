package com.tianrui.smartfactory.common.utils;

import org.apache.commons.lang.StringUtils;

public class NumberUtils {

    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆","伍", "陆", "柒", "捌", "玖" };
    
    private static final String[] CN_UPPER_NUMBER_UNIT = {"", "拾", "佰", "仟", "万"};
    
    private static final String DOT = ".";
    
    private static final String CN_UPPER_DOT = "点";
    
    private static final String CN_UPPER_ZERO = "零";
    
//    private static final String CN_UPPER_MINUS = "负";
    
    public static String numberUpper(String num) {
        String intUpper = "";
        String decUpper = "";
        if (StringUtils.contains(num, DOT)) {
            //小数
            if (StringUtils.startsWith(num, DOT)) {
                num = "0" + num;
            }
            String[] arr = StringUtils.split(num, DOT);
            //整数部分
            String intStr = arr[0];
            char[] intArr = intStr.toCharArray();
            for (int i = 0; i < intArr.length; i++) {
                int number = Character.getNumericValue(intArr[i]);
                if (number == 0) {
                    if (i < intArr.length - 1 && Character.getNumericValue(intArr[i + 1]) != 0) {
                        intUpper += CN_UPPER_ZERO;
                    } else {
                        if (intArr.length == 1) {
                            intUpper += CN_UPPER_ZERO;
                        }
                    }
                } else {
                    intUpper += CN_UPPER_NUMBER[number] + CN_UPPER_NUMBER_UNIT[intArr.length - 1 - i];
                }
            }
            //小数部分
            if (arr.length == 2) {
                String decStr = arr[1];
                char[] decArr = decStr.toCharArray();
                for (int i = 0; i < decArr.length; i++) {
                    int number = Character.getNumericValue(decArr[i]);
                    if (number == 0) {
                        if (i < decArr.length-1 && Integer.parseInt(decStr.substring(i+1, decStr.length())) > 0) {
                            decUpper += CN_UPPER_NUMBER[number];
                        }
                    } else {
                        decUpper += CN_UPPER_NUMBER[number];
                    }
                }
            }
        } else {
            char[] intArr = num.toCharArray();
            for (int i = 0; i < intArr.length; i++) {
                int number = Character.getNumericValue(intArr[i]);
                if (number == 0) {
                    if (i < intArr.length - 1 && Character.getNumericValue(intArr[i + 1]) != 0) {
                        intUpper += CN_UPPER_ZERO;
                    } else {
                        if (intArr.length == 1) {
                            intUpper += CN_UPPER_ZERO;
                        }
                    }
                } else {
                    intUpper += CN_UPPER_NUMBER[number] + CN_UPPER_NUMBER_UNIT[intArr.length - 1 - i];
                }
            }
        }
        if (StringUtils.isNotBlank(decUpper)) {
            intUpper += CN_UPPER_DOT + decUpper;
        }
        return intUpper;
    }
}