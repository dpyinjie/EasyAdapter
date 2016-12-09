package dpyinjie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpyinjie.adapter.common.CollectionUtil;
import dpyinjie.adapter.common.DataFilter;
import dpyinjie.adapter.common.DataManager;
import dpyinjie.adapter.holder.RecHolder;
import dpyinjie.adapter.multitype.RecItemMultiSupport;


public abstract class BaseRecAdapter<D> extends RecyclerView.Adapter<RecHolder> implements DataManager<D> {

    private final Object mLock = new Object();
    private List<D> mDataSet;
    private List<D> mOriginalDatas;
    private boolean mNotifyOnChange = true;
    private RecItemMultiSupport<D> mMultiItemSupport;
    private int mItemLayoutId;
    private LayoutInflater mInflater;

    private Context mContext;

    /**
     * @param context
     */
    public BaseRecAdapter(Context context) {
        init(context, 0, new ArrayList<D>());
    }

    /**
     * @param context
     * @param itemViewLayoutId
     */
    public BaseRecAdapter(Context context, int itemViewLayoutId) {
        init(context, itemViewLayoutId, new ArrayList<D>());
    }

    /**
     * @param context
     * @param dataSet
     */
    public BaseRecAdapter(Context context, D[] dataSet) {
        init(context, 0, Arrays.asList(dataSet));
    }

    /**
     * @param context
     * @param dataSet
     * @param itemViewLayoutId
     */
    public BaseRecAdapter(Context context, D[] dataSet, int itemViewLayoutId) {
        init(context, itemViewLayoutId, Arrays.asList(dataSet));
    }

    /**
     * @param context
     * @param dataSet
     */
    public BaseRecAdapter(Context context, List<D> dataSet) {
        init(context, 0, dataSet);
    }

    /**
     * @param context
     * @param dataSet
     * @param itemViewLayoutId
     */
    public BaseRecAdapter(Context context, List<D> dataSet, int itemViewLayoutId) {
        init(context, itemViewLayoutId, dataSet);
    }

    /**
     * @param context
     * @param itemViewLayoutId
     * @param dataSet
     */
    private void init(Context context, int itemViewLayoutId, List<D> dataSet) {
        if (dataSet == null) {
            dataSet = new ArrayList<>();
        }
        mContext = context;
        mDataSet = dataSet;
        mItemLayoutId = itemViewLayoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mItemLayoutId, parent, false);
        RecHolder holder = new RecHolder(mContext, itemView);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecHolder holder, int position) {
        onBindViews(getItemViewType(position), holder, position, getItem(position));
    }

    /**
     * @param itemViewType
     * @param holder
     * @param position
     * @param data
     */
    public abstract void onBindViews(int itemViewType, RecHolder holder, int position, D data);


    @Override
    public int getItemViewType(int position) {
        if (mMultiItemSupport != null) {
            return mMultiItemSupport.getItemViewType(position, mDataSet.get(position));
        }
        return super.getItemViewType(position);
    }

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
        if (CollectionUtil.isEmptyOrNull(collection)) {
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
        if (CollectionUtil.isEmptyOrNull(items)) {
            return;
        }
        synchronized (mLock) {
            Collections.addAll(mDataSet, items);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
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
        if (CollectionUtil.isEmptyOrNull(collection)) {
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
        if (CollectionUtil.isEmptyOrNull(items)) {
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
    public D getItem(int position) {
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
    public void filter(DataFilter filter) {
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
}
