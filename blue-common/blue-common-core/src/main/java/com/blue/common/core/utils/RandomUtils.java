package com.blue.common.core.utils;


import java.util.Random;

/**
 * 随机数工具类
 *
 * @author wcy
 * 创建时间 14:08 2020/8/27
 */
public class RandomUtils {
    /**
     * 获取指定位数的随机数
     * @param num
     * @return
     */
    public static String getRandom(int num){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < num; i++){
            sb.append(String.valueOf(random.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * 获得 区间随机数
     * @param start
     * @param end
     * @return
     */
    public static Integer getRandom(int start,int end){
        int count=(int)(start+Math.random()*(end-start+1));
        return count;
    }

    public static void main(String[] args) {
        long random = Long.parseLong(getRandom(6));
        System.out.println(random);
    }
}
