[![](https://jitpack.io/v/dpyinjie/easy-adapter.svg)](https://jitpack.io/#dpyinjie/easy-adapter)

# EasyAdapter

[示例 APK 下载](http://fir.im/9whv)

简化 Android 适配器编程库,内部都使用了 ViewHolder 模式。 包含 ListView 和 RecyclerView.

个人能力有限, 在使用的过程中如果遇到 Bug , 欢迎提交 Issue。

## Usage

1. 在 Project Level 的 build.gradle 中添加   

	```
	...
	allprojects {
    repositories {
        maven { url "https://jitpack.io" }//添加此行代码
    }
} 
...

	```  
 
2. 在 Module Level 的 build.gradle 中添加   

	`compile 'com.github.dpyinjie:EasyAdapter:1.0.0'`

## ListView 代码示例

### 单种 Item 视图

1. 	适配器类继承 `dpyinjie.adapter.BaseListAdapter<D>` , 实现抽象方法，泛型<D>表示你的适配器所绑定的数据类型。
2. 给你的 ListView 直接设置适配器即可。
3. 数据集变化可通过适配器的一系列方法触发更新 UI。

```
public class UserListAdapter extends BaseListAdapter<User> {

    public UserListAdapter(Context context) {
        super(context, R.layout.item_user_info/*Item layout*/);
    }

    @Override
    public void onBindViews(int itemViewType, ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
        holder.set...
        
        //通过 id 获得 View 对象，即可实现对 View 对象的其它操作
        TextView textView = holder.getView(R.id.tv_name);   
       }
}
```

### 多种 Item 视图

1. 适配器类继承 `dpyinjie.adapter.BaseListAdapter<D>` , 实现抽象方法，泛型<D>表示你的适配器所绑定的数据类型。
2. 新建类实现接口 `ListMultiItemSupport<D>` 
3. 在适配器中的构造方法中设置支持多种 Item, `BaseListAdapter#setMultiItemSupport()`,示例代码

```
public class MultiUserListAdapter extends BaseListAdapter<User> {

    private static final int VIEW_TYPE_1 = 0;

    private static final int VIEW_TYPE_2 = 1;

    public MultiUserListAdapter(Context context) {
        super(context);
        setMultiItemSupport(new MultiItem());//设置支持 Multi Item
    }

    @Override
    protected void onBindViews(int itemViewType, ListHolder holder, int position, User user) {
        if (VIEW_TYPE_1 == itemViewType) {
            bindUser1(holder, position, user);
        } else if (VIEW_TYPE_2 == itemViewType) {
            bindUser2(holder, position, user);
        }
    }

    private void bindUser1(ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private void bindUser2(ListHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }


    private class MultiItem implements ListMultiItemSupport<User> {

        @Override
        public int getItemLayoutId(int viewType) {

            switch (viewType) {

                case VIEW_TYPE_1:
                    return R.layout.item_user_type_1_info;

                case VIEW_TYPE_2:
                    return R.layout.item_user_type_2_info;

            }
            throw new RuntimeException("Unknow viewType  " + viewType);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position, User user) {

            switch (user.getType()) {

                case User.TYPE_1:
                    return VIEW_TYPE_1;

                case User.TYPE_2:
                    return VIEW_TYPE_2;
            }
            throw new RuntimeException("Unknow user type " + user.getType());
        }
    }
}


```

## RecyclerView 代码示例

### 单种 Item 视图

让你的适配器继承 `BaseRecAdapter<D>`， 其它和 ListView 使用方法相同。

```
public class UserRecAdapter extends BaseRecAdapter<User> {


    public UserRecAdapter(Context context) {
        super(context, R.layout.item_user_info/*Item layout*/);
    }

    @Override
    public void onBindViews(int itemViewType, RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
        holder.set...
    }
```   

### 多种 Item 视图

1. 适配器类继承 `BaseRecAdapter<D>`
2. 新建类实现接口 `RecMultiItemSupport<D>` 
3. 在适配器的构造方法中设置支持多种 Item

```
public class MultiUserRecAdapter extends BaseRecAdapter<User> {


    private static final int VIEW_TYPE_1 = 0;

    private static final int VIEW_TYPE_2 = 1;


    public MultiUserRecAdapter(Context context) {
        super(context);
        setMultiItemSupport(new MultiItem());//设置支持 Multi Item
    }

    @Override
    public void onBindViews(int itemViewType, RecHolder holder, int position, User user) {
        if (VIEW_TYPE_1 == itemViewType) {
            bindUser1(holder, position, user);
        } else if (VIEW_TYPE_2 == itemViewType) {
            bindUser2(holder, position, user);
        }
    }

    private void bindUser1(RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private void bindUser2(RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
    }

    private class MultiItem implements RecMultiItemSupport<User> {

        @Override
        public int getItemLayoutId(int viewType) {

            switch (viewType) {

                case VIEW_TYPE_1:
                    return R.layout.item_user_type_1_info;

                case VIEW_TYPE_2:
                    return R.layout.item_user_type_2_info;

            }
            throw new RuntimeException("Unknow viewType  " + viewType);
        }

        @Override
        public int getItemViewType(int position, User user) {

            switch (user.getType()) {

                case User.TYPE_1:
                    return VIEW_TYPE_1;

                case User.TYPE_2:
                    return VIEW_TYPE_2;
            }
            throw new RuntimeException("Unknow user type " + user.getType());
        }
    }
}

```
