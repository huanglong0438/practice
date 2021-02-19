package practice.synchronizedTest;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public enum Color {

    RED,BLUE,GREEN,BLACK,FUCK("fuck");

    @GuardedBy("this")
    private String name;

    Color() {
    }

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
