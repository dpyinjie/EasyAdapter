package dpyinjie.adapter;

import android.widget.Filterable;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by dpyinjie on 16/5/25.
 */
interface DataManager<D> extends Filterable {

    /**
     * @param object
     */
    void add(D object);

    /**
     * @param location
     * @param data
     */
    void add(int location, D data);

    /**
     * @param collection
     */
    void addAll(Collection<D> collection);

    /**
     * @param location
     * @param collection
     */
    void addAll(int location, Collection<D> collection);

    /**
     * @param items
     */
    void addAll(D... items);

    /**
     * @param location
     * @param items
     */
    void addAll(int location, D... items);

    /**
     * @param location
     * @param object
     */
    void insert(int location, D object);

    /**
     * @param object
     */
    void remove(D object);

    /**
     * @param location
     */
    void remove(int location);

    /**
     * @param comparator
     */
    void sort(Comparator<D> comparator);

    /**
     * @return
     */
    Collection<D> getDataSet();

    /**
     *
     */
    void notifyDataSetChanged();

    /**
     * @param notifyOnChange
     */
    void setNotifyOnChange(boolean notifyOnChange);

    /**
     * @return
     */
    int getCount();

    /**
     * @param position
     * @return
     */
    D getItem(int position);

    /**
     * @param item
     * @return
     */
    int getPosition(D item);

    /**
     * @param position
     * @return
     */
    long getItemId(int position);

    /**
     * @param dataSet
     */
    void update(Collection<D> dataSet);

    /**
     *
     */
    void clear();
}
