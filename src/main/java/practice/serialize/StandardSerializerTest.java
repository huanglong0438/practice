package practice.serialize;

import org.junit.Test;

import java.util.Arrays;

/**
 * StandardSerializerTest
 *
 * @title StandardSerializerTest
 * @Description
 * @Author donglongcheng01
 * @Date 2020-07-07
 **/
public class StandardSerializerTest {

    @Test
    public void testSerialize() {
        StandardSerializer serializer = new StandardSerializer();
        Integer i = 0;
        byte[] bytes = serializer.serialize(i);
        System.out.println(bytes.length);
        for (int j = 0; j < bytes.length; j++) {
            System.out.print(Integer.toHexString(bytes[j]));
        }
        System.out.println();

        System.out.println(Arrays.toString(bytes));
    }

}
