package ThoughtWorks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by donglongcheng01 on 2017/9/12.
 */
public class Main {

    public static final String INVALID_BOOKING = "the booking is invalid!";

    public static final String BOOKING_ACCEPT = "the booking is accepted!";

    public static Map<OrderKey, String> orderTable;

    public static Map<String, LinkedHashMap<TransactionKey, Double>> transationTable;

    public static void main(String[] args) {

        orderTable = new LinkedHashMap<>();

        transationTable = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String booking = scanner.nextLine();
            if (booking.length() > 0) {
                String[] bookings = booking.split(" ");
                if (bookings.length == 4) {
                    // booking
                    Result result = book(bookings);
                    System.out.println(result.getStatus().toString() + ": " + result.getReason());
                } else if (bookings.length == 5) {
                    // cancel
                    Result result = cancel(bookings);
                    System.out.println(result.getStatus().toString() + ": " + result.getReason());
                } else {
                    if (booking.equals("look")) {
                        for (OrderKey key : orderTable.keySet()) {
                            System.out.println(key.getLocation() + " " + key.getDate() + " " + key.getTime() + " "
                                    + orderTable.get(key));
                        }
                    } else if (booking.equals("total")) {
                        for (String location : transationTable.keySet()) {
                            System.out.println(location + ":");
                            Map<TransactionKey, Double> transactions = transationTable.get(location);
                            for (TransactionKey key : transactions.keySet()) {
                                System.out.println(key.getDate() + " " + key.getTime() + " " + key.getType()
                                        + " " + transactions.get(key));
                            }
                        }
                    } else {
                        System.out.println("Error: the booking is invalid!");
                    }
                }
            } else {
                System.out.println("收入汇总");
                System.out.println("---");
                double sum = 0;
                for (String location : transationTable.keySet()) {
                    double total = 0;
                    System.out.println("场地：" + location + ":");
                    Map<TransactionKey, Double> transactions = transationTable.get(location);
                    for (TransactionKey key : transactions.keySet()) {
                        System.out.println(key.getDate() + " " + key.getTime() + " " +
                                (key.getType() == 0 ? " " : "违约金")
                                + transactions.get(key) + "元");
                        total += transactions.get(key);
                    }
                    System.out.println("小计：" + total + "元");
                    sum += total;
                    System.out.println();
                }
                System.out.println("---");
                System.out.println("总计：" + sum + "元");
                return;
            }
        }
    }

    public static Result book(String bookings[]){
        Result result = new Result();
        if (Validator.validateBook(bookings[0], bookings[1], bookings[2], bookings[3])) {
            String uid = bookings[0];
            String date = bookings[1];
            int times[] = resolveTimes(bookings[2]);
            String location = bookings[3];
            // location date time1->time2 ordered by uid
            for (int i = 0; i < times.length; i++) {
                OrderKey orderKey = new OrderKey(location, date, times[i]);
                if (orderTable.get(orderKey) != null) {
                    result.setStatus(Status.ERROR);
                    result.setReason(INVALID_BOOKING);
                    return result;
                }
            }
            for (int i = 0; i < times.length; i++) {
                OrderKey orderKey = new OrderKey(location, date, times[i]);
                orderTable.put(orderKey, uid);
                double price = getPriceByDateAndTime(date, times[i]);
                LinkedHashMap<TransactionKey, Double> transactions = transationTable.get(location);
                if (transactions == null) {
                    transactions = new LinkedHashMap<>();
                    transactions.put(new TransactionKey(date, bookings[2], 0), price);
                    transationTable.put(location, transactions);
                } else {
                    TransactionKey tk = new TransactionKey(date, bookings[2], 0);
                    if (transactions.get(tk) == null) {
                        transactions.put(tk, price);
                    } else {
                        transactions.put(tk, transactions.get(tk) + price);
                    }
                }
            }
            result.setStatus(Status.SUCCESS);
            result.setReason(BOOKING_ACCEPT);
        } else {
            result.setStatus(Status.ERROR);
            result.setReason(INVALID_BOOKING);
        }
        return result;
    }


    private static boolean isWeekday(String date){
        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date bdate = format1.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(bdate);
            if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
            {
                return false;
            }
            else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static int[] resolveTimes(String time) {
        String[] fromTo = time.split("~");
        String from = fromTo[0].substring(0, fromTo[0].indexOf(':'));
        String to = fromTo[1].substring(0, fromTo[1].indexOf(':'));
        int fromInt = Integer.parseInt(from);
        int toInt = Integer.parseInt(to);
        int[] times = new int[toInt - fromInt + 1];
        for (int i = 0; i < times.length; i++) {
            times[i] = i + fromInt;
        }
        return times;
    }

    public static Result cancel(String bookings[]){
        Result result = new Result();
        if (Validator.validateCancel(bookings[0], bookings[1], bookings[2], bookings[3], bookings[4])) {
            String uid = bookings[0];
            String date = bookings[1];
            int times[] = resolveTimes(bookings[2]);
            String location = bookings[3];
            // 删掉预定记录
            for (int i = 0; i < times.length; i++) {
                OrderKey key = new OrderKey(location, date, times[i]);
                if (orderTable.get(key) != null) {
                    orderTable.remove(key);
                }
            }
            // 删掉交易记录，
            for (String loc : transationTable.keySet()) {
                LinkedHashMap<TransactionKey, Double> transactions = transationTable.get(loc);
                for (TransactionKey key : transactions.keySet()) {
                    if (key.getDate().equals(date) && key.getTime().equals(bookings[2])
                            && key.getType() == 0 && loc.equals(location)) {
                        transactions.remove(key);
                    }
                }
            }
            double rate;
            if (isWeekday(date)) {
                rate = 0.5;
            } else {
                rate = 0.25;
            }
            // 新插入一条违约金记录
            for (int i = 0; i < times.length; i++) {
                LinkedHashMap<TransactionKey, Double> transactions = transationTable.get(location);
                if (transactions == null) {
                    transactions = new LinkedHashMap<>();
                    transactions.put(new TransactionKey(date, bookings[2], 1),
                            getPriceByDateAndTime(date, times[i]) * rate);

                } else {
                    TransactionKey tk = new TransactionKey(date, bookings[2], 1);
                    if (transactions.get(tk) == null) {
                        transactions.put(tk, getPriceByDateAndTime(date, times[i]) * rate);
                    } else {
                        transactions.put(tk, transactions.get(tk) + getPriceByDateAndTime(date, times[i]) * rate);
                    }
                }

            }

            result.setStatus(Status.SUCCESS);
            result.setReason(BOOKING_ACCEPT);

        } else {
            result.setStatus(Status.ERROR);
            result.setReason(INVALID_BOOKING);
        }
        return result;
    }

    public static double getPriceByDateAndTime(String date, int time) {
        // get price by date and time
        if (isWeekday(date)) {
            if (time >= 9 && time < 12) {
                return 30;
            } else if (time >= 12 && time < 18) {
                return 50;
            } else if (time >= 18 && time < 20) {
                return 80;
            } else {
                return 60;
            }
        } else {
            if (time >= 9 && time < 12) {
                return 40;
            } else if (time >= 12 && time < 18) {
                return 50;
            } else {
                return 60;
            }
        }
    }
}
