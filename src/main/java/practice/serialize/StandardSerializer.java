package practice.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * StandardSerializer
 *
 * @title StandardSerializer
 * @Description
 * @Author donglongcheng01
 * @Date 2020-07-07
 **/
public class StandardSerializer implements Serializable {

    public byte[] serialize(Object obj) {

        if (obj == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException("object serialize exception, with object " + obj, e);
        } finally {
            if (null != oos)
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException("object serialize exception, with object " + obj, e);
                }
        }
        return baos.toByteArray();
    }

    public Serializable deserialize(byte[] source) {

        if (source == null)
            return null;
        Serializable obj;
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(source);
            ois = new ObjectInputStream(bis);
            obj = (Serializable) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("object serialize exception ", e);
        } finally {
            if (null != ois)
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException("object serialize exception ", e);
                }
        }
        return obj;
    }

}
