package dpyinjie.easyadapter.rec;

import android.content.Context;

import dpyinjie.adapter.BaseRecAdapter;
import dpyinjie.adapter.holder.RecHolder;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

/**
 * Created by YinJie on 2016/12/16 15:39.
 */
public class UserRecAdapter extends BaseRecAdapter<User> {


    public UserRecAdapter(Context context) {
        super(context, R.layout.item_user_info);
    }

    @Override
    public void onBindViews(int itemViewType, RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }
}
