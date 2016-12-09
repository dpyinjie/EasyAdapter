package dpyinjie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import dpyinjie.library.adapter.holder.ListHolder;


/**
 * Created by dpyinjie on 16/5/25.
 */
public abstract class BaseArrayAdapter<D> extends ArrayAdapter<D> {

    /** */
    protected Context mContext;
    /**
     * 适配器Item的布局资源id
     */
    protected int mLayoutId;

    /**
     * @param context
     * @param layoutId 适配器Item的布局资源id
     */
    public BaseArrayAdapter(Context context, int layoutId) {
        super(context, layoutId);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    /**
     * @param context
     * @param data     适配器的数据集
     * @param layoutId 适配器Item的布局资源id
     */
    public BaseArrayAdapter(Context context, List<D> data, int layoutId) {
        super(context, layoutId, data);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListHolder holder = getViewHolder(position, convertView, parent);
        bindView(holder, position, getItem(position));
        return holder.getConvertView();
    }

    /**
     * 把适配器维护的数据转换到布局中显示,实现从数据到显示的过程。 子类必须实现此方法来显示数据。
     *
     * @param holder
     * @param position
     * @param data
     */
    public abstract void bindView(ListHolder holder, int position, D data);

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    protected ListHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ListHolder.getHolder(mContext, convertView, parent, mLayoutId, position);
    }

}
