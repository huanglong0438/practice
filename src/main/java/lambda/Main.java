package lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * http://www.importnew.com/16436.html
 *
 * lambda表达式牛逼！！！
 *
 * 所有加了@FunctionalInterface注解的接口都可以变成lambda表达式，例如Runnable构造函数，例如foreach(Consumer)里面的consumer
 *
 * Created by donglongcheng01 on 2018/1/31.
 */
public class Main {

    public static void main(String[] args) {
        testGroupingBy();
    }

    private static void testGroupingBy() {
        List<String> words = Lists.newArrayList("dlc","DLC","Ly");
        Map<Integer, Long> freq = words.stream().collect(Collectors.groupingBy(s -> s.length(), Collectors.counting()));
        System.out.println(freq.entrySet().stream().sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue())).map(Map.Entry::getKey).collect(Collectors.toList()));
    }

    /**
     * stream还可以获得整数的统计对象，这样就不需要自己写一大堆重复的utils工具类辣！
     */
    public static void testIntSummaryStatistics() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    /**
     * stream还有distinct去重的功能，还有更多功能等待着勇士的探索！
     * 例如我就发现了stream还有排序的功能！！！
     */
    public static void lambdaCollectDistinct() {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> squares = numbers.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println("squares = " + squares);
        List<Integer> distinctSquares = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("distinctSquares = " + distinctSquares);
        List<Integer> sortedDistinctSquares = numbers.stream().map(i -> i * i).distinct().sorted().collect(Collectors.toList());
        System.out.println("sortedDistinctSquares = " + sortedDistinctSquares);
    }

    /**
     * stream流化以后不仅能变回list，还可以变成String，还可以变其它东西呢
     */
    public static void lambdaMapCollectAsString() {
        List<String> fuckers = Arrays.asList("dlc", "ys", "yyf", "wxd");
        String bigFuckers = fuckers.stream().map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println("bigFuckers = " + bigFuckers);
    }

    /**
     * 使用lambda表达式过滤str列表，大数据时代根据条件过滤list这种事情太常见了，
     * 原来的java搞过滤什么玩意啊，for循环各种括号if各种括号，太乱了，没什么那么多的逻辑（只有一个逻辑-过滤）却写了那么多行
     * 现在怎么说，反手给你来个lambda，跟我搞这个东西？
     */
    public static void filterStrList() {
        List<String> fuckers = Arrays.asList("dlc", "ys", "yyf", "wxd");

        /**
         * 不使用lambda表达式过滤str列表，代码又臭又长，功能点其实就一个——过滤
         */
        List<String> oldRealFuckers = new ArrayList<>();
        for (String fucker : fuckers) {
            if (fucker.length() > 2) {
                oldRealFuckers.add(fucker);
            }
        }
        System.out.println("oldRealFuckers = " + oldRealFuckers);

        /**
         * 使用了lambda表达式，一行代码搞定，很舒服~
         */
        List<String> realFuckers = fuckers.stream().filter(fucker -> fucker.length() > 2).collect(Collectors.toList());
        System.out.printf("Original fuckers: %s, filtered fuckers: %s", fuckers, realFuckers);
    }

    /**
     * 用lambda表达式搞mapReduce，map执行函数分别计算，reduce把map的零碎的点化零为整
     */
    public static void testMapReduce() {
        List<Integer> wagesBeforeTax = Arrays.asList(8000, 9000, 10000, 16000);
        double total = 0;
        System.out.println("before lambda: ");
        for (Integer wage : wagesBeforeTax) {
            double pay = wage - .12 * wage;
            total += pay;
        }
        System.out.println("total = " + total);

        System.out.println("using lambda: ");
        total = wagesBeforeTax.stream().map((wage) -> wage - .12 * wage).reduce((sum, pay) -> sum + pay).get();
        System.out.println("total = " + total);
    }

    /**
     * 测试lambda表达式的map
     */
    public static void testMap() {
        List<Integer> wagesBeforeTax = Arrays.asList(8000, 9000, 10000, 16000);
        System.out.println("before lambda: ");
        for (Integer wage : wagesBeforeTax) {
            double pay = wage - .12 * wage;
            System.out.println(pay);
        }

        System.out.println("using lambda: ");
        wagesBeforeTax.stream().map((wage) -> wage - .12 * wage).forEach(System.out::println);
    }


    /**
     * 测试Collection接口的stream，可以整成一个流，然后通过函数来filter，最后foreach print
     */
    public static void testStream() {
        Predicate<String> startWithY = (fucker) -> fucker.startsWith("y");
        Predicate<String> lengthThree = (fucker) -> fucker.length() == 3;
        List<String> fuckers = Arrays.asList("dlc", "ys", "yyf", "wxd");
        fuckers.stream().filter(startWithY.and(lengthThree))
                .forEach(s -> System.out.println("the filtered fucker " + s));
    }

    /**
     * Predicate也是@FunctionalInterface注解的接口，加了这个注解就表示可以用lambda表达式创建
     * 但是这个Predicate用lambda创建的时候需要注意函数的参数要有一个而且要符合泛型，函数的返回值只能是boolean
     */
    public static void testPredicate() {
        List<String> fuckers = Arrays.asList("dlc", "ys", "yyf", "wxd");
        System.out.println("fucker start with d :");
        // 这里面的lambda表达式(fucker) -> fucker.startsWith("d")返回的是boolean，就可以作为Predicate（断定）
        filter(fuckers, (fucker) -> fucker.startsWith("d"));
        System.out.println("无脑true");
        filter(fuckers, (fucker) -> true);
        // 编译错误，函数的返回值必须是boolean
//        filter(fuckers, (fucker) -> System.out.println());
    }

    private static void filter(List<String> fuckers, Predicate<String> condition) {
        for (String fucker : fuckers) {
            if (condition.test(fucker)) {
                System.out.println(fucker);
            }
        }
    }

    /**
     * 使用lambda前后的匿名内部类runnable的对比
     */
    public static void anonymusInnerClassToLambda() {
        // before
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'll fuck you to the hell!");
            }
        }).start();
        // after
        new Thread(() -> System.out.println("I'll fuck you to the lambda hell!")).start();
    }

    /**
     * 使用lambda进行迭代
     */
    public static void forEachLambda() {
        // before
        List<String> fuckers = Arrays.asList("dlc", "ys", "yyf", "wxd");
        for (String fucker : fuckers) {
            System.out.println(fucker);
        }

        // lambda
        fuckers.forEach(fucker -> System.out.println(fucker));

        // 方法的引用
        fuckers.forEach(System.out::println);
    }



}
