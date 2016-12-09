/**
 *
 */
package dpyinjie.adapter.multitype;

/**
 * Created by dpyinjie on 16/5/25.
 */
public interface ListItemMultiSupport<D> {

    /**
     * @param position
     * @param data
     * @return
     */
    int getItemLayoutId(int position, D data);

    /**
     * @return
     * @see android.widget.Adapter#getViewTypeCount()
     */
    int getViewTypeCount();

    /**
     * @param position
     * @param data
     * @return
     * @see android.widget.Adapter#getItemViewType(int)
     */
    int getItemViewType(int position, D data);

}
