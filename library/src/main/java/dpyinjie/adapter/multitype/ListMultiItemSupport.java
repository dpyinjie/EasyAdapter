/**
 *
 */
package dpyinjie.adapter.multitype;

public interface ListMultiItemSupport<D> {

    /**
     * @param viewType
     * @return
     */
    int getItemLayoutId(int viewType);

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
