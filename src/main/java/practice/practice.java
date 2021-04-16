package practice;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;


public class practice {
	
	protected int test = 5;

	public static final Integer AAA = 3;

    static {
/*        if (AAA == 3) {
            throw new RuntimeException("DataItem exist duplicate name [" + "fuckyou" + "] !!!");
        }*/
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(
            10, new CustomizableThreadFactory("cycTemplate-cas-modPlan-async-pool-"));

    private static final RateLimiter rateLimiter = RateLimiter.create(10);

    private static final ObjectMapper objectMapper = new ObjectMapper() {
        {
            this.enable(new MapperFeature[]{MapperFeature.SORT_PROPERTIES_ALPHABETICALLY});
            this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS"));
            this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    };

    static class ListNode {
        int val;
        Object obj;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private final static String IP_PORT_PATTERN = "\\d+.\\d+.\\d+.\\d+:\\d+";

    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> gatherKeywordIndex2SplitKeywordIndex = Maps.newHashMap();
        gatherKeywordIndex2SplitKeywordIndex.put(1, 3);
        Map<String, String> variable = Maps.newHashMap();
        variable.put("gatherKeywordIndex2SplitKeywordIndex",
                objectMapper.writeValueAsString(gatherKeywordIndex2SplitKeywordIndex));
        Map<Integer, Integer> out = objectMapper.readValue(variable.get("gatherKeywordIndex2SplitKeywordIndex"),
                new TypeReference<HashMap<Integer, Integer>>(){});
        System.out.println(out);
    }

    private static int getNumberDecimalDigits(Double number) {
        String moneyStr = String.format("%f", number);
        System.out.println(moneyStr);
        String[] num = moneyStr.split("\\.");
        if (num.length != 2 || num[1].length() == 0) {
            return 0;
        }
        int digitNumCount = num[1].length();
        int i = num[1].length() - 1;
        char ch;
        while (i >= 0 && (ch = num[1].charAt(i--)) == '0') {
            digitNumCount--;
        }
        return digitNumCount;
    }

    private static void calcMixWmatch() {
        int wmatch = 15;
        int wctrl = 0;
        int mixWmatch = (wmatch - 15) + (((wctrl + 1) % 4) * (((wmatch - 15) / 16) % 3));
        System.out.println(mixWmatch);
    }

    private static void urlDecode(String url) {
        System.out.println(url.replaceAll("&", "%26"));
    }

    private static boolean containsMultipleCoreWord(String keyword, String coreWord) {
        int index = keyword.indexOf(coreWord);
        return -1 != keyword.indexOf(coreWord, index + coreWord.length());
    }

    private static void testJackson() {

    }

    private static int testFinal() {
        try {
            return 1;
        } finally {
            return 0;
        }
    }

    private static void testLambda() {
        UrlPromotionPage page1 = new UrlPromotionPage();
        page1.setOnlineUrl("www.google.com");
        page1.setOcpcTransTypeList(Lists.newArrayList(1, 2, 3));
        UrlPromotionPage page2 = new UrlPromotionPage();
        page2.setOnlineUrl("wwww");
        List<UrlPromotionPage> urlPromotionPages = Lists.newArrayList(page1, page2);
        Map<String, List<Integer>> url2TransTypes = urlPromotionPages.stream()
                .filter(page -> page.getOcpcTransTypeList() != null)
                .collect(Collectors.toMap(UrlPromotionPage::getOnlineUrl, UrlPromotionPage::getOcpcTransTypeList,
                        (v1, v2) -> v2));
        System.out.println(url2TransTypes);
    }

    private static List<Long> strs2List(List<String> strIds) {
        Assert.notEmpty(strIds);
        List<Long> result = Lists.newArrayList();
        int maxLength = 0;
        List<List<Long>> allIds = Lists.newArrayList();
        for (String strId : strIds) {
            List<Long> list = str2List(strId);
            maxLength = Math.max(maxLength, list.size());
            allIds.add(list);
        }
        for (int i = 0; i < maxLength; i++) {
            for (List<Long> ids : allIds) {
                if (i >= ids.size()) {
                    continue;
                }
                result.add(ids.get(i));
            }
        }
        return result;
    }

    private static List<Long> str2List(String strList) {
        try {
            if (org.apache.commons.lang3.StringUtils.isEmpty(strList)) {
                return Lists.newArrayList();
            }
            List<Long> result = Lists.newArrayList();
            for (String str : strList.split(",")) {
                Long id = Long.parseLong(str);
                result.add(id);
            }
            return result;
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }


    public static void testNullEqual() {
        String a = null;
        String b = "b";
        System.out.println(a.equals(b));
    }

    public static void testXilaAlpha() {
        String alphas = "ΑΝΣ";
        System.out.println(alphas.toLowerCase());
    }

    public static void testSort() {
        int[] nums = new int[]{6, 3, 0, 1, 5, 2};
        quickSort(nums, 0, nums.length);
        for (int n : nums) {
            System.out.println(n);
        }
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            // 递归出口
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid);
        quickSort(nums, start + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int pioneer = start + 1;
        int divider = start + 1;
        while (pioneer < end) {
            if (nums[pioneer] < pivot) {
                // 如果遇到小的，把小的放到小区
                int temp = nums[divider];
                nums[divider] = nums[pioneer];
                nums[pioneer] = temp;
                // 然后扩充小区
                divider++;
            }
            // 如果遇到大的，先锋继续
            pioneer++;
        }
//        nums[] todo
        return -1;
    }

    /**
     * pb测试，pb会压缩掉List中为null的元素
     */
/*    private static void testProtostuff() {
        Schema<KeywordEstimatedDataByBidRequest> schema =
                RuntimeSchema.getSchema(KeywordEstimatedDataByBidRequest.class);
        KeywordEstimatedDataByBidRequest request = new KeywordEstimatedDataByBidRequest();
        request.setSearchRegions(Lists.newArrayList(1L, null, 2L)); // 这个null在转pb的时候会被丢掉
        byte[] protostuff = ProtostuffUtils.toByteArray(request, schema, LinkedBuffer.allocate(500));
        KeywordEstimatedDataByBidRequest out = new KeywordEstimatedDataByBidRequest();
        ProtostuffUtils.mergeFrom(protostuff, out, schema);
        System.out.println(JSON.toJSONString(out));
    }*/

    /**
     * 在增强型for循环中删除list元素会导致ConcurrentModificationException报错
     * 要换成迭代器
     */
    private static void concurrentModificationExeception() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(2, 4, 6, 8);
        for (Integer i1 : list1) {
            for (Integer i2 : list2) {
                if (i1.equals(i2)) {
                    list1.remove(i1);
                }
            }
        }
        System.out.println(list1);
        System.out.println(list2);
    }

    private static void nonConcurrentModificationExeception() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(2, 4, 6, 8);
        for (Iterator<Integer> itr1 = list1.iterator(); itr1.hasNext(); ) {
            Integer i1 = itr1.next();
            for (Integer i2 : list2) {
                if (i1.equals(i2)) {
                    itr1.remove();
                }
            }
        }
        System.out.println(list1);
        System.out.println(list2);
    }

    /**
     * 浮点数无法精确的表示0.1、0.01...因此计算结果会有问题，尽量用int,long,bigdecimal解决
     */
    private static void floatCompute() {
        System.out.println(1.03 - 0.42);
    }

    private static void reencode() throws Exception {
        String chn = "中文";
        Charset utf8 = Charset.forName("utf-8");
        Charset gbk = Charset.forName("GBK");
        ByteBuffer byteBuffer = ByteBuffer.wrap("鲜花".getBytes(StandardCharsets.UTF_8));
        ByteBuffer byteBuffer1 = gbk.encode(gbk.decode(byteBuffer));

        byte[] bytes = "鲜花".getBytes("utf-8");
        String newValue = new String(bytes, "gbk");
        byte[] bytes1 = newValue.getBytes("gbk");
        String newValue1 = new String(bytes1, "utf-8");
        System.out.println(newValue1);
    }

    private static void tryWithResource() {
        // try(括号里必须是一个AutoCloseable，否则会编译不过，语法糖)
        try (InputStream in = new FileInputStream("src");
             OutputStream out = new FileOutputStream("dst")) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*testSupplier(() -> "fuck world");
    testSupplier(String::new);*/
    private static void testSupplier(Supplier<String> factory) {
        System.out.println(factory.get());
    }

    private static void testResources() throws Exception {
        practice practice = new practice();
        Enumeration<URL> urls = practice.getClass().getClassLoader().getResources("");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }
    }

    private static void testAssert() {
        int a = 1;
        int b = 2;
//        Assert.isTrue(a == b);
        assert a == b;
    }

    private static void testCompare() {
        List<Integer> list = Lists.newArrayList(2, 4, 6, 7, 1, 2, 6, 7);
        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 - o2 > 0) {
                    return 1;
                } else if (o1 - o2 < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(list);

        Collections.sort(list, (i1, i2) -> i1.compareTo(i2) * -1);
        System.out.println(list);
    }

    private static void fileChannelTest() throws Exception {
        File file = new File("test");
        FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
        fc.position(fc.size());
        System.out.println(fc.position());
    }

    private static void testException() throws Exception {
        try {
            throw new UnsupportedOperationException("inner.");
        } catch (Exception e) {
            throw new Exception("outer : " + e.getMessage());
        }
    }

    private static void deleteList() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        for (Integer num : nums) {
            if (num == 4) {
                nums.remove(num);
            }
        }
        System.out.println(nums);

    }

