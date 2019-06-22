package ThoughtWorks;

/**
 * Created by donglongcheng01 on 2017/9/12.
 */
public class OrderKey {
    private String location;
    private String date;
    private int time;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderKey orderKey = (OrderKey) o;

        if (time != orderKey.time) return false;
        if (location != null ? !location.equals(orderKey.location) : orderKey.location != null) return false;
        return date != null ? date.equals(orderKey.date) : orderKey.date == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + time;
        return result;
    }

    public OrderKey(String location, String date, int time) {
        this.location = location;
        this.date = date;
        this.time = time;
    }
}
