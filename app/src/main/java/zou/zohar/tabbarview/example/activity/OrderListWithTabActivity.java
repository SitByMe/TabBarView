package zou.zohar.tabbarview.example.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

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
public class OrderListWithTabActivity extends TabWithViewPagerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public TabBarView.ItemStyle getItemStyle() {
        return TabBarView.ItemStyle.TEXT;
    }

    @Override
    public List<TabBarView.TabItemView> getTabViews() {
        List<TabBarView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new TabBarView.TabItemView(this, "已完成", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "未付款", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "待收货", R.color.colorPrimary,
                R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new TabBarView.TabItemView(this, "待评价", R.color.colorPrimary,
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
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_order_list_with_tab;
    }
}
