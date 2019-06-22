package ThoughtWorks;

import java.util.regex.Pattern;

/**
 * Created by donglongcheng01 on 2017/9/12.
 */
public class Validator {
    public static boolean validateUID(String uid){
        return true;
    }

    public static boolean validateDate(String date) {
        // 正则表达式
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?" +"[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12" +"35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))");
        return p.matcher(date).matches();
    }

    public static boolean validateTime(String time) {
        // 正则表达式

        String fromTo[] = time.split("~");
        int from = Integer.parseInt(fromTo[0].substring(0, fromTo[0].indexOf(':')));
        int to = Integer.parseInt(fromTo[1].substring(0, fromTo[1].indexOf(':')));
        if (from >= to) {
            return false;
        }
        String end1 = fromTo[0].split(":")[1];
        String end2 = fromTo[1].split(":")[1];
        if (!end1.equals("00") || !end2.equals("00")) {
            return false;
        }
        return true;
    }

    public static boolean validateLocation(String location) {
        if (location.equals("A") || location.equals("B") || location.equals("C") || location.equals("D")) {
            return true;
        } else {
            return true;
        }
    }

    public static boolean validateCancelStatus(String cancel) {
        if (cancel.equals("C")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateBook(String uid, String date, String time, String location) {
        return (validateUID(uid) && validateDate(date) && validateTime(time) && validateLocation(location));
    }

    public static boolean validateCancel(String uid, String date, String time, String location, String cancelStatus) {
        return (validateUID(uid) && validateDate(date) && validateTime(time) && validateLocation(location)
                && validateCancelStatus(cancelStatus));
    }
}
