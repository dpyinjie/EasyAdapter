package dpyinjie.adapter;

import android.widget.Filterable;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by dpyinjie on 16/5/25.
 */
@SuppressWarnings("ALL")
public interface DataSet<D> extends Filterable {

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    void add(D object);

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    void addAll(Collection<? extends D> collection);

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    void addAll(D... items);

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    void insert(D object, int index);

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    void remove(D object);

    /**
     * @param location
     */
    void remove(int location);

    /**
     * Remove all elements from the list.
     */
    void clear();

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this
     *                   adapter.
     */
    void sort(Comparator<? super D> comparator);

    /**
     * {@inheritDoc}
     */
    void notifyDataSetChanged();

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
    void setNotifyOnChange(boolean notifyOnChange);

    /**
     * {@inheritDoc}
     */
    int getCount();

    /**
     * {@inheritDoc}
     */
    D getItem(int position);

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    int getPosition(D item);

    /**
     * {@inheritDoc}
     */
    long getItemId(int position);

}
