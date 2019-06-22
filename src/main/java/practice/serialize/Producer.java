package practice.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by donglongcheng01 on 2017/11/9.
 */
public class Producer {
    public static void main(String[] args) {
        Fucker fucker = new Fucker();
        fucker.setType(1);
        Map<String, Long> target = new HashMap<>();
        target.put("First Blood", 1L);
        fucker.setTarget(target);
        FuckerInfo info = new FuckerInfo();
        info.setFuckName("donglongcheng");
        info.setFuckWho("snow");
//        fucker.setFuckerInfo(info);
        fucker.setName("dlc");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file"));
            oos.writeObject(fucker);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
