package ThoughtWorks;

/**
 * Created by donglongcheng01 on 2017/9/12.
 */
public class Transaction {
    private String location;
    private String date;
    private String time;
    private int type; // 0-订金，1-违约金
    private int amount;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Transaction(String location, String date, String time, int type, int amount) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.type = type;
        this.amount = amount;
    }
}
