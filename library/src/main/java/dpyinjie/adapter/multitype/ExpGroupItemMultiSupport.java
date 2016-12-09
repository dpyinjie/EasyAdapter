/**
 *
 */
package dpyinjie.adapter.multitype;

public interface ExpGroupItemMultiSupport<G> {

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
