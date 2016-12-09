package dpyinjie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpyinjie.adapter.holder.RecHolder;
import dpyinjie.adapter.multitype.ListItemMultiSupport;


/**
 * Created by dpyinjie on 16/5/25.
 */
@SuppressWarnings("unused")
public abstract class BaseRecAdapter<D> extends RecyclerView.Adapter<RecHolder> implements DataManager<D> {

    private final Object mLock = new Object();
    private List<D> mDataSet;
    private List<D> mOriginalDatas;
    private boolean mNotifyOnChange = true;
    private ListItemMultiSupport<D> mMultiViewTypeSupport;
    private int mItemLayoutId;
    private LayoutInflater mInflater;

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
            dataSet = new ArrayList<D>();
        }
        mDataSet = dataSet;
        mItemLayoutId = itemViewLayoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mItemLayoutId, parent, false);
        RecHolder holder = new RecHolder(itemView);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecHolder holder, int position) {
        onBindView(getItemViewType(position), holder, position, getItem(position));
    }

    /**
     * @param itemViewType
     * @param holder
     * @param position
     * @param data
     */
    public abstract void onBindView(int itemViewType, RecHolder holder, int position, D data);


    @Override
    public int getItemViewType(int position) {
        if (mMultiViewTypeSupport != null) {
            return mMultiViewTypeSupport.getItemViewType(position, mDataSet.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * @param position
     * @return
     */
    public D getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getPosition(D item) {
        return 0;
    }

    @Override
    public void update(Collection<D> dataSet) {

    }

    /**
     * @return
     */
    public List<D> getDateSet() {
        return mDataSet;
    }

    // Operate Internal DataManager Methods START*****/

    @Override
    public void add(D object) {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                mOriginalDatas.add(object);
            } else {
                mDataSet.add(object);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void add(int location, D data) {

    }

    @Override
    public void addAll(Collection<D> collection) {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                mOriginalDatas.addAll(collection);
            } else {
                mDataSet.addAll(collection);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void addAll(int location, Collection<D> collection) {

    }

    @Override
    public void addAll(D... items) {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                Collections.addAll(mOriginalDatas, items);
            } else {
                Collections.addAll(mDataSet, items);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void insert(D object, int index) {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                mOriginalDatas.add(index, object);
            } else {
                mDataSet.add(index, object);
            }
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void remove(D object) {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                mOriginalDatas.remove(object);
            } else {
                mDataSet.remove(object);
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void remove(int location) {
        synchronized (mLock) {
            if (mOriginalDatas != null && mOriginalDatas.size() > location) {
                mOriginalDatas.remove(location);
            } else {
                if (mDataSet.size() > location) {
                    mDataSet.remove(location);
                }
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void clear() {
        synchronized (mLock) {
            if (mOriginalDatas != null) {
                mOriginalDatas.clear();
            } else {
                mDataSet.clear();
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    @Override
    public void sort(Comparator<D> comparator) {
        // TODO: 16/8/1
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    @Override
    public int getCount() {
        return getItemCount();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    // Operate Internal DataManager Methods END*****/
}
