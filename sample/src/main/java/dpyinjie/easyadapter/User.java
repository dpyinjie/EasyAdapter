package dpyinjie.easyadapter;

/**
 * Created by YinJie on 2016/12/10 10:43.
 */
public class User {


    public static final int TYPE_1 = 1;

    public static final int TYPE_2 = 2;

    private int type = TYPE_1;

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(int type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
