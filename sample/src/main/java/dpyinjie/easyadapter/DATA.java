package dpyinjie.easyadapter;

import java.util.ArrayList;

/**
 * Created by YinJie on 2016/12/16 15:34.
 */
public class DATA {


    public static ArrayList<User> getUserList() {

        ArrayList<User> users = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            users.add(new User("name " + i, i + 1));
        }
        return users;

    }
}
