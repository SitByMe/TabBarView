package zou.zohar.tabbarview.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zou.zohar.tabbarview.R;

/**
 * Created by zohar on 2017/5/21.
 * desc:自定义的TabBarView（仿RadioGroup）
 * <p>
 * public methods:
 * 1. void setItemStyle(@NonNull ItemStyle itemStyle)   设置tabItemView的展示样式
 * 2. void setTabItemViews(@NonNull List<TabItemView> tabItemViews)   设置tabItemView集合
 * 3. void setTabItemViews(@NonNull List<TabItemView> tabItemViewList, View centerView)   设置tabItemView集合和centerView
 * 4. void check(int position)   切换选中的tabItemView
 * 5. TabItemView getCheckedTabItemView()   返回当前选中的tabItemView
 * 6. void setOnCheckedChangeListener(OnCheckedChangeListener listener)   设置切换tabItemView时的回调接口
 * <p>
 * 注意事项：
 * ① setTabItemViews() 方法不可重复调用
 * ② 如果需要有一个子超出父布局位置限制的centerView的话，需要在TabBarView的父布局xml中添加属性 android:clipChildren="false"
 */
public class TabBarView extends LinearLayout {

    /**
     * attribute
     * centerView的bottomMargin
     */
    private int childrenBottomMargin;

    /**
     * tabItemView的展示样式
     * 默认为 ICON_TEXT
     */
    private ItemStyle mItemStyle = ItemStyle.ICON_TEXT;

    /**
     * 最新选择的item的position，-1表示没有选择任何一个
     */
    private int mCheckedPos = -1;

    /**
     * tabItemViews集合
     */
    private List<TabItemView> mTabItemViewList;

    public TabBarView(Context context) {
        super(context);
        init();
    }

    public TabBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TabItemView);
        childrenBottomMargin = attributes.getInt(R.styleable.TabItemView_childrenBottomMargin, 40);
        attributes.recycle();

        init();
    }

    private void init() {
        mTabItemViewList = new ArrayList<>();
    }

    public void setItemStyle(@NonNull ItemStyle itemStyle) {
        this.mItemStyle = itemStyle;
    }

    public void setTabItemViews(@NonNull List<TabItemView> tabItemViews) {
        setTabItemViews(tabItemViews, null);
    }

    public void setTabItemViews(@NonNull List<TabItemView> tabItemViewList, View centerView) {
        /**
         * 不能重复设置mTabItemViewList
         */
        if (mTabItemViewList.size() != 0) {
            throw new RuntimeException("mTabItemViewList cannot be repeated!");
        }

        mTabItemViewList.addAll(tabItemViewList);

        if (mTabItemViewList.size() < 2) {
            throw new RuntimeException("The length of mTabItemViewList must not be less than 2!");
        }

        for (int i = 0; i < mTabItemViewList.size(); i++) {
            if (centerView != null && i == mTabItemViewList.size() / 2) {
                /**
                 * 给centerView设置childrenBottomMargin属性
                 */
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = this.getLayoutParams().height + childrenBottomMargin;
                layoutParams.bottomMargin = childrenBottomMargin;
                layoutParams.gravity = Gravity.BOTTOM;
                centerView.setLayoutParams(layoutParams);
                this.addView(centerView);
            }

            final TabItemView tabItemView = mTabItemViewList.get(i);
            tabItemView.setItemStyle(mItemStyle == ItemStyle.ICON || mItemStyle == ItemStyle.ICON_TEXT,
                    mItemStyle == ItemStyle.TEXT || mItemStyle == ItemStyle.ICON_TEXT);

            this.addView(tabItemView);

            final int currentItemPos = i;
            tabItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentItemPos == mCheckedPos) {
                        return;
                    }
                    check(currentItemPos);
                }
            });
        }

        /**
         * 舒适化所有的tabItemView
         */
        for (TabItemView tab : mTabItemViewList) {
            tab.setStatus(TabItemView.STATE_DEFAULT);
        }

        /**
         * 默认选中第一个
         */
        check(0);
    }

    /**
     * 通过position的选择设置tabItemView的标识，使用-1作为清除标识的选择
     *
     * @param position tabItemView在TabBarView中的序号
     */
    public void check(int position) {
        if (position != -1 && position == mCheckedPos) {
            return;
        }

        if (mCheckedPos != -1) {
            mTabItemViewList.get(mCheckedPos).setStatus(TabItemView.STATE_DEFAULT);
        }

        if (position != -1) {
            mTabItemViewList.get(position).setStatus(TabItemView.STATE_CHECKED);
        }

        setCheckedPos(position);
    }

    private void setCheckedPos(int checkedPos) {
        mCheckedPos = checkedPos;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedPos);
        }
    }

    public TabItemView getCheckedTabItemView() {
        return mTabItemViewList.get(mCheckedPos);
    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    /**
     * 注册一个回调函数，用来检查选项卡的选项更改
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {
        /**
         * <p>Called when the checked tab item has changed. When the
         * selection is cleared, checkedId is -1.</p>
         *
         * @param tabBarView the tabBarView in which the checked tab item has changed
         * @param checkedPos the position of the newly checked tab item
         */
        void onCheckedChanged(TabBarView tabBarView, int checkedPos);
    }

    public enum ItemStyle {
        ICON, TEXT, ICON_TEXT
    }

    /**
     * ItemView
     */
    public static class TabItemView extends LinearLayout {

        /**
         * 状态: checked and default
         */
        public final static int STATE_DEFAULT = 1;
        public final static int STATE_CHECKED = 2;

        /**
         * 标题：tabItemView上显示的文字
         */
        public String title;

        /**
         * 标题的颜色: checked and default
         */
        public int colorDef;
        public int colorChecked;

        /**
         * 图标: checked and default
         */
        public int iconResDef;
        public int iconResChecked;

        public ImageView ivIcon;
        public TextView tvTitle;

        public TabItemView(Context context, String title, int colorDef, int colorChecked,
                           int iconResDef, int iconResChecked) {
            super(context);
            this.title = title;
            this.colorDef = colorDef;
            this.colorChecked = colorChecked;
            this.iconResDef = iconResDef;
            this.iconResChecked = iconResChecked;
            init();
        }

        public void init() {
            View view = LayoutInflater.from(super.getContext()).inflate(R.layout.view_tab_item, this);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);

            tvTitle.setText(title);
        }

        protected void setItemStyle(boolean showIcon, boolean showTitle) {
            ivIcon.setVisibility(showIcon ? VISIBLE : GONE);
            tvTitle.setVisibility(showTitle ? VISIBLE : GONE);
        }

        /**
         * 设置itemView的状态
         */
        public void setStatus(int status) {
            ivIcon.setImageResource(status == STATE_CHECKED ? iconResChecked : iconResDef);
            tvTitle.setTextColor(ContextCompat.getColor(super.getContext(), status == STATE_CHECKED ? colorChecked : colorDef));
        }
    }
}
