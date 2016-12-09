/**
 *
 */
package dpyinjie.adapter.multitype;

/**
 * Created by dpyinjie on 16/5/25.
 */
public interface ListItemMultiSupport<D> {

    /**
     * 获取对应的 Item 数据的布局 ID
     *
     * @param position
     * @param data
     * @return
     */
    int getItemLayoutId(int position, D data);

    /**
     * Returns the number of types of Views.
     *
     * @return
     * @see android.widget.Adapter#getViewTypeCount()
     */
    int getViewTypeCount();

    /**
     * Get the type of View for the specified item.
     *
     * @param position
     * @param data
     * @return
     * @see android.widget.Adapter#getItemViewType(int)
     */
    int getItemViewType(int position, D data);

}
