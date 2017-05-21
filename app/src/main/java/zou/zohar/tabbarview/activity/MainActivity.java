package zou.zohar.tabbarview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zou.zohar.tabbarview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startToUseTabBarViewActivity(View view) {
        startActivity(new Intent(this, UseTabBarViewActivity.class));
    }

    public void startToHomeWithTabActivity(View view) {
        startActivity(new Intent(this, HomeWithTabActivity.class));
    }
}
