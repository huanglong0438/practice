package practice.jmxtest;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * JMXdemo
 *
 * @title JMXdemo
 * @Description
 * @Author donglongcheng01
 * @Date 2019-03-16
 **/
public class JMXdemo {

    public static void main(String[] args) {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("jmx:type=User");
            User bean = new User();
            server.registerMBean(bean, objectName);
            System.out.println("jmx started!!!");
            while (true) {
                System.out.println(bean.getName() + ":" + bean.getPasswd());
                Thread.sleep(3000);
            }

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
