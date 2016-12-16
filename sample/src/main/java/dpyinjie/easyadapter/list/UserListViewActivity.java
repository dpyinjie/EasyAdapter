package dpyinjie.easyadapter.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dpyinjie.easyadapter.DATA;
import dpyinjie.easyadapter.sample.R;

public class UserListViewActivity extends AppCompatActivity {

    @BindView(R.id.lv_user)
    ListView mLvUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private UserListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
        ButterKnife.bind(this);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        init();
    }

    private void init() {
        mAdapter = new UserListAdapter(this);
        mLvUser.setAdapter(mAdapter);

        mAdapter.add(DATA.getUserList());
    }


}
