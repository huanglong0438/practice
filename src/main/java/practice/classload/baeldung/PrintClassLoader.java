package practice.classload.baeldung;

import com.sun.javafx.util.Logging;
import org.junit.Test;

import java.util.ArrayList;

/**
 * PrintClassLoader
 *
 * @title PrintClassLoader
 * @Description
 * @Author donglongcheng01
 * @Date 2021/8/30
 **/
public class PrintClassLoader {

    public void printClassLoaders() throws ClassNotFoundException {

        System.out.println("Classloader of this class:"
                + PrintClassLoader.class.getClassLoader());

        System.out.println("Classloader of Logging:"
                + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());
    }

    @Test
    public void testPrintClassLoaders() throws ClassNotFoundException {
        PrintClassLoader printClassLoader = new PrintClassLoader();
        printClassLoader.printClassLoaders();
    }

}
