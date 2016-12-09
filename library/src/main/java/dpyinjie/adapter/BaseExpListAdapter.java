/**
 *
 */
package dpyinjie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dpyinjie.library.adapter.holder.ListHolder;
import dpyinjie.library.adapter.multitype.ExpGroupItemMultiSupport;
import dpyinjie.library.adapter.multitype.ExpItemMultiSupport;


/**
 * Created by dpyinjie on 16/5/25.
 */
public abstract class BaseExpListAdapter<G, C> extends BaseExpandableListAdapter {

    private Context mContext;
    private int mGroupLayoutId;
    private int mChildLayoutId;
    private List<G> mGroupDataSet;
    private List<List<C>> mChildDataSet;
    // 当数据集改变的时候是否刷新视图
    private boolean mNotifyOnChange = true;
    // 在移除一条Child数据的时候如果当前Group没有Child数据的时候是否移除当前的Group
    private boolean mRemoveGroupWhenChildIsEmpty = true;
    private final Object mLock = new Object();
    private ExpGroupItemMultiSupport<G> mGroupItemMultiTypeSupport;
    private ExpItemMultiSupport<C> mChildItemMultiTypeSupport;

    /**
     * @param context
     */
    public BaseExpListAdapter(Context context) {
        this(context, 0, 0, new LinkedList<G>(), new LinkedList<List<C>>());
    }

    /**
     * @param context
     * @param groupData
     * @param childData
     */
    public BaseExpListAdapter(Context context, List<G> groupData, List<List<C>> childData) {
        this(context, 0, 0, groupData, childData);
    }

    /**
     * @param context
     * @param groupLayoutId
     * @param childLayoutId
     * @param groupData
     * @param childData
     */
    public BaseExpListAdapter(Context context, int groupLayoutId, int childLayoutId, List<G> groupData,
                              List<List<C>> childData) {
        super();
        init(context, groupLayoutId, childLayoutId, groupData, childData);
    }

