package com.cesar.core.java8.stream;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName Stream
 * @Description TODO
 * @Author Cesar
 * @Data 2019/6/28
 **/
public class Stream {
    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

//        Random random = new Random();
//        IntStream ints = random.ints().limit(10);
//        ConfigurationCustomizer configurationCustomizer = System.out::println;
//        ints.forEach(a-> System.out.println(a));

        /**
         * map
         */
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        squaresList.forEach(System.out::println);

        /**
         * filter
         */
//        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        // 获取空字符串的数量
//        long count = strings.stream().filter(string -> string.isEmpty()).count();
//        System.out.println(count);

        /**
         * limit
         */
//        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        // 获取空字符串的数量
//        strings.stream().filter(string -> !string.isEmpty()).limit(2).forEach(System.out::println);

        /**
         * sorted
         */
//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);

        /**
         * Collectors
         */
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
