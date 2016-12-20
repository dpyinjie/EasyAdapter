package dpyinjie.easyadapter.rec;

import android.content.Context;

import dpyinjie.adapter.BaseRecAdapter;
import dpyinjie.adapter.holder.RecHolder;
import dpyinjie.adapter.multitype.RecMultiItemSupport;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

/**
 * RecyclerView Multi Item 示例
 *
 * @author Created by YinJie on 2016/12/20 15:20.
 */
public class MultiUserRecAdapter extends BaseRecAdapter<User> {


    private static final int VIEW_TYPE_1 = 0;

    private static final int VIEW_TYPE_2 = 1;


    public MultiUserRecAdapter(Context context) {
        super(context);
        setMultiItemSupport(new MultiItem());//设置支持 Multi Item
    }

    @Override
    public void onBindViews(int itemViewType, RecHolder holder, int position, User user) {
        if (VIEW_TYPE_1 == itemViewType) {
            bindUser1(holder, position, user);
        } else if (VIEW_TYPE_2 == itemViewType) {
            bindUser2(holder, position, user);
        }
    }

    private void bindUser1(RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private void bindUser2(RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private class MultiItem implements RecMultiItemSupport<User> {

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
