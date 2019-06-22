package practice.serialize;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by donglongcheng01 on 2017/11/9.
 */
public class Fucker implements Serializable {

    private static final long serialVersionUID = -3933849210443882128L;

//    private FuckerInfo fuckerInfo;

    private int type;

    private Map<String, Long> target;

    private String name;

    private int test;


//    public FuckerInfo getFuckerInfo() {
//        return fuckerInfo;
//    }
//
//    public void setFuckerInfo(FuckerInfo fuckerInfo) {
//        this.fuckerInfo = fuckerInfo;
//    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, Long> getTarget() {
        return target;
    }

    public void setTarget(Map<String, Long> target) {
        this.target = target;
    }
}
