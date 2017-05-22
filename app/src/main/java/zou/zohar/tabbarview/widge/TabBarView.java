package zou.zohar.tabbarview.widge;

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
 * 1. setItemStyle(@NonNull ItemStyle itemStyle)   设置tabItemView的展示样式
 * 2. void setTabItemViews(@NonNull List<TabItemView> tabItemViews)   设置tabItemView集合
 * 3. void setTabItemViews(@NonNull List<TabItemView> tabItemViewList, View centerView)   设置tabItemView集合和centerView
 * 4. void check(int position)   切换选中的tabItemView
 * 5. TabItemView getCheckedTabItemView()   返回当前选中的tabItemView
 * 6. void setOnCheckedChangeListener(OnCheckedChangeListener listener)   设置切换tabItemView时的回调接口
 * <p>
 * 注意事项：
 * setTabItemViews() 方法不可重复调用
 */
public class TabBarView extends LinearLayout {

    /**
     * attribute
     * centerView's bottomMargin
     */
    private int childrenBottomMargin;

    /**
     * the style of tabItemView
     * the default is ICON_TEXT
     */
    private ItemStyle mItemStyle = ItemStyle.ICON_TEXT;

    /**
     * record the new checked position
     */
    private int mCheckedPos = -1;

    /**
     * all tabItemViews
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

    /**
     * set tabItemViews
     */
    public void setTabItemViews(@NonNull List<TabItemView> tabItemViews) {
        setTabItemViews(tabItemViews, null);
    }

    /**
     * set tabItemViews and centerView
     */
    public void setTabItemViews(@NonNull List<TabItemView> tabItemViewList, View centerView) {
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
                 * setting childrenBottomMargin for centerView
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
         * initialize all Views
         */
        for (TabItemView tab : mTabItemViewList) {
            tab.setStatus(TabItemView.STATE_DEFAULT);
        }

        check(0);
    }

    /**
     * <p>Sets the selection to the tabItemView whose identifier is passed in
     * parameter. Using -1 as the selection identifier clears the selection;
     * such an operation is equivalent to invoking.</p>
     *
     * @param position the position of the tabItemView to select in this group
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
     * <p>Register a callback to be invoked when the checked tab item
     * changes in this view.</p>
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
     * Item
     */
    public static class TabItemView extends LinearLayout {

        /**
         * STATUS: checked and default
         */
        public final static int STATE_DEFAULT = 1;
        public final static int STATE_CHECKED = 2;

        /**
         * title of the tabItemView
         */
        public String title;

        /**
         * color res of the title: checked and default
         */
        public int colorDef;
        public int colorChecked;

        /**
         * image res of the icon: checked and default
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
         * set the status of this view
         */
        public void setStatus(int status) {
            ivIcon.setImageResource(status == STATE_CHECKED ? iconResChecked : iconResDef);
            tvTitle.setTextColor(ContextCompat.getColor(super.getContext(), status == STATE_CHECKED ? colorChecked : colorDef));
        }
    }
}
