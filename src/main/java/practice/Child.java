package practice;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import sun.reflect.Reflection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 *
 * fuck you
 *
 * Created by donglongcheng01 on 2017/9/22.
 */
public class Child extends Parent{
    public int m = 1;

    public Long l;

    public void doAction1() {
        System.out.println(m);
    }

    public Child() {
        System.out.println("child");
    }

    public static void main(String[] args) {
        for (Field field : Child.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }
}
