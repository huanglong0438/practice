package practice.jmxtest;

/**
 * UserMBean
 *
 * @title UserMBean
 * @Description
 * @Author donglongcheng01
 * @Date 2019-03-16
 **/
public interface UserMBean {

    String getName();

    void SetName(String name);

    String getPasswd();

    void SetPasswd(String pwd);

    int add(int x, int y);

}
