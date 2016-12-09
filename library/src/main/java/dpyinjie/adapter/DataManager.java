package dpyinjie.adapter;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by dpyinjie on 16/5/25.
 */
interface DataManager<D>  {

    /**
     * 在末尾添加一条数据
     *
     * @param data
     */
    void add(D data);

    /**
     * 在末尾添加一个集合
     *
     * @param collection
     */
    void add(Collection<D> collection);

    /**
     * 在末尾添加一个集合
     *
     * @param items
     */
    void add(D... items);

    /**
     * 在指定位置插入一条数据
     *
     * @param position
     * @param data
     */
    void insert(int position, D data);

    /**
     * 在指定位置插入一个集合
     *
     * @param position
     * @param collection
     */
    void insert(int position, Collection<D> collection);


    /**
     * 在指定位置插入一个集合
     *
     * @param position
     * @param items
     */
    void insert(int position, D... items);


    /**
     * 删除指定数据
     *
     * @param data
     */
    void remove(D data);

    /**
     * 删除指定位置的数据
     *
     * @param position
     */
    void remove(int position);


    /**
     * 对数据进行排序
     *
     * @param comparator
     */
    void sort(Comparator<D> comparator);

    /**
     * 获取数据集合
     *
     * @return
     */
    Collection<D> getDataSet();

    /**
     * 触发数据改变
     */
    void notifyDataSetChanged();

    /**
     * 设置是否出发数据改变
     *
     * @param notifyOnChange
     */
    void setNotifyOnChange(boolean notifyOnChange);

    /**
     * 获取数据条数
     *
     * @return
     */
    int getCount();

    /**
     * 获取指定位置的数据
     *
     * @param position
     * @return
     */
    D getItem(int position);

    /**
     * 获取指定元素的位置
     *
     * @param item
     * @return
     */
    int getPosition(D item);

    /**
     * 获取指定位置元素的 ID
     *
     * @param position
     * @return
     */
    long getItemId(int position);

    /**
     * 更新数据集合
     *
     * @param dataSet
     */
    void update(Collection<D> dataSet);

    /**
     * 清空数据集合
     */
    void clear();

    /**
     * 逆向排序
     */
    void reverse();

    /**
     * @param filter
     */
    void filter(DataFilter filter);

}
