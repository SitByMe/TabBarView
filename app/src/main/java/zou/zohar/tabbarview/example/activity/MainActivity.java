package zou.zohar.tabbarview.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zou.zohar.tabbarview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startToUseTabBarViewActivity(View view) {
        startActivity(new Intent(this, UseTabBarViewActivity.class));
    }

    public void startToHomeWithTabActivity(View view) {
        startActivity(new Intent(this, HomeWithTabActivity.class));
    }

    public void startToOrderListWithTabActivity(View view) {
        startActivity(new Intent(this, OrderListWithTabActivity.class));
    }

    public void startToUseInFragmentActivity(View view) {
        startActivity(new Intent(this, UseInFragmentActivity.class));
    }
}
