package dpyinjie.easyadapter.list;

import android.content.Context;

import dpyinjie.adapter.BaseListAdapter;
import dpyinjie.adapter.holder.ListHolder;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

/**
 * Created by YinJie on 2016/12/10 10:44.
 */
public class UserListAdapter extends BaseListAdapter<User> {

    public UserListAdapter(Context context) {
        super(context, R.layout.item_user_info);
    }

    @Override
    public void onBindViews(int itemViewType, ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }
}
