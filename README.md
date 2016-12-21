[![](https://jitpack.io/v/dpyinjie/easy-adapter.svg)](https://jitpack.io/#dpyinjie/easy-adapter)

# EasyAdapter

[示例 APK 下载](http://fir.im/9whv)

简化 Android 适配器编程库,内部都使用了 ViewHolder 模式。 包含 ListView , GridView , RecyclerView.

### ListView

**注：不同的颜色代表不同的 ItemType**

![ListView single Item](https://raw.githubusercontent.com/dpyinjie/easy-adapter/master/raw/list_single_item.png)
![ListView Multi Item](https://raw.githubusercontent.com/dpyinjie/easy-adapter/master/raw/list_multi_item.png)

### RecyclerView

**注：不同的颜色代表不同的 ItemType**

![RecyclerView single Item](https://raw.githubusercontent.com/dpyinjie/easy-adapter/master/raw/rec_single_item.png)
![RecyclerView single Item](https://raw.githubusercontent.com/dpyinjie/easy-adapter/master/raw/rec_multi_item.png)


## Integrate

1. 在 Project Level 的 build.gradle 中添加 jitpack 仓库。  

	```
maven { url "https://jitpack.io" }
	 
	```  
 
2. 在 Module Level 的 build.gradle 中添加依赖   

	`compile 'com.github.dpyinjie:easy-adapter:1.0.0'`

## ListView Sample

### Single Item

1. 	适配器类继承 `dpyinjie.adapter.BaseListAdapter<D>` , 实现抽象方法，泛型<D>表示你的适配器所绑定的数据类型。
2. 数据集变化可通过适配器的一系列方法触发更新 UI (请看如下示例代码)。

```
public class UserListAdapter extends BaseListAdapter<User> {

    public UserListAdapter(Context context) {
        super(context, R.layout.item_user_info/*Item layout*/);
    }

    @Override
    public void onBindViews(int itemViewType, ListHolder holder, int position, User user) {
   
   // holder 内部使用了一个 SparseArray 来缓存控件
    // holder 提供了一系列更新 UI 控件的方法，分别与该 View 的一些 set... 方法相对应。
    
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
        holder.set...
        
        //通过 id 获取任何 View 对象，即可实现对 View 对象的其它操作。
        TextView textView = holder.getView(R.id.tv_name);   
        
       }
}
```

3. 触发数据集合更新

可以直接通过适配器的一系列方法触发数据更新，刷新列表。

```
adapter.add..
adapter.remove...
adapter.insert...
adapter.clear...

```
这些方法定义在 `dpyinjie.adapter.DataManager` 这个接口中。

### Multi Item

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

## RecyclerView Sample

### Single Item

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

### Multi Item

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

## Thanks

[hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)

[JoanZapata/base-adapter-helper](https://github.com/JoanZapata/base-adapter-helper)