    /**
     * @param context
     * @param groupLayoutId
     * @param childLayoutId
     * @param groupData
     * @param childData
     */
    private void init(Context context, int groupLayoutId, int childLayoutId, List<G> groupData,
                      List<List<C>> childData) {
        if (groupData == null) {
            groupData = new ArrayList<G>();
        }
        if (childData == null) {
            childData = new ArrayList<List<C>>();
        }
        mContext = context;
        mGroupDataSet = groupData;
        mChildDataSet = childData;
        mGroupLayoutId = groupLayoutId;
        mChildLayoutId = childLayoutId;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (mGroupItemMultiTypeSupport != null) {
            mGroupLayoutId = mGroupItemMultiTypeSupport.getGroupLayoutId(groupPosition, getGroup(groupPosition));
        }
        ListHolder holder = getViewHolder(groupPosition, convertView, mGroupLayoutId, parent);
        bindGroupView(holder, groupPosition, isExpanded, getGroup(groupPosition));
        return holder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (mChildItemMultiTypeSupport != null) {
            mChildLayoutId = mChildItemMultiTypeSupport.getChildLayoutId(groupPosition, childPosition,
                    getChild(groupPosition, childPosition));
        }
        ListHolder holder = getViewHolder(groupPosition, convertView, mChildLayoutId, parent);
        bindChildView(holder, groupPosition, childPosition, isLastChild, getChild(groupPosition, childPosition));
        return holder.getConvertView();
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    protected ListHolder getViewHolder(int position, View convertView, int layoutId, ViewGroup parent) {
        return ListHolder.getHolder(mContext, convertView, parent, layoutId, position);
    }

    /**
     * @param groupItemMultiTypeSupport the mMultiExpandableItemTypeSupport to set
     */
    public void setGroupItemMultiTypeSupport(ExpGroupItemMultiSupport<G> groupItemMultiTypeSupport) {
        mGroupItemMultiTypeSupport = groupItemMultiTypeSupport;
    }

    /**
     * @param childItemMultiTypeSupport the mChildMultiTypeSupport to set
     */
    public void setChildItemMultiTypeSupport(ExpItemMultiSupport<C> childItemMultiTypeSupport) {
        this.mChildItemMultiTypeSupport = childItemMultiTypeSupport;
    }

    /**
     * Bind the Data to the Group View. sub Class must implements this method to
     * show data.
     *
     * @param holder
     * @param groupPosition
     * @param isExpanded
     * @param g
     */
    protected abstract void bindGroupView(ListHolder holder, int groupPosition, boolean isExpanded, G g);

    /**
     * Bind the Data to the Child View. sub class must implements this method to
     * show data.
     *
     * @param holder
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param c
     */
    protected abstract void bindChildView(ListHolder holder, int groupPosition, int childPosition,
                                          boolean isLastChild, C c);

    @Override
    public int getGroupCount() {
        return mGroupDataSet.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildDataSet.get(groupPosition).size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mGroupDataSet.get(groupPosition);
    }

    /**
     * @param groupPosition
     * @return
     */
    public List<C> getChilds(int groupPosition) {
        return mChildDataSet.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return getChilds(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (mChildItemMultiTypeSupport != null) {
            return mChildItemMultiTypeSupport.getChildType(groupPosition, childPosition,
                    getChild(groupPosition, childPosition));
        }
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getChildTypeCount() {
        if (mChildItemMultiTypeSupport != null) {
            return mChildItemMultiTypeSupport.getChildTypeCount();
        }
        return super.getChildTypeCount();
    }

    @Override
    public int getGroupType(int groupPosition) {
        if (mGroupItemMultiTypeSupport != null) {
            mGroupItemMultiTypeSupport.getGroupType(groupPosition);
        }
        return super.getGroupType(groupPosition);
    }

    @Override
    public int getGroupTypeCount() {
        if (mGroupItemMultiTypeSupport != null) {
            mGroupItemMultiTypeSupport.getGroupTypeCount();
        }
        return super.getGroupTypeCount();
    }

    /**
     * @return the mContext
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * @return the mNotifyOnChange
     */
    public boolean isNotifyOnChange() {
        return mNotifyOnChange;
    }

    /**
     * @param notifyOnChange the mNotifyOnChange to set
     */
    public void setNotifyOnChange(boolean notifyOnChange) {
        this.mNotifyOnChange = notifyOnChange;
    }

    /**
     * @return the mRemoveGroupWhenChildIsEmpty
     */
    public boolean isRemoveGroupWhenChildIsEmpty() {
        return mRemoveGroupWhenChildIsEmpty;
    }

    /**
     * @param removeGroupWhenChildIsEmpty the mRemoveGroupWhenChildIsEmpty to set
     */
    public void setRemoveGroupWhenChildIsEmpty(boolean removeGroupWhenChildIsEmpty) {
        this.mRemoveGroupWhenChildIsEmpty = removeGroupWhenChildIsEmpty;
    }

    // ****** 自定义增删方法区域 START ***//
    // ****************Group 操作*************************//

    /**
     * @param group
     */
    public void addGroup(G group) {
        // TODO
        synchronized (mLock) {
            mGroupDataSet.add(group);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();

    }

    public void addGroup(G... groups) {
        // TODO
    }

    /**
     * @param groups
     */
    public void addGroup(List<G> groups) {
        // TODO
        synchronized (mLock) {
            mGroupDataSet.addAll(groups);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    public void removeGroup(G group) {
        // TODO
    }

    /**
     * @param groupPosition
     */
    public void removeGroup(int groupPosition) {
        // TODO
        synchronized (mLock) {
            mGroupDataSet.remove(groupPosition);
            mChildDataSet.remove(groupPosition);
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    public void insertGroup(G group, int position) {
        // TODO
    }

    // ***************** Child Operate************************//
    public void addChild(int groupPosition, C child) {
        // TODO
    }

    public void addChild(int groupPosition, C... children) {
        // TODO
    }

    /**
     * @param groupPosition
     * @param children
     */
    public void addChild(int groupPosition, List<List<C>> children) {
        // TODO
        synchronized (mLock) {
            mChildDataSet.addAll(mChildDataSet);
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        synchronized (mLock) {
            mGroupDataSet.clear();
            mChildDataSet.clear();
        }
        if (mNotifyOnChange) {
            notifyDataSetChanged();
        }
    }

    /**
     * @param groupPosition
     * @param childPosition
     */
    public void removeChild(int groupPosition, int childPosition) {
        synchronized (mLock) {
            if (getGroupCount() > groupPosition && mChildDataSet.get(groupPosition).size() > childPosition) {
                mChildDataSet.get(groupPosition).remove(childPosition);
                //
                if (isRemoveGroupWhenChildIsEmpty() && mChildDataSet.get(groupPosition).size() == 0) {
                    // 删除Child的时候如果Group中没有数据了,删除Group
                    mChildDataSet.remove(groupPosition);
                    mGroupDataSet.remove(groupPosition);
                }
            }
        }
        if (isNotifyOnChange())
            notifyDataSetChanged();
        if (mGroupDataSet.size() == 0) {
            listener.showEmptyView();
        }
    }


    /**
     * @param child
     * @param groupPosition
     */
    public void removeChild(C child, int groupPosition) {
        // TODO
        synchronized (mLock) {
            if (mChildDataSet.get(groupPosition).indexOf(child) != -1) {
                mChildDataSet.get(groupPosition).remove(child);
            }
            if (mChildDataSet.get(groupPosition).size() == 0) {
                removeGroup(groupPosition);
                return;
            }
        }
        if (mNotifyOnChange)
            notifyDataSetChanged();

    }

    private emptyViewShowListener listener;

    public void insertChild(int groupPosition, int childPosition, C child) {
        // TODO
    }

    // ****** Operate method END ***//

    //当全部删除了购物车后,显示空页面
    public interface emptyViewShowListener {
        void showEmptyView();
    }

    public void setEmptyViewListener(emptyViewShowListener listener) {
        this.listener = listener;
    }
}
