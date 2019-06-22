package dynamicProxy;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getName() throws InterruptedException {
        Thread.sleep(1000L);
        return "donglongcheng";
    }

    @Override
    public int getAge() throws InterruptedException {
        Thread.sleep(200L);
        return 25;
    }
}
