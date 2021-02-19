package practice.mockito;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;


/**
 * mockito，一个优秀的mock框架
 *
 * @title TasteMockito
 * @Description
 * @Author donglongcheng01
 * @Date 2020-03-02
 **/
public class TasteMockito {

    public static void main(String[] args) {

        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = spy(LinkedList.class);

        System.out.println(mockedList);

//        // stubbing appears before the actual execution
//        when(mockedList.get(0)).thenReturn("first");
//        when(mockedList.get(999)).thenReturn("fuck");
//
//        // the following prints "first"
//        System.out.println(mockedList.get(0));
//
//        // the following prints "null" because get(999) was not stubbed
//        System.out.println(mockedList.get(999));
        // branch commit 1
        // branch commit 2

    }

}
