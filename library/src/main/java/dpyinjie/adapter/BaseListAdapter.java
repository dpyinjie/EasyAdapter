/**
 *
 */
package dpyinjie.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpyinjie.adapter.holder.HoldAble;
import dpyinjie.adapter.holder.ListHolder;
import dpyinjie.adapter.multitype.ListMultiItemSupport;


public abstract class BaseListAdapter<D, H extends HoldAble> extends BaseAdapter implements DataManager<D> {

    private final Object mLock = new Object();
    private Context mContext;
    private int mItemLayoutRes;
    private List<D> mDataSet;
    private boolean mNotifyOnChange = true;
    private ListMultiItemSupport<D> mMultiItemSupport;


    public BaseListAdapter(Context context) {
        init(context, mItemLayoutRes, new ArrayList<D>());
    }


    public BaseListAdapter(Context context, @LayoutRes int itemLayoutRes) {
        init(context, itemLayoutRes, new ArrayList<D>());
    }

    public BaseListAdapter(Context context, @LayoutRes int itemLayoutRes, @Nullable D[] dataSet) {
        if (dataSet == null) {
            init(context, itemLayoutRes, null);
            return;
        }
        init(context, itemLayoutRes, Arrays.asList(dataSet));
    }

    public BaseListAdapter(Context context, D[] dataSet) {
        init(context, mItemLayoutRes, Arrays.asList(dataSet));
    }

    public BaseListAdapter(Context context, @LayoutRes int itemLayoutRes, @Nullable List<D> dataSet) {
        init(context, itemLayoutRes, dataSet);
    }

    public BaseListAdapter(Context context, List<D> dataSet) {
        init(context, mItemLayoutRes, dataSet);
    }

    private void init(Context context, @LayoutRes int itemLayoutRes, @Nullable List<D> dataSet) {
        if (dataSet == null) {
            dataSet = new ArrayList<>();
        }
        mContext = context;
        mItemLayoutRes = itemLayoutRes;
        mDataSet = dataSet;
    }


    @Override
    public int getViewTypeCount() {
        if (mMultiItemSupport != null) {
            return mMultiItemSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemSupport != null) {
            return mMultiItemSupport.getItemViewType(position, mDataSet.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemSupport != null) {
            mItemLayoutRes = mMultiItemSupport.getItemLayoutId(getItemViewType(position));
        }
        H holder = ListHolder.getOrCreateHolder(mContext, convertView, parent, mItemLayoutRes, position);
        onBindViews(getItemViewType(position), holder, position, getItem(position));
        return holder.getConvertView();
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * @return the mMultiItemSupport
     */
    public ListMultiItemSupport<D> getMultiItemSupport() {
        return mMultiItemSupport;
    }

    /**
     * @param multiViewTypeSupport the mMultiItemSupport to set
     */
    public void setMultiItemSupport(ListMultiItemSupport<D> multiViewTypeSupport) {
        this.mMultiItemSupport = multiViewTypeSupport;
    }

    /**
     * @param itemViewType
     * @param holder
     * @param position
     * @param data
     */
    protected abstract void onBindViews(int itemViewType, H holder, int position, D data);


    @Override
    public void add(D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            mDataSet.add(data);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void add(Collection<D> collection) {
        if (Utils.isEmptyOrNull(collection)) {
            return;
        }
        synchronized (mLock) {
            mDataSet.addAll(collection);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void add(D... items) {
        if (Utils.isEmptyOrNull(items)) {
            return;
        }
        add(Arrays.asList(items));
    }

    @Override
    public void insert(int position, D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            mDataSet.add(position, data);
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void insert(int position, Collection<D> collection) {
        if (Utils.isEmptyOrNull(collection)) {
            return;
        }
        synchronized (mLock) {
            mDataSet.addAll(position, collection);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void insert(int position, D... items) {
        if (Utils.isEmptyOrNull(items)) {
            return;
        }
        synchronized (mLock) {
            mDataSet.addAll(position, Arrays.asList(items));
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void remove(D data) {
        if (data == null) {
            return;
        }
        synchronized (mLock) {
            mDataSet.remove(data);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void remove(int position) {
        synchronized (mLock) {
            if (mDataSet.size() > position) {
                mDataSet.remove(position);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void sort(Comparator<D> comparator) {
        if (comparator == null) {
            return;
        }
        synchronized (mLock) {
            Collections.sort(mDataSet, comparator);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public Collection<D> getDataSet() {
        return mDataSet;
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public D getItem( int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getPosition(D item) {
        return item == null ? -1 : mDataSet.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void update(Collection<D> dataSet) {
        if (dataSet == null) {
            return;
        }
        synchronized (mLock) {
            mDataSet.clear();
            mDataSet.addAll(dataSet);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void clear() {
        synchronized (mLock) {
            mDataSet.clear();
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void reverse() {
        synchronized (mLock) {
            Collections.reverse(mDataSet);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void filter(Filter filter) {
        ArrayList<D> dataSet = new ArrayList<>(getCount());
        synchronized (mLock) {
            for (D d : mDataSet) {
                if (filter.accept(d)) {
                    dataSet.add(d);
                }
            }
            //
            mDataSet = dataSet;
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mDataSet.isEmpty();
    }

    @Override
    public void refresh(Collection<D> dataSet) {
        synchronized (mLock) {
            mDataSet.clear();
            mDataSet.addAll(dataSet);
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void refresh(D... items) {
        refresh(Arrays.asList(items));
    }
}
