/**
 *
 */
package dpyinjie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpyinjie.adapter.holder.ListHolder;
import dpyinjie.adapter.multitype.ListItemMultiSupport;


/**
 * Created by dpyinjie on 16/5/25.
 */
@SuppressWarnings("unused")
public abstract class BaseListAdapter<D> extends BaseAdapter {

    private final Object mLock = new Object();
    private Context mContext;
    private int mResource;
    private List<D> mDataSet;
    private List<D> mOriginalDataSet;
    private boolean mNotifyOnChange = true;
    private ListItemMultiSupport<D> mMultiViewTypeSupport;

    /**
     * @param context The current context.
     */
    public BaseListAdapter(Context context) {
        init(context, mResource, new ArrayList<D>());
    }

    /**
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a layout to use
     *                 when instantiating views.
     */
    public BaseListAdapter(Context context, int resource) {
        init(context, resource, new ArrayList<D>());
    }

    /**
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a layout to use
     *                 when instantiating views.
     * @param dataSet  The objects to represent in the ListView.
     */
    public BaseListAdapter(Context context, int resource, D[] dataSet) {
        if (dataSet == null) {
            init(context, resource, null);
            return;
        }
        init(context, resource, Arrays.asList(dataSet));
    }

    /**
     * @param context The current context.
     * @param dataSet The objects to represent in the ListView.
     */
    public BaseListAdapter(Context context, D[] dataSet) {
        init(context, mResource, Arrays.asList(dataSet));
    }

    /**
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a layout to use
     *                 when instantiating views.
     * @param dataSet  The objects to represent in the ListView.
     */
    public BaseListAdapter(Context context, int resource, List<D> dataSet) {
        init(context, resource, dataSet);
    }

    /**
     * @param context The current context.
     * @param dataSet The objects to represent in the ListView.
     */
    public BaseListAdapter(Context context, List<D> dataSet) {
        init(context, mResource, dataSet);
    }

    /**
     * @param context
     * @param resourceId
     * @param dataSet
     */
    private void init(Context context, int resourceId, List<D> dataSet) {
        if (dataSet == null) {
            dataSet = new ArrayList<D>();
        }
        this.mContext = context;
        this.mResource = resourceId;
        this.mDataSet = dataSet;
    }

    /**
     * @return the mDataSet
     */
    public List<D> getDataSet() {
        return mDataSet;
    }

    /**
     * @param dataSet the mDataSet to set
     */
    public void setDataSet(List<D> dataSet) {
        if (dataSet != null) {
            mDataSet = dataSet;
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 过滤数据
     *
     * @param filter
     */
    public void filter(DataFilter<D> filter) {
        if (filter == null) {
            return;
        }
        List<D> newDataSet = new ArrayList<D>();
        synchronized (mLock) {
            for (int i = 0, count = getCount(); i < count; i++) {
                D data = getItem(i);
                if (filter.accept(data)) {
                    newDataSet.add(data);
                }
            }
            setDataSet(newDataSet);
        }
    }

    /**
     * 添加一个对象到末尾
     *
     * @param data
     */
    public void add(D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.add(data);
            } else {
                mDataSet.add(data);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 添加一个对象到指定的位置
     *
     * @param data
     */
    public void add(int location, D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.add(location, data);
            } else {
                mDataSet.add(location, data);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 添加一个集合到末尾
     *
     * @param object
     */
    public void addAll(Collection<D> object) {
        if (object == null || object.size() == 0) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.addAll(object);
            } else {
                mDataSet.addAll(object);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 添加一个集合到指定的位置
     *
     * @param location
     * @param collection
     */
    public void addAll(int location, Collection<D> collection) {
        if (collection == null || collection.size() == 0) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.addAll(location, collection);
            } else {
                mDataSet.addAll(location, collection);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 添加一些数据到结尾
     *
     * @param items
     */
    public void addAll(D... items) {
        if (items == null || items.length == 0) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                Collections.addAll(mOriginalDataSet, items);
            } else {
                Collections.addAll(mDataSet, items);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 向数据集合中指定的序号插入对象
     *
     * @param data  The object to insert into the array.
     * @param index The index at which the object must be inserted.
     */
    public void insert(D data, int index) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.add(index, data);
            } else {
                mDataSet.add(index, data);
            }
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    /**
     * 移除数据集合中指定的对象
     *
     * @param data The object to remove.
     */
    public void remove(D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.remove(data);
            } else {
                mDataSet.remove(data);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 移除数据集合中指定的对象
     *
     * @param location
     */
    public void remove(int location) {
        synchronized (mLock) {
            if (mOriginalDataSet != null && mOriginalDataSet.size() > location) {
                mOriginalDataSet.remove(location);
            } else {
                if (mDataSet.size() > location) {
                    mDataSet.remove(location);
                }
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 清空数据集合
     */
    public void clear() {
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                mOriginalDataSet.clear();
            } else {
                mDataSet.clear();
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 排序
     *
     * @param comparator The comparator used to sort the objects contained in this
     *                   adapter.
     */
    public void sort(Comparator<D> comparator) {
        synchronized (mLock) {
            if (mOriginalDataSet != null) {
                Collections.sort(mOriginalDataSet, comparator);
            } else {
                Collections.sort(mDataSet, comparator);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mNotifyOnChange = true;
    }

    /**
     * Control whether methods that change the list ({@link #add},
     * {@link #insert}, {@link #remove}, {@link #clear}) automatically call
     * {@link #notifyDataSetChanged}. If set to false, caller must manually call
     * notifyDataSetChanged() to have the changes reflected in the attached
     * view.
     * <p/>
     * The default is true, and calling notifyDataSetChanged() resets the flag
     * to true.
     *
     * @param notifyOnChange if true, modifications to the list will automatically call
     *                       {@link #notifyDataSetChanged}
     */
    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    /**
     * Returns the context associated with this array adapter. The context is
     * used to create views from the resource passed to the .
     *
     * @return The Context associated with this adapter.
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return mDataSet.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getItem(int position) {
        return mDataSet.get(position);
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    public int getPosition(D item) {
        return mDataSet.indexOf(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiViewTypeSupport != null) {
            return mMultiViewTypeSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiViewTypeSupport != null) {
            return mMultiViewTypeSupport.getItemViewType(position, mDataSet.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiViewTypeSupport != null) {
            mResource = mMultiViewTypeSupport.getItemLayoutId(position, getItem(position));
        }
        ListHolder holder = ListHolder.getHolder(mContext, convertView, parent, mResource, position);
        onBindView(getItemViewType(position), holder, position, getItem(position));
        return holder.getConvertView();
    }

    /**
     * @return the mMultiViewTypeSupport
     */
    public ListItemMultiSupport<D> getMultiViewTypeSupport() {
        return mMultiViewTypeSupport;
    }

    /**
     * @param multiViewTypeSupport the mMultiViewTypeSupport to set
     */
    public void setMultiViewTypeSupport(ListItemMultiSupport<D> multiViewTypeSupport) {
        this.mMultiViewTypeSupport = multiViewTypeSupport;
    }

    /**
     * 把适配器维护的数据转换到布局中显示,实现从数据到显示的过程
     *
     * @param itemViewType
     * @param holder
     * @param position
     * @param data
     */
    public abstract void onBindView(int itemViewType, ListHolder holder, int position, D data);
}
