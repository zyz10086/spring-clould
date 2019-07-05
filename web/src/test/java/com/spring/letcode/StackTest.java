package com.spring.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackTest {

    //0  48  9 57  2,1,5
    public static void main(String[] args) {
        NestedInteger ni=deserialize("[-1]");
        System.out.println("测试结束");
    }

    //验证二叉树的前序序列化

    /**
     * 可以理解成是节点数问题，叶子节点数总是比非叶子节点数多一。根据前序遍历过程，先遍历的非叶子节点数总是比叶子节点数多。
     * <p>
     * 也可以理解为出度入度相等问题：我命名有问题，根结点的入度为0出度为2，其他非叶子结点的入度为1出度为2，叶子节点入度为1出度为0。因为根节点多出来一个出度，所以初始化度为1，一个非叶子节点时度+1，加入一个空节点（叶子节点）时度-1，如果度为0，即达到出度入度相等，已经形成一颗二叉树。
     *
     * @param preorder
     * @return
     */
    public static boolean isValidSerialization(String preorder) {
        int degree = 1;
        for (String temp : preorder.split(",")) {
            if (degree == 0)
                return false;
            if (temp.equals("#"))
                degree--;
            else
                degree++;
        }
        return degree == 0;
    }

    public static int[] nextLargerNodes2(ListNode head) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        //转化为数组
        while (head!=null){
            arrayList.add(head.val);
            head=head.next;
        }
        int[] result=new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++){
            int  temp=arrayList.get(i);
            for(int j=i;j<arrayList.size();j++){
                if(temp<arrayList.get(j)){
                    result[i]=arrayList.get(j);
                    break;
                }
            }
        }
        return result;
    }

    //"[123,[456,[789]]]"
    //123
    public static NestedInteger deserialize(String s) {
        if(s==null || s=="")
            return null;
        NestedInteger result=null;
        Stack<String> values=new Stack<>();
        if(!s.startsWith("[")){
            result=new NestedInteger(Integer.parseInt(s));
        }else{
            String temp="";
            for(char c:s.toCharArray()){
                if(c=='['){
                    values.push("[");
                    continue;
                }
                if(c==','){
                    values.push(temp);
                    temp="";
                    continue;
                }
                if(c==']'){
                    if(temp!=""){
                        values.push(temp);
                        temp="";
                    }
                    String tempstr=null;
                    List<NestedInteger> datas=new ArrayList<>();
                    while(!values.isEmpty()){
                        tempstr=values.pop();
                        if("[".equals(tempstr))
                            break;
                        if(tempstr==""){
                            datas.add(new NestedInteger());
                            continue;
                        }
                        datas.add(new NestedInteger(Integer.parseInt(tempstr)));
                    }
                    NestedInteger tt=new NestedInteger();
                    if(datas.size()>1){
                        tt=new NestedInteger();
                        tt.getList().addAll(datas);
                    }else if(datas.size()==1){
                        if(datas.get(0).getInteger()!=null){
                            tt.setInteger(datas.get(0).getInteger());
                        }
                    }
                    if(result!=null){
                        tt.getList().add(result);
                    }else{
                        result=tt;
                    }
                    result=tt;
                    continue;
                }
                temp+=c;
            }
        }
        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class NestedInteger {
    Integer value;
    List<NestedInteger> nestedIntegers=new ArrayList<>();
    // Constructor initializes an empty nested list.
    public NestedInteger(){}

    // Constructor initializes a single integer.
    public NestedInteger(int value){
        this.value=value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger(){
        return value!=null && nestedIntegers==null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value){
        this.value=value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni){
        if(this.nestedIntegers==null){
            this.nestedIntegers=new ArrayList<>();
        }
        this.nestedIntegers.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){
        return this.nestedIntegers;
    }
}
