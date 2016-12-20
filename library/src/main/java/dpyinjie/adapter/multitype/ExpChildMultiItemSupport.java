/**
 *
 */
package dpyinjie.adapter.multitype;

public interface ExpChildMultiItemSupport<D> {

    /**
     * @param groupPosition
     * @param childPosition
     * @param data
     * @return
     */
    int getChildLayoutId(int groupPosition, int childPosition, D data);

    /**
     * @param groupPosition
     * @param childPosition
     * @return
     */
    int getChildType(int groupPosition, int childPosition, D data);

    /**
     * @return
     */
    int getChildTypeCount();

}
