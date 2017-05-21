package zou.zohar.tabbarview.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

import zou.zohar.tabbarview.R;
import zou.zohar.tabbarview.widge.TabBarView;

public class OrderListWithTabActivity extends TabWithViewPagerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<TabBarView.TabItemView> getTabViews() {
        return null;
    }

    @Override
    public List<Fragment> getFragments() {
        return null;
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
