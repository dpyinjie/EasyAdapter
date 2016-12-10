package dpyinjie.easyadapter;

import android.content.Context;

import dpyinjie.adapter.BaseListAdapter;
import dpyinjie.adapter.holder.ListHolder;

/**
 * Created by YinJie on 2016/12/10 10:44.
 */
public class UserListAdapter extends BaseListAdapter<User> {

    public UserListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViews(int itemViewType, ListHolder holder, int position, User user) {

    }
}