    private static String joinAccountIds(List<Long> selectedAccountIds) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(selectedAccountIds)) {
            for (int i = 0; i < selectedAccountIds.size(); i++) {
                if (i != selectedAccountIds.size() - 1) {
                    sb.append(selectedAccountIds.get(i)).append(",");
                } else {
                    sb.append(selectedAccountIds.get(i));
                }
            }
        }
        return sb.toString();
    }

    public static void testSpringThreadPools() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(3);
        executor.initialize();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        executor.submit(runnable);
        System.out.println("starting queue");
        // 这之后会入队列
        executor.submit(runnable);
        executor.submit(runnable);
        executor.submit(runnable);
        System.out.println("staring core -> max");
        // 这之后会扩张core线程
        executor.submit(runnable);
        executor.submit(runnable);
        executor.submit(runnable);
        System.out.println("time to error.");
        // 这之后就会报错了
        executor.submit(runnable);
        executor.shutdown();
    }

    public static void testThreadPools(int type) {
        ExecutorService executor = null;
        try {
            // 创建一个容量只有3的fixedThreadPool
            switch (type) {
                case 0:
                    // 固定大小是3的线程池，超了以后会队列
                    executor = Executors.newFixedThreadPool(3);
                    break;
                    // ThreadPoolExecutor的原则上是超过core就入队列，队列不行了就扩张到max，max不够用就抛异常
                case 1:
                    // core是1、max是3的线程池，超了1直接分配新线程（SynchronousQueue这个老哥不等），
                    // 超了3直接抛异常（SynchronousQueue这个老哥不会等的）
                    executor = new ThreadPoolExecutor(1, 3,
                            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                    break;
                case 2:
                    // core是1，max是3（没有卵用，因为队列的大小是无限的），超了1就队列了，然后一直等这个1
                    executor = new ThreadPoolExecutor(1, 3,
                            60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
                    break;
                case 3:
                    // 用了LinkedBlockingQueue，max还是没卵用，超过core就入队列
                    executor = new ThreadPoolExecutor(1, 3,
                            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
                    break;
                case 4:
                    // 用了LinkedBlockingQueue(只能排一个)，超过core(1)入队列，超过队列(1+1)，开新线程
                    executor = new ThreadPoolExecutor(1, 3,
                            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
                default:
                    executor = Executors.newFixedThreadPool(3);
                    break;
            }
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.submit(runnable);
            executor.submit(runnable);
            executor.submit(runnable);
            // 这之后就超了，理论上会入队列，5秒后才会执行
            executor.submit(runnable);
            executor.submit(runnable);
            executor.submit(runnable);
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }


    // 获取某一天过去几天的日期
    public static Date getPastDate(Date date, int past) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        // 测试使用
        // calendar.setTime(format.parse("2018-04-02"));
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date result = calendar.getTime();
        result = format.parse(format.format(result));
        return result;
    }

    public static void hashMapOrdertest() {
        Map<String, String> map = Maps.newHashMap();
        map.put("yi", "1");
        map.put("er", "4");
        map.put("san", "9");

        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }

        List<String> list = Lists.newArrayList(map.keySet());
        for (String key : list) {
            System.out.println(key);
        }

        for (String value : map.values()) {
            System.out.println(value);
        }
    }

    private static Method[] techGetMethods(Class<?> objClass) {
        Method[] methods = objClass.getMethods();
        return methods;
    }

    public static void multimapTest() {
        Multimap<String, Integer> testMultimap = ArrayListMultimap.create();
        testMultimap.put("one", 1);
        testMultimap.put("two", 2);
        testMultimap.put("three", 3);
        testMultimap.put("four", 4);
        testMultimap.put("five", 5);
        testMultimap.put("liu", 6);
        testMultimap.put("liu", 6);
        for (String key : testMultimap.keySet()) {
            System.out.println(key.hashCode() + ": " + key);
        }
        System.out.println("==========hashset=========");
        Set<String> hashSet = Sets.newHashSet();
        hashSet.add("one");
        hashSet.add("two");
        hashSet.add("three");
        hashSet.add("four");
        hashSet.add("five");
        hashSet.add("liu");
        for (String str : hashSet) {
            System.out.println(str.hashCode() + ": " + str);
        }
    }

    public static void encodingCheck() throws IOException {
        FileInputStream fis = new FileInputStream("encoding");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "gbk"));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println("哈喽".equals(str));
        }
    }

    public static void calendarTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }

    public static void roundUp() {
        Double d = 1.666;
        d = (double) Math.round(d * 100) / 100;
        System.out.println(d);
    }

    /**
     * map的比较是通过比较每个键值对
     */
    public static void testMapCompare() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "donglongcheng");
        map1.put("sex", "male");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "donglongcheng");
        map2.put("sex", "female");
