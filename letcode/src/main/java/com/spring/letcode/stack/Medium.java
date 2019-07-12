package com.spring.letcode.stack;

import java.util.*;

/**
 * @author wangxia
 * @date 2019/7/9 10:37
 * @Description: 中等难度
 */
public class Medium {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{-2,1,2,-2,1,2}));
    }
    public static boolean find132pattern(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(nums.length-i<2){
                return false;
            }
            int[] temp=new int[3];
            temp[0]=nums[i];
            int index=0;
            for(int j=i+1;j<nums.length;j++){
                if(index==0 && nums[j]>temp[0]){
                    index++;
                    temp[index]=nums[j];
                    continue;
                }
                if(nums[j]>temp[0] && nums[j]<temp[1])
                    return true;
                if(nums[j]>temp[1])
                    temp[1]=nums[j];
            }
        }
        return false;
    }


    public static List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        //调用回溯算法
        backTrack(n, nums_lst, output, 0);
        return output;
    }

    /**
     *
     * @param len     字符串长度
     * @param numList
     * @param output
     * @param first
     */
    public static void backTrack(int len,List<Integer> numList,List<List<Integer>> output,int first){
        //判断是否终止
        if(first==len){
            output.add(new ArrayList<>(numList));
        }
        for(int i=first;i<len;i++){
            //交换
            Collections.swap(numList,first,i); //交换
            //下一个
            backTrack(len,numList,output,first+1);
            //还原数据
            Collections.swap(numList,first,i); //交换
        }
    }

}

