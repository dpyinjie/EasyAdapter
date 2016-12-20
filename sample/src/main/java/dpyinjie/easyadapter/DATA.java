package dpyinjie.easyadapter;

import java.util.ArrayList;

/**
 * Created by YinJie on 2016/12/16 15:34.
 */
public class DATA {


    public static ArrayList<User> getSingleUserList() {

        ArrayList<User> users = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            users.add(new User("SingleUser " + i, i + 1));
        }
        return users;

    }

    public static ArrayList<User> getMultiUserList() {

        ArrayList<User> users = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            User user = new User("MultiUser " + i, i + 1);
            user.setType(i % 2 == 0 ? User.TYPE_1 : User.TYPE_2);
            users.add(user);
        }
        return users;

    }
}
