package practice.beantest;

import com.google.common.collect.Maps;
import com.google.common.primitives.UnsignedLong;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * Main
 *
 * @title Main
 * @Description
 * @Author donglongcheng01
 * @Date 2019-04-23
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        Map<String, PropertyDescriptor> name2Desc = Maps.newHashMap();
        for (PropertyDescriptor descriptor : descriptors) {
            name2Desc.put(descriptor.getName(), descriptor);
        }
        System.out.println(name2Desc);

        Map<String, Object> map = Maps.newHashMap();
        map.put("age", "1");
        map.put("name", "dc");
        map.put("sex", "male");
        map.put("ulong", UnsignedLong.valueOf(555L));

        for (String key : map.keySet()) {
            Object value = map.get(key);
            System.out.println(value.getClass());
            PropertyDescriptor descriptor = name2Desc.get(key);
            if (descriptor.getPropertyType().equals(Long.class)) {
                if (value instanceof UnsignedLong) {
                    value = ((UnsignedLong) value).longValue();
                }
            }
            Method setter = descriptor.getWriteMethod();
            setter.invoke(person, value);
        }

        System.out.println(person);

        System.out.println(person.getClass().getSimpleName().toUpperCase());
    }

    private static <T> void genericTest(Person obj) {
        System.out.println(obj.getClass().getGenericSuperclass());

//        System.out.println(obj.getWhat().getClass());
    }

}
