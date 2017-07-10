package zou.zohar.tabbarview.example.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.base.TabBarView;
import zou.zohar.tabbarview.base.TabWithViewPagerBaseFragment;

/**
 * Created by Zohar on 2017/7/6.
 * desc:
 */
public class MainFragment extends TabWithViewPagerBaseFragment {
    @Override
    public int getContentLayout() {
        return R.layout.activity_order_list_with_tab;
    }

    @Override
    public TabBarView.ItemStyle getItemStyle() {
        return TabBarView.ItemStyle.TEXT;
    }

    @Override
    public List<TabBarView.TabItemView> getTabViews() {
        List<TabBarView.TabItemView> itemViewList = new ArrayList<>();
        itemViewList.add(new TabBarView.TabItemView(getContext(), "Tab1", R.color.colorAccent, R.color.colorPrimary, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        itemViewList.add(new TabBarView.TabItemView(getContext(), "Tab2", R.color.colorAccent, R.color.colorPrimary, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        itemViewList.add(new TabBarView.TabItemView(getContext(), "Tab3", R.color.colorAccent, R.color.colorPrimary, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        itemViewList.add(new TabBarView.TabItemView(getContext(), "Tab4", R.color.colorAccent, R.color.colorPrimary, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        return itemViewList;
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
}
