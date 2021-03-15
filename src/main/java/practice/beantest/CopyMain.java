package practice.beantest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * CopyMain
 *
 * @title CopyMain
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/12
 **/
public class CopyMain {

    public static void main(String[] args) {
        Out1 out1 = new Out1();
        out1.setTitle("title1");
        {
            Person person = new Person();
            person.setName("ssfs");
            person.setSex("male");
            person.setUlong(123L);
            out1.setPerson(person);
        }
        Out2 out2 = new Out2();
        BeanUtils.copyProperties(out1, out2);
        System.out.println(JSON.toJSONString(out2));
    }

}
