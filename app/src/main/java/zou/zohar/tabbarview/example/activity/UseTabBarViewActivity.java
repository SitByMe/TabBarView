package zou.zohar.tabbarview.example.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.base.TabBarView;

public class UseTabBarViewActivity extends AppCompatActivity {

    private TabBarView tabBarViewTop;
    private TabBarView tabBarViewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_tab_bar_view);

        tabBarViewTop = (TabBarView) findViewById(R.id.tabBarViewTop);
        tabBarViewBottom = (TabBarView) findViewById(R.id.tabBarViewBottom);

        tabBarViewTop.setTabItemViews(getTabItemViews());
        tabBarViewTop.setOnCheckedChangeListener(new TabBarView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TabBarView tabBarView, int checkedPos) {
                Snackbar.make(tabBarView, tabBarView.getCheckedTabItemView().title + "   top", Snackbar.LENGTH_SHORT).show();
            }
        });

        tabBarViewBottom.setTabItemViews(getTabItemViews(), getImageView());
        tabBarViewBottom.setOnCheckedChangeListener(new TabBarView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TabBarView tabBarView, int checkedPos) {
                Snackbar.make(tabBarView, tabBarView.getCheckedTabItemView().title + "   bottom", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private List<TabBarView.TabItemView> getTabItemViews() {
        List<TabBarView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new TabBarView.TabItemView(this, "标题1", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "标题2", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "标题3", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "标题4", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        return tabItemViews;
    }

    private View getImageView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "center view click", Snackbar.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }
}
