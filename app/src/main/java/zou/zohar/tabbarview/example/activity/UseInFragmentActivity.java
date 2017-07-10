package zou.zohar.tabbarview.example.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.example.fragment.MainFragment;

public class UseInFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_in_fragment);

        initContentView();
    }

    private void initContentView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        MainFragment fragment = new MainFragment();
//        TabFragment1 fragment = new TabFragment1();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }
}
