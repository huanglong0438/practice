package practice.synchronizedTest;

public enum Color {

    RED,BLUE,GREEN,BLACK,FUCK("fuck");

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
