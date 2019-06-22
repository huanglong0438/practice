package clone;

/**
 * Created by donglongcheng01 on 2018/2/4.
 */
public class Fucker implements Cloneable {

    int fucked;

    long totalFuckTime;

    double fuckedRate;

    String name;

    FirstVictim victim;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("this mother fucker named %s, has fucked %d people, total fuck %d seconds, " +
                "among the victims women account for %f.\nAnd first victim of this son of the bitch is %s.",
                name, fucked, totalFuckTime, fuckedRate, victim);
    }

    static class FirstVictim {

        String name;

        int age;

        @Override
        public String toString() {
            return String.format("this innocent victim is called %s, %d years old.", name, age);
        }
    }

}
