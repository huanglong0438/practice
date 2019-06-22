package practice.serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 经过测试，序列化是需要看serialVersionUID的，
 * stiuation 1 统一写死了serialVersionUID
 *  situation 1.1 序列化的字段比反序列时的多，反序列用不到多余字段也不会管
 *  situation 1.2 序列化的字段比反序列时的少，反序列会读出默认的零值null
 *  situation 1.3.1 序列化的字段比反序列时的多，在字段中间插入字段（所谓不兼容升级但是没设置新字段），ok的
 *  situation 1.3.2 序列化的字段比反序列时的多，在字段中间插入字段（所谓不兼容升级且设置了新字段)，也tm是ok的
 *
 * situantion 2 没有写死，靠字段来反序列化
 *  situation 2.1 序列化的字段比反序列时的多，反序列化失败
 *  situation 2.2 序列化的字段比反序列时的少，反序列化失败
 *
 * Created by donglongcheng01 on 2017/11/9.
 */
public class Consumer {
    public static void main(String[] args) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file"));
            Fucker fucker = (Fucker) ois.readObject();
            /* 如果序列化之前没有的，跟对象内部成员变量的初始化一样，默认置零值，null, 0
               所以前端ajax请求反序列化之后，如果后端接口有升级，前端传的json没有的项会置零值，
               这样升级了接口如果有以前的请求打过来那就需要判断"零值"来区分是以前的请求还是新的请求
             */
            System.out.println(fucker.getName());
            System.out.println(fucker.getTest());
            System.out.println(fucker.getType());
            System.out.println(fucker.getTarget());
//            System.out.println(fucker.getFuckerInfo());
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
