package com.java.study.java8;

import com.java.study.impl.ImplParamTest;
import com.java.study.impl.ImplParamTypeTest;
import com.java.study.impl.ImplTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wangxia
 * @date 2019/9/2 10:23
 * @Description:  Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中)。  函数式编程
 * 语法格式:
 *    （param）->表达式
 *     (param)->{ 一个或多个表达式 }
 *  特征:
 *  1.可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 *  2.可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 *  3.可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 *  4.可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 *
 *
 *  内置四大核心函数式接口
 * 1.消费型 Consumer void accept(T t)
 * 2.供给型 Supplier T get()
 * 3.函数型 Function<T,R> R apply(T t)
 * 4.断定型 Predicate boolean test(T t)
 */
public class LambdaStudy {


    public static void main(String[] args) {

        //创建一个线程
        //lambda创建匿名内部类
        Thread thread=new Thread(()->{
            System.out.println("lambda表达式尝试创建一个线程");
        });

        //创建接口的时候只能是只有一个接口的情况不能是多个
//        runImpl(()->{
//            public void before(){
//
//            }
//            public void after(){}
//        });
        //测试有参接口创建
        func((x) -> System.out.println("Hello World" + x));

        funcType((Integer x)->{
            System.out.println("Hello World" + x);
            return x;  //无返回值直接去除即可
        });

//        内置四大核心函数式接口
//        1.消费型 Consumer void accept(T t)
//        2.供给型 Supplier T get()
//        3.函数型 Function<T,R> R apply(T t)
//        4.断定型 Predicate boolean test(T t)

//        1.消费型 Consumer void accept(T t)
        Consumer<String> c=(s)->{System.out.println(s);};
        c.accept("consumer hello world");

        //2.供给型 Supplier T get()
        //返回你好
        //Supplier<String> stringSupplier=()->"你好";
        Supplier<String> stringSupplier=()->{return "你好";};
        System.out.println(stringSupplier.get());

//        3.函数型 Function<T,R> R apply(T t)  T--参数类型  R --返回值类型
        Function<String,Integer> function=s->s.length();
        System.out.println(function.apply("hello word"));

        //4.断定型  Predicate<T>  boolean test(T t)
        Predicate<Integer> predicate=integer -> integer>=100;
        System.out.println(predicate.test(99));
    }

    public static void runImpl(ImplTest implTest){
        implTest.before();
        implTest.after();
        System.out.println("调用结束");
    }

    private static void func(ImplParamTest functionInterface) {
        int x = 1;
        functionInterface.test(x);
    }

    private static void funcType(ImplParamTypeTest<Integer> implParamTypeTest) {
        int x = 1;
        System.out.println(implParamTypeTest.TestParam(x));
    }


}
