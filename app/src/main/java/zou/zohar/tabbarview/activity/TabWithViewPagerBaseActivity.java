package zou.zohar.tabbarview.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.widge.TabBarView;

public abstract class TabWithViewPagerBaseActivity extends AppCompatActivity {

    private TabBarView tabBarView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());

        tabBarView = (TabBarView) findViewById(R.id.tabBarView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        });

        tabBarView.setItemStyle(getItemStyle());
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
    }

    /**
     * @return return the style of the tabItemView   ( ICON, TEXT, ICON_TEXT)
     */
    public abstract TabBarView.ItemStyle getItemStyle();

    public abstract List<TabBarView.TabItemView> getTabViews();

    public abstract List<Fragment> getFragments();

    public abstract View getCenterView();

    /**
     * @return return a layout res with R.id.tabBarViewBottom and R.id.viewPager
     */
    public abstract
    @LayoutRes
    int getContentLayout();

}
