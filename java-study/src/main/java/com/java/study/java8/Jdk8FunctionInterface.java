package com.java.study.java8;

/**
 * @author wangxia
 * @date 2019/9/2 14:02
 * @Description: 定义一个函数式接口
 */
public class Jdk8FunctionInterface {

    public static void main(String[] args) {
        TestMessage testMessage=msg->System.out.println(msg);
        testMessage.send("测试发送");
    }



}

/**
 * 函数式接口
 * 1.只能有一个未被实现的方法
 * 2.可以有多个默认方法
 *
 * 加不加 @FunctionalInterface 对于接口是不是函数式接口没有影响，
 * 该注解只是提醒编译器去检查该接口是否仅包含一个抽象方法
 */
@FunctionalInterface
interface TestMessage{

    public void send(String msg);

    /**
     * 默认方法
     * @param testMessage
     */
    default void before(TestMessage testMessage){
        testMessage.send("在发放之前执行");
    }

    /**
     * 静态方法
     */
    static void testStatic(){
        System.out.println("测试静态方法");
    }

}
