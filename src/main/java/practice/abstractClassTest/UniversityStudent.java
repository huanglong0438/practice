package practice.abstractClassTest;

/**
 * Created by donglongcheng01 on 2018/2/14.
 */
public class UniversityStudent extends AbstractStudent implements Student {

    /**
     * Student接口需要我们实现的方法，是AbstractStudent透传过来的
     */
    @Override
    public void fuck() {

    }

    /**
     * AbstractStudent抽象类的抽象方法，是AbstractStudent要求我们实现的
     */
    @Override
    public void abstractStudy() {

    }

    /**
     * Student还有一个方法是study方法，在AbstractStudent类就实现了，被消化了
     */

}
