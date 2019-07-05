package com.spring.test;

import java.util.Stack;

public class Test {

    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("(()(()))"));
    }

    public static int scoreOfParentheses(String S) {
        if (S.length() < 2)
            return -1;
        char[] chars = S.toCharArray();
        Stack<Object> stack = new Stack<>();
        String num = null;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    if (num != null) {
                        stack.push(num);
                        num = null;
                    }
                    stack.push('(');
                    break;
                case ')':
                    int sum = 0;
                    int beigin = stack.size();
                    Object temp = stack.pop();
                    while (temp != null && !"(".equals(temp.toString())) {
                        sum += Integer.parseInt(temp.toString());
                        temp = stack.pop();
                    }
                    if ((beigin - stack.size()) == 1) {
                        stack.push(1);
                        break;
                    }
                    stack.push(sum * 2);
                    break;
                default:
                    num += chars[i];
                    break;
            }
        }
        Object temp = stack.pop();
        int sum=0;
        while (temp != null) {
            if (temp.toString().equals("(") || temp.toString().equals(")")) {
                return -1;
            }
            sum += Integer.parseInt(temp.toString());
            if(stack.isEmpty()){
                break;
            }
            temp = stack.pop();
        }
        return sum;
    }
}
