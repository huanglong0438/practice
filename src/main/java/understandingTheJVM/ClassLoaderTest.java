package understandingTheJVM;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by donglongcheng01 on 2018/5/7.
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        // 自定义的ClassLoader
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        // 自定义的ClassLoader载入的ClassLoaderTest和系统应用程序类加载器加载的不一样
        Object obj = myLoader.loadClass("understandingTheJVM.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof understandingTheJVM.ClassLoaderTest);
    }

}
