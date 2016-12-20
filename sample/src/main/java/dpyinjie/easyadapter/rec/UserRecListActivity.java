package dpyinjie.easyadapter.rec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpyinjie.adapter.BaseRecAdapter;
import dpyinjie.adapter.rec.OnRecItemTouchListener;
import dpyinjie.easyadapter.DATA;
import dpyinjie.easyadapter.User;
import dpyinjie.easyadapter.sample.R;

public class UserRecListActivity extends AppCompatActivity {

    @BindView(R.id.rv_user)
    RecyclerView mRvUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_user_rec_list)
    LinearLayout mActivityUserRecList;

    private BaseRecAdapter<User> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_rec_list);
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
                        mAdapter = new SingleUserRecAdapter(UserRecListActivity.this);
                        mRvUser.setAdapter(mAdapter);
                        mAdapter.add(DATA.getSingleUserList());
                        mToolbar.setTitle("Rec Single Item");

                    }
                    return true;

                    case R.id.item_multi://
                    {
                        mAdapter = new MultiUserRecAdapter(UserRecListActivity.this);
                        mRvUser.setAdapter(mAdapter);
                        mAdapter.add(DATA.getMultiUserList());
                        mToolbar.setTitle("Rec Multi Item");
                    }
                    return true;
                }
                return false;
            }
        });

        init();
    }

    private void init() {
        LinearLayoutManager layoutManaer = new LinearLayoutManager(this);
        mRvUser.setLayoutManager(layoutManaer);
        mAdapter = new SingleUserRecAdapter(this);
        mRvUser.setAdapter(mAdapter);
        mAdapter.add(DATA.getSingleUserList());

        mRvUser.addOnItemTouchListener(new OnRecItemTouchListener(mRvUser) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
                User user = mAdapter.getItem(adapterPosition);
                Toast.makeText(UserRecListActivity.this, "点击 name=" + user.getName() + " type=" + user.getType(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
                User user = mAdapter.getItem(adapterPosition);
                Toast.makeText(UserRecListActivity.this, "长按 name=" + user.getName() + " type=" + user.getType(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
