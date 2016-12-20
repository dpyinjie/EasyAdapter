package dpyinjie.easyadapter.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpyinjie.adapter.BaseListAdapter;
import dpyinjie.easyadapter.DATA;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

public class UserListViewActivity extends AppCompatActivity {

    @BindView(R.id.lv_user)
    ListView mLvUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private BaseListAdapter<User> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
        ButterKnife.bind(this);

        mToolbar.inflateMenu(R.menu.menu_rec_users);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item_single://
                    {
                        mAdapter = new SingleUserListAdapter(UserListViewActivity.this);
                        mLvUser.setAdapter(mAdapter);
                        mAdapter.add(DATA.getSingleUserList());
                        mToolbar.setTitle("List Single Item");

                    }
                    return true;

                    case R.id.item_multi://
                    {
                        mAdapter = new MultiUserListAdapter(UserListViewActivity.this);
                        mLvUser.setAdapter(mAdapter);
                        mAdapter.add(DATA.getMultiUserList());
                        mToolbar.setTitle("List Multi Item");
                    }
                    return true;
                }
                return false;
            }
        });


        init();
    }

    private void init() {

        mAdapter = new SingleUserListAdapter(this);
        mLvUser.setAdapter(mAdapter);
        mAdapter.add(DATA.getSingleUserList());


        mLvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getItemAtPosition(position);
                Toast.makeText(UserListViewActivity.this, "点击 name=" + user.getName() + " type=" + user.getType(), Toast.LENGTH_SHORT).show();
            }
        });

        mLvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getItemAtPosition(position);
                Toast.makeText(UserListViewActivity.this, "长按 name=" + user.getName() + " type=" + user.getType(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
