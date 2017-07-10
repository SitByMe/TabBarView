# TabBarView
自定义TabBarView（仿RadioGroup），可设置展示样式，快速实现Tab+ViewPager的Activity

使用方法一：</br>
1. 将项目下载到本地
  
  ```
  git clone https://github.com/SitByMe/TabBarView.git
  ```
2. 将项目添加进您自己的项目中
3. 新建一个Activity继承至TabWithViewPagerBaseActivity 或 新建一个Fragment继承至TabWithViewPagerBaseFragment，然后重写几个抽象方法：</br>
  ```
  public abstract TabBarView.ItemStyle getItemStyle();

  public abstract List<TabBarView.TabItemView> getTabViews();

  public abstract List<Fragment> getFragments();

  public abstract View getCenterView();

  public abstract @LayoutRes int getContentLayout();
  ```

使用方法二：</br>
1. 复制以下文件到你的项目中
  ```
  zou.zohar.tabbarview.base.TabBarView
  zou.zohar.tabbarview.base.TabWithViewPagerBaseActivity
  zou.zohar.tabbarview.base.TabWithViewPagerBaseFragment
  R.layout.view_tab_item
  ```
2. 添加declare-styleable
  ```
  <declare-styleable name="TabItemView">
      <attr name="childrenBottomMargin" format="integer" />
  </declare-styleable>
  ```
3. 新建一个Activity继承至TabWithViewPagerBaseActivity 或 新建一个Fragment继承至TabWithViewPagerBaseFragment，然后重写几个抽象方法：</br>
  ```
  public abstract TabBarView.ItemStyle getItemStyle();

  public abstract List<TabBarView.TabItemView> getTabViews();

  public abstract List<Fragment> getFragments();

  public abstract View getCenterView();

  public abstract @LayoutRes int getContentLayout();

  ```

---
## API guides
### TabBarView.java：
public class TabBarView extends LinearLayout</bar>

|Nested classes|        |
|------------- |:-------|
|enum          |`TabBarView.ItemStyle`</br>Item的展示样式枚举，有三种样式：ICON, TEXT, ICON_TEXT|
|interface     |`TabBarView.OnCheckedChangeListener`</br>点击Item切换tab时的回调|
|class         |`TabBarView.TabItemView`</br>ItemView，继承至LinearLayout|

|XML added attributes|      |
|-------------       |:-------|
|childrenBottomMargin|centerView相对于TabBarView的底部外边距，默认为40。</br>`eg. childrenBottomMargin="40"`|

| Public methods|               |
| ------------- |:--------------|
| void          |`setItemStyle(@NonNull ItemStyle itemStyle)`</br>设置tabItemView的展示样式|
| void          |`setTabItemViews(@NonNull List<TabItemView> tabItemViews)`</br>设置tabItemView集合|
| void          |`setTabItemViews(@NonNull List<TabItemView> tabItemViewList, View centerView)`</br>设置tabItemView集合和centerView|
| void          |`void check(int position)`</br>切换选中的tabItemView|
| TabItemView   |`getCheckedTabItemView()`</br>返回当前选中的tabItemView|
| void          |`setOnCheckedChangeListener(OnCheckedChangeListener listener)`</br>设置切换tabItemView时的回调接口|

**注意事项：**</br>
1. setTabItemViews() 方法不可重复调用
2. 如果需要有一个子超出父布局位置限制的centerView的话，需要在TabBarView的父布局xml中添加属性 android:clipChildren="false"
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_home_with_tab"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clipChildren="false">

    <zou.zohar.tabbarview.base.TabBarView
        android:id="@+id/tabBarView"
        android:layout_width="match_parent"
        android:layout_height="46dp"
        tools:childrenBottomMargin="40" />
</RelativeLayout>
```

---
### TabWithViewPagerBaseActivity.java
public abstract class TabWithViewPagerBaseActivity extends AppCompatActivity</br>

| Abstract methods     |               |
| -------------------- |:--------------|
| TabBarView.ItemStyle |`getItemStyle()`</br>返回tabItemView的展示样式|
| List<TabBarView.TabItemView> |`getTabViews()`</br>提供tabItemView的集合，不可返回null|
| List< Fragment> |`getFragments()`</br>提供Fragment的集合，不可返回null|
| View |`getCenterView()`</br>提供一个中间按钮，可返回null|
| int |`getContentLayout()`</br>提供xml布局文件|

**注意事项：**</br>
1. 你的Activity的onCreat()方法中不能调用setContentView(int resLayout)方法，xmlLayout必须由getContentLayout()方法提供
2. 由getContentLayout()方法提供的xml布局文件中必须得至少包含一个id为R.id.tabBarView的TabBarView和一个id为R.id.viewpager的ViewPager
3. getTabViews()和getFragments()分别提供的集合的长度必须一致
