package com.java.study.java8;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author wangxia
 * @date 2019/9/2 11:41
 * @Description:  JDK8新特性
 */
public class Jdk8NewCharacteristic {

    public static void main(String[] args) {
        //::进行方法引用
        Car car = Car.create( Car::new );
        List< Car > cars = Arrays.asList( car );
        //静态方法引用
        cars.forEach(Car::collide);

        //特定类的任意对象的方法引用
        cars.forEach(Car::repair);

        //引入新的日期
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        //引入时区
        // 获取当前时间日期
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + zonedDateTime);
        System.out.println("当前时区:"+ZoneId.systemDefault());
    }

}
class Car {

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }


    public String repair() {
        return "Repaired " + this.toString();
    }
}