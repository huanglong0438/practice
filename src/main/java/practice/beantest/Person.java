package practice.beantest;

import com.google.common.base.Joiner;
import com.google.common.primitives.UnsignedLong;

import java.util.StringJoiner;

/**
 * Person
 *
 * @title Person
 * @Description
 * @Author donglongcheng01
 * @Date 2019-04-23
 **/
public class Person<T> {

    private String name;

    private String sex;

    private String age;

    private Long ulong;

    private T what;

    public T getWhat() {
        return what;
    }

    public void setWhat(T what) {
        this.what = what;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getUlong() {
        return ulong;
    }

    public void setUlong(Long ulong) {
        this.ulong = ulong;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        return joiner.add(name).add(sex).add(age).add(ulong+"").toString();
    }
}
