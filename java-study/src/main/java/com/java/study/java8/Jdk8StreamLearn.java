package com.java.study.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wangxia
 * @date 2019/9/2 14:25
 * @Description:  stream流学习  是一个来自数据源的元素队列并支持聚合操作
 * 特征:
 * 1.元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * 2.数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * 3.聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *
 * 4.和以前的Collection操作不同， Stream操作还有两个基础的特征：
 *
 *      1.Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 *      2.内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现
 */
public class Jdk8StreamLearn {

    public static void main(String[] args) {
        Function<List<String>,Integer> repeactAndGetSize=list->{
            System.out.println("列表内容如下:");
            list.forEach(v->{System.out.print(v+"\t");});
            System.out.println();
            return list.size();
        };
        //message 总长度5
        List<String> messages= Arrays.asList(new String[]{"123","456","789","",null,"596"});
       System.out.println("大小："+repeactAndGetSize.apply(messages));
       System.out.println("----------------------filter--------------------");
       //1.使用filter过滤掉空字符串  过滤掉不符合条件的
        messages=messages.stream().filter(s->{System.out.println("进行过滤");return  !(s==null || "".equals(s));}).
                sorted((v1,v2)->{System.out.println("进行排序");return Integer.parseInt(v1)-Integer.parseInt(v2);}).
                collect(Collectors.toList());
        System.out.println("大小："+repeactAndGetSize.apply(messages));

        System.out.println("----------------------map--------------------");
        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素转化未了int
        //可以对集合原有元素进行转换
        List<Integer> newMsg=messages.stream().mapToInt(v->Integer.parseInt(v)).boxed().collect(Collectors.toList());
        newMsg.forEach(v->{System.out.print(v+"\t");});
        System.out.println();

        System.out.println("----------------------limit--------------------");
        //limit  限制返回的最大条数
        messages=messages.stream().limit(10).collect(Collectors.toList());
        System.out.println("大小："+repeactAndGetSize.apply(messages));

        System.out.println("----------------------sort--------------------");
        //sort 排序
        //默认排序规则
        List<Integer> sortMsg=newMsg.stream().sorted().collect(Collectors.toList());
        sortMsg.forEach(v->{System.out.print(v+"\t");});
        System.out.println();

        //自定义排序规则
        List<String> sortMsg2=messages.stream().sorted((v1,v2)->Integer.parseInt(v1)-Integer.parseInt(v2)).collect(Collectors.toList());
        System.out.println("大小："+repeactAndGetSize.apply(sortMsg2));
        System.out.println("----------------------max  min--------------------");
        //max 获取最大值
        int max=newMsg.stream().max((v1,v2)->v1-v2).get();
        int min=newMsg.stream().min((v1,v2)->v1-v2).get();
        System.out.println("max: "+max+" min: "+min);

        //parallelStream其实就是一个并行执行的流.它通过默认的ForkJoinPool,可能提高你的多线程任务的速度.
        System.out.println("----------------------parallel--------------------");
        Long count=messages.stream().parallel().count();
        System.out.println("数据总量:"+count);
        //reduce 根据一定的规则将Stream中的元素进行计算后返回一个唯一的值
        System.out.println("----------------------reduce--------------------");
        String identify=messages.stream().reduce((String s,String s2)->{return s+s2;}).get();
        System.out.println("identify:"+identify);

        //是否都符合
        boolean allMatch=messages.stream().allMatch(s->s!=null && !s.equals(""));
        //是否存在符合的
        boolean anyMatch=messages.stream().anyMatch(s->s!=null && !s.equals(""));
        //是否都不符合
        boolean noneMatch=messages.stream().noneMatch(s->s!=null && !s.equals(""));
    }

}
