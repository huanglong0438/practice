package practice.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * DumpOOM
 * 测试下oom情况下的dump
 * -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=oom.dump
 *
 * @title DumpOOM
 * @Description
 * @Author donglongcheng01
 * @Date 2019-07-08
 **/
public class DumpOOM {

    public static void main(String[] args) {
        List list = Lists.newArrayList();
        for (int i = 0; i < 25; i++) {
            list.add(new byte[1 * 1024 * 1024]);
        }
    }

}
