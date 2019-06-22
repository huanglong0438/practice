package zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * zk测试类
 *
 * Created by donglongcheng01 on 2018/1/23.
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String connStr = "127.0.0.1:2181";
        int sessionTimeout = 600000;
        boolean running = true;
        boolean zkStatus = false;
        while (running) {
            try {
                if (!zkStatus) {
                    ZooKeeper zk = new ZooKeeper(connStr, sessionTimeout, new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            log.info("get event from zk: " + watchedEvent);
                        }
                    });
                    // 得到节点的值
                    byte[] bytes = zk.getData("/", false, null);
                    log.info(new String(bytes));

                    // 得到节点的子节点
                    List<String> children = zk.getChildren("/", false);
                    System.out.println(children);
                    zkStatus = true;

                    existWatcher(zk);

                }

            } catch (Exception e) {
                log.error("cannot connect to the fuck zk!", e);
                break;
            }
        }

    }

    private static void existWatcher(ZooKeeper zk) throws Exception {
        // 注册一个路径的watcher
        DataChangeLisenter listener = new DataChangeLisenter();
        Stat outp = zk.exists("/donglongcheng01", listener);
        System.out.println("exists Stat is " + outp);
    }

    private static class DataChangeLisenter implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("something is fucking happened！" + watchedEvent);
        }
    }

}
