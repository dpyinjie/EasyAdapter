package dpyinjie.easyadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dpyinjie.easyadapter.list.UserListViewActivity;
import dpyinjie.easyadapter.rec.UserRecListActivity;
import dpyinjie.easyadapter.sample.R;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_listview_sample)
    Button mBtnListviewSample;
    @BindView(R.id.btn_recyslerview_sample)
    Button mBtnRecyslerviewSample;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_listview_sample, R.id.btn_recyslerview_sample})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_listview_sample: //
            {
                Intent intent = new Intent(this, UserListViewActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_recyslerview_sample://
            {
                Intent intent = new Intent(this, UserRecListActivity.class);
                startActivity(intent);
            }
            break;

        }
    }

}
