package com.cesar.core.java8.methodreference;

import lombok.Data;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Data
class Car {

    private String name;
//    @FunctionalInterface
//    public interface Supplier<T> {
//        T get();
//    }

    public Car() {
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println(this.toString()+"Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
//        Runnable aNew = Car::new;

//        Supplier<Car> aNew = new Supplier<Car>() {
//            @Override
//            public Car get() {
//                return new Car();
//            }
//        };

        Supplier<Car> aNew = ()->new Car();

//        Supplier<Car> aNew = Car::new;

        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        Car car = Car.create(aNew);
        car.setName("car");
        Car car1 = Car.create(Car::new);
        car1.setName("car1");
        Car car2 = Car.create(Car::new);
        car2.setName("car2");
        Car car3 = new Car();
        car3.setName("car3");
        List<Car> cars = Arrays.asList(car, car1, car2, car3);
        System.out.println("===================构造器引用========================");
        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);
//        for (Car car4 : cars) {
//            Car.collide(car4);
//        }
        System.out.println("===================静态方法引用========================");
        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);
        Consumer<Car> repair = Car::repair;
//        for (Car car4 : cars) {
//            car4.repair();
//        }
        System.out.println("==============特定类的任意对象的方法引用================");
        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Car police = Car.create(Car::new);
        police.setName("police");
        Consumer<Car> follow = police::follow;
//        Car police = new Car();
        cars.forEach(police::follow);
        System.out.println("===================特定对象的方法引用===================");

    }
}