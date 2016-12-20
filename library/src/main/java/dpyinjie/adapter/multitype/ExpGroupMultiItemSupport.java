/**
 *
 */
package dpyinjie.adapter.multitype;

public interface ExpGroupMultiItemSupport<G> {

    /**
     * @param groupPosition
     * @param data
     * @return
     */
     int getGroupLayoutId(int groupPosition, G data);

    /**
     * @param groupPosition
     * @return
     */
     int getGroupType(int groupPosition);

    /**
     * @return
     */
     int getGroupTypeCount();

}
