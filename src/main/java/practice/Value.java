package practice;

import java.util.List;
import java.util.Map;

/**
 * Created by donglongcheng01 on 2017/6/13.
 */
public class Value {
    private int version;
    private List<OneStuff> value;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<OneStuff> getValue() {
        return value;
    }

    public void setValue(List<OneStuff> value) {
        this.value = value;
    }

    public static class OneStuff {
        String name;
        boolean selected;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String toString(){
            return name + ":" + selected;
        }
    }

    @Override
    public String toString() {
        return  value.toString();
    }
}
