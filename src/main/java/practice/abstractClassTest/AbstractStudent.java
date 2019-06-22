package practice.abstractClassTest;

/**
 * Created by donglongcheng01 on 2018/2/14.
 */
public abstract class AbstractStudent implements Student {

    /**
     * Student的方法，在抽象类实现了，就被消化了，子类不需要再实现
     */
    @Override
    public void study() {
        System.out.println("abstract study.");
    }

    /**
     * 然后它又提供给子类需要实现的一个新的方法
     */
    public abstract void abstractStudy();
}
