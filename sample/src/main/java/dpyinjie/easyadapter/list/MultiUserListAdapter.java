package dpyinjie.easyadapter.list;

import android.content.Context;

import dpyinjie.adapter.BaseListAdapter;
import dpyinjie.adapter.holder.ListHolder;
import dpyinjie.adapter.multitype.ListMultiItemSupport;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

/**
 * Created by YinJie on 2016/12/20 15:30.
 */
public class MultiUserListAdapter extends BaseListAdapter<User> {

    private static final int VIEW_TYPE_1 = 0;

    private static final int VIEW_TYPE_2 = 1;

    public MultiUserListAdapter(Context context) {
        super(context);
        setMultiItemSupport(new MultiItem());//设置支持 Multi Item
    }

    @Override
    protected void onBindViews(int itemViewType, ListHolder holder, int position, User user) {
        if (VIEW_TYPE_1 == itemViewType) {
            bindUser1(holder, position, user);
        } else if (VIEW_TYPE_2 == itemViewType) {
            bindUser2(holder, position, user);
        }
    }

    private void bindUser1(ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private void bindUser2(ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }


    private class MultiItem implements ListMultiItemSupport<User> {

        @Override
        public int getItemLayoutId(int viewType) {

            switch (viewType) {

                case VIEW_TYPE_1:
                    return R.layout.item_user_type_1_info;

                case VIEW_TYPE_2:
                    return R.layout.item_user_type_2_info;

            }
            throw new RuntimeException("Unknow viewType  " + viewType);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position, User user) {

            switch (user.getType()) {

                case User.TYPE_1:
                    return VIEW_TYPE_1;

                case User.TYPE_2:
                    return VIEW_TYPE_2;
            }
            throw new RuntimeException("Unknow user type " + user.getType());
        }
    }
}
