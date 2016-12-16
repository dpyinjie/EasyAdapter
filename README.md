[![](https://jitpack.io/v/dpyinjie/easy-adapter.svg)](https://jitpack.io/#dpyinjie/easy-adapter)

# EasyAdapter

简化 Android 适配器编程库,内部都使用了 ViewHolder 模式。 包含 ListView 和 RecyclerView.

个人能力有限, 在使用的过程中如果遇到 Bug , 欢迎提交 Issue。

[示例 APK 下载](http://fir.im/9whv)

## ListView 代码示例

### 单种 Item 视图

1. 	 适配器类继承 `dpyinjie.adapter.BaseListAdapter<D>` , 实现抽象方法，泛型<D>表示你的适配器所绑定的数据类型。
2. 给你的 ListView 直接设置适配器即可。
3. 数据集变化可通过适配器的一系列方法触发更新 UI。

```
public class UserListAdapter extends BaseListAdapter<User> {

    public UserListAdapter(Context context) {
        super(context, R.layout.item_user_info);
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

## RecyclerView 代码示例

### 单种 Item 视图

让你的适配器继承 `BaseRecAdapter<D>`， 其它和 ListView 使用方法相同。

```
public class UserRecAdapter extends BaseRecAdapter<User> {


    public UserRecAdapter(Context context) {
        super(context, R.layout.item_user_info);
    }

    @Override
    public void onBindViews(int itemViewType, RecHolder holder, int position, User user) {
        holder.setText(R.id.tv_name, user.getName());
        holder.setText(R.id.tv_age, "年龄： " + user.getAge());
        holder.set...
    }
```   

### 多种 Item 视图

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

	`compile 'com.github.dpyinjie:EasyAdapter:0.1.1'`