package zou.zohar.tabbarview.example.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.base.TabWithViewPagerBaseActivity;
import zou.zohar.tabbarview.example.fragment.TabFragment1;
import zou.zohar.tabbarview.example.fragment.TabFragment2;
import zou.zohar.tabbarview.example.fragment.TabFragment3;
import zou.zohar.tabbarview.example.fragment.TabFragment4;
import zou.zohar.tabbarview.base.TabBarView;

/**
 * Created by zohar on 2017/5/21.
 * resLayout由getContentLayout()提供，不在onCreate()方法中调用setContentView()方法。
 */
public class HomeWithTabActivity extends TabWithViewPagerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public TabBarView.ItemStyle getItemStyle() {
        return TabBarView.ItemStyle.ICON_TEXT;
    }

    @Override
    public List<TabBarView.TabItemView> getTabViews() {
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

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        fragments.add(new TabFragment4());
        return fragments;
    }

    @Override
    public View getCenterView() {
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

    @Override
    public int getContentLayout() {
        return R.layout.activity_home_with_tab;
    }
}
