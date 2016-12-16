package dpyinjie.easyadapter.rec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpyinjie.adapter.rec.OnRecItemTouchListener;
import dpyinjie.easyadapter.DATA;
import dpyinjie.easyadapter.sample.R;

public class UserRecListActivity extends AppCompatActivity {

    @BindView(R.id.rv_user)
    RecyclerView mRvUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_user_rec_list)
    LinearLayout mActivityUserRecList;


    private UserRecAdapter mAdapter;

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

                    case R.id.item_linear:
                        LinearLayoutManager linearManaer = new LinearLayoutManager(UserRecListActivity.this);
                        mRvUser.setLayoutManager(linearManaer);
                        return true;

                    case R.id.item_grid:
                        GridLayoutManager gridManager = new GridLayoutManager(UserRecListActivity.this, 4);
                        mRvUser.setLayoutManager(gridManager);
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
        mAdapter = new UserRecAdapter(this);
        mRvUser.setAdapter(mAdapter);
        mAdapter.add(DATA.getUserList());

        mRvUser.addOnItemTouchListener(new OnRecItemTouchListener(mRvUser) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
                Toast.makeText(UserRecListActivity.this, "点击 " + mAdapter.getItem(adapterPosition).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
                Toast.makeText(UserRecListActivity.this, "长按 " + mAdapter.getItem(adapterPosition).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