//        System.out.println(map1.equals(map2));

//        for (Map.Entry<String, String> entry : map1.entrySet()) {
//            if (map2.containsValue(entry.getValue())) {
//                System.out.println("ok");
//            }
//        }

        System.out.println("before: " + map1);
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            if (entry.getKey().equals("name")) {
                map1.remove("name");
            }
        }
        System.out.println("after: " + map1);
    }


    /**
     * String的trim不会修改String本身，需要通过结果返回
     */
    public static void testStringOperate() {
        String name = "name ";
        name.trim();
        System.out.println(name);
    }

    /**
     * Java的所有方法操作都是传递右值（也就是值，而不是指针，所以方法内部指向别的东西后，不会返回）
     */
    public static void testMap() {
	    Wrap wrap = new Wrap();
        wrap.content = new HashMap<>();
        wrap.content.put("1", "1");
        testModMap(wrap);
        System.out.println(wrap.content);
    }

    public static void testModMap(Wrap wrap) {
        wrap.content = new HashMap<>();
    }

    /**
     * enum对大小写敏感，注意
     */
    public static void testEnumUpper() {
//        System.out.println(COLOR.valueOf("red"));
//        System.out.println(COLOR.valueOf("RED"));
        System.out.println(COLOR.valueOf("rEd"));
    }

    public static enum COLOR {
	    RED,
        GREEN
    }

    /**
     * 用装箱类的时候==不能信啊，java没有操作符重载的说法，==比较的就是指针引用，
     * Integer在内部维护了一个缓冲池，Integer.valueOf()方法是Integer的默认装箱方式，
     * 其中在[-128,127]区间是直接指向了缓冲池，因此这个区间的==比较是可以比较值的
     * 而在这个区间外，就是直接比较指针了。
     *
     * 所以Integer比较的时候还是用equals或者用Integer.intValue()来进行==比较
     *
     * https://www.cnblogs.com/zhangminghui/p/4487710.html
     */
    public static void compareInteger() {
	    Integer a = 1;
	    Integer b = 1;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        Integer c = 123456;
        Integer d = 123456;
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }

    public static void replaceAllMapEntry() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 4);
        map.replaceAll((a, b) -> a);
        System.out.println(map);

    }

    public static void compareMapEntry() {
        Comparator<Map.Entry<Integer, Integer>> comparator = Map.Entry.comparingByKey();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 4);
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
        int res = comparator.compare(iterator.next(), iterator.next());
        System.out.println(res);
    }


    public static void testSubString() {
        String test = "0123456789";
        System.out.println(test.substring(1));
    }

    public static void testRegularExpression() {
        Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\s*\\{?\\s*([\\._0-9a-zA-Z]+)\\s*\\}?");
        String str = "${donglongcheng }";
        Matcher matcher = VARIABLE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            // 这个group就是用来找()里面的东西的，group(0)是全部，group(1)是第一个括号的
            // 这个正则表达式只有一个()，所以只能匹配到{}里面trim过的东西
            String value = matcher.group(1);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    public static void subtractCollections() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(3);
        set1.add(5);
        set1.add(6);
        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(4);
        set2.add(6);
        set2.removeAll(set1);
        System.out.println(set2);
    }

    public static void timeTranslate() {
	    Date date = new Date(1511798400000L);
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println(sdf.format(date));
        System.out.println(sdf.format(new Date(1511971200000L)));
    }

    public static void testAutoboxing() {
	    Integer a = 10002;
	    Integer b = 10001;
        System.out.println(a.intValue() < b.intValue());
    }

    public static void testListToArray() {
        List<Integer> binds = new ArrayList<>();
        Integer[] binds2 = binds.toArray(new Integer[] {});
        System.out.println(binds2.length);
    }

    public static void throwExceptionTest(int num) {
        System.out.println("before");
        if (num < 0) {
            throw new RuntimeException();
        }
        System.out.println("after");
    }

    public static void getYesterday() {
	    Calendar calendar = Calendar.getInstance();
        System.out.println(Calendar.DATE);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(yesterday));
        calendar.add(Calendar.DATE, 2);
        Date tomorow = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(tomorow));
    }

    public static void dateToString() {
        Date date = new Date();
        System.out.println(date.toString());
        Date date1 = new Date();
        System.out.println(date1.toString());
        System.out.println(date.toString().compareTo(date1.toString()));
    }

    public static void removeLastChar() {
	    String url = "http://www.google.com/";
        url = url.replaceFirst("^(?i)(http://|https://){0,1}", "");
        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }
        System.out.println(url);
    }

    public static void checkUrlFinal() {
	    String url = "www.right.com/1/2/";
        url = url.replaceFirst("^(?i)(http://|https://){0,1}", "");
        String regex = "([^/]*/){3,}..*"; // 三级目录
        System.out.println(!url.matches(regex));
    }

    public static void removeUrlPrex() {
	    String browseurl = "HTTP://00.auto.sohu.com/details";
        browseurl = browseurl.replaceFirst("^(?i)(http://|https://){0,1}", "");
        System.out.println(browseurl);
    }

    public static void checkUrlLevel() {
        String regex = "[^/]*/[^/]*/..*"; // 二级目录
        String browseurl = "00.auto.sohu.com/details/";
        System.out.println(browseurl.matches(regex));
    }

    public static void testEnum() {
        System.out.println(ModelField.FUCKYou.name());
    }

	public practice(){
		
	}

	public static void stringCompare() {
        String s1 = "阿西吧";
        String s2 = "卧槽";
        String ss1 = "axiba";
        String ss2 = "wocao";
        System.out.println(ss1.compareTo(ss2));
    }

	public static void setEqual() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 2, 1));
        System.out.println(set1.equals(set2));
    }

	public static void stringJoinTest() {
        String s = StringUtils.join(new String[]{"123","","789"},",");
        System.out.println(s);
    }

	public static void longIntTest() {
	    Long num1 = 0L;
        System.out.println(num1.equals("0"));
    }

	public static void randTest2() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random(123);
            System.out.println(Math.abs(random.nextInt()));
        }
    }

	public static void randTest() {
//        System.out.println(new Random(123).nextInt());
        new Thread(){
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("thread1.txt");
                    for (int i = 0; i < 100; i++) {
                        int logId = Math.abs(new Random(System.currentTimeMillis()).nextInt());
                        fos.write((logId+"\n").getBytes());
                    }
                    System.out.println("done");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            FileOutputStream fos1 = null;
            @Override
            public void run() {
                try {
                    fos1 = new FileOutputStream("thread2.txt");
                    for (int i = 0; i < 100; i++) {
                        int logId = Math.abs(new Random(System.currentTimeMillis()).nextInt());
                        fos1.write((logId+"\n").getBytes());
                    }
                    System.out.println("done");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public String practice(int a){
		return null;
	}

    public static void testChineseLen() {
        String template = "苟";
        try {
            System.out.println(template.getBytes("utf-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void fastFailTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        for (int i : list) {
            if (i == 3) {
                list.remove(3);
            }
        }
    }

    public static void notFastFailTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(3)) {
                iter.remove();
            }
        }
    }

	public static void getInpeoplesUnicode() {
		String s = "\\u5efa\\u6750\\u5bb6\\u5c45\\u002c\\u91d1\\u878d\\u8d22\\u7ecf\\u002c\\u6559\\u80b2\\u57f9\\u8bad\\u002c\\u5bb6\\u7535\\u6570\\u7801\\u002c\\u516c\\u76ca\\u002c\\u9910\\u996e\\u7f8e\\u98df\\u002c\\u623f\\u4ea7\\u002c\\u65c5\\u6e38\\u51fa\\u884c\\u002c\\u6c7d\\u8f66\\u002c\\u82b1\\u9e1f\\u840c\\u5ba0\\u002c\\u6bcd\\u5a74\\u4eb2\\u5b50\\u002c\\u975e\\u6c7d\\u8f66\\u7c7b\\u673a\\u52a8\\u8f66\\u002c\\u6c42\\u804c\\u521b\\u4e1a\\u002c\\u661f\\u5ea7\\u8fd0\\u52bf\\u002c\\u4f11\\u95f2\\u7231\\u597d\\u002c\\u5a5a\\u604b\\u4ea4\\u53cb\\u002c\\u4e66\\u7c4d\\u9605\\u8bfb\\u002c\\u8f6f\\u4ef6\\u5e94\\u7528\\u002c\\u5546\\u52a1\\u670d\\u52a1\\u002c\\u8d44\\u8baf\\u002c\\u751f\\u6d3b\\u670d\\u52a1\\u002c\\u4f53\\u80b2\\u5065\\u8eab\\u002c\\u7f51\\u7edc\\u8d2d\\u7269\\u002c\\u533b\\u7597\\u5065\\u5eb7\\u002c\\u4e2a\\u62a4\\u7f8e\\u5bb9\\u002c\\u5f71\\u89c6\\u97f3\\u4e50\\u002c\\u6e38\\u620f\\u002c\\u670d\\u9970\\u978b\\u5305";
		String ss[] = s.split("\\\\u002c");
		for (String str: ss
				) {
			System.out.print(str.replace("\\","") + ",");
		}
	}


	public static void testPrintSet() {
		Set<Long> set = new HashSet<>();
		set.add(630152L);
		set.add(6789863L);
		System.out.println(set);
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}


	public static void testJSON() {
        String value = "{\"haha\":250,\"version\":1,\"value\":[{\"name\":\"accountSummary\",\"selected\":true},{\"name\":\"dataChart\",\"selected\":true},{\"name\":\"consumptionTopN\",\"selected\":true},{\"name\":\"maxIncreaseN\",\"selected\":true},{\"name\":\"maxDecreaseN\",\"selected\":true},{\"name\":\"lastWeekKeywordShow\",\"selected\":true},{\"name\":\"ideaImage\",\"selected\":true},{\"name\":\"appendIdeaConsumption\",\"selected\":true},{\"name\":\"consumptionRank\",\"selected\":true},{\"name\":\"hotspot\",\"selected\":true},{\"name\":\"effectOnDevice\",\"selected\":true},{\"name\":\"effectOnPlatform\",\"selected\":true}]}";
//        String value = "{\"version\":1,\"value\":[{\"name\":\"haha\",\"selected\":true}]}";
        Value value2 = JSON.parseObject(value, Value.class);
        System.out.println(value2);
    }

    public static void testJSON2() {
	    Value value = new Value();
	    value.setVersion(1);
        Value.OneStuff stuff = new Value.OneStuff();
        stuff.setName("gagag");
        stuff.setSelected(true);
        List<Value.OneStuff> list = Arrays.asList(new Value.OneStuff[]{stuff});
        value.setValue(list);
        System.out.println(JSON.toJSONString(value));
        String svalue = JSON.toJSONString(value);
        Value value1 = JSON.parseObject(svalue, Value.class);
        System.out.println(value1);
    }

	public static void testJSON3() {
		String value = "{\"delWordList\":[68572043419]}";
		Map<String, List<Object>> map = JSON.parseObject(value, Map.class);
		for (Object obj : map.get("delWordList")) {
			Long num = 0L;
			if (obj instanceof Integer) {
				num = ((Integer)obj).longValue();
			} else if (obj instanceof Long) {
				num = (Long) obj;
			}
			System.out.println(num);
		}

	}

	public static void testJSON4() {
		String value2 = "[123,68572043419]";
		Set<Long> set = JSON.parseObject(value2, Set.class);
		for (Long num : set) {
			System.out.println(num);
		}
	}

	public static void fuk() {
		List<String> d = Arrays.asList("a","b");
//		List<String> e = new ArrayList("a","b");
	}

	public static void testString(){
		String str1 = "hello";
		String str2 = "he"+ new String("llo");
		System.out.print(str1 == str2);
	}
	
	public static void testDoubleAdd(){
//		Double d1 = 6.59;
//		Double d2 = 6.88;
		double d1 = 6.59;
		double d2 = 6.88;
		System.out.println(d1 + d2);
	}
	
	public static void testSplit(){
		String uidAndTypeOrign = "123-1";
		Set<String> set = new LinkedHashSet<String>(asList(uidAndTypeOrign.split(",")));
		for(String uidAndType : set){
			String part[] = uidAndType.split("-");
			Long uid = Long.parseLong(part[0]);
			Integer type = Integer.parseInt(part[1]);
			System.out.println(uid);
			System.out.println(type);
		}

	}
	
	public static int Fibonacci(int n){
		if(n == 1)
            return 1;
        if(n == 2)
            return 1;
        else
            return Fibonacci(n-1)+Fibonacci(n-2);
    }
	
	public static int fibMemo(int n, int[] memo){
		if(memo[n] > 0){
			return memo[n];
		}
        else{
        	int f1;
        	if(memo[n-1] > 0)
        		f1 = memo[n-1];
        	else
        		f1 = fibMemo(n-1, memo);
        	int f2;
        	if(memo[n-2] > 0)
        		f2 = memo[n-2];
        	else
        		f2 = fibMemo(n-2, memo);
            return f1+f2;
        }
	}
	
	public static int JumpFloorII(int target) {
        int count = 0;
        if(target == 0)
        	return 1;
        if(target == 1)
            return 1;
        if(target == 2)
            return 2;
        else{
            for(int i = target-1;i >= 0; i--){
                count += JumpFloorII(i);
            }
            return count;
        }
    }
	
	public static int RectCover(int target) {
		//target=1 return 1; target,return 2; target=3,target=2 + 1,target=1 + 2
		        if(target == 1)
		            return 1;
		        if(target == 2)
		            return 2;
		        else{
		            return RectCover(target-2)+RectCover(target-1)+3;
		        }
	}

}
