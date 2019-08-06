package com.spring.letcode.stack;

/**
 * @author wangxia
 * @date 2019/7/9 10:37
 * @Description: 中等难度
 */
public class Medium {

    public static void main(String[] args) {
        System.out.println(decodeAtIndex("abc", 3));
    }

    public static String decodeAtIndex(String S,int K){
        char[] chars=S.toCharArray();
        Long size=0L;
        String res="";
        int i;
        //计算出所需字符串长度
        for(i=0;i<chars.length;i++){
            char temp=chars[i];
            if(temp>=49 && temp<=57){
                size*=(temp-'0');
                if(size>K){
                    break;
                }
                continue;
            }
            size++;
            if(size>K){
                break;
            }
        }
        //逆向找出字符
        for(i=i==chars.length?i-1:i;i>=0;i--){
            K%=size;
            char temp=chars[i];
            if(K==0 &&  !(temp>=49 && temp<=57)){
                return chars[i]+"";
            }
            if(temp>=49 && temp<=57){
                size/=(temp-'0');
            }else{
                size--;
            }
        }
        return res;
    }

    public static String removeKdigits(String num, int k) {
        if (num.length() <= k)
            return "0";
        char[] chars = num.toCharArray();
        String newnum = "";
        int index = 0;
        int size = chars.length - k;
        for (int i = 0; i < size; i++) {
            for (int j = index; j <= i + k; j++) {
                if (chars[index] > chars[j]) {
                    index = j;
                }
            }
            if (!(chars[index] == '0' && newnum.equals(""))) {
                newnum += chars[index];
            }
            if (index != chars.length - 1) {
                index = index + 1;
            }
        }
        return newnum==""?"0":newnum;
    }
}

