package zou.zohar.tabbarview.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import zou.zohar.tabbarview.R;

/**
 * Created by zohar on 2017/5/21.
 * 抽象的含TabBarView和ViewPager的Activity
 * <p>
 * abstract methods:
 * 1. TabBarView.ItemStyle getItemStyle()   返回tabItemView的展示样式，返回null时为默认的ICON_TEXT
 * 2. List<TabBarView.TabItemView> getTabViews()   提供tabItemView的集合，不可返回null
 * 3. List<Fragment> getFragments()   提供Fragment的集合，不可返回null
 * 4. View getCenterView()   提供一个中间按钮，可返回null
 * 5. int getContentLayout()   提供xml布局文件
 * <p>
 * 注意事项：
 * ① 你的Activity的onCreat()方法中不能调用setContentView(int resLayout)方法，xmlLayout必须由getContentLayout()方法提供
 * ② 由getContentLayout()方法提供的xml布局文件中必须得至少包含一个id为R.id.tabBarView的TabBarView和一个id为R.id.viewpager的ViewPager
 * ③ getTabViews()和getFragments()分别提供的集合的长度必须一致
 */
public abstract class TabWithViewPagerBaseActivity extends AppCompatActivity {

    private TabBarView tabBarView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    /**
     * @return 返回tabItemView的展示样式   ( ICON, TEXT, ICON_TEXT)
     */
    public abstract TabBarView.ItemStyle getItemStyle();

    public abstract List<TabBarView.TabItemView> getTabViews();

    public abstract List<Fragment> getFragments();

    public abstract View getCenterView();

    /**
     * @return 返回xml布局文件
     */
    public abstract
    @LayoutRes
    int getContentLayout();

}
