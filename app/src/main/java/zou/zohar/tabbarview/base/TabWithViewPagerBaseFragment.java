package zou.zohar.tabbarview.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zou.zohar.tabbarview.R;

public abstract class TabWithViewPagerBaseFragment extends Fragment {

    private TabBarView tabBarView;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getContentLayout(), container, false);
        tabBarView = (TabBarView) view.findViewById(R.id.tabBarView);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        });

        tabBarView.setItemStyle(getItemStyle() != null ? getItemStyle() : TabBarView.ItemStyle.ICON_TEXT);
        tabBarView.setTabItemViews(getTabViews(), getCenterView());

        tabBarView.setOnCheckedChangeListener(new TabBarView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TabBarView tabBarView, int checkedPos) {
                viewPager.setCurrentItem(checkedPos);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabBarView.check(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    /**
     * @return 返回xml布局文件
     */
    public abstract
    @LayoutRes
    int getContentLayout();

    /**
     * @return 返回tabItemView的展示样式   ( ICON, TEXT, ICON_TEXT)
     */
    public abstract TabBarView.ItemStyle getItemStyle();

    public abstract List<TabBarView.TabItemView> getTabViews();

    public abstract List<Fragment> getFragments();

    public abstract View getCenterView();
}
