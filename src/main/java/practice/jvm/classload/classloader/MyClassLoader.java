package practice.jvm.classload.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义热部署ClassLoader
 *
 * @title MyClassLoader
 * @Description 自定义热部署ClassLoader
 * @Author donglongcheng01
 * @Date 2019-09-09
 **/
public class MyClassLoader extends ClassLoader {

    private String fileName;

    public MyClassLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class clazz = findLoadedClass(className);
        if (clazz == null) {
            try {
                String classFile = getClassFile(className);
                FileInputStream fis = new FileInputStream(classFile);
                FileChannel fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                WritableByteChannel outChannel = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1) {
                        break;
                    }
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
                fis.close();
                byte[] bytes = baos.toByteArray();
                clazz = defineClass(className, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

    private String getClassFile(String className) {
//        return "/Users/donglongcheng01/Documents/workspace/" +
//                "myPractice/practice/target/classes/practice/jvm/classload/" + className;
        return fileName + className + ".class";
    }

}
